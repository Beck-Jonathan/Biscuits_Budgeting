$(document).ready(function() {
    normalizeHeight();
    tooltips();
    $("#dialog").dialog({
        modal: true,
        bgiframe: true,
        autoOpen: false,
        width: 500,
        height: 400,
    });
    $(".delButton").click(function(e) {
        e.preventDefault();
        var headers = document.getElementsByClassName('table-responsive')[0].childNodes[0].childNodes[1].childNodes[1].childNodes;
        var parentrow = this.parentElement.parentElement.parentElement.children;var rowid ="#"+ targetUrl+"row";
        var text = "";
        for (i=1;i<headers.length-2;i=i+2){
            text +=headers[i].textContent+": "+parentrow[(i-1)/2].innerHTML+"</br>";
        }
        document.getElementById("dialog").innerHTML=text;var targetUrl = $(this).attr("href");
        $('#dialog').dialog('option', 'title', 'Delete '+parentrow[1].innerHTML+"???");
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
                        url: 'deleteBudget',
                        data: "budgetid=" + targetUrl ,
                        type: 'get',
                        async: true,
                        success: function (response) {
                            if (response==1){
                                $(rowid).slideUp();}
                            else {
                                $(rowid).addClass("ui-state-error");
                            }
                        }})
                    $(this).dialog("close");
                    var rowid ="#"+ targetUrl+"_row";
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

function tooltips(){
    // Check if bootstrap is defined to avoid the error
    if (typeof bootstrap !== 'undefined') {
        var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
        tooltipTriggerList.map(function (tooltipTriggerEl) {
            return new bootstrap.Tooltip(tooltipTriggerEl);
        });
    } else {
        console.error("Bootstrap JS is not loaded. Tooltips will not work.");
    }
    // Initialize all tooltips on the page

    }


