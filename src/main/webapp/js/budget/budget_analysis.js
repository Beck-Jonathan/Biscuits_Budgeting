$(document).ready(function() {
    let currentData = window.initialData;
    let mode = "0";  // 0=Annual, 1=Monthly
    let level = "0"; // 0=Sub, 1=Super

    // --- 1. Listeners ---

    $(document).on('click', '#selectAll', function() {
        $('.cat-check').prop('checked', true);
        renderCharts();
    });

    $(document).on('click', '#deselectAll', function() {
        $('.cat-check').prop('checked', false);
        renderCharts();
    });

    // Bank Account Listener
    $('#bankAccountSelect').on('change', function() {
        fetchAnalysisData();
    });

    // --- 2. Sidebar Population ---

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

    // --- 3. Chart Rendering Logic ---

    const renderCharts = () => {
        if (!currentData || currentData.length === 0) return;

        const selectedCats = $('.cat-check:checked').map(function() { return this.value; }).get()
            .filter(name => name.toLowerCase() !== "total in");

        let trendSeries = [];
        let pieDataPoints = [];

        const xAxisLabels = currentData.map(period => {
            const firstEntry = period[0];
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
                trendSeries.push({
                    name: name,
                    data: dataValues,
                    type: 'column',
                    stack: stackGroup,
                    color: color
                });

                const latestPeriod = currentData[currentData.length - 1];
                const latestMatch = latestPeriod.find(c => c.category_Name === name);
                if (latestMatch) {
                    pieDataPoints.push({
                        name: name,
                        y: Math.abs(latestMatch.amount),
                        color: color
                    });
                }
            }
        });

        // --- Stacked Column Chart ---
        Highcharts.chart('chartContainer', {
            chart: { type: 'column' },
            title: { text: "Budget Analysis Stacks", style: { fontWeight: 'bold' } },
            xAxis: { categories: xAxisLabels },
            yAxis: {
                title: { text: 'Total Amount ($)' },
                labels: { format: '${value}' }
            },
            legend: { enabled: true },
            plotOptions: {
                column: { stacking: 'normal', borderWidth: 0 }
            },
            tooltip: {
                shared: true,
                useHTML: true,
                outside: true,
                backgroundColor: 'rgba(255, 255, 255, 0.98)',
                borderWidth: 0,
                shadow: true,
                padding: 0,
                positioner: function(labelWidth, labelHeight, point) {
                    let x;
                    const chartWidth = this.chart.plotWidth;
                    const padding = 60;
                    if (point.plotX > chartWidth / 2) {
                        const pdding = 20;
                        x = point.plotX + this.chart.plotLeft - labelWidth - pdding;
                    } else {
                        x = point.plotX + this.chart.plotLeft + padding;
                    }
                    let y = point.plotY + this.chart.plotTop - (labelHeight / 2);
                    return { x: Math.max(10, Math.min(x, this.chart.chartWidth - labelWidth - 10)), y: Math.max(10, y) };
                },
                formatter: function() {
                    // 1. Separate the data by stack type
                    const incomePoints = this.points.filter(p => (p.series.options.stack || '').toLowerCase() === 'income').sort((a, b) => b.y - a.y);
                    const investPoints = this.points.filter(p => (p.series.options.stack || '').toLowerCase() === 'investment').sort((a, b) => b.y - a.y);
                    const expensePoints = this.points.filter(p => (p.series.options.stack || '').toLowerCase() === 'expense').sort((a, b) => b.y - a.y);

                    // Totals for the footer
                    let inTotal = 0, outTotal = 0, investTotal = 0;
                    this.points.forEach(p => {
                        const stack = p.series.options.stack;
                        if (stack === 'Income') inTotal += p.y;
                        else if (stack === 'Investment') investTotal += p.y;
                        else outTotal += p.y;
                    });

                    let s = `<div style="padding: 15px; min-width: 500px;">`;
                    s += `<div style="font-size: 13px; font-weight: bold; margin-bottom: 8px; border-bottom: 2px solid #3498db;">${this.x}</div>`;

                    s += '<div style="display: flex; align-items: flex-start;">';

                    // LEFT COLUMN: Income & Investment
                    s += '<div style="width: 50%; border-right: 1px solid #eee; padding-right: 15px;">';

                    // Income Section
                    s += '<div style="font-size: 10px; font-weight: bold; text-decoration: underline; color: #27ae60; margin-bottom: 4px;">INCOME</div>';
                    if (incomePoints.length > 0) {
                        incomePoints.forEach(p => {
                            s += `<div style="display: flex; align-items: center; font-size: 11px; margin-bottom: 2px;">
                        <span style="color:${p.color}; margin-right: 6px;">\u25CF</span>
                        <span style="text-overflow: ellipsis; white-space: nowrap; overflow: hidden; max-width: 110px;">${p.series.name}</span>
                        <b style="margin-left: auto;">$${p.y.toLocaleString()}</b>
                      </div>`;
                        });
                    } else { s += '<div style="font-size: 10px; color: #ccc; margin-bottom: 5px;">None</div>'; }

                    // Investment Section
                    s += '<div style="font-size: 10px; font-weight: bold; text-decoration: underline; color: #2980b9; margin: 8px 0 4px 0;">INVESTMENTS</div>';
                    if (investPoints.length > 0) {
                        investPoints.forEach(p => {
                            s += `<div style="display: flex; align-items: center; font-size: 11px; margin-bottom: 2px;">
                        <span style="color:${p.color}; margin-right: 6px;">\u25CF</span>
                        <span style="text-overflow: ellipsis; white-space: nowrap; overflow: hidden; max-width: 110px;">${p.series.name}</span>
                        <b style="margin-left: auto;">$${p.y.toLocaleString()}</b>
                      </div>`;
                        });
                    } else { s += '<div style="font-size: 10px; color: #ccc;">None</div>'; }
                    s += '</div>';

                    // RIGHT COLUMN: Expenses
                    s += '<div style="width: 50%; padding-left: 15px;">';
                    s += '<div style="font-size: 10px; font-weight: bold; text-decoration: underline; color: #e74c3c; margin-bottom: 4px;">EXPENSES</div>';
                    if (expensePoints.length > 0) {
                        expensePoints.forEach(p => {
                            s += `<div style="display: flex; align-items: center; font-size: 11px; margin-bottom: 2px;">
                        <span style="color:${p.color}; margin-right: 6px;">\u25CF</span>
                        <span style="text-overflow: ellipsis; white-space: nowrap; overflow: hidden; max-width: 110px;">${p.series.name}</span>
                        <b style="margin-left: auto;">$${p.y.toLocaleString()}</b>
                      </div>`;
                        });
                    } else { s += '<div style="font-size: 10px; color: #ccc;">None</div>'; }
                    s += '</div>';

                    s += '</div>'; // End Flex

                    // Footer Summary
                    const net = inTotal - outTotal - investTotal;
                    s += `<div style="margin-top: 10px; padding-top: 8px; border-top: 1px dashed #ccc; font-size: 11px;">
                <div style="display: flex; justify-content: space-between; margin-bottom: 3px;">
                    <span>In: <b style="color:#27ae60">$${inTotal.toLocaleString()}</b></span>
                    <span>Out: <b style="color:#e74c3c">$${outTotal.toLocaleString()}</b></span>
                </div>
                <div style="display: flex; justify-content: space-between;">
                    <span>Invested: <b style="color:#2980b9">$${investTotal.toLocaleString()}</b></span>
                    <span>Net: <b style="color:${net >= 0 ? '#27ae60' : '#e74c3c'}">$${net.toLocaleString()}</b></span>
                </div>
              </div></div>`;
                    return s;
                }
            },
            series: trendSeries
        });

        // --- Pie Chart ---
        Highcharts.chart('pieContainer', {
            chart: { type: 'pie' },
            title: { text: 'Current Period Share' },
            tooltip: { pointFormat: '<b>\${point.y:,.2f}</b> ({point.percentage:.1f}%)' },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: { enabled: true, format: '{point.name}: {point.percentage:.1f}%' },
                    showInLegend: true
                }
            },
            series: [{ name: 'Amount', data: pieDataPoints }]
        });
    };

    // --- 4. Data Fetching ---

    const fetchAnalysisData = () => {
        const selectedYear = $('#inputYear').val();
        const selectedBank = $('#bankAccountSelect').val();

        $.get("AnalysisAJAX", {
            mode: mode,
            level: level,
            year: selectedYear,
            bankAccountSelect: selectedBank
        }, (res) => {
            currentData = res;
            updateSidebar();
            renderCharts();
        });
    };

    $('#btnAnnual, #btnMonthly').off().click(function() {
        mode = $(this).data('val').toString();
        $(this).addClass('active btn-primary').removeClass('btn-outline-primary').siblings().removeClass('active btn-primary').addClass('btn-outline-primary');
        mode === "1" ? $('#yearSelectorContainer').show() : $('#yearSelectorContainer').hide();
        fetchAnalysisData();
    });

    $('#btnSub, #btnSuper').off().click(function() {
        level = $(this).data('val').toString();
        $(this).addClass('active btn-primary').removeClass('btn-outline-primary').siblings().removeClass('active btn-primary').addClass('btn-outline-primary');
        fetchAnalysisData();
    });

    $('#inputYear').on('change', function() {
        if (mode === "1") fetchAnalysisData();
    });

    $(document).on('change', '.cat-check', renderCharts);

    if (window.initialData && window.initialData.length > 0) {
        updateSidebar();
        renderCharts();
    } else {
        fetchAnalysisData();
    }
});

function getMonthName(m) {
    return ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"][m - 1] || "N/A";
}