let payment = document.getElementById("Mortgage_Payment")
let Nickname_input= document.getElementById("inputmortgageNickname");
let Present_Value_input= document.getElementById("inputmortgagePresent_Value");
let Present_Value_Button= document.getElementById("ButtonPresent_Value");

let Future_Value_input= document.getElementById("inputmortgageFuture_Value");
let Future_Value_Button= document.getElementById("ButtonFuture_Value");

let Interest_Rate_input= document.getElementById("inputmortgageInterest_Rate");
let Interest_Rate_Button= document.getElementById("ButtonInterest_Rate");

let Monthly_Payment_input= document.getElementById("inputmortgageMonthly_Payment");
let Monthly_Payment_Button= document.getElementById("ButtonMonthly_Payment");

let Extra_Payment_input= document.getElementById("inputmortgageExtra_Payment");
let Extra_Payment_Button= document.getElementById("ButtonExtra_Payment");

let Remaining_Term_input= document.getElementById("inputmortgageRemaining_Term");
let Remaining_Term_Button= document.getElementById("ButtonRemaining_Term");

let submit_mortgage = document.getElementById("Save_Mortgage");
let amortButton = document.getElementById("Amortize");

let pv=0;
let fv=0;
let rate=0;
let pmt=0;
let extra=0;
let nper=0;

