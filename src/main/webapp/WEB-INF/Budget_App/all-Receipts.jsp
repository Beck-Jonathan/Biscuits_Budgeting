<%--************
Create the JSP  For Viewing All of The  Receipt table
 Created By Jonathan Beck 10/8/2025
**********--%>
<%@include file="/WEB-INF/Budget_App/budget_top.jsp"%>
<div class = "container">
    <div class="row">
        <div class="col-12">
            <h1>All budget_app Receipts</h1>
            <p>There ${Receipts.size() eq 1 ? "is" : "are"}&nbsp;${Receipts.size()} Receipt${Receipts.size() ne 1 ? "s" : ""}</p>
            Add Receipt   <a href="addReceipt">Add</a>
            <c:if test="${Receipts.size() > 0}">
                <div class="search-container">
                    <form action="all-Receipts">
                        <input type="text" placeholder="Search.." id="searchBox" name="search">

                        <button type="submit"><i class="fa fa-search"></i></button>
                    </form>
                </div>

                <div class="table-responsive">

                    <c:forEach items="${Receipts}" var="receipt">
                        <span id="${receipt.receipt_ID}_card" class="card receiptCard">
     <a href="${receipt.image_Link}" >
         <img <c:if test="${fn:contains(receipt.image_Link, 'drive')}">crossorigin="anonymous" </c:if>

              referrerPolicy="no-referrer"
              src="${receipt.image_Link}" alt="Avatar"> </a>

    <p>${fn:escapeXml(receipt.name)}</p>
        <div class="row">
            <div class="col-2">
                    <a  class="delButton" href="${receipt.receipt_ID}">❌</a>
            </div>
            <div class="col-5"></div>
            <div class="col-2 ">
                    <a href = "editReceipt?receiptid=${receipt.receipt_ID}&mode=edit"> ⚙️ </a>
            </div>
            <div class="col-2"><a href = "editReceipt?receiptid=${receipt.receipt_ID}&mode=view"> 🔎 </a></div>
            <div class="col-1"></div>
        </div>
    </span>
                    </c:forEach>

                </div>
            </c:if>
        </div>
    </div>
</div>
<%--For displaying Previous link except for the 1st page --%>
<c:if test="${currentPage != 1}">
    <form action="all-Receipts" method="get">
        <input type="hidden" name="transaction_id" value="${transaction_id}">
        <input type="hidden" name="page" value="${currentPage-1}">
        <br/><br/>
        <input type="submit" value="Previous Page" />
    </form>
</c:if>
<%--For displaying Page numbers.
The when condition does not display a link for the current page--%><form action="all-Receipts" method="get" ><input type="hidden" name="transaction_id" value="${transaction_id}">
    Select a page :
    <select name="page" onchange="this.form.submit()">
        <c:forEach var="i" begin="1" end="${noOfPages}">
            <option value=${i}  ${currentPage == i ? ' selected' : ''} >${i}</option>
        </c:forEach>
    </select>
    <br/><br/>
    <input type="submit" value="Submit" />
</form>
<%--For displaying Next link --%>
<c:if test="${currentPage lt noOfPages}">
    <form action="all-Transactions" method="get">
        <input type="hidden" name="transaction_id" value="${transaction_id}">
        <input type="hidden" name="page" value="${currentPage+1}">
        <br/><br/>
        <input type="submit" value="Next Page" />
    </form>
</c:if>
<div id="dialog" title="Confirmation Required">
    Are you sure about this?
</div>
</main>
<%@include file="/WEB-INF/Budget_App/budget_bottom.jsp"%>

