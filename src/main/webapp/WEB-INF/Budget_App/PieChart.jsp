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
                <label class="small fw-bold text-uppercase text-muted mb-2 d-block">View Level</label>
                <div class="btn-group w-100 mb-3">
                    <button id="btnSub" class="btn btn-outline-primary active" data-val="0">Sub</button>
                    <button id="btnSuper" class="btn btn-outline-primary" data-val="1">Super</button>
                </div>

                <label class="small fw-bold text-uppercase text-muted mb-2 d-block">Timeframe</label>
                <div class="btn-group w-100">
                    <button id="btnAnnual" class="btn btn-outline-primary active" data-val="0">Yearly</button>
                    <button id="btnMonthly" class="btn btn-outline-primary" data-val="1">Monthly</button>
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

            <div class="budget-panel shadow-sm">
                <div class="panel-header">Filters</div>
                <div class="panel-body p-0" style="max-height: 400px; overflow-y: auto;">
                    <ul class="category-list p-0 m-0" id="categoryList"></ul>
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
                        <div id="pieContainer" style="height: 350px; width: 100%;"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>window.initialData = ${jsonBreakdown != null ? jsonBreakdown : '[]'};</script>
<%@include file="/WEB-INF/Budget_App/budget_bottom.jsp"%>