/**
 * Displays a toast notification based on the registry mapping.
 * @param {string} servletName - The key from ResponseRegistry (e.g., 'deleteBudget').
 * @param {string|number} responseCode - The result code received from the servlet (e.g., '-1', '1').
 */
function showToast(servletName, responseCode) {
    const container = document.getElementById('toastContainer');
    if (!container) return;

    // 1. Retrieve the message
    const servletMap = ResponseRegistry[servletName] || ResponseRegistry.shared;
    const message = servletMap[String(responseCode)] || ResponseRegistry.shared[String(responseCode)] || "Error: An unknown error occurred.";

    // 2. Determine Styling
    const prefix = message.split(':')[0].trim().toLowerCase();

    // Separate background, text, and close-button classes
    const styleMap = {
        'success': {bg: 'bg-success', text: 'text-black', btn: 'btn-close'},
        'warning': {bg: 'bg-warning', text: 'text-black', btn: 'btn-close'},
        'error': {bg: 'bg-danger', text: 'text-white', btn: 'btn-close-white'},
        'system error': {bg: 'bg-dark', text: 'text-white', btn: 'btn-close-white'},
        'unauthorized': {bg: 'bg-dark', text: 'text-white', btn: 'btn-close-white'},
        'working': {bg: 'bg-warning', text: 'text-black', btn: 'btn-close'}
    };

    // Default to a dark theme if no prefix match
    const styles = styleMap[prefix] || {bg: 'bg-secondary', text: 'text-white', btn: 'btn-close-white'};

    // 3. Create the Toast DOM
    const toastEl = document.createElement('div');
    // Apply bg and text classes to the outer wrapper
    toastEl.className = `toast align-items-center border-0 ${styles.bg} ${styles.text}`;
    toastEl.setAttribute('role', 'alert');
    toastEl.setAttribute('aria-live', 'assertive');
    toastEl.setAttribute('aria-atomic', 'true');

    // 4. Inject Dynamic HTML
    // Note: We use the dynamic 'styles.btn' variable here
    toastEl.innerHTML = `
        <div class="d-flex">
            <div class="toast-body">${message}</div>
            <button type="button" class="btn-close ${styles.btn} me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
    `;

    container.appendChild(toastEl);

    // Set timing
    const timeLimit = {'success': 1000, 'working': 1500}[prefix] || 6000;

    const toast = new bootstrap.Toast(toastEl, {delay: timeLimit});
    toast.show();

    toastEl.addEventListener('hidden.bs.toast', () => {
        toastEl.remove();
    });
}


