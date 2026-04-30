/**
 * Executes the auto-assignment logic on the server.
 * @returns {Promise<string>} The server response code.
 */
async function apiAutoAssignProjections() {
    try {
        const response = await $.ajax({
            url: 'autoAssignProjections',
            type: 'POST'
        });
        return response;
    } catch (error) {
        throw new Error("Connection error. The projection engine could not be reached.");
    }
}

/**
 * Sends a request to the server to delete a budget category.
 * @param {string|number} id - The category ID to delete.
 * @returns {Promise<boolean>} - True if successful, false otherwise.
 */
async function apiDeleteCategory(id) {
    const response = await fetch('deleteBudgetCategory', {
        method: 'POST',
        body: new URLSearchParams({categoryid: id})
    });
    return response.ok;
}

/**
 * Persists the strategy change to the server.
 * @param {jQuery} $card - The card element.
 * @param {string} hex - The current card color.
 * @param {Object} pickers - The color picker instances.
 */
async function apiUpdateStrategy($card, hex, pickers) {
    return await sendUpdate($card, hex, pickers);
}

/**
 * Handles Strategy Icon Selection
 */

/**
 * Sends a request to the server to update category details.
 * @param {Object} data - The prepared data object for the API.
 * @returns {Promise<string>} - The result code from the server.
 */
async function apiUpdateCategory(data) {
    try {
        const response = await fetch('editCategory', {
            method: 'POST',
            body: new URLSearchParams(data)
        });
        return await response.text();
    } catch (err) {
        console.error("Critical AJAX failure:", err);
        return "-1"; // Standard error code for network failure
    }
}

/**
 * Triggers the projection optimization engine.
 * @returns {Promise<number>} The number of updates made or an error code.
 */
async function apiRunAutoAssign() {
    try {
        const response = await $.ajax({
            url: 'autoAssignProjections',
            type: 'POST'
        });
        return parseInt(response);
    } catch (error) {
        throw new Error("The Projection Engine is temporarily unavailable.");
    }
}

/**
 * Triggers the automatic color assignment engine.
 * @returns {Promise<number>} Number of color updates or error code.
 */
async function apiRunAutoColorAssign() {
    try {
        const response = await $.ajax({
            url: 'autoAssignColors',
            type: 'POST'
        });
        return parseInt(response);
    } catch (error) {
        throw new Error("The Color Assignment Engine is temporarily unavailable.");
    }
}

/**
 * Sends a request to lock or unlock a subcategory.
 * @param {string|number} id - The subcategory ID.
 * @param {number} mode - 1 for lock, 0 for unlock.
 * @returns {Promise<string>} - The server response code.
 */
async function apiLockSubcategory(id, mode) {
    try {
        return await $.ajax({
            url: 'LockSubcategory',
            type: 'POST',
            data: {subcategoryid: id, mode: mode}
        });
    } catch (err) {
        throw new Error("-6"); // General connection error code
    }
}

/**
 * Fetches projection and historical data for a specific subcategory.
 * @param {string|number} subcatId
 * @returns {Promise<Object>} The raw JSON data from the server.
 */
async function apiFetchProjectionData(subcatId) {
    try {
        const response = await $.ajax({
            url: 'AnalyzeCategoryAJAX',
            type: 'GET',
            data: {subcatId: subcatId},
            dataType: 'json'
        });

        if (response === "-1" || response === "-2") {
            showToast("budgetAjax", response);
            return null;
        }
        return response;
    } catch (err) {
        showToast("budgetAjax", -2);
        throw err;
    }
}


/**
 * Sends a request to create a new category.
 * @param {Object} data - The category details.
 * @returns {Promise<string>} - The UUID of the new category or an error code.
 */
async function apiAddCategory(data) {
    const response = await fetch('addTransactionCategory', {
        method: 'POST',
        body: new URLSearchParams(data)
    });
    return (await response.text()).trim();
}


/**
 * Fetches annual performance metrics for a specific category.
 * @param {string} id - The category ID.
 * @param {string|number} year - The selected year.
 * @returns {Promise<Object>} The performance data.
 */
async function apiFetchPerformanceData(id, year) {
    const response = await fetch(`getCategoryPerformance?id=${id}&year=${year}`);
    if (!response.ok) throw new Error("Failed to load performance metrics.");
    return await response.json();
}

/**
 * Sends a request to the server to update a subcategory's budget threshold.
 * @param {string} id - The subcategory ID.
 * @param {number} amount - The new budgeted amount.
 * @returns {Promise<string>} - The server response code.
 */
async function apiUpdateThreshold(id, amount) {
    try {
        return await $.ajax({
            url: 'updateThreshold',
            type: 'POST',
            data: {
                subcategoryid: id,
                amount: amount
            }
        });
    } catch (err) {
        throw new Error("Update failed");
    }
}

/**
 * Fetches the category grid HTML for a specific month and year.
 * @param {string|number} year - The selected year.
 * @param {string|number} month - The selected month.
 * @returns {Promise<string>} - The HTML response from the server.
 */
async function apiFetchCategoriesByDate(year, month) {
    try {
        return await $.ajax({
            url: 'all-Categories',
            type: 'GET',
            data: {
                year: year,
                month: month,
                refresh: true
            }
        });
    } catch (err) {
        throw new Error("Failed to load categories for the selected date.");
    }
}

