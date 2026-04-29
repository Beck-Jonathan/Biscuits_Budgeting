<div id="allSuperCategories">
    <c:forEach items="${ParentCategories}" var="parent">
        <c:set var="groupColor" value="${parent.color_id}"/>
        <%-- Logical Flags --%>
        <c:set var="isExpense" value="${fn:toLowerCase(parent.transaction_type) == 'expense'}"/>
        <c:set var="isOverTarget" value="${parent.percentUsed > 100}"/>

        <div class="super-category-wrapper mb-4">
            <div class="super-category-header p-3 d-flex align-items-center justify-content-between"
                 style="background: #f8f9fa; border-left: 5px solid ${groupColor}; border-bottom: 1px solid #dee2e6; cursor: pointer;"
                 data-bs-toggle="collapse"
                 data-bs-target="#collapse-${parent.parent_category_id}">

                <div class="d-flex align-items-center">
                    <i class="bi bi-chevron-down me-3 toggle-arrow transition-icon"></i>
                    <div>
                        <h5 class="mb-0 fw-bold text-uppercase" style="letter-spacing: 1px; color: #495057;">
                                ${fn:escapeXml(parent.super_category_name)}
                        </h5>
                        <span class="badge text-dark"
                              style="background-color: ${groupColor}33; border: 1px solid ${groupColor}">
                                ${parent.transaction_type}
                        </span>
                    </div>
                </div>

                <div class="analysis-snippet text-end" style="min-width: 200px;">
                    <div class="small text-muted mb-1">
                        <strong>${parent.subcategoryCount}</strong> Categories |
                        Target: <strong><fmt:formatNumber value="${parent.totalMonthlyTarget}"
                                                          type="currency"/></strong>
                    </div>

                    <div class="d-flex align-items-center justify-content-end">
                        <div class="me-2 small fw-bold">
                            <fmt:formatNumber value="${parent.currentMonthSpent}" type="currency"/>
                        </div>
                        <div class="progress" style="height: 8px; width: 100px; background-color: #e9ecef;">
                                <%-- Logic: If over target, use Red for Expenses, Green for Income/Investments. Otherwise use groupColor. --%>
                            <c:set var="barDisplayColor" value="${groupColor}"/>
                            <c:if test="${isOverTarget}">
                                <c:set var="barDisplayColor" value="${isExpense ? '#dc3545' : '#198754'}"/>
                            </c:if>

                            <div class="progress-bar" role="progressbar"
                                 style="width: ${parent.percentUsed > 100 ? 100 : parent.percentUsed}%; background-color: ${barDisplayColor};"
                                 aria-valuenow="${parent.percentUsed}" aria-valuemin="0" aria-valuemax="100">
                            </div>
                        </div>
                    </div>

                        <%-- Status Message Logic --%>
                    <c:choose>
                        <c:when test="${parent.remainingBudget < 0 && isExpense}">
                            <div class="text-danger x-small fw-bold mt-1" style="font-size: 0.7rem;">
                                <i class="bi bi-exclamation-triangle-fill me-1"></i>
                                EXCEEDED BY <fmt:formatNumber value="${fn:replace(parent.remainingBudget, '-', '')}"
                                                              type="currency"/>
                            </div>
                        </c:when>
                        <c:when test="${parent.remainingBudget < 0 && !isExpense}">
                            <div class="text-success x-small fw-bold mt-1" style="font-size: 0.7rem;">
                                <i class="bi bi-arrow-up-circle-fill me-1"></i>
                                SURPLUS OF <fmt:formatNumber value="${fn:replace(parent.remainingBudget, '-', '')}"
                                                             type="currency"/>
                            </div>
                        </c:when>
                    </c:choose>
                </div>
            </div>
        </div>

        <div class="collapse show" id="collapse-${parent.parent_category_id}">
            <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-xl-4 g-4 p-4 border-start border-top-0 ms-2"
                 id="category-grid-${parent.parent_category_id}"
                 style="border-left: 3px solid ${groupColor}55 !important; background-color: rgba(0,0,0,0.01);">

                <c:forEach items="${Categories}" var="category">
                    <c:set var="isExpense" value="${fn:toLowerCase(parent.transaction_type) == 'expense'}"/>
                    <c:set var="isOverBudget" value="${category.amount > category.target_Threshold}"/>
                    <c:if test="${category.parentCategoryId == parent.parent_category_id}">

                        <c:set var="typeClass" value=""/>
                        <c:choose>
                            <c:when test="${fn:toLowerCase(parent.transaction_type) == 'income'}"><c:set
                                    var="typeClass" value="border-income"/></c:when>
                            <c:when test="${fn:toLowerCase(parent.transaction_type) == 'investment'}"><c:set
                                    var="typeClass" value="border-investment"/></c:when>
                            <c:when test="${fn:toLowerCase(parent.transaction_type) == 'expense'}"><c:set
                                    var="typeClass" value="border-expense"/></c:when>
                            <c:when test="${fn:toLowerCase(parent.transaction_type) == 'transfer'}"><c:set
                                    var="typeClass" value="border-transfer"/></c:when>
                        </c:choose>

                        <div class="col">
                            <div class="modern-cat-card ${typeClass}"
                                 data-id="${category.category_ID}"
                                 id="${category.category_ID}_card"
                                 data-strategy="${category.projection_strategy_ID}">

                                <div class="card-accent" style="background-color: ${category.color_id};">
                                    <div class="swatch-container">
                                        <div class="color-swatch-trigger"
                                             style="background-color: ${category.color_id};"></div>
                                        <div class="picker-popover d-none shadow-lg">
                                            <div class="wheel-canvas"></div>
                                        </div>
                                    </div>
                                </div>

                                <div class="card-body-content">
                                    <div class="card-actions-modern">


                                        <i class="bi bi-search action-icon-modern search-trigger"
                                           title="View Transactions"
                                           onclick="handleMagnifyClick(event, '${category.category_ID}')"></i>

                                        <i id="${category.category_ID}_lockIcon"
                                           class="bi ${category.is_Locked ? 'bi-lock-fill text-warning' : 'bi-unlock'} action-icon-modern lock-trigger bottomLock"
                                           title="Toggle Projection Lock"
                                           onclick="handleLockToggle(event, '${category.category_ID}')"></i>

                                        <i class="bi bi-gear action-icon-modern gear-trigger"
                                           title="Advanced Settings"
                                           onclick="handleGearClick(event, '${category.category_ID}')"></i>

                                        <i class="bi bi-x action-icon-modern delete-trigger"
                                           onclick="confirmDeleteCategory('${category.category_ID}', '${fn:escapeXml(category.category_Name)}')"></i>
                                    </div>
                                    <div class="category-name-row">
                                    <span class="category-text"
                                          contenteditable="true">${fn:escapeXml(category.category_Name)}</span>
                                    </div>
                                    <div class="subcategory-mini-analysis mb-2 mt-1">
                                        <div class="d-flex justify-content-between align-items-center mb-1"
                                             style="font-size: 0.75rem;">
                                                <%-- Text Color Logic: Red for over-expenses, Green for over-income --%>
                                            <span class="fw-bold
            <c:choose>
                <c:when test="${isOverBudget && isExpense}">text-danger</c:when>
                <c:when test="${isOverBudget && !isExpense}">text-success</c:when>
                <c:otherwise>text-dark</c:otherwise>
            </c:choose>">
            <fmt:formatNumber value="${category.amount}" type="currency"/>
        </span>
                                            <span class="text-muted">
                                of <span class="target-text-label"><fmt:formatNumber
                                                    value="${category.target_Threshold}" type="currency"/></span>
                                    </span>
                                        </div>

                                        <div class="progress" style="height: 4px; background-color: #e9ecef;">
                                            <c:set var="percentUsed"
                                                   value="${(category.target_Threshold > 0) ? (category.amount / category.target_Threshold) * 100 : 0}"/>

                                                <%-- Bar Color Logic --%>
                                            <c:set var="barColor" value="${category.color_id}"/>
                                            <c:if test="${isOverBudget}">
                                                <c:set var="barColor" value="${isExpense ? '#dc3545' : '#198754'}"/>
                                            </c:if>

                                            <div class="progress-bar progress-bar-fill" role="progressbar"
                                                 data-amount="${category.amount}" <%-- Store actual spent for JS math --%>
                                                 style="width: ${percentUsed > 100 ? 100 : percentUsed}%;
                                                         background-color: ${barColor};"
                                                 aria-valuenow="${percentUsed}" aria-valuemin="0"
                                                 aria-valuemax="100">
                                            </div>
                                        </div>
                                    </div>


                                    <div class="strategy-row">

                                        <i class="bi bi-graph-up-arrow strategy-icon ${category.is_Locked ? ' disabled ' :'' }"
                                           title="Regression"
                                           data-val="REGRESSION" ${category.is_Locked ? 'disabled' :'' }></i>
                                        <i class="bi bi-lightning-fill strategy-icon ${category.is_Locked ? ' disabled ' :'' }"
                                           title="Alpha Spike"
                                           data-val="ALPHA_SPIKE"></i>
                                        <i class="bi bi-pause-fill strategy-icon ${category.is_Locked ? ' disabled ' :'' }"
                                           title="Last Value"
                                           data-val="LVCF"></i>
                                        <i class="bi bi-list-stars strategy-icon ${category.is_Locked ? ' disabled ' :'' }"
                                           title="Strict Average"
                                           data-val="AVG_STRICT"></i>
                                        <i class="bi bi-percent strategy-icon${category.is_Locked ? ' disabled ' :'' }"
                                           title="Inflation Only"
                                           data-val="INFLATION_ONLY"></i>
                                        <i class="bi bi-x-circle strategy-icon ${category.is_Locked ? ' disabled ' :'' }"
                                           title="Zero Out"
                                           data-val="ZERO_SUM"></i>
                                    </div>

                                    <div class="parent-row">
                                        <select class="modern-select" ${category.is_Locked ? 'disabled' :'' }
                                                onchange="updateParentCategory('${category.category_ID}', this.value)">
                                            <c:forEach items="${ParentCategories}" var="pOption">
                                                <option value="${pOption.parent_category_id}"
                                                        data-type="${fn:toLowerCase(pOption.transaction_type)}"
                                                        <c:if test="${pOption.parent_category_id == category.parentCategoryId}">selected</c:if>>
                                                        ${fn:escapeXml(pOption.super_category_name)}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:if>
                </c:forEach>
            </div>
        </div>
    </c:forEach>
</div>