$(document).ready(function() {
        let submitbutton = document.getElementById("submitButton")
        submitbutton.disabled=true;
        let total_errors=0;

        let name_error=0;
        let details_error=0;
        let start_date_error=0;
        let limit_amount_error=0;
        let currency_code_id_error=0;
        let is_active_error=0;


// to clean the field, then set event listener for validating the input for name
        var name_input= document.getElementById("inputbudgetname");
        name_input.addEventListener('blur',function(){
                name_input.value = name_input.value.trim();
                if (name_input.value!=""&& name_input.value.length>1 && name_input.value.length<=50){
                    $(name_input).addClass("ui-state-highlight");
                    $(name_input).removeClass("ui-state-error");
                    name_error=0;}
                else {
                    $(name_input).removeClass("ui-state-highlight");
                    $(name_input).addClass("ui-state-error");
                    name_error=1;}
                total_errors =  name_error+ details_error+ start_date_error+ limit_amount_error+ currency_code_id_error;
                if (total_errors ==0){
                    submitbutton.disabled=false;
                } else {
                    submitbutton.disabled=true;
                }
            }
        );
// to clean the field, then set event listener for validating the input for details
        var details_input= document.getElementById("inputbudgetdetails");
        details_input.addEventListener('blur',function(){
                details_input.value = details_input.value.trim();
                if (details_input.value!=""&& details_input.value.length>1 && details_input.value.length<=255){
                    $(details_input).addClass("ui-state-highlight");
                    $(details_input).removeClass("ui-state-error");
                    details_error=0;}
                else {
                    $(details_input).removeClass("ui-state-highlight");
                    $(details_input).addClass("ui-state-error");
                    details_error=1;}
            total_errors =  name_error+ details_error+ start_date_error+ limit_amount_error+ currency_code_id_error;
                if (total_errors ==0){
                    submitbutton.disabled=false;
                } else {
                    submitbutton.disabled=true;
                }
            }
        );
// to clean the field, then set event listener for validating the input for start_date
        var start_date_input= document.getElementById("inputbudgetstart_date");
        start_date_input.addEventListener('blur',function(){
                start_date_input.value = start_date_input.value.trim();
            if (start_date_input.value != "" && !isNaN(Date.parse(start_date_input.value))){
                    $(start_date_input).addClass("ui-state-highlight");
                    $(start_date_input).removeClass("ui-state-error");
                    start_date_error=0;}
                else {
                    $(start_date_input).removeClass("ui-state-highlight");
                    $(start_date_input).addClass("ui-state-error");
                    start_date_error=1;}
            total_errors =  name_error+ details_error+ start_date_error+ limit_amount_error+ currency_code_id_error;
                if (total_errors ==0){
                    submitbutton.disabled=false;
                } else {
                    submitbutton.disabled=true;
                }
            }
        );
// to clean the field, then set event listener for validating the input for limit_amount
        var limit_amount_input= document.getElementById("inputbudgetlimit_amount");
        limit_amount_input.addEventListener('blur',function(){
                limit_amount_input.value = limit_amount_input.value.trim();
                if (limit_amount_input.value!=""&& $.isNumeric(limit_amount_input.value)){
                    $(limit_amount_input).addClass("ui-state-highlight");
                    $(limit_amount_input).removeClass("ui-state-error");
                    limit_amount_error=0;}
                else {
                    $(limit_amount_input).removeClass("ui-state-highlight");
                    $(limit_amount_input).addClass("ui-state-error");
                    limit_amount_error=1;}
            total_errors =  name_error+ details_error+ start_date_error+ limit_amount_error+ currency_code_id_error;
                if (total_errors ==0){
                    submitbutton.disabled=false;
                } else {
                    submitbutton.disabled=true;
                }
            }
        );
// to clean the field, then set event listener for validating the input for currency_code_id
        var currency_code_id_input= document.getElementById("inputbudgetcurrency_code_id");
        currency_code_id_input.addEventListener('blur',function(){
                currency_code_id_input.value = currency_code_id_input.value.trim();
                if (currency_code_id_input.value!=""&& currency_code_id_input.value.length>1 && currency_code_id_input.value.length<=3){
                    $(currency_code_id_input).addClass("ui-state-highlight");
                    $(currency_code_id_input).removeClass("ui-state-error");
                    currency_code_id_error=0;}
                else {
                    $(currency_code_id_input).removeClass("ui-state-highlight");
                    $(currency_code_id_input).addClass("ui-state-error");
                    currency_code_id_error=1;}
            total_errors =  name_error+ details_error+ start_date_error+ limit_amount_error+ currency_code_id_error;
                if (total_errors ==0){
                    submitbutton.disabled=false;
                } else {
                    submitbutton.disabled=true;
                }
            }
        );
// to clean the field, then set event listener for validating the input for is_active



    }
)

