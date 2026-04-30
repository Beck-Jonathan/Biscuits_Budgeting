/**
 * Generates the HTML for the model comparison rows.
 */
/**
 * Renders the Forecast Tab content safely.
 * Logic updated to handle undefined totals.
 */
function renderForecastComparison(totals, histAvg, activeStrategy, isLocked) {
    // 1. Safety Check: Provide defaults if totals or threshold is missing
    const safeThreshold = (totals && totals.threshold !== undefined) ? totals.threshold : 0;
    const safeHistAvg = histAvg || 0;

    const strategies = [
        {id: 'avgStrict', label: 'Strict Average', icon: 'bi-list-stars', color: '#198754'},
        {id: 'regression', label: 'Linear Regression', icon: 'bi-graph-up-arrow', color: '#0d6efd'},
        {id: 'alphaSpike', label: 'Alpha Spike (Aggressive)', icon: 'bi-lightning-fill', color: '#fd7e14'},
        {id: 'lvcf', label: 'LVCF (Last Valid)', icon: 'bi-pause-fill', color: '#6f42c1'},
        {id: 'inflation', label: 'Inflation Adjusted', icon: 'bi-percent', color: '#0dcaf0'}
    ];

    // ... (Strategy button mapping remains the same) ...

    return `
        <div class="row g-3">
            <div class="col-md-5">
                <h6 class="text-uppercase text-muted fw-bold mb-3" style="font-size: 0.7rem;">Select Projection Model</h6>
                <div class="forecast-strategy-list">
                    ${strategyButtons}
                </div>
            </div>
            <div class="col-md-7 border-start">
                <div class="p-3 bg-light rounded">
                    <h6 class="text-uppercase text-muted fw-bold mb-2" style="font-size: 0.7rem;">Forecast Summary</h6>
                    <div class="d-flex justify-content-between mb-1">
                        <span class="small">Historical Avg:</span>
                        <span class="fw-bold small">$${safeHistAvg.toLocaleString()}</span>
                    </div>
                    <div class="d-flex justify-content-between mb-3">
                        <span class="small">Current Limit:</span>
                        <span class="fw-bold small text-primary">$${safeThreshold.toLocaleString()}</span>
                    </div>
                    <button class="btn btn-primary btn-sm w-100 rounded-pill forecastButton" 
                            ${isLocked ? 'disabled' : ''}
                            onclick="applySelectedStrategy()">
                        Apply Model to Budget
                    </button>
                </div>
            </div>
        </div>`;
}

/**
 * Returns the HTML for the Budget Velocity card.
 * @param {Object} metrics - The impact metrics object.
 */
function renderGrowthImpactHtml(metrics) {

    const {
        monthly = 0,
        yearly = 0,
        totalThreeYear = 0,
        isIncreasing = false
    } = metrics;
    const colorClass = isIncreasing ? 'text-danger' : 'text-success';
    const bgClass = isIncreasing ? 'bg-soft-danger text-danger' : 'bg-soft-success text-success';
    const arrowIcon = isIncreasing ? 'bi-graph-up-arrow' : 'bi-graph-down-arrow';
    const statusText = isIncreasing ? 'Expanding' : 'Contracting';

    return `
        <div class="p-3 border rounded bg-light mt-2 animate__animated animate__fadeIn">
            <div class="d-flex justify-content-between align-items-center mb-3">
                <h6 class="fw-bold small text-uppercase text-secondary mb-0">Budget Velocity</h6>
                <span class="badge ${bgClass} rounded-pill">
                    <i class="bi ${arrowIcon} me-1"></i>${statusText}
                </span>
            </div>
            <div class="row text-center g-0">
                <div class="col-6 border-end">
                    <span class="d-block text-muted" style="font-size: 0.7rem;">Monthly Change</span>
                    <span class="h5 mb-0 fw-bold ${colorClass}">
                        ${isIncreasing ? '+' : ''}$${monthly.toFixed(2)}
                    </span>
                </div>
                <div class="col-6">
                    <span class="d-block text-muted" style="font-size: 0.7rem;">Annual Change</span>
                    <span class="h5 mb-0 fw-bold ${colorClass}">
                        ${isIncreasing ? '+' : ''}$${yearly.toFixed(2)}
                    </span>
                </div>
            </div>
            <div class="mt-3 pt-2 border-top">
                <p class="mb-0 text-muted" style="font-size: 0.75rem;">
                    Compared to your historical average, this category is projected to cost you 
                    <b class="${colorClass}">$${Math.abs(totalThreeYear).toLocaleString(undefined, {maximumFractionDigits: 0})}</b> 
                    ${isIncreasing ? 'more' : 'less'} over the next 3 years.
                </p>
            </div>
        </div>`;
}

