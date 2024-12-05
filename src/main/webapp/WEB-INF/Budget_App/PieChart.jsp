<%@include file="/WEB-INF/Budget_App/budget_top.jsp"%>



<div class="jumbotron jumbotron-fluid">
    <div class="container">
        <h2 class="display-4 text-center">Welcome to Biscuit's Budgeting</h2>
        <p class="lead text-center">Let's make some money</p>
        <div class="row">
            <div class="col col-md-4">
                <!-- Pie CHart Year selector -->
                <div class ="row" id = "row1">
                    <label for="inputtransactionYear" class="form-label">Year</label>
                    <div class="input-group input-group-lg">
                        <select  class="<c:if test="${not empty results.transactionCategory_IDerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Year" id="inputtransactionYear" name="inputtransactionYear" >
                            <c:forEach var="year" begin="0" end="${yearRange}">
                                <option value=${2024-year}>${2024-year}   </option>
                            </c:forEach>
                        </select>
                        <c:if test="${not empty results.transactionCategory_IDerror}">
                            <div class="invalid-feedback">${results.transactionCategory_IDerror}</div>
                        </c:if>
                    </div>
                </div>
            </div>
            <div id="chartContainer" style="height: 370px; width: 100%;"></div>

            </div>
            <div class="col col-md-4">

            </div>
        </div>
    <!-- end row 1 -->
    <div class="row">
        <div class="col col-md-4">
            <!-- Bar CHart Category selector -->
            <div class ="row" id = "row2">
                <label for="inputCategoryID" class="form-label">Category</label>
                <div class="input-group input-group-lg">
                    <select  class="<c:if test="${not empty results.transactionCategory_IDerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Year" id="inputCategoryID" name="inputCategoryID" >
                        <c:forEach items="${Categories}" var="category">
                            <option value=${category.category_ID}>${category.category_ID}   </option>
                        </c:forEach>
                    </select>
                    <c:if test="${not empty results.transactionCategory_IDerror}">
                        <div class="invalid-feedback">${results.transactionCategory_IDerror}</div>
                    </c:if>
                </div>
            </div>
        </div>
        <div id="barContainer" style="height: 370px; width: 100%;"></div>

    </div>
    <div class="col col-md-4">

    </div>
</div>


    </div>
</div>


<%@include file="/WEB-INF/Budget_App/budget_bottom.jsp"%>