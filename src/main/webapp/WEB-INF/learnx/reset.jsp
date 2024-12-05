<%@include file="/WEB-INF/learnx/top.jsp"%>
<main>
  <section class="p-0 d-flex align-items-center position-relative overflow-hidden">
    <div class="container-fluid">
      <div class="row">
        <div class="col-12 col-lg-8 m-auto">
          <div class="row my-5">
            <div class="col-sm-10 col-xl-8 m-auto">
              <h2>Nice to see you!</h2>
              <p class="lead mb-4">Please share your email so we can reset your password.</p>
              <c:if test="${not empty results.loginFail}">
                <p class="alert alert-danger my-2">${results.loginFail}</p>
              </c:if>


              <!-- Form START -->
              <form method="post" action="${appURL}/resetpassword">
                <!-- Email -->
                <div class="mb-4">
                  <c:if test="${not empty results.loginFail}">
                    <div class="invalid-feedback">${results.loginFail}</div>
                  </c:if>
                  <label for="inputEmail1" class="form-label">Email address *</label>
                  <div class="input-group input-group-lg">
                    <span class="input-group-text bg-light rounded-start border-0 text-secondary px-3"><i class="bi bi-envelope-fill"></i></span>
                    <input type="text" class="<c:if test="${not empty results.emailError}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="E-mail" id="inputEmail1" name="inputEmail1" value="${results.email}">
                    <c:if test="${not empty results.emailError}">
                      <div class="invalid-feedback">${results.emailError}</div>
                    </c:if>
                  </div>
                </div>





                <!-- Button -->
                <div class="align-items-center mt-0">
                  <div class="d-grid">
                    <button class="btn btn-orange mb-0" type="submit">Submit</button>
                  </div>
                </div>
              </form>
              <!-- Form END -->
              <!-- Sign up link -->
              <div class="mt-4 text-center">
                <span>Don't have an account? <a href="${appURL}/signup">Sign up here</a></span>
              </div>
              <div class="mt-4 text-center">
                <span>Here by accident?<a href="${appURL}/login">Sign In here</a></span>
              </div>


            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</main>
<%@include file="/WEB-INF/learnx/bottom.jsp"%>