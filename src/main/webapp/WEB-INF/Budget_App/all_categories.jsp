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
                            <div class="col">
                                <div class="category-card-mini shadow-sm" data-id="${category.category_ID}">

                                    <div class="swatch-container">
                                        <div class="color-swatch-trigger" style="background-color: ${category.color_id};"></div>

                                        <div class="wheel-picker-container">
                                            <div class="wheel-canvas"></div>
                                            <button class="btn-save-color">Apply Color</button>
                                        </div>
                                    </div>

                                    <div class="card-body py-1 px-2 d-flex align-items-center">
                                        <h6 class="mb-0 fw-black category-text" contenteditable="true" spellcheck="false">
                                                ${fn:escapeXml(category.category_Name)}
                                        </h6>
                                    </div>

                                    <c:if test="${fn:toLowerCase(category.category_Name) != 'uncategorized' && fn:toLowerCase(category.category_Name) != 'savings'}">
                                        <a href="#modal${category.category_ID}" rel="modal:open" class="btn-delete-corner">
                                            &times;
                                        </a>
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

