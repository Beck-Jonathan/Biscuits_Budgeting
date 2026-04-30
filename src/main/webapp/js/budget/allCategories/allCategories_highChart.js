/**
 * Handles the transition between analysis tabs, including layout swapping
 * and restoring the engine chart when leaving the performance view.
 */
function handleTabTransition(e) {
    // targetId = the tab being opened (e.g., #tab-performance)
    const targetId = $(e.target).attr('data-bs-target');
    // relatedTarget = the tab being left (e.g., #tab-overview)
    const leavingTabId = $(e.relatedTarget).attr('data-bs-target');

    const $chart = $('#categoryAnalysisChart');
    const $chartCol = $('#categoryAnalysisSection > div:first-child');
    const $tabsCol = $('#categoryAnalysisSection > div:last-child');

    // --- 1. State Restoration Logic ---
    // If leaving Performance and moving to any other tab, restore the original Spline chart
    if (leavingTabId === '#tab-performance' && targetId !== '#tab-performance') {
        if (window.originalChartState && window.analysisChart) {
            restoreOriginalChart();
        }
    }

    // --- 2. Visual Transition: Phase 1 (Blur/Fade) ---
    $chart.removeClass('chart-ready').addClass('chart-reworking');

    // --- 3. Visual Transition: Phase 2 (Layout Swap) ---
    setTimeout(() => {
        const isPerformance = targetId === '#tab-performance';

        // Swap Column Widths: Performance needs more space for the table/year picker
        // col-lg-8 becomes col-lg-4 for the chart, and vice versa for tabs
        $chartCol.toggleClass('col-lg-8', !isPerformance).toggleClass('col-lg-4', isPerformance);
        $tabsCol.toggleClass('col-lg-4', !isPerformance).toggleClass('col-lg-8', isPerformance);

        // Update Chart Titles based on context
        if (isPerformance) {
            $('#analysisChartTitle').text('Budget Accuracy (Annual Performance)');
        } else {
            $('#analysisChartTitle').text('Historical vs. Projected');
        }

        // Force Highcharts to resize to its new container dimensions
        if (window.analysisChart) {
            window.analysisChart.reflow();
        }

        // --- 4. Visual Transition: Phase 3 (Focus) ---
        setTimeout(() => {
            $chart.removeClass('chart-reworking').addClass('chart-ready');
        }, 600);
    }, 300);
}

function restoreOriginalChart() {
    //console.log("Restoring original engine view...");
    const chart = window.analysisChart;
    const state = window.originalChartState;

    chart.update({
        xAxis: {categories: state.categories},
        title: {text: state.title}
    }, false);

    while (chart.series.length > 0) chart.series[0].remove(false);

    state.series.forEach(s => chart.addSeries(s, false));
    chart.redraw();
    window.originalChartState = null;
}

// --- 3. Forecast Interaction Functions ---

function handleForecastHover(e) {
    const $row = $(e.currentTarget);
    const $lock = $(e.currentTarget).closest('.card-body').find('.topLock').children().first();
    //console.log($lock[0])
    var locked = $($lock[0]).hasClass('bi-lock-fill')
    //console.log(locked)

    if (locked) {
        $row.removeClass('is-hoverable');
    } else {
        $row.addClass('is-hoverable');
    }
    const methodKey = $(this).data('series');
    const chart = Highcharts.charts.find(c => c && c.renderTo.id === 'categoryAnalysisChart');

    if (chart) {
        chart.series.forEach(s => {
            if (s.options.id === methodKey) {
                s.setState('hover');
                s.group.toFront();
            } else if (s.options.id !== 'historicalTruth') {
                s.setState('inactive');
            }
        });
    }
}

function resetForecastHover() {
    const chart = Highcharts.charts.find(c => c && c.renderTo.id === 'categoryAnalysisChart');
    if (chart) {
        chart.series.forEach(s => s.setState(''));
    }
}