$(document).ready(async function() {
    var tvmCalculator = await import("./tvmCalculator.js")
// Specify params as object


    setButtons();

    submit_mortgage.setAttribute("disabled", "true");
// to get and validate the input for Present_Value


        amortButton.addEventListener('click', function () {
            if (document.getElementsByClassName("ui-state-error").length === 0) {
                rate = parseFloat(rate);
                pmt = parseFloat(pmt);
                pv = parseFloat(pv);
                fv = parseFloat(fv)
                extra=parseFloat(extra);
                rate = parseFloat(rate);
                var totalPayment = pmt+extra;
                const tvmParams = {pv: pv, fv: fv, nper: nper, rate: rate, pmt : pmt};
                console.log(pv);
                console.log(fv);
                console.log(nper);
                console.log(rate);
                console.log(pmt);
                console.log(extra);
                const periodicPayment = tvmCalculator.calcPMT(tvmParams);
                console.log(periodicPayment);
                makeAmortTable();

            }
        });

    Present_Value_Button.addEventListener('click', function () {
        if (document.getElementsByClassName("ui-state-error").length === 0) {
            rate = parseFloat(rate);
            pmt = parseFloat(pmt);
            pv = parseFloat(pv);
            fv = parseFloat(fv)
            extra=parseFloat(extra);
            rate = parseFloat(rate);
            var totalPayment = pmt+extra;
            const tvmParams = {rate: rate,nper:nper,pmt:totalPayment,fv:fv};
            console.log(pv);
            console.log(fv);
            console.log(nper);
            console.log(rate);
            console.log(pmt);
            console.log(extra);
            console.log(totalPayment);
            var PresentValueCalculated = tvmCalculator.calcPV(tvmParams);
            console.log(PresentValueCalculated);
            Present_Value_input.value=PresentValueCalculated;
        }
    });

    Future_Value_Button.addEventListener('click', function () {
        if (document.getElementsByClassName("ui-state-error").length === 0) {
            rate = parseFloat(rate);
            pmt = parseFloat(pmt);
            pv = parseFloat(pv);
            fv = parseFloat(fv)
            extra=parseFloat(extra);
            rate = parseFloat(rate);
            var totalPayment = pmt+extra;
            const tvmParams = {pv: pv, fv: fv, nper: nper, rate: rate, pmt : totalPayment};
            console.log(pv);
            console.log(fv);
            console.log(nper);
            console.log(rate);
            console.log(pmt);
            console.log(extra);
            var FutureValueCalculated = tvmCalculator.calcFV(tvmParams);
            console.log(FutureValueCalculated);
            Future_Value_input.value=FutureValueCalculated;
        }
    });

    Interest_Rate_Button.addEventListener('click', function () {
        if (document.getElementsByClassName("ui-state-error").length === 0) {
            rate = parseFloat(rate);
            pmt = parseFloat(pmt);
            pv = parseFloat(pv);
            fv = parseFloat(fv)
            extra=parseFloat(extra);
            rate = parseFloat(rate);
            var totalPayment = pmt+extra;
            const tvmParams = {pv: pv, fv: fv, nper: nper, rate: rate, pmt : totalPayment};
            console.log(pv);
            console.log(fv);
            console.log(nper);
            console.log(rate);
            console.log(pmt);
            console.log(extra);
            var InterestRateCalculated = tvmCalculator.calcInterestRate(tvmParams);
            console.log(InterestRateCalculated);
            Interest_Rate_input.value = InterestRateCalculated;
        }
    });

    Monthly_Payment_Button.addEventListener('click', function () {
        if (document.getElementsByClassName("ui-state-error").length === 0) {
            rate = parseFloat(rate);
            pmt = parseFloat(pmt);
            pv = parseFloat(pv);
            fv = parseFloat(fv)
            extra=parseFloat(extra);
            rate = parseFloat(rate);
            var totalPayment = pmt+extra;
            const tvmParams = {pv: pv, fv: fv, nper: nper, rate: rate, pmt : pmt};
            console.log(pv);
            console.log(fv);
            console.log(nper);
            console.log(rate);
            console.log(pmt);
            console.log(extra);
            var PaymentCalculated = tvmCalculator.calcPMT(tvmParams);
            console.log(PaymentCalculated);
            Monthly_Payment_input.value = PaymentCalculated
        }
    });

    Extra_Payment_Button.addEventListener('click', function () {
        rate = parseFloat(rate);
        pmt = parseFloat(pmt);
        pv = parseFloat(pv);
        fv = parseFloat(fv)
        extra=parseFloat(extra);
        rate = parseFloat(rate);
        var totalPayment = pmt+extra;
        if (document.getElementsByClassName("ui-state-error").length === 0) {
            rate = parseFloat(rate);
            pmt = parseFloat(pmt);
            pv = parseFloat(pv);
            fv = parseFloat(fv)
            extra=parseFloat(extra);
            rate = parseFloat(rate);
            const tvmParams = {pv: pv, fv: fv, nper: nper, rate: rate, pmt : pmt};
            console.log(pv);
            console.log(fv);
            console.log(nper);
            console.log(rate);
            console.log(pmt);
            console.log(extra);
            var PaymentCalculated = tvmCalculator.calcPMT(tvmParams);
            PaymentCalculated = PaymentCalculated-pmt;
            console.log(PaymentCalculated);
            Extra_Payment_input.value = PaymentCalculated;
        }
    });

    Remaining_Term_Button.addEventListener('click', function () {
        if (document.getElementsByClassName("ui-state-error").length === 0) {
            rate = parseFloat(rate);
            pmt = parseFloat(pmt);
            pv = parseFloat(pv);
            fv = parseFloat(fv)
            extra=parseFloat(extra);
            rate = parseFloat(rate);
            var totalPayment = pmt+extra;
            const tvmParams = {rate: rate, pmt : totalPayment,pv: pv, fv: fv  };
            console.log("pv="+pv);
            console.log("fv="+fv);
            console.log("nper="+nper);
            console.log("rate="+rate);
            console.log("pmt="+pmt);
            console.log("extra="+extra);
            console.log("here");
            var TermCalculated = tvmCalculator.calcNPer(tvmParams);
            console.log(TermCalculated);
            Remaining_Term_input.value = TermCalculated;
        }
    });


        let submitbutton = document.getElementById("Save_Mortgage")
        submitbutton.disabled=true;
        let total_errors=0;

        let Nickname_error=0;
        let Present_Value_error=0;
        let Future_Value_error=0;
        let Interest_Rate_error=0;
        let Monthly_Payment_error=0;
        let Extra_Payment_error=0;
        let Remaining_Term_error=0;

// to clean the field, then set event listener for validating the input for Nickname
        Nickname_input.addEventListener('blur',function(){
                Nickname_input.value = Nickname_input.value.trim();
                if (Nickname_input.value!=""&& Nickname_input.value.length>1 && Nickname_input.value.length<=255){
                    $(Nickname_input).addClass("ui-state-highlight");
                    $(Nickname_input).removeClass("ui-state-error");
                    Nickname_error=0;}
                else {
                    $(Nickname_input).removeClass("ui-state-highlight");
                    $(Nickname_input).addClass("ui-state-error");
                    Nickname_error=1;}
                total_errors =  Nickname_error+ Present_Value_error+ Future_Value_error+ Interest_Rate_error+ Monthly_Payment_error+ Extra_Payment_error+ Remaining_Term_error;
                if (total_errors ==0){
                    submitbutton.disabled=false;
                } else {
                    submitbutton.disabled=true;
                }
            }
        );
// to clean the field, then set event listener for validating the input for Present_Value
        Present_Value_input.addEventListener('blur',function(){
                Present_Value_input.value = Present_Value_input.value.trim();
                if (Present_Value_input.value!=""&& $.isNumeric(Present_Value_input.value)){
                    $(Present_Value_input).addClass("ui-state-highlight");
                    $(Present_Value_input).removeClass("ui-state-error");
                    Present_Value_error=0;
                    pv = Present_Value_input.value;
                }
                else {
                    $(Present_Value_input).removeClass("ui-state-highlight");
                    $(Present_Value_input).addClass("ui-state-error");
                    Present_Value_error=1;}
                total_errors =  Nickname_error+ Present_Value_error+ Future_Value_error+ Interest_Rate_error+ Monthly_Payment_error+ Extra_Payment_error+ Remaining_Term_error;
                if (total_errors ==0){
                    submitbutton.disabled=false;
                } else {
                    submitbutton.disabled=true;
                }
            }
        );
// to clean the field, then set event listener for validating the input for Future_Value
        Future_Value_input.addEventListener('blur',function(){
                Future_Value_input.value = Future_Value_input.value.trim();
                if (Future_Value_input.value!=""&& $.isNumeric(Future_Value_input.value)){
                    $(Future_Value_input).addClass("ui-state-highlight");
                    $(Future_Value_input).removeClass("ui-state-error");
                    Future_Value_error=0;
                    fv=Future_Value_input.value;
                }
                else {
                    $(Future_Value_input).removeClass("ui-state-highlight");
                    $(Future_Value_input).addClass("ui-state-error");
                    Future_Value_error=1;}
                total_errors =  Nickname_error+ Present_Value_error+ Future_Value_error+ Interest_Rate_error+ Monthly_Payment_error+ Extra_Payment_error+ Remaining_Term_error;
                if (total_errors ==0){
                    submitbutton.disabled=false;
                } else {
                    submitbutton.disabled=true;
                }
            }
        );
// to clean the field, then set event listener for validating the input for Interest_Rate
        Interest_Rate_input.addEventListener('blur',function(){
                Interest_Rate_input.value = Interest_Rate_input.value.trim();
                if (Interest_Rate_input.value!=""&& $.isNumeric(Interest_Rate_input.value)){
                    $(Interest_Rate_input).addClass("ui-state-highlight");
                    $(Interest_Rate_input).removeClass("ui-state-error");
                    Interest_Rate_error=0;
                    rate = Interest_Rate_input.value;
                }
                else {
                    $(Interest_Rate_input).removeClass("ui-state-highlight");
                    $(Interest_Rate_input).addClass("ui-state-error");
                    Interest_Rate_error=1;
                }
                total_errors =  Nickname_error+ Present_Value_error+ Future_Value_error+ Interest_Rate_error+ Monthly_Payment_error+ Extra_Payment_error+ Remaining_Term_error;
                if (total_errors ==0){
                    submitbutton.disabled=false;
                } else {
                    submitbutton.disabled=true;
                }
            }
        );
// to clean the field, then set event listener for validating the input for Monthly_Payment
        Monthly_Payment_input.addEventListener('blur',function(){
                Monthly_Payment_input.value = Monthly_Payment_input.value.trim();
                if (Monthly_Payment_input.value!=""&& $.isNumeric(Monthly_Payment_input.value)){
                    $(Monthly_Payment_input).addClass("ui-state-highlight");
                    $(Monthly_Payment_input).removeClass("ui-state-error");
                    Monthly_Payment_error=0;
                    pmt = Monthly_Payment_input.value;
                }
                else {
                    $(Monthly_Payment_input).removeClass("ui-state-highlight");
                    $(Monthly_Payment_input).addClass("ui-state-error");
                    Monthly_Payment_error=1;}
                total_errors =  Nickname_error+ Present_Value_error+ Future_Value_error+ Interest_Rate_error+ Monthly_Payment_error+ Extra_Payment_error+ Remaining_Term_error;
                if (total_errors ==0){
                    submitbutton.disabled=false;
                } else {
                    submitbutton.disabled=true;
                }
            }
        );
// to clean the field, then set event listener for validating the input for Extra_Payment
        Extra_Payment_input.addEventListener('blur',function(){
                Extra_Payment_input.value = Extra_Payment_input.value.trim();
                if (Extra_Payment_input.value!=""&& $.isNumeric(Extra_Payment_input.value)){
                    $(Extra_Payment_input).addClass("ui-state-highlight");
                    $(Extra_Payment_input).removeClass("ui-state-error");
                    Extra_Payment_error=0;
                    extra=Extra_Payment_input.value;
                }
                else {
                    $(Extra_Payment_input).removeClass("ui-state-highlight");
                    $(Extra_Payment_input).addClass("ui-state-error");
                    Extra_Payment_error=1;}
                total_errors =  Nickname_error+ Present_Value_error+ Future_Value_error+ Interest_Rate_error+ Monthly_Payment_error+ Extra_Payment_error+ Remaining_Term_error;
                if (total_errors ==0){
                    submitbutton.disabled=false;
                } else {
                    submitbutton.disabled=true;
                }
            }
        );
// to clean the field, then set event listener for validating the input for Remaining_Term
        Remaining_Term_input.addEventListener('blur',function(){
            Remaining_Term_input.value = Remaining_Term_input.value.trim();
            if (Remaining_Term_input.value!=""&& $.isNumeric(Remaining_Term_input.value)){
                $(Remaining_Term_input).addClass("ui-state-highlight");
                $(Remaining_Term_input).removeClass("ui-state-error");
                Remaining_Term_error=0;
                nper = Remaining_Term_input.value;
            }
            else {
                $(Remaining_Term_input).removeClass("ui-state-highlight");
                $(Remaining_Term_input).addClass("ui-state-error");
                Remaining_Term_error=1;}
            total_errors =  Nickname_error+ Present_Value_error+ Future_Value_error+ Interest_Rate_error+ Monthly_Payment_error+ Extra_Payment_error+ Remaining_Term_error;
            if (total_errors ==0){
                submitbutton.disabled=false;
            } else {
                submitbutton.disabled=true;
            }
        }
        )

    })

