$(document).ready(function() {


    $('button[data-bs-toggle="pill"]').on('show.bs.tab', function (e) {
        const targetId = $(e.target).attr('data-bs-target');
        const $chart = $('#categoryAnalysisChart');
        const $chartCol = $('#categoryAnalysisSection > div:first-child');
        const $tabsCol = $('#categoryAnalysisSection > div:last-child');

        if (targetId !== '#tab-performance' && window.originalChartState && window.analysisChart) {
            console.log("Restoring original engine view...");

            window.analysisChart.update({
                xAxis: {categories: window.originalChartState.categories},
                title: {text: window.originalChartState.title}
            }, false);

            while (window.analysisChart.series.length > 0) window.analysisChart.series[0].remove(false);

            window.originalChartState.series.forEach(s => {
                window.analysisChart.addSeries(s, false);
            });

            window.analysisChart.redraw();

            // Clear the buffer so we can capture it again if they change categories
            window.originalChartState = null;
        }
        // PHASE 1: "Store" the chart
        $chart.removeClass('chart-ready').addClass('chart-reworking');

        // PHASE 2: Wait for the blur/fade to finish (300ms)
        setTimeout(() => {

            // PHASE 3: Swap the Layout (Instantly or via CSS)
            if (targetId === '#tab-performance') {
                $chartCol.removeClass('col-lg-8').addClass('col-lg-6');
                $tabsCol.removeClass('col-lg-4').addClass('col-lg-6');
            } else {
                $chartCol.removeClass('col-lg-6').addClass('col-lg-8');
                $tabsCol.removeClass('col-lg-6').addClass('col-lg-4');
            }

            // PHASE 4: Recalculate while the user sees nothing but a blur
            if (window.analysisChart) {
                window.analysisChart.reflow();
            }

            // PHASE 5: "Reveal" the chart in its new home
            // We wait an extra 200ms to let the Bootstrap columns settle
            setTimeout(() => {
                $chart.removeClass('chart-reworking').addClass('chart-ready');
            }, 600);

        }, 300);
    });





    // 1. Scope definition
    const pickers = {};
    let categoryIdToDelete = null;

    // 2. Event Listeners (Calling named functions)
    $("#btnAutoAssign").on("click", function () {
        const modalEl = document.getElementById('autoAssignModal');
        const autoModal = new bootstrap.Modal(modalEl);
        autoModal.show();
    });
    $(document).on('click', '.color-swatch-trigger', (e) => handleColorPickerToggle(e, pickers));
    $(document).on('click', '#confirmDeleteBtn', handleConfirmDelete);
    $(document).on('click', '.strategy-icon', (e) => handleStrategyClick(e, pickers));
    $(document).on('blur', '.category-text', (e) => handleNameBlur(e, pickers));

    // Global clicks to close popovers
    $(document).on('click', handleGlobalClick);
    $(document).on('click', '.picker-popover', (e) => e.stopPropagation());
    $("#confirmAutoAssignBtn").on("click", handleAutoAssignExecution);

    // 3. Initial UI Setup
    // Any logic that needs to run on load goes here


    // 1. Mouse Enter: Highlight the line on the chart
    $(document).on('mouseenter', '.forecast-row', function () {
        const methodKey = $(this).data('series'); // e.g., 'regression'
        const chart = Highcharts.charts.find(c => c && c.renderTo.id === 'categoryAnalysisChart');

        if (chart) {
            chart.series.forEach(s => {
                if (s.options.id === methodKey) {
                    s.setState('hover');
                    s.group.toFront();
                } else if (s.options.id !== 'historicalTruth') {
                    // Dim everything except the historical data and the hovered line
                    s.setState('inactive');
                }
            });
        }
    });

// 2. Mouse Leave: Reset the chart to show all lines normally
    $(document).on('mouseleave', '.forecast-row', function () {
        const chart = Highcharts.charts.find(c => c && c.renderTo.id === 'categoryAnalysisChart');
        if (chart) {
            chart.series.forEach(s => s.setState(''));
        }
    });

    // ADD THIS HERE:
    $(document).on('click', '.forecast-row', function (e) {
        const methodKey = $(this).data('series');
        const currentId = $('#analysisSubcatId').val();
        const mapping = {
            'regression': 'REGRESSION', 'alphaSpike': 'ALPHA_SPIKE',
            'lvcf': 'LVCF', 'avgStrict': 'AVG_STRICT',
            'inflation': 'INFLATION_ONLY', 'zeroSum': 'ZERO_SUM'
        };
        const $targetIcon = $(`.modern-cat-card[data-id="${currentId}"] .strategy-icon[data-val="${mapping[methodKey]}"]`);

        if ($targetIcon.length > 0) {
            handleStrategyClick({
                currentTarget: $targetIcon[0], stopPropagation: () => {
                }
            }, pickers);
        }
    });
});

// --- FUNCTION DEFINITIONS (Implementation) ---

/**
 * Handles the Projection Engine Auto-Assign AJAX call
 */
function handleAutoAssign() {
    const $btn = $(this);
    const originalHtml = $btn.html();

    if (!confirm("This will analyze your spending history and automatically assign the best projection model to every category. Continue?")) {
        return;
    }

    $.ajax({
        url: 'autoAssignProjections',
        type: 'POST',
        beforeSend: function () {
            $btn.prop("disabled", true).addClass("btn-secondary").removeClass("btn-primary");
            $btn.html('<span class="spinner-border spinner-border-sm me-2"></span> Analyzing Data...');
        },
        success: function (response) {
            if (response == "1" || response == 1) {
                alert("Success! Your categories have been optimized based on historical trends.");
                location.reload();
            } else {
                alert("Analysis complete, but no changes were made. (Code: " + response + ")");
            }
        },
        error: function () {
            alert("Connection error. The projection engine could not be reached.");
        },
        complete: function () {
            $btn.prop("disabled", false).addClass("btn-primary").removeClass("btn-secondary");
            $btn.html(originalHtml);
        }
    });
}

