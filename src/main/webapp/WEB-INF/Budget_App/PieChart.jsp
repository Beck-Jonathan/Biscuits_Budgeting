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
            <div class="mb-3">
                <label for="openingBalance" class="form-label small">Starting Cash ($)</label>
                <input type="number" class="form-control form-control-sm" id="openingBalance" value="30000">
            </div>
            <div id="forecastControlsContainer" class="budget-panel mb-3 p-3"
                 style="display:none; border-left: 4px solid #f39c12;">
                <label class="small fw-bold text-uppercase text-muted mb-2 d-block">Forecast Settings</label>

                <div class="mb-3">
                    <label class="small text-muted">History (Months Back)</label>
                    <input type="number" id="monthsBack" class="form-control form-control-sm border-0 bg-light"
                           value="24" min="6" max="60">
                    <div class="form-text" style="font-size: 0.6rem;">Min 6m for regression</div>
                </div>

                <div>
                    <label class="small text-muted">Projection (Months Forward)</label>
                    <input type="number" id="monthsForward" class="form-control form-control-sm border-0 bg-light"
                           value="12" min="1" max="36">
                </div>
                <label class="small fw-bold text-uppercase text-muted mt-3 mb-2 d-block">Group By</label>
                <div class="btn-group w-100">
                    <button class="btn btn-sm btn-outline-secondary active f-view" data-v="monthly">Month</button>
                    <button class="btn btn-sm btn-outline-secondary f-view" data-v="annual">Year</button>
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
                                        <button class="nav-link" id="history-tab" data-bs-toggle="tab"
                                                data-bs-target="#historyTabPane" type="button" role="tab">History
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
                                        <h5 class="text-muted">Budget Goals</h5>
                                        <p>Track your "Biscuits Budgeting" goals and progress bars here.</p>
                                    </div>

                                    <div class="tab-pane fade p-4" id="historyTabPane" role="tabpanel">
                                        <h5 class="text-muted">Transaction History</h5>
                                        <p>A quick-view list of the latest raw transactions for the selected period.</p>
                                    </div>
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
    // Pass the initial breakdown from the controller to the JS file
    window.initialData = ${jsonBreakdown != null ? jsonBreakdown : '[]'};
</script>
<%@include file="/WEB-INF/Budget_App/budget_bottom.jsp"%>