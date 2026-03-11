<%--************
Create the JSP  For Viewing All of The  Category table
 Created By Jonathan Beck 7/31/2024
**********--%>
<%@include file="/WEB-INF/Budget_App/budget_top.jsp"%>
<div class = "container">

    <div class="row">
        <div class="col-12">
            <h1>All Budget Categories</h1>
            <p>There ${Categorys.size() eq 1 ? "is" : "are"}&nbsp;${Categories.size()} Category${Categories.size() ne 1 ? "s" : ""}</p>
            Add Category   <a href="addTransactionCategory">Add</a>
            <c:if test="${Categories.size() > 0}">
                <div class="row g-3">
                    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-4 g-2">
                        <c:forEach items="${Categories}" var="category">
                            <div class="col-xl-3 col-lg-4 col-md-6 mb-3">
                                <div class="category-pill shadow-sm border rounded-pill bg-white d-flex align-items-center p-1"
                                     data-id="${category.category_ID}"
                                     style="transition: all 0.2s ease; border-left: 5px solid ${category.color_id} !important;">

                                    <div class="swatch-container ms-1">
                                        <div class="color-swatch-trigger rounded-circle border-0"
                                             style="background-color: ${category.color_id}; width: 28px; height: 28px; cursor: pointer;">
                                        </div>
                                        <div class="wheel-picker-container shadow-lg border rounded p-3 bg-white">
                                            <div class="wheel-canvas"></div>
                                            <button class="btn btn-sm btn-dark btn-save-color w-100 mt-2">Save Color</button>
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
                                                style="font-size: 0.7rem; width: auto; max-width: 100px;"
                                                name="inputsub_categoryparent_category_id"
                                                onchange="updateParentCategory('${category.category_ID}', this.value)">
                                            <c:forEach items="${ParentCategories}" var="parent">
                                                <option value="${parent.parent_category_id}"
                                                        <c:if test="${parent.parent_category_id == category.parentCategoryId}">selected</c:if>>
                                                        ${fn:escapeXml(parent.super_category_name)}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>

                                    <c:if test="${fn:toLowerCase(category.category_Name) != 'uncategorized' && fn:toLowerCase(category.category_Name) != 'savings'}">
                                        <a href="#modal${category.category_ID}" rel="modal:open"
                                           class="btn-delete-pill text-muted me-2 text-decoration-none"
                                           style="font-size: 1.1rem; line-height: 1;">&times;</a>
                                    </c:if>
                                </div>
                            </div>
                        

                            <div id="modal${category.category_ID}" class="modal" style="display:none; max-width: 450px; border-radius: 12px; padding: 0; overflow: hidden;">
                                <div style="height: 10px; background-color: ${category.color_id};"></div>

                                <div class="p-4">
                                    <div class="text-center mb-3">
                                        <span style="color: ${category.color_id}; font-size: 2.5rem;">●</span>
                                        <h4 class="fw-bold mt-2">Delete Category?</h4>
                                    </div>

                                    <p class="text-center text-muted">
                                        You are about to delete <strong>${fn:escapeXml(category.category_Name)}</strong>.
                                        All associated transactions will be moved to the
                                        <span class="badge bg-light text-dark border">Uncategorized</span> pool.
                                    </p>

                                    <hr class="my-4">

                                    <div class="d-flex justify-content-center gap-3">
                                        <a href="#" rel="modal:close" class="btn btn-light border px-4">Keep it</a>

                                        <a href="deleteBudgetCategory?categoryid=${category.category_ID}"
                                           class="btn btn-danger px-4 shadow-sm">
                                            Confirm Delete
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
    </div>
            </c:if>
</div>
</main>
<%@include file="/WEB-INF/Budget_App/budget_bottom.jsp"%>