/**
 * Handles iro.js Color Picker initialization and toggling
 */
function handleColorPickerToggle(e, pickers) {
    e.stopPropagation();
    const $this = $(e.currentTarget);
    const $card = $this.closest('.modern-cat-card');
    const id = $card.data('id');

    if (!id) return;

    const $popover = $this.siblings('.picker-popover');

    if ($popover.hasClass('d-none')) {
        $('.modern-cat-card').removeClass('card-on-top');
        $card.addClass('card-on-top');
    } else {
        $card.removeClass('card-on-top');
    }

    $('.picker-popover').not($popover).addClass('d-none');
    $popover.toggleClass('d-none');

    if (!pickers[id]) {
        const canvas = $popover.find('.wheel-canvas')[0];
        const initialColor = $this.css('background-color');

        pickers[id] = new iro.ColorPicker(canvas, {
            width: 150,
            color: initialColor,
            layout: [
                {component: iro.ui.Wheel},
                {component: iro.ui.Slider, options: {sliderType: 'hue'}}
            ]
        });

        pickers[id].on('color:change', function (color) {
            $card.find('.color-swatch-trigger').css('background-color', color.hexString);
            $card.find('.card-accent').css('background-color', color.hexString);
        });

        pickers[id].on('input:end', function (color) {
            sendUpdate($card, color.hexString, pickers);
        });
    }
}

/**
 * Handles Category Deletion via the Modal
 */
async function handleConfirmDelete() {
    const $btn = $(this);
    const originalText = $btn.text();
    $btn.prop('disabled', true).html('<span class="spinner-border spinner-border-sm"></span> Deleting...');

    try {
        const response = await fetch('deleteBudgetCategory', {
            method: 'POST',
            body: new URLSearchParams({categoryid: categoryIdToDelete})
        });

        if (response.ok) {
            location.reload();
        } else {
            alert("Error deleting category.");
            $btn.prop('disabled', false).text(originalText);
        }
    } catch (err) {
        console.error(err);
        $btn.prop('disabled', false).text(originalText);
    }
}

/**
 * Handles Strategy Icon Selection
 */
function handleStrategyClick(e, pickers) {
    e.stopPropagation();
    const $icon = $(e.currentTarget);
    const $card = $icon.closest('.modern-cat-card');
    const newVal = $icon.data('val');

    // 1. Define the Brand Colors for each strategy
    // Replace these hex codes with your actual CSS variables or brand colors
    const strategyColors = {
        'REGRESSION': '#0d6efd',    // Bootstrap Blue
        'ALPHA_SPIKE': '#fd7e14',   // Orange
        'LVCF': '#6f42c1',          // Purple
        'AVG_STRICT': '#198754',    // Green
        'INFLATION_ONLY': '#0dcaf0', // Cyan
        'ZERO_SUM': '#6c757d'       // Gray
    };

    // 2. Update Card UI
    $card.attr('data-strategy', newVal);

    // Reset all icons in this card to "Muted/Gray"
    $card.find('.strategy-icon').css({
        'color': '',
        'font-weight': 'normal',
        'opacity': '0.5'
    }).removeClass('active-strategy');

    // Apply the "Brand Color" to the selected icon
    $icon.css({
        'color': strategyColors[newVal],
        'font-weight': 'bold',
        'opacity': '1'
    }).addClass('active-strategy');

    // 3. Update Sidebar UI (The Model Comparison Tab)
    const mapping = {
        'REGRESSION': 'regression',
        'ALPHA_SPIKE': 'alphaSpike',
        'LVCF': 'lvcf',
        'AVG_STRICT': 'avgStrict',
        'INFLATION_ONLY': 'inflation',
        'ZERO_SUM': 'zeroSum'
    };

    const sidebarKey = mapping[newVal];
    const $sidebarBtn = $(`.forecast-row[data-series="${sidebarKey}"]`);

    if ($sidebarBtn.length > 0) {
        $('.forecast-row').removeClass('active border-primary').css('background-color', '');
        $sidebarBtn.addClass('active border-primary')
            .css('background-color', 'rgba(13, 110, 253, 0.05)');
    }

    // Find the lock icon within this specific card
    const lockIcon = $card.find('.lock-trigger');
    console.log(lockIcon)
    // If it's currently unlocked (bi-unlock), trigger the toggle
    //if ($lockIcon.hasClass('bi-unlock')) {
    lockIcon[0].click();
    // }

    // 4. Persistence
    const hex = getHexFromCard($card, pickers);
    sendUpdate($card, hex, pickers);
}

/**
 * Handles Name update when focus is lost
 */
function handleNameBlur(e, pickers) {
    const $card = $(e.currentTarget).closest('.modern-cat-card');
    const hex = getHexFromCard($card, pickers);
    sendUpdate($card, hex, pickers);
}

/**
 * Closes pickers when clicking outside
 */
function handleGlobalClick() {
    $('.picker-popover').addClass('d-none');
    $('.modern-cat-card').removeClass('card-on-top');
}

// --- HELPER & UTILITY FUNCTIONS ---

