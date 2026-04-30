// --- 1. Global State & Scoped Objects ---
const pickers = {};
let categoryIdToDelete = null;
let selectedMonth = "";
let selectedYyear = "";
let lockBoxSkip = false;

$(document).ready(function () {
    // Tab/Chart Layout Management
    $('button[data-bs-toggle="pill"]').on('show.bs.tab', handleTabTransition);

    // General UI Listeners
    $("#btnAutoAssign").on("click", showAutoAssignModal);
    $("#btnAutoColor").on("click", showAutoAssignColorModal);
    $("#confirmAutoAssignBtn").on("click", handleAutoAssignExecution);
    $("#confirmAutoAssignColorBtn").on("click", handleAutoAssignColorExecution);

    // Dynamic Element Listeners
    $(document).on('click', '.color-swatch-trigger', (e) => handleColorPickerToggle(e, pickers));
    $(document).on('click', '#confirmDeleteBtn', handleConfirmDelete);
    $(document).on('click', '.strategy-icon', (e) => handleStrategyClick(e, pickers));
    $(document).on('blur', '.category-text', (e) => handleNameBlur(e, pickers));

    // Forecast Chart Highlighting
    $(document).on('mouseenter', '.forecast-row', handleForecastHover);
    $(document).on('mouseleave', '.forecast-row', resetForecastHover);
    $(document).on('click', '.forecast-row', handleForecastRowClick);

    // Global Popover Management
    $(document).on('click', handleGlobalClick);
    $(document).on('click', '.picker-popover', (e) => e.stopPropagation());


    $(document).on('blur', '#setBudgetedAmount', (e) => handleThresholdBlur(e));
    $(document).on('focus', '#setBudgetedAmount', (e) => handleThresholdFocus(e));
    $(document).on('change', '#yearSelect', (e) => handleMonthChange(e));
    $(document).on('change', '#monthSelect', (e) => handleMonthChange(e));


});