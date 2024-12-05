
<!DOCTYPE html>
<html lang="en">
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">



  <title>${pageTitle}</title>
  <link href="css/jquery-ui.css" rel="stylesheet">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
        integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
  <link href="css/site.css" rel="stylesheet">




<body onload="">


<header id="rollerHeader" onload="formatHeader">
  <div class="row ">
    <div class="col-sm-1 .d-sm-none .d-xxl-block" id="topleftblack" ></div>
    <div class="col-sm-1 .d-sm-none .d-xxl-block" id="Headerr1c1" >


    </div>
    <div class="col-sm-2 .d-sm-none .d-xxl-block" id="Headerr1c2" >
      <a href="home"> <button type="button" value="Home" id="Home" ></button> </a>
    </div>

    <div class="col-sm-2 .d-sm-none .d-xxl-block" id="Headerr1c3" >
      <c:if test="${empty User}">
        <a href="joinus">   <button type="button" value="SignUp" id="SignUp" ></button></a>
      </c:if>
    </div>

    <div class="col-sm-2 .d-sm-none .d-xxl-block" id="Headerr1c4" >
      <button type="button" value="ManageDB" id="ManageDB" ></button>

    </div>
    <div class="col-sm-2 .d-sm-none .d-xxl-block" id="Headerr1c5" >
      <%@include file="/WEB-INF/personal-project/Login.jsp"%>

    </div>

    <div class="col-sm-2 .d-none.d-xxl-block" id="toprightblack"></div>

  </div>
  <div class="row">

  </div>


</header>
<main>
  <div class ="row">

    <div class = "col-sm-2 pe-0 .d-xxl-block"  id="left"><img src="images/left.png" alt="Left Image"></div>
    <div class = "col-lg-8 pe-0 ps-0" id="mainForm">
      <input type="hidden" id="currentpage" name="currentpage" value=${requestScope['javax.servlet.forward.request_uri']}/>