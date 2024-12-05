$(document).ready(function() {
    let address = $(location).attr('href');
    address=address.replace("PieChart","");
    console.log(address);
    let userid=0;
    $.get(address+"user_Id", function(response){

        userid = response;
    });
    $('#inputtransactionYear').change(function()
    {

        let year = $('#inputtransactionYear').val();
        $("#chartContainer").slideUp();

       jQuery.ajax({
           type:"get",
           url: address+"category_by_year/",
           data : {"sort":year,
               "user_id" : userid,
               "type":"pie"
           },
           success: function (request) {
               var array = request;
               var dps = [];
               for(var i=0; i< array.length; i++) {
                   var _label = array[i].category_ID;
                   var number = array[i].amount;
                   dps.push({label:_label, y: number});
               }
               var options2 = {
                   title: {
                       text: "Expense Breakddown for "+year
                   },
                   data: [{
                       type: "pie",
                       startAngle: 45,
                       showInLegend: "true",
                       legendText: "{label}",
                       indexLabel: "{label} ({y})",
                       yValueFormatString: "#,##0.#" % "",
                       dataPoints: dps
                   }]
               };
               $("#chartContainer").CanvasJSChart(options2);
               $("#chartContainer").slideDown();
           },
           error: function (request, status, error) {
               console.log("fail")
               console.log(request.responseText);
           }
       });
    }
    );

    $('#inputCategoryID').change(function()
        {
            let category = $('#inputCategoryID').val();
            $("#barContainer").slideUp();
            jQuery.ajax({
                type:"get",
                url: address+"category_by_year/",
                data : {"sort":category,
                    "user_id" : userid,
                    "type":"bar"
                },
                success: function (request) {
                    var array = request;
                    var dps = [];
                    for(var i=0; i< array.length; i++) {
                        var _label = array[i].category_ID;
                        var number = array[i].amount;
                        dps.push({label:_label, y: number});
                    }
                    var options3 = {
                        title: {
                            text: "Annualy Breakdown  for "+category
                        },
                        data: [{
                            type: "bar",

                            dataPoints: dps
                        }]
                    };
                    $("#barContainer").CanvasJSChart(options3);
                    $("#barContainer").slideDown();
                },
                error: function (request, status, error) {
                    console.log("fail")
                    console.log(request.responseText);
                }
            });
        }
    );


    var options = {
        title: {
            text: "No Data"
        },
        data: [{
            type: "pie",
            startAngle: 45,
            showInLegend: "true",
            legendText: "{label}",
            indexLabel: "{label} ({y})",
            yValueFormatString: "#,##0.#" % "",
            dataPoints: [

            ]
        }]
    };
    var bar = {
        title: {
            text: "No Data"
        },
        data: [{
            type: "bar",

            dataPoints: [

            ]
        }]
    };
    $("#barContainer").CanvasJSChart(bar);

});
