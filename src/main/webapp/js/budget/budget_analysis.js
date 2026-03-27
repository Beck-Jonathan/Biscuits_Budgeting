$(document).ready(function() {

    // --- 0. State Variables ---
    let currentData = window.initialData || [];
    let xAxisLabels = [];
    let mode = "0";
    let level = "0";
    let colorMap = {};
    let selectedIndex = 0;

    // Goal Percentages (State)
    let savingsPct = 50;
    let slushPct = 30;
    let bigPct = 20;

    let forecastBigProjectFunds = [];
    let activeBudgets = [];

    // --- 1. Helper Functions ---
    const getMonthName = (m) => ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"][parseInt(m) - 1] || "N/A";

    const refreshColorMap = () => {
        if (!currentData || currentData.length === 0) return;
        currentData.forEach(period => {
            period.forEach(cat => {
                if (cat.category_Name && cat.color_id) {
                    colorMap[cat.category_Name] = cat.color_id;
                }
            });
        });
    };

    const updateSidebar = () => {
        const list = $('#categoryList').empty();
        if (!currentData || currentData.length === 0) return;
        currentData[0].forEach(cat => {
            const color = colorMap[cat.category_Name] || '#bdc3c7';
            list.append(`
                <li class="category-item p-2 border-bottom">
                    <div class="form-check d-flex align-items-center">
                        <input class="form-check-input cat-check me-2" type="checkbox" 
                               value="${cat.category_Name}" id="c_${cat.category_ID}" checked>
                        <span class="color-swatch" style="background-color:${color}; width:12px; height:12px; border-radius:50%; margin-right:10px; display:inline-block;"></span>
                        <label class="form-check-label small" for="c_${cat.category_ID}">${cat.category_Name}</label>
                    </div>
                </li>`);
        });
    };

    // --- 2. Multi-Tab Slider Sync Logic ---
    const handleSliderChange = (event) => {
        const $el = $(event.target);
        const $allS = $('.savingsPct'), $allL = $('.slushPct'), $allB = $('.bigPct');
        const $dispS = $('.savingsDisp'), $dispL = $('.slushDisp'), $dispB = $('.bigDisp');

        let sV, lV, bV;
        if ($el.hasClass('savingsPct')) {
            sV = parseInt($el.val());
            let remain = 100 - sV;
            lV = Math.round(remain * 0.6);
            bV = 100 - sV - lV;
        } else if ($el.hasClass('slushPct')) {
            lV = parseInt($el.val());
            let remain = 100 - lV;
            sV = Math.round(remain * 0.7);
            bV = 100 - lV - sV;
        } else {
            bV = parseInt($el.val());
            let remain = 100 - bV;
            sV = Math.round(remain * 0.7);
            lV = 100 - bV - sV;
        }

        savingsPct = sV;
        slushPct = lV;
        bigPct = bV;
        $allS.val(sV);
        $allL.val(lV);
        $allB.val(bV);
        $dispS.text(sV + '%');
        $dispL.text(lV + '%');
        $dispB.text(bV + '%');

        renderCharts();
    };

    // --- 3. Main Rendering Engine ---
    const renderCharts = () => {
        if (!currentData || currentData.length === 0) return;

        const startingBalance = parseFloat($('#openingBalance').val()) || 0;
        const selectedCats = $('.cat-check:checked').map(function () {
            return this.value;
        }).get();

        let trendSeries = [];
        let cashPoints = [];
        let pieData = [];
        let runningCash = startingBalance;
        let allocationHtml = "";
        forecastBigProjectFunds = [];

        xAxisLabels = currentData.map((period, index) => {
            const first = period[0];
            let label = (mode === "1") ? getMonthName(first.year) : (first.year || "N/A");

            if (mode === "2") {
                if (first.sign && first.sign.includes('-')) {
                    const pts = first.sign.split('-');
                    label = `${getMonthName(pts[1])} ${pts[0].substring(2)}`;
                }
            }

            let periodNet = 0;
            period.forEach(cat => {
                if (selectedCats.includes(cat.category_Name)) {
                    const type = (cat.transactionType || 'expense').toLowerCase();
                    const amt = Math.abs(cat.amount);
                    if (type === 'income') periodNet += amt;
                    else periodNet -= amt;

                    if (index === selectedIndex && type !== 'income') {
                        pieData.push({name: cat.category_Name, y: amt, color: colorMap[cat.category_Name]});
                    }
                }
            });

            runningCash += periodNet;
            cashPoints.push(runningCash);

            const totalGain = runningCash - startingBalance;
            const bAmt = totalGain > 0 ? (totalGain * (bigPct / 100)) : 0;
            forecastBigProjectFunds.push({label: label, amount: bAmt});

            if (mode === "2") {
                const sAmt = totalGain > 0 ? (totalGain * (savingsPct / 100)) : 0;
                const lAmt = totalGain > 0 ? (totalGain * (slushPct / 100)) : 0;
                allocationHtml += `
                    <tr>
                        <td>${label}</td>
                        <td class="text-end">$${runningCash.toLocaleString()}</td>
                        <td class="text-end fw-bold ${totalGain >= 0 ? 'text-success' : 'text-danger'}">$${totalGain.toLocaleString()}</td>
                        <td class="text-end">$${sAmt.toLocaleString(undefined, {maximumFractionDigits: 0})}</td>
                        <td class="text-end">$${lAmt.toLocaleString(undefined, {maximumFractionDigits: 0})}</td>
                        <td class="text-end">$${bAmt.toLocaleString(undefined, {maximumFractionDigits: 0})}</td>
                    </tr>`;
            }
            return label;
        });

        $('#forecastAllocationBody').html(allocationHtml);

        // --- Restored 3-Bar Stack Logic ---
        selectedCats.forEach(name => {
            let stackGroup = 'Expense';
            const dataValues = currentData.map(period => {
                const match = period.find(c => c.category_Name === name);
                if (match) {
                    const type = (match.transactionType || 'expense').toLowerCase();
                    if (type === 'income') stackGroup = 'Income';
                    else if (type === 'investment') stackGroup = 'Investment';
                    else stackGroup = 'Expense';
                }
                return match ? Math.abs(match.amount) : 0;
            });

            if (dataValues.some(v => v > 0)) {
                trendSeries.push({
                    name: name, data: dataValues, type: 'column',
                    stack: stackGroup, color: colorMap[name] || '#bdc3c7'
                });
            }
        });

        trendSeries.push({
            name: 'Cash Balance', type: 'spline', data: cashPoints, yAxis: 1,
            color: '#2c3e50', dashStyle: 'ShortDash'
        });

        if (Highcharts.charts[0]) {
            Highcharts.charts[0].destroy();
        }
        Highcharts.chart('chartContainer', {
            chart: {zoomType: 'xy', backgroundColor: 'transparent'},
            title: {text: "Budget Analysis"},
            xAxis: {categories: xAxisLabels},
            // Two Y-Axes: 0 is Flow (Left), 1 is Cash (Right)
            yAxis: [{
                title: {text: 'Flow ($)'},
                labels: {format: '${value}'}
            }, {
                title: {text: 'Cash Balance ($)'},
                labels: {format: '${value}'},
                opposite: true // THIS MOVES IT TO THE RIGHT
            }],
            plotOptions: {
                column: {
                    stacking: 'normal',
                    cursor: 'pointer',
                    grouping: true,
                    point: {
                        events: {
                            click: function () {
                                selectedIndex = this.index;
                                renderCharts();
                            }
                        }
                    }
                }
            },
            series: trendSeries
        });

        Highcharts.chart('pieContainer', {
            chart: {type: 'pie', backgroundColor: 'transparent'},
            title: {text: `Expenses: ${xAxisLabels[selectedIndex] || 'Selected'}`},
            tooltip: {pointFormat: '<b>{point.percentage:.1f}%</b> ($ {point.y:,.0f})'},
            series: [{name: 'Amount', data: pieData}]
        });

        renderBudgetCards(selectedIndex);
        updateSecondaryViews(selectedIndex);
    };

    // --- 4. Affordability Tab Logic ---
    const fetchBudgets = () => {
        $.get("BudgetAJAX", {action: "getActiveBudgets"}, (data) => {
            activeBudgets = data;
            renderBudgetCards(selectedIndex);
        });
    };

    const renderBudgetCards = (forecastIndex) => {
        const container = $('#budgetAffordabilityContainer');
        if (!container.length) return;
        container.empty();

        if (activeBudgets.length === 0) return;

        const fundData = forecastBigProjectFunds[forecastIndex] || {amount: 0, label: "Current"};

        $('#affordabilityHeader').html(`
            <div class="d-flex justify-content-between align-items-center w-100">
                <span>Funding Status for <b>${fundData.label}</b></span>
                <span class="badge bg-dark fs-6 px-3">Available Fund: $${Math.round(fundData.amount).toLocaleString()}</span>
            </div>
        `);

        activeBudgets.forEach(b => {
            const limit = b.limit_amount || 0;
            const spent = b.totalSpent || 0;
            const isAffordable = fundData.amount >= spent;
            const progressPct = limit > 0 ? Math.min(100, (spent / limit) * 100) : 0;

            container.append(`
                <div class="col">
                    <div class="card h-100 border-0 border-start border-4 ${isAffordable ? 'border-success' : 'border-warning'} shadow-sm">
                        <div class="card-body p-3">
                            <h6 class="fw-bold mb-1">${b.name}</h6>
                            <div class="mb-2">
                                <span class="badge ${isAffordable ? 'bg-success' : 'bg-warning text-dark'}">
                                    ${isAffordable ? 'Fully Funded' : 'Underfunded'}
                                </span>
                            </div>
                            <div class="progress mb-2" style="height: 10px; background-color: #eee;">
                                <div class="progress-bar ${isAffordable ? 'bg-success' : 'bg-warning'}" style="width: ${progressPct}%"></div>
                            </div>
                            <div class="d-flex justify-content-between x-small fw-bold">
                                <span>$${Math.round(spent).toLocaleString()} spent</span>
                                <span class="text-muted">of $${Math.round(limit).toLocaleString()}</span>
                            </div>
                            <div class="mt-2 pt-2 border-top x-small">
                                ${isAffordable ?
                `<span class="text-success"><i class="bi bi-check-circle-fill"></i> Fund covers $${Math.round(spent)} spent</span>` :
                `<span class="text-danger"><i class="bi bi-exclamation-triangle-fill"></i> Fund is $${Math.round(spent - fundData.amount).toLocaleString()} short</span>`
            }
                            </div>
                        </div>
                    </div>
                </div>`);
        });
    };

    // --- 5. Statistics View (Restored Investment Section) ---
    const updateSecondaryViews = (index) => {
        const periodData = currentData[index];
        if (!periodData) return;

        let incomeList = [], expenseList = [], investList = [];
        let inTotal = 0, outTotal = 0, investTotal = 0;
        const selectedCats = $('.cat-check:checked').map(function () {
            return this.value;
        }).get()
            .filter(name => !name.toLowerCase().includes("total"));

        selectedCats.forEach(name => {
            const match = periodData.find(c => c.category_Name === name);
            if (match && Math.abs(match.amount) > 0) {
                const val = Math.abs(match.amount);
                const type = (match.transactionType || 'expense').toLowerCase();
                const item = {name: name, val: val, color: colorMap[name] || '#bdc3c7'};
                if (type === 'income') {
                    inTotal += val;
                    incomeList.push(item);
                } else if (type === 'investment') {
                    investTotal += val;
                    investList.push(item);
                } else {
                    outTotal += val;
                    expenseList.push(item);
                }
            }
        });

        incomeList.sort((a, b) => b.val - a.val);
        expenseList.sort((a, b) => b.val - a.val);
        investList.sort((a, b) => b.val - a.val);

        const buildRows = (list) => list.map(p => `
            <div class="d-flex justify-content-between x-small mb-1">
                <span><i class="bi bi-circle-fill me-1" style="color:${p.color}; font-size:8px;"></i>${p.name}</span>
                <b>$${p.val.toLocaleString()}</b>
            </div>`).join('');

        const net = inTotal - outTotal - investTotal;
        $('#statsTabPane').html(`
            <div class="p-3 bg-white border rounded shadow-sm">
                <h6 class="border-bottom pb-2 fw-bold text-uppercase x-small">${xAxisLabels[index]} Summary</h6>
                <div class="row">
                    <div class="col-md-4 border-end">
                        <div class="text-success fw-bold x-small mb-2">INCOME</div>
                        ${buildRows(incomeList) || '<div class="text-muted x-small">None</div>'}
                        <div class="border-top mt-2 pt-1 d-flex justify-content-between fw-bold small"><span>TOTAL:</span><span>$${inTotal.toLocaleString()}</span></div>
                    </div>
                    <div class="col-md-4 border-end">
                        <div class="text-danger fw-bold x-small mb-2">EXPENSES</div>
                        ${buildRows(expenseList) || '<div class="text-muted x-small">None</div>'}
                        <div class="border-top mt-2 pt-1 d-flex justify-content-between fw-bold small"><span>TOTAL:</span><span>$${outTotal.toLocaleString()}</span></div>
                    </div>
                    <div class="col-md-4">
                        <div class="text-primary fw-bold x-small mb-2">INVESTMENT</div>
                        ${buildRows(investList) || '<div class="text-muted x-small">None</div>'}
                        <div class="border-top mt-2 pt-1 d-flex justify-content-between fw-bold small"><span>TOTAL:</span><span>$${investTotal.toLocaleString()}</span></div>
                    </div>
                </div>
                <div class="mt-3 p-2 rounded bg-light d-flex justify-content-between align-items-center">
                    <span class="fw-bold small">NET CASHFLOW:</span>
                    <span class="fs-5 fw-bold ${net >= 0 ? 'text-success' : 'text-danger'}">$${net.toLocaleString()}</span>
                </div>
            </div>`);
    };

    // --- 6. Event Listeners ---
    const fetchAnalysisData = () => {
        $.get("AnalysisAJAX", {
            mode, level,
            year: $('#inputYear').val(),
            bankAccountSelect: $('#bankAccountSelect').val(),
            monthsBack: $('#monthsBack').val(),
            monthsForward: $('#monthsForward').val()
        }, (res) => {
            currentData = res;
            refreshColorMap();
            updateSidebar();
            renderCharts();
        });
    };

    $('#btnAnnual, #btnMonthly, #btnForecast').click(function () {
        mode = $(this).data('val').toString();
        $(this).addClass('active btn-primary').siblings().removeClass('active btn-primary').addClass('btn-outline-primary');
        $('#yearSelectorContainer').toggle(mode === "1");
        $('#forecastControlsContainer').toggle(mode === "2");
        fetchAnalysisData();
    });

    $('#btnSub, #btnSuper').click(function () {
        level = $(this).data('val').toString();
        $(this).addClass('active').siblings().removeClass('active');
        fetchAnalysisData();
    });

    $(document).on('input', '.goal-input', handleSliderChange);
    $(document).on('change', '.cat-check', renderCharts);
    $('#openingBalance, #inputYear, #bankAccountSelect, #monthsBack, #monthsForward').on('change', fetchAnalysisData);

    fetchBudgets();
    fetchAnalysisData();
});