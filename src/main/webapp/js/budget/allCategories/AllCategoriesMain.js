async function handleAutoAssign() {
    const $btn = $(this);
    const originalHtml = $btn.html();

    if (!confirm("This will analyze your spending history and automatically assign the best projection model to every category. Continue?")) {
        return;
    }

    // 1. UI: Start Loading
    toggleAutoAssignLoading($btn, true);

    try {
        // 2. DB: Call the Server
        const response = await apiAutoAssignProjections();

        // 3. Logic: Handle Success/Failure
        if (response == "1" || response == 1) {
            alert("Success! Your categories have been optimized based on historical trends.");
            location.reload();
        } else {
            showToast("autoAssignProjections", response);
            alert("Analysis complete, but no changes were made.");
        }
    } catch (error) {
        alert(error.message);
    } finally {
        // 4. UI: Cleanup
        toggleAutoAssignLoading($btn, false, originalHtml);
    }
}

/**
 * Event handler for the delete confirmation button.
 */
async function handleConfirmDelete() {
    const $btn = $(this);
    const originalText = $btn.text();

    // 1. UI: Start Loading
    toggleDeleteLoading($btn, true);

    try {
        // 2. DB: Attempt to delete using the global categoryIdToDelete
        const success = await apiDeleteCategory(categoryIdToDelete);

        // 3. Logic: Check results
        if (success) {
            location.reload();
        } else {
            alert("Error deleting category.");
            toggleDeleteLoading($btn, false, originalText);
        }
    } catch (err) {
        console.error("Deletion failed:", err);
        alert("A critical error occurred while deleting.");
        toggleDeleteLoading($btn, false, originalText);
    }
}

/**
 * Event handler for changing a category's projection strategy.
 */
async function handleStrategyClick(e, pickers) {
    e.stopPropagation();
    const $icon = $(e.currentTarget);

    if ($icon[0].classList.contains('disabled')) {
        showToast("shared", "-7");
        //console.log("here too")
        return;
    }

    const $card = $icon.closest('.modern-cat-card');
    const newVal = $icon.data('val');

    // 1. UI: Update Card and Sidebar visuals
    $card.attr('data-strategy', newVal);
    updateStrategyUI($icon, $card, newVal);

    // 2. DB: Persist change
    const hex = getHexFromCard($card, pickers);
    await apiUpdateStrategy($card, hex, pickers);

    // 3. Logic: Auto-trigger lock if currently unlocked
    const $lockIcon = $card.find('.lock-trigger');
    if ($lockIcon.length > 0 && $lockIcon.hasClass('bi-unlock')) {
        $lockIcon[0].click();
    }

    lockBoxSkip = false;
}

/**
 * Orchestrates the full update process for a category card.
 */
async function sendUpdate($card, hexColor, pickers) {
    // 1. UI: Gather and format data
    const data = prepareCategoryData($card, hexColor);

    // 2. DB: Send data to server
    const resultCode = await apiUpdateCategory(data);

    // 3. UI: Provide feedback
    showUpdateFeedback(resultCode);

    return resultCode;
}

/**
 * Event handler for executing the auto-assign optimization.
 */
async function handleAutoAssignExecution() {
    const $btn = $(this);
    const $modal = $('#autoAssignModal');
    const originalHtml = $btn.html();

    // 1. UI: Enter loading state
    setAutoAssignProcessing($btn, $modal);

    try {
        // 2. DB: Run the engine
        const result = await apiRunAutoAssign();
        showToast("autoAssignProjections", result);

        if (result >= 0) {
            // 3. UI: Success transition
            setAutoAssignSuccess($btn, result);

            // Delay reload so user can see the result count
            setTimeout(() => {
                location.reload();
            }, 1000);
        } else {
            alert("The engine returned an error code: " + result);
            resetAutoModal($btn, originalHtml);
        }
    } catch (err) {
        alert(err.message);
        resetAutoModal($btn, originalHtml);
    }
}

/**
 * Event handler for executing automatic color assignment.
 */
