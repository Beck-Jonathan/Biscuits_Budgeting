$(document).ready(function () {
    // Configuration constants
    const id = document.getElementById("budgetID").innerText;

    const colorNames = {
        "000000": "Black", "FFFFFF": "White", "808080": "Gray", "A9A9A9": "Dark Gray",
        "D3D3D3": "Light Gray", "C0C0C0": "Silver", "696969": "Dim Gray", "F5F5F5": "White Smoke",
        "FF0000": "Red", "8B0000": "Dark Red", "B22222": "Firebrick", "FF4500": "Orange Red",
        "FF6347": "Tomato", "FF7F50": "Coral", "FFC0CB": "Pink", "FF69B4": "Hot Pink",
        "FF1493": "Deep Pink", "C71585": "Medium Violet Red", "DB7093": "Pale Violet Red",
        "FFA500": "Orange", "FF8C00": "Dark Orange", "FFD700": "Gold", "FFFF00": "Yellow",
        "FFFFE0": "Light Yellow", "FFFACD": "Lemon Chiffon", "FAFAD2": "Light Goldenrod Yellow",
        "FFEFD5": "Papaya Whip", "FFE4B5": "Moccasin", "F5DEB3": "Wheat",
        "008000": "Green", "00FF00": "Lime", "32CD32": "Lime Green", "228B22": "Forest Green",
        "006400": "Dark Green", "6B8E23": "Olive Drab", "808000": "Olive", "9ACD32": "Yellow Green",
        "2E8B57": "Sea Green", "3CB371": "Medium Sea Green", "8FBC8F": "Dark Sea Green",
        "90EE90": "Light Green", "00FA9A": "Medium Spring Green",
        "0000FF": "Blue", "00008B": "Dark Blue", "000080": "Navy", "4169E1": "Royal Blue",
        "1E90FF": "Dodger Blue", "00BFFF": "Deep Sky Blue", "87CEEB": "Sky Blue",
        "ADD8E6": "Light Blue", "00FFFF": "Cyan", "E0FFFF": "Light Cyan", "AFEEEE": "Pale Turquoise",
        "40E0D0": "Turquoise", "008B8B": "Dark Cyan", "008080": "Teal",
        "800080": "Purple", "8B008B": "Dark Magenta", "FF00FF": "Magenta", "9370DB": "Medium Purple",
        "8A2BE2": "Blue Violet", "4B0082": "Indigo", "A52A2A": "Brown", "8B4513": "Saddle Brown",
        "D2691E": "Chocolate", "F4A460": "Sandy Brown", "BC8F8F": "Rosy Brown", "DEB887": "Burly Wood"
    };

    const budget_line_types_json = ['Income', 'Expense', 'Transfer', 'Debt_Payment', 'Savings'];
    const budget_line_statuses_json = ['Planned', 'Pending', 'Completed', 'Cancelled', 'Recurring'];

    const color_palette = [
        {hex: "#FF0000", name: ''}, {hex: "#00FF00", name: ''}, {hex: "#0000FF", name: ''},
        {hex: "#FFFF00", name: ''}, {hex: "#FF00FF", name: ''}, {hex: "#00FFFF", name: ''},
        {hex: "#FF8C00", name: ''}, {hex: "#8A2BE2", name: ''}, {hex: "#008080", name: ''},
        {hex: "#FF1493", name: ''}, {hex: "#32CD32", name: ''}, {hex: "#1E90FF", name: ''}
    ];

    for (let colorObj of color_palette) {
        colorObj.name = hexToWord(colorObj.hex);
    }

    let chart;
    let submitbutton = document.getElementById("submitButton");

    // ================= INIT =================
    initChart();
    makeEditable();
    recalcTotal();
    translateDropdown();
    prepColorPicker();
    nameExistingColors();

    // ================= EVENT LISTENERS =================
    $("#addButton").click(function () {
        addLineItem();
    });

    $("#inputBudgetname, #inputBudgetdetails").on("blur input", function () {
        let nameVal = $("#inputBudgetname").val().trim();
        let detailVal = $("#inputBudgetdetails").val().trim();
        let isValid = nameVal.length > 1 && nameVal.length <= 50 && detailVal.length > 1;

        if (submitbutton) submitbutton.disabled = !isValid;
        $(this).toggleClass("is-invalid", !isValid).toggleClass("is-valid", isValid);
    });

    // ================= FUNCTIONS =================

    function initChart() {
        const ctx = document.getElementById("budgetChart").getContext("2d");
        chart = new Chart(ctx, {
            type: "pie",
            data: {
                labels: [],
                datasets: [{
                    data: [],
                    backgroundColor: [],
                    borderWidth: 1
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                plugins: {
                    legend: { position: 'bottom' }
                }
            }
        });
    }

    function updateChart(currentTotal) {
        const limit = parseFloat($("#budgetLimit").text()) || 0;
        const labels = [];
        const data = [];
        const colors = [];

        $("#lineItemBody tr").each(function () {
            if (this.id !== "inputRow") {
                let name = $(this).find('[data-field="name"]').text().trim();
                let amt = parseFloat($(this).find('[data-field="amount"]').text());
                let hex = $(this).find('[data-field="color"]').attr("data-hex") || $(this).find('[data-field="color"]').data("hex");

                if (hex && !hex.startsWith('#')) hex = '#' + hex;

                if (!isNaN(amt) && amt > 0) {
                    labels.push(name);
                    data.push(amt);
                    colors.push(hex || "#4E79A7");
                }
            }
        });

        if (limit > currentTotal) {
            labels.push("Available");
            data.push(limit - currentTotal);
            colors.push("#e0e0e0");
        }

        chart.data.labels = labels;
        chart.data.datasets[0].data = data;
        chart.data.datasets[0].backgroundColor = colors;
        chart.update();
    }

    function recalcTotal() {
        let currentTotal = 0;
        $("#lineItemBody tr").each(function () {
            if (this.id !== "inputRow") {
                let amount = parseFloat($(this).find('[data-field="amount"]').text());
                if (!isNaN(amount)) currentTotal += amount;
            }
        });
        $("#totalUsed").text(currentTotal.toFixed(2));
        updateChart(currentTotal);
    }

    window.addLineItem = function () {
        const name = $("#name").val();
        const details = $("#details").val();
        const amount = parseFloat($("#amount").val());
        const date = $("#date").val();
        const type = $("#type").val();
        const status = $("#status").val();
        const color = $("#color").val();

        if (!name || isNaN(amount) || !date) {
            alert("Please fill in Name, Amount, and Date.");
            return;
        }

        const tempId = "temp_" + Date.now();
        const row = `
            <tr data-id="${tempId}">
                <td class="editable" data-field="name">${name}</td>
                <td class="editable" data-field="details">${details}</td>
                <td class="editable" data-field="amount">${amount.toFixed(2)}</td>
                <td class="editable" data-field="date">${date}</td>
                <td class="editable-select" data-field="type">${type}</td>
                <td class="editable-select" data-field="status">${status}</td>
                <td class="editable-select" data-field="color" data-hex="${color}">
                    <span style="color:${color}; font-size: 1.5rem; line-height: 1; vertical-align: middle;">●</span>
                </td>
                <td>
                    <button class="btn btn-sm btn-danger" type="button" 
                            onclick="deleteLineItem(this, '${tempId}')">Delete</button>
                </td>
            </tr>`;

        $("#inputRow").before(row);
        recalcTotal();

        const params = new URLSearchParams();
        params.append("inputbudget_line_itembudget_id", id);
        params.append("inputbudget_line_itemname", name);
        params.append("inputbudget_line_itemdetails", details);
        params.append("inputbudget_line_itemamount", amount);
        params.append("inputbudget_line_itemline_item_date", date);
        params.append("inputbudget_line_itembudget_line_type_id", type);
        params.append("inputbudget_line_itembudget_line_status_id", status);
        params.append("inputbudget_line_itemcolor_id", color);

        fetch('addBudget_line_item', {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: params
        })
            .then(response => response.text())
            .then(result => {
                const statusCode = parseInt(result);
                if (statusCode <= 0) handleError(statusCode);
            })
            .catch(error => console.error('Fetch error:', error));

        $("#name, #details, #amount, #date").val("");
    };

    window.deleteLineItem = function(btn, id) {
        if(confirm("Delete this item?")) {
            $(btn).closest("tr").remove();
            recalcTotal();
            fetch('/deleteLineItem', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ id: id })
            });
        }
    };

    function updateLineItem(row) {
        const data = {
            id: row.data("id"),
            name: row.find('[data-field="name"]').text().trim(),
            details: row.find('[data-field="details"]').text().trim(),
            amount: parseFloat(row.find('[data-field="amount"]').text()),
            line_item_date: row.find('[data-field="date"]').text().trim(),
            type: row.find('[data-field="type"]').text().trim(),
            status: row.find('[data-field="status"]').text().trim(),
            color: row.find('[data-field="color"]').attr("data-hex")
        };
        recalcTotal();
        fetch('/updateLineItem', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data)
        });
    }

    function makeEditable() {
        $(document).off("click", ".editable, .editable-select").on("click", ".editable, .editable-select", function () {
            if ($(this).find("input, select").length > 0) return;

            const field = $(this).data("field");
            const cell = $(this);
            const row = cell.closest("tr");

            if (field === "color") {
                let currentHex = cell.attr("data-hex") || cell.data("hex");
                let select = $("<select class='form-select form-select-sm'></select>");

                select.css({"background-color": currentHex, "color": isDark(currentHex) ? "white" : "black"});

                color_palette.forEach(c => {
                    select.append(`<option value="${c.hex}" ${c.hex === currentHex ? "selected" : ""}>${c.name}</option>`);
                });

                cell.html(select);
                select.focus();

                select.on("change", function() {
                    const newHex = $(this).val();
                    $(this).css({"background-color": newHex, "color": isDark(newHex) ? "white" : "black"});
                    cell.attr("data-hex", newHex);
                    recalcTotal();
                });

                select.on("blur keydown", function(e) {
                    if (e.type === "keydown" && e.key !== "Enter") return;
                    const finalHex = $(this).val();
                    cell.html(`<span style="color:${finalHex}; font-size: 1.5rem; line-height: 1; vertical-align: middle;">●</span>`);
                    updateLineItem(row);
                });
            } else if (cell.hasClass("editable-select")) {
                let current = cell.text().trim();
                let options = field === "type" ? budget_line_types_json : budget_line_statuses_json;
                let select = $("<select class='form-select form-select-sm'></select>");
                options.forEach(opt => select.append(`<option value="${opt}" ${opt === current ? "selected" : ""}>${opt}</option>`));
                cell.html(select);
                select.focus().on("blur change", function() {
                    cell.text($(this).val());
                    updateLineItem(row);
                });
            } else {
                let currentText = cell.text().trim();
                let inputType = field === "amount" ? "number" : (field === "date" ? "date" : "text");
                let input = $(`<input type="${inputType}" class="form-control form-control-sm" value="${currentText}" />`);
                cell.html(input);
                input.focus().on("blur keydown", function(e) {
                    if (e.type === "keydown" && e.key !== "Enter") return;
                    cell.text($(this).val());
                    updateLineItem(row);
                });
            }
        });
    }

    function isDark(color) {
        const hex = color.replace('#', '');
        const r = parseInt(hex.substr(0, 2), 16), g = parseInt(hex.substr(2, 2), 16), b = parseInt(hex.substr(4, 2), 16);
        return ((r * 299) + (g * 587) + (b * 114)) / 1000 < 155;
    }

    function hexToRgb(hex) {
        hex = hex.replace('#', '');
        return { r: parseInt(hex.substring(0, 2), 16), g: parseInt(hex.substring(2, 4), 16), b: parseInt(hex.substring(4, 6), 16) };
    }

    function hexToWord(targetHex) {
        const cleanHex = targetHex.replace('#', '').toUpperCase();
        if (colorNames[cleanHex]) return colorNames[cleanHex];
        const targetRgb = hexToRgb(cleanHex);
        let closestName = "Unknown", minDistance = Infinity;
        for (const [hex, name] of Object.entries(colorNames)) {
            const currentRgb = hexToRgb(hex);
            const distance = Math.sqrt(Math.pow(targetRgb.r - currentRgb.r, 2) + Math.pow(targetRgb.g - currentRgb.g, 2) + Math.pow(targetRgb.b - currentRgb.b, 2));
            if (distance < minDistance) { minDistance = distance; closestName = name; }
        }
        return closestName;
    }

    function translateDropdown() {
        $(".color-option").each(function() {
            const hex = $(this).val();
            if (hex) $(this).text(hexToWord(hex));
        });
    }

    function prepColorPicker() {
        // Clear any existing content first to prevent "double circles" on re-init
        $("#colorPreview").empty();

        // Add the initial circle
        $("#colorPreview").append('<span id="previewBullet" style="font-size: 1.5rem; line-height: 1; vertical-align: middle;">●</span>');

        $("#color").on("change", function() {
            const selectedColor = $(this).val();
            // Ensure the hex has a hash
            const cleanHex = selectedColor.startsWith('#') ? selectedColor : '#' + selectedColor;

            // Update the color of the existing bullet
            $("#previewBullet").css("color", cleanHex);
        }).trigger("change"); // Trigger immediately to set the initial color
    }


function handleError(code) {
    const errors = { "-1": "Session expired.", "-2": "Invalid Budget ID.", "-10": "Database error." };
    alert("Error (" + code + "): " + (errors[code] || "Request failed."));
}

function nameExistingColors(){
    var existingColors = document.getElementsByClassName("color-label");
    for (i=0;i<existingColors.length;i++){
        existingColors[i].textContent = hexToWord(existingColors[i].textContent)
    }
}

});