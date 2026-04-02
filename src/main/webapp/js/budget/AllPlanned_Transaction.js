/* --- GLOBAL FUNCTIONS --- */
let currentSortCol = -1;
let isAsc = true;

let forecastDataNextMonth = [];

function refreshForecastContext() {
    $.get("AnalysisAJAX", {
        mode: "2", // Forecast Mode
        level: "0", // Subcategory Level
        monthsBack: "24",
        monthsForward: "1", // Just the next month
        bankAccountSelect: $('#bankAccountSelect').val() || "all"
    }, (res) => {
        // AnalysisAJAX usually returns an array of periods; we want the first (and only) forecast period
        forecastDataNextMonth = res[0] || [];
        console.log("Power User Context Loaded:", forecastDataNextMonth);
    });
}

function toggleDetails(btn) {
    const $mainRow = $(btn).closest('tr');
    const uuid = $mainRow.data('id');
    const $detailsRow = $(`#details-${uuid}`);
    const $icon = $(btn).find('.toggle-icon');

    if ($detailsRow.hasClass('d-none')) {
        // --- 1. DATA EXTRACTION ---
        const nickname = $mainRow.find('td[data-field="nickname"] input').val();
        const amount = parseFloat($mainRow.find('td[data-field="amount"] input').val()) || 0;
        const startDateStr = $mainRow.find('td[data-field="start_date"] input').val();
        const freq = parseInt($mainRow.find('td[data-field="times_per_year"] select').val());
        const occurrences = parseInt($mainRow.find('td[data-field="occurrences"] input').val());
        const subcatID = $mainRow.find('td[data-field="subcategory_ID"] select').val();
        const subcatName = $mainRow.find('td[data-field="subcategory_ID"] option:selected').text().trim();

        // --- 2. COLUMN A: SCHEDULE PROJECTION ---
        let colSchedule = `
            <h6 class="fw-bold text-uppercase small text-muted mb-3"><i class="bi bi-calendar3 me-2"></i>Upcoming Schedule</h6>
            <ul class="list-unstyled small mb-0" style="max-height: 150px; overflow-y: auto;">`;

        if (freq === 0) {
            colSchedule += `<li><i class="bi bi-calendar-event me-2"></i>${startDateStr} (One-Time)</li>`;
        } else {
            let currentDate = new Date(startDateStr + "T00:00:00");
            const displayLimit = (occurrences === -1) ? 12 : Math.min(occurrences, 24);
            const monthsStep = 12 / freq;

            for (let i = 0; i < displayLimit; i++) {
                colSchedule += `<li class="mb-1"><i class="bi bi-dot"></i>${currentDate.toISOString().split('T')[0]}</li>`;
                currentDate.setMonth(currentDate.getMonth() + monthsStep);
            }
            if (occurrences === -1 || occurrences > 24) colSchedule += '<li class="text-muted italic">... View full schedule in reports</li>';
        }
        colSchedule += `</ul>`;

        // --- 3. COLUMN B: SAVINGS ENGINE (3% APY) ---
        const monthsToFirst = Math.max(1, Math.ceil((new Date(startDateStr) - new Date()) / (1000 * 60 * 60 * 24 * 30)));
        const monthlyRate = 0.03 / 12;
        const denominator = Math.pow(1 + monthlyRate, monthsToFirst) - 1;
        const suggestedSavings = denominator > 0 ? (amount * monthlyRate) / denominator : amount;

        let colSavings = `
            <h6 class="fw-bold text-uppercase small text-muted mb-3"><i class="bi bi-piggy-bank me-2"></i>Sinking Fund</h6>
            <p class="small mb-2">Target for first payment in <strong>${monthsToFirst} months</strong>:</p>
            <div class="alert alert-info py-3 mb-2 shadow-sm">
                <span class="h4 mb-0">$${suggestedSavings.toFixed(2)}</span> <small class="text-uppercase">/ month</small>
            </div>
            <p class="x-small text-muted mb-0">Calculated at 3% APY to ensure liquidity by the start date.</p>`;

        // --- COLUMN C: POWER USER RISK & ALLOCATION ---

// 1. Find the specific budget for THIS category in the forecast
        const categoryBudget = forecastDataNextMonth.find(c => c.category_ID == subcatID);
        const catLimit = categoryBudget ? Math.abs(categoryBudget.amount) : 0;

// 2. Find the TOTAL budgeted expenses for the whole month
        const totalMonthlyBudget = forecastDataNextMonth
            .filter(c => (c.transactionType || 'expense').toLowerCase() !== 'income')
            .reduce((sum, c) => sum + Math.abs(c.amount), 0);

// 3. Calculate Percentages
        let absAmount = Math.abs(amount)
        const pctOfCategory = catLimit > 0 ? (absAmount / catLimit) * 100 : 0;
        const pctOfTotalBudget = totalMonthlyBudget > 0 ? (absAmount / totalMonthlyBudget) * 100 : 0;
        // Determine the action verb based on the amount
        const actionVerb = amount < 0 ? "saves" : "uses";
// Use the absolute value for display so we don't show "$-50.00"
        const displayAmount = Math.abs(amount).toFixed(2);

        let colRisk = `
    <h6 class="fw-bold text-uppercase small text-muted mb-3"><i class="bi bi-shield-exclamation me-2"></i>Budget Impact</h6>
    
    <div class="mb-3">
        <div class="d-flex justify-content-between x-small mb-1">
            <span>vs. <strong>${subcatName}</strong> Budget</span>
            <span class="fw-bold ${pctOfCategory > 100 ? 'text-danger' : ''}">${pctOfCategory.toFixed(1)}%</span>
        </div>
        <div class="progress" style="height: 6px;">
            <div class="progress-bar ${pctOfCategory > 100 ? 'bg-danger' : 'bg-success'}" 
                 style="width: ${Math.min(Math.abs(pctOfCategory), 100)}%"></div>
        </div>
        
        
        <p class="x-small text-muted mt-1">
            This <strong>${actionVerb}</strong> $${displayAmount} of your $${catLimit.toLocaleString()} monthly allocation.
        </p>
    </div>

    <div class="mb-3">
        <div class="d-flex justify-content-between x-small mb-1">
            <span>vs. <strong>Total Monthly</strong> Budget</span>
            <span class="fw-bold">${pctOfTotalBudget.toFixed(1)}%</span>
        </div>
        <div class="progress" style="height: 6px;">
            <div class="progress-bar bg-info" style="width: ${Math.min(Math.abs(pctOfTotalBudget), 100)}%"></div>
        </div>
    </div>
`;

        // --- 5. FINAL ASSEMBLY ---
        const finalHtml = `
            <div class="container-fluid py-2">
                <div class="row gx-5">
                    <div class="col-md-4 border-end">${colSchedule}</div>
                    <div class="col-md-4 border-end">${colSavings}</div>
                    <div class="col-md-4">${colRisk}</div>
                </div>
            </div>`;

        $detailsRow.find('.details-content').html(finalHtml);
        $detailsRow.removeClass('d-none');
        $icon.removeClass('bi-chevron-right').addClass('bi-chevron-down');
        $mainRow.addClass('table-light fw-bold');
    } else {
        $detailsRow.addClass('d-none');
        $icon.removeClass('bi-chevron-down').addClass('bi-chevron-right');
        $mainRow.removeClass('table-light fw-bold');
    }
}