/**
 * Renders the HTML for a single Performance Card.
 */
function renderPerformanceCardHtml(item, metrics, linkUrl) {
    const statusClass = metrics.isOverThreshold ? 'text-danger' : 'text-success';
    const progressBarClass = metrics.isOverThreshold ? 'bg-danger' : 'bg-success';
    const thresholdLabel = metrics.isOverThreshold ? 'BREACH' : 'SAFE';
    const thresholdBadge = metrics.isOverThreshold
        ? 'bg-danger text-white'
        : 'bg-success-subtle text-success border border-success-subtle';

    return `
    <div class="col-md-3 mb-3 animate__animated animate__fadeInUp">
        <a href="${linkUrl}" class="text-decoration-none">
            <div class="card h-100 border-0 shadow-sm overflow-hidden performance-card-hover">
                <div class="progress rounded-0" style="height: 8px; background-color: rgba(0,0,0,0.05);">
                    <div class="progress-bar ${progressBarClass} progress-bar-striped" 
                         style="width: ${metrics.percentOfThreshold}%"></div>
                </div>
                <div class="card-header bg-white py-2 d-flex justify-content-between align-items-center">
                    <span class="fw-bold text-dark small"><i class="bi bi-calendar3 me-2"></i>${item.period}</span>
                    <span class="badge ${thresholdBadge} rounded-pill px-2" style="font-size: 0.65rem;">
                        ${thresholdLabel}
                    </span>
                </div>
                <div class="card-body p-3 pt-1">
                    <div class="text-center mb-3">
                        <small class="text-uppercase text-muted d-block" style="font-size: 0.65rem;">Actual Spent</small>
                        <h3 class="fw-black mb-0 ${statusClass}">$${metrics.formattedActual}</h3>
                    </div>
                    <div class="row g-0 py-2 border-top">
                        <div class="col-6 border-end pe-2">
                            <small class="text-uppercase text-muted d-block mb-1" style="font-size: 0.6rem;">Vs. Forecast</small>
                            <span class="fw-bold ${metrics.isOverBudget ? 'text-danger' : 'text-success'}">
                                ${metrics.isOverBudget ? '+' : '-'}$${Math.abs(metrics.budgetDiff).toFixed(2)}
                            </span>
                        </div>
                        <div class="col-6 ps-3">
                            <small class="text-uppercase text-muted d-block mb-1" style="font-size: 0.6rem;">Vs. Limit</small>
                            <span class="fw-bold ${metrics.isOverThreshold ? 'text-danger' : 'text-success'}">
                                ${metrics.isOverThreshold ? '+' : '-'}$${Math.abs(metrics.thresholdDiff).toFixed(2)}
                            </span>
                        </div>
                    </div>
                </div>
            </div>
        </a>
    </div>`;
}

/**
 * Renders the HTML for the Annual Report Card component.
 */
