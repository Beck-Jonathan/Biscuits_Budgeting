$(document).ready(function () {
    // 1. Initial UI Setup
    normalizeHeight();
    tooltips();

    // 2. Toggle Active/Inactive Status
    $(".status-badge").on("click", function () {
        const $badge = $(this);
        const budgetId = $badge.data("id");
        const isCurrentlyActive = $badge.data("active");
        const targetMode = isCurrentlyActive ? 0 : 1;

        $.ajax({
            url: 'activateBudget',
            type: 'POST',
            data: {budgetid: budgetId, mode: targetMode},
            beforeSend: () => {
                $badge.css("pointer-events", "none")
                    .html('<span class="spinner-border spinner-border-sm"></span>');
            },
            success: (response) => {
                if (response == "1") {
                    // Success: Update UI state
                    const isActive = (targetMode === 1);
                    $badge.removeClass(isActive ? "bg-secondary" : "bg-success")
                        .addClass(isActive ? "bg-success" : "bg-secondary")
                        .text(isActive ? "Active" : "Inactive")
                        .data("active", isActive);
                } else {
                    // Logic Error (e.g. Database failure)
                    showToast('activateBudget', response);
                    // Reset to original text (if needed, or handle via specific error response)
                    $badge.text(isCurrentlyActive ? "Active" : "Inactive");
                }
            },
            error: () => {
                showToast('activateBudget', '-5'); // Generic system error
                $badge.text(isCurrentlyActive ? "Active" : "Inactive");
            },
            complete: () => {
                $badge.css("pointer-events", "auto");
            }
        });
    });

    // 3. Delete Button Logic (using jQuery UI Dialog)
    $("#dialog").dialog({autoOpen: false, modal: true, width: 450, resizable: false});

    $(".delButton").on("click", function(e) {
        e.preventDefault();

        const $card = $(this).closest('.budget_card');
        const budgetId = $(this).data("id");
        const budgetName = $card.find('.card-title').text().trim();
        const budgetLimit = $card.find('.progress').prev().find('span:last').text().trim();

        $("#dialog")
            .html(`
                <div class="p-2">
                    <p>Are you sure you want to delete this budget?</p>
                    <ul class="list-unstyled">
                        <li><strong>Name:</strong> ${budgetName}</li>
                        <li><strong>Amount:</strong> ${budgetLimit}</li>
                    </ul>
                    <p class="text-danger small"><i class="bi bi-exclamation-triangle"></i> This action cannot be undone.</p>
                </div>`)
            .dialog({
                title: "Confirm Deletion",
                hide: {effect: "explode", duration: 300},
                show: {effect: "fade", duration: 200},
                buttons: {
                    "Delete For Real": function () {
                        const dialogSelf = $(this);
                        $.ajax({
                            url: 'deleteBudget',
                            data: {budgetid: budgetId},
                            type: 'GET',
                            success: (response) => {
                                if (response == "1") {
                                    $card.slideUp(400, () => {
                                        $card.remove();
                                        normalizeHeight();
                                    });
                                    dialogSelf.dialog("close");
                                } else {
                                    showToast('deleteBudget', response);
                                    dialogSelf.dialog("close");
                                }
                            },
                            error: () => {
                                showToast('deleteBudget', '-2');
                                dialogSelf.dialog("close");
                            }
                        });
                    },
                    "Let It Stay": function () {
                        $(this).dialog("close");
                    }
                }
            }).dialog("open");
    });
});

/**
 * Normalizes heights of cards.
 * Note: If using Bootstrap 5, consider using flexbox classes
 * (e.g., 'd-flex align-items-stretch') on your row container instead.
 */
function normalizeHeight() {
    const $cards = $(".budget_card");
    $cards.css("height", "auto");

    let maxHeight = 0;
    $cards.each(function() {
        maxHeight = Math.max(maxHeight, $(this).outerHeight());
    });
    $cards.css("height", maxHeight + "px");
}

function tooltips() {
    const tooltipTriggerList = document.querySelectorAll('[data-bs-toggle="tooltip"]');
    [...tooltipTriggerList].map(el => new bootstrap.Tooltip(el));
}