$(document).ready(function() {
    let currentData = window.initialData;
    let mode = "0";  // 0=Annual, 1=Monthly
    let level = "0"; // 0=Sub, 1=Super

    const updateSidebar = () => {
        const list = $('#categoryList').empty();
        if (!currentData || currentData.length === 0) return;
        currentData[0].forEach(cat => {
            list.append(`
                <li class="category-item">
                    <div class="form-check d-flex align-items-center">
                        <input class="form-check-input cat-check me-2" type="checkbox" 
                               value="${cat.category_Name}" id="c_${cat.category_ID}" checked>
                        <span class="color-swatch" style="background-color:${cat.color_id}; width:12px; height:12px; border-radius:50%; margin-right:10px; display:inline-block;"></span>
                        <label class="form-check-label small" for="c_${cat.category_ID}">${cat.category_Name}</label>
                    </div>
                </li>`);
        });
    };

    const renderCharts = () => {
        const selectedCats = $('.cat-check:checked').map(function() { return this.value; }).get();
        let trendSeries = [];
        let pieDataPoints = [];

        selectedCats.forEach(name => {
            let color = "";
            const points = currentData.map(period => {
                const match = period.find(c => c.category_Name === name);
                if (match) color = match.color_id;
                // period_val is the year in annual mode, month index in monthly mode
                let label = mode === "0" ? match?.year : getMonthName(match?.year);
                return match ? { label: label, y: Math.abs(match.amount), count: match.count } : null;
            }).filter(p => p);

            if (points.length > 0) {
                const lowerName = name.toLowerCase();
                const isIncome = lowerName.includes("income") || lowerName.includes("total in");

                // MANDATORY: stackGroup must be present on EVERY series to prevent "The Tower"
                trendSeries.push({
                    type: "stackedColumn",
                    name: name,
                    stackGroup: isIncome ? "inflow" : "outflow",
                    color: color,
                    showInLegend: true,
                    dataPoints: points
                });

                // PIE DATA (Latest period, Expenses Only)
                const latestPeriod = currentData[currentData.length - 1];
                const latestMatch = latestPeriod.find(c => c.category_Name === name);
                if (latestMatch && !isIncome && !latestMatch.category_ID.includes("SYSTEM")) {
                    pieDataPoints.push({ y: Math.abs(latestMatch.amount), name: name, color: color });
                }
            }
        });

        // MAIN TREND CHART
        new CanvasJS.Chart("chartContainer", {
            animationEnabled: true,
            theme: "light2",
            title: { text: "Income Stack vs Expense Stack", fontWeight: "bold" },
            axisY: { prefix: "$", title: "Amount" },
            toolTip: {
                shared: true,
                contentFormatter: function(e) {
                    let content = "<strong>" + e.entries[0].dataPoint.label + "</strong><br/>";
                    let inSum = 0, outSum = 0;
                    e.entries.forEach(entry => {
                        if (entry.dataSeries.stackGroup === "inflow") inSum += entry.dataPoint.y;
                        else outSum += entry.dataPoint.y;
                        content += `<span style='color:${entry.dataSeries.color}'>${entry.dataSeries.name}</span>: $${entry.dataPoint.y.toLocaleString()}<br/>`;
                    });
                    const net = inSum - outSum;
                    content += `<hr/>In: $${inSum.toLocaleString()} | Out: $${outSum.toLocaleString()}<br/>`;
                    content += `<strong style='color:${net >= 0 ? "#28a745" : "#dc3545"}'>Net: $${net.toLocaleString()}</strong>`;
                    return content;
                }
            },
            data: trendSeries
        }).render();

        // PIE CHART
        new CanvasJS.Chart("pieContainer", {
            animationEnabled: true,
            theme: "light2",
            title: { text: "Spending Breakdown", fontSize: 18 },
            legend: { verticalAlign: "bottom" },
            data: [{
                type: "pie",
                showInLegend: true,
                indexLabel: "",
                toolTipContent: "{name}: $#total (#percent%)",
                dataPoints: pieDataPoints
            }]
        }).render();
    };

    const fetchAnalysisData = () => {
        const selectedYear = $('#inputYear').val();
        $.get("AnalysisAJAX", { mode, level, year: selectedYear }, (res) => {
            currentData = res;
            updateSidebar();
            renderCharts();
        });
    };

    // UI BUTTONS
    $('#btnAnnual, #btnMonthly').off().click(function() {
        mode = $(this).data('val').toString();
        $(this).addClass('active btn-primary').removeClass('btn-outline-primary');
        $(this).siblings().removeClass('active btn-primary').addClass('btn-outline-primary');

        // Fix for Year Selector visibility
        if (mode === "1") {
            $('#yearSelectorContainer').show();
        } else {
            $('#yearSelectorContainer').hide();
        }
        fetchAnalysisData();
    });

    $('#btnSub, #btnSuper').off().click(function() {
        level = $(this).data('val').toString();
        $(this).addClass('active btn-primary').removeClass('btn-outline-primary');
        $(this).siblings().removeClass('active btn-primary').addClass('btn-outline-primary');
        fetchAnalysisData();
    });

    // Ensure Year Selector change triggers a refresh
    $('#inputYear').on('change', function() {
        if (mode === "1") fetchAnalysisData();
    });

    $(document).on('change', '.cat-check', renderCharts);

    // Initial Load
    if (window.initialData && window.initialData.length > 0) {
        updateSidebar();
        renderCharts();
    } else {
        fetchAnalysisData();
    }
});

function getMonthName(m) {
    return ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"][m - 1] || "N/A";
}