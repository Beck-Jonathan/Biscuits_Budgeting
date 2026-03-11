$(document).ready(function() {
    const pickers = {};

    // 1. Initialize iro.js for each PILL (Changed selector from .category-card-mini)
    $('.category-pill').each(function() {
        const $pill = $(this);
        const id = $pill.data('id');

        // Get initial color from the style attribute or computed CSS
        const initialColor = $pill.find('.color-swatch-trigger').css('background-color');

        pickers[id] = new iro.ColorPicker($pill.find('.wheel-canvas')[0], {
            width: 150,
            color: initialColor,
            layout: [
                { component: iro.ui.Wheel },
                { component: iro.ui.Slider, options: { sliderType: 'hue' } }
            ]
        });
    });

    // 2. Toggle the picker
    $(document).on('click', '.color-swatch-trigger', function(e) {
        e.stopPropagation();
        const $menu = $(this).siblings('.wheel-picker-container');
        const $parentPill = $(this).closest('.category-pill');

        // 1. Remove active state from all other pills and menus
        $('.wheel-picker-container').not($menu).removeClass('active');
        $('.category-pill').not($parentPill).removeClass('active-pill');

        // 2. Toggle active state for this pill and menu
        $menu.toggleClass('active');
        $parentPill.toggleClass('active-pill');
    });

    // 3. Click anywhere else to close pickers
    $(document).on('click', function() {
        $('.wheel-picker-container').removeClass('active');
        $('.category-pill').removeClass('active-pill');
    });

    // 4. Prevent clicks inside the picker from closing it
    $(document).on('click', '.wheel-picker-container', function(e) {
        e.stopPropagation();
    });

    // 5. AJAX Save on Color Apply
    $('.btn-save-color').on('click', function() {
        const $pill = $(this).closest('.category-pill');
        const id = $pill.data('id');
        const hex = pickers[id].color.hexString;

        // Update UI immediately
        $pill.find('.color-swatch-trigger').css('background-color', hex);
        // Also update the pill border color to match our sleek design
        $pill.css('border-left', '5px solid ' + hex);

        $pill.find('.wheel-picker-container').removeClass('active');

        sendUpdate($pill, hex);
    });

    // 6. AJAX Save on Text Blur
    $('.category-text').on('blur', function() {
        const $pill = $(this).closest('.category-pill');
        const id = $pill.data('id');
        // Fallback to initial color if picker isn't ready
        const hexColor = pickers[id] ? pickers[id].color.hexString : $pill.find('.color-swatch-trigger').css('background-color');

        sendUpdate($pill, hexColor);
    });

    // 7. NEW: AJAX Save on Parent Category Change
    window.updateParentCategory = function(categoryId, parentId) {
        const $pill = $(`.category-pill[data-id="${categoryId}"]`);
        const hexColor = pickers[categoryId] ? pickers[categoryId].color.hexString : $pill.find('.color-swatch-trigger').css('background-color');

        // We can reuse the sendUpdate logic by passing the new parent ID
        sendUpdate($pill, hexColor, parentId);
    };

    function sendUpdate($pill, hexColor, parentId = null) {
        const id = $pill.data('id');
        const name = $pill.find('.category-text').text().trim();

        // If parentId isn't passed, grab the current value from the dropdown
        const pId = parentId || $pill.find('select[name="inputsub_categoryparent_category_id"]').val();

        $.ajax({
            url: 'editCategory',
            method: 'POST',
            data: {
                category_ID: id,
                inputcategoryCategory_Name: name,
                inputcategoryColor_id: hexColor,
                inputsub_categoryparent_category_id: pId // Added parent ID to the payload
            },
            success: function() {
                console.log("Updated: " + name);
                // Visual feedback: brief green glow
                $pill.addClass('border-success');
                setTimeout(() => $pill.removeClass('border-success'), 1000);
            }
        });
    }
});