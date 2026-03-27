$(document).ready(function() {
    // 1. Scope definition
    const pickers = {};
    let categoryIdToDelete = null;

    // 2. Event Listeners (Calling named functions)
    $("#btnAutoAssign").on("click", function () {
        const modalEl = document.getElementById('autoAssignModal');
        const autoModal = new bootstrap.Modal(modalEl);
        autoModal.show();
    });
    $(document).on('click', '.color-swatch-trigger', (e) => handleColorPickerToggle(e, pickers));
    $(document).on('click', '#confirmDeleteBtn', handleConfirmDelete);
    $(document).on('click', '.strategy-icon', (e) => handleStrategyClick(e, pickers));
    $(document).on('blur', '.category-text', (e) => handleNameBlur(e, pickers));

    // Global clicks to close popovers
    $(document).on('click', handleGlobalClick);
    $(document).on('click', '.picker-popover', (e) => e.stopPropagation());
    $("#confirmAutoAssignBtn").on("click", handleAutoAssignExecution);

    // 3. Initial UI Setup
    // Any logic that needs to run on load goes here
});

// --- FUNCTION DEFINITIONS (Implementation) ---

/**
 * Handles the Projection Engine Auto-Assign AJAX call
 */
function handleAutoAssign() {
    const $btn = $(this);
    const originalHtml = $btn.html();

    if (!confirm("This will analyze your spending history and automatically assign the best projection model to every category. Continue?")) {
        return;
    }

    $.ajax({
        url: 'autoAssignProjections',
        type: 'POST',
        beforeSend: function () {
            $btn.prop("disabled", true).addClass("btn-secondary").removeClass("btn-primary");
            $btn.html('<span class="spinner-border spinner-border-sm me-2"></span> Analyzing Data...');
        },
        success: function (response) {
            if (response == "1" || response == 1) {
                alert("Success! Your categories have been optimized based on historical trends.");
                location.reload();
            } else {
                alert("Analysis complete, but no changes were made. (Code: " + response + ")");
            }
        },
        error: function () {
            alert("Connection error. The projection engine could not be reached.");
        },
        complete: function () {
            $btn.prop("disabled", false).addClass("btn-primary").removeClass("btn-secondary");
            $btn.html(originalHtml);
        }
    });
}

/**
 * Handles iro.js Color Picker initialization and toggling
 */
function handleColorPickerToggle(e, pickers) {
    e.stopPropagation();
    const $this = $(e.currentTarget);
    const $card = $this.closest('.modern-cat-card');
    const id = $card.data('id');

    if (!id) return;

    const $popover = $this.siblings('.picker-popover');

    if ($popover.hasClass('d-none')) {
        $('.modern-cat-card').removeClass('card-on-top');
        $card.addClass('card-on-top');
    } else {
        $card.removeClass('card-on-top');
    }

    $('.picker-popover').not($popover).addClass('d-none');
    $popover.toggleClass('d-none');

    if (!pickers[id]) {
        const canvas = $popover.find('.wheel-canvas')[0];
        const initialColor = $this.css('background-color');

        pickers[id] = new iro.ColorPicker(canvas, {
            width: 150,
            color: initialColor,
            layout: [
                {component: iro.ui.Wheel},
                {component: iro.ui.Slider, options: {sliderType: 'hue'}}
            ]
        });

        pickers[id].on('color:change', function (color) {
            $card.find('.color-swatch-trigger').css('background-color', color.hexString);
            $card.find('.card-accent').css('background-color', color.hexString);
        });

        pickers[id].on('input:end', function (color) {
            sendUpdate($card, color.hexString, pickers);
        });
    }
}

/**
 * Handles Category Deletion via the Modal
 */
async function handleConfirmDelete() {
    const $btn = $(this);
    const originalText = $btn.text();
    $btn.prop('disabled', true).html('<span class="spinner-border spinner-border-sm"></span> Deleting...');

    try {
        const response = await fetch('deleteBudgetCategory', {
            method: 'POST',
            body: new URLSearchParams({categoryid: categoryIdToDelete})
        });

        if (response.ok) {
            location.reload();
        } else {
            alert("Error deleting category.");
            $btn.prop('disabled', false).text(originalText);
        }
    } catch (err) {
        console.error(err);
        $btn.prop('disabled', false).text(originalText);
    }
}

/**
 * Handles Strategy Icon Selection
 */
