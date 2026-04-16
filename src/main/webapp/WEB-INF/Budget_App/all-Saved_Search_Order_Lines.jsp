
<%--************
    Budget App: Saved Search Rules (Order Lines)
    Updated for UX - 4/16/2026
**********--%>

<div class="container py-4">
    <div class="row">
        <div class="col-12">
            <div class="d-flex justify-content-between align-items-end mb-4">
                <div>
                    <h1 class="fw-bold" style="color: #2c3e50;">Auto-Sort Rules</h1>
                    <p class="text-muted mb-0">
                        You have <span class="badge bg-primary" id="ruleCountBadge">
                        ${saved_search_order.saved_Search_Order_Lines.size()}
                    </span>
                        active rule${saved_search_order.saved_Search_Order_Lines.size() ne 1 ? "s" : ""}
                    </p>
                </div>
                <form method="post" action="${appURL}/apply-Saved_Search_Order">
                    <input type="hidden" name="saved_search_orderid"
                           value="${saved_search_order.saved_Search_Order_ID}">
                    <button class="btn btn-success shadow-sm" type="submit">
                        <i class="bi bi-play-fill"></i> Apply Sorting Now
                    </button>
                </form>
            </div>

            <c:if test="${not empty SearchTooShort}">
                <div class="alert alert-danger border-0 shadow-sm animate__animated animate__shakeX">
                    <i class="bi bi-exclamation-octagon me-2"></i> ${SearchTooShort}
                </div>
            </c:if>

            <div class="card border-0 shadow-sm mb-4">
                <div class="card-header bg-white py-3">
                    <h5 class="card-title mb-0 text-uppercase small fw-bold text-muted">Priority Ranking & Keywords</h5>
                </div>
                <div class="table-responsive">
                    <table class="table table-hover align-middle mb-0">
                        <thead class="bg-light">
                        <tr>
                            <th class="ps-3" style="width: 80px;">#</th>
                            <th class="col-md-5">Target Category</th>
                            <th class="col-md-5">Search Keyword/Phrase</th>
                            <th class="text-end pe-4" style="width: 150px;">Action</th>
                        </tr>
                        </thead>
                        <tbody id="searchLineTable">
                        <c:forEach items="${saved_search_order.saved_Search_Order_Lines}" var="saved_search_order_line">
                            <tr id="row-${saved_search_order_line.line_No}">
                                <td class="ps-3 fw-bold text-muted">
                                        ${fn:escapeXml(saved_search_order_line.line_No)}
                                </td>
                                <td colspan="3" class="p-0">
                                    <form method="post" action="${appURL}/editSaved_Search_Order_Line"
                                          id="editSaved_Search_Order_Line${saved_search_order_line.line_No}"
                                          class="row g-0 align-items-center w-100 py-2 m-0">

                                        <input type="hidden" name="inputsaved_search_order_lineSaved_Search_Order_ID"
                                               value="${saved_search_order.saved_Search_Order_ID}">
                                        <input type="hidden" name="inputsaved_search_order_lineLine_No"
                                               value="${fn:escapeXml(saved_search_order_line.line_No)}">
                                        <input type="hidden" name="oldCategory"
                                               value="${saved_search_order_line.category_ID}">
                                        <input type="hidden" name="oldPhrase"
                                               value="${fn:escapeXml(saved_search_order_line.search_Phrase)}">

                                        <div class="col-md-5 px-2">
                                            <select class="form-select form-select-sm border-0 bg-light-subtle"
                                                    name="inputsaved_search_order_lineCategory_ID">
                                                <c:forEach items="${Categorys}" var="Category">
                                                    <option value="${Category.category_ID}"
                                                            <c:if test="${saved_search_order_line.category_ID eq Category.category_ID}">selected</c:if>>
                                                            ${Category.category_Name}
                                                    </option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="col-md-5 px-2">
                                            <input type="text"
                                                   class="form-control form-control-sm border-0 bg-light-subtle"
                                                   name="inputsaved_search_order_lineSearch_Phrase"
                                                   value="${fn:escapeXml(saved_search_order_line.search_Phrase)}">
                                        </div>
                                        <div class="col-md-2 text-end pe-4">
                                            <button class="btn btn-sm btn-outline-primary px-3" type="submit">Update
                                            </button>
                                        </div>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>

                        <tr class="bg-light-subtle border-top border-primary-subtle">
                            <td class="ps-3 fw-bold text-primary" id="nextLineNoDisplay">
                                ${saved_search_order.saved_Search_Order_Lines.size()+1}
                            </td>
                            <td colspan="3" class="p-0">
                                <form id="addSaved_Search_Order_Line" method="post"
                                      action="${appURL}/addSaved_Search_Order_Line"
                                      class="row g-0 align-items-center w-100 py-3 m-0">

                                    <input type="hidden" name="inputsaved_search_order_lineSaved_Search_Order_ID"
                                           value="${fn:escapeXml(saved_search_order.saved_Search_Order_ID)}"/>
                                    <input type="hidden" name="inputsaved_search_order_lineLine_No"
                                           value="${saved_search_order.saved_Search_Order_Lines.size()+1}"/>
                                    <input type="hidden" name="ajax" value="true"/>

                                    <div class="col-md-5 px-2">
                                        <select class="form-select border-0 shadow-sm"
                                                name="inputsaved_search_order_lineCategory_ID" required>
                                            <option value="" disabled selected>Select Target Category...</option>
                                            <c:forEach items="${Categorys}" var="Category">
                                                <option value="${Category.category_ID}">${Category.category_Name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-md-5 px-2">
                                        <input type="text" class="form-control border-0 shadow-sm"
                                               placeholder="Keyword (e.g. Starbucks)"
                                               name="inputsaved_search_order_lineSearch_Phrase" required>
                                    </div>
                                    <div class="col-md-2 text-end pe-4">
                                        <button class="btn btn-orange text-black fw-bold btn-sm w-100 shadow-sm"
                                                type="submit">
                                            <i class="bi bi-plus-lg me-1"></i> Add Rule
                                        </button>
                                    </div>
                                </form>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="d-flex justify-content-between align-items-center mt-3">
                <div class="small text-muted fst-italic">
                    <c:choose>
                        <c:when test="${not empty results.dbStatus}">
                            <i class="bi bi-info-circle me-1"></i> ${results.dbStatus}
                        </c:when>
                        <c:otherwise>
                            Changes are saved instantly when you click Update or Add.
                        </c:otherwise>
                    </c:choose>
                </div>
                <form method="post" action="${appURL}/apply-Saved_Search_Order">
                    <input type="hidden" name="saved_search_orderid"
                           value="${saved_search_order.saved_Search_Order_ID}">
                    <button class="btn btn-success shadow-sm" type="submit">
                        <i class="bi bi-play-fill"></i> Apply Sorting Now
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>