function updateSubcategoryColor(selectElement) {
    const $select = $(selectElement);
    const selectedOption = $select.find('option:selected');
    const newColor = selectedOption.data('color');

    // 1. Try to find a pill within the same TD (works for table rows)
    let $pill = $select.closest('td').find('.subcategory-color-pill');

    // 2. If it's the "Quick Add" row, explicitly target its ID if the sibling find fails
    if ($select.attr('id') === 'new_subcategory') {
        $pill = $('#new_subcategory_color_pill');
    }

    if ($pill.length && newColor) {
        $pill.css('background-color', newColor);
    }
}

function updateSubcategoryColor(selectElement) {
    const $select = $(selectElement);
    const selectedOption = $select.find('option:selected');
    const newColor = selectedOption.data('color'); // Grab the color from the data attribute

    // Find the color pill in the same TD and update it
    const $pill = $select.closest('td').find('.subcategory-color-pill');
    if ($pill.length && newColor) {
        $pill.css('background-color', newColor);
    }
}

function sortTable(colIndex, type) {
    const tableBody = document.getElementById("plannedLineBody");
    const inputRow = document.getElementById("plannedInputRow");

    // Get all rows EXCEPT the input row
    let rows = Array.from(tableBody.querySelectorAll("tr:not(#plannedInputRow)"));

    // Toggle ASC/DESC if clicking the same column
    if (currentSortCol === colIndex) {
        isAsc = !isAsc;
    } else {
        isAsc = true;
        currentSortCol = colIndex;
    }

    rows.sort((a, b) => {
        let valA = getCellValue(a, colIndex, type);
        let valB = getCellValue(b, colIndex, type);

        if (valA === valB) return 0;

        if (isAsc) {
            return valA > valB ? 1 : -1;
        } else {
            return valA < valB ? 1 : -1;
        }
    });

    // Re-append sorted rows
    rows.forEach(row => {
        tableBody.appendChild(row); // Append the main row
        const uuid = $(row).data('id');
        const detailsRow = document.getElementById(`details-${uuid}`);
        if (detailsRow) tableBody.appendChild(detailsRow); // Append the details row right after it
    });

    // ALWAYS put the input row back at the very bottom
    tableBody.appendChild(inputRow);

    // Update Icons (Optional but helpful)
    updateSortIcons(colIndex, isAsc);
}

