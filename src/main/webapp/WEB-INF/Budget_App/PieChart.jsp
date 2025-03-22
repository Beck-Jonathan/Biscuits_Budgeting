<%@include file="/WEB-INF/Budget_App/budget_top.jsp"%>



<div class="jumbotron jumbotron-fluid">
    <div class="container">
        <h2 class="display-4 text-center">Welcome to Biscuit's Budgeting</h2>
        <p class="lead text-center">Let's make some money</p>
        <div class="row" id="tablespace">

            <!--Start chart Container -->
            <div id="barContainer" class="col col-md-8">
            </div>
            <!--End chart Container -->

            <!--Start Chart Year selector -->
            <div class="col col-md-2">
                <select  class="<c:if test="${not empty results.transactionCategory_IDerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Year" id="inputtransactionYear" name="inputtransactionYear" >
                    <c:forEach items="${yearRange}" var="year">
                        <option value=${year}>${year}   </option>
                    </c:forEach>
                </select>
            </div>
            <!--End  Chart Year selector -->

            <!--Start Chart Category selector -->
            <div class="col col-md-2">
                <ul  class="<c:if test="${not empty results.transactionCategory_IDerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Year" id="inputCategoryID" name="inputCategoryID" >
                    <c:forEach items="${Categories}" var="category">
                        <li><input type="checkbox" name="category[]" value=${category.replace(" ","")}>${category}   </li>
                    </c:forEach>
                </ul>
            </div>


    </div>



    </div>
</div>
<p hidden id="FirstYear">${Breakdown[0][0].year}</p>
<p hidden >
    <table hidden>
    <th></th>
    <c:forEach items="${Breakdown}" var="year">
        <tr>
        <c:forEach items="${year}" var="category">
            <td> <p id="${category.year}${category.category_ID.replace(" ","")}year"> ${category.year}</p>  <p id="${category.year}${category.category_ID.replace(" ","")}amount">${category.amount}</p>  <p id="${category.year}${category.category_ID.replace(" ","")}count">${category.count}</p>  <p id="${category.year}${category.category_ID.replace(" ","")}name">${category.category_ID}</p> </td>
        </c:forEach>
        </tr>
    </c:forEach>
</table>
</p>


<%@include file="/WEB-INF/Budget_App/budget_bottom.jsp"%>