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
        // Essential: This script updates the hidden field that the Servlet reads
        document.getElementById('colorSource').addEventListener('input', function(e) {
            const newColor = e.target.value; // This will be #RRGGBB
            document.getElementById('inputcategoryColor_id').value = newColor;
            document.getElementById('hexDisplay').innerText = newColor;
        });
    }


