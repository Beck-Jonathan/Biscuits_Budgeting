document.addEventListener('DOMContentLoaded', function() {
    const tableBody = document.getElementById('searchLineTable');

    tableBody.addEventListener('submit', function(e) {
        if (e.target.id.startsWith('editSaved_Search_Order_Line')) {
            e.preventDefault();

            const form = e.target;

            // FIX: Find the row first, then find the button inside that row
            const row = form.closest('tr');
            const submitBtn = row.querySelector('button[type="submit"]');

            // Safety check to prevent the "innerHTML" error
            if (!submitBtn) return;

            const originalBtnText = submitBtn.innerHTML;

            // 1. Visual Indicator: "Awaiting reply"
            submitBtn.disabled = true;
            submitBtn.innerHTML = '<span class="spinner-border spinner-border-sm" role="status"></span> Saving...';

            const formData = new FormData(form);

            fetch(form.action, {
                method: 'POST',
                body: new URLSearchParams(formData)
            })
                .then(response => {
                    if (response.ok) {
                        // 2. Success Feedback
                        submitBtn.classList.remove('btn-orange');
                        submitBtn.classList.add('btn-success');
                        submitBtn.innerHTML = '✅ Updated';

                        setTimeout(() => {
                            submitBtn.classList.remove('btn-success');
                            submitBtn.classList.add('btn-orange');
                            submitBtn.innerHTML = originalBtnText;
                            submitBtn.disabled = false;
                        }, 2000);
                    } else {
                        throw new Error('Update failed');
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    submitBtn.classList.add('btn-danger');
                    submitBtn.innerHTML = '❌ Error';
                    submitBtn.disabled = false;
                });
        }
    });
});

document.addEventListener('DOMContentLoaded', function() {
        const addForm = document.getElementById('addSaved_Search_Order_Line');
        const tableBody = document.getElementById('searchLineTable');

        addForm.addEventListener('submit', function (e) {
            e.preventDefault(); // Stop page refresh

            const formData = new FormData(addForm);

            // Send data to your Servlet
            fetch(addForm.action, {
                method: 'POST',
                body: new URLSearchParams(formData) // Standard form encoding
            })
                .then(response => response.text()) // Assuming Servlet returns the new Line No
                .then(newLineNo => {
                    if (newLineNo) {
                        addNewRowToTable(formData, newLineNo.trim());
                        addForm.reset(); // Clear the add row fields
                    }
                })
                .catch(error => console.error('Error:', error));
        });

        function addNewRowToTable(formData, lineNo) {
            // 1. Get the Add Row - find it relative to the form
            const addRow = addForm.closest('tr');
            const tableBody = addRow.closest('tbody') || addRow.parentNode;

            // 2. Extract values safely from the formData object (most reliable)
            const categoryId = formData.get('inputsaved_search_order_lineCategory_ID');
            const searchPhrase = formData.get('inputsaved_search_order_lineSearch_Phrase');
            const orderId = formData.get('inputsaved_search_order_lineSaved_Search_Order_ID');

            // Get the text label for the category from the actual select element
            const categorySelect = addRow.querySelector('select[name="inputsaved_search_order_lineCategory_ID"]');
            const categoryText = categorySelect.options[categorySelect.selectedIndex].text;

            // 3. Create the new row HTML
            const row = document.createElement('tr');
            row.innerHTML = `
        <td>
        <form method="post" action="editSaved_Search_Order_Line" id="editSaved_Search_Order_Line${lineNo}">
                <input type="hidden" name="inputsaved_search_order_lineSaved_Search_Order_ID" value="${orderId}">
                <input type="hidden" name="inputsaved_search_order_lineLine_No" value="${lineNo}"> 
                ${lineNo}
               
            </td>
            <td>
                <input type="hidden" name="oldCategory" value="${categoryId}">
                <div class="input-group input-group-lg">
                    <select class="form-control border-0 bg-light rounded-end ps-1" name="inputsaved_search_order_lineCategory_ID">
                        <option value="${categoryId}" selected>${categoryText}</option>
                    </select>
                </div>
            </td>
            <td>
                <input type="hidden" name="oldPhrase" value="${searchPhrase}">
                <div class="input-group input-group-lg">
                    <input type="text" class="form-control border-0 bg-light rounded-end ps-1" name="inputsaved_search_order_lineSearch_Phrase" value="${searchPhrase}">
                </div>
            </td>
            <td>
                <div class="d-grid"><button class="btn btn-orange mb-0" type="submit">Edit Line</button></div>
            </form>
        </td>
    `;

            // 4. Insert the new row before the "Add" row
            tableBody.insertBefore(row, addRow);

            // 5. Update the "Add" row line number for the next entry
            // We search for the input by name instead of using :first-child
            const nextLineNo = parseInt(lineNo) + 1;
            const addRowLineNoInput = addRow.querySelector('input[name="inputsaved_search_order_lineLine_No"]');
            if (addRowLineNoInput) {
                addRowLineNoInput.value = nextLineNo;
                // Update the visible text next to the hidden input
                addRowLineNoInput.parentElement.lastChild.textContent = nextLineNo;
            }
        }
    }
);