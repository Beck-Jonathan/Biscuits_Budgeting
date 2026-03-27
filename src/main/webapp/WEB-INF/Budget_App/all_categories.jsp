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
                        <h5 class="fw-bold m-0"><i class="bi bi-cpu me-2"></i>Projection Engine Logic</h5>

                        <span class="badge rounded-pill bg-dark-subtle text-dark px-3">Active Models</span>
                        <button id="btnAutoAssign" class="btn btn-primary btn-sm shadow-sm d-flex align-items-center">
                            <i class="bi bi-magic me-2"></i> Smart Assign Models
                        </button>
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


        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-xl-4 g-4" id="category-grid"
             style="overflow: visible;">
            <c:forEach items="${Categories}" var="category">
                <c:set var="typeClass" value="" />
                <c:forEach items="${ParentCategories}" var="p">
                    <c:if test="${p.parent_category_id == category.parentCategoryId}">
                        <c:choose>
                            <c:when test="${fn:toLowerCase(p.transaction_type) == 'income'}"><c:set var="typeClass"
                                                                                                    value="border-income"/></c:when>
                            <c:when test="${fn:toLowerCase(p.transaction_type) == 'investment'}"><c:set var="typeClass"
                                                                                                        value="border-investment"/></c:when>
                            <c:when test="${fn:toLowerCase(p.transaction_type) == 'expense'}"><c:set var="typeClass"
                                                                                                     value="border-expense"/></c:when>
                            <c:when test="${fn:toLowerCase(p.transaction_type) == 'transfer'}"><c:set var="typeClass"
                                                                                                      value="border-transfer"/></c:when>
                        </c:choose>
                    </c:if>
                </c:forEach>

                <div class="col">
                    <div class="modern-cat-card ${typeClass}"
                         data-id="${category.category_ID}"
                         data-strategy="${category.projection_strategy_ID}">

                        <div class="card-accent" style="background-color: ${category.color_id};">
                            <div class="swatch-container">
                                <div class="color-swatch-trigger" style="background-color: ${category.color_id};"></div>
                                <div class="picker-popover d-none shadow-lg">
                                    <div class="wheel-canvas"></div>
                                </div>
                            </div>
                        </div>

                        <div class="card-body-content">
                            <i class="bi bi-x delete-icon-modern"
                               onclick="confirmDeleteCategory('${category.category_ID}', '${fn:escapeXml(category.category_Name)}')"></i>

                            <div class="category-name-row">
                                <span class="category-text"
                                      contenteditable="true">${fn:escapeXml(category.category_Name)}</span>
                            </div>

                            <div class="strategy-row">
                                <i class="bi bi-graph-up-arrow strategy-icon" title="Regression"
                                   data-val="REGRESSION"></i>
                                <i class="bi bi-lightning-fill strategy-icon" title="Alpha Spike"
                                   data-val="ALPHA_SPIKE"></i>
                                <i class="bi bi-pause-fill strategy-icon" title="Last Value" data-val="LVCF"></i>
                                <i class="bi bi-list-stars strategy-icon" title="Strict Average"
                                   data-val="AVG_STRICT"></i>
                                <i class="bi bi-percent strategy-icon" title="Inflation Only"
                                   data-val="INFLATION_ONLY"></i>
                                <i class="bi bi-x-circle strategy-icon" title="Zero Out" data-val="ZERO_SUM"></i>
                            </div>

                            <div class="parent-row">
                                <select class="modern-select"
                                        onchange="updateParentCategory('${category.category_ID}', this.value)">
                                    <c:forEach items="${ParentCategories}" var="parent">
                                        <option value="${parent.parent_category_id}"
                                                data-type="${fn:toLowerCase(parent.transaction_type)}"
                                                <c:if test="${parent.parent_category_id == category.parentCategoryId}">selected</c:if>>
                                                ${fn:escapeXml(parent.super_category_name)}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>

            <div class="col" id="add-pill-wrapper">
                <div id="new-pill-container" class="modern-cat-card border-dashed">

                    <div class="card-accent" id="new-card-accent" style="background-color: #0d6efd;">
                        <div class="swatch-container">
                            <label for="new-category-color" id="new-color-preview" class="color-swatch-trigger"
                                   style="background-color: #0d6efd; display: block; border: 2px solid #fff;"></label>
                            <input type="color" id="new-category-color" class="d-none" value="#0d6efd"
                                   oninput="updateAddPillColor(this.value)">
                        </div>
                    </div>

                    <div class="card-body-content">
                        <div class="category-name-row">
                            <input type="text" id="new-category-name"
                                   class="form-control form-control-sm border-0 bg-transparent fw-bold p-0"
                                   placeholder="New Category Name..."
                                   style="box-shadow: none; font-size: 0.9rem;"
                                   onkeydown="if(event.key==='Enter') addNewCategory()">
                        </div>

                        <div class="strategy-row">
                <span class="text-muted" style="font-size: 0.65rem; text-transform: uppercase; letter-spacing: 0.5px;">
                    Model: AVG_STRICT
                </span>
                        </div>

                        <div class="parent-row d-flex align-items-center gap-2">
                            <select id="new-category-parent" class="modern-select"
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
                                    style="width: 24px; height: 24px; padding: 0; display: flex; align-items: center; justify-content: center;">
                                <i class="bi bi-plus"></i>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
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
                    Existing manual strategy assignments will be overwritten.
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



<%@include file="/WEB-INF/Budget_App/budget_bottom.jsp"%>