function getHexFromCard($card, pickers) {
    const id = $card.data('id');
    return pickers[id] ? pickers[id].color.hexString : rgbToHex($card.find('.card-accent').css('background-color'));
}

async function sendUpdate($card, hexColor, pickers) {
    const id = $card.data('id');
    const name = $card.find('.category-text').text().trim();
    const parentId = $card.find('select').val();
    const strategyId = $card.attr('data-strategy') || 'AVG_STRICT';

    if (!hexColor.startsWith('#')) hexColor = '#' + hexColor;

    try {
        await fetch('editCategory', {
            method: 'POST',
            body: new URLSearchParams({
                category_ID: id,
                inputcategoryCategory_Name: name,
                inputcategoryColor_id: hexColor,
                inputsub_categoryparent_category_id: parentId,
                inputsub_categoryprojection_strategy_ID: strategyId
            })
        });
    } catch (err) {
        console.error("Update failed:", err);
    }
}

function rgbToHex(rgb) {
    if (!rgb || !rgb.startsWith('rgb')) return '#0d6efd';
    const vals = rgb.match(/\d+/g);
    return "#" + vals.map(x => parseInt(x).toString(16).padStart(2, '0')).join("");
}

// Global window functions for inline HTML calls (unchanged)
window.confirmDeleteCategory = function (id, name) {
    categoryIdToDelete = id;
    $('#deleteTargetName').text(name);
    const modalEl = document.getElementById('deleteCategoryModal');
    if (window.bootstrap && window.bootstrap.Modal) {
        new bootstrap.Modal(modalEl).show();
    } else {
        $(modalEl).modal('show');
    }
};

window.updateParentCategory = function (id, parentId) {
    const $card = $(`.modern-cat-card[data-id="${id}"]`);
    const type = $card.find('select option:selected').data('type') || '';
    $card.removeClass('border-income border-investment border-expense border-transfer').addClass('border-' + type);
    sendUpdate($card, getHexFromCard($card, {}), {});
};

function handleAutoAssignExecution() {
    const $btn = $(this);
    const $modal = $('#autoAssignModal');
    const originalHtml = $btn.html();

    $.ajax({
        url: 'autoAssignProjections',
        type: 'POST',
        beforeSend: function () {
            // Disable interactions to prevent double-submits
            $btn.prop("disabled", true);
            $modal.find('.btn-close, .btn-light').addClass('d-none');
            $btn.html('<span class="spinner-border spinner-border-sm me-2"></span> Running Optimization...');
        },
        success: function (response) {
            // parse as int just in case it comes back as a string
            const result = parseInt(response);

            if (result >= 0) {
                // Success State
                $btn.removeClass('btn-primary').addClass('btn-success')
                    .html('<i class="bi bi-check-lg me-2"></i> Finished, ' + result + ' updates!');

                // Short delay so the user sees the "Success" state before the flash
                setTimeout(() => {
                    location.reload();
                }, 1000);
            } else {
                // Negative response = Error Code
                alert("The engine returned an error code: " + result);
                resetAutoModal($btn, originalHtml);
            }
        },
        error: function () {
            alert("The Projection Engine is temporarily unavailable.");
            resetAutoModal($btn, originalHtml);
        }
    });
}

/**
 * Toggles the "Locked" status of a category projection.
 * If locked, the Projection Engine should ignore this category during Auto-Assign.
 */
function handleLockToggle(e, id) {
    e.stopPropagation();
    const $icon = $(e.currentTarget);
    const isLocking = $icon.hasClass('bi-unlock');
    let mode = 0;
    if (isLocking) {
        mode = 1;
    }

    $.ajax({
        url: 'LockSubcategory',
        type: 'POST',
        data: {subcategoryid: id, mode: mode},
        beforeSend: function () {
            $icon.css('opacity', '0.2'); // Visual pending state
        },
        success: function (response) {
            if (response == "1" || response == 1) {
                // Toggle the icon classes
                $icon.toggleClass('bi-unlock bi-lock-fill text-warning');
                // Optional: Add a visual cue to the card
                $icon.closest('.modern-cat-card').toggleClass('is-locked-visual');
            } else {
                alert("Failed to update lock status.");
            }
        },
        error: function () {
            alert("Connection error while toggling lock.");
        },
        complete: function () {
            $icon.css('opacity', '');
        }
    });
}


/**
 * Handles the Gear Icon click: Expands analysis, fetches real projection data,
 * and populates the multi-tab info panel.
 */
/**
 * Handles the Gear Icon click: Expands analysis, fetches real projection data,
 * and populates the multi-tab info panel.
 */
function handleGearClick(e, id) {
    e.stopPropagation();

    $('#categoryAnalysisSection').attr('data-current-id', id);


    // CRITICAL: Store the ID so the sidebar knows which card to 'click' later
    if ($('#analysisSubcatId').length === 0) {
        $('body').append(`<input type="hidden" id="analysisSubcatId" value="${id}">`);
    } else {
        $('#analysisSubcatId').val(id);
    }

    const $card = $(`.modern-cat-card[data-id="${id}"]`);

    // 1. Basic Data Extraction from the clicked card
    const name = $card.find('.category-text').text().trim();
    const strategy = $card.attr('data-strategy'); // e.g. "REGRESSION"
    const isLocked = $card.find('.lock-trigger').hasClass('bi-lock-fill');

    // 2. Populate Tab 1 (Overview) - Immediate UI feedback
    $('#detailCatName').text(name);
    $('#detailStrategy').text(strategy);
    $('#detailLockStatus').html(isLocked ?
        '<span class="badge bg-warning-subtle text-warning border border-warning"><i class="bi bi-lock-fill"></i> Locked</span>' :
        '<span class="badge bg-light text-muted border"><i class="bi bi-unlock"></i> Mutable</span>'
    );

    // 3. UI Transitions: Open section and scroll
    $('#analysisChartTitle').html(`<span class="spinner-border spinner-border-sm me-2"></span> Analyzing ${name}...`);

    $('#categoryAnalysisSection').slideDown(400, function () {
        // PASS STRATEGY HERE: This ensures updateAnalysisTabs knows which row to highlight
        fetchProjectionData(id, name, strategy);
    });

    // Force reset to first tab on every click
    $('#overview-tab').tab('show');
    window.scrollTo({top: 0, behavior: 'smooth'});
}

