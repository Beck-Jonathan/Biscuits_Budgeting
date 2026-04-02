/**
 * Biscuit's Budget: Retirement Architect v52.0
 * Logic:
 * - Trust the SQL: All filtering (Retirement/Investment zeroing) happens in the SP.
 * - Merged Data: SQL handles the union of Regression data + Planned Transactions.
 * - Simple Rendering: JS just compounds balances and aggregates.
 */
const RetirementEngine = {
    settings: {
        growth: 0.05 / 12,    // 5% Annual
        inflation: 0.03 / 12, // 3% Annual
        penalty: 0.10,        // 10% Early Withdrawal
        maxAge: 110
    },
    simulationCache: {},

    runMightyAnalysis: function () {
        const $btn = $('#runRetirementCalc');
        const currentAge = parseFloat($('#currentAge').val()) || 28;
        const retireAge = parseFloat($('#retireAge').val()) || 62;
        const retirementOffset = Math.round((retireAge - currentAge) * 12);

        $btn.prop('disabled', true).html('<span class="spinner-border spinner-border-sm"></span> Simulating...');

        $.get("AnalysisAJAX", {
            mode: "2",
            level: "0",
            retirementOffset: retirementOffset,
            monthsForward: (this.settings.maxAge - currentAge) * 12,
            monthsBack: 24
        }, (data) => {
            this.processSimulation(data || []);
            $btn.prop('disabled', false).text('Simulate');
        }, "json").fail(() => {
            $btn.prop('disabled', false).text('Error');
        });
    },

    processSimulation: function (forecastData) {
        const startAge = parseFloat($('#currentAge').val()) || 28;
        const targetRetireAge = parseFloat($('#retireAge').val()) || 62;
        const baseAnnual401k = parseFloat($('#annual401k').val()) || 0;
        const startYear = 2026;

        let liq = parseFloat($('#retireLiquid').val()) || 0;
        let lock = parseFloat($('#retireLocked').val()) || 0;
        let interest = 0;
        this.simulationCache = {};
        const monthlySubData = {};

        // Flatten List<List<SubCategory_VM>> from Java
        forecastData.forEach(monthList => {
            if (monthList && monthList.length > 0) {
                const mIdx = parseInt(monthList[0].year);
                monthlySubData[mIdx] = monthList;
            }
        });

        const maxDataIdx = Math.max(...Object.keys(monthlySubData).map(Number), 0);

        // ... (previous variables like liq, lock)

        let timeline = [];
        let m = 1, curAge = startAge;

// INITIALIZE ALL ANNUAL TRACKERS HERE
        let yExp = 0, yInc = 0, yLiqDraw = 0, yLockDraw = 0;
        let y401k = 0, ySQLInvest = 0, yBonusInvest = 0, yFun = 0, ySlush = 0, ySurplus = 0;
        let yInterest = 0;
        let currentYearSubcategories = [];

        while (curAge < this.settings.maxAge) {
            interest = 0;
            // FIX: Move these declarations to the top of the loop
            curAge = startAge + (m / 12);
            let yearsPassed = Math.floor(m / 12);
            let isRetired = (curAge >= targetRetireAge);

            // Now yearsPassed is safe to use
            let m401k = (baseAnnual401k + (yearsPassed * 500)) / 12;
            if (isRetired) {
                m401k = 0;
            }
            // 1. Growth
            //FIX INTEREST
            interest += (liq + lock) * (this.settings.growth)
            liq *= (1 + this.settings.growth);
            lock *= (1 + this.settings.growth);

            let monthlyDataItems = monthlySubData[m] || [];
            // 1. Data Resolution: Granular categorization
            let mOut = 0, mIn = 0, mInvest = 0;

            monthlyDataItems.forEach(item => {
                const amt = parseFloat(item.amount) || 0;
                const type = (item.transactionType || '').toLowerCase().trim();

                if (type === 'income') {
                    mIn += amt;
                } else if (type === 'investment' || item.category_Name === 'Investment') {
                    // Ensure all investment-like categories are caught here
                    mInvest += amt;
                } else {
                    mOut += amt; // Pure expenses/bills only
                }
            });

            // 2. Calculate Split (Corrected Math)
            // Income - Bills - Regression Investments = Leftover
            let freeCash = mIn - mOut - mInvest;

            let bonusInvest = freeCash * 0.50;
            let fun = freeCash * 0.30;
            let slush = freeCash * 0.20;
            // 2. THE RETIREMENT OVERRIDE
            if (isRetired) {
                freeCash = 0;
                m401k = 0;       // Zeroes the 401k Dropdown/Step-up
                mInvest = 0;     // Zeroes the SQL/Regression Investments ("Other Accounts")
                bonusInvest = 0; // Zeroes the 50% Split
                fun = 0;         // Zeroes the 30% Split
                slush = 0;       // Zeroes the 20% Split
                // Note: We do NOT zero mIn or mOut, as those are your Social Security/Bills
            }

            // 3. Increment Annual Totals
            yExp += mOut;
            yInc += mIn;
            y401k += m401k;
            ySQLInvest += mInvest;
            yBonusInvest += bonusInvest;
            yFun += fun;
            ySlush += slush;
            yInterest += interest;

            if (isRetired) {
                bonusInvest = 0;
                fun = 0;
                slush = 0;
            }


            currentYearSubcategories.push(...monthlyDataItems);

            // 4. Cashflow Logic
            if (!isRetired) {
                // Compound the "Locked" 401k
                lock += m401k;
                // SQL Investments go to Liquid
                liq += mInvest;

                if (mIn >= (mOut + mInvest)) {
                    liq += bonusInvest;
                    ySurplus += (mInvest + bonusInvest);
                } else {
                    let deficit = (mOut + mInvest) - mIn;
                    liq -= deficit;
                    yLiqDraw += deficit;
                }
            } else {
                // 1. Apply Income (Pensions/SS) to the month first
                // Note: mIn is already added to yInc globally

                if ((mIn + liq) >= mOut) {
                    // We have enough in Liquid + Monthly Income to cover bills
                    let netChange = mIn - mOut;

                    if (netChange < 0) {
                        // We had to pull the difference from Liquid
                        yLiqDraw += Math.abs(netChange);
                    }

                    liq += netChange; // Adjust balance (will go up or down)
                } else {
                    // Liquid is exhausted! We need the "Locked" 401k
                    let deficit = mOut - (mIn + liq);

                    // Everything left in Liquid is used up
                    yLiqDraw += liq;
                    liq = 0;

                    // Calculate 401k hit with penalty if applicable
                    let pen = (curAge < 59.5) ? (1 + this.settings.penalty) : 1;
                    let lockWithdrawal = deficit * pen;

                    yLockDraw += lockWithdrawal;
                    lock -= lockWithdrawal;
                }
            }

            // 5. Annual Aggregation
            if (m % 12 === 0) {
                const ageKey = Math.floor(curAge);
                timeline.push({
                    x: ageKey,
                    year: startYear + yearsPassed,
                    liq: Math.round(Math.max(0, liq)),
                    lock: Math.round(Math.max(0, lock)),
                    totalExp: Math.round(yExp),
                    totalInc: Math.round(yInc),
                    val401k: Math.round(y401k),
                    valSQL: Math.round(ySQLInvest),
                    // ADD THIS LINE:
                    valFree: Math.round(yInc - yExp - ySQLInvest),
                    valBonus: Math.round(yBonusInvest),
                    totalFun: Math.round(yFun),
                    totalSlush: Math.round(ySlush),
                    liqDraw: Math.round(yLiqDraw),
                    lockDraw: Math.round(yLockDraw),
                    surplus: Math.round(ySurplus),
                    retired: isRetired,
                    interest: Math.round(yInterest)
                });

                this.simulationCache[ageKey] = {
                    details: this.aggregateYear(currentYearSubcategories),
                    label: `Age ${ageKey} (${startYear + yearsPassed})`
                };

                // 6. RESET ALL TRACKERS
                yExp = 0;
                yInc = 0;
                yLiqDraw = 0;
                yLockDraw = 0;
                ySurplus = 0;
                y401k = 0;
                ySQLInvest = 0;
                yBonusInvest = 0;
                yFun = 0;
                ySlush = 0;
                currentYearSubcategories = [];
                yInterest = 0;
            }
            if (isRetired && liq <= 0 && lock <= 0) break;
            m++;
        }
        this.renderMightyChart(timeline, targetRetireAge);
        this.renderSummaryTable(timeline);
    },


    aggregateYear: function (monthlyItems) {
        const categories = {};
        monthlyItems.forEach(item => {
            const catName = item.category_Name || 'Uncategorized';
            if (!categories[catName]) {
                categories[catName] = {
                    category_Name: catName,
                    transactionType: item.transactionType,
                    amount: 0
                };
            }
            categories[catName].amount += (parseFloat(item.amount) || 0);
        });
        return Object.values(categories);
    },

    renderSummaryTable: function (timeline) {
        // Split the data
        const workingData = timeline.filter(d => !d.retired);
        const retiredData = timeline.filter(d => d.retired);

        // Helper to build rows for Table 1 (Working)
        let workingRows = workingData.map(d => `
        <tr onclick="RetirementEngine.showAgeDetails(${d.x})" style="cursor:pointer">
            <td><b>${d.x}</b></td>
            <td>${d.year}</td>
            <td class="text-success">$${d.totalInc.toLocaleString()}</td>
            <td class="text-danger">$${d.totalExp.toLocaleString()}</td>
            <td class="text-primary">$${d.val401k.toLocaleString()}</td>
            <td class="text-primary">$${d.valSQL.toLocaleString()}</td>
            <td class="fw-bold" style="background: rgba(0,0,0,0.03)">$${(d.valFree || 0).toLocaleString()}</td>
            <td class="text-primary" style="font-weight:bold">$${d.valBonus.toLocaleString()}</td>
            <td class="text-info" style="opacity: 0.8">$${d.totalFun.toLocaleString()}</td>
            <td class="text-secondary" style="opacity: 0.8">$${d.totalSlush.toLocaleString()}</td>
            <td><b>$${(d.liq + d.lock).toLocaleString()}</b></td>
        </tr>`).join('');

        // Helper to build rows for Table 2 (Retired - Simplified)
        let retiredRows = retiredData.map(d => `
        <tr onclick="RetirementEngine.showAgeDetails(${d.x})" style="cursor:pointer">
            <td><b>${d.x}</b></td>
            <td>${d.year}</td>
            <td class="text-danger">$${d.totalExp.toLocaleString()}</td>
            <td class="text-success">$${d.totalInc.toLocaleString()}</td>
            <td class="text-success">-$${d.liqDraw.toLocaleString()}</td>
            <td class="text-warning">-$${d.lockDraw.toLocaleString()}</td>
            <td class="text-success">$${d.interest.toLocaleString()}</td>
            <td><b>$${(d.liq + d.lock).toLocaleString()}</b></td>
            <td><span class="badge bg-danger">Retired</span></td>
        </tr>`).join('');

        // Inject Tabs and Tab Panes
        $('#retirementTableContainer').html(`
        <ul class="nav nav-tabs" id="retirementTabs" role="tablist">
            <li class="nav-item">
                <button class="nav-link active" id="working-tab" data-bs-toggle="tab" data-bs-target="#working-pane" type="button">Accumulation Phase</button>
            </li>
            <li class="nav-item">
                <button class="nav-link" id="retired-tab" data-bs-toggle="tab" data-bs-target="#retired-pane" type="button">Distribution Phase</button>
            </li>
        </ul>
        <div class="tab-content" id="retirementTabContent">
            <div class="tab-pane fade show active" id="working-pane" role="tabpanel">
                <table class="table table-sm table-striped table-hover mt-2" style="font-size: 0.75rem;">
                    <thead class="table-dark">
                        <tr>
                            <th>Age</th><th>Year</th><th>Income</th><th>Bills</th>
                            <th>401k</th><th>Regress</th><th>Free</th><th>Bonus</th>
                            <th>Fun</th><th>Slush</th><th>Net Worth</th>
                        </tr>
                    </thead>
                    <tbody>${workingRows}</tbody>
                </table>
            </div>
            <div class="tab-pane fade" id="retired-pane" role="tabpanel">
                <table class="table table-sm table-striped table-hover mt-2" style="font-size: 0.85rem;">
                    <thead class="table-dark">
                        <tr>
                            <th>Age</th><th>Year</th><th>Annual Bills</th><th>Income</th>
                            <th>Liq Draw</th><th>Locked Draw</th><th>Interest</th><th>Net Worth</th><th>Status</th>
                        </tr>
                    </thead>
                    <tbody>${retiredRows}</tbody>
                </table>
            </div>
        </div>
    `);
    },

    showAgeDetails: function (age) {
        const cacheEntry = this.simulationCache[age];
        if (cacheEntry && typeof window.updateFromSimulation === 'function') {
            window.updateFromSimulation(cacheEntry.details, cacheEntry.label);
        }
    },

    renderMightyChart: function (data, retireAge) {
        const self = this;
        Highcharts.chart('retirementChartContainer', {
            chart: {type: 'area', height: 400, backgroundColor: 'transparent'},
            title: {text: 'Wealth Architect (v52.0)'},
            xAxis: {
                tickInterval: 5,
                plotLines: [{
                    color: '#e74c3c',
                    width: 2,
                    value: retireAge,
                    zIndex: 5,
                    label: {text: 'Retirement', style: {color: '#e74c3c'}}
                }]
            },
            yAxis: {title: {text: 'USD'}, labels: {format: '${value:,.0f}'}},

            // --- NEW ENHANCED TOOLTIP ---
            tooltip: {
                shared: true,
                useHTML: true,
                formatter: function () {
                    const d = this.points[0].point; // Access the underlying data object
                    const totalNet = d.liq + d.lock;
                    const statusClass = d.retired ? 'text-danger' : 'text-success';

                    let s = `<div style="padding: 10px; min-width: 200px;">
                            <b style="font-size: 1.1rem;">Age ${d.x} (${d.year})</b><br/>
                            <span class="${statusClass}" style="font-weight:bold">${d.retired ? 'RETIRED' : 'ACCUMULATING'}</span><hr/>`;

                    // Balances Section
                    s += `<b>Balances:</b><br/>
                      <span style="color:#27ae60">●</span> Liquid: <b>$${d.liq.toLocaleString()}</b><br/>
                      <span style="color:#2980b9">●</span> 401k: <b>$${d.lock.toLocaleString()}</b><br/>
                      Total: <b>$${totalNet.toLocaleString()}</b><hr/>`;

                    if (!d.retired) {
                        // Growth Contributors Section
                        s += `<b>Yearly Contributions:</b><br/>
                          401k Dropdown: <span class="text-primary">$${d.val401k.toLocaleString()}</span><br/>
                          SQL/Regression: <span class="text-primary">$${d.valSQL.toLocaleString()}</span><br/>
                          50% Bonus: <span class="text-primary" style="font-weight:bold">$${d.valBonus.toLocaleString()}</span>`;
                    } else {
                        // UPDATED: Withdrawal Section with SS and Penalty logic
                        const hasPenalty = d.x < 59.5 && d.lockDraw > 0;

                        s += `<b>Yearly Burn & Income:</b><br/>
                          Social Security/Pensions: <span class="text-success">+$${d.totalInc.toLocaleString()}</span><br/>
                          Interest: <span class="text-success">+$${d.interest.toLocaleString()}</span><br/>

                          Annual Bills: <span class="text-danger">-$${d.totalExp.toLocaleString()}</span><hr/>
                          
                          <b>Net Withdrawals:</b><br/>
                          Liquid Draw: <span class="text-warning">-$${d.liqDraw.toLocaleString()}</span><br/>
                          Locked Draw: <span class="text-warning">-$${d.lockDraw.toLocaleString()}</span>`;

                        if (hasPenalty) {
                            s += `<br/><span style="color: #e74c3c; font-size: 0.8rem; font-weight:bold;">
                                ⚠️ Includes 10% Early Withdrawal Penalty
                              </span>`;
                        }
                    }

                    s += `</div>`;
                    return s;
                }
            },

            plotOptions: {
                series: {
                    stacking: 'normal',
                    marker: {enabled: false},
                    point: {
                        events: {
                            click: function () {
                                self.showAgeDetails(this.x);
                            }
                        }
                    }
                }
            },
            series: [
                // Note: We use the full data object 'd' in the map so the formatter can see all properties
                {name: '401k', color: '#2980b9', data: data.map(d => ({...d, y: d.lock}))},
                {name: 'Liquid', color: '#27ae60', data: data.map(d => ({...d, y: d.liq}))}
            ]
        });
    }

};

$(document).ready(function () {
    $('#runRetirementCalc').on('click', function () {
        RetirementEngine.runMightyAnalysis();
    });
});