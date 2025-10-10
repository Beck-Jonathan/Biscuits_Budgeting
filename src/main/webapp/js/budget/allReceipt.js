$(document).ready(function() {
    normalizeHeight();
    $("#dialog").dialog({
        modal: true,
        bgiframe: true,
        autoOpen: false,
        width: 500,
        height: 400,
    });
    $(".delButton").click(function(e) {
        e.preventDefault();
        var href = this.getAttribute("href");
        console.log(href);
        var card = document.getElementById(href+'_card');
        var card2 = card.cloneNode(true)
        console.log(card2)
        card2.removeChild(card2.lastChild);
        card2.removeChild(card2.lastChild);
        card2.removeChild(card2.lastChild);


        document.getElementById("dialog").innerHTML=card2.innerHTML;
        $('#dialog').dialog('option', 'title', 'Delete ???');
        $("#dialog").dialog({
            hide: {
                effect: "explode",
                duration: 300
            },
            show: {
                effect: "explode",
                duration: 300
            },
            buttons : {
                "Delete For Real" : function() {
                    console.log("try");
                    $.ajax({
                        url: 'deletereceipt',
                        data: "receiptid=" + href+"&AJAX=true" ,
                        type: 'post',
                        async: true,
                        success: function (response) {
                            if (response==1){
                                $(rowid).slideUp();}
                            else {
                                $(rowid).addClass("ui-state-error");
                            }
                        }})
                    $(this).dialog("close");
                    var rowid ="#"+ href+"_card";
                    $(rowid).slideUp();
                },
                "Let It Stay" : function() {
                    $(this).dialog("close");
                }
            }
        });
        $("#dialog").dialog("open"); });
})
function normalizeHeight() {
    var cards = jQuery("span.card");
    var big = 0;
    cards.each(function (index, el) {
        if (jQuery(el).height() > big)
            big = jQuery(el).height(); //find the largest height
    });
    cards.each(function (index, el) {
        jQuery(el).css("height", big + "px"); //assign largest height to all the divs
    });}

