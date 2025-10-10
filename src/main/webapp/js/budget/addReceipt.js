$(document).ready(function() {
        let submitbutton = document.getElementById("submitButton")
        submitbutton.disabled=true;
        let total_errors=0;
        let Transaction_ID_error=0;
        let Image_Link_error=0;
        let Name_error=0;
        let Description_error=0;
// to clean the field, then set event listener for validating the input for Transaction_ID
        var Transaction_ID_input= document.getElementById("inputreceiptTransaction_ID");
        Transaction_ID_input.addEventListener('blur',function(){
                Transaction_ID_input.value = Transaction_ID_input.value.trim();
                if (Transaction_ID_input.value!=""&& Transaction_ID_input.value.length>1 && Transaction_ID_input.value.length<=36){
                    $(Transaction_ID_input).addClass("ui-state-highlight");
                    $(Transaction_ID_input).removeClass("ui-state-error");
                    Transaction_ID_error=0;
                }
                else {
                    $(Transaction_ID_input).removeClass("ui-state-highlight");
                    $(Transaction_ID_input).addClass("ui-state-error");
                    Transaction_ID_error=1;
                }
                total_errors = Transaction_ID_error+ Image_Link_error+ Name_error+  Description_error;
                if (total_errors ==0){
                    submitbutton.disabled=false;
                } else {
                    submitbutton.disabled=true;
                }
            }
        );

// to clean the field, then set event listener for validating the input for Name
        var Name_input= document.getElementById("inputreceiptName");
        Name_input.addEventListener('blur',function(){
                Name_input.value = Name_input.value.trim();
                if (Name_input.value!=""&& Name_input.value.length>1 && Name_input.value.length<=255){
                    $(Name_input).addClass("ui-state-highlight");
                    $(Name_input).removeClass("ui-state-error");
                    Name_error=0;
                }
                else {
                    $(Name_input).removeClass("ui-state-highlight");
                    $(Name_input).addClass("ui-state-error");
                    Name_error=1;
                }
            total_errors = Transaction_ID_error+ Image_Link_error+ Name_error+  Description_error;
                if (total_errors ==0){
                    submitbutton.disabled=false;
                } else {
                    submitbutton.disabled=true;
                }
            }
        );
// to clean the field, then set event listener for validating the input for Description
        var Description_input= document.getElementById("inputreceiptDescription");
        Description_input.addEventListener('blur',function(){
                Description_input.value = Description_input.value.trim();
                if (Description_input.value!=""&& Description_input.value.length>1 && Description_input.value.length<=1000){
                    $(Description_input).addClass("ui-state-highlight");
                    $(Description_input).removeClass("ui-state-error");
                    Description_error=0;}
                else {
                    $(Description_input).removeClass("ui-state-highlight");
                    $(Description_input).addClass("ui-state-error");
                    Description_error=1;}
            total_errors = Transaction_ID_error+ Image_Link_error+ Name_error+ Transaction_ID_error+ Description_error;
                if (total_errors ==0){
                    submitbutton.disabled=false;
                } else {
                    submitbutton.disabled=true;
                }
            }
        );

    }
)

