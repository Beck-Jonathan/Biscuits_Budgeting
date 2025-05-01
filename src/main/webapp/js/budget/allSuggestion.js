$(document).ready(function() {
    $("#dialog").dialog({
        modal: true,
        bgiframe: true,
        autoOpen: false,
        width: 500,
        height: 400,
    });
    $(".detailButton").click(function (e) {
        e.preventDefault();
        var headers = document.getElementsByClassName('table-responsive')[0].childNodes[0].childNodes[1].childNodes[1].childNodes;
        var parentrow = this.parentElement.parentElement.children;

        var text = "";
        for (i = 3; i < headers.length - 2; i = i + 2) {
            text += headers[i].textContent + ": " + parentrow[(i - 1) / 2].innerHTML + "</br>";
        }
        document.getElementById("dialog").innerHTML = text;

        $('#dialog').dialog('option', 'title', 'View ' + parentrow[1].innerHTML + ".");
        $("#dialog").dialog({
            hide: {
                effect: "explode",
                duration: 300
            },
            show: {
                effect: "explode",
                duration: 300
            },
            buttons: {
                "Let It Stay": function () {
                    $(this).dialog("close");
                }
            }
        });
        $("#dialog").dialog("open");
    });

    $(".delButton").click(function (e) {
        e.preventDefault();
        var headers = document.getElementsByClassName('table-responsive')[0].childNodes[0].childNodes[1].childNodes[1].childNodes;
        var parentrow = this.parentElement.parentElement.parentElement.children;
        var rowid = "#" + targetUrl + "row";
        var text = "";
        for (i = 3; i < headers.length - 2; i = i + 2) {
            text += headers[i].textContent + ": " + parentrow[(i - 1) / 2].innerHTML + "</br>";
        }
        document.getElementById("dialog").innerHTML = text;
        var targetUrl = $(this).attr("href");
        $('#dialog').dialog('option', 'title', 'Delete ' + parentrow[1].innerHTML + "???");
        $("#dialog").dialog({
            hide: {
                effect: "explode",
                duration: 300
            },
            show: {
                effect: "explode",
                duration: 300
            },
            buttons: {
                "Delete For Real": function () {
                    console.log("try");
                    $.ajax({
                        url: 'deletesuggestion',
                        data: "suggestionid=" + targetUrl + "&AJAX=true",
                        type: 'post',
                        async: true,
                        success: function (response) {
                            if (response == 1) {
                                $(rowid).slideUp();
                            } else {
                                $(rowid).addClass("ui-state-error");
                            }
                        }
                    })
                    $(this).dialog("close");
                    var rowid = "#" + targetUrl + "row";
                    $(rowid).slideUp();
                },
                "Let It Stay": function () {
                    $(this).dialog("close");
                }
            }
        });
        $("#dialog").dialog("open");
    });
})