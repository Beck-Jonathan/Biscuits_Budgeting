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
                <div class="table-responsive"><table class="table table-bordered">
                    <thead>
                    <tr>
                        <th scope="col">Category_ID</th>

                        <th scope="col">Edit</th>

                        <th scope="col">Delete</th>

                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${Categories}" var="category">
                        <tr>
                            <td><a href = "editCategory?categoryid=${category.category_ID}&mode=view">${fn:escapeXml(category.category_ID)}</a></td>

                            <td><a href = "editCategory?categoryid=${category.category_ID}&mode=edit" > Edit </a></td>


                            <td><p><a href="#modal${category.category_ID}" rel="modal:open">Delete</a></p></td>


                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                </div>
                <c:forEach items="${Categories}" var="category">
                    <div id="modal${category.category_ID}" class="modal">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <div class="modal-header">
                                    <h1 class="modal-title fs-5" id="Cateory_Label">${category.category_ID}"</h1>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <p>You are about to delete the category ${category.category_ID}. Any transactions
                                        that you have assigned to this category will have their category changed to "Uncategorized". This can
                                        not be undone.</p>
                                </div>
                                <div class="modal-footer">

                                    <a href = "deleteBudgetCategory?categoryid=${category.category_ID}" > Delete </a>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                </c:forEach>
            </c:if>
        </div>
    </div>
</div>
</main>
<%@include file="/WEB-INF/Budget_App/budget_bottom.jsp"%>