function makeAmortTable(){
    let p = Present_Value_input.value;
    let i = Interest_Rate_input.value * 1.00 / 100 / 12;
    let fv = Future_Value_input.value;
    let n = Remaining_Term_input.value;
    var payment_bpx = document.getElementById("inputmortgageMonthly_Payment");
    //console.log("Pricipal:" + p + "\n Interest Rate: " + i + "\n Nper: " + n + " ")
    var monthly_payment = Math.round(p * i * (Math.pow(1 + i, n)) / (Math.pow(1 + i, n) - 1) * 100) / 100;
    var month = 1;
    //console.log((monthly_payment));
    submit_mortgage.removeAttribute("disabled");
    submit_mortgage.setAttribute("enabled", "true");
    let amortization = document.getElementById("amortization");
    var table = document.createElement("table");
    table.classList.add("table");
    table.classList.add("table-bordered");
    var th = document.createElement("tr");
    var rh = document.createElement("th");
    rh.innerHTML = "Month";
    th.appendChild(rh);
    rh = document.createElement("th");
    rh.innerHTML = "Payment";
    th.appendChild(rh);
    rh = document.createElement("th");
    rh.innerHTML = "Interest";
    th.appendChild(rh);
    rh = document.createElement("th");
    rh.innerHTML = "Principal";
    th.appendChild(rh);
    table.appendChild(th);
    while (p > 0) {
        let tr = document.createElement("tr")
        var interest = Math.round(100 * p * i) / 100;
        p = Math.round(100 * (p - monthly_payment + interest)) / 100;
        month++;
        var td = document.createElement("td")
        td.innerHTML = month;
        tr.append(td);
        td = document.createElement("td")
        td.innerHTML = monthly_payment;
        tr.append(td);
        td = document.createElement("td")
        td.innerHTML = interest;
        tr.append(td);
        td = document.createElement("td")
        td.innerHTML = p;
        tr.append(td);
        table.appendChild(tr);
    }
    document.getElementById('amortization').appendChild(table);
}

function setButtons(){
    $("#ButtonMonthly_Payment").button()
        .button("option", "icon", "ui-icon-check")
        .button("option", "showIcon", "true")
        .button("option", "label", "calculate");

    $("#ButtonPresent_Value").button()
        .button("option", "icon", "ui-icon-check")
        .button("option", "showIcon", "true")
        .button("option", "label", "calculate");
    $("#ButtonFuture_Value").button()
        .button("option", "icon", "ui-icon-check")
        .button("option", "showIcon", "true")
        .button("option", "label", "calculate");
    $("#ButtonInterest_Rate").button()
        .button("option", "icon", "ui-icon-check")
        .button("option", "showIcon", "true")
        .button("option", "label", "calculate");
    $("#ButtonExtra_Payment").button()
        .button("option", "icon", "ui-icon-check")
        .button("option", "showIcon", "true")
        .button("option", "label", "calculate");
    $("#ButtonRemaining_Term").button()
        .button("option", "icon", "ui-icon-check")
        .button("option", "showIcon", "true")
        .button("option", "label", "calculate");
    $("#ButtonAmortize").button()
        .button("option", "icon", "ui-icon-check")
        .button("option", "showIcon", "true")
        .button("option", "label", "Amortize");
}
