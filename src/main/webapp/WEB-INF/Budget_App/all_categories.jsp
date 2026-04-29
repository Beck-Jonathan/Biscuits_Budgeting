<%@include file="/WEB-INF/Budget_App/budget_top.jsp"%>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.css">

<div class="container my-4">
    <div class="row">
        <div class="col-12 text-center mb-4">
            <h1 class="fw-bold" style="color: #2c3e50;">Category Manager</h1>
            <p class="text-muted">Total Categories: <span class="fw-bold">${Categories.size()}</span></p>

        </div>
        <div class="row mb-4">
            <div class="col-12">
                <div class="budget-logic-legend p-4">
                    <div class="d-flex justify-content-between align-items-center mb-3">
                        <h5 class="fw-bold m-0">
                            <i class="bi bi-cpu me-2"></i>Projection Engine Logic
                        </h5>
                        <span class="badge rounded-pill bg-dark-subtle text-dark px-3 me-2">Active Models</span>

                        <div class="d-flex align-items-center gap-2">

                            <button id="btnAutoAssign"
                                    class="btn btn-primary btn-sm shadow-sm d-flex align-items-center">
                                <i class="bi bi-magic me-1"></i> Smart Assign Models
                            </button>

                            <button id="btnAutoColor"
                                    class="btn btn-primary btn-sm shadow-sm d-flex align-items-center">
                                <i class="bi bi-palette me-1"></i> Smart Assign Colors
                            </button>
                        </div>
                    </div>

                    <div class="row g-4">
                        <div class="col-md-4 col-sm-6">
                            <div class="logic-item">
                                <i class="bi bi-graph-up-arrow text-primary"></i>
                                <div>
                                    <span class="logic-label">Linear Regression</span>
                                    <p class="logic-desc">Solves $y = mx + b$ over your history. Ideal for utilities or
                                        income with steady growth/decay.</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 col-sm-6">
                            <div class="logic-item">
                                <i class="bi bi-lightning-fill text-warning"></i>
                                <div>
                                    <span class="logic-label">Alpha Spike</span>
                                    <p class="logic-desc">Hard-codes the "peak" month (Bonus/Tax) and applies Regression
                                        trends to the off-peak months.</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 col-sm-6">
                            <div class="logic-item">
                                <i class="bi bi-pause-fill" style="color: #6f42c1;"></i>
                                <div>
                                    <span class="logic-label">Last Value (LVCF)</span>
                                    <p class="logic-desc">"Last Value Carried Forward." Zero math—just mimics the most
                                        recent month's actual total.</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 col-sm-6">
                            <div class="logic-item">
                                <i class="bi bi-list-stars text-success"></i>
                                <div>
                                    <span class="logic-label">Strict Average</span>
                                    <p class="logic-desc">A flat mean across the full lookback period
                                        ($p\_months\_back$). Use for erratic, non-trending costs.</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 col-sm-6">
                            <div class="logic-item">
                                <i class="bi bi-percent text-info"></i>
                                <div>
                                    <span class="logic-label">Inflation Adjusted</span>
                                    <p class="logic-desc">Takes the average and compounds by 0.3% monthly. Best for
                                        groceries and general household items.</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 col-sm-6">
                            <div class="logic-item">
                                <i class="bi bi-x-circle text-danger"></i>
                                <div>
                                    <span class="logic-label">Zero Sum</span>
                                    <p class="logic-desc">Discards all history. Projections will only show your
                                        explicitly "Planned Transactions."</p>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>

        <div id="categoryAnalysisSection" class="row mb-4 animate__animated animate__fadeIn" style="display: none;">
            <div class="col-lg-8 mb-3">
                <div class="card shadow-sm border-0 h-100" style="border-radius: 15px;">
                    <div class="card-body">
                        <div class="d-flex justify-content-between align-items-center mb-2">
                            <h5 class="fw-bold mb-0">
                                <i class="bi bi-graph-up text-primary me-2"></i>
                                <span id="analysisChartTitle">Historical vs. Projected</span>
                            </h5>
                            <button class="btn btn-sm btn-light" onclick="$('#categoryAnalysisSection').slideUp()">
                                <i class="bi bi-x-lg"></i>
                            </button>
                        </div>
                        <div id="categoryAnalysisChart" style="width: 100%; height: 320px;"></div>
                    </div>
                </div>
            </div>

            <div class="col-lg-4 mb-3">
                <div class="card-body p-3">
                    <ul class="nav nav-pills nav-fill mb-3 small" id="categoryAnalysisTabs" role="tablist">
                        <li class="nav-item" role="presentation">
                            <button class="nav-link active py-1" id="overview-tab" data-bs-toggle="pill"
                                    data-bs-target="#tab-overview" type="button">Overview
                            </button>
                        </li>
                        <li class="nav-item" role="presentation">
                            <button class="nav-link py-1" id="forecast-tab" data-bs-toggle="pill"
                                    data-bs-target="#tab-forecast" type="button">Forecast
                            </button>
                        </li>
                        <li class="nav-item" role="presentation">
                            <button class="nav-link py-1" id="regression-tab" data-bs-toggle="pill"
                                    data-bs-target="#tab-regression" type="button">Regression
                            </button>
                        </li>
                        <li class="nav-item" role="presentation">
                            <button class="nav-link py-1" id="performance-tab" data-bs-toggle="pill"
                                    data-bs-target="#tab-performance" type="button">Performance
                            </button>
                        </li>
                    </ul>

                    <div class="tab-content" id="analysisTabContent">
                        <div class="row">
                            <h5 class="fw-bold mb-1 text-primary col-md-10" id="detailCatName">Category Name</h5>
                            <div id="detailLockStatus" class="mb-2 col-md-2 topLock"></div>
                        </div>

                        <div class="tab-pane fade show active" id="tab-overview" role="tabpanel">


                        <div class="p-2 bg-light rounded mb-3 small">
                                <div class="d-flex justify-content-between">
                                    <span class="text-secondary">Current Strategy:</span>
                                    <span class="badge bg-primary" id="detailStrategy">REGRESSION</span>
                                </div>
                                <div class="d-flex justify-content-between align-items-center mt-3 p-2 bg-light rounded"
                                     style="border: 1px solid #eee;">
    <span class="small fw-bold text-uppercase text-secondary" style="letter-spacing: 0.5px; font-size: 0.7rem;">
        Budgeted Amount
    </span>

                                    <div class="d-flex align-items-center">
                                        <span class="text-muted me-1 small">$</span>
                                        <input
                                                id="setBudgetedAmount"
                                                type="number"
                                                step="0.01"
                                                class="modern-budget-input"
                                                placeholder="0.00"
                                                data-focus-color="#0d6efd">
                                    </div>
                                    <div id="budgetUpdateFeedback"
                                         class="d-none mt-2 p-1 px-2 rounded small text-center fw-bold"
                                         style="font-size: 0.65rem;">
                                    </div>
                                </div>
                            </div>
                            <p class="small text-muted" id="detailInsights">
                                Select a category gear to see engine insights for your budget.
                            </p>
                        </div>

                        <div class="tab-pane fade" id="tab-forecast" role="tabpanel">
                            <h6 class="fw-bold small text-uppercase text-secondary">Retirement Impact</h6>
                            <div class="d-flex align-items-center gap-2 mb-3">
                                <i class="bi bi-calendar-check text-success fs-4"></i>
                                <div>
                                    <span class="d-block fw-bold" style="font-size: 0.8rem;">Burn at Age 65</span>
                                    <span class="text-dark h6 mb-0" id="forecastAgeValue">$0.00 /mo</span>
                                </div>
                            </div>
                            <div class="progress mb-2" style="height: 6px;">
                                <div class="progress-bar bg-info" role="progressbar" style="width: 70%"></div>
                            </div>
                            <p class="text-muted" style="font-size: 0.7rem;">
                                Based on current inflation ($0.3\%$), this category accounts for <b
                                    id="forecastPercent">0%</b> of your projected retirement expenses.
                            </p>
                        </div>

                        <div class="tab-pane fade" id="tab-regression" role="tabpanel">
                            <h6 class="fw-bold small text-uppercase text-secondary">Mathematical Fit</h6>
                            <table class="table table-sm table-borderless small mb-0">
                                <tbody>
                                <tr>
                                    <td class="text-secondary">Slope ($m$):</td>
                                    <td class="text-end fw-bold" id="mathSlope">0.00</td>
                                </tr>
                                <tr>
                                    <td class="text-secondary">Intercept ($b$):</td>
                                    <td class="text-end fw-bold" id="mathIntercept">0.00</td>
                                </tr>
                                <tr>
                                    <td class="text-secondary">R-Squared:</td>
                                    <td class="text-end text-success fw-bold" id="mathRSquared">0.00</td>
                                </tr>
                                </tbody>
                            </table>
                            <hr class="my-2">
                            <div class="alert alert-info p-2 mb-0" style="font-size: 0.7rem;">
                                <i class="bi bi-info-circle me-1"></i>
                                A high R-Squared indicates this category is highly predictable.
                            </div>
                        </div>

                        <div class="tab-pane fade" id="tab-performance" role="presentation">
                            <h6 class="fw-bold small text-uppercase text-secondary mb-3">Budget Accuracy</h6>

                            <div class="mb-3">
                                <label class="small text-muted mb-1 d-block text-uppercase fw-bold"
                                       style="font-size: 0.65rem; letter-spacing: 0.5px;">Analysis Year</label>
                                <div class="d-flex align-items-center gap-2">
                                    <div class="flex-grow-1" style="max-width: 50%;">
                                        <select class="form-select form-select-sm" id="performanceMonthSelect">
                                            <option value="null" selected disabled>Select...</option>
                                            <option value="2026">2026</option>
                                            <option value="2025">2025</option>
                                            <option value="2024">2024</option>
                                            <option value="2023">2023</option>
                                        </select>
                                    </div>
                                </div>
                                <div id="annualReportContainer" class="row mb-4 d-none">
                                </div>
                            </div>

                            <div id="performanceResults" class="animate__animated animate__fadeIn">
                                <div class="p-3 border rounded bg-light text-center">
                                    <p class="small text-muted mb-0">Select a year to compare your engine's forecast
                                        against actual spending.</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="super-category-wrapper mb-4">
            <div class="super-category-header p-3"
                 style="background: #f8f9fa; border-left: 5px solid #00CC00; border-bottom: 1px solid #dee2e6; cursor: pointer;"
                 data-bs-toggle="collapse"
                 data-bs-target="#collapse-selectors">

                <div class="row align-items-center">
                    <div class="col-md-3">
                        <label class="small fw-bold text-muted text-uppercase mb-1">Date Range</label>

                        <div class="input-group shadow-sm rounded border" onclick="event.stopPropagation()">

                            <select id="monthSelect" name="month" class="form-select border-0">
                                <option value="-1">Any</option>
                                <c:forEach var="m" begin="1" end="12">
                                    <option value="${m}"
                                        ${(empty month ? defaultMonth : month) == m ? 'selected' : ''}>
                                            ${m == 1 ? 'January' : m == 2 ? 'February' : m == 3 ? 'March' :
                                                    m == 4 ? 'April' : m == 5 ? 'May' : m == 6 ? 'June' :
                                                            m == 7 ? 'July' : m == 8 ? 'August' : m == 9 ? 'September' :
                                                                    m == 10 ? 'October' : m == 11 ? 'November' : 'December'}
                                    </option>
                                </c:forEach>
                            </select>

                            <span class="input-group-text bg-light border-0 text-muted">/</span>

                            <select id="yearSelect" name="year" class="form-select border-0">
                                <option value="-1">Any</option>
                                <c:forEach var="i" begin="2020" end="2030">
                                    <option value="${i}"
                                        ${(empty year ? defaultYear : year) == i ? 'selected' : ''}>
                                            ${i}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <jsp:include page="all_categories_Partial.jsp"></jsp:include>




        <div class="col" id="add-pill-wrapper">
            <div id="new-pill-container" class="modern-cat-card border-dashed"
                 data-id="NEWCARD">

                <div class="card-accent " id="new-card-accent" style="background-color: #0d6efd;">
                    <div class="swatch-container color-swatch-trigger">
                        <div class="color-swatch-trigger" style="background-color: #0d6efd;"></div>

                        <div class="picker-popover d-none shadow-lg">
                            <div class="wheel-canvas"></div>
                        </div>
                    </div>
                    <input type="hidden" id="new-category-color" value="#0d6efd">
                </div>

                <div class="card-body-content">
                    <div class="category-name-row">
                        <input type="text" id="new-category-name"
                               class="form-control form-control-sm border-0 bg-transparent fw-bold p-0"
                               placeholder="New Category Name..."
                               style="box-shadow: none; font-size: 0.9rem;"
                               onkeydown="if(event.key==='Enter') addNewCategory()">
                        <div id="new-category-error" class="text-danger mt-1 d-none"
                             style="font-size: 0.7rem; font-weight: bold;"></div>
                    </div>


                    <div class="strategy-row">
                <span class="text-muted" style="font-size: 0.65rem; text-transform: uppercase; letter-spacing: 0.5px;">
                    Model: <span class="fw-bold text-primary">AVG_STRICT</span>
                </span>
                    </div>

                    <div class="parent-row d-flex align-items-center gap-2">
                        <select id="new-category-parent" class="modern-select flex-grow-1"
                                onchange="updateAddPillIndicator(this)">
                            <c:forEach items="${ParentCategories}" var="parent">
                                <option value="${parent.parent_category_id}"
                                        data-type="${fn:toLowerCase(parent.transaction_type)}">
                                        ${fn:escapeXml(parent.super_category_name)}
                                </option>
                            </c:forEach>
                        </select>
                        <button onclick="addNewCategory()"
                                class="btn btn-primary btn-sm rounded-circle flex-shrink-0"
                                style="width: 26px; height: 26px; padding: 0;">
                            <i class="bi bi-plus"></i>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<h3>Below maaybe?</h3>
