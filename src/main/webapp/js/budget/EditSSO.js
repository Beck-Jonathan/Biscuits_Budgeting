/**
 * Biscuit's Budget Analysis - Search Order Line Controller
 * Handles CRUD operations for Search Order Lines with Toast Feedback.
 */

document.addEventListener('DOMContentLoaded', function() {
    // 1. Table Event Delegation
    const tableBody = document.getElementById('searchLineTable');

    // Delegation for manual changes and blur events
    tableBody.addEventListener('change', (e) => handleAutoUpdate(e));
    tableBody.addEventListener('blur', (e) => {
        if (e.target.tagName === 'INPUT') handleAutoUpdate(e);
    }, true);

    // Delegation for Form Submissions
    tableBody.addEventListener('submit', function(e) {
        if (e.target.id && e.target.id.startsWith('editSaved_Search_Order_Line')) {
            e.preventDefault();
            processEditForm(e.target);
        }
    });

    // 2. Add New Line Form Listener
    const addForm = document.getElementById('addSaved_Search_Order_Line');
    if (addForm) {
        addForm.addEventListener('submit', function (e) {
            e.preventDefault();
            processAddForm(addForm);
        });
    }
});

/**
 * Handles the logic for editing existing lines
 */
async function processEditForm(form) {
    showToast("shared", 2)
    const row = form.closest('tr');
    const submitBtn = row.querySelector('button[type="submit"]');
    if (!submitBtn) return;

    const originalBtnText = submitBtn.innerHTML;
    submitBtn.disabled = true;
    submitBtn.innerHTML = '<span class="spinner-border spinner-border-sm" role="status"></span> Saving...';

    const formData = new FormData(form);

    try {
        const response = await fetch(form.action, {
            method: 'POST',
            body: new URLSearchParams(formData)
        });

        const resultCode = await response.text();

        // --- TOAST INTEGRATION ---
        showToast('editSaved_Search_Order_Line', resultCode.trim());

        if (resultCode.trim() === "1") {
            submitBtn.classList.remove('btn-orange');
            submitBtn.classList.add('btn-success');
            submitBtn.innerHTML = '✅ Updated';


            const currentCat = form.querySelector('[name="inputsaved_search_order_lineCategory_ID"]').value;
            const currentPhrase = form.querySelector('[name="inputsaved_search_order_lineSearch_Phrase"]').value;

            form.querySelector('input[name="oldCategory"]').value = currentCat;
            form.querySelector('input[name="oldPhrase"]').value = currentPhrase;
            setTimeout(() => {
                submitBtn.classList.remove('btn-success');
                submitBtn.classList.add('btn-orange');
                submitBtn.innerHTML = originalBtnText;
                submitBtn.disabled = false;
            }, 2000);
        } else {
            //showToast('editSaved_Search_Order_Line', 'resultCode');
        }
    } catch (error) {
        showToast('editSaved_Search_Order_Line', '-1'); // Generic failure mapping
        submitBtn.classList.add('btn-danger');
        submitBtn.innerHTML = '❌ Error';
        submitBtn.disabled = false;
    }
}

/**
 * Handles the logic for adding new lines
 */
async function processAddForm(form) {
    const formData = new FormData(form);

    try {
        const response = await fetch(form.action, {
            method: 'POST',
            body: new URLSearchParams(formData)
        });

        const result = await response.text();
        const code = result.trim();
        const parsedCode = parseInt(code);

        // If it's a positive number, it's the new LineNo (Success)
        if (parsedCode > 0) {
            showToast('addSaved_Search_Order_Line', '1');
            addNewRowToTable(formData, code);
            form.reset();
        } else {
            showToast('addSaved_Search_Order_Line', code);
        }
    } catch (error) {
        showToast('addSaved_Search_Order_Line', '-1');
    }
}

/**
 * Updates DOM with new row
 */
function addNewRowToTable(formData, lineNo) {
    const tableBody = document.getElementById('searchLineTable');
    const addRow = document.getElementById('addSaved_Search_Order_Line').closest('tr');
    const categoryId = formData.get('inputsaved_search_order_lineCategory_ID');
    const searchPhrase = formData.get('inputsaved_search_order_lineSearch_Phrase');
    const orderId = formData.get('inputsaved_search_order_lineSaved_Search_Order_ID');
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
                <div class="col-md-2 text-end pe-4">
                    <button class="btn btn-sm btn-outline-primary px-3" type="submit">Update</button>
                </div>
            </form>
        </td>
    `;
    tableBody.insertBefore(row, addRow);
    const selectEl = row.querySelector('select[name="inputsaved_search_order_lineCategory_ID"]');
    if (selectEl) {
        selectEl.value = categoryId;
    }

    const nextLineNo = parseInt(lineNo) + 1;
    document.querySelector('input[name="inputsaved_search_order_lineLine_No"]').value = nextLineNo;
    document.getElementById('nextLineNoDisplay').textContent = nextLineNo;
}

/**
 * Auto-trigger logic
 */
function handleAutoUpdate(e) {
    const el = e.target;
    const form = el.closest('form');
    if (!form || form.id === 'addSaved_Search_Order_Line') return;

    const submitBtn = form.querySelector('button[type="submit"]');
    if (submitBtn) {
        submitBtn.click();
    }
}