function getCellValue(row, index, type) {
    const td = row.cells[index];

    switch (type) {
        case 'number':
            // Strip $ and commas, return float
            return parseFloat(td.querySelector('input').value) || 0;
        case 'date':
            return td.querySelector('input').value; // YYYY-MM-DD strings sort naturally
        case 'select':
            // Sort by the TEXT of the selected option, not the ID
            const sel = td.querySelector('select');
            return sel.options[sel.selectedIndex].text.toLowerCase();
        case 'status':
            return td.querySelector('input[type="checkbox"]').checked ? 1 : 0;
        case 'text':
        default:
            return td.querySelector('input').value.toLowerCase();
    }
}

function updateSortIcons(activeIndex, isAsc) {
    $('#plannedTransactionTable thead i').removeClass('bi-sort-up bi-sort-down').addClass('bi-arrows-vertical text-muted');
    const icon = $('#plannedTransactionTable thead th').eq(activeIndex).find('i');
    icon.removeClass('bi-arrows-vertical text-muted').addClass(isAsc ? 'bi-sort-down' : 'bi-sort-up');
}

/**
 * Toggles an existing row between a finite number and Indefinite (-1)
 */
function setIndefinite(btn) {
    const $row = $(btn).closest('tr');
    const $inputContainer = $row.find('.occurrence-input-container');
    const $infiniteContainer = $row.find('.occurrence-infinite-container');
    const $input = $row.find('td[data-field="occurrences"] input');

    if ($input.val() != -1) {
        $input.val(-1);
        $inputContainer.addClass('d-none');
        $infiniteContainer.removeClass('d-none');
    } else {
        $input.val(1);
        $infiniteContainer.addClass('d-none');
        $inputContainer.removeClass('d-none');
        $input.prop('disabled', false).focus();
    }
    updatePlannedItem($row);
}

/**
 * Toggles the Quick Add row at the bottom of the table
 */
function setNewIndefinite(btn) {
    const $input = $('#new_occurrences');
    const $inputContainer = $('#new_occurrence_input_container');
    const $infiniteContainer = $('#new_occurrence_infinite_container');

    if ($input.val() != -1) {
        $input.val(-1);
        $inputContainer.addClass('d-none');
        $infiniteContainer.removeClass('d-none');
    } else {
        $input.val(1);
        $infiniteContainer.addClass('d-none');
        $inputContainer.removeClass('d-none');
        $input.focus();
    }
}

