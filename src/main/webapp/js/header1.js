$(document).ready(function(){


	//create the tab 0 button
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


	$("#ManageDB").button();
	$( "#ManageDB" ).button( "option", "icon", "ui-icon-check" );
	$( "#ManageDB" ).button( "option", "showIcon", "true" );
	$( "#ManageDB" ).button( "option", "label", "ManageDB" );

	$("#user-dash").button();
	$( "#user-dash" ).button( "option", "icon", "ui-icon-check" );
	$( "#user-dash" ).button( "option", "showIcon", "true" );
	$( "#user-dash" ).button( "option", "label", "User Dashboard" );

	$("#team-admin-dash").button();
	$( "#team-admin-dash" ).button( "option", "icon", "ui-icon-check" );
	$( "#team-admin-dash" ).button( "option", "showIcon", "true" );
	$( "#team-admin-dash" ).button( "option", "label", "Team Admin Dashboard" );

	$("#league-admin-dash").button();
	$( "#league-admin-dash" ).button( "option", "icon", "ui-icon-check" );
	$( "#league-admin-dash" ).button( "option", "showIcon", "true" );
	$( "#league-admin-dash" ).button( "option", "label", "League Admin Dashboard" );
})