function renderAnnualReportHtml(year, metrics, categoryId) {
    const {totals, spikeMonth, leanMonth, usagePercent, accuracyScore, isOverYearly} = metrics;
    const statusColor = isOverYearly ? 'text-danger' : 'text-success';
    const barClass = isOverYearly ? 'bg-danger' : 'bg-success';

    return `
    <div class="col-12 animate__animated animate__fadeIn">
        <div class="card border-0 shadow-sm overflow-hidden" style="background: linear-gradient(to right, #ffffff, #f8f9fa);">
            <div class="card-body p-4">
                <div class="row align-items-center">
                    <div class="col-md-4 border-end">
                        <h6 class="text-uppercase text-muted fw-bold mb-3" style="font-size: 0.7rem;">${year} Annual Performance</h6>
                        <h2 class="fw-black ${statusColor} mb-1">$${totals.actual.toLocaleString(undefined, {minimumFractionDigits: 2})}</h2>
                        <p class="small text-muted mb-0">Total Year Spend vs. <span class="fw-bold">$${totals.threshold.toLocaleString()}</span> Limit</p>
                        <div class="progress mt-3" style="height: 10px;">
                            <div class="progress-bar ${barClass}" style="width: ${Math.min(usagePercent, 100)}%"></div>
                        </div>
                        <small class="mt-1 d-block text-muted" style="font-size: 0.65rem;">Utilized ${usagePercent}% of annual safety threshold</small>
                    </div>
                    <div class="col-md-5 px-4 border-end">
                        <div class="row">
                            <div class="col-6 mb-3">
                                <small class="text-muted d-block text-uppercase" style="font-size: 0.6rem;">Highest Spending</small>
                                <span class="fw-bold text-dark">${spikeMonth.period}</span>
                                <div class="text-danger fw-bold">$${spikeMonth.actualValue.toFixed(2)}</div>
                            </div>
                            <div class="col-6 mb-3">
                                <small class="text-muted d-block text-uppercase" style="font-size: 0.6rem;">Lowest Spending</small>
                                <span class="fw-bold text-dark">${leanMonth.period}</span>
                                <div class="text-success fw-bold">$${leanMonth.actualValue.toFixed(2)}</div>
                            </div>
                            <div class="col-12">
                                <div class="p-2 rounded bg-white border border-light">
                                    <small class="d-block text-muted mb-1" style="font-size: 0.65rem;">Budget Accuracy Score</small>
                                    <div class="h5 mb-0 fw-bold text-primary">${accuracyScore}%</div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3 text-center">
                        <a href="all-Transactions?year=${year}&category=${categoryId}" 
                           class="btn btn-outline-primary btn-sm rounded-pill px-4 shadow-sm">Detailed Annual Log</a>
                    </div>
                </div>
            </div>
        </div>
    </div>`;
}

/**
 * Renders a new modern category card.
 *
 * @param {Object} cat - Object containing id, name, color, type.
 * @param {string} parentSelectHtml - Pre-rendered options for the parent dropdown.
 * @returns {string} The complete HTML column.
 */
