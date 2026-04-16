document.addEventListener('DOMContentLoaded', function() {
    const tableBody = document.getElementById('searchLineTable');
    tableBody.addEventListener('change', (e) => handleAutoUpdate(e));
    tableBody.addEventListener('blur', (e) => {
        if (e.target.tagName === 'INPUT') handleAutoUpdate(e);
    }, true);

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
            const tableBody = document.getElementById('searchLineTable');
            const addRow = document.getElementById('addSaved_Search_Order_Line').closest('tr');

            const categoryId = formData.get('inputsaved_search_order_lineCategory_ID');
            const searchPhrase = formData.get('inputsaved_search_order_lineSearch_Phrase');
            const orderId = formData.get('inputsaved_search_order_lineSaved_Search_Order_ID');

            // Get the category name from the select list
            const categorySelect = document.querySelector('#addSaved_Search_Order_Line select');
            const categoryText = categorySelect.options[categorySelect.selectedIndex].text;

            const row = document.createElement('tr');
            row.innerHTML = `
        <td class="ps-3 fw-bold text-muted">${lineNo}</td>
        <td colspan="3" class="p-0">
            <form method="post" action="editSaved_Search_Order_Line" id="editSaved_Search_Order_Line${lineNo}" class="row g-0 align-items-center w-100 py-2">
                <input type="hidden" name="inputsaved_search_order_lineSaved_Search_Order_ID" value="${orderId}">
                <input type="hidden" name="inputsaved_search_order_lineLine_No" value="${lineNo}">
                <input type="hidden" name="oldCategory" value="${categoryId}">
                <input type="hidden" name="oldPhrase" value="${searchPhrase}">

                <div class="col-md-5 px-2">
                    <select class="form-select form-select-sm border-0 bg-light" name="inputsaved_search_order_lineCategory_ID">
                        <option value="${categoryId}" selected>${categoryText}</option>
                        ${categorySelect.innerHTML} 
                    </select>
                </div>
                <div class="col-md-5 px-2">
                    <input type="text" class="form-control form-control-sm border-0 bg-light" name="inputsaved_search_order_lineSearch_Phrase" value="${searchPhrase}">
                </div>
                <div class="col-md-2 text-end pe-3">
                    <button class="btn btn-sm btn-outline-primary" type="submit">Update</button>
                </div>
            </form>
        </td>
    `;

            // Insert before the add row
            tableBody.insertBefore(row, addRow);

            // Increment for next time
            const nextLineNo = parseInt(lineNo) + 1;
            document.querySelector('input[name="inputsaved_search_order_lineLine_No"]').value = nextLineNo;
            document.getElementById('nextLineNoDisplay').textContent = nextLineNo;
        }


    }
);

function handleAutoUpdate(e) {
    const el = e.target;
    const row = el.closest('tr'); // This is usually the "parent" you want to manipulate
    const form = el.closest('form');
    if (!form || form.id === 'addSaved_Search_Order_Line') return;
    const submitBtn = row.querySelector('button[type="submit"]');
    if (submitBtn) {
        submitBtn.click();
    }
    console.log("Saving row for line:", row.querySelector('.fw-bold').innerText);
}