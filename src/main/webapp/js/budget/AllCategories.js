$(document).ready(function() {
    const pickers = {};

    function initPickerForPill($pill) {
        const id = $pill.data('id');
        const trigger = $pill.find('.wheel-canvas')[0];
        if (!trigger || pickers[id]) return;
        const initialColor = $pill.find('.color-swatch-trigger').css('background-color');
        pickers[id] = new iro.ColorPicker(trigger, {
            width: 150, color: initialColor,
            layout: [{ component: iro.ui.Wheel }, { component: iro.ui.Slider, options: { sliderType: 'hue' } }]
        });
    }

    $('.category-pill[data-id]').each(function() { initPickerForPill($(this)); });

    // --- RESTORED FUNCTIONS START ---

    window.updateParentCategory = function(id, parentId) {
        const $pill = $(`.category-pill[data-id="${id}"]`);

        // 1. Update Glow UI
        const $selectedOption = $pill.find('select option:selected');
        const type = $selectedOption.data('type') || '';

        $pill.removeClass('glow-income glow-investment glow-expense');
        if (type === 'income') $pill.addClass('glow-income');
        else if (type === 'investment') $pill.addClass('glow-investment');
        else if (type === 'expense') $pill.addClass('glow-expense');

        // 2. Get the Hex Color
        let hexColor;
        if (pickers[id]) {
            hexColor = pickers[id].color.hexString; // iro.js hex format: #FFFFFF
        } else {
            // Fallback: Convert rgb() to hex if picker isn't ready
            const rgb = $pill.find('.color-swatch-trigger').css('background-color');
            hexColor = rgbToHex(rgb);
        }

        sendUpdate($pill, hexColor);
    };

    async function sendUpdate($pill, hexColor) {
        const id = $pill.data('id');
        const name = $pill.find('.category-text').text().trim();
        const parentId = $pill.find('select').val();

        // Safety check: ensure hexColor always starts with #
        if (!hexColor.startsWith('#')) {
            hexColor = '#' + hexColor;
        }

        try {
            await fetch('editCategory', {
                method: 'POST',
                body: new URLSearchParams({
                    category_ID: id,
                    inputcategoryCategory_Name: name,
                    inputcategoryColor_id: hexColor, // Now guaranteed to be #XXXXXX
                    inputsub_categoryparent_category_id: parentId
                })
            });
        } catch (err) {
            console.error("Update failed:", err);
        }
    }

// Helper to convert rgb(r, g, b) to #rrggbb
    function rgbToHex(rgb) {
        if (!rgb || !rgb.startsWith('rgb')) return '#0d6efd'; // Default blue fallback
        const vals = rgb.match(/\d+/g);
        return "#" + vals.map(x => {
            const hex = parseInt(x).toString(16);
            return hex.length === 1 ? "0" + hex : hex;
        }).join("");
    }

    // --- RESTORED FUNCTIONS END ---

    window.updateAddPillIndicator = function(selectElement) {
        const type = selectElement.options[selectElement.selectedIndex].getAttribute('data-type');
        const $container = $('#new-pill-container');

        $container.removeClass('glow-income glow-investment glow-expense');
        if (type === 'income') $container.addClass('glow-income');
        else if (type === 'investment') $container.addClass('glow-investment');
        else if (type === 'expense') $container.addClass('glow-expense');
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
                    inputcategoryParent_id: parentId
                })
            });
            if (response.ok) location.reload();
        } catch (err) { console.error(err); }
    };

    const $parentSelect = $('#new-category-parent');
    if($parentSelect.length) window.updateAddPillIndicator($parentSelect[0]);
});

function updateAddPillColor(val) {
    $('#new-color-preview').css('background-color', val);
    $('#new-pill-container').css('border-left-color', val);
}