async function handleAutoAssignColorExecution() {
    const $btn = $(this);
    const $modal = $('#autoAssignColorModal');
    const originalHtml = $btn.html();

    // 1. UI: Enter loading state
    setAutoColorProcessing($btn, $modal);

    try {
        // 2. DB: Run the color engine
        const result = await apiRunAutoColorAssign();

        // Fixed: Passing string "autoAssignColors" as the result tag
        showToast("autoAssignProjections", "autoAssignColors");

        if (result >= 0) {
            // 3. UI: Success transition
            setAutoColorSuccess($btn, result);

            setTimeout(() => {
                location.reload();
            }, 1000);
        } else {
            alert("The engine returned an error code: " + result);
            resetAutoModal($btn, originalHtml);
        }
    } catch (err) {
        alert(err.message);
        resetAutoModal($btn, originalHtml);
    }
}

/**
 * Event handler for toggling the lock status of a category.
 */
async function handleLockToggle(e, id) {
    e.stopPropagation();
    const $icon = $(e.currentTarget);
    const isLocking = $icon.hasClass('bi-unlock');
    const isTop = $icon.hasClass('topLock');
    const mode = isLocking ? 1 : 0;
    const skipToast = typeof lockBoxSkip !== 'undefined' && lockBoxSkip;

    if (!skipToast) showToast("shared", "2");

    // 1. UI: Show pending state
    setLockPending($icon, true);

    try {
        // 2. DB: Persist lock state
        const response = await apiLockSubcategory(id, mode);

        if (!skipToast) showToast("lockSubcategory", response);

        if (response == "1" || response == 1) {
            // 3. UI: Update visuals across the app
            updateLockUI($icon, id, isLocking, isTop);
        } else {
            showToast("shared", "-4");
        }
    } catch (err) {
        showToast("shared", err.message);
    } finally {
        // 4. Cleanup
        setLockPending($icon, false);
        if (!isTop) {
            handleGearClick(e, id);
        }
        lockBoxSkip = false;
    }
}

async function fetchProjectionData(subcatId, name, strategy, locked) {
    showToast("shared", 2);

    try {
        // 1. DB: Fetch raw data
        const rawData = await apiFetchProjectionData(subcatId);
        if (!rawData) return;

        // 2. Data Processing (Internal logic)
        const data = anchorProjections(rawData);
        const categories = data.map(d => (d.monthDate ? d.monthDate.substring(0, 7) : "???"));

        // 3. Charting: Render and capture state
        renderAnalysisChart(data, categories, strategy);

        // 4. UI: Update labels and tabs
        updateAnalysisUI(name, data, strategy, locked);

    } catch (err) {
        console.error("Chart data pipeline failed:", err);
    }
}


window.addNewCategory = async function () {
    const $nameInput = $('#new-category-name');
    const $colorInput = $('#new-category-color');
    const $parentSelect = $('#new-category-parent');
    const $errorDisplay = $('#new-category-error');

    const name = $nameInput.val().trim();
    const color = $colorInput.val() || '#FFFFFF';
    const parentId = $parentSelect.val();

    if (!name) return $nameInput.addClass('is-invalid');

    try {
        // 1. DB: Call API
        const result = await apiAddCategory({
            inputcategoryCategory_Name: name,
            inputcategoryColor_id: color,
            inputcategoryParent_id: parentId,
            inputsub_categoryprojection_strategy_ID: 'AVG_STRICT'
        });

        const uuidRegex = /^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$/i;

        if (uuidRegex.test(result)) {
            // 2. UI: Render new card
            const type = $parentSelect.find('option:selected').data('type') || 'expense';
            const html = generateCategoryCardHtml(result, name, color, type, $parentSelect.html());

            const $targetCollapse = $(`#collapse-${parentId}`);
            bootstrap.Collapse.getOrCreateInstance($targetCollapse[0]).show();

            const $newElement = $(html);
            $(`#category-grid-${parentId}`).append($newElement);
            $newElement.find('select').val(parentId);

            if (typeof initColorPickerForCard === "function") initColorPickerForCard($newElement.find('.modern-cat-card'));
            resetNewCategoryForm($nameInput, $errorDisplay);
        } else {
            const errorMap = {"-1": "Session expired.", "-2": "Invalid name.", "-10": "DB Error."};
            $errorDisplay.text(errorMap[result] || "Error occurred.").removeClass('d-none');
            $nameInput.addClass('is-invalid');
        }
    } catch (err) {
        $errorDisplay.text("Network error.").removeClass('d-none');
    }
};

