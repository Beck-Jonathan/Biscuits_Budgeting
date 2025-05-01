$(document).ready(function() {


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

