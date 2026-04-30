/**
 * Finds the strategy icon element on a specific category card.
 * @param {string} categoryId
 * @param {string} strategyValue
 * @returns {jQuery}
 */
function getStrategyIcon(categoryId, strategyValue) {
    return $(`.modern-cat-card[data-id="${categoryId}"] .strategy-icon[data-val="${strategyValue}"]`);
}

function showAutoAssignModal() {
    const modalEl = document.getElementById('autoAssignModal');
    new bootstrap.Modal(modalEl).show();
}

function showAutoAssignColorModal() {
    const modalColor = document.getElementById('autoAssignColorModal');

    new bootstrap.Modal(modalColor).show();
}

/**
 * Toggles the visibility of the color picker popover and manages card z-index.
 * @param {jQuery} $this - The clicked trigger element.
 * @param {jQuery} $card - The parent category card.
 * @param {jQuery} $popover - The popover element to toggle.
 */
function togglePickerVisibility($this, $card, $popover) {
    if ($popover.hasClass('d-none')) {
        $('.modern-cat-card').removeClass('card-on-top');
        $card.addClass('card-on-top');
    } else {
        $card.removeClass('card-on-top');
    }

    // Hide all other open pickers, then toggle this one
    $('.picker-popover').not($popover).addClass('d-none');
    $popover.toggleClass('d-none');
}

/**
 * Updates the card's visual color accents immediately.
 * @param {jQuery} $card - The category card.
 * @param {string} hexColor - The new hex string.
 */
function updateCardVisualColor($card, hexColor) {
    $card.find('.color-swatch-trigger').css('background-color', hexColor);
    $card.find('.card-accent').css('background-color', hexColor);
}

/**
 * Initializes an iro.js ColorPicker instance.
 * @param {HTMLElement} canvas - The element to mount the picker.
 * @param {string} initialColor - Starting color.
 * @returns {iro.ColorPicker}
 */