const ResponseRegistry = {
    "activateBudget": {
        "-1": "Unauthorized: Session expired or access denied.",
        "-2": "Error: Invalid mode provided.",
        "-3": "Error: Could not set Budget ID.",
        "-4": "Error: Could not set User ID.",
        "-5": "System Error: Failed to deactivate budget.",
        "-6": "System Error: Failed to activate budget.",
        "1": "Success: Budget status updated."
    },
    "updatePlannedStatus": {
        "-1": "Unauthorized: Access denied.",
        "-2": "Error: Invalid Transaction ID or format.",
        "-3": "Error: User verification failed.",
        "-4": "System Error: Deactivation failed.",
        "-5": "System Error: Reactivation failed.",
        "-6": "Error: Invalid mode specified.",
        "1": "Success: Status updated successfully."
    },
    "addBudgetLineItem": {
        "-1": "Unauthorized: Session expired or invalid permissions.",
        "-2": "Error: Invalid Budget ID.",
        "-3": "Error: Invalid Category ID.",
        "-4": "Error: Invalid Name.",
        "-5": "Error: Invalid Details.",
        "-6": "Error: Invalid Date format.",
        "-7": "Error: Invalid Amount.",
        "-8": "Error: Invalid Type ID.",
        "-9": "Error: Invalid Status ID.",
        "-10": "System Error: Database insertion failed.",
        "0": "Error: The operation failed to complete."
    },
    "addBudget": {
        "-1": "Unauthorized: Session expired or invalid permissions.",
        "-2": "Error: Invalid User ID.",
        "-3": "Error: Invalid Name.",
        "-4": "Error: Invalid Details.",
        "-5": "Error: Invalid Start Date format.",
        "-6": "Error: Invalid Limit Amount.",
        "-7": "Error: Invalid Currency Code ID.",
        "-8": "System Error: Database insertion failed.",
        "-9": "Error: Budget could not be added."
    },
    "addTransactionCategory": {
        "-1": "Unauthorized: Session expired or invalid permissions.",
        "-2": "Error: Invalid Category Name.",
        "-3": "Error: Invalid Color ID.",
        "-4": "Error: Invalid Parent ID.",
        "-5": "Error: Invalid Projection Strategy ID.",
        "-10": "System Error: Database insertion failed.",
        "0": "Error: The operation failed to complete."
    },
    "addPlannedTransaction": {
        "-1": "Unauthorized: Session expired or invalid permissions.",
        "-2": "Error: Invalid User context.",
        "-4": "Error: Invalid Subcategory ID.",
        "-5": "Error: Invalid Budget ID.",
        "-6": "Error: Invalid Nickname.",
        "-7": "Error: Invalid Amount.",
        "-8": "Error: Invalid Start Date format.",
        "-9": "Error: Invalid Times per Year.",
        "-10": "Error: Invalid Occurrences.",
        "-12": "System Error: Database insertion failed."
    },
    "addSavedSearchOrderLine": {
        "-1": "Error: The operation failed to complete."
    },
    "analysisAjax": {
        "-1": "Unauthorized: Session expired or invalid permissions.",
        "-3": "System Error: AJAX data retrieval failed."
    },
    "analyzeCategoryAjax": {
        "-1": "Unauthorized: Access denied.",
        "-2": "System Error: Failed to fetch projection data."
    },
    "autoAssignColors": {
        "-1": "Unauthorized: Session expired or invalid permissions.",
        "-2": "Error: Invalid User ID.",
        "-3": "System Error: Failed to auto-assign colors."
    },
    "autoAssignProjections": {
        "-1": "Unauthorized: Session expired or invalid permissions.",
        "-2": "Error: Invalid User ID.",
        "-3": "System Error: Failed to auto-assign projection models."
    },
    "budgetAjax": {
        "-1": "Unauthorized: Session expired or invalid permissions.",
        "-2": "System Error: Failed to retrieve budget data."
    },
    "deleteBudgetLineItem": {
        "-1": "Unauthorized: Session expired or invalid permissions.",
        "-2": "Error: Invalid Budget Line Item ID.",
        "-3": "System Error: Database deletion failed.",
        "1": "Success: Item deleted successfully."
    },
    "deleteBudget": {
        "-1": "Unauthorized: Session expired or invalid permissions.",
        "-2": "System Error: Database deletion failed."
    },
    "deleteBudgetCategory": {
        "-1": "Error: Invalid Category ID.",
        "0": "Error: Unable to delete the specified category.",
        "uncategorized": "Warning: You cannot delete the 'Uncategorized' category."
    },
    "deletePlannedTransaction": {
        "-1": "Unauthorized: Session expired or invalid permissions.",
        "-2": "Error: Invalid Planned Transaction ID.",
        "-3": "Error: User verification failed.",
        "-4": "System Error: Database deletion failed.",
        "1": "Success: Planned transaction deleted successfully."
    },
    "editBudgetLineItem": {
        "-1": "Unauthorized: Session expired or invalid permissions.",
        "-2": "Error: Invalid Budget Line Item ID.",
        "-3": "Error: Invalid Category ID.",
        "-4": "Error: Invalid Name.",
        "-5": "Error: Invalid Details.",
        "-6": "Error: Invalid Date format.",
        "-7": "Error: Invalid Amount.",
        "-8": "Error: Invalid Budget Line Type ID.",
        "-9": "Error: Invalid Budget Line Status ID.",
        "-10": "System Error: Database update failed."
    },
    "updatePlannedTransaction": {
        "-1": "Unauthorized: Session expired or invalid permissions.",
        "-2": "Error: Invalid Planned Transaction ID.",
        "-3": "Error: User verification failed.",
        "-4": "Error: Invalid Subcategory ID.",
        "-5": "Error: Invalid Budget ID.",
        "-6": "Error: Invalid Nickname.",
        "-7": "Error: Invalid Amount.",
        "-8": "Error: Invalid Start Date format.",
        "-9": "Error: Invalid Times per Year.",
        "-10": "Error: Invalid Occurrences.",
        "-11": "Error: Invalid Active status.",
        "-12": "System Error: Database update failed.",
        "0": "Error: The operation failed to complete.",
        "1": "Success: Planned transaction updated successfully."
    },
    "editCategory": {
        "1": "Success: Category Updated",
        "0": "System Error:: Category Not Updated, check the lock on the subcategory",
        "-1": "Unauthorized: Session expired or invalid permissions.",
        "-2": "Error: Invalid Category ID.",
        "-3": "Error: Invalid Category Name.",
        "-4": "Error: Invalid Parent Category ID.",
        "-5": "Error: Invalid Projection Strategy ID.",
        "-6": "Error: Invalid Color ID.",
        "-7": "System Error: Database update failed."
    },
    "editTransaction": {
        "success": "Success: Transaction updated successfully.",
        "1": "Success: Transaction category updated successfully.",
        "0": "Error: The operation failed to complete."
    },
    "getBudgetDetails": {
        "401": "Unauthorized: Session expired or invalid permissions.",
        "-2": "Error: Missing or empty Budget ID.",
        "0": "Error: Budget not found.",
        "-12": "System Error: Failed to retrieve budget details."
    },
    "getCategoryPerformance": {
        "-1": "Unauthorized: Session expired or invalid permissions.",
        "-2": "System Error: Failed to retrieve category performance data."
    },
    "lockSubcategory": {
        "1": "Success, lock toggled.",
        "0": "Error, lock not toggled.",
        "-1": "Unauthorized: Session expired or invalid permissions.",
        "-2": "Error: Invalid Subcategory ID.",
        "-3": "Error: User verification failed.",
        "-4": "System Error: Failed to unlock subcategory.",
        "-5": "System Error: Failed to lock subcategory."
    },
    "lockTransaction": {
        "success": "Success: Transaction lock status toggled successfully.",
        "1": "Success: Transaction lock status toggled successfully.",
        "0": "Error: The operation failed to complete."
    },
    "pieChart": {
        "unauthorized": "Unauthorized: Session expired or invalid permissions.",
        "no_data": "Warning: No financial data found.",
        "system_error": "System Error: Exception during initialization."
    },
    "updateThreshold": {
        "-1": "Unauthorized: Session expired or invalid permissions.",
        "-2": "Error: Invalid Subcategory ID.",
        "-3": "Error: User verification failed.",
        "-4": "System Error: Database update failed.",
        "-5": "Error: Invalid Threshold amount.",
        "1": "Success: Threshold updated successfully."
    },
    "shared": {
        "2": "Working: Computing",
        "1": "Success: Operation completed.",
        "-1": "Unauthorized: Please log in again.",
        "-2": "Error: Invalid input provided.",
        "-3": "System Error: Database error, please contact support.",
        "-4": "Error: Item not found.",
        "-5": "Warning: Operation prohibited in current state.",
        "-6": "System Error: System unavailable."
    },
    "editSaved_Search_Order_Line": {
        "1": "Success: Search order line updated.",
        "-1": "Unauthorized: Access denied.",
        "-2": "Error: Invalid data provided.",
        "-3": "System Error: Database update failed.",
        "0": "Warning: No changes detected."
    }
};