async function fetchPerformanceData(selectedYear) {
    const categoryId = $('#analysisSubcatId').val();
    if (!categoryId) return console.error("No Category ID found!");

    // 1. UI Preparation
    $('#displayYear').text(selectedYear);
    $('#viewYearlyTransactions').attr('href', `/all-transactions?year=${selectedYear}&category_id=${categoryId}`);
    setPerformanceLoading(true, selectedYear);

    try {
        // 2. DB: Fetch Data
        const data = await apiFetchPerformanceData(categoryId, selectedYear);

        // 3. UI/Chart: Render results
        renderAnnualReportCard(data, selectedYear); // Existing helper
        renderPerformanceResults(data);

        const chart = window.analysisChart || Highcharts.charts.find(c => c && c.renderTo.id === 'categoryAnalysisChart');
        renderPerformanceChart(chart, data, selectedYear);

    } catch (err) {
        console.error("Performance Fetch Error:", err);
        $('#performanceResults').html('<div class="alert alert-danger small">Failed to load performance metrics.</div>');
    } finally {
        // 4. UI: Clean up loading state
        setPerformanceLoading(false);
        if (window.analysisChart) window.analysisChart.reflow();
    }
}

/**
 * Event handler for when the threshold input loses focus (blur).
 */
async function handleThresholdBlur(e) {
    const $input = $(e.target);
    const newAmount = parseFloat($input.val().trim());
    const subcategoryId = $('#categoryAnalysisSection').attr('data-current-id');

    // 1. UI: Immediate Style Reset
    resetThresholdInputStyle($input);

    if (!subcategoryId || isNaN(newAmount)) return;

    try {
        // 2. DB: Persist the new threshold
        const response = await apiUpdateThreshold(subcategoryId, newAmount);
        showToast("updateThreshold", response);

        if (response === "1") {
            // 3. UI: Success Feedback
            $input.css('border-bottom-color', '#198754');
            setTimeout(() => $input.css('border-bottom-color', ''), 1500);

            // 4. UI: Update the related card's progress bar
            updateCardMiniAnalysis(subcategoryId, newAmount);
        }
    } catch (err) {
        // UI: Error Feedback
        $input.css('border-bottom-color', '#dc3545');
    }
}

/**
 * Transitions the category container to show new content.
 * @param {jQuery} $container - The main category container.
 * @param {string} html - The new HTML to inject.
 */
function transitionCategoryGrid($container, html) {
    $container.slideUp(400, function () {
        $container.html(html);
        $container.slideDown(400);
    });
}

/**
 * Event handler for when the month or year selects are changed.
 */
async function handleMonthChange(e) {
    // 1. Logic: Update Global State and provide feedback
    showToast("shared", 2);
    selectedMonth = $('#monthSelect').val();
    selectedYyear = $('#yearSelect').val();

    const $categoryDiv = $("#allSuperCategories");

    try {
        // 2. DB: Fetch the updated category HTML
        const responseHtml = await apiFetchCategoriesByDate(selectedYyear, selectedMonth);

        // 3. UI: Execute the transition
        transitionCategoryGrid($categoryDiv, responseHtml);

    } catch (err) {
        console.error(err.message);
        // Optional: Show error state in the UI
        $categoryDiv.slideDown();
    }
}

/**
 * Event handler for clicking a row in the forecast comparison table.
 */
function handleForecastRowClick(e) {
    // 1. Logic: Set state and get identifiers

    const $lock = $(e.currentTarget).closest('.card-body').find('.topLock').children().first();
    //console.log($lock[0])
    var locked = $($lock[0]).hasClass('bi-lock-fill')
    //console.log(locked)
    if (locked) {
        //console.log("wtf")
        return;
    }
    // if (locked) {
    //     $row.removeClass('is-hoverable');
    // } else {
    //     $row.addClass('is-hoverable');
    // }


    const seriesKey = $(this).data('series');
    const currentId = $('#analysisSubcatId').val();

    // 2. Logic: Translate the key using our utility
    const strategyName = getStrategyFromKey(seriesKey);

    // 3. UI: Locate the corresponding icon on the main card
    const $targetIcon = getStrategyIcon(currentId, strategyName);

    // 4. Orchestration: Trigger the strategy change logic
    if ($targetIcon.length > 0) {
        handleStrategyClick({
            currentTarget: $targetIcon[0],
            stopPropagation: () => {
            } // Mocking the event for the sub-function
        }, pickers);
    }
}

