<%@include file="/WEB-INF/Budget_App/budget_top.jsp"%>
<div class="container my-4">
    <div class="row">
        <div class="col-12">
            <h1 class="fw-bold">All Budget Categories</h1>
            <p class="text-muted">
                There <span id="category-verb">${Categories.size() eq 1 ? "is" : "are"}</span>&nbsp;
                <span id="category-count" class="fw-bold">${Categories.size()}</span>
                Category${Categories.size() ne 1 ? "s" : ""}
            </p>

            <hr class="mb-4">

            <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-xl-4 g-3" id="category-grid">


                <c:forEach items="${Categories}" var="category">
                    <div class="col mb-3">
                        <div class="category-pill shadow-sm border rounded-pill bg-white d-flex align-items-center p-1"
                             data-id="${category.category_ID}"
                             style="transition: all 0.2s ease; border-left: 5px solid ${category.color_id} !important;">

                            <div class="swatch-container ms-1">
                                <div class="color-swatch-trigger rounded-circle border-0"
                                     style="background-color: ${category.color_id}; width: 28px; height: 28px; cursor: pointer;">
                                </div>
                            </div>

                            <div class="flex-grow-1 px-2 overflow-hidden">
                                <div class="category-text fw-bold text-truncate"
                                     contenteditable="true"
                                     spellcheck="false"
                                     style="outline: none; font-size: 0.9rem; min-width: 50px;">
                                        ${fn:escapeXml(category.category_Name)}
                                </div>
                            </div>

                            <div class="pe-2">
                                <select class="form-select form-select-sm border-0 bg-light rounded-pill px-2 text-muted"
                                        style="font-size: 0.7rem; width: auto; max-width: 100px;">
                                    <c:forEach items="${ParentCategories}" var="parent">
                                        <option value="${parent.parent_category_id}"
                                                <c:if test="${parent.parent_category_id == category.parentCategoryId}">selected</c:if>>
                                                ${fn:escapeXml(parent.super_category_name)}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>

                            <c:if test="${fn:toLowerCase(category.category_Name) != 'uncategorized' && fn:toLowerCase(category.category_Name) != 'savings'}">
                                <a href="javascript:void(0)" class="text-muted me-2 text-decoration-none" style="font-size: 1.2rem;">&times;</a>
                            </c:if>
                        </div>
                    </div>
                </c:forEach>

                <div class="col mb-3" id="add-pill-wrapper">
                    <div class="category-pill shadow-sm border border-primary border-dashed rounded-pill bg-light d-flex align-items-center p-1"
                         style="border-style: dashed !important; border-width: 2px !important;">

                        <div class="ms-1">
                            <input type="color" id="new-category-color"
                                   class="form-control-color border-0 bg-transparent"
                                   value="#0d6efd"
                                   style="width: 28px; height: 28px; cursor: pointer; padding:0;">
                        </div>

                        <div class="flex-grow-1 px-2">
                            <input type="text" id="new-category-name"
                                   class="form-control form-control-sm border-0 bg-transparent fw-bold"
                                   placeholder="Add new..."
                                   style="box-shadow: none; font-size: 0.9rem;"
                                   onkeydown="if(event.key==='Enter') addNewCategory()">
                        </div>

                        <div class="pe-2">
                            <button onclick="addNewCategory()" class="btn btn-primary btn-sm rounded-circle d-flex align-items-center justify-content-center" style="width: 26px; height: 26px;">
                                <span>+</span>
                            </button>
                        </div>
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