/**
 * Helper to fetch the 60-month data array and update the Highchart + Tabs
 */
function fetchProjectionData(subcatId, name, strategy) {
    $.ajax({
        url: 'AnalyzeCategoryAJAX',
        type: 'GET',
        data: {subcatId: subcatId},
        dataType: 'json',
        success: function (rawData) {
            if (rawData === "-1" || rawData === "-2") {
                console.error("Budget Engine Error: " + data);
                return;
            }
            const data = anchorProjections(rawData);

            const categories = data.map(d => (d.monthDate ? d.monthDate.substring(0, 7) : "???"));

            // Build the series array with IDs for interaction logic
            const seriesData = [
                {
                    id: 'historicalTruth',
                    name: 'Historical Truth',
                    data: data.map(d => d.historicalTruth),
                    color: '#2c3e50',
                    type: 'areaspline',
                    fillOpacity: 0.1
                },
                {
                    id: 'regression',
                    name: 'Regression',
                    data: data.map(d => d.regression),
                    color: '#0d6efd',
                    dashStyle: 'Dash'
                },
                {
                    id: 'avgStrict',
                    name: 'Strict Average',
                    data: data.map(d => d.avgStrict),
                    color: '#198754',
                    dashStyle: 'Dot'
                },
                {id: 'lvcf', name: 'LVCF', data: data.map(d => d.lvcf), color: '#6f42c1', dashStyle: 'ShortDot'},
                {
                    id: 'inflation',
                    name: 'Inflation (0.3%)',
                    data: data.map(d => d.inflation),
                    color: '#0dcaf0',
                    dashStyle: 'LongDash'
                },
                {
                    id: 'alphaSpike',
                    name: 'Alpha Spike',
                    data: data.map(d => d.alphaSpike),
                    color: '#fd7e14',
                    dashStyle: 'DashDot'
                },
                {
                    id: 'zeroSum',
                    name: 'Zero Sum',
                    data: data.map(d => d.zeroSum),
                    color: '#6c757d',
                    dashStyle: 'Solid',
                    lineWidth: 1
                }
            ];

            // Re-initialize the chart
            Highcharts.chart('categoryAnalysisChart', {
                chart: {type: 'line', backgroundColor: 'transparent'},
                title: {text: null},
                xAxis: {categories: categories, crosshair: true, tickInterval: 6},
                yAxis: {title: {text: 'Monthly Flow ($)'}, labels: {format: '${value}'}},
                tooltip: {shared: true, valuePrefix: '$', valueDecimals: 2},
                credits: {enabled: false},
                plotOptions: {
                    series: {
                        marker: {enabled: false},
                        states: {
                            hover: {lineWidthPlus: 3},
                            inactive: {opacity: 0.3}
                        }
                    }
                },
                series: seriesData
            });

            $('#analysisChartTitle').text("Analysis: " + name);
            $('#detailCatName').text(name);

            // Update the math and the sidebar tabs
            updateAnalysisTabs(data, strategy);
            updateGrowthImpactUI(data);
        },
        error: function (xhr, status, error) {
            console.error("AJAX Failure: ", error);
        }
    });
}

/**
 * Calculates Regression math and populates the sidebar tabs
 */
/**
 * Calculates Regression math and populates the sidebar tabs with Growth Impact
 */
