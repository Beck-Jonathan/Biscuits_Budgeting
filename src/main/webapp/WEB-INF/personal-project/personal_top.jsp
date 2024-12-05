
<!DOCTYPE html>
<html lang="en">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">



    <title>${pageTitle}</title>
    <link href="css/jquery-ui.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <link href="css/site.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/nprogress@0.2.0/nprogress.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${appURL}/css/loading.css">




<body onload="">


<header id="rollerHeader" onload="formatHeader">
    <div class="row ">
        <div class="col-md-1 " id="topleftblack" ></div>
        <div class="col-md-1 " id="Headerr1c1" >


        </div>
        <div class="col-sm-2 " id="Headerr1c2" >
            <a href="home"> <button type="button" value="Home" id="Home" ></button> </a>
        </div>

        <div class="col-sm-2 " id="Headerr1c3" >
            <c:if test="${empty User}">
            <a href="joinus">   <button type="button" value="SignUp" id="SignUp" ></button></a>
            </c:if>
            <c:if test="${not empty User}">
            <c:if test="${User.privilege_ID eq 1}">
                <a href="user-dash">   <button type="button" value="User Dashboard" id="user-dash" ></button></a>
            </c:if>
            <c:if test="${User.privilege_ID eq 2}">
                <a href="team-admin-dash">   <button type="button" value="Team Admin Dashboard" id="team-admin-dash" ></button></a>
            </c:if>
            <c:if test="${User.privilege_ID eq 3}">
                <a href="league-admin-dash">   <button type="button" value="League Admin Dashboard" id="league-admin-dash" ></button></a>
            </c:if>
            </c:if>
        </div>


        <div class="col-sm-2 " id="Headerr1c5" >


        </div>
        <div class="col-sm-2 " id="Headerr1c6" >
        <%@include file="/WEB-INF/personal-project/Login.jsp"%>

        </div>

        <div class="col-sm-2 .d-none.d-xxl-block" id="toprightblack"></div>

    </div>
    <div class="row">

    </div>


</header>
<main>
<div class ="row">

    <div class = "hidden-sm col-md-2  pe-0 "  id="left"><img src="images/Roller/banner.png" alt="Left Image"></div>
    <div class = "col-md-8 pe-0 ps-0" id="mainForm">
        <input type="hidden" id="currentpage" name="currentpage" value=${requestScope['javax.servlet.forward.request_uri']}/>