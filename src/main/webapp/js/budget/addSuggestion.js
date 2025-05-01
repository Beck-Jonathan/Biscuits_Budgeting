$(document).ready(function() {

// to clean the field, then set event listener for validating the input for Application_Name
        var Application_Name_input= document.getElementById("inputsuggestionApplication_Name");
        Application_Name_input.value='';
        Application_Name_input.addEventListener('keyup',function(){
                if (Application_Name_input.value!=""&& Application_Name_input.value.length>1 && Application_Name_input.value.length<=100){
                    $(Application_Name_input).addClass("ui-state-highlight");
                    $(Application_Name_input).removeClass("ui-state-error");
                }
                else {
                    $(Application_Name_input).removeClass("ui-state-highlight");
                    $(Application_Name_input).addClass("ui-state-error");
                }
            }
        );
// to clean the field, then set event listener for validating the input for content
        var content_input= document.getElementById("inputsuggestioncontent");
        content_input.value='';
        content_input.addEventListener('keyup',function(){
                if (content_input.value!=""&& content_input.value.length>1 && content_input.value.length<=1000){
                    $(content_input).addClass("ui-state-highlight");
                    $(content_input).removeClass("ui-state-error");
                }
                else {
                    $(content_input).removeClass("ui-state-highlight");
                    $(content_input).addClass("ui-state-error");
                }
            }
        );

    }
)

