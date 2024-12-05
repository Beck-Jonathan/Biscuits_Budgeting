<%--************
Create the JSP For Viuw/Edit from the course table
 Created By Jonathan Beck 4/25/2024
**********--%>
<%@include file="/WEB-INF/learnx/top.jsp"%>
<div class = "container">
    <form method="post" action="${appURL}/editCourse" id = "editcourse" >
        <!-- id -->
        <div class ="row" id = "row0">
            <h2>id  :
                ${fn:escapeXml(course.id)}</h2>
        </div>
        <!-- name -->
        <div class ="row" id = "row1">
            <label for="inputcoursename" class="form-label">name</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.coursenameerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="name" <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputcoursename" name="inputcoursename" value="${fn:escapeXml(course.name)}">
                <c:if test="${not empty results.coursenameerror}">
                    <div class="invalid-feedback">${results.coursenameerror}</div>
                </c:if>
            </div>
        </div>
        <!-- description -->
        <div class ="row" id = "row2">
            <label for="inputcoursedescription" class="form-label">description</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.coursedescriptionerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="description" <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputcoursedescription" name="inputcoursedescription" value="${fn:escapeXml(course.description)}">
                <c:if test="${not empty results.coursedescriptionerror}">
                    <div class="invalid-feedback">${results.coursedescriptionerror}</div>
                </c:if>
            </div>
        </div>
        <!-- level -->
        <div class ="row" id = "row3">
            <label for="inputcourselevel" class="form-label">level</label>
            <div class="input-group input-group-lg">
                <select  class="<c:if test="${not empty results.coursecategory_iderror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="course_level" id="inputcourselevel" name="inputcourselevel" value="${fn:escapeXml(course.level)}">
                    <c:forEach items="${levels}" var="level">
                        <option value="${level}">${level}   </option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <!-- picture -->
        <div class ="row" id = "row4">
            <label for="inputcoursepicture" class="form-label">picture</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.coursepictureerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="picture" <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputcoursepicture" name="inputcoursepicture" value="${fn:escapeXml(course.picture)}">
                <c:if test="${not empty results.coursepictureerror}">
                    <div class="invalid-feedback">${results.coursepictureerror}</div>
                </c:if>
            </div>
        </div>
        <!-- teacher_id -->
        <div class ="row" id = "row5">
            <label for="inputcourseteacher_id" class="form-label">teacher_id</label>
            <div class="input-group input-group-lg">
                <select  class="<c:if test="${not empty results.courseteacher_iderror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="teacher_id" id="inputcourseteacher_id" name="inputcourseteacher_id" value="${fn:escapeXml(results.teacherID)}">
                    <c:forEach items="${teachers}" var="teacher">
                        <option value="${teacher.ID}">${teacher.first_name}&nbsp${teacher.last_name}   </option>
                    </c:forEach>
                </select>
                <c:if test="${not empty results.courseteacher_iderror}">
                    <div class="invalid-feedback">${results.courseteacher_iderror}</div>
                </c:if>
            </div>
        </div>
        <!-- category_id -->
        <div class ="row" id = "row6">
            <label for="inputcoursecategory_id" class="form-label">category_id</label>
            <div class="input-group input-group-lg">
                <select  class="<c:if test="${not empty results.coursecategory_iderror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1"  <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputcoursecategory_id" name="inputcoursecategory_id" value="${fn:escapeXml(course.categoryId)}">
                    <c:forEach items="${categorys}" var="category">
                        <option value="${category.id}"<c:if test="${course.id eq category.id}"> selected </c:if>>${category.name}   </option>
                    </c:forEach>
                </select>
                <c:if test="${not empty results.coursecategory_iderror}">
                    <div class="invalid-feedback">${results.coursecategory_iderror}</div>
                </c:if>
            </div>
        </div>
        <div class="align-items-center mt-0">
            <div class="d-grid"><button class="btn btn-orange mb-0" type="submit">Edit course </button></div>
            <c:if test="${not empty results.dbStatus}"
            ><p>${results.dbStatus}</p>
            </c:if>
        </div>
    </form>
</div>
<%@include file="/WEB-INF/learnx/bottom.jsp"%>

