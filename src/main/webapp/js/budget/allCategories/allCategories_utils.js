/**
 * Maps frontend series keys to database strategy constants.
 */
const STRATEGY_MAPPING = {
    'regression': 'REGRESSION',
    'alphaSpike': 'ALPHA_SPIKE',
    'lvcf': 'LVCF',
    'avgStrict': 'AVG_STRICT',
    'inflation': 'INFLATION_ONLY',
    'zeroSum': 'ZERO_SUM'
};

const REVERSE_STRATEGY_MAPPING = Object.fromEntries(
    Object.entries(STRATEGY_MAPPING).map(([key, value]) => [value, key])
);

/**
 * Returns the formal strategy name for a given series key.
 * @param {string} key
 * @returns {string}
 */
function getStrategyFromKey(key) {
    return STRATEGY_MAPPING[key] || 'AVG_STRICT';
}

function getKeyFromStrategy(formalName) {
    return REVERSE_STRATEGY_MAPPING[formalName] || 'avgStrict';
}

/**
 * Ensures the hidden analysis ID input exists and has the correct value.
 */
function syncAnalysisId(id) {
    if ($('#analysisSubcatId').length === 0) {
        $('body').append(`<input type="hidden" id="analysisSubcatId" value="${id}">`);
    } else {
        $('#analysisSubcatId').val(id);
    }
    $('#categoryAnalysisSection').attr('data-current-id', id);
}

/**
 * Calculates linear regression stats and forecast totals.
 */
function calculateAnalysisMetrics(history, forecast) {
    const n = history.length;
    let metrics = {slope: 0, intercept: 0, rSquared: 0, histAvg: 0};

    if (n > 1) {
        let sumX = 0, sumY = 0, sumXY = 0, sumX2 = 0, sumY2 = 0;
        history.forEach((d, i) => {
            const x = i + 1;
            const y = d.historicalTruth;
            sumX += x;
            sumY += y;
            sumXY += x * y;
            sumX2 += x * x;
            sumY2 += y * y;
        });

        metrics.histAvg = sumY / n;
        metrics.slope = (n * sumXY - sumX * sumY) / (n * sumX2 - sumX * sumX);
        metrics.intercept = (sumY - metrics.slope * sumX) / n;

        const num = (n * sumXY - sumX * sumY);
        const den = (n * sumX2 - sumX * sumX) * (n * sumY2 - sumY * sumY);
        metrics.rSquared = (den === 0) ? 0 : Math.pow(num / Math.sqrt(den), 2);
    }

    const totals = {
        regression: forecast.reduce((sum, d) => sum + (d.regression || 0), 0),
        avgStrict: forecast.reduce((sum, d) => sum + (d.avgStrict || 0), 0),
        alphaSpike: forecast.reduce((sum, d) => sum + (d.alphaSpike || 0), 0),
        lvcf: forecast.reduce((sum, d) => sum + (d.lvcf || 0), 0),
        inflation: forecast.reduce((sum, d) => sum + (d.inflation || 0), 0),
        zeroSum: forecast.reduce((sum, d) => sum + (d.zeroSum || 0), 0)
    };

    return {metrics, totals};
}

/**
 * Calculates the financial delta between historical spending and projected costs.
 * @param {Array} data - The unified data array.
 * @param {string} strategyKey - The model to compare against (e.g., 'regression', 'inflation').
 * @returns {Object} Impact metrics.
 */
/**
 * Calculates the delta between the current limit and the selected model.
 */
function calculateModelImpact(data, strategy) {
    const history = data.filter(d => d.historicalTruth != null);
    if (history.length === 0) return {monthly: 0, percentChange: 0, isIncreasing: false};

    // Get current threshold and the projected value from the chosen strategy
    const currentThreshold = history[history.length - 1].threshold || 0;
    const lastRow = data[data.length - 1];
    const projectedValue = lastRow[strategy] || 0;

    const monthlyDiff = projectedValue - currentThreshold;

    // Avoid Divide by Zero
    const percentChange = currentThreshold !== 0
        ? (monthlyDiff / currentThreshold) * 100
        : 0;

    return {
        monthly: monthlyDiff,
        percentChange: percentChange,
        isIncreasing: monthlyDiff > 0
    };
}

/**
 * Calculates financial velocity.
 * Note: Reuses logic from calculateModelImpact but ensures
 * consistency for "Growth Impact" labels.
 */
function getGrowthMetrics(data, strategyKey = 'regression') {
    // Reuse the logic we built in the previous step
    return calculateModelImpact(data, strategyKey);
}

/**
 * Aligns all projection models to start exactly where historical data ends.
 * This prevents "gaps" in Highcharts when transitioning from history to forecast.
 *
 * @param {Array} data - The unified data array.
 * @returns {Array} The data array with anchored projection points.
 */
function anchorProjections(data) {
    // 1. Find the last index containing real spending data
    const lastHistIndex = data.map(d => d.historicalTruth)
        .findLastIndex(val => val !== null && val !== undefined);

    if (lastHistIndex === -1) return data;

    const anchorValue = data[lastHistIndex].historicalTruth;

    // 2. Identify keys that represent projection models (anything that isn't metadata)
    const projectionKeys = [
        'regression', 'avgStrict', 'lvcf',
        'inflation', 'alphaSpike', 'zeroSum'
    ];

    // 3. Return transformed data
    return data.map((row, idx) => {
        if (idx === lastHistIndex) {
            const anchoredRow = {...row};
            projectionKeys.forEach(key => {
                anchoredRow[key] = anchorValue;
            });
            return anchoredRow;
        }
        return row;
    });
}

/**
 * Calculates performance metrics for a specific period.
 */
function calculatePerformanceMetrics(item) {
    const budgetDiff = item.actualValue - item.budgetedValue;
    const thresholdDiff = item.actualValue - item.threshold;

    return {
        budgetDiff,
        thresholdDiff,
        isOverBudget: budgetDiff > 0,
        isOverThreshold: thresholdDiff > 0,
        percentOfThreshold: item.threshold > 0
            ? Math.min((item.actualValue / item.threshold) * 100, 100)
            : 0,
        formattedActual: item.actualValue.toLocaleString(undefined, {
            minimumFractionDigits: 2,
            maximumFractionDigits: 2
        })
    };
}

/**
 * Generates the specific transaction deep-link URL.
 */
function getPerformanceLink(period, categoryId) {
    const [month, year] = period.split('/');
    return `all-Transactions?month=${month}&year=${year}&category=${categoryId}`;
}

/**
 * Calculates annual summary metrics and identifies outlier months.
 */
function calculateAnnualMetrics(data) {
    // 1. Summation logic
    const totals = data.reduce((acc, m) => {
        acc.actual += m.actualValue;
        acc.budget += m.budgetedValue;
        acc.threshold += m.threshold;
        return acc;
    }, {actual: 0, budget: 0, threshold: 0});

    // 2. Outlier identification
    const sortedBySpend = [...data].sort((a, b) => b.actualValue - a.actualValue);

    return {
        totals,
        spikeMonth: sortedBySpend[0],
        leanMonth: sortedBySpend[sortedBySpend.length - 1],
        usagePercent: totals.threshold > 0 ? ((totals.actual / totals.threshold) * 100).toFixed(1) : 0,
        accuracyScore: totals.actual > 0 ? ((totals.budget / totals.actual) * 100).toFixed(0) : 0,
        isOverYearly: totals.actual > totals.threshold
    };
}