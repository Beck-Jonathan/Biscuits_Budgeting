<%@include file="/WEB-INF/Budget_App/budget_top.jsp"%>
<div class="container my-4">
    <div class="row">
        <div class="col-12 text-center mb-4">
            <h1 class="fw-bold" style="color: #2c3e50;">Category Manager</h1>
            <p class="text-muted">Total Categories: <span class="fw-bold">${Categories.size()}</span></p>
        </div>

        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-xl-4 g-4" id="category-grid">
            <c:forEach items="${Categories}" var="category">
                <c:set var="glowClass" value="" />
                <c:forEach items="${ParentCategories}" var="p">
                    <c:if test="${p.parent_category_id == category.parentCategoryId}">
                        <c:choose>
                            <c:when test="${fn:toLowerCase(p.transaction_type) == 'income'}"><c:set var="glowClass" value="glow-income" /></c:when>
                            <c:when test="${fn:toLowerCase(p.transaction_type) == 'investment'}"><c:set var="glowClass" value="glow-investment" /></c:when>
                            <c:when test="${fn:toLowerCase(p.transaction_type) == 'expense'}"><c:set var="glowClass" value="glow-expense" /></c:when>
                        </c:choose>
                    </c:if>
                </c:forEach>

                <div class="col">
                    <div class="category-pill rounded-pill bg-white d-flex align-items-center p-1 ${glowClass}"
                         data-id="${category.category_ID}"
                         style="border-left: 6px solid ${category.color_id} !important;">

                        <div class="swatch-container ms-1">
                            <div class="color-swatch-trigger rounded-circle"
                                 style="background-color: ${category.color_id}; width: 28px; height: 28px; cursor: pointer;">
                            </div>
                        </div>

                        <div class="flex-grow-1 px-2 overflow-hidden">
                            <div class="category-text fw-bold text-truncate" contenteditable="true" spellcheck="false"
                                 style="outline: none; font-size: 0.9rem;">
                                    ${fn:escapeXml(category.category_Name)}
                            </div>
                        </div>

                        <div class="pe-2">
                            <select class="form-select form-select-sm border-0 bg-light rounded-pill px-2 text-muted"
                                    style="font-size: 0.7rem; width: auto;" onchange="updateParentCategory('${category.category_ID}', this.value)">
                                <c:forEach items="${ParentCategories}" var="parent">
                                    <option value="${parent.parent_category_id}"
                                            <c:if test="${parent.parent_category_id == category.parentCategoryId}">selected</c:if>>
                                            ${fn:escapeXml(parent.super_category_name)}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
            </c:forEach>

            <div class="col" id="add-pill-wrapper">
                <div id="new-pill-container" class="category-pill border-dashed rounded-pill bg-light d-flex align-items-center p-1"
                     style="border-left: 6px solid #0d6efd !important;">

                    <div class="ms-1">
                        <label for="new-category-color" id="new-color-preview" class="rounded-circle border d-block"
                               style="background-color: #0d6efd; width: 28px; height: 28px; cursor: pointer; margin: 0;"></label>
                        <input type="color" id="new-category-color" class="d-none" value="#0d6efd" oninput="updateAddPillColor(this.value)">
                    </div>

                    <div class="flex-grow-1 px-2">
                        <input type="text" id="new-category-name" class="form-control form-control-sm border-0 bg-transparent fw-bold"
                               placeholder="New Cat..." style="box-shadow: none;" onkeydown="if(event.key==='Enter') addNewCategory()">
                    </div>

                    <div class="pe-2 d-flex align-items-center gap-2">
                        <select id="new-category-parent" class="form-select form-select-sm border-0 bg-white rounded-pill px-1 text-muted"
                                style="font-size: 0.65rem; width: 80px;" onchange="updateAddPillIndicator(this)">
                            <c:forEach items="${ParentCategories}" var="parent">
                                <option value="${parent.parent_category_id}" data-type="${fn:toLowerCase(parent.transaction_type)}">
                                        ${fn:escapeXml(parent.super_category_name)}
                                </option>
                            </c:forEach>
                        </select>
                        <button onclick="addNewCategory()" class="btn btn-primary btn-sm rounded-circle" style="width: 26px; height: 26px; padding: 0;">+</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<style>
    .border-dashed { border-style: dashed !important; }
    .new-pill-animation { animation: highlight 1.5s ease; }
    @keyframes highlight {
        0% { background-color: #e7f1ff; transform: scale(1.05); }
        100% { background-color: white; transform: scale(1); }
    }
</style>

<script>
    // This lives in the JSP so the server can "fill in" the data
    const parentCategories = [
        <c:forEach items="${ParentCategories}" var="parent" varStatus="status">
        {
            "id": "${parent.parent_category_id}",
            "name": "${fn:escapeXml(parent.super_category_name)}"
        }${!status.last ? ',' : ''}
        </c:forEach>
    ];
</script>

<%@include file="/WEB-INF/Budget_App/budget_bottom.jsp"%>