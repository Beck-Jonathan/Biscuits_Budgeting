<%--************
    Budget App: Saved Search Rules (Order Lines)
    Updated for UX - 3/27/2026
**********--%>

<div class="container py-4">
    <div class="row">
        <div class="col-12">
            <div class="d-flex justify-content-between align-items-end mb-4">
                <div>
                    <h1 class="fw-bold" style="color: #2c3e50;">Auto-Sort Rules</h1>
                    <p class="text-muted mb-0">
                        You have <span
                            class="badge bg-primary">${saved_search_order.saved_Search_Order_Lines.size()}</span>
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
                <div class="alert alert-danger border-0 shadow-sm">${SearchTooShort}</div>
            </c:if>

            <div class="card border-0 shadow-sm mb-4">
                <div class="card-header bg-white py-3">
                    <h5 class="card-title mb-0 text-uppercase small fw-bold text-muted">Existing Rules</h5>
                </div>
                <div class="table-responsive">
                    <table class="table table-hover align-middle mb-0">
                        <thead class="bg-light">
                        <tr>
                            <th class="ps-3" style="width: 80px;">#</th>
                            <th>Target Category</th>
                            <th>Search Keyword/Phrase</th>
                            <th class="text-end pe-3">Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${saved_search_order.saved_Search_Order_Lines}" var="saved_search_order_line">
                            <tr>
                                <form method="post" action="${appURL}/editSaved_Search_Order_Line">
                                    <input type="hidden" name="inputsaved_search_order_lineSaved_Search_Order_ID"
                                           value="${saved_search_order.saved_Search_Order_ID}">
                                    <input type="hidden" name="inputsaved_search_order_lineLine_No"
                                           value="${fn:escapeXml(saved_search_order_line.line_No)}">
                                    <input type="hidden" name="oldCategory"
                                           value="${saved_search_order_line.category_ID}">
                                    <input type="hidden" name="oldPhrase"
                                           value="${fn:escapeXml(saved_search_order_line.search_Phrase)}">

                                    <td class="ps-3 fw-bold text-muted">${fn:escapeXml(saved_search_order_line.line_No)}</td>
                                    <td>
                                        <select class="form-select form-select-sm border-0 bg-light <c:if test='${not empty results.saved_search_order_lineCategory_IDerror}'>is-invalid</c:if>'"
                                                name="inputsaved_search_order_lineCategory_ID">
                                            <c:forEach items="${Categorys}" var="Category">
                                                <option value="${Category.category_ID}"
                                                        <c:if test="${saved_search_order_line.category_ID eq Category.category_ID}">selected</c:if>>
                                                        ${Category.category_Name}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td>
                                        <input type="text"
                                               class="form-control form-control-sm border-0 bg-light <c:if test='${not empty results.saved_search_order_lineSearch_Phraseerror}'>is-invalid</c:if>'"
                                               name="inputsaved_search_order_lineSearch_Phrase"
                                               value="${fn:escapeXml(saved_search_order_line.search_Phrase)}">
                                    </td>
                                    <td class="text-end pe-3">
                                        <button class="btn btn-sm btn-outline-primary" type="submit">Update</button>
                                    </td>
                                </form>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="card border-0 shadow-sm bg-light">
                <div class="card-body">
                    <h6 class="fw-bold text-uppercase small text-muted mb-3">Add New Sorting Rule</h6>
                    <form method="post" action="${appURL}/addSaved_Search_Order_Line"
                          class="row g-3 align-items-center">
                        <input type="hidden" name="inputsaved_search_order_lineSaved_Search_Order_ID"
                               value="${fn:escapeXml(saved_search_order.saved_Search_Order_ID)}"/>
                        <input type="hidden" name="inputsaved_search_order_lineLine_No"
                               value="${saved_search_order.saved_Search_Order_Lines.size()+1}"/>
                        <input type="hidden" name="ajax" value="true"/>

                        <div class="col-md-1 text-muted fw-bold ps-3">
                            ${saved_search_order.saved_Search_Order_Lines.size()+1}
                        </div>
                        <div class="col-md-4">
                            <select class="form-select border-0 <c:if test='${not empty results.saved_search_order_lineCategory_IDerror}'>is-invalid</c:if>'"
                                    name="inputsaved_search_order_lineCategory_ID">
                                <option value="" disabled selected>Select Category...</option>
                                <c:forEach items="${Categorys}" var="Category">
                                    <option value="${Category.category_ID}">${Category.category_Name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-md-4">
                            <input type="text"
                                   class="form-control border-0 <c:if test='${not empty results.saved_search_order_lineSearch_Phraseerror}'>is-invalid</c:if>'"
                                   placeholder="Keyword (e.g. Starbucks)"
                                   name="inputsaved_search_order_lineSearch_Phrase"
                                   value="${fn:escapeXml(results.Search_Phrase)}">
                        </div>
                        <div class="col-md-3 d-grid">
                            <button class="btn btn-orange text-white fw-bold" type="submit">Add Rule</button>
                        </div>
                    </form>
                </div>
            </div>

            <c:if test="${not empty results.dbStatus}">
                <div class="mt-3 small text-center text-muted italic">${results.dbStatus}</div>
            </c:if>
            <form method="post" action="${appURL}/apply-Saved_Search_Order">
                <input type="hidden" name="saved_search_orderid" value="${saved_search_order.saved_Search_Order_ID}">
                <button class="btn btn-success shadow-sm" type="submit">
                    <i class="bi bi-play-fill"></i> Apply Sorting Now
                </button>
            </form>

        </div>
    </div>
</div>