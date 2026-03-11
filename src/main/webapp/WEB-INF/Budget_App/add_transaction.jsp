<%@include file="/WEB-INF/Budget_App/budget_top.jsp"%>

<div class="table-responsive col-12">

  <table class="table table-bordered">




    <c:if test="${empty User}">
      <!--<tr><td>Join Us</td><td><a href="joinus"> View </a> </td></tr> -->
    </c:if>


    <thead>
    <row  id = "row7">
      <form method="post" action="${appURL}/add_transaction" id="budget" enctype="multipart/form-data">
        <label for="upload_transactions" class="form-label">Transaction Text File (.txt)</label>

        <div class="input-group input-group-lg">
          <input type="file"
                 accept=".txt"
                 class="form-control border-0 bg-light rounded-end ps-1 <c:if test='${not empty results.FileEmptyError}'>is-invalid</c:if>"
                 id="upload_transactions"
                 name="upload_transactions">

          <c:if test="${not empty results.FileEmptyError}">
            <div class="invalid-feedback">${results.FileEmptyError}</div>
          </c:if>
        </div>

        <div class="align-items-center mt-3">
          <div class="d-grid">
            <button class="btn btn-orange mb-0" type="submit">Import Transactions</button>
          </div>

          <%-- Displays general database or system errors --%>
          <c:if test="${not empty results.dbStatus}">
            <p class="text-danger mt-2">${results.dbStatus}</p>
          </c:if>
          <c:if test="${not empty results.dbError}">
            <p class="text-danger mt-2">${results.dbError}</p>
          </c:if>
        </div>
      </form>
    </row>
    </thead>
      <tr>upload</tr>
    <tbody>

    </tbody>
  </table>

</div>

<div class="jumbotron jumbotron-fluid">
  <div class="container">
    <h2 class="display-4 text-center">Welcome to Biscuit's Budgeting</h2>
    <p class="lead text-center">Let's make some money</p>
    <div class="row">
      <div class="col col-md-4">

      </div>
      <div class="col col-md-4">

      </div>
      <div class="col col-md-4">

      </div>
    </div>
  </div>
</div>


<%@include file="/WEB-INF/Budget_App/budget_bottom.jsp"%>
