$(document).ready(function() {
    // 1. Initialize the dialog once
    $("#dialog").dialog({
        modal: true,
        autoOpen: false,
        width: 500,
        height: 400,
        hide: {effect: "explode", duration: 300},
        show: {effect: "explode", duration: 300}
    });

    // Helper function to build the summary text for the modal
    function getSummaryText(rowElement) {
        var text = "";
        // Get headers from the table (skipping first and last columns: Details and Action)
        var headers = $("table thead th");
        var cells = $(rowElement).find("td");

        headers.each(function (index) {
            if (index > 0 && index < headers.length - 1) {
                var headerText = $(this).text().trim();
                var cellValue = cells.eq(index).text().trim();
                text += "<strong>" + headerText + ":</strong> " + cellValue + "<br/>";
            }
        });
        return text;
    }

    // Details Button Logic
    $(".detailButton").click(function (e) {
        e.preventDefault();
        var row = $(this).closest("tr");
        var userName = row.find("td:eq(1)").text(); // Grabs the User column

        $("#dialog").html(getSummaryText(row));
        $("#dialog").dialog('option', 'title', 'View Details for ' + userName);
        $("#dialog").dialog('option', 'buttons', {
            "Close": function () {
                $(this).dialog("close");
            }
        });
        $("#dialog").dialog("open");
    });

    // Delete Button Logic
    $(".delButton").click(function (e) {
        e.preventDefault();
        var row = $(this).closest("tr");
        var suggestionId = $(this).attr("href");
        var userName = row.find("td:eq(1)").text();

        $("#dialog").html(getSummaryText(row));
        $("#dialog").dialog('option', 'title', 'Delete suggestion from ' + userName + '?');

        $("#dialog").dialog('option', 'buttons', {
            "Delete For Real": function () {
                var $dialog = $(this);
                $.ajax({
                    url: 'deletesuggestion',
                    data: {suggestionid: suggestionId, AJAX: true},
                    type: 'post',
                    success: function (response) {
                        if (response == 1) {
                            row.slideUp(400, function () {
                                $(this).remove();
                            });
                        } else {
                            row.addClass("table-danger");
                            alert("Delete failed on server.");
                        }
                    },
                    error: function () {
                        alert("Error connecting to server.");
                    }
                });
                $dialog.dialog("close");
            },
            "Cancel": function () {
                $(this).dialog("close");
            }
        });
        $("#dialog").dialog("open");
    });
});