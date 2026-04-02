$(document).ready(function () {
	// Peace treaty between libraries
	if ($.widget && $.widget.bridge) {
		$.widget.bridge('uibutton', $.ui.button);
	}

	// Map of ID to Label and a Bootstrap Icon class
	const navConfig = {
		"#Add": {label: "Upload", icon: "bi-cloud-arrow-up"},
		"#search": {label: "Search", icon: "bi-search"},
		"#View": {label: "Activity", icon: "bi-list-ul"},
		"#Planned": {label: "Activity", icon: "bi-list-ul"},
		"#Accounts": {label: "Accounts", icon: "bi-bank"},
		"#Category": {label: "Categories", icon: "bi-tags"},
		"#PieChart": {label: "Stats", icon: "bi-pie-chart"},
		"#budgets": {label: "Budgets", icon: "bi-wallet2"},
		"#suggestions": {label: "Feedback", icon: "bi-chat-dots"},
		"#Export": {label: "Export", icon: "bi-download"}
	};

	$.each(navConfig, function (id, data) {

		const $btn = $(id);
		//$btn.removeClass("ui-state-default ui-widget-content");
		if ($btn.length) {
			// Initialize jQuery UI Button
			$btn.button({label: data.label});

			// Prepend a nice Bootstrap Icon since they look better than UI icons
			$btn.prepend(`<i class="bi ${data.icon} me-2"></i>`);
		}
		$btn.css({
			'background': '#ffffff',
			'box-shadow': '0 4px 12px rgba(0,0,0,0.15)',
			'border-radius': '12px',
			'border': '1px solid #dee2e6',
			'padding': '10px 20px',
			'display': 'inline-flex'
		});
	});
});




function hiderow(row){
	//console.log(row);
	var rowtohide = document.getElementById(row);
	//rowtohide.setAttribute('display','none')
	$(rowtohide).slideUp();

}

function hidecol(col_no) {
	//console.log(col_no);
	var tbl = document.getElementById('moneyTable');
	var rows = tbl.getElementsByTagName('tr');

	for (var row = 0; row < rows.length; row++) {
		var cols = rows[row].children;
		var coll = cols[col_no];
		$(coll).slideUp();


	}
}
	function restorecells() {
		var tbl = document.getElementById('moneyTable');
		var rows = tbl.getElementsByTagName('tr');
		for (var row = 0; row < rows.length; row++) {
			$(rows[row]).slideDown();
			var cols = rows[row].children;
			for (var i =0; i<cols.length; i++){
				var cell = cols[i];
				$(cell).slideDown();
			}
		}


}