function renderCategoryCard(cat, parentSelectHtml) {
    // Sanitize name for the delete confirmation to prevent JS errors
    const escapedName = cat.name.replace(/'/g, "\\'");

    return `
        <div class="col animate__animated animate__zoomIn">
            <div class="modern-cat-card border-${cat.type}" data-id="${cat.id}" data-strategy="AVG_STRICT">
                <div class="card-accent" style="background-color: ${cat.color};">
                    <div class="swatch-container">
                        <div class="color-swatch-trigger" style="background-color: ${cat.color};"></div>
                        <div class="picker-popover d-none shadow-lg">
                            <div class="wheel-canvas"></div>
                        </div>
                    </div>
                </div>
                <div class="card-body-content">
                    <div class="card-actions-modern">
                        <i class="bi bi-search action-icon-modern search-trigger" onclick="handleMagnifyClick(event, '${cat.id}')"></i>
                        <i class="bi bi-unlock action-icon-modern lock-trigger" onclick="handleLockToggle(event, '${cat.id}')"></i>
                        <i class="bi bi-gear action-icon-modern gear-trigger" onclick="handleGearClick(event, '${cat.id}')"></i>
                        <i class="bi bi-x action-icon-modern delete-trigger" onclick="confirmDeleteCategory('${cat.id}', '${escapedName}')"></i>
                    </div>
                    <div class="category-name-row">
                        <span class="category-text" contenteditable="true">${cat.name}</span>
                    </div>
                    
                    ${renderMiniAnalysis(cat.color)}

                    <div class="strategy-row">
                        <i class="bi bi-graph-up-arrow strategy-icon" data-val="REGRESSION"></i>
                        <i class="bi bi-lightning-fill strategy-icon" data-val="ALPHA_SPIKE"></i>
                        <i class="bi bi-pause-fill strategy-icon" data-val="LVCF"></i>
                        <i class="bi bi-list-stars strategy-icon active-strategy" data-val="AVG_STRICT" style="color: #198754; opacity: 1;"></i>
                        <i class="bi bi-percent strategy-icon" data-val="INFLATION_ONLY"></i>
                        <i class="bi bi-x-circle strategy-icon" data-val="ZERO_SUM"></i>
                    </div>
                    <div class="parent-row">
                        <select class="modern-select" onchange="updateParentCategory('${cat.id}', this.value)">
                            ${parentSelectHtml}
                        </select>
                    </div>
                </div>
            </div>
        </div>`;
}

/**
 * Small sub-template for the progress bar section.
 */
function renderMiniAnalysis(color) {
    return `
        <div class="subcategory-mini-analysis mb-2 mt-1">
            <div class="d-flex justify-content-between align-items-center mb-1" style="font-size: 0.75rem;">
                <span class="fw-bold text-dark">$0.00</span>
                <span class="text-muted">of <span class="target-text-label">$0.00</span></span>
            </div>
            <div class="progress" style="height: 4px; background-color: #e9ecef;">
                <div class="progress-bar progress-bar-fill" style="width: 0%; background-color: ${color};"></div>
            </div>
        </div>`;
}

/**
 * Renders the Retirement Impact progress bar and text wrapper.
 * This is appended to the bottom of the Forecast tab.
 */
function renderRetirementProgress() {
    return `
        <div class="mt-3 pt-3 border-top">
            <h6 class="fw-bold small text-uppercase text-secondary">
                Retirement Impact
            </h6>
            <div class="progress mb-2" style="height: 8px; background-color: #e9ecef; border-radius: 4px;">
                <div id="retirementProgressBar" 
                     class="progress-bar bg-info" 
                     role="progressbar" 
                     style="width: 0%; transition: width 0.8s cubic-bezier(0.34, 1.56, 0.64, 1);">
                </div>
            </div>
            <p class="text-muted mb-0" style="font-size: 0.75rem;">
                This category accounts for <b id="forecastPercent">0%</b> of your projected retirement burn.
            </p>
        </div>
    `;
}

/**
 * Renders the Forecast Tab content with strategy selection buttons.
 */
function renderForecastComparison(totals, histAvg, activeStrategy, isLocked) {

    // Define the strategy definitions for the button loop
    const strategies = [
        {id: 'avgStrict', alt: "avg_strict", label: 'Strict Average', icon: 'bi-list-stars', color: '#198754'},
        {id: 'regression', alt: 'regression', label: 'Linear Regression', icon: 'bi-graph-up-arrow', color: '#0d6efd'},
        {
            id: 'alphaSpike',
            alt: 'alpha_Spike',
            label: 'Alpha Spike (Aggressive)',
            icon: 'bi-lightning-fill',
            color: '#fd7e14'
        },
        {id: 'lvcf', alt: 'lvcf', label: 'LVCF (Last Valid)', icon: 'bi-pause-fill', color: '#6f42c1'},
        {id: 'inflation', alt: 'inflation_only', label: 'Inflation Adjusted', icon: 'bi-percent', color: '#0dcaf0'},
        {id: 'zeroSum', alt: 'zero_sum', label: 'Zero Out', icon: 'bi-x-circle', color: '#6C757D'}
    ];
    const safeThreshold = (totals && totals.threshold !== undefined) ? totals.threshold : 0;
    const safeHistAvg = histAvg || 0;
    const strategyButtons = strategies.map(s => {

        const isActive = activeStrategy.toLowerCase() === s.alt.toLowerCase();

        const activeClass = isActive ? 'active border-primary' : '';
        const activeStyle = isActive ? 'background-color: rgba(13, 110, 253, 0.05);' : '';

        return `
            <div class="forecast-row p-2 mb-2 rounded border transition-all pointer ${activeClass}" 
                 data-series="${s.id}" 
                 style="cursor: pointer; ${activeStyle}"
                 onclick="handleStrategyPreview(event,'${s.id}')">
                <div class="d-flex align-items-center justify-content-between">
                    <div class="d-flex align-items-center">
                        <i class="bi ${s.icon} me-2" style="color: ${s.color};"></i>
                        <span class="small fw-bold">${s.label}</span>
                    </div>
                    <i class="bi bi-chevron-right small opacity-50"></i>
                </div>
            </div>`;
    }).join('');

    return `
        <div class="row g-3">
            <div class="col-md-12">
                <h6 class="text-uppercase text-muted fw-bold mb-3" style="font-size: 0.7rem;">Select Projection Model</h6>
                <div class="forecast-strategy-list">
                    ${strategyButtons}
                </div>
            </div>
            
            </div>
        </div>
    `;
}