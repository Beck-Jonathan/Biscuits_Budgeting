$(document).ready(function() {
        let submitbutton = document.getElementById("submitButton")
        submitbutton.disabled=true;
        let total_errors=0;

        let Present_Value_error=0;
        let Future_Value_error=0;
        let Interest_Rate_error=0;
        let Monthly_Payment_error=0;
        let Extra_Payment_error=0;
        let Remaining_Term_error=0;

// to clean the field, then set event listener for validating the input for Present_Value
        var Present_Value_input= document.getElementById("inputmortgagePresent_Value");
        Present_Value_input.addEventListener('blur',function(){
                Present_Value_input.value = Present_Value_input.value.trim();
                if (Present_Value_input.value!=""&& $.isNumeric(Present_Value_input.value)){
                    $(Present_Value_input).addClass("ui-state-highlight");
                    $(Present_Value_input).removeClass("ui-state-error");
                    Present_Value_error=0;}
                else {
                    $(Present_Value_input).removeClass("ui-state-highlight");
                    $(Present_Value_input).addClass("ui-state-error");
                    Present_Value_error=1;}
                total_errors = Present_Value_error+ Present_Value_error+ Present_Value_error+ Present_Value_error+ Present_Value_error+ Present_Value_error+ Present_Value_error+ Present_Value_error;
                if (total_errors ==0){
                    submitbutton.disabled=false;
                } else {
                    submitbutton.disabled=true;
                }
            }
        );
// to clean the field, then set event listener for validating the input for Future_Value
        var Future_Value_input= document.getElementById("inputmortgageFuture_Value");
        Future_Value_input.addEventListener('blur',function(){
                Future_Value_input.value = Future_Value_input.value.trim();
                if (Future_Value_input.value!=""&& $.isNumeric(Future_Value_input.value)){
                    $(Future_Value_input).addClass("ui-state-highlight");
                    $(Future_Value_input).removeClass("ui-state-error");
                    Future_Value_error=0;}
                else {
                    $(Future_Value_input).removeClass("ui-state-highlight");
                    $(Future_Value_input).addClass("ui-state-error");
                    Future_Value_error=1;}
                total_errors = Future_Value_error+ Future_Value_error+ Future_Value_error+ Future_Value_error+ Future_Value_error+ Future_Value_error+ Future_Value_error+ Future_Value_error;
                if (total_errors ==0){
                    submitbutton.disabled=false;
                } else {
                    submitbutton.disabled=true;
                }
            }
        );
// to clean the field, then set event listener for validating the input for Interest_Rate
        var Interest_Rate_input= document.getElementById("inputmortgageInterest_Rate");
        Interest_Rate_input.addEventListener('blur',function(){
                Interest_Rate_input.value = Interest_Rate_input.value.trim();
                if (Interest_Rate_input.value!=""&& $.isNumeric(Interest_Rate_input.value)){
                    $(Interest_Rate_input).addClass("ui-state-highlight");
                    $(Interest_Rate_input).removeClass("ui-state-error");
                    Interest_Rate_error=0;}
                else {
                    $(Interest_Rate_input).removeClass("ui-state-highlight");
                    $(Interest_Rate_input).addClass("ui-state-error");
                    Interest_Rate_error=1;}
                total_errors = Interest_Rate_error+ Interest_Rate_error+ Interest_Rate_error+ Interest_Rate_error+ Interest_Rate_error+ Interest_Rate_error+ Interest_Rate_error+ Interest_Rate_error;
                if (total_errors ==0){
                    submitbutton.disabled=false;
                } else {
                    submitbutton.disabled=true;
                }
            }
        );
// to clean the field, then set event listener for validating the input for Monthly_Payment
        var Monthly_Payment_input= document.getElementById("inputmortgageMonthly_Payment");
        Monthly_Payment_input.addEventListener('blur',function(){
                Monthly_Payment_input.value = Monthly_Payment_input.value.trim();
                if (Monthly_Payment_input.value!=""&& $.isNumeric(Monthly_Payment_input.value)){
                    $(Monthly_Payment_input).addClass("ui-state-highlight");
                    $(Monthly_Payment_input).removeClass("ui-state-error");
                    Monthly_Payment_error=0;}
                else {
                    $(Monthly_Payment_input).removeClass("ui-state-highlight");
                    $(Monthly_Payment_input).addClass("ui-state-error");
                    Monthly_Payment_error=1;}
                total_errors = Monthly_Payment_error+ Monthly_Payment_error+ Monthly_Payment_error+ Monthly_Payment_error+ Monthly_Payment_error+ Monthly_Payment_error+ Monthly_Payment_error+ Monthly_Payment_error;
                if (total_errors ==0){
                    submitbutton.disabled=false;
                } else {
                    submitbutton.disabled=true;
                }
            }
        );
// to clean the field, then set event listener for validating the input for Extra_Payment
        var Extra_Payment_input= document.getElementById("inputmortgageExtra_Payment");
        Extra_Payment_input.addEventListener('blur',function(){
                Extra_Payment_input.value = Extra_Payment_input.value.trim();
                if (Extra_Payment_input.value!=""&& $.isNumeric(Extra_Payment_input.value)){
                    $(Extra_Payment_input).addClass("ui-state-highlight");
                    $(Extra_Payment_input).removeClass("ui-state-error");
                    Extra_Payment_error=0;}
                else {
                    $(Extra_Payment_input).removeClass("ui-state-highlight");
                    $(Extra_Payment_input).addClass("ui-state-error");
                    Extra_Payment_error=1;}
                total_errors = Extra_Payment_error+ Extra_Payment_error+ Extra_Payment_error+ Extra_Payment_error+ Extra_Payment_error+ Extra_Payment_error+ Extra_Payment_error+ Extra_Payment_error;
                if (total_errors ==0){
                    submitbutton.disabled=false;
                } else {
                    submitbutton.disabled=true;
                }
            }
        );
// to clean the field, then set event listener for validating the input for Remaining_Term
        var Remaining_Term_input= document.getElementById("inputmortgageRemaining_Term");
        Remaining_Term_input.addEventListener('blur',function(){
                Remaining_Term_input.value = Remaining_Term_input.value.trim();
                if (Remaining_Term_input.value!=""&& $.isNumeric(Remaining_Term_input.value)){
                    $(Remaining_Term_input).addClass("ui-state-highlight");
                    $(Remaining_Term_input).removeClass("ui-state-error");
                    Remaining_Term_error=0;}
                else {
                    $(Remaining_Term_input).removeClass("ui-state-highlight");
                    $(Remaining_Term_input).addClass("ui-state-error");
                    Remaining_Term_error=1;}
                total_errors = Remaining_Term_error+ Remaining_Term_error+ Remaining_Term_error+ Remaining_Term_error+ Remaining_Term_error+ Remaining_Term_error+ Remaining_Term_error+ Remaining_Term_error;
                if (total_errors ==0){
                    submitbutton.disabled=false;
                } else {
                    submitbutton.disabled=true;
                }
            }
        );

    }
)

