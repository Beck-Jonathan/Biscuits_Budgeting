$(document).ready(function() {
    const pickers = {};



    // Helper to initialize a color picker for a specific pill
    function initPickerForPill($pill) {
        const id = $pill.data('id');
        const trigger = $pill.find('.wheel-canvas')[0];
        if (!trigger || pickers[id]) return; // Skip if no canvas or already exists

        const initialColor = $pill.find('.color-swatch-trigger').css('background-color');

        pickers[id] = new iro.ColorPicker(trigger, {
            width: 150,
            color: initialColor,
            layout: [
                { component: iro.ui.Wheel },
                { component: iro.ui.Slider, options: { sliderType: 'hue' } }
            ]
        });
    }

    // Initialize existing pills
    $('.category-pill[data-id]').each(function() {
        initPickerForPill($(this));
    });

    // 2. Toggle the picker (Delegated)
    $(document).on('click', '.color-swatch-trigger', function(e) {
        e.stopPropagation();
        const $pill = $(this).closest('.category-pill');
        const $menu = $(this).siblings('.wheel-picker-container');

        $('.wheel-picker-container').not($menu).removeClass('active');
        $('.category-pill').not($pill).removeClass('active-pill');

        $menu.toggleClass('active');
        $pill.toggleClass('active-pill');
    });

    // 5. Save on Color Apply (Delegated)
    $(document).on('click', '.btn-save-color', function() {
        const $pill = $(this).closest('.category-pill');
        const id = $pill.data('id');
        const hex = pickers[id].color.hexString;

        $pill.find('.color-swatch-trigger').css('background-color', hex);
        $pill.css('border-left', '5px solid ' + hex);
        $pill.find('.wheel-picker-container').removeClass('active');

        sendUpdate($pill, hex);
    });

    // 6. Save on Text Blur (Delegated)
    $(document).on('blur', '.category-text', function() {
        const $pill = $(this).closest('.category-pill');
        const id = $pill.data('id');
        const hexColor = pickers[id] ? pickers[id].color.hexString : $pill.find('.color-swatch-trigger').css('background-color');
        sendUpdate($pill, hexColor);
    });

    // ... (Your existing sendUpdate and updateParentCategory functions) ...

    // Updated addNewCategory to include picker logic
    window.addNewCategory = async function() {
        const nameInput = document.getElementById('new-category-name');
        const colorInput = document.getElementById('new-category-color');
        const addWrapper = document.getElementById('add-pill-wrapper');
        const name = nameInput.value.trim();
        const color = colorInput.value;

        if (!name) return;

        try {
            const response = await fetch('addTransactionCategory', {
                method: 'POST',
                body: new URLSearchParams({ inputcategoryCategory_Name: name, inputcategoryColor_id: color })
            });

            if (response.ok) {
                const newId = await response.text();
                const optionsHtml = parentCategories.map(p => `<option value="${p.id}">${p.name}</option>`).join('');

                // Full HTML matching your existing structure
                const newPillHtml = `
                <div class="col mb-3 new-pill-animation">
                    <div class="category-pill shadow-sm border rounded-pill bg-white d-flex align-items-center p-1"
                         data-id="${newId}"
                         style="border-left: 5px solid ${color} !important;">
                        <div class="swatch-container ms-1">
                            <div class="color-swatch-trigger rounded-circle border-0"
                                 style="background-color: ${color}; width: 28px; height: 28px; cursor: pointer;">
                            </div>
                            <div class="wheel-picker-container shadow-lg border rounded p-3 bg-white">
                                <div class="wheel-canvas"></div>
                                <button class="btn btn-sm btn-dark btn-save-color w-100 mt-2">Save Color</button>
                            </div>
                        </div>
                        <div class="flex-grow-1 px-2 overflow-hidden">
                            <div class="category-text fw-bold text-truncate" contenteditable="true" style="outline: none; font-size: 0.9rem;">
                                ${name}
                            </div>
                        </div>
                        <div class="pe-2">
                            <select class="form-select form-select-sm border-0 bg-light rounded-pill px-2 text-muted"
                                    style="font-size: 0.7rem; width: auto; max-width: 100px;"
                                    onchange="updateParentCategory('${newId}', this.value)">
                                ${optionsHtml}
                            </select>
                        </div>
                    </div>
                </div>`;

                addWrapper.insertAdjacentHTML('beforebegin', newPillHtml);

                // CRITICAL: Initialize iro.js for the newly created element
                const $newPill = $(`.category-pill[data-id="${newId}"]`);
                initPickerForPill($newPill);

                nameInput.value = '';
                nameInput.focus();
            }
        } catch (err) { console.error(err); }
    };
});