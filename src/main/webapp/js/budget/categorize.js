// Add this registry at the top of your file
const TOAST_CONFIG = {
    category: {success: "Category updated!", error: "Failed to update category."},
    lock: {success: "Transaction locked.", error: "Could not lock transaction."}
};

$(document).ready(function() {
    const baseUrl = $("#addr").attr("addr");
    console.log("Transaction JS Loaded. Base Address: " + baseUrl);

    // 1. Category Change Listener
    $(document).on('change', '.category', async function () {

        showToast("shared", 2);
        const transactionId = $(this).attr("id");
        const categoryValue = $(this).val();

        if (transactionId && categoryValue) {
            // Await the category update
            console.log("here")
            const success = await updateCategory(transactionId, categoryValue, baseUrl);
            console.log("there " + success)
            if (success) {
                // Auto-lock logic
                let lockBox = document.getElementById(transactionId + "_lock");
                console.log(lockBox)
                if (lockBox && !lockBox.checked) {
                    lockBox.click();
                }
            }
        }
    });

    // 2. Lock Toggle Listener
    $(document).on('change', '.lock', async function () {
        const fullId = $(this).attr("id");
        const transactionId = fullId.replace("_lock", "");

        await toggleLock(transactionId, baseUrl);
    });

    /**
     * Updates category via POST
     */
    async function updateCategory(id, cat, url) {


        try {
            const response = await fetch(`${url}/UTS`, {
                method: 'POST',
                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                body: `t_id=${id}&category=${cat}`
            });

            // Get the response code (e.g., "1" or "-1") directly from the servlet
            const responseCode = await response.text();
            console.log("resp code" + responseCode)
            // --- INTEGRATION POINT ---
            showToast('editTransaction', responseCode.trim());

            if (responseCode.trim() === "success") {
                return true;
            }
            return false;
        } catch (err) {
            showToast('editTransaction', '-1'); // Trigger generic error if fetch fails
            return false;
        }
    }

    /**
     * Toggles lock status via POST
     */
    async function toggleLock(id, url) {
        showToast("shared", 2);
        try {
            const response = await fetch(`${url}/LockTransaction`, {
                method: 'POST',
                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                body: `t_id=${id}`
            });

            const responseCode = await response.text();

            // --- INTEGRATION POINT ---
            showToast('lockTransaction', responseCode.trim());

        } catch (err) {
            showToast('lockTransaction', '-1');
        }
    }
});