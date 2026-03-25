$(document).ready(function () {
    // Configuration constants
    var budgetId = "";
    try {
        budgetId = document.getElementById("budgetID").innerText;
    } catch (e){
        budgetId = "";
    }

    const colorNames = {
        "000000": "Black", "FFFFFF": "White", "808080": "Gray", "A9A9A9": "Dark Gray",
        "D3D3D3": "Light Gray", "C0C0C0": "Silver", "696969": "Dim Gray", "F5F5F5": "White Smoke",
        "FF0000": "Red", "8B0000": "Dark Red", "B22222": "Firebrick", "FF4500": "Orange Red",
        "FF6347": "Tomato", "FF7F50": "Coral", "FFC0CB": "Pink", "FF69B4": "Hot Pink",
        "FF1493": "Deep Pink", "C71585": "Medium Violet Red", "DB7093": "Pale Violet Red",
        "FFA500": "Orange", "FF8C00": "Dark Orange", "FFD700": "Gold", "FFFF00": "Yellow",
        "008000": "Green", "00FF00": "Lime", "32CD32": "Lime Green", "228B22": "Forest Green",
        "0000FF": "Blue", "00008B": "Dark Blue", "1E90FF": "Dodger Blue", "00FFFF": "Cyan",
        "800080": "Purple", "FF00FF": "Magenta", "8A2BE2": "Blue Violet", "A52A2A": "Brown"
    };

    const budget_line_types_json = ['Income', 'Expense', 'Transfer', 'Debt_Payment', 'Savings'];
    const budget_line_statuses_json = ['Planned', 'Pending', 'Completed', 'Cancelled', 'Recurring'];

    // We keep this palette for the "Edit" dropdowns in the table
    const color_palette = [
        {hex: "#FF0000", name: 'Red'}, {hex: "#00FF00", name: 'Green'}, {hex: "#0000FF", name: 'Blue'},
        {hex: "#FFFF00", name: 'Yellow'}, {hex: "#FF00FF", name: 'Magenta'}, {hex: "#00FFFF", name: 'Cyan'},
        {hex: "#FF8C00", name: 'Orange'}, {hex: "#8A2BE2", name: 'Violet'}, {hex: "#008080", name: 'Teal'},
        {hex: "#FF1493", name: 'Pink'}, {hex: "#32CD32", name: 'Lime'}, {hex: "#1E90FF", name: 'Dodger Blue'}
    ];

    let chart;
    let submitbutton = document.getElementById("submitButton");

    // ================= INIT =================
    initChart();
    makeEditable();
    recalcTotal();
    nameExistingColors();
    prepColorPicker(); // This handles the #color and #colorPreview logic
    addTableHeaders();

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

});

    // ================= FUNCTIONS =================

    function initChart() {
        try {
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
                    plugins: { legend: { position: 'bottom' } }
                }
            });
        } catch (e) { console.warn("Chart.js not loaded or element missing"); }
    }

function updateChart(currentTotal) {
    if (chart) {
        const limit = parseFloat($("#budgetLimit").text().replace(/,/g, '')) || 0;
        const labels = [];
        const data = [];
        const colors = [];

        $("#lineItemBody tr").each(function () {
            // 1. Skip the input row
            if (this.id !== "inputRow") {
                const $row = $(this);

                // 2. Determine current status (checks both static text and active <select> dropdowns)
                let status = $row.find('[data-field="status"] select').val() ||
                    $row.find('[data-field="status"]').text().trim();

                // 3. FILTER: Only process if status is NOT inactive
                if (status.toLowerCase() !== 'inactive') {
                    let name = $row.find('[data-field="name"]').text().trim();
                    let amt = parseFloat($row.find('[data-field="amount"]').text());
                    let hex = $row.find('[data-field="color"]').attr("data-hex");

                    // Ensure hex has the hash prefix for Chart.js
                    if (hex && !hex.startsWith('#')) hex = '#' + hex;

                    if (!isNaN(amt) && amt > 0) {
                        labels.push(name);
                        data.push(amt);
                        colors.push(hex || "#4E79A7");
                    }
                }
            }
        });

        // 4. Calculate "Available" slice based on filtered currentTotal
        if (limit > currentTotal) {
            labels.push("Available");
            data.push(limit - currentTotal);
            colors.push("#e0e0e0");
        }

        // 5. Push updates to the Chart.js instance
        chart.data.labels = labels;
        chart.data.datasets[0].data = data;
        chart.data.datasets[0].backgroundColor = colors;
        chart.update();
    }
}

