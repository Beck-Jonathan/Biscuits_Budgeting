$(document).ready(function() {
    // --- 0. State Variables ---
    let currentData = window.initialData;
    let mode = "0";  // 0=Annual, 1=Monthly, 2=Forecast
    let level = "0"; // 0=Sub, 1=Super
    let forecastViewMode = "monthly"; // 'monthly' or 'annual'

    // --- 1. Helper Functions ---

    const getMonthName = (m) => {
        return ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"][m - 1] || "N/A";
    };

    const crunchForecastByYear = (monthlyData) => {
        const flatData = monthlyData.flat();
        const grouped = flatData.reduce((acc, item) => {
            const year = item.sign.split('-')[0];
            if (!acc[year]) acc[year] = {};
            if (!acc[year][item.category_Name]) {
                acc[year][item.category_Name] = {
                    ...item,
                    amount: 0,
                    sign: year
                };
            }
            acc[year][item.category_Name].amount += parseFloat(item.amount);
            return acc;
        }, {});

        return Object.keys(grouped).map(year => Object.values(grouped[year]));
    };

    const updateSidebar = () => {
        const list = $('#categoryList').empty();
        if (!currentData || currentData.length === 0) return;
        currentData[0].forEach(cat => {
            list.append(`
                <li class="category-item p-2 border-bottom">
                    <div class="form-check d-flex align-items-center">
                        <input class="form-check-input cat-check me-2" type="checkbox" 
                               value="${cat.category_Name}" id="c_${cat.category_ID}" checked>
                        <span class="color-swatch" style="background-color:${cat.color_id}; width:12px; height:12px; border-radius:50%; margin-right:10px; display:inline-block;"></span>
                        <label class="form-check-label small" for="c_${cat.category_ID}">${cat.category_Name}</label>
                    </div>
                </li>`);
        });
    };

    // --- 2. Data Fetching ---

    const fetchAnalysisData = () => {
        const selectedYear = $('#inputYear').val();
        const selectedBank = $('#bankAccountSelect').val();
        const monthsBack = $('#monthsBack').val() || 24;
        const monthsForward = $('#monthsForward').val() || 12;

        $.get("AnalysisAJAX", {
            mode: mode,
            level: level,
            year: selectedYear,
            bankAccountSelect: selectedBank,
            monthsBack: monthsBack,
            monthsForward: monthsForward
        }, (res) => {
            if (mode === "2" && forecastViewMode === "annual") {
                currentData = crunchForecastByYear(res);
            } else {
                currentData = res;
            }
            updateSidebar();
            renderCharts();
        });
    };

    // --- 3. Chart Rendering Logic ---

    const renderCharts = () => {
        if (!currentData || currentData.length === 0) return;

        const selectedCats = $('.cat-check:checked').map(function() { return this.value; }).get()
            .filter(name => name.toLowerCase() !== "total in");

        let trendSeries = [];
        let pieDataPoints = [];

        const xAxisLabels = currentData.map(period => {
            const firstEntry = period[0];
            if (mode === "2" && forecastViewMode === "monthly") {
                const parts = firstEntry.sign.split('-');
                return `${getMonthName(parseInt(parts[1]))} ${parts[0].substring(2)}`;
            }
            if (mode === "2" && forecastViewMode === "annual") {
                return firstEntry.sign; // Just the year
            }
            return mode === "0" ? firstEntry.year : getMonthName(firstEntry.month);
        });

        selectedCats.forEach(name => {
            let color = "";
            let stackGroup = 'Expense';

            const dataValues = currentData.map(period => {
                const match = period.find(c => c.category_Name === name);
                if (match) {
                    color = match.color_id;
                    const type = (match.transactionType || 'expense').toLowerCase();
                    if (type === 'income') stackGroup = 'Income';
                    else if (type === 'investment') stackGroup = 'Investment';
                    else stackGroup = 'Expense';
                }
                return match ? Math.abs(match.amount) : 0;
            });

            if (dataValues.some(v => v > 0)) {
                trendSeries.push({name: name, data: dataValues, type: 'column', stack: stackGroup, color: color});
                const latestPeriod = currentData[currentData.length - 1];
                const latestMatch = latestPeriod.find(c => c.category_Name === name);
                if (latestMatch) {
                    pieDataPoints.push({name: name, y: Math.abs(latestMatch.amount), color: color});
                }
            }
        });

        Highcharts.chart('chartContainer', {
            chart: {type: 'column', panning: {enabled: true, type: 'x'}, panKey: 'shift'},
            title: { text: "Budget Analysis Stacks", style: { fontWeight: 'bold' } },
            xAxis: {categories: xAxisLabels, min: 0, max: 11, scrollbar: {enabled: true}},
            yAxis: {title: {text: 'Total Amount ($)'}, labels: {format: '${value}'}},
            plotOptions: {column: {stacking: 'normal', borderWidth: 0}},
            tooltip: {
                shared: true, useHTML: true, outside: true,
                positioner: function(labelWidth, labelHeight, point) {
                    let x = (point.plotX > this.chart.plotWidth / 2) ?
                        point.plotX + this.chart.plotLeft - labelWidth - 20 :
                        point.plotX + this.chart.plotLeft + 60;
                    return {x: Math.max(10, Math.min(x, this.chart.chartWidth - labelWidth - 10)), y: 40};
                },
                formatter: function() {
                    const incomePoints = this.points.filter(p => (p.series.options.stack || '').toLowerCase() === 'income').sort((a, b) => b.y - a.y);
                    const investPoints = this.points.filter(p => (p.series.options.stack || '').toLowerCase() === 'investment').sort((a, b) => b.y - a.y);
                    const expensePoints = this.points.filter(p => (p.series.options.stack || '').toLowerCase() === 'expense').sort((a, b) => b.y - a.y);

                    let inTotal = 0, outTotal = 0, investTotal = 0;
                    this.points.forEach(p => {
                        if (p.series.options.stack === 'Income') inTotal += p.y;
                        else if (p.series.options.stack === 'Investment') investTotal += p.y;
                        else outTotal += p.y;
                    });

                    let s = `<div style="padding: 15px; min-width: 450px;"><div style="font-weight:bold; border-bottom:2px solid #3498db; margin-bottom:8px;">${this.x}</div><div style="display:flex;">`;

                    // Column logic for tooltip
                    const buildSection = (title, points, color) => {
                        let res = `<div style="font-size:10px; font-weight:bold; color:${color}; text-decoration:underline;">${title}</div>`;
                        if (points.length > 0) {
                            points.forEach(p => {
                                res += `<div style="display:flex; font-size:11px;"><span>\u25CF</span> ${p.series.name} <b style="margin-left:auto;">$${p.y.toLocaleString()}</b></div>`;
                            });
                        } else {
                            res += '<div style="color:#ccc;">None</div>';
                        }
                        return res;
                    };

                    s += `<div style="width:50%; border-right:1px solid #eee; padding-right:10px;">${buildSection('INCOME', incomePoints, '#27ae60')}${buildSection('INVESTMENTS', investPoints, '#2980b9')}</div>`;
                    s += `<div style="width:50%; padding-left:10px;">${buildSection('EXPENSES', expensePoints, '#e74c3c')}</div></div>`;

                    const net = inTotal - outTotal - investTotal;
                    s += `<div style="margin-top:10px; border-top:1px dashed #ccc; font-size:11px; padding-top:5px;">
                            In: <b>$${inTotal.toLocaleString()}</b> | Out: <b>$${outTotal.toLocaleString()}</b> | Net: <b style="color:${net >= 0 ? '#27ae60' : '#e74c3c'}">$${net.toLocaleString()}</b>
                          </div></div>`;
                    return s;
                }
            },
            series: trendSeries
        });

        Highcharts.chart('pieContainer', {
            chart: { type: 'pie' },
            title: { text: 'Current Period Share' },
            series: [{ name: 'Amount', data: pieDataPoints }]
        });
    };

    // --- 4. Listeners ---

    $('#btnAnnual, #btnMonthly, #btnForecast').off().click(function () {
        mode = $(this).data('val').toString();
        $(this).addClass('active btn-primary').removeClass('btn-outline-primary').siblings().removeClass('active btn-primary').addClass('btn-outline-primary');

        $('#yearSelectorContainer').toggle(mode === "1");
        $('#forecastControlsContainer').toggle(mode === "2");

        fetchAnalysisData();
    });

    $(document).on('click', '.f-view', function () {
        forecastViewMode = $(this).data('v');
        $(this).addClass('active').siblings().removeClass('active');
        fetchAnalysisData();
    });

    $('#selectAll').click(() => {
        $('.cat-check').prop('checked', true);
        renderCharts();
    });
    $('#deselectAll').click(() => {
        $('.cat-check').prop('checked', false);
        renderCharts();
    });
    $('#bankAccountSelect, #monthsBack, #monthsForward, #inputYear').on('change', fetchAnalysisData);
    $('#btnSub, #btnSuper').click(function () {
        level = $(this).data('val').toString();
        $(this).addClass('active btn-primary').removeClass('btn-outline-primary').siblings().removeClass('active btn-primary').addClass('btn-outline-primary');
        fetchAnalysisData();
    });

    $(document).on('change', '.cat-check', renderCharts);

    // Initial Load
    if (window.initialData && window.initialData.length > 0) {
        updateSidebar();
        renderCharts();
    } else {
        fetchAnalysisData();
    }
});