function showToast(message, isError = false) {
    const toastEl = document.getElementById('statusToast');
    const toastBody = document.getElementById('toastMessage');
    if (!toastEl) return;

    toastEl.classList.remove('bg-success', 'bg-danger');
    toastEl.classList.add(isError ? 'bg-danger' : 'bg-success');
    toastBody.innerText = message;

    const toast = new bootstrap.Toast(toastEl);
    toast.show();
}

/**
 * Core Logic for Row Updates - Orchestrates UI state before saving
 */
function updatePlannedItem($row, eventSource = null) {
    const $budgetSelect = $row.find('td[data-field="budget_ID"] select');
    const $amountInput = $row.find('td[data-field="amount"] input');
    const $dateInput = $row.find('td[data-field="start_date"] input');
    const $freqSelect = $row.find('td[data-field="times_per_year"] select');
    const $occInput = $row.find('td[data-field="occurrences"] input');

    const freq = $freqSelect.val();

    if (eventSource === 'budget' && $budgetSelect.val() !== "") {
        $.ajax({
            url: 'get-budget-details',
            type: 'GET',
            data: {budget_id: $budgetSelect.val()},
            success: function (data) {
                $amountInput.val(data.amount);
                $dateInput.val(data.start_date);
                $freqSelect.val("0");
                $occInput.val(1).prop('disabled', true).addClass('text-muted');
                $row.find('.occurrence-input-container').removeClass('d-none');
                $row.find('.occurrence-infinite-container').addClass('d-none');

                sendUpdateToServer($row);
            }
        });
    } else {
        if (freq === "0") {
            $occInput.val(1).prop('disabled', true).addClass('text-muted');
            $row.find('.occurrence-input-container').removeClass('d-none');
            $row.find('.occurrence-infinite-container').addClass('d-none');
        } else {
            if ($occInput.val() != -1) {
                $occInput.prop('disabled', false).removeClass('text-muted');
            }
        }
        sendUpdateToServer($row);
    }
}

/**
 * Handles the actual AJAX for UPDATING an existing row
 */
function sendUpdateToServer($row) {
    const rowData = {
        inputplanned_transactionplanned_transaction_ID: $row.data('id'),
        inputplanned_transactionsubcategory_ID: $row.find('td[data-field="subcategory_ID"] select').val(),
        inputplanned_transactionbudget_id: $row.find('td[data-field="budget_ID"] select').val(),
        inputplanned_transactionnickname: $row.find('td[data-field="nickname"] input').val(),
        inputplanned_transactionamount: $row.find('td[data-field="amount"] input').val(),
        inputplanned_transactionstart_date: $row.find('td[data-field="start_date"] input').val(),
        inputplanned_transactiontimes_per_year: $row.find('td[data-field="times_per_year"] select').val(),
        inputplanned_transactionoccurrences: $row.find('td[data-field="occurrences"] input').val(),
        inputplanned_transactionis_active: $row.find('td[data-field="is_active"] input').is(':checked') ? 1 : 0
    };

    $.ajax({
        url: 'updatePlannedTransaction',
        type: 'POST',
        data: rowData,
        success: function (response) {
            const errorMap = {
                "-1": "Session expired.",
                "-2": "Invalid ID.",
                "-3": "User Error.",
                "-4": "Invalid Subcategory.",
                "-5": "Invalid Budget.",
                "-6": "Nickname required.",
                "-7": "Invalid Amount.",
                "-8": "Invalid Date.",
                "-9": "Invalid Frequency.",
                "-10": "Invalid Occurrences.",
                "-11": "Status Error.",
                "-12": "Database Error.",
                "0": "No changes saved."
            };

            if (errorMap[response]) {
                showToast(errorMap[response], true);
                $row.css('background-color', 'rgba(220, 53, 69, 0.2)');
                setTimeout(() => $row.css('background-color', ''), 1000);
                return;
            }

            if (response === "1") {
                $row.css('background-color', 'rgba(25, 135, 84, 0.1)');
                setTimeout(() => $row.css('background-color', ''), 500);
            }
        },
        error: function () {
            showToast("Server Communication Error.", true);
        }
    });
}