function handleStrategyClick(e, pickers) {
    e.stopPropagation();
    const $card = $(e.currentTarget).closest('.modern-cat-card');
    const newVal = $(e.currentTarget).data('val');
    $card.attr('data-strategy', newVal);

    const hex = getHexFromCard($card, pickers);
    sendUpdate($card, hex, pickers);
}

/**
 * Handles Name update when focus is lost
 */
function handleNameBlur(e, pickers) {
    const $card = $(e.currentTarget).closest('.modern-cat-card');
    const hex = getHexFromCard($card, pickers);
    sendUpdate($card, hex, pickers);
}

/**
 * Closes pickers when clicking outside
 */
function handleGlobalClick() {
    $('.picker-popover').addClass('d-none');
    $('.modern-cat-card').removeClass('card-on-top');
}

// --- HELPER & UTILITY FUNCTIONS ---

function getHexFromCard($card, pickers) {
    const id = $card.data('id');
    return pickers[id] ? pickers[id].color.hexString : rgbToHex($card.find('.card-accent').css('background-color'));
}

async function sendUpdate($card, hexColor, pickers) {
    const id = $card.data('id');
    const name = $card.find('.category-text').text().trim();
    const parentId = $card.find('select').val();
    const strategyId = $card.attr('data-strategy') || 'AVG_STRICT';

    if (!hexColor.startsWith('#')) hexColor = '#' + hexColor;

    try {
        await fetch('editCategory', {
            method: 'POST',
            body: new URLSearchParams({
                category_ID: id,
                inputcategoryCategory_Name: name,
                inputcategoryColor_id: hexColor,
                inputsub_categoryparent_category_id: parentId,
                inputsub_categoryprojection_strategy_ID: strategyId
            })
        });
    } catch (err) {
        console.error("Update failed:", err);
    }
}

function rgbToHex(rgb) {
    if (!rgb || !rgb.startsWith('rgb')) return '#0d6efd';
    const vals = rgb.match(/\d+/g);
    return "#" + vals.map(x => parseInt(x).toString(16).padStart(2, '0')).join("");
}

// Global window functions for inline HTML calls (unchanged)
window.confirmDeleteCategory = function (id, name) {
    categoryIdToDelete = id;
    $('#deleteTargetName').text(name);
    const modalEl = document.getElementById('deleteCategoryModal');
    if (window.bootstrap && window.bootstrap.Modal) {
        new bootstrap.Modal(modalEl).show();
    } else {
        $(modalEl).modal('show');
    }
};

window.updateParentCategory = function (id, parentId) {
    const $card = $(`.modern-cat-card[data-id="${id}"]`);
    const type = $card.find('select option:selected').data('type') || '';
    $card.removeClass('border-income border-investment border-expense border-transfer').addClass('border-' + type);
    sendUpdate($card, getHexFromCard($card, {}), {});
};

function handleAutoAssignExecution() {
    const $btn = $(this);
    const $modal = $('#autoAssignModal');
    const originalHtml = $btn.html();

    $.ajax({
        url: 'autoAssignProjections',
        type: 'POST',
        beforeSend: function () {
            // Disable interactions to prevent double-submits
            $btn.prop("disabled", true);
            $modal.find('.btn-close, .btn-light').addClass('d-none');
            $btn.html('<span class="spinner-border spinner-border-sm me-2"></span> Running Optimization...');
        },
        success: function (response) {
            // parse as int just in case it comes back as a string
            const result = parseInt(response);

            if (result >= 0) {
                // Success State
                $btn.removeClass('btn-primary').addClass('btn-success')
                    .html('<i class="bi bi-check-lg me-2"></i> Finished, ' + result + ' updates!');

                // Short delay so the user sees the "Success" state before the flash
                setTimeout(() => {
                    location.reload();
                }, 1000);
            } else {
                // Negative response = Error Code
                alert("The engine returned an error code: " + result);
                resetAutoModal($btn, originalHtml);
            }
        },
        error: function () {
            alert("The Projection Engine is temporarily unavailable.");
            resetAutoModal($btn, originalHtml);
        }
    });
}

/**
 * Helper to reset modal UI on error
 */
function resetAutoModal($btn, originalHtml) {
    $btn.prop("disabled", false).html(originalHtml);
    $('#autoAssignModal').find('.btn-close, .btn-light').removeClass('d-none');
}