$(document).ready(function() {

    // --- 0. State Variables ---
    let currentData = window.initialData;
    let xAxisLabels = [];
    let mode = "0";
    let level = "0";
    let forecastViewMode = "monthly";
    let colorMap = {};

    // --- 1. Helper Functions ---
    const getMonthName = (m) => ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"][m - 1] || "N/A";

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

    const updateSecondaryViews = (index) => {
        const periodData = currentData[index];
        const selectedYear = $('#inputYear').val();
        if (!periodData || !xAxisLabels[index]) return;

        const selectedCats = $('.cat-check:checked').map(function () {
            return this.value;
        }).get()
            .filter(name => name.toLowerCase() !== "total in");

        let inTotal = 0, outTotal = 0, investTotal = 0;
        let incomePoints = [], investPoints = [], expensePoints = [];
        const newPieData = [];

        selectedCats.forEach(name => {
            const match = periodData.find(c => c.category_Name === name);
            if (match && Math.abs(match.amount) > 0) {
                const val = Math.abs(match.amount);
                const type = (match.transactionType || 'expense').toLowerCase();
                const catColor = colorMap[name] || '#bdc3c7';
                const point = {name: name, y: val, color: catColor};

                if (type === 'income') {
                    inTotal += val;
                    incomePoints.push(point);
                } else if (type === 'investment') {
                    investTotal += val;
                    investPoints.push(point);
                } else {
                    outTotal += val;
                    expensePoints.push(point);
                }
                newPieData.push(point);
            }
        });

        const pieChart = $('#pieContainer').highcharts();
        if (pieChart) {
            pieChart.series[0].setData(newPieData, true);
            const pieTitle = (mode === "1") ? `${xAxisLabels[index]} ${selectedYear}` : xAxisLabels[index];
            pieChart.setTitle({text: `Share: ${pieTitle}`});
        }

        const buildSection = (title, points, color) => {
            let res = `<div style="font-size:12px; font-weight:bold; color:${color}; text-decoration:underline; margin-top:10px;">${title}</div>`;
            if (points.length > 0) {
                points.forEach(p => {
                    res += `<div style="display:flex; font-size:13px; margin-bottom:2px;">
                                <span style="color:${p.color}">●</span> <span style="margin-left:5px;">${p.name}</span> 
                                <b style="margin-left:auto;">$${p.y.toLocaleString()}</b>
                            </div>`;
                });
            } else {
                res += '<div style="color:#ccc; font-size:12px;">None</div>';
            }
            return res;
        };

        const net = inTotal - outTotal - investTotal;
        const detailHeader = (mode === "1") ? `${xAxisLabels[index]} ${selectedYear}` : xAxisLabels[index];

        $('#statsTabPane').html(`
            <div class="p-4 border rounded shadow-sm bg-white">
                <div style="font-weight:bold; font-size:1.2rem; border-bottom:2px solid #3498db; margin-bottom:12px; padding-bottom:5px;">
                    ${detailHeader} Detail
                </div>
                <div style="display:flex; gap:20px;">
                    <div style="flex:1; border-right:1px solid #eee; padding-right:15px;">
                        ${buildSection('INCOME', incomePoints, '#27ae60')}
                        ${buildSection('INVESTMENTS', investPoints, '#2980b9')}
                    </div>
                    <div style="flex:1; padding-left:15px;">
                        ${buildSection('EXPENSES', expensePoints, '#e74c3c')}
                    </div>
                </div>
                <div style="margin-top:20px; border-top:1px dashed #ccc; font-size:14px; padding-top:10px;">
                    <div class="d-flex justify-content-between align-items-center">
                        <span>In: <b class="text-success">$${inTotal.toLocaleString()}</b></span>
                        <span>Out: <b class="text-danger">$${(outTotal + investTotal).toLocaleString()}</b></span>
                        <span>Net Flow: <b style="color:${net >= 0 ? '#27ae60' : '#e74c3c'}">$${net.toLocaleString()}</b></span>
                    </div>
                </div>
            </div>`);
    };

    // --- 2. Data Fetching ---
    const fetchAnalysisData = () => {
        $.get("AnalysisAJAX", {
            mode: mode, level: level, year: $('#inputYear').val(),
            bankAccountSelect: $('#bankAccountSelect').val(),
            monthsBack: $('#monthsBack').val() || 24,
            monthsForward: $('#monthsForward').val() || 12
        }, (res) => {
            currentData = res;
            refreshColorMap();
            updateSidebar();
            renderCharts();
        });
    };

    // --- 3. Chart Rendering Logic ---
    const renderCharts = () => {
        if (!currentData || currentData.length === 0) return;

        const selectedYear = $('#inputYear').val();
        const startingBalance = parseFloat($('#openingBalance').val()) || 0;
        const selectedCats = $('.cat-check:checked').map(function () {
            return this.value;
        }).get();

        let trendSeries = [];
        let cashPoints = [];
        let runningCash = startingBalance;

        xAxisLabels = currentData.map(period => {
            const first = period[0];
            if (!first) return "N/A";
            if (mode === "2") {
                if (first.sign && first.sign.includes('-')) {
                    const pts = first.sign.split('-');
                    return `${getMonthName(parseInt(pts[1]))} ${pts[0].substring(2)}`;
                }
                return first.sign || "N/A";
            }
            return (mode === "1") ? getMonthName(parseInt(first.year)) : (first.year || "N/A");
        });

        selectedCats.forEach(name => {
            let stackGroup = 'Expense';
            const dataValues = currentData.map(period => {
                const match = period.find(c => c.category_Name === name);
                if (match) {
                    const type = (match.transactionType || 'expense').toLowerCase();
                    stackGroup = (type === 'income') ? 'Income' : (type === 'investment' ? 'Investment' : 'Expense');
                }
                return match ? Math.abs(match.amount) : 0;
            });
            if (dataValues.some(v => v > 0)) {
                trendSeries.push({
                    name: name, data: dataValues, type: 'column',
                    stack: stackGroup, color: colorMap[name] || '#bdc3c7',
                    dataLabels: {enabled: false}
                });
            }
        });

        currentData.forEach(period => {
            let periodNet = 0;
            period.forEach(cat => {
                if (selectedCats.includes(cat.category_Name)) {
                    const type = (cat.transactionType || 'expense').toLowerCase();
                    const val = Math.abs(cat.amount);
                    if (type === 'income') periodNet += val;
                    else periodNet -= val;
                }
            });
            runningCash += periodNet;
            cashPoints.push(runningCash);
        });

        trendSeries.push({
            name: 'Cash Balance', type: 'spline', data: cashPoints, yAxis: 1,
            color: '#2c3e50', dashStyle: 'ShortDash', marker: {enabled: true, radius: 3}
        });

        // Initialize Main Chart
        Highcharts.chart('chartContainer', {
            chart: {zoomType: 'xy'},
            title: {text: (mode === "0") ? "Annual Overview" : (mode === "2" ? "Financial Forecast" : `Budget Analysis ${selectedYear}`)},
            xAxis: {categories: xAxisLabels, crosshair: true},
            yAxis: [
                {title: {text: 'Flow ($)'}, stackLabels: {enabled: false}},
                {title: {text: 'Cash Balance ($)'}, opposite: true, labels: {format: '${value}'}}
            ],
            plotOptions: {
                column: {
                    stacking: 'normal', cursor: 'pointer', point: {
                        events: {
                            click: function () {
                                updateSecondaryViews(this.index);
                            }
                        }
                    }
                }
            },
            series: trendSeries
        });

        // FIX: Build explicit pie data for the initial render (last month)
        const lastIdx = currentData.length - 1;
        const initialPieData = [];
        selectedCats.forEach(name => {
            const match = currentData[lastIdx].find(c => c.category_Name === name);
            if (match && Math.abs(match.amount) > 0) {
                initialPieData.push({name: name, y: Math.abs(match.amount), color: colorMap[name] || '#bdc3c7'});
            }
        });

        // Initialize Pie Chart with Data
        Highcharts.chart('pieContainer', {
            chart: { type: 'pie' },
            title: {text: `Share: ${xAxisLabels[lastIdx]}`},
            series: [{name: 'Amount', data: initialPieData}]
        });

        // Run the detailed stats view update
        updateSecondaryViews(lastIdx);
    };

    // --- 4. Listeners ---
    $('#btnAnnual, #btnMonthly, #btnForecast').off().click(function () {
        mode = $(this).data('val').toString();
        $(this).addClass('active btn-primary').siblings().removeClass('active btn-primary').addClass('btn-outline-primary');
        $('#yearSelectorContainer').toggle(mode === "1");
        $('#forecastControlsContainer').toggle(mode === "2");
        fetchAnalysisData();
    });

    $('#inputYear, #bankAccountSelect, #openingBalance, #monthsBack, #monthsForward').on('change', (e) => {
        if (e.target.id === 'openingBalance') renderCharts();
        else fetchAnalysisData();
    });

    $(document).on('change', '.cat-check', renderCharts);

    fetchAnalysisData();
});