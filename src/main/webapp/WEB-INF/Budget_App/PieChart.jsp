<%@include file="/WEB-INF/Budget_App/budget_top.jsp"%>
<div id="budget-app-container" class="container-fluid py-4">
    <div class="row mb-4 text-center">
        <div class="col-12">
            <h1 class="fw-bold" style="color: #2c3e50;">Biscuit's Budget Analysis</h1>
            <div class="badge bg-primary text-wrap" style="width: 6rem;">V 2.0</div>
        </div>
    </div>

    <div class="row g-4">
        <div class="col-lg-3">
            <div class="budget-panel mb-3 p-3">
                <label class="small fw-bold text-uppercase text-muted mb-2 d-block">Account</label>
                <select class="form-select border-0 bg-light mb-3" id="bankAccountSelect" name="bankAccountSelect">
                    <option value="">All Accounts</option>
                    <c:forEach items="${BankAccounts}" var="bank">
                        <option value="${bank.bank_Account_ID}">${bank.account_Nickname}</option>
                    </c:forEach>
                </select>

                <label class="small fw-bold text-uppercase text-muted mb-2 d-block">View Level</label>
                <div class="btn-group w-100 mb-3">
                    <button id="btnSub" class="btn btn-outline-primary active" data-val="0">Sub</button>
                    <button id="btnSuper" class="btn btn-outline-primary" data-val="1">Super</button>
                </div>

                <label class="small fw-bold text-uppercase text-muted mb-2 d-block">Timeframe</label>
                <div class="btn-group w-100">
                    <button id="btnAnnual" class="btn btn-outline-primary active" data-val="0">Yearly</button>
                    <button id="btnMonthly" class="btn btn-outline-primary" data-val="1">Monthly</button>
                    <button id="btnForecast" class="btn btn-outline-primary" data-val="2">Forecast</button>
                </div>
            </div>

            <div id="yearSelectorContainer" class="budget-panel mb-3 p-3" style="display:none;">
                <label class="small fw-bold text-uppercase text-muted mb-2 d-block">Focus Year</label>
                <select class="form-select border-0 bg-light" id="inputYear">
                    <c:forEach items="${yearRange}" var="year">
                        <option value="${year}" ${year eq 2026 ? 'selected' : ''}>${year}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="budget-panel mb-3 p-3">
                <label for="openingBalance" class="small fw-bold text-uppercase text-muted mb-2 d-block">Starting Cash
                    ($)</label>
                <input type="number" class="form-control form-control-sm border-0 bg-light" id="openingBalance"
                       value="30000">
            </div>

            <div id="forecastControlsContainer" class="budget-panel mb-3 p-3"
                 style="display:none; border-left: 4px solid #f39c12;">
                <label class="small fw-bold text-uppercase text-muted mb-2 d-block">Forecast Settings</label>

                <div class="mb-3">
                    <label class="small text-muted">History (Months Back)</label>
                    <input type="number" id="monthsBack" class="form-control form-control-sm border-0 bg-light"
                           value="24" min="6" max="60">
                </div>

                <div>
                    <label class="small text-muted">Projection (Months Forward)</label>
                    <input type="number" id="monthsForward" class="form-control form-control-sm border-0 bg-light"
                           value="12" min="1" max="36">
                </div>
            </div>

            <div class="budget-panel shadow-sm d-flex flex-column" style="max-height: 500px;">
                <div class="panel-header d-flex justify-content-between align-items-center bg-primary text-white p-2">
                    <span class="small fw-bold text-uppercase">Filters</span>
                    <div class="d-flex gap-2">
                        <button type="button" class="btn btn-xxs btn-outline-light py-0 px-2" id="selectAll" style="font-size: 0.65rem;">ALL</button>
                        <button type="button" class="btn btn-xxs btn-outline-light py-0 px-2" id="deselectAll" style="font-size: 0.65rem;">NONE</button>
                    </div>
                </div>
                <div class="panel-body p-0" style="overflow-y: auto; overflow-x: hidden; flex-grow: 1;">
                    <ul class="category-list p-0 m-0 list-unstyled" id="categoryList">
                    </ul>
                </div>
            </div>
        </div>

        <div class="col-lg-9">
            <div class="row g-4">
                <div class="col-12">
                    <div class="budget-panel p-3">
                        <div id="chartContainer" style="height: 400px; width: 100%;"></div>
                    </div>
                </div>
                <div class="col-12">
                    <div class="budget-panel p-3">
                        <ul class="nav nav-tabs mb-3" id="analysisTabs" role="tablist">
                            <li class="nav-item" role="presentation">
                                <button class="nav-link active" id="pie-tab" data-bs-toggle="tab"
                                        data-bs-target="#pieTabPane" type="button" role="tab">Distribution
                                </button>
                            </li>
                            <li class="nav-item" role="presentation">
                                <button class="nav-link" id="stats-tab" data-bs-toggle="tab"
                                        data-bs-target="#statsTabPane" type="button" role="tab">Statistics
                                </button>
                            </li>
                            <li class="nav-item" role="presentation">
                                <button class="nav-link" id="goals-tab" data-bs-toggle="tab"
                                        data-bs-target="#goalsTabPane" type="button" role="tab">Goals
                                </button>
                            </li>
                            <li class="nav-item" role="presentation">
                                <button class="nav-link" id="affordability-tab" data-bs-toggle="tab"
                                        data-bs-target="#affordabilityTabPane" type="button" role="tab">Affordability
                                </button>
                            </li>
                        </ul>

                        <div class="tab-content" id="analysisTabsContent">
                            <div class="tab-pane fade show active" id="pieTabPane" role="tabpanel">
                                <div id="pieContainer" style="height: 350px; width: 100%;"></div>
                            </div>

                            <div class="tab-pane fade" id="statsTabPane" role="tabpanel">
                                <div class="text-center p-5 text-muted">
                                    Click a column in the main chart to see detailed stats.
                                </div>
                            </div>

                            <div class="tab-pane fade p-4" id="goalsTabPane" role="tabpanel">
                                <div class="row">
                                    <div class="col-12">
                                        <h5 class="fw-bold mb-3" style="color: #2c3e50;">Surplus Allocation</h5>
                                        <div class="row g-3 mb-4">
                                            <div class="col-md-4">
                                                <label class="small fw-bold text-primary">Savings: <span
                                                        class="savingsDisp">50%</span></label>
                                                <input type="range" class="form-range goal-input savingsPct" value="50"
                                                       min="0" max="100">
                                            </div>
                                            <div class="col-md-4">
                                                <label class="small fw-bold text-info">Slush: <span class="slushDisp">30%</span></label>
                                                <input type="range" class="form-range goal-input slushPct" value="30"
                                                       min="0" max="100">
                                            </div>
                                            <div class="col-md-4">
                                                <label class="small fw-bold text-dark">Projects: <span class="bigDisp">20%</span></label>
                                                <input type="range" class="form-range goal-input bigPct" value="20"
                                                       min="0" max="100">
                                            </div>
                                        </div>

                                        <div class="table-responsive mt-4">
                                            <table class="table table-hover table-sm align-middle"
                                                   style="font-size: 0.85rem;">
                                                <thead class="table-light">
                                                <tr>
                                                    <th>Month</th>
                                                    <th class="text-end">Proj. Balance</th>
                                                    <th class="text-end text-success">Total Gain</th>
                                                    <th class="text-end text-primary">Savings</th>
                                                    <th class="text-end text-info">Slush</th>
                                                    <th class="text-end text-dark">Projects</th>
                                                </tr>
                                                </thead>
                                                <tbody id="forecastAllocationBody">
                                                </tbody>
                                            </table>
                                        </div>
                                        <button type="button" id="saveGoalsBtn"
                                                class="btn btn-sm btn-primary px-4 rounded-pill mt-3">
                                            Save Goal Percentages
                                        </button>
                                    </div>
                                </div>
                            </div>

                            <div class="tab-pane fade p-4" id="affordabilityTabPane" role="tabpanel">
                                <div class="bg-light p-3 rounded mb-4 border">
                                    <h6 class="small fw-bold text-uppercase text-muted mb-3">Adjust Allocation
                                        Priorities</h6>
                                    <div class="row g-3">
                                        <div class="col-md-4">
                                            <label class="small fw-bold text-primary">Savings: <span
                                                    class="savingsDisp">50%</span></label>
                                            <input type="range" class="form-range goal-input savingsPct" value="50"
                                                   min="0" max="100">
                                        </div>
                                        <div class="col-md-4">
                                            <label class="small fw-bold text-info">Slush: <span
                                                    class="slushDisp">30%</span></label>
                                            <input type="range" class="form-range goal-input slushPct" value="30"
                                                   min="0" max="100">
                                        </div>
                                        <div class="col-md-4">
                                            <label class="small fw-bold text-dark">Projects: <span
                                                    class="bigDisp">20%</span></label>
                                            <input type="range" class="form-range goal-input bigPct" value="20" min="0"
                                                   max="100">
                                        </div>
                                    </div>
                                </div>

                                <div class="alert alert-info border-0 shadow-sm d-flex align-items-center mb-4">
                                    <i class="bi bi-info-circle-fill fs-4 me-3"></i>
                                    <div id="affordabilityHeader" class="w-100">
                                    </div>
                                </div>

                                <div class="row row-cols-1 row-cols-md-2 row-cols-xl-4 g-4"
                                     id="budgetAffordabilityContainer">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    window.initialData = ${jsonBreakdown != null ? jsonBreakdown : '[]'};
</script>
<%@include file="/WEB-INF/Budget_App/budget_bottom.jsp"%>