function togglePlannedActive(element, uuid) {
    const isChecked = $(element).is(':checked');
    const statusValue = isChecked ? 1 : 0;
    const $row = $(element).closest('tr');

    $.ajax({
        url: 'update-planned-status',
        type: 'POST',
        data: {planned_transactionid: uuid, mode: statusValue},
        success: function () {
            if (statusValue === 0) {
                $row.addClass('row-inactive').css('opacity', '0.5');
                showToast("Transaction deactivated.");
            } else {
                $row.removeClass('row-inactive').css('opacity', '1');
                showToast("Transaction activated.");
            }
        },
        error: function () {
            $(element).prop('checked', !isChecked);
            showToast("Failed to update status.", true);
        }
    });
}

let rowToDelete = null;
let uuidToDelete = null;

function deletePlannedItem(btnElement, uuid) {
    rowToDelete = $(btnElement).closest('tr');
    uuidToDelete = uuid;
    const nickname = rowToDelete.find('td[data-field="nickname"] input').val();
    $('#deleteTargetName').text(nickname || "this transaction");

    const deleteModal = new bootstrap.Modal(document.getElementById('deleteConfirmModal'));
    deleteModal.show();
}

/**
 * Generates HTML for a freshly added row
 */
/**
 * Generates HTML for a freshly added row with subcategory color indicators.
 * @param {string} uuid - The unique ID returned from the server.
 * @param {object} data - The data object used to populate the row.
 */