function recalcTotal() {
    let currentTotal = 0;
    let itemCount = 0;
    const limit = parseFloat($("#budgetLimit").text().replace(/,/g, '')) || 0;

    $("#lineItemBody tr").each(function () {
        if (this.id !== "inputRow") {
            const $row = $(this);
            // Get current status
            let status = $row.find('[data-field="status"] select').val() ||
                $row.find('[data-field="status"]').text().trim();

            if (status.toLowerCase() !== 'inactive') {
                // ACTIVE ROW
                $row.removeClass("row-inactive"); // Ensure fade is removed
                let amount = parseFloat($row.find('[data-field="amount"]').text());
                if (!isNaN(amount)) {
                    currentTotal += amount;
                    itemCount++;
                }
            } else {
                // INACTIVE ROW
                $row.addClass("row-inactive"); // Apply the fade
            }
        }
    });

    $("#totalUsed").text(currentTotal.toFixed(2));
    $("#totalRemaining").text((limit - currentTotal).toFixed(2));
    $("#totalCount").text(itemCount);
    updateChart(currentTotal);
}

    window.addLineItem = function () {
        var budgetId = document.getElementById("budgetID").innerText.trim();
        const name = $("#name").val();
        const details = $("#details").val();
        const amount = parseFloat($("#amount").val());
        const date = $("#date").val();
        const type = $("#type").val();
        const status = $("#status").val();

        // 1. Grab the Select element
        const categorySelect = document.getElementById("color");
        const selectedOption = categorySelect.options[categorySelect.selectedIndex];

        // 2. Extract all 3 parts of the category
        const categoryId = categorySelect.value;
        const categoryName = selectedOption.text.trim(); // This gets the text inside the <option>
        const colorHex = selectedOption.getAttribute("data-color");

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
            <td class="editable-select" data-field="color" data-hex="${colorHex}" data-categoryid="${categoryId}">
                <span style="color:${colorHex}; font-size: 1.5rem; line-height: 1; vertical-align: middle;">●</span>
                <span class="color-label">${categoryName}</span>
            </td>
            <td>
                <button class="btn btn-sm btn-outline-danger" type="button" 
                        onclick="deleteLineItem(this, '${tempId}')">Delete</button>
            </td>
        </tr>`;

        $("#inputRow").before(row);
        recalcTotal();
        

        const params = new URLSearchParams();
        params.append("inputbudget_line_itembudget_id", budgetId);
        params.append("inputbudget_line_itemname", name);
        params.append("inputbudget_line_itemdetails", details);
        params.append("inputbudget_line_itemamount", amount);
        params.append("inputbudget_line_itemline_item_date", date);
        params.append("inputbudget_line_itembudget_line_type_id", type);
        params.append("inputbudget_line_itembudget_line_status_id", status);
        params.append("inputcategory_id", categoryId); // Send the ID, not the color string

        fetch('addBudget_line_item', {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: params
        })
            .then(response => response.text())
            .then(newUuid => {
                if (parseInt(newUuid) <= 0) {
                    handleError(parseInt(newUuid));
                    $(`tr[data-id="${tempId}"]`).remove();
                    recalcTotal();
                    return;
                }
                const $row = $(`tr[data-id="${tempId}"]`);
                $row.attr("data-id", newUuid);
                $row.find("button").attr("onclick", `deleteLineItem(this, '${newUuid}')`);
            })
            .catch(err => console.error('Fetch error:', err));

        $("#name, #details, #amount").val("");
    };

    window.deleteLineItem = function(btn, lineId) {
        if(confirm("Delete this item?")) {
            const params = new URLSearchParams();
            params.append("budget_line_itemid", lineId);
            $(btn).closest("tr").remove();
            recalcTotal();
            fetch('deleteBudget_line_item', {
                method: 'POST',
                headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                body: params
            });
        }
    };

    function updateLineItem(row) {
        // 'row' is a jQuery object representing the <tr>
        const colorCell = row.find('[data-field="color"]');

        if (!colorCell.length) {
            console.error("Could not find the color cell for row:", row.data("id"));
            return;
        }

        const params = new URLSearchParams();
        params.append("inputbudget_line_itembudget_line_item_id", row.data("id"));

        // Standard text fields
        params.append("inputbudget_line_itemname", row.find('[data-field="name"]').text().trim());
        params.append("inputbudget_line_itemdetails", row.find('[data-field="details"]').text().trim());
        params.append("inputbudget_line_itemamount", parseFloat(row.find('[data-field="amount"]').text()) || 0);
        params.append("inputbudget_line_itemline_item_date", row.find('[data-field="date"]').text().trim());

        // UPDATED: Grab values from the dropdowns
        // We look for the select inside the cell; if it's not a select, we fallback to text
        const typeVal = row.find('[data-field="type"] select').val() || row.find('[data-field="type"]').text().trim();
        const statusVal = row.find('[data-field="status"] select').val() || row.find('[data-field="status"]').text().trim();

        params.append("inputbudget_line_itembudget_line_type_id", typeVal);
        params.append("inputbudget_line_itembudget_line_status_id", statusVal);

        // Category ID from data attribute
        params.append("inputbudget_line_itemCategory_id", colorCell.attr("data-categoryid"));

        fetch('editbudget_line_item', {
            method: 'POST',
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            body: params
        }).then(response => {
            if (response.ok) {
                recalcTotal();
                // Optional: Provide a small visual "saved" flash
                row.css('background-color', '#f0fff0');
                setTimeout(() => row.css('background-color', ''), 500);
            }
        })}


    function makeEditable() {
        $(document).off("click", ".editable, .editable-select").on("click", ".editable, .editable-select", function () {
            if ($(this).find("input, select").length > 0) return;

            const field = $(this).data("field");
            const cell = $(this);
            const row = cell.closest("tr");

            if (field === "color") {
                let currentId = cell.attr("data-categoryid");
                let select = $("<select class='form-select form-select-sm'></select>");

                // Pull categories from the hidden/template list or clone the main categorySelect
                $("#color option").each(function() {
                    let catId = $(this).val();
                    let catName = $(this).text().trim();
                    let catHex = $(this).data("color");
                    select.append(`<option value="${catId}" data-color="${catHex}" ${catId === currentId ? "selected" : ""}>${catName}</option>`);
                });

                cell.html(select);
                select.focus().on("blur change", function() {
                    const selectedOpt = $(this).find('option:selected');
                    const finalId = $(this).val();
                    const finalName = selectedOpt.text();
                    const finalHex = selectedOpt.data("color");

                    // Update the cell attributes
                    cell.attr("data-categoryid", finalId);
                    cell.attr("data-hex", finalHex);

                    // Update the cell display
                    cell.html(`
            <span style="color:${finalHex}; font-size: 1.5rem; line-height: 1; vertical-align: middle;">●</span> 
            <span class="color-label">${finalName}</span>
        `);

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

    function hexToRgb(hex) {
        hex = hex.replace('#', '');
        return { r: parseInt(hex.substring(0, 2), 16), g: parseInt(hex.substring(2, 4), 16), b: parseInt(hex.substring(4, 6), 16) };
    }

    function hexToWord(targetHex) {
        if (!targetHex) return "Unknown";
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

    function prepColorPicker() {
        const $colorSelect = $("#color");
        const $preview = $("#colorPreview");

        $colorSelect.on("change", function() {
            // Get the hex code from the data-color attribute of the selected option
            const hex = $(this).find('option:selected').data('color');

            if (hex) {
                // Apply the color to the text (the bullet character)
                $preview.css("color", hex);
            }
        }).trigger("change"); // Initialize immediately
    }

    function nameExistingColors(){
       // $(".color-label").each(function() {
       //     $(this).text(hexToWord($(this).text()));
       // });
    }

    function handleError(code) {
        const errors = { "-1": "Session expired.", "-2": "Invalid Budget ID.", "-10": "Database error." };
        alert("Error (" + code + "): " + (errors[code] || "Request failed."));
    }

function addTableHeaders() {
    const table = document.getElementById('lineItemTable');
    if (!table) return; // Guard clause

    const headers = table.querySelectorAll('thead th');
    const tableBody = table.querySelector('#lineItemBody');
    const inputRow = document.getElementById('inputRow');

    let sortDirection = true; // true = asc, false = desc

    headers.forEach((header, index) => {
        // Skip the "Action" column
        if (header.innerText.trim() === 'Action') return;

        header.style.cursor = 'pointer';
        // Optional: add a small indicator or title
        header.title = "Click to sort";

        header.addEventListener('click', () => {
            sortDirection = !sortDirection;
            sortTable(index, sortDirection);
        });
    });

    function sortTable(columnIndex, ascending) {
        const rows = Array.from(tableBody.querySelectorAll('tr:not(#inputRow)'));

        const sortedRows = rows.sort((a, b) => {
            // Helper to get value whether cell is currently an input/select or just text
            const getVal = (row, idx) => {
                const cell = $(row.children[idx]);
                const input = cell.find('input, select');

                if (input.length > 0) {
                    return input.val().toLowerCase();
                }

                // For the "Category/Color" column, sort by the label text
                if (cell.data('field') === 'color') {
                    return cell.find('.color-label').text().trim().toLowerCase();
                }

                return cell.text().trim().toLowerCase();
            };

            let valA = getVal(a, columnIndex);
            let valB = getVal(b, columnIndex);

            // Numerical sort for the "Amount" column (Index 2)
            if (columnIndex === 2) {
                let numA = parseFloat(valA.replace(/[^0-9.-]+/g, "")) || 0;
                let numB = parseFloat(valB.replace(/[^0-9.-]+/g, "")) || 0;
                return ascending ? numA - numB : numB - numA;
            }

            // Date sort (Index 3)
            if (columnIndex === 3) {
                let dateA = new Date(valA);
                let dateB = new Date(valB);
                return ascending ? dateA - dateB : dateB - dateA;
            }

            // Default string sort
            return ascending
                ? valA.localeCompare(valB)
                : valB.localeCompare(valA);
        });

        // Re-append rows in new order
        sortedRows.forEach(row => tableBody.appendChild(row));

        // Always push the inputRow to the bottom
        if (inputRow) {
            tableBody.appendChild(inputRow);
        }
    }
}

window.saveTheRest = function() {
    // 1. Pull current numbers from Summary div
    const remainingText = $('#totalRemaining').text().replace(/[^0-9.-]+/g,"");
    const remainingBalance = parseFloat(remainingText);

    // Attempt to get budgetId from your defined variable or the DOM
    var budgetId = "";
    try {
        budgetId = document.getElementById("budgetID").innerText.trim();
    } catch (e) {
        console.error("Could not find budgetID element");
        return;
    }

    // 2. Validation
    if (isNaN(remainingBalance) || remainingBalance <= 0) {
        alert("There is no remaining balance to save!");
        return;
    }

    // 3. Confirmation
    if (confirm(`Move the remaining $${remainingBalance.toFixed(2)} to Savings?`)) {
        // Prepare Data for Row Injection
        const name = "Budget Surplus";
        const details = "Automated surplus transfer";
        const amount = remainingBalance;
        const date = new Date().toISOString().split('T')[0];
        const type = "Savings";
        const status = "Planned";

        // 1. Find the specific 'Savings' option in your existing category dropdown
        const categorySelect = document.getElementById("color");
        let savingsOption = Array.from(categorySelect.options).find(opt =>
            opt.text.trim().toLowerCase() === 'savings'
        );

// 2. Fallback: If no "Savings" category exists, use the currently selected one
        if (!savingsOption) {
            console.warn("Savings category not found in dropdown, using current selection.");
            savingsOption = categorySelect.options[categorySelect.selectedIndex];
        }

// 3. Extract the 3 parts needed for the dynamic row and the Servlet
        const categoryId   = savingsOption.value;
        const categoryName = savingsOption.text.trim();
        const colorHex     = savingsOption.getAttribute("data-color") || "#808080"; // Default gray if missing

        // Create Temporary ID for dynamic row
        const tempId = "temp_" + Date.now();

        // 4. Construct and Inject the Row immediately (Optimistic UI)
        const rowHTML = `
        <tr data-id="${tempId}">
            <td  data-field="name">${name}</td>
            <td  data-field="details">${details}</td>
            <td class="editable" data-field="amount">${amount.toFixed(2)}</td>
            <td class="editable" data-field="date">${date}</td>
            <td  data-field="type">${type}</td>
            <td  data-field="status">${status}</td>
            <td class="editable-select" data-field="color" data-hex="${colorHex}" data-categoryid="${categoryId}">
                <span style="color:${colorHex}; font-size: 1.5rem; line-height: 1; vertical-align: middle;">●</span>
                <span class="color-label">${categoryName}</span>
            </td>
            <td>
                <button class="btn btn-sm btn-outline-danger" type="button" 
                        onclick="deleteLineItem(this, '${tempId}')">Delete</button>
            </td>
        </tr>`;

        $("#inputRow").before(rowHTML);
        recalcTotal(); // Updates Summary card and Chart immediately

        // 5. Prepare Payload for Servlet
        const params = new URLSearchParams();
        params.append("inputbudget_line_itembudget_id", budgetId);
        params.append("inputbudget_line_itemname", name);
        params.append("inputbudget_line_itemdetails", details);
        params.append("inputbudget_line_itemamount", amount.toFixed(2));
        params.append("inputbudget_line_itemline_item_date", date);
        params.append("inputbudget_line_itembudget_line_type_id", type);
        params.append("inputbudget_line_itembudget_line_status_id", status);
        params.append("inputcategory_id", categoryId);

        // 6. Async Fetch to Database
        fetch('addBudget_line_item', {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: params
        })
            .then(response => response.text())
            .then(newUuid => {
                // Check for servlet error codes (matching your handleError logic)
                if (parseInt(newUuid) <= 0) {
                    handleError(parseInt(newUuid));
                    $(`tr[data-id="${tempId}"]`).remove();
                    recalcTotal();
                    return;
                }

                // Update the temporary row with the real UUID from the DB
                const $row = $(`tr[data-id="${tempId}"]`);
                $row.attr("data-id", newUuid);
                $row.find("button").attr("onclick", `deleteLineItem(this, '${newUuid}')`);

                // Visual feedback of successful save
                $row.css('background-color', '#d4edda');
                setTimeout(() => $row.css('background-color', ''), 800);
            })
            .catch(err => {
                console.error('Fetch error:', err);
                $(`tr[data-id="${tempId}"]`).remove();
                recalcTotal();
                alert("Network error: Item was not saved.");
            });
    }
};