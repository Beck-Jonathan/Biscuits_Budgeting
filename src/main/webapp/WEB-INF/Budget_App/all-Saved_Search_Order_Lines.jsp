<%--************
Create the JSP  For Viewing All of The  Saved_Search_Order_Line table
 Created By Jonathan Beck 2/6/2025
**********--%>

<div class = "container">
    <div class="row">
        <div class="col-12">
            <h1>All Roller Saved_Search_Order_Lines</h1>
            <p>There ${saved_search_order.saved_Search_Order_Lines.size() eq 1 ? "is" : "are"}&nbsp;${saved_search_order.saved_Search_Order_Lines.size()} Saved_Search_Order_Line${saved_search_order.saved_Search_Order_Lines.size() ne 1 ? "s" : ""}</p>
            <p color="red">${SearchTooShort}</p>

                <div class="table-responsive"><table class="table table-bordered">
                    <thead>
                    <tr>

                        <th scope="col">Line_No</th>
                        <th scope="col">Category_ID</th>
                        <th scope="col">Search_Phrase</th>


                        <th scope="col">Edit/Create</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${saved_search_order.saved_Search_Order_Lines}" var="saved_search_order_line">
                        <tr>
                            <form method="post" action="${appURL}/editSaved_Search_Order_Line" id = "editSaved_Search_Order_Line${fn:escapeXml(saved_search_order_line.line_No)}"  >
                            <!-- search_id -->
                                <input type="hidden" name ="inputsaved_search_order_lineSaved_Search_Order_ID" value="${saved_search_order.saved_Search_Order_ID}">
                                <!-- end search id -->

                                <!-- start line number -->
                                <td><input type="hidden" name ="inputsaved_search_order_lineLine_No" value="${fn:escapeXml(saved_search_order_line.line_No)}"> ${fn:escapeXml(saved_search_order_line.line_No)}</td>
                            <!-- end line number -->
                            <!-- start category -->
                                <td>
                                    <input type="hidden" name ="oldCategory" value="${saved_search_order_line.category_ID}">
                                <div class="input-group input-group-lg">
                                    <select  class="<c:if test="${not empty results.saved_search_order_lineCategory_IDerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1"   id="inputsaved_search_order_lineCategory_ID" name="inputsaved_search_order_lineCategory_ID" value="${fn:escapeXml(saved_search_order_line.category_ID)}">
                                        <c:forEach items="${Categorys}" var="Category">
                                            <option value="${Category.category_ID}"<c:if test="${saved_search_order_line.category_ID eq Category.category_ID}"> selected </c:if>>${Category.category_ID}   </option>
                                        </c:forEach>
                                    </select>
                                    <c:if test="${not empty results.saved_search_order_lineCategory_IDerror}">
                                        <div class="invalid-feedback">${results.saved_search_order_lineCategory_IDerror}</div>
                                    </c:if>
                                </div>
                            </td>
                            <!-- end category -->
                            <!-- start search phrase -->
                            <td>
                                <input type="hidden" name="oldPhrase" value ="${fn:escapeXml(saved_search_order_line.search_Phrase)}">
                                <div class="input-group input-group-lg">
                                    <input type="text" class="<c:if test="${not empty results.saved_search_order_lineSearch_Phraseerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Search_Phrase"   id="inputsaved_search_order_lineSearch_Phrase" name="inputsaved_search_order_lineSearch_Phrase" value="${fn:escapeXml(saved_search_order_line.search_Phrase)}">
                                    <c:if test="${not empty results.saved_search_order_lineSearch_Phraseerror}">
                                        <div class="invalid-feedback">${results.saved_search_order_lineSearch_Phraseerror}</div>
                                    </c:if>
                                </div>
                            </td>
                            <!-- end search prhase -->


                            <!-- start button -->
                            <td>
                                <div class="d-grid"><button class="btn btn-orange mb-0" type="submit">Edit Line </button></div>
                                <c:if test="${not empty results.dbStatus}"
                                ><p>${results.dbStatus}</p>
                                </c:if>
                            </td>
                            <!-- end button -->
                            </form>


                        </tr>
                    </c:forEach>
                    <tr>
                    <form method="post" action="${appURL}/addSaved_Search_Order_Line" id = "addSaved_Search_Order_Line" >

                        <!-- Line no-->
                        <td><input type="hidden" name="inputsaved_search_order_lineLine_No" value="${saved_search_order.saved_Search_Order_Lines.size()+1}" />${saved_search_order.saved_Search_Order_Lines.size()+1}</td>
                        <!-- Category_ID -->
                        <td>

                            <div class="input-group input-group-lg">
                                <select  class="<c:if test="${not empty results.saved_search_order_lineCategory_IDerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Category_ID" id="inputsaved_search_order_lineCategory_ID" name="inputsaved_search_order_lineCategory_ID" value="${fn:escapeXml(results.Category_ID)}">
                                    <c:forEach items="${Categorys}" var="Category">
                                        <option value="${Category.category_ID}">${Category.category_ID}   </option>
                                    </c:forEach>
                                </select>
                                <c:if test="${not empty results.saved_search_order_lineCategory_IDerror}">
                                    <div class="invalid-feedback">${results.saved_search_order_lineCategory_IDerror}</div>
                                </c:if>
                            </div>
                        </td>
                        <!-- Search_Phrase -->
                        <td>
                            <div class="input-group input-group-lg">
                                <input type="text" class="<c:if test="${not empty results.saved_search_order_lineSearch_Phraseerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Search_Phrase" id="inputsaved_search_order_lineSearch_Phrase" name="inputsaved_search_order_lineSearch_Phrase" value="${fn:escapeXml(results.Search_Phrase)}">
                                <c:if test="${not empty results.saved_search_order_lineSearch_Phraseerror}">
                                    <div class="invalid-feedback">${results.saved_search_order_lineSearch_Phraseerror}</div>
                                </c:if>
                            </div>
                        </td>
                           
                        <!-- submit -->

                        <td>
                            <input type="hidden" name="inputsaved_search_order_lineSaved_Search_Order_ID" value="${fn:escapeXml(saved_search_order.saved_Search_Order_ID)}" />
                            <div class="d-grid"><button class="btn btn-orange mb-0" type="submit">Create Saved_Search_Order_Line  </button></div>
                            <c:if test="${not empty results.dbStatus}"
                            ><p>${results.dbStatus}</p>
                            </c:if>
                        </td>
                            </form>
                    </tr>



                    </tbody>
                </table>
                </div>
            <div>
                <form method="post" action="${appURL}/apply-Saved_Search_Order" id = "editSaved_Search_Order_Line${fn:escapeXml(saved_search_order_line.line_No)}">

            <input type="hidden" name="saved_search_orderid" value="${saved_search_order.saved_Search_Order_ID}">
            <button class="btn btn-orange mb-0" type="submit">Apply Sorting!  </button>
            </form>
            </div>

        </div>
    </div>
</div>
</main>


