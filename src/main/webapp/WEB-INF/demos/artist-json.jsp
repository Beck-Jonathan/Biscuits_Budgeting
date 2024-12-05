<%@include file="../Shared/top.jsp"%>
    <title>Music Artists</title>
    <link href="https://cdn.jsdelivr.net/npm/nprogress@0.2.0/nprogress.css" rel="stylesheet"/>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/nprogress@0.2.0/nprogress.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/loading.css">
    <script src="js/loading.js"></script>

</head>
<body>
<div class="container my-4">
    <div class="row">
        <c:forEach items="${artists}" var="artist">
            <div class="col-xl-2 col-lg-3 col-md-4 col-sm-6">
                <div class="card mb-4">
                    <img class="card-img-top" src="${artist.picture_big}" alt="${artist.name}">
                    <div class="card-body">
                        <h5 class="card-title">${artist.name}</h5>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<%@include file="../Shared/bottom.jsp"%>