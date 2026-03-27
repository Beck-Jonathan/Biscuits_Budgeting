$(document).ready(function() {
    // 1. Initial UI Setup
    normalizeHeight();
    tooltips();
    // 4. Toggle Active/Inactive Status
    $(".status-badge").on("click", function () {
        var $badge = $(this);
        var budgetId = $badge.data("id");
        var isCurrentlyActive = $badge.data("active");
        var targetMode = isCurrentlyActive ? 0 : 1;

        // Store the original text to restore it if there's an error
        var originalText = $badge.text().trim();

        $.ajax({
            url: 'activateBudget',
            type: 'POST',
            data: {
                budgetid: budgetId,
                mode: targetMode
            },
            beforeSend: function () {
                // Disable clicks and show spinner
                $badge.css("pointer-events", "none");
                $badge.html('<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>');
            },
            success: function (response) {
                if (response == "1" || response == 1) {
                    // Update state based on targetMode
                    if (targetMode === 1) {
                        $badge.removeClass("bg-secondary").addClass("bg-success").text("Active");
                        $badge.data("active", true);
                    } else {
                        $badge.removeClass("bg-success").addClass("bg-secondary").text("Inactive");
                        $badge.data("active", false);
                    }
                } else {
                    // Restore original state on logical error
                    $badge.text(originalText);
                    alert("Server Error: " + response);
                }
            },
            error: function () {
                $badge.text(originalText);
                alert("Connection failed.");
            },
            complete: function () {
                // Re-enable clicks
                $badge.css("pointer-events", "auto");
            }
        });
    });

    // 2. Pre-initialize the dialog container (hidden by default)
    $("#dialog").dialog({
        autoOpen: false,
        modal: true,
        width: 450,
        height: "auto", // Responsive height based on content
        resizable: false
    });

    // 3. Delete Button Logic
    $(".delButton").on("click", function(e) {
        e.preventDefault();

        // Get ID and DOM references
        var budgetId = $(this).data("id");
        var $cardContainer = $("#" + budgetId + "Card");
        var $card = $(this).closest('.budget_card');

        // Extract data for the confirmation popup
        var budgetName = $card.find('.card-title').text().trim();
        // Grabs the text from the "Limit" span
        var budgetLimit = $card.find('.progress').prev().find('span:last').text().trim();

        // Construct the confirmation message
        var confirmationHtml = `
            <div class="p-2">
                <p>Are you sure you want to delete this budget?</p>
                <ul class="list-unstyled">
                    <li><strong>Name:</strong> ${budgetName}</li>
                    <li><strong>Amount:</strong> ${budgetLimit}</li>
                </ul>
                <p class="text-danger small"><i class="bi bi-exclamation-triangle"></i> This action cannot be undone.</p>
            </div>`;

        var $dialog = $("#dialog");
        $dialog.html(confirmationHtml);

        // Configure and open the dialog
        $dialog.dialog({
            title: "Confirm Deletion",
            hide: { effect: "explode", duration: 300 },
            show: { effect: "fade", duration: 200 },
            buttons: {
                "Delete For Real": function() {
                    var dialogSelf = this;

                    $.ajax({
                        url: 'deleteBudget',
                        data: { budgetid: budgetId },
                        type: 'GET',
                        success: function(response) {
                            // Assuming '1' is success from your backend
                            if (response == 1 || response == "1") {
                                $cardContainer.slideUp(400, function() {
                                    $(this).remove();
                                    normalizeHeight(); // Recalculate heights after one disappears
                                });
                            } else {
                                $cardContainer.addClass("border border-danger animate__animated animate__shakeX");
                                alert("Server could not delete this item.");
                            }
                        },
                        error: function() {
                            alert("Connection error. Please try again.");
                        },
                        complete: function() {
                            $(dialogSelf).dialog("close");
                        }
                    });
                },
                "Let It Stay": function() {
                    $(this).dialog("close");
                }
            }
        });

        $dialog.dialog("open");
    });
});

/**
 * Ensures all budget cards in a row have equal height for a clean UI.
 */
function normalizeHeight() {
    var $cards = $(".budget_card");
    $cards.css("height", "auto"); // Reset heights first

    var maxHeight = 0;
    $cards.each(function() {
        var thisHeight = $(this).outerHeight();
        if (thisHeight > maxHeight) {
            maxHeight = thisHeight;
        }
    });

    $cards.css("height", maxHeight + "px");
}

/**
 * Initializes Bootstrap 5 Tooltips for the progress bars.
 */
function tooltips() {
    if (typeof bootstrap !== 'undefined' && bootstrap.Tooltip) {
        var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
        tooltipTriggerList.map(function (tooltipTriggerEl) {
            return new bootstrap.Tooltip(tooltipTriggerEl);
        });
    } else {
        console.warn("Bootstrap Tooltip library not found.");
    }
}