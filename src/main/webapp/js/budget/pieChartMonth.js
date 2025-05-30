$(document).ready(function() {
let categories = document.getElementsByName('category[]')
let monthdata = [];
let firstValue=0;

let periods = ["january","february","march","april","may","june","july","august","september","october","november","december"]
    let lastValue = periods.length;
    //monthdata= callAjaxMonthly(2024);
$.when($.ajax   ({
    url: 'PieChartMonthAJAX',
    data: "year=" + 2024 ,
    type: 'get'
    })).then(function(response){
        monthdata = response;
    //console.log(monthdata);
})

    $("#byYear").on("click",async function(){
        $("#byYear").slideUp().enabled=false;
        $("#byMonth").slideDown().enabled=true;
        $("#inputtransactionYear").slideUp();
        monthdata=null;
        $.when($.ajax   ({
            url: 'PieChartMonthAJAX',
            data: "mode=annual&year=0",
            type: 'get'
        })).then(function(response){
            monthdata = response;
            console.log(response);
            periods=[];
            for (i=0;i<response.length-1;i++){
                periods[i]=(response[i][0].year)
            }
            //periods[periods.length]="total"
            //console.log(periods);
            var checked=[];
            for(var i=0; i< categories.length; i++) {
                checked[i]=categories[i].checked
            }
            for( i=0; i< categories.length; i++) {
                categories[i].checked=false
            }
            for( i=0; i< categories.length; i++) {
                if (checked[i]){
                    categories[i].click()
                }}
        })

    })
    $("#byMonth").on("click", async function(){
        periods = ["january","february","march","april","may","june","july","august","september","october","november","december"]

        $("#byYear").slideDown().enabled=true;
        $("#byMonth").slideUp().enabled=false
        $("#inputtransactionYear").slideDown();
        var year = document.getElementById("inputtransactionYear").value;
        console.log(year)

        $.when($.ajax   ({
            url: 'PieChartMonthAJAX',
            data: "year="+year,
            type: 'get'
        })).then(function(response){
            monthdata = response;
            console.log(response)
            var checked=[];
            for(var i=0; i< categories.length; i++) {
                checked[i]=categories[i].checked
            }
            for( i=0; i< categories.length; i++) {
                categories[i].checked=false
            }
            for( i=0; i< categories.length; i++) {
                if (checked[i]){
                    categories[i].click()
                }}
        })
    })

//console.log(monthdata)
    $( "#inputtransactionYear" ).on( "change", async function() {
        //console.log(monthdata);
        $.when($.ajax   ({
            url: 'PieChartMonthAJAX',
            data: "year=" + this.value ,
            type: 'get'
        })).then(function(response){
            monthdata = response;
            var checked=[];
            for(var i=0; i< categories.length; i++) {
                checked[i]=categories[i].checked
            }
            for( i=0; i< categories.length; i++) {
                categories[i].checked=false
            }
            for( i=0; i< categories.length; i++) {
                if (checked[i]){
                    categories[i].click()
                }}
        })
    } );

    $('#inputCategoryID').change(function()
        {var datapoints = [];
            for(var i=0; i< categories.length; i++) {
                if (categories[i].checked) {
                    var _label = categories[i].value;
                    _label = _label.replace(" ","");
                    var thisData = [];
                    for (j=firstValue;j<lastValue;j++){
                        var number = 0;
                        for (k=0;k<monthdata[j].length;k++){
                            if (monthdata[j][k].category_ID==_label){
                                number=monthdata[j][k].amount;
                                break;
                            }
                        }
                        number = number.toString().replace("-","");
                        var number1= Number.parseFloat(number)
                        thisData.push({label:periods[j],y:number1});
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
                    labelAngle: -90,
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

async function callAjaxMonthly(year){

    //console.log("function called")
    $.ajax   ({
        url: 'PieChartAJAX',
        data: "year=" + year+"&mode=monthly" ,
        type: 'get',
        async: false,
        success: function (response) {
                return response;

        }
    })
}
async function callAjaxMonthly(year){

    //console.log("function called")
    $.ajax   ({
        url: 'PieChartMonthAJAX',
        data: "year=" + year +"&mode=annual",
        type: 'get',
        async: false,
        success: function (response) {
            return response;

        }
    })
}
function sleep(seconds)
{
    var e = new Date().getTime() + (seconds * 1000);
    while (new Date().getTime() <= e) {}
}