/**
 * Handles iro.js Color Picker initialization and toggling.
 */
function handleColorPickerToggle(e, pickers) {
    e.stopPropagation();

    const $this = $(e.currentTarget);
    const $card = $this.closest('.modern-cat-card');
    const id = $card.data('id');
    const $popover = $this.siblings('.picker-popover');

    if (!id) return;

    // 1. UI: Handle opening/closing logic
    togglePickerVisibility($this, $card, $popover);

    // 2. Tool: Initialize if it doesn't exist
    if (!pickers[id]) {
        const canvas = $popover.find('.wheel-canvas')[0];
        const initialColor = $this.css('background-color');

        pickers[id] = createColorPickerInstance(canvas, initialColor);

        // 3. Events: Wire up the logic
        pickers[id].on('color:change', function (color) {
            updateCardVisualColor($card, color.hexString);
        });

        pickers[id].on('input:end', function (color) {
            // Orchestrate the DB update
            sendUpdate($card, color.hexString, pickers);
        });
    }
}

/**
 * Orchestrates the movement of a category to a new parent.
 */
window.updateParentCategory = function (id, parentId) {
    const $card = $(`.modern-cat-card[data-id="${id}"]`);
    const $column = $card.closest('.col');
    const $oldCollapse = $card.closest('.collapse');
    const $newCollapse = $(`#collapse-${parentId}`);
    const $newGrid = $(`#category-grid-${parentId}`);
    const type = $card.find('select option:selected').data('type') || '';

    // Trigger the UI animation sequence
    animateCategoryMove(
        $column,
        $newGrid,
        // Midpoint Logic: Update visuals while card is hidden
        () => {
            $card.removeClass('border-income border-investment border-expense border-transfer')
                .addClass('border-' + type);
        },
        // Completion Logic: Handle panels and DB update
        () => {
            syncCategoryPanelStates($oldCollapse, $newCollapse, () => {
                $card.addClass('card-just-dropped');
                setTimeout(() => $card.removeClass('card-just-dropped'), 1000);
            });

            // Final step: Persist to DB
            sendUpdate($card, getHexFromCard($card, {}), {});
        }
    );
};

/**
 * Handles the logic when the "gear" icon is clicked on a category card.
 */
function handleGearClick(e, id) {
    e.stopPropagation();

    // 1. Data Extraction
    const $card = $(`.modern-cat-card[data-id="${id}"]`);
    const name = $card.find('.category-text').text().trim();
    const strategy = $card.attr('data-strategy');
    const isLocked = $card.find('.lock-trigger').hasClass('bi-lock-fill');
    const currentColor = $card.find('.card-accent').css('background-color');
    const currentThreshold = $card.find('.target-text-label').text().replace(/[^0-9.]/g, '');

    // 2. Logic: Reset Global State
    window.originalChartState = null;
    syncAnalysisId(id);

    // 3. UI: Launch Sidebar and Update Tabs
    openAnalysisSidebar(name, currentColor);
    updateOverviewTabUI(name, strategy, isLocked, id, currentThreshold);

    // 4. Data: Trigger Fetching (using the slideDown callback pattern)
    // We delay the fetch slightly or use a callback to ensure the container is visible for Chart.js
    setTimeout(() => {
        fetchProjectionData(id, name, strategy, isLocked);
    }, 400);
}

