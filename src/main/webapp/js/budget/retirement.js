/**
 * Biscuit's Budget: Retirement Architect v36.0
 * Feature: Advanced Math Tooltip & Subtotal Tracking
 */
const RetirementEngine = {
    settings: {
        growth: 0.05 / 12,
        inflation: 0.03 / 12,
        penalty: 0.10,
        maxAge: 110
    },

    runMightyAnalysis: function () {
        const $btn = $('#runRetirementCalc');
        const currentAge = parseFloat($('#currentAge').val()) || 28;
        const retireAge = parseFloat($('#retireAge').val()) || 62;
        const retirementOffset = Math.round((retireAge - currentAge) * 12);
        const monthsForward = (100 - currentAge) * 12;

        $btn.prop('disabled', true).html('<span class="spinner-border spinner-border-sm"></span> Simulating...');

        $.get("AnalysisAJAX", {
            mode: "2",
            level: $('#btnSuper').hasClass('active') ? "1" : "0",
            retirementOffset: retirementOffset,
            monthsForward: monthsForward,
            monthsBack: 24
        }, (data) => {
            this.processSimulation(data || []);
            $btn.prop('disabled', false).text('Run Mighty Analysis at 5% DJIA');
        }, "json").fail(() => {
            $btn.prop('disabled', false).text('Error - Try Again');
        });
    },

    processSimulation: function (forecastData) {
        const startAge = parseFloat($('#currentAge').val()) || 28;
        const targetRetireAge = parseFloat($('#retireAge').val()) || 62;
        const baseAnnual401k = parseFloat($('#annual401k').val()) || 0;
        let liq = parseFloat($('#retireLiquid').val()) || 0;
        let lock = parseFloat($('#retireLocked').val()) || 0;

        const monthlyTotals = {};
        forecastData.flat().forEach(item => {
            const idx = parseInt(item.year) || 0;
            if (idx === 0) return;
            if (!monthlyTotals[idx]) monthlyTotals[idx] = {exp: 0, inc: 0};
            const amt = parseFloat(item.forecasted_amount) || parseFloat(item.amount) || 0;
            const type = (item.transactionType || '').toLowerCase();
            if (type === 'expense') monthlyTotals[idx].exp += amt;
            else if (type === 'income') monthlyTotals[idx].inc += amt;
        });

        const maxDataIdx = Math.max(...Object.keys(monthlyTotals).map(Number), 0);

        let timeline = [];
        let m = 1, curAge = startAge;

        // Tracking variables for the Tooltip Math
        let yLiqDraw = 0, yLockDraw = 0, yExp = 0, yInc = 0, yGrowth = 0, yContrib = 0;
        let totalGrowthToDate = 0;

        while (curAge < this.settings.maxAge) {
            curAge = startAge + (m / 12);
            let yearsPassed = Math.floor(m / 12);
            let isRetired = (curAge >= targetRetireAge);

            // Calculate Growth for this month
            let startMoVal = liq + lock;
            liq *= (1 + this.settings.growth);
            lock *= (1 + this.settings.growth);
            let moGrowth = (liq + lock) - startMoVal;
            yGrowth += moGrowth;
            totalGrowthToDate += moGrowth;

            let mOut = 0, mIn = 0;
            if (m <= maxDataIdx) {
                mOut = monthlyTotals[m]?.exp || 0;
                mIn = monthlyTotals[m]?.inc || 0;
            } else {
                const last = monthlyTotals[maxDataIdx] || {exp: 0, inc: 0};
                mOut = last.exp * Math.pow(1 + this.settings.inflation, m - maxDataIdx);
                mIn = isRetired ? 1000 : last.inc;
            }

            yExp += mOut;
            yInc += mIn;

            if (!isRetired) {
                let monthly401k = (baseAnnual401k + (yearsPassed * 500)) / 12;
                lock += monthly401k;
                yContrib += monthly401k;
                let surplus = mIn - mOut - monthly401k;
                if (surplus > 0) liq += (surplus * 0.50);
                else liq += surplus;
            } else {
                liq += mIn;
                if (liq < mOut) {
                    let deficit = mOut - liq;
                    yLiqDraw += liq;
                    liq = 0;
                    let pen = (curAge < 59.5) ? (1 + this.settings.penalty) : 1;
                    lock = Math.max(0, lock - (deficit * pen));
                    yLockDraw += (deficit * pen);
                } else {
                    liq -= mOut;
                    yLiqDraw += Math.max(0, mOut - mIn);
                }
            }

            liq = Math.max(0, liq);
            lock = Math.max(0, lock);

            if (m % 12 === 0) {
                timeline.push({
                    x: Math.floor(curAge),
                    liq: Math.round(liq),
                    lock: Math.round(lock),
                    drawLiq: Math.round(yLiqDraw),
                    drawLock: Math.round(yLockDraw),
                    totalExp: Math.round(yExp),
                    totalInc: Math.round(yInc),
                    annualGrowth: Math.round(yGrowth),
                    annualContrib: Math.round(yContrib),
                    cumGrowth: Math.round(totalGrowthToDate),
                    retired: isRetired
                });
                // Reset Annual Buckets
                yLiqDraw = 0;
                yLockDraw = 0;
                yExp = 0;
                yInc = 0;
                yGrowth = 0;
                yContrib = 0;
            }
            if (isRetired && liq <= 0 && lock <= 0) break;
            m++;
        }

        this.renderMightyChart(timeline, targetRetireAge);
        this.updateFinalReport(timeline[timeline.length - 1]);
    },

    renderMightyChart: function (data, retireAge) {
        Highcharts.chart('retirementChartContainer', {
            chart: {type: 'area', height: 550, backgroundColor: '#fdfdfd'},
            title: {text: 'Mighty Retirement Simulation v36'},
            xAxis: {
                tickInterval: 5,
                plotLines: [{
                    color: '#e74c3c',
                    width: 2,
                    value: retireAge,
                    label: {
                        text: 'RETIREMENT',
                        rotation: 0,
                        align: 'right',
                        style: {color: '#e74c3c', fontWeight: 'bold'}
                    }
                }]
            },
            yAxis: {title: {text: 'Net Worth'}, labels: {format: '${value:,.0f}'}, min: 0},
            tooltip: {
                shared: true,
                useHTML: true,
                backgroundColor: 'rgba(255, 255, 255, 0.95)',
                borderWidth: 1,
                borderColor: '#ccc',
                formatter: function () {
                    const d = this.points[0].point.options;
                    const totalNW = d.liq + d.lock;
                    const netFlow = (d.totalInc + d.annualGrowth + d.annualContrib) - d.totalExp;

                    let s = `<div style="padding:10px; width:280px; font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;">`;
                    s += `<div style="display:flex; justify-content:space-between; align-items:center;">`;
                    s += `<b style="font-size:16px;">Age ${this.x}</b>`;
                    s += `<span class="badge ${d.retired ? 'bg-danger' : 'bg-success'}" style="color:white; padding:3px 8px; border-radius:12px; font-size:10px;">${d.retired ? 'RETIRED' : 'WORKING'}</span>`;
                    s += `</div><hr style="margin:8px 0;"/>`;

                    s += `<div style="font-size:13px; margin-bottom:10px;">`;
                    s += `<div style="display:flex; justify-content:space-between;"><span>Liquid:</span><b>$${d.liq.toLocaleString()}</b></div>`;
                    s += `<div style="display:flex; justify-content:space-between;"><span>Locked:</span><b>$${d.lock.toLocaleString()}</b></div>`;
                    s += `<div style="display:flex; justify-content:space-between; font-size:14px; margin-top:4px; border-top:1px solid #eee; padding-top:4px;"><span><b>Total Net Worth:</b></span><b>$${totalNW.toLocaleString()}</b></div>`;
                    s += `</div>`;

                    s += `<b style="font-size:11px; color:#666;">ANNUAL PERFORMANCE</b><br/>`;
                    s += `<div style="font-size:12px; line-height:1.6;">`;
                    s += `<div style="display:flex; justify-content:space-between;"><span>Income + Contrib:</span><span style="color:#27ae60;">+$${(d.totalInc + d.annualContrib).toLocaleString()}</span></div>`;
                    s += `<div style="display:flex; justify-content:space-between;"><span>Market Growth:</span><span style="color:#27ae60;">+$${d.annualGrowth.toLocaleString()}</span></div>`;
                    s += `<div style="display:flex; justify-content:space-between;"><span>Total Expenses:</span><span style="color:#e74c3c;">-$${d.totalExp.toLocaleString()}</span></div>`;
                    s += `<div style="display:flex; justify-content:space-between; font-weight:bold; border-top:1px dashed #ccc; margin-top:2px;"><span>Annual Net:</span><span style="color:${netFlow >= 0 ? '#27ae60' : '#e74c3c'};">${netFlow >= 0 ? '+' : ''}$${netFlow.toLocaleString()}</span></div>`;
                    s += `</div>`;

                    s += `<hr style="margin:8px 0;"/>`;
                    s += `<div style="font-size:11px; color:#888; text-align:center;">Cumulative Market Gains: $${d.cumGrowth.toLocaleString()}</div>`;
                    s += `</div>`;
                    return s;
                }
            },
            plotOptions: {
                area: {
                    stacking: 'normal',
                    marker: {enabled: false},
                    fillOpacity: 0.7,
                    lineWidth: 1,
                    states: {hover: {lineWidth: 2}}
                }
            },
            series: [
                {
                    name: '401k/IRA',
                    color: '#2980b9',
                    data: data.map(d => ({...d, y: d.lock}))
                },
                {
                    name: 'Liquid Assets',
                    color: '#27ae60',
                    data: data.map(d => ({...d, y: d.liq}))
                }
            ]
        });
    },

    updateFinalReport: function (final) {
        if (!final) return;
        const total = final.liq + final.lock;
        const roiLabel = final.cumGrowth > 0 ? `<br/><small>Includes $${final.cumGrowth.toLocaleString()} in compounded market gains</small>` : '';
        $('#retirementSummary').html(`
            <div class="alert ${total > 0 ? 'alert-success' : 'alert-danger'} text-center mt-3 shadow border-0" style="border-radius:15px;">
                <h3 class="mb-0">Wealth at Age ${final.x}: $${total.toLocaleString()}</h3>
                ${roiLabel}
            </div>
        `);
    }
};

$(document).ready(function () {
    $('#runRetirementCalc').on('click', function () {
        RetirementEngine.runMightyAnalysis();
    });
});