function createColorPickerInstance(canvas, initialColor) {
    return new iro.ColorPicker(canvas, {
        width: 150,
        color: initialColor,
        layout: [
            {component: iro.ui.Wheel},
            {component: iro.ui.Slider, options: {sliderType: 'hue'}}
        ]
    });
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

function getHexFromCard($card, pickers) {
    const id = $card.data('id');
    return pickers[id] ? pickers[id].color.hexString : rgbToHex($card.find('.card-accent').css('background-color'));
}

function rgbToHex(rgb) {
    if (!rgb || !rgb.startsWith('rgb')) return '#0d6efd';
    const vals = rgb.match(/\d+/g);
    return "#" + vals.map(x => parseInt(x).toString(16).padStart(2, '0')).join("");
}

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

/**
 * Executes the 'teleport' animation of a category card between grids.
 * @param {jQuery} $column - The column containing the card.
 * @param {jQuery} $newGrid - The destination grid.
 * @param {Function} onMidpoint - Callback to run while the card is invisible.
 * @param {Function} onComplete - Callback to run after entrance.
 */
function animateCategoryMove($column, $newGrid, onMidpoint, onComplete) {
    // Phase 1: Fade Out
    $column.css({
        'transition': 'all 0.4s ease-in',
        'opacity': '0',
        'transform': 'scale(0.8) translateY(10px)'
    });

    setTimeout(() => {
        // Phase 2: Relocate DOM
        $newGrid.append($column);

        if (onMidpoint) onMidpoint();

        // Reset for entrance (Hidden teleport)
        $column.css({
            'transition': 'none',
            'opacity': '0',
            'transform': 'scale(0.9) translateY(-10px)'
        });

        $column[0].offsetHeight; // Force reflow

        // Phase 3: Entrance
        $column.css({
            'transition': 'all 0.5s cubic-bezier(0.34, 1.56, 0.64, 1)',
            'opacity': '1',
            'transform': 'scale(1) translateY(0)'
        });

        if (onComplete) onComplete();
    }, 400);
}

/**
 * Ensures category panels (collapse) open or close based on content.
 */
function syncCategoryPanelStates($oldCollapse, $newCollapse, entranceCallback) {
    // Hide old panel if empty
    if ($oldCollapse.find('.modern-cat-card').length === 0) {
        bootstrap.Collapse.getOrCreateInstance($oldCollapse[0]).hide();
    }

    const destInstance = bootstrap.Collapse.getOrCreateInstance($newCollapse[0]);

    if (!$newCollapse.hasClass('show')) {
        $newCollapse.one('shown.bs.collapse', entranceCallback);
        destInstance.show();
    } else {
        entranceCallback();
    }
}

/**
 * Prepares the analysis sidebar's visual state and animations.
 */
function openAnalysisSidebar(name, currentColor) {
    const $section = $('#categoryAnalysisSection');

    // Set CSS theme variable
    $section.css('--theme-color', currentColor);
    window.themeColor = currentColor; // Keep global for chart colors

    // UI Feedback & Reset
    $('#performanceResults').html('');
    $('#performanceMonthSelect').val('');
    $('#analysisChartTitle').html(
        `<span class="spinner-border spinner-border-sm me-2"></span> Analyzing ${name}...`
    );

    // Transitions
    $('#overview-tab').tab('show');
    $section.slideDown(400);
    window.scrollTo({top: 0, behavior: 'smooth'});
}

/**
 * Updates the basic metadata displayed in the Overview tab.
 */
function updateOverviewTabUI(name, strategy, isLocked, id, currentThreshold) {
    $('#detailCatName').text(name);
    $('#detailStrategy').text(strategy);

    const lockIconHtml = `
        <i class="bi ${isLocked ? 'bi-lock-fill text-warning' : 'bi-unlock'} action-icon-modern lock-trigger topLock"
           title="Toggle Projection Lock"
           onclick="handleLockToggle(event, '${id}')"></i>`;

    $('#detailLockStatus').html(lockIconHtml);

    const $budgetInput = $('#setBudgetedAmount');
    $budgetInput.val(currentThreshold);
    $budgetInput.prop("disabled", isLocked);
}


function resetAutoModal($btn, originalHtml) {
    $btn.prop("disabled", false).html(originalHtml);
    $('#autoAssignModal').find('.btn-close, .btn-light').removeClass('d-none');
}

/**
 * Updates the Impact UI section in the analysis sidebar.
 * @param {Object} impact - The object returned by calculateModelImpact.
 */
/**
 * Updates the Impact UI section with defensive checks.
 */
function updateImpactUI(impact) {
    const $container = $('#impactSummary');

    // Defensive check: ensure impact exists and has numbers
    const monthly = impact?.monthly || 0;
    const percent = impact?.percentChange || 0;
    const isIncreasing = impact?.isIncreasing || false;

    const colorClass = isIncreasing ? 'text-danger' : 'text-success';
    const arrow = isIncreasing ? '↑' : '↓';

    $container.find('.monthly-delta').text(`$${Math.abs(monthly).toFixed(2)}`);
    $container.find('.impact-direction').text(`${arrow} ${percent.toFixed(1)}%`);

    $container.removeClass('text-danger text-success').addClass(colorClass);
}


// Updates the background color of the 'Add' pill preview
window.updateAddPillColor = function (hex) {
    $('#new-card-accent').css('background-color', hex);
    $('#new-color-preview').css('background-color', hex);
};

// Updates the border class (income/expense/etc) of the 'Add' pill based on the selected parent
window.updateAddPillIndicator = function (selectEl) {
    const $pill = $('#new-pill-container');
    const type = $(selectEl).find('option:selected').data('type') || 'expense';

    $pill.removeClass('border-income border-investment border-expense border-transfer')
        .addClass('border-' + type);
};

function handleThresholdFocus(e) {
    const focusColor = $(this).attr('data-focus-color') || '#0d6efd';
    $(this).css({
        'border-bottom-color': focusColor,
        'background-color': 'rgba(0, 0, 0, 0.02)' // Subtle highlight
    });
}

function handleMagnifyClick(e, category) {

    window.location.href = 'all-Transactions?category=' + category + "&year=" + selectedYyear + "&month=" + selectedMonth

}

/**
 * Toggles the loading state of the auto-assign button.
 * @param {jQuery} $btn - The button element.
 * @param {boolean} isLoading - Whether the app is currently processing.
 * @param {string} originalHtml - The original button text to restore.
 */
function toggleAutoAssignLoading($btn, isLoading, originalHtml = '') {
    if (isLoading) {
        $btn.prop("disabled", true).addClass("btn-secondary").removeClass("btn-primary");
        $btn.html('<span class="spinner-border spinner-border-sm me-2"></span> Analyzing Data...');
    } else {
        $btn.prop("disabled", false).addClass("btn-primary").removeClass("btn-secondary");
        $btn.html(originalHtml);
    }
}

/**
 * Updates the delete button to a loading state or restores it.
 * @param {jQuery} $btn - The jQuery button element.
 * @param {boolean} isDeleting - Whether the deletion is in progress.
 * @param {string} originalText - The text to restore when finished.
 */
function toggleDeleteLoading($btn, isDeleting, originalText = 'Delete') {
    if (isDeleting) {
        $btn.prop('disabled', true)
            .html('<span class="spinner-border spinner-border-sm"></span> Deleting...');
    } else {
        $btn.prop('disabled', false).text(originalText);
    }
}

/**
 * Updates the visual state of strategy icons and the sidebar comparison list.
 * @param {jQuery} $icon - The clicked icon.
 * @param {jQuery} $card - The parent card.
 * @param {string} newVal - The strategy value (e.g., 'REGRESSION').
 */
function updateStrategyUI($icon, $card, newVal) {
    const strategyColors = {
        'REGRESSION': '#0d6efd',
        'ALPHA_SPIKE': '#fd7e14',
        'LVCF': '#6f42c1',
        'AVG_STRICT': '#198754',
        'INFLATION_ONLY': '#0dcaf0',
        'ZERO_SUM': '#6c757d'
    };

    // Update Card Icons
    $card.find('.strategy-icon').css({
        'color': '',
        'font-weight': 'normal',
        'opacity': '0.5'
    }).removeClass('active-strategy');

    $icon.css({
        'color': strategyColors[newVal],
        'font-weight': 'bold',
        'opacity': '1'
    }).addClass('active-strategy');

    // Update Sidebar highlighting
    const mapping = {
        'REGRESSION': 'regression', 'ALPHA_SPIKE': 'alphaSpike', 'LVCF': 'lvcf',
        'AVG_STRICT': 'avgStrict', 'INFLATION_ONLY': 'inflation', 'ZERO_SUM': 'zeroSum'
    };

    const $sidebarBtn = $(`.forecast-row[data-series="${mapping[newVal]}"]`);
    if ($sidebarBtn.length > 0) {
        $('.forecast-row').removeClass('active border-primary').css('background-color', '');
        $sidebarBtn.addClass('active border-primary').css('background-color', 'rgba(13, 110, 253, 0.05)');
    }
}

/**
 * Extracts and prepares category data from a card element.
 * @param {jQuery} $card - The category card element.
 * @param {string} hexColor - The chosen hex color.
 * @returns {Object} - Formatted data object for the API.
 */
function prepareCategoryData($card, hexColor) {
    // Ensure hex format
    const formattedColor = hexColor.startsWith('#') ? hexColor : '#' + hexColor;

    return {
        category_ID: $card.data('id'),
        inputcategoryCategory_Name: $card.find('.category-text').text().trim(),
        inputcategoryColor_id: formattedColor,
        inputsub_categoryparent_category_id: $card.find('select').val(),
        inputsub_categoryprojection_strategy_ID: $card.attr('data-strategy') || 'AVG_STRICT'
    };
}

/**
 * Displays the result of the update to the user.
 * @param {string} resultCode - The response from the server.
 */
function showUpdateFeedback(resultCode) {
    showToast('editCategory', resultCode.trim());
}

/**
 * Sets the auto-assign modal to a processing state.
 */
function setAutoAssignProcessing($btn, $modal) {
    $btn.prop("disabled", true);
    $modal.find('.btn-close, .btn-light').addClass('d-none');
    $btn.html('<span class="spinner-border spinner-border-sm me-2"></span> Running Optimization...');
}

/**
 * Sets the button to a success state.
 */
function setAutoAssignSuccess($btn, count) {
    $btn.removeClass('btn-primary').addClass('btn-success')
        .html(`<i class="bi bi-check-lg me-2"></i> Finished, ${count} updates!`);
}

/**
 * Resets the modal and button to their original state.
 */
function resetAutoModal($btn, originalHtml) {
    const $modal = $btn.closest('.modal');
    $btn.prop("disabled", false).removeClass('btn-success').addClass('btn-primary').html(originalHtml);
    $modal.find('.btn-close, .btn-light').removeClass('d-none');
}

/**
 * Sets the color assignment modal to a processing state.
 */
function setAutoColorProcessing($btn, $modal) {
    $btn.prop("disabled", true);
    $modal.find('.btn-close, .btn-light').addClass('d-none');
    $btn.html('<span class="spinner-border spinner-border-sm me-2"></span> Running Optimization...');
}

/**
 * Sets the color button to a success state.
 */
function setAutoColorSuccess($btn, count) {
    $btn.removeClass('btn-primary').addClass('btn-success')
        .html(`<i class="bi bi-check-lg me-2"></i> Finished, ${count} updates!`);
}

/**
 * Toggles the visual state of lock icons and associated inputs.
 * @param {jQuery} $icon - The specific icon clicked.
 * @param {string} id - The category ID.
 * @param {boolean} isLocking - Whether the action is locking or unlocking.
 * @param {boolean} isTop - Whether the click originated from the sidebar.
 */
function updateLockUI($icon, id, isLocking, isTop) {
    const isDisabled = isLocking;
    const $card = isTop ? $('#' + id + '_card') : $icon.closest('.modern-cat-card');

    // 1. Toggle the clicked icon and card visual
    $icon.toggleClass('bi-unlock bi-lock-fill text-warning');
    $icon.closest('.modern-cat-card').toggleClass('is-locked-visual');

    // 2. Sync the "other" icon if this is the top-level toggle
    if (isTop) {
        $(`#${id}_lockIcon`).toggleClass('bi-unlock bi-lock-fill text-warning');
        $('#setBudgetedAmount').prop("disabled", isDisabled);
        $('.forecastButton').prop('disabled', isDisabled);
    }

    // 3. Disable/Enable card inputs and strategy icons
    $card.find('.modern-select').prop('disabled', isDisabled);
    const $strategyIcons = $card.find('.strategy-icon');
    if (isDisabled) {
        $strategyIcons.addClass('disabled').attr('disabled', 'disabled');
    } else {
        $strategyIcons.removeClass('disabled').removeAttr('disabled');
    }
}

function setLockPending($icon, isPending) {
    $icon.css('opacity', isPending ? '0.2' : '');
}

/**
 * Updates text labels and triggers secondary UI components.
 * @param {string} name - Subcategory name.
 * @param {Array} data - Processed data.
 * @param {string} strategy - Active strategy ID.
 * @param {boolean} locked - Lock status.
 */
function updateAnalysisUI(name, data, strategy, locked) {
    $('#analysisChartTitle').text("Analysis: " + name);
    $('#detailCatName').text(name);

    updateAnalysisTabs(data, strategy, locked);
    updateGrowthImpactUI(data);
}


function resetNewCategoryForm($nameInput, $errorDisplay) {
    $errorDisplay.addClass('d-none').text('');
    $nameInput.removeClass('is-invalid').val('');
    updateAddPillColor('#0d6efd');
}

/**
 * Updates the loading and visual state of the performance workbench.
 */
function setPerformanceLoading(isLoading, year = "") {
    const $resultsContainer = $('#performanceResults');
    const $chartContainer = $('#categoryAnalysisChart');

    if (isLoading) {
        $chartContainer.removeClass('chart-ready').addClass('chart-reworking');
        $resultsContainer.html(`
            <div class="text-center p-5">
                <div class="spinner-grow text-primary" role="status"></div>
                <p class="small text-muted mt-2">Analyzing ${year} Budget Accuracy...</p>
            </div>
        `);
    } else {
        setTimeout(() => {
            $chartContainer.removeClass('chart-reworking').addClass('chart-ready');
        }, 400);
    }
}


/**
 * Renders the results cards into the performance container.
 */
function renderPerformanceResults(data) {
    const categoryId = $('#analysisSubcatId').val();

    // Map data to HTML using our new modular functions
    const cardsHtml = data.map(item => {
        const metrics = calculatePerformanceMetrics(item);
        const linkUrl = getPerformanceLink(item.period, categoryId);
        return renderPerformanceCardHtml(item, metrics, linkUrl);
    }).join('');

    $('#performanceResults').html(`<div class="row g-2">${cardsHtml}</div>`);
}

/**
 * Resets the styling of the threshold input field.
 * @param {jQuery} $input - The threshold input element.
 */
function resetThresholdInputStyle($input) {
    $input.css({
        'border-bottom-color': '#dee2e6',
        'background-color': 'transparent'
    });
}

/**
 * Updates the "Mini Analysis" UI on the category card.
 * @param {string} id - The category ID.
 * @param {number} newAmount - The new threshold amount.
 */
function updateCardMiniAnalysis(id, newAmount) {
    const $card = $(`.modern-cat-card[data-id="${id}"]`);
    if (!$card.length) return;

    const $bar = $card.find('.progress-bar-fill');
    const spentAmount = parseFloat($bar.attr('data-amount') || 0);
    const isExpense = $card.hasClass('border-expense');

    // 1. Calculate percentage and update labels
    const newPercent = (newAmount > 0) ? (spentAmount / newAmount) * 100 : 0;
    $card.find('.target-text-label').text('$' + newAmount.toLocaleString(undefined, {
        minimumFractionDigits: 2,
        maximumFractionDigits: 2
    }));

    // 2. Update Progress Bar state
    $bar.css('width', (newPercent > 100 ? 100 : newPercent) + '%');
    $bar.attr('aria-valuenow', newPercent);

    // 3. Update Colors based on "Over-Budget" status
    const isOver = spentAmount > newAmount;
    let color = $card.find('.card-accent').css('background-color');

    if (isOver) {
        color = isExpense ? '#dc3545' : '#198754';
        $card.find('.fw-bold').first()
            .addClass(isExpense ? 'text-danger' : 'text-success')
            .removeClass('text-dark');
    } else {
        $card.find('.fw-bold').first()
            .addClass('text-dark')
            .removeClass('text-danger text-success');
    }
    $bar.css('background-color', color);
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
 * Updates the Retirement Impact visual state.
 * @param {number} monthlyBurn - The projected monthly cost (usually from inflation model).
 * @param {number} totalGoal - The user's total monthly retirement budget (e.g., 5000).
 */
function updateRetirementUI(monthlyBurn, totalGoal = 5000) {
    // 1. Calculate percentage
    const percent = Math.min((monthlyBurn / totalGoal) * 100, 100);

    // 2. Update the Text
    $('#forecastAgeValue').text(`$${monthlyBurn.toLocaleString(undefined, {
        minimumFractionDigits: 2,
        maximumFractionDigits: 2
    })} /mo`);

    // 3. Update the Progress Bar and Percentage Label
    $('#retirementProgressBar').css('width', `${percent}%`);
    $('#forecastPercent').text(`${percent.toFixed(1)}%`);
}