function updateAnalysisTabs(data, strategy, locked) {
    const history = data.filter(d => d.historicalTruth != null);
    const forecast = data.filter(d => d.historicalTruth == null);

    // 1. Math - Ensure metrics and totals are always defined
    const results = calculateAnalysisMetrics(history, forecast) || {};
    const metrics = results.metrics || {slope: 0, rSquared: 0, histAvg: 0};

    // Safety check: Ensure totals and its sub-properties exist
    const totals = results.totals || {threshold: 0, actual: 0, budget: 0};

    // 2. Update Regression UI
    $('#mathSlope').text((metrics.slope || 0).toFixed(2));
    $('#mathRSquared').text((metrics.rSquared || 0).toFixed(3));

    // 3. Render Forecast Tab
    // Now passing a guaranteed totals object
    const forecastHtml = renderForecastComparison(totals, metrics.histAvg, strategy, locked);
    const progressHtml = renderRetirementProgress();
    $('#tab-forecast').html(forecastHtml + progressHtml);

    // 4. Update Progress Bars
    const lastRow = data[data.length - 1];
    if (lastRow) {
        updateRetirementUI(lastRow.inflation || 0);
    }

    // 5. Lifecycle & Impact
    $('#performanceMonthSelect').off('change').on('change', function () {
        fetchPerformanceData($(this).val());
    });

    const impact = calculateModelImpact(data, strategy || "avg_strict");
    updateImpactUI(impact);
}

/**
 * Updates the Growth Impact section in the sidebar.
 */
function updateGrowthImpactUI(data) {
    // 1. Check for data sufficiency
    const hasHistory = data.some(d => d.historicalTruth != null);
    const hasForecast = data.some(d => d.regression != null);

    if (!hasHistory || !hasForecast) {
        $('#detailInsights').html("<p class='small text-muted'>Insufficient data for growth analysis.</p>");
        return;
    }

    // 2. Logic: Get the math
    const metrics = getGrowthMetrics(data);

    // 3. UI: Render the template
    const html = renderGrowthImpactHtml(metrics);
    $('#detailInsights').html(html);
}

/**
 * Example of how this is orchestrated in the main flow
 */
function processCategoryData(rawData) {
    // 1. Logic: Smooth the transition between history and future
    const preparedData = anchorProjections(rawData);

    // 2. UI: Update the chart with the anchored data
    renderMainAnalysisChart(preparedData);

    // 3. UI: Update the comparison tables
    updateAnalysisTabs(preparedData, currentStrategy, isLocked);
}

/**
 * Orchestrates the rendering of the performance tab results.
 */
function updatePerformanceResults(items) {
    const $container = $('#performanceResults');
    const categoryId = $('#analysisSubcatId').val();

    const cardsHtml = items.map(item => {
        // 1. Logic: Calculate the numbers
        const metrics = calculatePerformanceMetrics(item);

        // 2. Logic: Create the link
        const linkUrl = getPerformanceLink(item.period, categoryId);

        // 3. UI: Generate the HTML
        return renderPerformanceCardHtml(item, metrics, linkUrl);
    }).join('');

    $container.html(cardsHtml);
}

/**
 * Orchestrates the rendering of the annual report section.
 */
function renderAnnualReportCard(data, year) {
    const $container = $('#annualReportContainer');
    const categoryId = $('#analysisSubcatId').val();

    // 1. Logic: Compute metrics
    const metrics = calculateAnnualMetrics(data);

    // 2. UI: Build and display
    const html = renderAnnualReportHtml(year, metrics, categoryId);
    $container.html(html).removeClass('d-none');
}

/**
 * Orchestrates the creation of a new category card.
 */
function handleAddNewCategory(id, name, color, type) {
    // 1. Logic: Prepare the data object
    const categoryData = {id, name, color, type};

    // 2. Logic: Get the current parent options (reusing a utility)
    const parentSelectHtml = generateParentOptionsHtml(type);

    // 3. UI: Render the card
    const cardHtml = renderCategoryCard(categoryData, parentSelectHtml);

    // 4. UI: Append to the correct grid
    $(`#grid-${type}`).append(cardHtml);

    // Re-initialize any color pickers or tooltips if needed
    initColorPickers();
}

/**
 * Highlights a strategy in the sidebar and updates the chart preview.
 */
function handleStrategyPreview(e, strategyId) {
    // 1. UI: Update Button Highlight
    const $lock = $(e.currentTarget).closest('.card-body').find('.topLock').children().first();

    var locked = $($lock[0]).hasClass('bi-lock-fill')

    if (locked) {
        showToast("shared", "-7")

        return;
    }

    $('.forecast-row').removeClass('active border-primary').css('background-color', '');
    $(`.forecast-row[data-series="${strategyId}"]`)
        .addClass('active border-primary')
        .css('background-color', 'rgba(13, 110, 253, 0.05)');


}