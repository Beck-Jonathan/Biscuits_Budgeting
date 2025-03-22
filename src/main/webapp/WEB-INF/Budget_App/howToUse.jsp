<%--************
Create the JSP for the tutorial
 Created By Jonathan Beck 3/10/2025
**********--%>
<%@include file="/WEB-INF/Budget_App/budget_top.jsp"%>
<div class = "container">
    <!--start header A-->
    <div class = "row">
        <div class = "col-2">

        </div>
        <div class = "col-6">
        <div><h2>How To Make The Most Of Biscuit's</h2></div>
            <div><p> With these 4 simple steps you'll be able to take care of your finances in no time!</p></div>
        </div>
        <div class = "col-3">

        </div>

    </div>
    <!--end  header A-->
    <!--start header B-->
    <div class = "row">
        <div class = "col-4">

        </div>
        <div class = "col-8">

        </div>

    </div>
    <!-- end header B-->
    <!--start upload A-->
    <div class = "row">
        <div class = "col-3">

        </div>
        <div class = "col-6">
            <h2>1)Upload your transactions</h2>
        </div>
        <div class = "col-3">

        </div>

    </div>
    <!--end  upload A-->
    <!--start upload B-->
    <div class = "row">
        <div class = "col-4">
            <p>Upload a csv containing your transactions from your bank account,
                and our software will parse your transactions and store them in our database</p>
        </div>
        <div class = "col-8">
            <a href="${pageContext.request.contextPath}/images/upload.png"> <img  width="636"  src="${pageContext.request.contextPath}/images/upload.png"> </a>
        </div>

    </div>
    <!-- end upload B-->
    <!--start categories A-->
    <div class = "row">
        <div class = "col-3">

        </div>
        <div class = "col-6">
            <h2>2)Manage Your Categories</h2>
        </div>
        <div class = "col-3">

        </div>

    </div>
    <!--end  categories A-->
    <!--start categories B-->
    <div class = "row">
        <div class = "col-6">
            <a href="${pageContext.request.contextPath}/images/categories.png">  <img  width="636"  src="${pageContext.request.contextPath}/images/categories.png"> </a>
        </div>
        <div class = "col-4">
            <p>Create categories that fit your life and goals.</p>
        </div>


    </div>
    <!-- end categories B-->
    <!--start categorize A-->
    <div class = "row">
        <div class = "col-3">

        </div>
        <div class = "col-6">
                <h2>3)Categorize Your Transactions</h2>
        </div>
        <div class = "col-3">

        </div>

    </div>
    <!--end  categorize A-->
    <!--start categorize B-->
    <div class = "row">
        <div class = "col-4">
            Assign each transaction to it's proper category.
        </div>
        <div class = "col-8">
            <a href="${pageContext.request.contextPath}/images/categorize.png">   <img  width="636"  src="${pageContext.request.contextPath}/images/categorize.png"> </a>
        </div>

    </div>
    <!-- end categorize B-->
    <!--start breakdown A-->
    <div class = "row">
        <div class = "col-3">

        </div>
        <div class = "col-6">
            <h2>4)View your spending breakdown</h2>
        </div>
        <div class = "col-3">

        </div>

    </div>
    <!--end  breakdown A-->
    <!--start breakdown B-->
    <div class = "row">
        <div class = "col-6">
            <a href="${pageContext.request.contextPath}/images/breakdown.png">  <img width="636" class="object-fit-scale border rounded" src="${pageContext.request.contextPath}/images/breakdown.png"> </a>
        </div>
        <div class = "col-4">
               <p>Our powerful tool will allow you to quickly visualize and understand your spending habits and trends!</p>

        </div>


    </div>
    <!-- end breakdown B-->
</div>
<%@include file="/WEB-INF/Budget_App/budget_bottom.jsp"%>