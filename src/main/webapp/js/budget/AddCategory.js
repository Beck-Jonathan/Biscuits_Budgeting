$(document).ready(function() {
    setupColorPicker();
        let submitbutton = document.getElementById("submitButton")
        submitbutton.disabled=true;
        let total_errors=0;
        let category_name_error=0;

        let color_id_error=0;
// to clean the field, then set event listener for validating the input for category_id
        var category_name_input= document.getElementById("inputcategoryCategory_Name");
    category_name_input.addEventListener('blur',function(){
            category_name_input.value = category_name_input.value.trim();
                if (category_name_input.value!=""&& category_name_input.value.length>1 && category_name_input.value.length<=100){
                    $(category_name_input).addClass("ui-state-highlight");
                    $(category_name_input).removeClass("ui-state-error");
                    category_name_error=0;}
                else {
                    $(category_name_input).removeClass("ui-state-highlight");
                    $(category_name_input).addClass("ui-state-error");
                    category_name_error=1;}
                total_errors = category_name_error;
                if (total_errors ==0){
                    submitbutton.disabled=false;
                } else {
                    submitbutton.disabled=true;
                }
            }
        );
// to clean the field, then set event listener for validating the input for user_id


    })
    function setupColorPicker(){
        const initialColor = $('#inputcategoryColor_id').val();

        // 1. Initialize the single picker
        const formPicker = new iro.ColorPicker("#formWheelCanvas", {
            width: 180,
            color: initialColor,
            layout: [
                { component: iro.ui.Wheel },
                { component: iro.ui.Slider, options: { sliderType: 'hue' } }
            ]
        });

        // 2. Open/Toggle the picker
        $('#formColorSwatch').on('click', function(e) {
            e.stopPropagation();
            $('#formColorPickerContainer').toggleClass('active');
        });

        // 3. Update the UI and Hidden Input when the color changes
        formPicker.on('color:change', function(color) {
            const hex = color.hexString.toUpperCase();
            $('#formColorSwatch').css('background-color', hex);
            $('#formHexDisplay').text(hex);
            $('#inputcategoryColor_id').val(hex);
        });

        // 4. Close on "Done" button or clicking outside
        $('#btnConfirmColor').on('click', function() {
            $('#formColorPickerContainer').removeClass('active');
        });

        $(document).on('click', function(e) {
            if (!$(e.target).closest('.swatch-container').length) {
                $('#formColorPickerContainer').removeClass('active');
            }
        });

    }


