$(document).ready(function() {

    // --- 0. State Variables ---
    let currentData = window.initialData || [];
    let xAxisLabels = [];
    let mode = "0";
    let level = "0";
    let colorMap = {};
    let selectedIndex = 0;

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
                if (cat.category_Name && (cat.color_id || cat.color_ID)) {
                    colorMap[cat.category_Name] = cat.color_id || cat.color_ID;
                }
            });
        });
    };
    $('#performanceMonthSelect, #performanceSpecificMonth').on('change', function () {
        const selectedYear = $('#performanceYearSelect').val();
        const selectedMonth = $('#performanceMonthSelect').val() || -1; // -1 for "All Year"

        if (selectedYear && selectedYear !== 'null') {
            loadGlobalPerformance(selectedYear, selectedMonth);
        }
    });
    $('button[data-bs-toggle="tab"]').on('shown.bs.tab', function (e) {
        const targetId = $(e.target).attr('data-bs-target');

        // If switching to Affordability, re-render the legacy budget cards
        if (targetId === '#affordabilityTabPane') {
            renderBudgetCards(selectedIndex);
        }

        // If switching to Goals, ensure the chart data has populated the table
        if (targetId === '#goalsTabPane') {
            renderCharts();
        }

        // If switching to Performance, trigger the new AJAX call
        if (targetId === '#performanceTabPane') {
            const year = $('#performanceYearSelect').val() || $('#inputYear').val();
            const month = $('#performanceMonthSelect').val() || -1;
            if (year && year !== 'null') {
                loadGlobalPerformance(year, month);
            }
        }
    });

    const updateSidebar = () => {
        const list = $('#categoryList').empty();
        if (!currentData || currentData.length === 0) return;

        const uniqueCats = currentData[0];
        uniqueCats.forEach(cat => {
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

    const renderCharts = () => {
        if (!currentData || currentData.length === 0 || mode === "3") return;

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
            let label = (mode === "0") ? (first.year || "N/A") : getMonthName(first.month || first.year);

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

        Highcharts.chart('chartContainer', {
            chart: {zoomType: 'xy', backgroundColor: 'transparent'},
            title: {text: "Budget Analysis"},
            xAxis: {categories: xAxisLabels},
            yAxis: [{title: {text: 'Flow ($)'}, labels: {format: '${value}'}}, {
                title: {text: 'Cash Balance ($)'},
                labels: {format: '${value}'},
                opposite: true
            }],
            plotOptions: {
                column: {
                    stacking: 'normal',
                    cursor: 'pointer',
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

    const updateSecondaryViews = (index, overrideData = null) => {
        // If overrideData exists, it's an array of items (like a year's subcategories)
        const periodData = overrideData ? overrideData : (currentData[index] || []);
        if (!periodData || periodData.length === 0) return;

        let incomeList = [], expenseList = [], investList = [];
        let inTotal = 0, outTotal = 0, investTotal = 0;

        periodData.forEach(match => {
            const val = Math.abs(match.amount);
            if (val > 0) {
                const type = (match.transactionType || 'expense').toLowerCase();
                const item = {name: match.category_Name, val: val, color: colorMap[match.category_Name] || '#bdc3c7'};
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
                <b>$${p.val.toLocaleString(undefined, {maximumFractionDigits: 0})}</b>
            </div>`).join('');

        const net = inTotal - outTotal - investTotal;
        // Use either the label passed via bridge or the xAxis label
        const currentLabel = overrideData ? (window.simLabel || "Simulation") : (xAxisLabels[index] || "Selected Period");

        $('#statsTabPane').html(`
            <div class="p-3 bg-white border rounded shadow-sm">
                <h6 class="border-bottom pb-2 fw-bold text-uppercase x-small">${currentLabel} Summary</h6>
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

    // --- CRITICAL BRIDGE ---
    window.updateFromSimulation = (simData, label) => {
        window.simLabel = label;

        // 1. Manually update Stats
        updateSecondaryViews(0, simData);

        // 2. Manually update Pie
        const pieData = simData
            .filter(c => (c.transactionType || 'expense').toLowerCase() !== 'income')
            .filter(c => Math.abs(c.amount) > 0)
            .map(c => ({
                name: c.category_Name,
                y: Math.abs(c.amount),
                color: colorMap[c.category_Name] || '#bdc3c7'
            }));

        Highcharts.chart('pieContainer', {
            chart: {type: 'pie', backgroundColor: 'transparent'},
            title: {text: `Breakdown: ${label}`},
            tooltip: {pointFormat: '<b>{point.percentage:.1f}%</b> ($ {point.y:,.0f})'},
            series: [{name: 'Annual Amount', data: pieData}]
        });

        // 3. Navigate to tab
        $('#pie-tab').tab('show');
    };

    const fetchBudgets = () => {
        $.get("BudgetAJAX", {action: "getActiveBudgets"}, (data) => {
            activeBudgets = data;
            renderBudgetCards(selectedIndex);
        });
    };

    const renderBudgetCards = (forecastIndex) => {
        // 1. Ensure this points to the cards container in the Affordability Tab
        const container = $('#budgetAffordabilityContainer');
        if (!container.length) return;
        container.empty();

        const fundData = forecastBigProjectFunds[forecastIndex] || {amount: 0, label: "Current"};

        // 2. Ensure this points to the header specifically in the Affordability Tab
        // If you renamed this ID in the JSP, update it here!
        $('#affordabilityHeader').html(`
        <div class="d-flex justify-content-between align-items-center w-100">
            <span>Funding for <b>${fundData.label}</b></span>
            <span class="badge bg-dark fs-6 px-3">Available Fund: $${Math.round(fundData.amount).toLocaleString()}</span>
        </div>
    `);

        activeBudgets.forEach(b => {
            const spent = b.totalSpent || 0;
            const isAffordable = fundData.amount >= spent;
            container.append(`
            <div class="col">
                <div class="card h-100 border-0 border-start border-4 ${isAffordable ? 'border-success' : 'border-warning'} shadow-sm">
                    <div class="card-body p-3">
                        <h6 class="fw-bold mb-1">${b.name}</h6>
                        <div class="mb-2"><span class="badge ${isAffordable ? 'bg-success' : 'bg-warning text-dark'}">${isAffordable ? 'Affordable' : 'Over Budget'}</span></div>
                        <div class="d-flex justify-content-between x-small fw-bold"><span>Spent: $${Math.round(spent).toLocaleString()}</span></div>
                    </div>
                </div>
            </div>`);
        });
    };

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

    $('#btnAnnual, #btnMonthly, #btnForecast, #btnRetire').click(function () {
        mode = $(this).data('val').toString();
        $(this).addClass('active btn-primary').siblings().removeClass('active btn-primary').addClass('btn-outline-primary');
        const isRetire = (mode === "3");
        const isForecast = (mode === "2");
        $('#mainChartWrapper').toggle(!isRetire);
        $('#retirementChartWrapper').toggle(isRetire);
        $('#startingCashPanel, #categoryFilterPanel').toggle(!isRetire);
        $('#yearSelectorContainer').toggle(mode === "1");
        $('#forecastControlsContainer').toggle(isForecast);
        $('#goals-tab, #affordability-tab').closest('.nav-item').toggle(isForecast);
        if (isRetire) RetirementEngine.runMightyAnalysis();
        else fetchAnalysisData();
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

function renderPerformanceHeader(data, year, month) {
    const buckets = data.reduce((acc, cat) => {
        const type = (cat.transaction_type || 'expense').toLowerCase();
        if (!acc[type]) acc[type] = {act: 0, lim: 0};
        acc[type].act += cat.actualValue;
        acc[type].lim += cat.threshold;
        return acc;
    }, {income: {act: 0, lim: 0}, expense: {act: 0, lim: 0}, investment: {act: 0, lim: 0}});

    const net = buckets.income.act - buckets.expense.act - buckets.investment.act;
    const label = month > 0 ? `Month ${month}, ${year}` : `${year} Annual`;

    const html = `
    <div class="card border-0 bg-white shadow-sm mb-4">
        <div class="card-body p-3">
            <div class="row g-3">
                <div class="col-md-3 border-end">
                    <small class="text-uppercase text-muted fw-bold d-block mb-1" style="font-size:0.7rem">Total Income</small>
                    <h4 class="mb-0 text-success">$${buckets.income.act.toLocaleString()}</h4>
                    <div class="small text-muted">Target: $${buckets.income.lim.toLocaleString()}</div>
                </div>
                <div class="col-md-3 border-end">
                    <small class="text-uppercase text-muted fw-bold d-block mb-1" style="font-size:0.7rem">Total Investment</small>
                    <h4 class="mb-0 text-info">$${buckets.investment.act.toLocaleString()}</h4>
                    <div class="small text-muted">Target: $${buckets.investment.lim.toLocaleString()}</div>
                </div>
                <div class="col-md-3 border-end">
                    <small class="text-uppercase text-muted fw-bold d-block mb-1" style="font-size:0.7rem">Total Expenses</small>
                    <h4 class="mb-0 ${buckets.expense.act > buckets.expense.lim ? 'text-danger' : 'text-dark'}">$${buckets.expense.act.toLocaleString()}</h4>
                    <div class="small text-muted">Limit: $${buckets.expense.lim.toLocaleString()}</div>
                </div>
                <div class="col-md-3 text-md-end">
                    <small class="text-uppercase text-muted fw-bold d-block mb-1" style="font-size:0.7rem">Net Cashflow (${label})</small>
                    <h3 class="mb-0 ${net >= 0 ? 'text-success' : 'text-danger'}">$${net.toLocaleString()}</h3>
                    <span class="badge ${net >= 0 ? 'bg-success' : 'bg-danger'}">${net >= 0 ? 'SURPLUS' : 'DEFICIT'}</span>
                </div>
            </div>
        </div>
    </div>`;
    $('#performanceSummaryHeader').html(html);
}

function buildGlobalCategoryCard(cat, year, month) {
    const type = (cat.transaction_type || 'expense').toLowerCase();
    const isRevenue = (type === 'income' || type === 'investment');
    const diff = cat.actualValue - cat.threshold;
    const isHealthy = isRevenue ? diff >= 0 : diff <= 0;

    const progress = cat.threshold > 0 ? Math.min((cat.actualValue / cat.threshold) * 100, 100) : 0;

    // High-contrast badge and text colors
    const colorClass = isHealthy ? 'text-success' : 'text-danger';
    const bgClass = isHealthy ? 'bg-success' : 'bg-danger';

    const drillDownUrl = `all-Transactions?category=${cat.categoryID}&year=${year}&month=${month}`;

    return `
    <div class="col-xl-3 col-lg-4 col-md-6 col-sm-12  animate__animated animate__fadeIn">
        <div class="card h-100 border-0 shadow-sm budget-card position-relative">
            <div class="progress rounded-0" style="height: 6px; background: rgba(0,0,0,0.08);">
                <div class="progress-bar ${bgClass}" style="width: ${progress}%"></div>
            </div>
            <div class="card-body p-4">
                <div class="d-flex justify-content-between align-items-start mb-3">
                    <h6 class="fw-bold mb-0 text-muted text-uppercase" style="font-size: 0.75rem; letter-spacing: 0.5px;" title="${cat.categoryName}">
                        ${cat.categoryName}
                    </h6>
                    <span class="badge ${bgClass} shadow-sm px-2 py-1" style="font-size: 0.7rem;">
                        ${progress.toFixed(0)}%
                    </span>
                </div>

                <h2 class="fw-bold mb-3 ${colorClass}">$${cat.actualValue.toLocaleString()}</h2>
                
                <div class="pt-3 border-top d-flex justify-content-between align-items-center">
                    <div>
                        <span class="text-muted d-block" style="font-size: 0.65rem; text-uppercase fw-bold">Target</span>
                        <span class="fw-bold text-dark" style="font-size: 0.9rem;">$${cat.threshold.toLocaleString()}</span>
                    </div>
                    <div class="text-end">
                        <span class="text-muted d-block" style="font-size: 0.65rem; text-uppercase fw-bold">Variance</span>
                        <span class="fw-bold ${colorClass}" style="font-size: 0.9rem;">
                            ${diff >= 0 ? '+' : '-'}$${Math.abs(diff).toLocaleString()}
                        </span>
                    </div>
                </div>
                
                <a href="${drillDownUrl}" class="stretched-link"></a>
            </div>
        </div>
    </div>`;
}

async function loadGlobalPerformance(year, month = -1) {
    const $grid = $('#performanceResultsGrid');
    const $header = $('#performanceSummaryHeader');

    $grid.html('<div class="col-12 text-center p-5"><div class="spinner-border text-primary"></div></div>');

    try {
        const response = await fetch(`getCategoryPerformance?year=${year}&mode=1&month=${month}`);
        const data = await response.json();

        if (!data || data.length === 0) {
            $grid.html('<div class="col-12 alert alert-warning text-center">No data found.</div>');
            $header.empty();
            return;
        }

        // 1. Render the Top Scoreboard
        renderPerformanceHeader(data, year, month);

        // 2. Initialize our 3 buckets
        const sections = {
            income: {label: 'Income Sources', html: '', count: 0},
            investment: {label: 'Investments & Growth', html: '', count: 0},
            expense: {label: 'Monthly Expenses', html: '', count: 0}
        };

        // 3. Sort and Distribute
        data.sort((a, b) => b.actualValue - a.actualValue);

        data.forEach(cat => {
            const type = (cat.transaction_type || 'expense').toLowerCase();
            if (sections[type]) {
                sections[type].html += buildGlobalCategoryCard(cat, year, month);
                sections[type].count++;
            }
        });

        // 4. Combine the 6 bits (Header + Cards for each type)
        let finalHtml = '';
        ['income', 'investment', 'expense'].forEach(key => {
            if (sections[key].count > 0) {
                // Type Header
                finalHtml += `
                    <div class="col-12 mt-4 mb-2 animate__animated animate__fadeIn">
                        <div class="d-flex align-items-center">
                            <h5 class="fw-bold mb-0 text-uppercase text-muted" style="letter-spacing: 1px;">
                                ${sections[key].label}
                            </h5>
                            <div class="flex-grow-1 ms-3 border-top" style="opacity: 0.1;"></div>
                        </div>
                    </div>`;
                // Type Cards
                finalHtml += sections[key].html;
            }
        });

        $grid.html(finalHtml);

    } catch (err) {
        console.error(err);
        $grid.html('<div class="col-12 alert alert-danger">Error fetching performance data.</div>');
    }
}
