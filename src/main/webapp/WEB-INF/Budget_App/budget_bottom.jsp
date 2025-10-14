</div> <!-- end row-->
<div class = "col-md-1 ps-0 " id="right" alt="Right Hand Border" ></div>

</div> <!-- end row-->




<footer>
    <div class="row">
        <div class="hidden-sm col-md-2" id="bottomleftblack" ></div>
        <div class="col-md-2 col-sm-2" id="howtoplay"></div>
        <div class="col-md-4 col-sm-8" id="footerimage"></div>
        <div class="col-md-2 col-sm-2" id="privacypolicy"><p><a href="howtouse"> How To Use</a></p></div>
        <div class="hidden-sm col-md-2  " id="bottomrightblack"></div>

    </div>


</footer>





<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script> 
<script src="js/jquery.validate.js"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.13.2/jquery-ui.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
<script type="text/javascript" src="https://cdn.canvasjs.com/jquery.canvasjs.min.js"></script>

<script src="js/budget/site.js"></script>
<script type="module" src="js/budget/tvmCalculator.js"></script>

<c:if test="${pageTitle eq 'Mortgage'}">
    <script src="js/budget/mortgage.js"></script>
</c:if>

<c:if test="${pageTitle eq 'Pie Chart'}">
    <script src="js/budget/pieChartMonth.js"></script>
</c:if>

<%-- <c:if test="${pageTitle eq 'Pie Chart Month'}">
    <script src="js/budget/pieChartMonth.js"></script>
</c:if>
--%>
<c:if test="${pageTitle eq 'All Transactions'}">
    <script src="js/budget/categorize.js"></script>
</c:if>
<c:if test="${pageTitle eq 'Search Transactions'}">
    <script src="js/budget/categorize.js"></script>
</c:if>
<c:if test="${pageTitle eq 'Add Receipt'|| pageTitle eq 'Edit Receipt'}">
    <script src="js/budget/addReceipt.js"></script>
</c:if>

<c:if test="${pageTitle eq 'Add Suggestion'}">
    <script src="js/budget/addSuggestion.js"></script>
</c:if>

<c:if test="${pageTitle eq 'All Suggestions'}">
    <script src="js/budget/allSuggestion.js"></script>
</c:if>

<c:if test="${pageTitle eq 'Add Mortgage'|| pageTitle eq 'Edit Mortgage'}">
    <script src="js/budget/addMortgage.js"></script>
</c:if>
<c:if test="${pageTitle eq 'All Mortgages'}">
    <script src="js/budget/allMortgage.js"></script>
</c:if>

<c:if test="${pageTitle eq 'Edit Transaction'}">
    <script src="js/budget/editTransaction.js"></script>
    <script src="js/budget/allReceipt.js"></script>
</c:if>
<c:if test="${pageTitle eq 'All Receipts'}">
    <script src="js/budget/allReceipt.js"></script>
</c:if>









</body>


</html>