function createNewRow(uuid, data) {
    const isInf = data.inputplanned_transactionoccurrences == -1;

    // Grab the color hex from the "Quick Add" dropdown to set the initial pill color
    const selectedSubColor = $('#new_subcategory option:selected').data('color') || '#cccccc';

    return `
    <tr data-id="${uuid}">
    <td>
            <button class="btn btn-sm btn-link p-0 text-decoration-none" onclick="toggleDetails(this)">
                <i class="bi bi-chevron-right toggle-icon"></i>
            </button>
        </td>
        <%-- Nickname --%>
        <td class="editable" data-field="nickname">
            <input type="text" class="form-control form-control-sm border-0 bg-transparent" 
                   value="${data.inputplanned_transactionnickname}" 
                   onblur="updatePlannedItem($(this).closest('tr'))">
        </td>

        <%-- Subcategory with Color Pill --%>
        <td class="editable-select d-flex align-items-center" data-field="subcategory_ID">
            <span class="subcategory-color-pill me-2" 
                  style="width: 12px; height: 12px; border-radius: 50%; display: inline-block; background-color: ${selectedSubColor}; border: 1px solid rgba(0,0,0,0.1);">
            </span>
            <select class="form-select form-select-sm table-edit-input border-0 bg-transparent flex-grow-1"
                    onchange="updateSubcategoryColor(this); updatePlannedItem($(this).closest('tr'))">
                ${$('#new_subcategory').html()} 
            </select>
        </td>

        <%-- Budget --%>
        <td class="editable-select" data-field="budget_ID">
            <select class="form-select form-select-sm border-0 bg-transparent" 
                    onchange="updatePlannedItem($(this).closest('tr'), 'budget')">
                ${$('#new_budget').html()}
            </select>
        </td>

        <%-- Amount --%>
        <td class="editable" data-field="amount">
            <div class="input-group input-group-sm">
                <span class="input-group-text bg-transparent border-0 pe-0">$</span>
                <input type="number" step="0.01" class="form-control form-control-sm border-0 bg-transparent fw-bold" 
                       value="${data.inputplanned_transactionamount}" 
                       onblur="updatePlannedItem($(this).closest('tr'))">
            </div>
        </td>

        <%-- Start Date --%>
        <td class="editable" data-field="start_date">
            <input type="date" class="form-control form-control-sm border-0 bg-transparent" 
                   value="${data.inputplanned_transactionstart_date}" 
                   onchange="updatePlannedItem($(this).closest('tr'))">
        </td>

        <%-- Frequency --%>
        <td data-field="times_per_year">
            <select class="form-select form-select-sm border-0 bg-transparent" 
                    onchange="updatePlannedItem($(this).closest('tr'))">
                <option value="12" ${data.inputplanned_transactiontimes_per_year == 12 ? 'selected' : ''}>Monthly</option>
                <option value="4" ${data.inputplanned_transactiontimes_per_year == 4 ? 'selected' : ''}>Quarterly</option>
                <option value="2" ${data.inputplanned_transactiontimes_per_year == 2 ? 'selected' : ''}>Semi-Annual</option>
                <option value="1" ${data.inputplanned_transactiontimes_per_year == 1 ? 'selected' : ''}>Annual</option>
                <option value="0" ${data.inputplanned_transactiontimes_per_year == 0 ? 'selected' : ''}>One-Time</option>
            </select>
        </td>

        <%-- Occurrences --%>
        <td class="editable" data-field="occurrences">
            <div class="occurrence-input-container ${isInf ? 'd-none' : ''}">
                <div class="input-group input-group-sm">
                    <input type="number" class="form-control border-0 bg-transparent" 
                           value="${data.inputplanned_transactionoccurrences}" 
                           onblur="updatePlannedItem($(this).closest('tr'))">
                    <button class="btn btn-outline-secondary border-0 p-1" type="button" onclick="setIndefinite(this)">
                        <i class="bi bi-arrow-repeat"></i>
                    </button>
                </div>
            </div>
            <div class="occurrence-infinite-container ${isInf ? '' : 'd-none'}">
                <button class="btn btn-sm btn-link text-primary p-0 text-decoration-none fw-bold" onclick="setIndefinite(this)">
                    <i class="bi bi-infinity"></i> Indefinite
                </button>
            </div>
        </td>

        <%-- Status --%>
        <td data-field="is_active">
            <div class="form-check form-switch">
                <input class="form-check-input" type="checkbox" checked 
                       onchange="togglePlannedActive(this, '${uuid}')">
            </div>
        </td>

        <%-- Action --%>
        <td>
            <button class="btn btn-sm btn-outline-danger border-0" 
                    onclick="deletePlannedItem(this, '${uuid}')">
                <i class="bi bi-trash"></i>
            </button>
        </td>
    </tr>
<tr id="details-${uuid}" class="details-row d-none bg-light">
        <td colspan="10"><div class="p-3 details-content"></div></td>
    </tr>`;
}

