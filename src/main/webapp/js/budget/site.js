$(document).ready(function() {
	const category ="";

////console.log("ready on new version")
	$("#Home").button();
	$( "#Home" ).button( "option", "icon", "ui-icon-check" );
	$( "#Home" ).button( "option", "showIcon", "true" );
	$( "#Home" ).button( "option", "label", "Home" );

	$("#SignUp").button();
	$( "#SignUp" ).button( "option", "icon", "ui-icon-check" );
	$( "#SignUp" ).button( "option", "showIcon", "true" );
	$( "#SignUp" ).button( "option", "label", "SignUp" );

	$("#SignIn").button();
	$( "#SignIn" ).button( "option", "icon", "ui-icon-check" );
	$( "#SignIn" ).button( "option", "showIcon", "true" );
	$( "#SignIn" ).button( "option", "label", "SignIn" );

	$("#user-dash").button();
	$( "#user-dash" ).button( "option", "icon", "ui-icon-check" );
	$( "#user-dash" ).button( "option", "showIcon", "true" );
	$( "#user-dash" ).button( "option", "label", "User Dash" );


	$("#Add").button();
	$( "#Add" ).button( "option", "icon", "ui-icon-check" );
	$( "#Add" ).button( "option", "showIcon", "true" );
	$( "#Add" ).button( "option", "label", "upload" );

	$("#search").button();
	$( "#search" ).button( "option", "icon", "ui-icon-check" );
	$( "#search" ).button( "option", "showIcon", "true" );
	$( "#search" ).button( "option", "label", "Search And Categorize" );

	$("#Accounts").button();
	$( "#Accounts" ).button( "option", "icon", "ui-icon-check" );
	$( "#Accounts" ).button( "option", "showIcon", "true" );
	$( "#Accounts" ).button( "option", "label", "View Accounts" );

	$("#View").button();
	$( "#View" ).button( "option", "icon", "ui-icon-check" );
	$( "#View" ).button( "option", "showIcon", "true" );
	$( "#View" ).button( "option", "label", "View Transactions" );


	$("#Category").button();
	$( "#Category" ).button( "option", "icon", "ui-icon-check" );
	$( "#Category" ).button( "option", "showIcon", "true" );
	$( "#Category" ).button( "option", "label", "Categories" );


	$("#MoneyBreakdown").button();
	$( "#MoneyBreakdown" ).button( "option", "icon", "ui-icon-check" );
	$( "#MoneyBreakdown" ).button( "option", "showIcon", "true" );
	$( "#MoneyBreakdown" ).button( "option", "label", "Yearly Summary" );

	$("#Export").button();
	$( "#Export" ).button( "option", "icon", "ui-icon-check" );
	$( "#Export" ).button( "option", "showIcon", "true" );
	$( "#Export" ).button( "option", "label", "Export" );

	$("#Mortgage").button();
	$( "#Mortgage" ).button( "option", "icon", "ui-icon-check" );
	$( "#Mortgage" ).button( "option", "showIcon", "true" );
	$( "#Mortgage" ).button( "option", "label", "Mortgage" );

	$("#PieChart").button();
	$( "#PieChart" ).button( "option", "icon", "ui-icon-check" );
	$( "#PieChart" ).button( "option", "showIcon", "true" );
	$( "#PieChart" ).button( "option", "label", "Bar Chart" );

	$("#suggestions").button();
	$( "#suggestions" ).button( "option", "icon", "ui-icon-check" );
	$( "#suggestions" ).button( "option", "showIcon", "true" );
	$( "#suggestions" ).button( "option", "label", "Suggestion Box" );




})

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