function updateAnalysisTabs(data, strategy) {
    const history = data.filter(d => d.historicalTruth !== null && d.historicalTruth !== undefined);
    const forecast = data.filter(d => d.historicalTruth === null || d.historicalTruth === undefined);
    const n = history.length;

    // --- 1. REGRESSION MATH (Tab 3) ---
    let sumX = 0, sumY = 0, sumXY = 0, sumX2 = 0, sumY2 = 0;
    let histAvg = 0;

    if (n > 1) {
        history.forEach((d, i) => {
            const x = i + 1;
            const y = d.historicalTruth;
            sumX += x;
            sumY += y;
            sumXY += x * y;
            sumX2 += x * x;
            sumY2 += y * y;
        });

        histAvg = sumY / n; // Baseline for history

        const slope = (n * sumXY - sumX * sumY) / (n * sumX2 - sumX * sumX);
        const intercept = (sumY - slope * sumX) / n;
        const numerator = (n * sumXY - sumX * sumY);
        const denPart1 = (n * sumX2 - sumX * sumX);
        const denPart2 = (n * sumY2 - sumY * sumY);
        const rSquared = (denPart1 * denPart2 === 0) ? 0 : Math.pow(numerator / Math.sqrt(denPart1 * denPart2), 2);

        $('#mathSlope').text(slope.toFixed(2));
        $('#mathIntercept').text(intercept.toFixed(2));
        $('#mathRSquared').text(rSquared.toFixed(3));

        let insight = "This category follows a stable trend.";
        let alertClass = "alert-info";
        if (rSquared < 0.3) {
            insight = "<i class='bi bi-exclamation-triangle-fill'></i> <b>Warning:</b> High volatility (Low R²). Projections are unreliable.";
            alertClass = "alert-warning";
        } else if (slope > 5.0) {
            insight = "<i class='bi bi-graph-up-arrow'></i> <b>Caution:</b> Costs are trending upward significantly.";
            alertClass = "alert-danger";
        }
        $('#tab-regression .alert').removeClass('alert-info alert-warning alert-danger').addClass(alertClass).html(insight);
    }

    // --- 2. FORECAST TOTALS & TWO-ROW BADGES (Tab 2) ---
    const totals = {
        regression: forecast.reduce((sum, d) => sum + (d.regression || 0), 0),
        avgStrict: forecast.reduce((sum, d) => sum + (d.avgStrict || 0), 0),
        alphaSpike: forecast.reduce((sum, d) => sum + (d.alphaSpike || 0), 0),
        lvcf: forecast.reduce((sum, d) => sum + (d.lvcf || 0), 0),
        inflation: forecast.reduce((sum, d) => sum + (d.inflation || 0), 0),
        zeroSum: forecast.reduce((sum, d) => sum + (d.zeroSum || 0), 0)
    };

    let forecastHtml = `<h6 class="fw-bold small text-uppercase text-secondary mb-2">36-Month Model Comparison</h6>`;
    forecastHtml += `<div class="list-group list-group-flush small" id="forecastListGroup">`;
    const reverseMapping = {
        'REGRESSION': 'regression',
        'ALPHA_SPIKE': 'alphaSpike',
        'LVCF': 'lvcf',
        'AVG_STRICT': 'avgStrict',
        'INFLATION_ONLY': 'inflation',
        'ZERO_SUM': 'zeroSum'
    };
    Object.entries(totals).forEach(([key, val]) => {
        const seriesId = reverseMapping[key] || key.toLowerCase(); // Ensure it matches Highcharts IDs
        const modelMonthlyAvg = val / 36;
        const monthlyVariance = modelMonthlyAvg - histAvg;
        const isUp = monthlyVariance > 0;
        const label = key.replace(/([A-Z])/g, ' $1');

        const statusClass = isUp ? 'bg-soft-danger text-danger' : 'bg-soft-success text-success';
        const sign = isUp ? '+' : '-';


        const isActive = reverseMapping[strategy] === key;
        const activeClass = isActive ? 'active border-primary' : 'border-bottom';
        const activeBg = isActive ? 'style="background-color: rgba(13, 110, 253, 0.05);"' : '';

        forecastHtml += `
            <button type="button" 
            class="list-group-item list-group-item-action forecast-row p-1 border-0 border-bottom d-flex align-items-center" data-series="${key}"
            data-series="${seriesId}" ${activeBg}>
                <div class="flex-grow-1 text-start">
                    <div class="fw-bold text-dark text-capitalize mb-1" style="font-size: 0.95rem;">${label}</div>
                    <div class="text-muted small">
                        36-Month Total: <span class="fw-medium text-dark">$${val.toLocaleString(undefined, {maximumFractionDigits: 0})}</span>
                    </div>
                </div>

                <div class="${statusClass} rounded px-3 py-2 d-flex flex-column align-items-center justify-content-center" 
                     style="min-width: 100px; min-height: 58px; border: 1px solid rgba(0,0,0,0.05);">
                    <span class="fw-bold lh-1" style="font-size: 1.1rem;">
                        ${sign}$${Math.abs(monthlyVariance).toFixed(2)}
                    </span>
                    <span class="text-uppercase fw-bold opacity-75 mt-1" style="font-size: 0.6rem; letter-spacing: 0.5px;">
                        Per Month
                    </span>
                </div>
            </button>
        `;
    });

    forecastHtml += `</div>`;

    // Progress Bar Wrapper
    const progressWrapper = `
        <div class="mt-3 pt-3 border-top">
            <h6 class="fw-bold small text-uppercase text-secondary">Retirement Impact</h6>
            <div class="progress mb-2" style="height: 8px; background-color: #e9ecef;">
                <div class="progress-bar bg-info" role="progressbar" style="width: 0%; transition: width 0.6s ease;"></div>
            </div>
            <p class="text-muted mb-0" style="font-size: 0.75rem;">
                This category accounts for <b id="forecastPercent">0%</b> of your projected retirement burn.
            </p>
        </div>
    `;

    $('#tab-forecast').html(forecastHtml + progressWrapper);

    // --- 3. RETIREMENT BURN UPDATES ---
    const lastRow = data[data.length - 1];
    if (lastRow) {
        const burnVal = lastRow.inflation || 0;
        $('#forecastAgeValue').text('$' + burnVal.toLocaleString(undefined, {minimumFractionDigits: 2}) + ' /mo');
        const percent = Math.min((burnVal / 5000) * 100, 100);
        $('#tab-forecast .progress-bar').css('width', percent + '%');
        $('#forecastPercent').text(percent.toFixed(1) + '%');
    }

    // --- 4. PERFORMANCE TAB INITIALIZATION ---
    // This part ensures the dropdown trigger works
    $('#performanceMonthSelect').off('change').on('change', function () {
        console.log("test")
        const selectedMonth = $(this).val();
        fetchPerformanceData(selectedMonth);
    });
}