function initAnalysisChart() {
    Highcharts.chart('categoryAnalysisChart', {
        chart: {type: 'areaspline', backgroundColor: 'transparent'},
        title: {text: null},
        xAxis: {categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun'], crosshair: true},
        yAxis: {title: {text: 'Monthly Flow ($)'}, labels: {format: '${value}'}},
        tooltip: {shared: true, valuePrefix: '$'},
        credits: {enabled: false},
        plotOptions: {areaspline: {fillOpacity: 0.1}},
        series: [{
            name: 'Historical Actuals',
            color: '#2c3e50',
            data: [2500, 2800, 2650, 3100, 2900, 3200]
        }, {
            name: 'Engine Projection',
            color: '#0d6efd',
            dashStyle: 'ShortDash',
            data: [null, null, null, null, null, 3200, 3350, 3500, 3650]
        }]
    });
}

function captureChartState() {
    if (!window.analysisChart) return;

    const chart = window.analysisChart;
    window.originalChartState = {
        categories: chart.xAxis[0].categories,
        title: $('#analysisChartTitle').text(),
        // Map the series to clean objects so Highcharts can re-add them later
        series: chart.series.map(s => ({
            id: s.options.id,
            name: s.name,
            type: s.type,
            data: s.options.data,
            color: s.color,
            fillOpacity: s.options.fillOpacity
        }))
    };
}

/**
 * Configures and renders the Highcharts analysis chart.
 * @param {Array} data - Processed data array.
 * @param {Array} categories - X-axis labels.
 */
function renderAnalysisChart(data, categories, strategy) {
    //console.log("chart strat:" + strategy)
    var key = getKeyFromStrategy(strategy);
    //console.log("chart key:" + strategy)
    const seriesData = [
        {
            id: 'historicalTruth',
            name: 'Historical Truth',
            data: data.map(d => d.historicalTruth),
            color: '#4400DD',
            type: 'areaspline',
            fillOpacity: 0.1
        },
        {
            id: 'regression',
            name: 'Regression',
            data: data.map(d => d.regression),
            color: '#0d6efd',
            dashStyle: 'ShortDot'
        },
        {
            id: 'avgStrict',
            name: 'Strict Average',
            data: data.map(d => d.avgStrict),
            color: '#198754',
            dashStyle: 'ShortDot'
        },
        {id: 'lvcf', name: 'LVCF', data: data.map(d => d.lvcf), color: '#6f42c1', dashStyle: 'ShortDot'},
        {
            id: 'inflation',
            name: 'Inflation (0.3%)',
            data: data.map(d => d.inflation),
            color: '#0dcaf0',
            dashStyle: 'ShortDot'
        },
        {
            id: 'alphaSpike',
            name: 'Alpha Spike',
            data: data.map(d => d.alphaSpike),
            color: '#fd7e14',
            dashStyle: 'ShortDot'
        },
        {id: 'zeroSum', name: 'Zero Sum', data: data.map(d => d.zeroSum), color: '#6c757d', dashStyle: 'ShortDot'}
    ];
    seriesData.forEach(s => {
        if (s.id === key) {
            s.lineWidth = 4;        // Make the selected line thick
            s.zIndex = 10;          // Bring it to the front
            s.opacity = 1;
        } else {
            s.lineWidth = 1;        // Thin out others
            s.opacity = 0.9;        // Mute others
            s.zIndex = 1;
        }
    });

    window.analysisChart = Highcharts.chart('categoryAnalysisChart', {
        chart: {type: 'line', backgroundColor: 'transparent'},
        title: {text: null},
        xAxis: {categories: categories, crosshair: true, tickInterval: 6},
        yAxis: {title: {text: 'Monthly Flow ($)'}, labels: {format: '${value}'}},
        tooltip: {shared: true, valuePrefix: '$', valueDecimals: 2},
        credits: {enabled: false},
        plotOptions: {
            series: {
                marker: {enabled: false},
                states: {hover: {lineWidthPlus: 3}, inactive: {opacity: 0.3}}
            }
        },
        series: seriesData
    });

    // Capture state for restoration
    window.originalChartState = {
        categories: categories,
        series: seriesData.map(s => ({...s, data: [...s.data]}))
    };
}

/**
 * Switches the chart to Bar mode and renders annual budget performance.
 * @param {Object} chart - The Highcharts instance.
 * @param {Array} data - The annual performance data array.
 * @param {string|number} year - The year for the title.
 */
function renderPerformanceChart(chart, data, year) {
    if (!chart) return;

    const periods = data.map(d => d.period);
    const budgeted = data.map(d => d.budgetedValue);
    const actuals = data.map(d => d.actualValue);
    const thresholds = data.map(d => d.threshold);

    // Wipe existing series
    while (chart.series.length > 0) {
        chart.series[0].remove(false);
    }

    // Update Axis and Title
    chart.update({
        xAxis: {categories: periods},
        title: {text: `Budget Performance: ${year}`}
    }, false);

    // Add new series
    chart.addSeries({name: 'Budgeted', data: budgeted, type: 'column', color: '#6c757d', opacity: 0.4}, false);
    chart.addSeries({name: 'Actual Spending', data: actuals, type: 'column', color: '#0d6efd'}, false);
    chart.addSeries({
        name: 'Hard Limit',
        data: thresholds,
        type: 'line',
        color: '#dc3545',
        dashStyle: 'Dot',
        marker: {enabled: false}
    }, false);

    chart.redraw();
}