/* --- INITIALIZATION --- */
$(document).ready(function () {

    // Quick Add Budget listener
    $('#new_budget').on('change', function () {
        const budgetId = $(this).val().trim();
        if (budgetId !== "") {
            $.ajax({
                url: 'get-budget-details',
                type: 'GET',
                data: {budget_id: budgetId},
                success: function (data) {
                    $('#new_amount').val(data.amount);
                    $('#new_start_date').val(data.start_date);
                    $('#new_frequency').val("0").change();
                    $('#new_amount, #new_start_date, #new_frequency').prop('disabled', true);
                    showToast("Budget details applied.");
                },
                error: function () {
                    showToast("Could not retrieve budget details.", true);
                }
            });
        } else {
            $('#new_amount, #new_start_date, #new_frequency').prop('disabled', false);
        }
    });

    // Frequency Lock for Quick Add row
    $('#new_frequency').on('change', function () {
        const isOneTime = $(this).val() === "0";
        const $input = $('#new_occurrences');
        const $btn = $('#new_occurrence_input_container button');

        if (isOneTime) {
            $input.val(1).prop('disabled', true);
            $btn.prop('disabled', true);
            $('#new_occurrence_input_container').removeClass('d-none');
            $('#new_occurrence_infinite_container').addClass('d-none');
        } else {
            $input.prop('disabled', false);
            $btn.prop('disabled', false);
        }
    });

    // Delete Confirmation handler
    $('#confirmDeleteBtn').on('click', function () {
        if (!uuidToDelete) return;
        $.ajax({
            url: 'delete-planned_transaction',
            type: 'POST',
            data: {Planned_Transaction_ID: uuidToDelete},
            success: function () {
                bootstrap.Modal.getInstance(document.getElementById('deleteConfirmModal')).hide();
                rowToDelete.fadeOut(400, function () {
                    $(this).remove();
                    showToast("Transaction deleted.");
                });
            },
            error: function () {
                showToast("Error deleting transaction.", true);
            }
        });
    });

    // ADD BUTTON: Handles the Quick Add Row
    $('#addPlannedButton').on('click', function () {
        $('#new_amount, #new_start_date, #new_frequency').prop('disabled', false);
        const newData = {
            inputplanned_transactionnickname: $('#new_nickname').val(),
            inputplanned_transactionsubcategory_ID: $('#new_subcategory').val(),
            inputplanned_transactionbudget_id: $('#new_budget').val(),
            inputplanned_transactionamount: $('#new_amount').val(),
            inputplanned_transactionstart_date: $('#new_start_date').val(),
            inputplanned_transactiontimes_per_year: $('#new_frequency').val(),
            inputplanned_transactionoccurrences: $('#new_occurrences').val(),
            is_active: 1
        };
        $('#new_amount, #new_start_date, #new_frequency').prop('disabled', true);
        if (!newData.inputplanned_transactionnickname || !newData.inputplanned_transactionamount) {
            showToast("Please enter a nickname and amount.", true);
            return;
        }

        $.ajax({
            url: 'add-planned_transaction',
            type: 'POST',
            data: newData,
            success: function (response) {
                const addErrorMap = {
                    "-2": "Session Error: User ID not found.",
                    "-4": "Invalid Subcategory selection.",
                    "-5": "Invalid Budget selection.",
                    "-6": "Nickname is required.",
                    "-7": "Invalid Amount. Please enter a number.",
                    "-8": "Invalid Date format.",
                    "-9": "Invalid Frequency selection.",
                    "-10": "Invalid Occurrences value.",
                    "-12": "Database Error: Could not save.",
                    "0": "Transaction could not be added."
                };

                if (addErrorMap[response]) {
                    showToast(addErrorMap[response], true);
                    return;
                }

                // If success, response is the new UUID
                const newRowHtml = createNewRow(response, newData);
                const $newRow = $(newRowHtml).hide();

                // Inserts the new row right above the input row
                $('#plannedInputRow').before($newRow);
                $('#new_amount, #new_start_date, #new_frequency').prop('disabled', false);
                // Re-sync select values
                $newRow.find('td[data-field="subcategory_ID"] select').val(newData.inputplanned_transactionsubcategory_ID);
                $newRow.find('td[data-field="budget_ID"] select').val(newData.inputplanned_transactionbudget_id);

                // Animate and Scroll
                $newRow.fadeIn(600).addClass('table-success');
                $newRow[0].scrollIntoView({behavior: 'smooth', block: 'center'});
                setTimeout(() => $newRow.removeClass('table-success'), 2000);

                // Reset Inputs
                $('#new_nickname, #new_amount').val('');
                $('#new_frequency').val('12').change();
                $('#new_occurrence_input_container').removeClass('d-none');
                $('#new_occurrence_infinite_container').addClass('d-none');


                showToast("Transaction added!");
            },
            error: function () {
                showToast("Error adding transaction.", true);
            }
        });
    });
    // Set the initial color for the Quick Add dot
    updateSubcategoryColor($('#new_subcategory'));

    refreshForecastContext();
    // Initial check for existing One-Time rows
    $('#plannedLineBody tr').each(function () {
        const freq = $(this).find('td[data-field="times_per_year"] select').val();
        if (freq === "0") {
            $(this).find('td[data-field="occurrences"] input').val(1).prop('disabled', true).addClass('text-muted');
        }
    });
});