function initAnalysisChart() {
    Highcharts.chart('categoryAnalysisChart', {
        chart: {type: 'areaspline', backgroundColor: 'transparent'},
        title: {text: null},
        xAxis: {categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun'], crosshair: true},
        yAxis: {title: {text: 'Monthly Flow ($)'}, labels: {format: '${value}'}},
        tooltip: {shared: true, valuePrefix: '$'},
        credits: {enabled: false},
        plotOptions: {areaspline: {fillOpacity: 0.1}},
        series: [{
            name: 'Historical Actuals',
            color: '#2c3e50',
            data: [2500, 2800, 2650, 3100, 2900, 3200]
        }, {
            name: 'Engine Projection',
            color: '#0d6efd',
            dashStyle: 'ShortDash',
            data: [null, null, null, null, null, 3200, 3350, 3500, 3650]
        }]
    });
}
/**
 * Helper to reset modal UI on error
 */
function resetAutoModal($btn, originalHtml) {
    $btn.prop("disabled", false).html(originalHtml);
    $('#autoAssignModal').find('.btn-close, .btn-light').removeClass('d-none');
}

function calculateDollarImpact(data) {
    const history = data.filter(d => d.historicalTruth !== null && d.historicalTruth !== undefined);
    const forecast = data.filter(d => d.regression !== null && d.regression !== undefined);

    if (history.length === 0 || forecast.length === 0) return {monthly: 0, yearly: 0};

    // 1. Average of what you "Used to pay" (Historical Baseline)
    const histAvg = history.reduce((sum, d) => sum + d.historicalTruth, 0) / history.length;

    // 2. Average of what you "Will pay" (Projected Mean)
    const projAvg = forecast.reduce((sum, d) => sum + d.regression, 0) / forecast.length;

    const monthlyDelta = projAvg - histAvg;

    return {
        monthly: monthlyDelta,
        yearly: monthlyDelta * 12,
        totalThreeYear: monthlyDelta * 36,
        isIncreasing: monthlyDelta > 0
    };
}

/**
 * Calculates and displays the raw dollar shift between History and Projection
 * @param {Array} data - The 60-month data array from the Servlet
 */
function updateGrowthImpactUI(data) {
    const history = data.filter(d => d.historicalTruth !== null && d.historicalTruth !== undefined);
    const forecast = data.filter(d => d.regression !== null && d.regression !== undefined);

    if (history.length === 0 || forecast.length === 0) {
        $('#detailInsights').html("<p class='small text-muted'>Insufficient data for growth analysis.</p>");
        return;
    }

    // 1. Calculate Baselines (Averages)
    const histAvg = history.reduce((sum, d) => sum + d.historicalTruth, 0) / history.length;
    const projAvg = forecast.reduce((sum, d) => sum + d.regression, 0) / forecast.length;

    // 2. Calculate the "Shift" (The Delta)
    const monthlyShift = projAvg - histAvg;
    const yearlyShift = monthlyShift * 12;
    const threeYearBurn = monthlyShift * 36;

    const isIncreasing = monthlyShift > 0;
    const colorClass = isIncreasing ? 'text-danger' : 'text-success';
    const arrowIcon = isIncreasing ? 'bi-graph-up-arrow' : 'bi-graph-down-arrow';
    const statusText = isIncreasing ? 'Expanding' : 'Contracting';

    // 3. Build the HTML UI
    let impactHtml = `
        <div class="p-3 border rounded bg-light mt-2 animate__animated animate__fadeIn">
            <div class="d-flex justify-content-between align-items-center mb-3">
                <h6 class="fw-bold small text-uppercase text-secondary mb-0">Budget Velocity</h6>
                <span class="badge ${isIncreasing ? 'bg-soft-danger text-danger' : 'bg-soft-success text-success'} rounded-pill">
                    <i class="bi ${arrowIcon} me-1"></i>${statusText}
                </span>
            </div>
            
            <div class="row text-center g-0">
                <div class="col-6 border-end">
                    <span class="d-block text-muted" style="font-size: 0.7rem;">Monthly Change</span>
                    <span class="h5 mb-0 fw-bold ${colorClass}">
                        ${isIncreasing ? '+' : ''}$${monthlyShift.toFixed(2)}
                    </span>
                </div>
                <div class="col-6">
                    <span class="d-block text-muted" style="font-size: 0.7rem;">Annual Change</span>
                    <span class="h5 mb-0 fw-bold ${colorClass}">
                        ${isIncreasing ? '+' : ''}$${yearlyShift.toFixed(2)}
                    </span>
                </div>
            </div>

            <div class="mt-3 pt-2 border-top">
                <p class="mb-0 text-muted" style="font-size: 0.75rem;">
                    Compared to your historical average, this category is projected to cost you 
                    <b class="${colorClass}">$${Math.abs(threeYearBurn).toLocaleString(undefined, {maximumFractionDigits: 0})}</b> 
                    ${isIncreasing ? 'more' : 'less'} over the next 3 years.
                </p>
            </div>
        </div>
    `;

    $('#detailInsights').html(impactHtml);
}


/**
 * Synchronizes the start of all projection series with the last historical data point.
 * @param {Array} data - The raw JSON array from your AJAX call.
 * @returns {Array} - The "Anchored" data ready for Highcharts.
 */
function anchorProjections(data) {
    // 1. Find the last index that has historical data
    const lastHistIndex = data.map(d => d.historicalTruth).findLastIndex(val => val !== null && val !== undefined);

    if (lastHistIndex === -1) return data; // Safety check

    const anchorValue = data[lastHistIndex].historicalTruth;

    // 2. Map through the data and set the anchor for all projection keys
    return data.map((d, idx) => {
        if (idx === lastHistIndex) {
            return {
                ...d,
                regression: anchorValue,
                avgStrict: anchorValue,
                lvcf: anchorValue,
                inflation: anchorValue,
                alphaSpike: anchorValue,
                zeroSum: anchorValue
            };
        }
        return d;
    });


}

window.addNewCategory = async function () {
    const $nameInput = $('#new-category-name');
    const name = $nameInput.val().trim();
    const color = $('#new-category-color').val() || '#FFFFFF';
    const parentId = $('#new-category-parent').val();

    if (!name) return;

    try {
        const response = await fetch('addTransactionCategory', {
            method: 'POST',
            body: new URLSearchParams({
                inputcategoryCategory_Name: name,
                inputcategoryColor_id: color,
                inputcategoryParent_id: parentId,
                inputsub_categoryprojection_strategy_ID: 'AVG_STRICT'
            })
        });

        if (response.ok) {
            // Option A: Quick Reload (Simplest, ensures all IDs match DB)
            // location.reload();

            // Option B: Vanilla JS Injection (Faster UX, matches your JSP structure)
            const newId = await response.text(); // Assuming your Servlet returns the new UUID
            const parentOption = $(`#new-category-parent option[value="${parentId}"]`);
            const type = parentOption.data('type') || 'expense';
            const parentName = parentOption.text().trim();

            const newCardHtml = `
                <div class="col animate__animated animate__fadeIn">
                    <div class="modern-cat-card border-${type}" data-id="${newId}" data-strategy="AVG_STRICT">
                        <div class="card-accent" style="background-color: ${color};">
                            <div class="swatch-container">
                                <div class="color-swatch-trigger" style="background-color: ${color};"></div>
                                <div class="picker-popover d-none shadow-lg">
                                    <div class="wheel-canvas"></div>
                                </div>
                            </div>
                        </div>
                        <div class="card-body-content">
                            <div class="card-actions-modern">
                                <i class="bi bi-lock-fill text-warning action-icon-modern lock-trigger" 
                                   title="Toggle Projection Lock" 
                                   onclick="handleLockToggle(event, '${newId}')"></i>
                                <i class="bi bi-gear action-icon-modern gear-trigger" 
                                   title="Advanced Settings" 
                                   onclick="handleGearClick(event, '${newId}')"></i>
                                <i class="bi bi-x action-icon-modern delete-trigger" 
                                   onclick="confirmDeleteCategory('${newId}', '${name}')"></i>
                            </div>
                            <div class="category-name-row">
                                <span class="category-text" contenteditable="true">${name}</span>
                            </div>
                            <div class="strategy-row">
                                <i class="bi bi-graph-up-arrow strategy-icon" title="Regression" data-val="REGRESSION"></i>
                                <i class="bi bi-lightning-fill strategy-icon" title="Alpha Spike" data-val="ALPHA_SPIKE"></i>
                                <i class="bi bi-pause-fill strategy-icon" title="Last Value" data-val="LVCF"></i>
                                <i class="bi bi-list-stars strategy-icon active-strategy" title="Strict Average" data-val="AVG_STRICT" style="color: #198754; font-weight: bold; opacity: 1;"></i>
                                <i class="bi bi-percent strategy-icon" title="Inflation Only" data-val="INFLATION_ONLY"></i>
                                <i class="bi bi-x-circle strategy-icon" title="Zero Out" data-val="ZERO_SUM"></i>
                            </div>
                            <div class="parent-row">
                                <select class="modern-select" onchange="updateParentCategory('${newId}', this.value)">
                                    ${$('#new-category-parent').html()}
                                </select>
                            </div>
                        </div>
                    </div>
                </div>`;

            // Append to your container (adjust selector as needed)
            $('.category-grid-container').append(newCardHtml);

            // Clean up UI
            $nameInput.val('');
            bootstrap.Modal.getInstance(document.getElementById('addCategoryModal')).hide();
        }
    } catch (err) {
        console.error("Failed to add category:", err);
    }
};

async function fetchPerformanceData(selectedYear) {
    const categoryId = $('#categoryAnalysisSection').data('current-id') ||
        $('#categoryAnalysisSection').attr('data-current-id');

    if (!categoryId) {
        console.error("No Category ID found! Make sure you clicked a Gear icon first.");
        return;
    }
    const $resultsContainer = $('#performanceResults');
    const $chart = $('#categoryAnalysisChart');

    // PHASE 1: "Store" the chart (The Workbench Blur)
    $chart.removeClass('chart-ready').addClass('chart-reworking');
    // --- 1. STATE BUFFERING (Save the old view) ---
    if (!window.originalChartState && window.analysisChart) {
        console.log("Buffering original chart state...");
        window.originalChartState = {
            series: window.analysisChart.series.map(s => ({
                name: s.name,
                data: s.options.data,
                color: s.color,
                type: s.type
            })),
            categories: window.analysisChart.xAxis[0].categories,
            title: window.analysisChart.title.textStr
        };
    }

    // PHASE 2: UI Loading State
    $chart.removeClass('chart-ready').addClass('chart-reworking');
    $resultsContainer.html(`
        <div class="text-center p-5">
            <div class="spinner-grow text-primary" role="status"></div>
            <p class="small text-muted mt-2">Analyzing ${selectedYear} Budget Accuracy...</p>
        </div>
    `);
    console.log(categoryId);

    try {
        const response = await fetch(`getCategoryPerformance?id=${categoryId}&year=${selectedYear}`);
        const data = await response.json(); // Array of PerformanceDTO

        // --- 3. PHASE 3: Update Highcharts with Performance Data ---
        if (window.analysisChart) {
            const periods = data.map(d => d.period);
            const budgeted = data.map(d => d.budgetedValue);
            const actuals = data.map(d => d.actualValue);
            const threshholds = data.map(d => d.threshold)

            // Wipe existing series and load the "Focused" view
            while (window.analysisChart.series.length > 0) window.analysisChart.series[0].remove(false);

            window.analysisChart.update({
                xAxis: {categories: periods},
                title: {text: `Budget Performance: ${selectedYear}`}
            }, false);

            window.analysisChart.addSeries({
                name: 'Budgeted',
                data: budgeted,
                type: 'column',
                color: '#6c757d', // Muted gray
                opacity: 0.6
            }, false);

            window.analysisChart.addSeries({
                name: 'Actual Spending',
                data: actuals,
                type: 'column',
                color: '#0d6efd' // Brand Blue
            }, false);

            window.analysisChart.redraw();
        }
        let html = `<div class="row g-2">`;

        data.forEach(item => {
            html += createPerformanceCard(item);
        })


        html += `</div>`;
        $resultsContainer.html(html);

        // PHASE 3: Update Highcharts (Next Step)
        // updatePerformanceChart(data);

        // PHASE 4: Reveal the Workbench
        setTimeout(() => {
            if (window.analysisChart) window.analysisChart.reflow();
            $chart.removeClass('chart-reworking').addClass('chart-ready');
        }, 400);

    } catch (err) {
        console.error("Performance Fetch Error:", err);
        $resultsContainer.html('<div class="alert alert-danger small">Failed to load performance metrics.</div>');
        $chart.removeClass('chart-reworking').addClass('chart-ready');
    }
}

/**
 * Generates a Bootstrap-styled card comparing Actual spending
 * against both the Engine's Forecast and the User's Hard Limit.
 */
function createPerformanceCard(item) {
    // 1. Comparison Logic: Actual vs. Engine Forecast
    const budgetDiff = item.actualValue - item.budgetedValue;
    const isOverBudget = budgetDiff > 0;
    const budgetStatusClass = isOverBudget ? 'text-danger' : 'text-success';

    // 2. Comparison Logic: Actual vs. Hard Threshold
    const thresholdDiff = item.actualValue - item.threshold;
    const isOverThreshold = thresholdDiff > 0;

    // UI State for Threshold
    const thresholdStatusClass = isOverThreshold ? 'bg-danger text-white' : 'bg-light text-muted border';
    const thresholdLabel = isOverThreshold ? 'BREACH' : 'SAFE';
    const progressBarClass = isOverThreshold ? 'bg-danger' : 'bg-primary';

    // 3. Calculate Progress Percentage (Cap at 100% for the bar width)
    const percentOfThreshold = item.threshold > 0
        ? Math.min((item.actualValue / item.threshold) * 100, 100)
        : 0;

    return `
    <div class="col-md-6 mb-3 animate__animated animate__fadeInUp">
        <div class="card h-100 border-0 shadow-sm overflow-hidden">
            <div class="card-header bg-white py-2 d-flex justify-content-between align-items-center border-bottom-0">
                <span class="fw-bold text-dark"><i class="bi bi-calendar3 me-2"></i>${item.period}</span>
                <span class="badge ${thresholdStatusClass} rounded-pill small px-2" style="font-size: 0.7rem;">
                    ${thresholdLabel}
                </span>
            </div>

            <div class="card-body p-3 pt-0">
                <div class="row g-0 mt-2">
                    <div class="col-6 border-end pe-2">
                        <small class="text-uppercase text-muted d-block mb-1" style="font-size: 0.65rem; letter-spacing: 0.5px;">Vs. Forecast</small>
                        <div class="d-flex align-items-center">
                            <span class="h6 mb-0 fw-bold ${budgetStatusClass}">
                                ${isOverBudget ? '+' : '-'}$${Math.abs(budgetDiff).toFixed(2)}
                            </span>
                        </div>
                        <p class="small text-muted mb-0 mt-1" style="font-size: 0.7rem;">
                            Model: $${item.budgetedValue.toFixed(0)}
                        </p>
                    </div>

                    <div class="col-6 ps-3">
                        <small class="text-uppercase text-muted d-block mb-1" style="font-size: 0.65rem; letter-spacing: 0.5px;">Vs. Threshold</small>
                        <div class="d-flex align-items-center">
                            <span class="h6 mb-0 fw-bold ${isOverThreshold ? 'text-danger' : 'text-dark'}">
                                ${isOverThreshold ? '+' : '-'}$${Math.abs(thresholdDiff).toFixed(2)}
                            </span>
                        </div>
                        <p class="small text-muted mb-0 mt-1" style="font-size: 0.7rem;">
                            Limit: $${item.threshold.toFixed(0)}
                        </p>
                    </div>
                </div>
                
                <div class="mt-3 bg-light p-2 rounded">
                    <div class="d-flex justify-content-between small mb-1">
                        <span class="text-muted" style="font-size: 0.75rem;">Total Month Spending</span>
                        <span class="fw-bold text-dark">$${item.actualValue.toLocaleString(undefined, {minimumFractionDigits: 2})}</span>
                    </div>
                    <div class="progress" style="height: 6px; background-color: rgba(0,0,0,0.05);">
                        <div class="progress-bar ${progressBarClass} progress-bar-striped" 
                             role="progressbar" 
                             style="width: ${percentOfThreshold}%"
                             aria-valuenow="${percentOfThreshold}" 
                             aria-valuemin="0" 
                             aria-valuemax="100"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>`;
}