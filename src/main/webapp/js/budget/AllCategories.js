$(document).ready(function() {
    // 1. Scope definition at the very top
    const pickers = {};
    // Variable to store the ID while the modal is open
    let categoryIdToDelete = null;

    // --- COLOR PICKER & SELECTOR LOGIC ---
    $(document).on('click', '.color-swatch-trigger', function (e) {
        e.stopPropagation();

        const $card = $(this).closest('.modern-cat-card');
        const id = $card.data('id');

        // Ignore "Add New" card which uses a standard <input type="color">
        if (!id) return;

        const $popover = $(this).siblings('.picker-popover');

        // Toggle Z-Index stacking to prevent neighboring cards from covering the picker
        if ($popover.hasClass('d-none')) {
            $('.modern-cat-card').removeClass('card-on-top');
            $card.addClass('card-on-top');
        } else {
            $card.removeClass('card-on-top');
        }

        // Close all other pickers before opening this one
        $('.picker-popover').not($popover).addClass('d-none');
        $popover.toggleClass('d-none');

        // Initialize iro.js if it hasn't been created for this ID yet
        if (!pickers[id]) {
            const canvas = $popover.find('.wheel-canvas')[0];
            const initialColor = $(this).css('background-color');

            pickers[id] = new iro.ColorPicker(canvas, {
                width: 150,
                color: initialColor,
                layout: [
                    {component: iro.ui.Wheel},
                    {component: iro.ui.Slider, options: {sliderType: 'hue'}}
                ]
            });

            pickers[id].on('color:change', function (color) {
                // Real-time visual feedback
                $card.find('.color-swatch-trigger').css('background-color', color.hexString);
                $card.find('.card-accent').css('background-color', color.hexString);
            });

            pickers[id].on('input:end', function (color) {
                sendUpdate($card, color.hexString);
            });
        }
    });

    //delete stuff
    window.confirmDeleteCategory = function (id, name) {
        categoryIdToDelete = id;
        $('#deleteTargetName').text(name);

        const modalEl = document.getElementById('deleteCategoryModal');

        // Check if we are in Bootstrap 5
        if (window.bootstrap && window.bootstrap.Modal) {
            const deleteModal = new bootstrap.Modal(modalEl);
            deleteModal.show();
        } else {
            // Fallback for Bootstrap 4 / jQuery
            $(modalEl).modal('show');
        }
    };

    $(document).on('click', '#confirmDeleteBtn', async function () {
        if (!categoryIdToDelete) return;

        // Visual feedback on the button
        const $btn = $(this);
        const originalText = $btn.text();
        $btn.prop('disabled', true).html('<span class="spinner-border spinner-border-sm"></span> Deleting...');

        try {
            const response = await fetch('deleteBudgetCategory', {
                method: 'POST',
                body: new URLSearchParams({categoryid: categoryIdToDelete})
            });

            if (response.ok) {
                location.reload(); // Refresh to show updated list
            } else {
                alert("Error deleting category. Please try again.");
                $btn.prop('disabled', false).text(originalText);
            }
        } catch (err) {
            console.error(err);
            $btn.prop('disabled', false).text(originalText);
        }
    });

    // Close popovers and reset stacking when clicking elsewhere
    $(document).on('click', function () {
        $('.picker-popover').addClass('d-none');
        $('.modern-cat-card').removeClass('card-on-top');
    });

    // Prevent clicks inside the popover from closing itself
    $(document).on('click', '.picker-popover', function (e) {
        e.stopPropagation();
    });

    // --- STRATEGY ICON CLICK ---
    $(document).on('click', '.strategy-icon', function (e) {
        e.stopPropagation();
        const $card = $(this).closest('.modern-cat-card');
        const newVal = $(this).data('val');

        $card.attr('data-strategy', newVal);

        const hex = pickers[$card.data('id')]
            ? pickers[$card.data('id')].color.hexString
            : rgbToHex($card.find('.card-accent').css('background-color'));

        sendUpdate($card, hex);
    });

    // --- NAME UPDATE (ContentEditable) ---
    $(document).on('blur', '.category-text', function () {
        const $card = $(this).closest('.modern-cat-card');
        const hex = pickers[$card.data('id')]
            ? pickers[$card.data('id')].color.hexString
            : rgbToHex($card.find('.card-accent').css('background-color'));

        sendUpdate($card, hex);
    });

    // --- PARENT SELECTOR UPDATE ---
    window.updateParentCategory = function (id, parentId) {
        const $card = $(`.modern-cat-card[data-id="${id}"]`);
        const type = $card.find('select option:selected').data('type') || '';

        // Update the bottom indicator border
        $card.removeClass('border-income border-investment border-expense border-transfer');
        if (type) $card.addClass('border-' + type);

        const hex = pickers[id]
            ? pickers[id].color.hexString
            : rgbToHex($card.find('.card-accent').css('background-color'));

        sendUpdate($card, hex);
    };

    // --- AJAX PERSISTENCE ---
    async function sendUpdate($card, hexColor) {
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


    // --- ADD NEW CATEGORY LOGIC ---
    window.updateAddPillColor = function (val) {
        $('#new-color-preview').css('background-color', val);
        $('#new-card-accent').css('background-color', val);
    };

    window.updateAddPillIndicator = function(selectElement) {
        const type = $(selectElement).find('option:selected').data('type');
        const $container = $('#new-pill-container');
        $container.removeClass('border-income border-expense border-investment border-transfer');
        if (type) $container.addClass('border-' + type);
    };

    window.addNewCategory = async function() {
        const name = $('#new-category-name').val().trim();
        const color = $('#new-category-color').val();
        const parentId = $('#new-category-parent').val();
        if (!name) return;

        try {
            const response = await fetch('addTransactionCategory', {
                method: 'POST',
                body: new URLSearchParams({
                    inputcategoryCategory_Name: name,
                    inputcategoryColor_id: color,
                    inputcategoryParent_id: parentId,
                    inputsub_categoryprojection_strategy_ID: 'AVG_STRICT'
                })
            });
            if (response.ok) location.reload();
        } catch (err) { console.error(err); }
    };

    // Helper to convert computed RGB styles to hex for the database
    function rgbToHex(rgb) {
        if (!rgb || !rgb.startsWith('rgb')) return '#0d6efd';
        const vals = rgb.match(/\d+/g);
        return "#" + vals.map(x => parseInt(x).toString(16).padStart(2, '0')).join("");
    }
});