$(document).ready(function() {
    console.log("Transaction JS Loaded. Base Address: " + document.getElementById("addr").getAttribute("addr"));

    // 1. Category Change Listener
    $(document).on('change', '.category', function () {
        let categoryValue = $(this).val();
        let transactionId = $(this).attr("id");
        let address = $("#addr").attr("addr");

        console.log("Category Change detected for ID: " + transactionId + " to Value: " + categoryValue);

        if (transactionId && categoryValue) {
            takevalues(transactionId, categoryValue, address);

            // Auto-lock logic: If user changes category, we usually want to lock it
            let lockBox = document.getElementById(transactionId + "_lock");
            if (lockBox && !lockBox.checked) {
                lockBox.click(); // This triggers the .lock change listener below
            }
        }
    });

    // 2. Lock Toggle Listener
    $(document).on('change', '.lock', function () {
        let fullId = $(this).attr("id"); // e.g., "uuid_lock"
        let transactionId = fullId.replace("_lock", "");
        let address = $("#addr").attr("addr");

        console.log("Lock Toggle detected for ID: " + transactionId);
        lock(transactionId, address);
    });

    function takevalues(x, cat, url) {
        let statusDiv = document.getElementById(x + "_status");
        if (!statusDiv) return;

        $(statusDiv).html("<h5>&#9202;</h5>").slideDown();

        const xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function () {
            if (xhr.readyState == XMLHttpRequest.DONE) {
                statusDiv.innerHTML = "<h5>&#x2705;</h5>";
                statusDiv.style.color = "green";
                setTimeout(() => $(statusDiv).slideUp(1000), 2000);
            }
        }
        xhr.open("POST", url + "/UTS", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.send("t_id=" + x + "&category=" + cat);
    }

    function lock(x, url) {
        // Find the animation container or the checkbox parent
        const xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if (xhr.readyState == XMLHttpRequest.DONE) {
                console.log("Lock confirmed for: " + x);
                // Optional: add visual confirmation here
            }
        }
        xhr.open("POST", url + "/LockTransaction", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.send("t_id=" + x);
    }
});