<%--
  Created by IntelliJ IDEA.
  User: jjbec
  Date: 1/25/2024
  Time: 8:17 PM
  To change this template use File | Settings | File Templates.
--%>

<%@include file="../Shared/top.jsp"%>
<link href="https://cdn.jsdelivr.net/npm/nprogress@0.2.0/nprogress.css" rel="stylesheet"/>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/nprogress@0.2.0/nprogress.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/loading.css">
<script src="js/loading.js"></script>

</head>
<body  onload="">>
<h1> Send an email</h1>
<c:choose>
<c:when test="${not empty Status and Status eq true}"><p>Email sent</p></c:when>
<c:when test="${not empty Status and Status eq false}"><p>Email not sent</p></c:when>
<c:otherwise><p>Use this form to send an email</p></c:otherwise>
</c:choose>
<form action="email" method="post">
    <!-- Email Address -->
    <label for="email">Email Address:</label>
    <input type="text" id="email" name="email" required>
    <br>

    <!-- Subject -->
    <label for="subject">Subject:</label>
    <input type="text" id="subject" name="subject" required>
    <br>

    <!-- Message -->
    <label for="message">Message:</label>
    <textarea id="message" name="message" rows="4" required></textarea>
    <br>

    <!-- Submit Button -->
    <input type="submit" value="Submit" onclick="showBar2()">



</form>

<%@include file="../Shared/bottom.jsp"%>