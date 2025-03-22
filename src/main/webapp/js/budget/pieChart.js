$(document).ready(function() {

    // $('#inputtransactionYear').change(function()
    // {
    //     let year = $('#inputtransactionYear').val();
    //            var datapoints = [];
    //            var categories = document.getElementsByName('category[]')
    //     //console.log(categories);
    //            for(var i=0; i< categories.length; i++) {
    //                if (categories[i].checked) {
    //                    var _label = categories[i].value;
    //                    //console.log(_label);
    //                    var number = document.getElementById(year+_label+'amount').innerText;
    //                    //console.log(number);
    //                    datapoints.push({label: _label, y: number});
    //                }
    //            }
    //            var options2 = {
    //                title: {
    //                    text: "Expense Breakddown for "+year
    //                },
    //                data: [{
    //                    type: "pie",
    //                    startAngle: 45,
    //                    showInLegend: "true",
    //                    legendText: "{label}",
    //                    indexLabel: "{label} ({y})",
    //                    yValueFormatString: "#,##0.#" % "",
    //                    dataPoints: datapoints
    //                }]
    //            };
    //            $("#barContainer").CanvasJSChart(options2);
    //
    // }
    // );

    $('#inputCategoryID').change(function()
        {
            var minyear = parseInt(document.getElementById("FirstYear").innerText);
            var maxyear = new Date().getFullYear();
            let year = $('#inputtransactionYear').val();
            var datapoints = [];
            var categories = document.getElementsByName('category[]')
            for(var i=0; i< categories.length; i++) {
                //console.log(i);
                if (categories[i].checked) {
                    var _label = categories[i].value;
                    _label = _label.replace(" ","");
                    var number = document.getElementById(year+_label+'amount').innerText;
                    var thisData = [];
                    for (j=minyear;j<maxyear;j++){
                        var value = 5;
                        var number = document.getElementById(j+_label+'amount').innerText.replace("-","");
                        var number1= Number.parseFloat(number)

                        thisData.push({x:j,y:number1});
                    }

                    datapoints.push({type:"stackedColumn",name: _label, dataPoints: thisData});
                }
            }
            var options2 = {
                animationEnabled: true,

                title:{
                    text: "Spending Breakdown"
                },
                axisX: {
                    valueFormatString:"####"
                },
                axisY: {
                    prefix: "$"
                },
                toolTip: {
                    shared: true
                },
                legend:{
                    cursor: "pointer",

                },
                data: datapoints
            };
            $("#barContainer").CanvasJSChart(options2);

        }
    );
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
