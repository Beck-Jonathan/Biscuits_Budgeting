$(document).ready(function() {
    let currentData = window.initialData;
    let mode = "0";  // 0=Annual, 1=Monthly
    let level = "0"; // 0=Sub, 1=Super

    // --- New Select/Deselect Listeners ---

// Select All
    $(document).on('click', '#selectAll', function() {
        $('.cat-check').prop('checked', true);
        renderCharts(); // Update charts immediately
    });

// Deselect All
    $(document).on('click', '#deselectAll', function() {
        $('.cat-check').prop('checked', false);
        renderCharts(); // Update charts immediately
    });

    const updateSidebar = () => {
        const list = $('#categoryList').empty();
        if (!currentData || currentData.length === 0) return;

        // Use the first period to populate the sidebar
        currentData[0].forEach(cat => {
            list.append(`
                <li class="category-item">
                    <div class="form-check d-flex align-items-center">
                        <input class="form-check-input cat-check me-2" type="checkbox" 
                               value="${cat.category_Name}" id="c_${cat.category_ID}" checked>
                        <span class="color-swatch" style="background-color:${cat.color_id}; width:12px; height:12px; border-radius:50%; margin-right:10px; display:inline-block;"></span>
                        <label class="form-check-label small" for="c_${cat.category_ID}">${cat.category_Name}</label>
                    </div>
                </li>`);
        });
    };

    const renderCharts = () => {
        if (!currentData || currentData.length === 0) return;

        // Filter out 'Total In' to prevent double-counting in the stacks
        const selectedCats = $('.cat-check:checked').map(function() { return this.value; }).get()
            .filter(name => name.toLowerCase() !== "total in");

        let trendSeries = [];
        let pieDataPoints = [];

        // 1. Define X-Axis Labels (Years or Months)
        const categories = currentData.map(period => {
            const firstEntry = period[0];
            return mode === "0" ? firstEntry.year : getMonthName(firstEntry.year);
        });

        // 2. Loop through selected categories to build series
        selectedCats.forEach(name => {
            let color = "";
            const lowerName = name.toLowerCase();

            // LOGIC: Three Stack Sorting
            let stackGroup = 'Expense';
            if (lowerName.includes("income") || lowerName.includes("wages") || lowerName.includes("deposit")) {
                stackGroup = 'Income';
            } else if (lowerName.includes("invest") || lowerName.includes("401k") || lowerName.includes("ira") || lowerName.includes("roth") || lowerName.includes("brokerage")) {
                stackGroup = 'Investment';
            }

            const dataValues = currentData.map(period => {
                const match = period.find(c => c.category_Name === name);
                if (match) color = match.color_id;
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

                // Add to Pie Chart (Latest period data)
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

        // 3. Render Main Three-Stack Chart
        Highcharts.chart('chartContainer', {
            chart: { type: 'column' },
            title: { text: "Budget Analysis Stacks", style: { fontWeight: 'bold' } },
            xAxis: { categories: categories },
            yAxis: {
                title: { text: 'Total Amount ($)' },
                labels: { format: '${value}' }
            },
            legend: { enabled: true },
            plotOptions: {
                column: {
                    stacking: 'normal',
                    borderWidth: 0
                }
            },
            tooltip: {
                shared: true,
                useHTML: true,
                outside: true,
                backgroundColor: 'rgba(255, 255, 255, 0.98)',
                borderWidth: 0,
                shadow: true,
                hideDelay: 0,
                padding: 0,
                // Positions the tooltip 20px away from the hovered point
                positioner: function(labelWidth, labelHeight, point) {
                    let x;
                    const chartWidth = this.chart.plotWidth;
                    const padding = 20;

                    // Check if we are on the right half of the chart
                    if (point.plotX > chartWidth / 2) {
                        // Position to the LEFT of the cursor
                        x = point.plotX + this.chart.plotLeft - labelWidth - padding;
                    } else {
                        // Position to the RIGHT of the cursor
                        x = point.plotX + this.chart.plotLeft + padding;
                    }

                    let y = point.plotY + this.chart.plotTop - (labelHeight / 2);

                    // Final safety checks for chart boundaries
                    return {
                        x: Math.max(10, Math.min(x, this.chart.chartWidth - labelWidth - 10)),
                        y: Math.max(10, y)
                    };
                },
                formatter: function() {
                    let s = `<div style="padding: 15px; min-width: 480px; box-sizing: border-box;">`;
                    s += `<div style="font-size: 13px; font-weight: bold; margin-bottom: 8px; border-bottom: 2px solid #3498db; color: #2c3e50;">${this.x}</div>`;
                    s += '<div style="display: flex; flex-wrap: wrap; width: 100%;">';

                    let inTotal = 0, outTotal = 0, investTotal = 0;

                    this.points.forEach(point => {
                        const val = point.y;
                        const stack = point.series.options.stack;
                        if (stack === 'Income') inTotal += val;
                        else if (stack === 'Investment') investTotal += val;
                        else outTotal += val;

                        s += `
                            <div style="width: 50%; padding: 2px 0; display: flex; align-items: center; font-size: 11px;">
                                <span style="color:${point.color}; margin-right: 6px; font-size: 14px;">\u25CF</span>
                                <span style="white-space: nowrap; overflow: hidden; text-overflow: ellipsis; max-width: 150px; color: #555;">${point.series.name}:</span>
                                <b style="margin-left: auto; padding-right: 12px; color: #222;">$${val.toLocaleString()}</b>
                            </div>`;
                    });

                    s += '</div>';

                    const net = inTotal - outTotal - investTotal;
                    s += `<div style="margin-top: 10px; padding-top: 8px; border-top: 1px dashed #ccc; font-size: 11px;">
                            <div style="display: flex; justify-content: space-between; margin-bottom: 3px;">
                                <span>Total Income: <b style="color: #2c3e50;">$${inTotal.toLocaleString()}</b></span>
                                <span>Total Expenses: <b style="color: #2c3e50;">$${outTotal.toLocaleString()}</b></span>
                            </div>
                            <div style="display: flex; justify-content: space-between;">
                                <span>Total Invested: <b style="color: #2c3e50;">$${investTotal.toLocaleString()}</b></span>
                                <span>Net Savings: <b style="color:${net >= 0 ? '#27ae60' : '#e74c3c'}">$${net.toLocaleString()}</b></span>
                            </div>
                          </div>`;

                    s += '</div>';
                    return s;
                }
            },
            series: trendSeries
        });

        // 4. Render Pie Chart
        Highcharts.chart('pieContainer', {
            chart: { type: 'pie' },
            title: { text: 'Current Period Share' },
            tooltip: { pointFormat: '<b>${point.y:,.2f}</b> ({point.percentage:.1f}%)' },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: { enabled: true, format: '{point.name}: {point.percentage:.1f}%' },
                    showInLegend: true
                }
            },
            series: [{
                name: 'Amount',
                data: pieDataPoints
            }]
        });
    };

    const fetchAnalysisData = () => {
        const selectedYear = $('#inputYear').val();
        $.get("AnalysisAJAX", { mode, level, year: selectedYear }, (res) => {
            currentData = res;
            updateSidebar();
            renderCharts();
        });
    };

    // UI BUTTONS
    $('#btnAnnual, #btnMonthly').off().click(function() {
        mode = $(this).data('val').toString();
        $(this).addClass('active btn-primary').removeClass('btn-outline-primary');
        $(this).siblings().removeClass('active btn-primary').addClass('btn-outline-primary');

        if (mode === "1") $('#yearSelectorContainer').show();
        else $('#yearSelectorContainer').hide();

        fetchAnalysisData();
    });

    $('#btnSub, #btnSuper').off().click(function() {
        level = $(this).data('val').toString();
        $(this).addClass('active btn-primary').removeClass('btn-outline-primary');
        $(this).siblings().removeClass('active btn-primary').addClass('btn-outline-primary');
        fetchAnalysisData();
    });

    $('#inputYear').on('change', function() {
        if (mode === "1") fetchAnalysisData();
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

function getMonthName(m) {
    return ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"][m - 1] || "N/A";
}