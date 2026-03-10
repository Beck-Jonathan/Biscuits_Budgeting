$(document).ready(function() {
    const pickers = {};

    // Initialize iro.js for each card
    $('.category-card-mini').each(function() {
        const $card = $(this);
        const id = $card.data('id');
        const initialColor = $card.find('.color-swatch-trigger').css('background-color');

        pickers[id] = new iro.ColorPicker($card.find('.wheel-canvas')[0], {
            width: 150,
            color: initialColor,
            layout: [{ component: iro.ui.Wheel }, { component: iro.ui.Slider }]
        });
    });

    // Toggle the picker
    $('.color-swatch-trigger').on('click', function(e) {
        e.stopPropagation();
        const $menu = $(this).siblings('.wheel-picker-container');
        $('.wheel-picker-container').not($menu).removeClass('active');
        $menu.toggleClass('active');
    });

    $(document).on('click', function() { $('.wheel-picker-container').removeClass('active'); });
    $('.wheel-picker-container').on('click', function(e) { e.stopPropagation(); });

    // AJAX Save on Color Apply
    $('.btn-save-color').on('click', function() {
        const $card = $(this).closest('.category-card-mini');
        const id = $card.data('id');
        const hex = pickers[id].color.hexString;

        $card.find('.color-swatch-trigger').css('background-color', hex);
        $card.find('.wheel-picker-container').removeClass('active');

        sendUpdate($card, hex);
    });

    // AJAX Save on Text Blur
    $('.category-text').on('blur', function() {
        const $card = $(this).closest('.category-card-mini');
        const id = $card.data('id');
        sendUpdate($card, pickers[id].color.hexString);
    });

    function sendUpdate($card, hexColor) {
        const id = $card.data('id');
        const name = $card.find('.category-text').text().trim();

        $.ajax({
            url: 'editCategory',
            method: 'POST',
            data: {
                category_ID: id, // Now sending the ID
                inputcategoryCategory_Name: name,
                inputcategoryColor_id: hexColor
            },
            success: function() {
                console.log("Updated Category ID " + id + " (" + name + ")");
            }
        });
    }
});