</div>
<div class="modal fade" id="deleteCategoryModal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content border-0 shadow-lg" style="border-radius: 15px;">
            <div class="modal-header border-0 pb-0">
                <h5 class="modal-title fw-bold text-danger">Delete Category?</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body py-3">
                <p class="mb-1">Are you sure you want to delete <span id="deleteTargetName"
                                                                      class="fw-bold text-dark"></span>?</p>
                <small class="text-muted">All existing transactions will be moved to <span
                        class="badge bg-light text-muted border">Uncategorized</span>.</small>
            </div>
            <div class="modal-footer border-0 pt-0">
                <button type="button" class="btn btn-light rounded-pill px-4" data-bs-dismiss="modal">Cancel</button>
                <button type="button" id="confirmDeleteBtn" class="btn btn-danger rounded-pill px-4 shadow-sm">Delete
                    Category
                </button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="autoAssignModal" data-bs-backdrop="static" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content border-0 shadow-lg">
            <div class="modal-header bg-primary text-white border-0">
                <h5 class="modal-title fw-bold"><i class="bi bi-cpu-fill me-2"></i>Projection Engine</h5>
                <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal"
                        aria-label="Close"></button>
            </div>
            <div class="modal-body p-4 text-center">
                <div class="mb-3">
                    <i class="bi bi-magic text-primary" style="font-size: 3rem;"></i>
                </div>
                <h4 class="fw-bold">Optimize Projection Models?</h4>
                <p class="text-muted">
                    This will analyze your transaction history for every category.
                    The engine will automatically select the best mathematical model
                    (Regression, Averaging, or Alpha Spikes) to improve your budget accuracy.
                </p>
                <div class="alert alert-warning border-0 small d-flex align-items-center">
                    <i class="bi bi-exclamation-triangle-fill me-2"></i>
                    Existing manual strategy assignments will be overwritten, unless locked.
                </div>
            </div>
            <div class="modal-footer border-0 pb-4 justify-content-center">
                <button type="button" class="btn btn-light px-4" data-bs-dismiss="modal">Keep Current</button>
                <button type="button" id="confirmAutoAssignBtn" class="btn btn-primary px-4 shadow-sm">
                    Run Analysis
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="autoAssignColorModal" data-bs-backdrop="static" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content border-0 shadow-lg">
            <div class="modal-header bg-info text-white border-0">
                <h5 class="modal-title fw-bold"><i class="bi bi-palette-fill me-2"></i>Design Engine</h5>
                <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal"
                        aria-label="Close"></button>
            </div>
            <div class="modal-body p-4 text-center">
                <div class="mb-3">
                    <i class="bi bi-paint-bucket text-info" style="font-size: 3rem;"></i>
                </div>
                <h4 class="fw-bold">Sync Category Colors?</h4>
                <p class="text-muted">
                    This will automatically update all subcategory accent colors to match
                    the brand colors of their Parent Categories.
                </p>
                <div class="alert alert-info border-0 small d-flex align-items-center text-start">
                    <i class="bi bi-info-circle-fill me-2"></i>
                    This helps maintain visual consistency across your dashboard and
                    spending reports.
                </div>
            </div>
            <div class="modal-footer border-0 pb-4 justify-content-center">
                <button type="button" class="btn btn-light px-4" data-bs-dismiss="modal">Cancel</button>
                <button type="button" id="confirmAutoAssignColorBtn" class="btn btn-info text-white px-4 shadow-sm">
                    Apply Colors
                </button>
            </div>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/Budget_App/budget_bottom.jsp"%>