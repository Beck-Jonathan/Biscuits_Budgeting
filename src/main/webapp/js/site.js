$(document).ready(function(){

	var activeTab=0;
	let date  = new Date();


	
	//hide this unless they select yes
	$(".derby_name").hide();
	
	//$("input[type='text']").value="";
	let submitbutton = document.getElementById("tab0submit");
	

	var elements = document.getElementsByTagName("input");
	//grab all text elements and clear them
for (var i=0; i < elements.length; i++) {
  if (elements[i].type == "text") {
   // elements[i].value = "";
  }
}


	//hde comment about being 18+
	$("#r3c2").hide();
	var pronounSelect = document.getElementById("Pronouns");
			$("#Pronouns").addClass("errorInput");
			



			//fill combo box
			for (let i = 0; i < pronouns.length; i++) {
				const element = pronouns[i];
				
				var option = document.createElement("option");
				option.text = element.name;
				option.value = element.code;
	
				pronounSelect.appendChild(option);
	
			}
			//when one selected, remove error input, add good input
			$("#Pronouns").click(function(){
				var selectedPronouns = $(this).val();
				
				if (selectedPronouns!="NA"){
				$(this).addClass("goodInput");
				$(this).removeClass("errorInput");
				}
				else {
					$(this).removeClass("goodInput");
				$(this).addClass("errorInput");
	
				}
	
			})
	var DerbyNameYes = document.getElementById('derbynameyes');
	var DerbyNameNo = document.getElementById('derbynameno');
	DerbyNameNo.checked=true;
	var eighteen = document.getElementById("18+").classList.add("goodInput");
	//show derby name if they click yes
	$(document.getElementsByName("derbyName")).click(function(){
		var derbyName= (document.querySelector('input[name="derbyName"]:checked').value);
		if (derbyName=="yes"){$(".derby_name").show("slide", { direction: "left" }, 100);
	$("#derbynameinput").addClass("errorInput");}
		else {$(".derby_name").hide("slide", { direction: "left" }, 100);
		$("#derbynameinput").removeClass("errorInput");}
	})

	var HelmYes = document.getElementById('Helmyes');
	var HelmNo = document.getElementById('Helmno');
	//show associated drop down if they click that they need this gear
	HelmNo.checked=true;
	$(document.getElementsByName("Helm")).click(function(){
		var helm= (document.querySelector('input[name="Helm"]:checked').value);
		if (helm=="no"){
			$("#p3r1c4").show("slide", { direction: "left" }, 100);
		$("#p3r1c5").show();
		$("#HelmDropDown").addClass("errorInput");}
		else {
			$("#p3r1c4").hide("slide", { direction: "left" }, 100);
		$("#p3r1c5").hide("slide", { direction: "left" }, 100);
		$("#HelmDropDown").removeClass("errorInput");}
	})

	var WristYes = document.getElementById('Wristyes');
	var WristNo = document.getElementById('Wristno');
	WristNo.checked=true;
		//show associated drop down if they click that they need this gear
	$(document.getElementsByName("Wrist")).click(function(){
		var wrist= (document.querySelector('input[name="Wrist"]:checked').value);
		if (wrist=="no"){
			$("#p3r2c4").show("slide", { direction: "left" }, 100);
		$("#p3r2c5").show("slide", { direction: "left" }, 100);
		$("#WristDropDown").addClass("errorInput");}
		else {
			$("#p3r2c4").hide("slide", { direction: "left" }, 100);
		$("#p3r2c5").hide("slide", { direction: "left" }, 100);
		$("#WristDropDown").removeClass("errorInput");}
	})

	var ElbowYes = document.getElementById('Elbowyes');
	var ElbowNo = document.getElementById('Elbowno');
	ElbowNo.checked=true;
		//show associated drop down if they click that they need this gear
	$(document.getElementsByName("Elbow")).click(function(){
		var Elbow= (document.querySelector('input[name="Elbow"]:checked').value);
		if (Elbow=="no"){
			$("#p3r3c4").show("slide", { direction: "left" }, 100);
		$("#p3r3c5").show("slide", { direction: "left" }, 100);
		$("#ElbowDropDown").addClass("errorInput");}
		else {
			$("#p3r3c4").hide("slide", { direction: "left" }, 100);
		$("#p3r3c5").hide("slide", { direction: "left" }, 100);
		$("#ElbowDropDown").removeClass("errorInput");
	}
	})

	var KneeYes = document.getElementById('Kneeyes');
	var KneeNo = document.getElementById('Kneeno');
	KneeNo.checked=true;
		//show associated drop down if they click that they need this gear
	$(document.getElementsByName("Knee")).click(function(){
		var Knee= (document.querySelector('input[name="Knee"]:checked').value);
		if (Knee=="no"){
			$("#p3r4c4").show("slide", { direction: "left" }, 100);
		$("#p3r4c5").show("slide", { direction: "left" }, 100);
		$("#KneeDropDown").addClass("errorInput");
	
	}
		else {$("#p3r4c4").hide("slide", { direction: "left" }, 100);
		$("#p3r4c5").hide("slide", { direction: "left" }, 100);
		$("#KneeDropDown").removeClass("errorInput");}
	})

	var SkatesYes = document.getElementById('Skatesyes');
	var SkatesNo = document.getElementById('Skatesno');
	SkatesNo.checked=true;
		//show associated drop down if they click that they need this gear
	$(document.getElementsByName("Skates")).click(function(){
		var Skates= (document.querySelector('input[name="Skates"]:checked').value);
		if (Skates=="no"){
			$("#p3r5c4").show("slide", { direction: "left" }, 100);
			$("#p3r5c5").show("slide", { direction: "left" }, 100);
			$("#SkateDropDown").addClass("errorInput");
		
		}
		else {
			$("#p3r5c4").hide("slide", { direction: "left" }, 100);
			$("#p3r5c5").hide("slide", { direction: "left" }, 100);
			$("#SkateDropDown").removeClass("errorInput");
		
		}
	})
	//show associated text box if they have insuarnce
	var InsuranceYes = document.getElementById('InsuranceYes');
	var InsuranceNo = document.getElementById('InsuranceNo');
	InsuranceNo.checked=true;
	$("#WFTDANumber").removeClass("errorInput3");
	$(document.getElementsByName("Insurance")).click(function(){
		var Insurance= (document.querySelector('input[name="Insurance"]:checked').value);
		if (Insurance=="yes"){
			$("#WFTDArow").show("slide", { direction: "left" }, 100);
		$("#WFTDANumber").addClass("errorInput");
	}
		else {
			$("#WFTDArow").hide("slide", { direction: "left" }, 100);
		$("#WFTDANumber").removeClass("errorInput");
	}
	})



	
	
	

	$("input[type='text']").blur(function(){
		
				//when leaving text, check length. If too short, classify it as eerror
				//otherwise color it as a good text
		let text=$(this).val();
		
		if(text.length <3){
			$(this).removeClass('goodInput');
			$(this).addClass('errorInput');
			$('.errorInput>p').addClass('errorText');
			

		}
		else{$(this).removeClass('errorInput');
		$(this).addClass('goodInput');
		

		$('.goodInput > p').addClass('GoodText');}
		
		
		
	}
)


$("input[type='checkbox']").click(function(){
	//all check boxes need to be checked
		
	var decision = document.getElementById("18+").checked;
	
	
	
	if(!decision){
		
		$(this).removeClass('goodInput');
		$(this).addClass('errorInput');
		$('.errorInput>p').addClass('errorText');
		$("#r3c2").show("slide", { direction: "left" }, 100);
		

	}
	else{$(this).removeClass('errorInput');
	
	$(this).addClass('goodInput');
	$("#r3c2").hide("slide", { direction: "left" }, 100);
	

	$('.goodInput > p').addClass('GoodText');}
	
	
	
}
)
function ValidateEmail(input) {
	//regex for email

	var validRegex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
  
	if (input.match(validRegex)) {
  
	    
	  return true;
  
	} else {
  
	  
  
	  return false;
  
	}
  
  }





		$("#email").blur(function() {
			let email = $(this).val();
			var result = ValidateEmail(email);
			//on email leave, validate
			
					

			if (result){
				$(this).addClass('goodInput');
				$(this).removeClass('errorInput');
	
			}
			else {$(this).addClass('errorInput');
					$(this).removeClass('goodInput');}
	
			
		})
		
		




	
	$("#confirm").dialog({
		title: "Become A Skater?",
		autoOpen: false,
		buttons: [
			{
			  text: "I agree",
			  icon: "ui-icon-check",
			  click: function() {
				$( this ).dialog( "close" );
				window.open("https://play.unity.com/mg/other/build1-sg"); 
			  }
			},
			{
			  text: "Cancel",
			  icon: "ui-icon-cancel",
			  click: function() {
				$(this).dialog( "close" );
			  }
			}
		]
	});
	
	//jquery validation rules for tabs
	$("#testForm0").validate({
        rules: {
            FirstName: "required",
            LastName: "required",
            email: "required",
            
            

        }, //end rules
        messages: {
            FirstName: "Required\n",
			LastName: "Required\n",
			email:"Required\n",
        }, 
    });
	
	$("#testForm2").validate();
	$("#testForm3").validate();
	$("#testForm4").validate({
		rules:{
		SignName:"required"
		},
		messages: {
			SignName:"Required"
		}

});
	
	$("#tabs").tabs({
		
		disabled: [ 1, 2, 3, 4 ],
		active: 0,
		//heightStyle: "fill"
		
	});
	
	//create the tab 0 button
	$("#tab0submit").button();
	$( "#tab0submit" ).button( "option", "icon", "ui-icon-check" );
	$( "#tab0submit" ).button( "option", "showIcon", "true" );
	$( "#tab0submit" ).button( "option", "label", "&nbsp&nbsp&nbsp&nbsp All Fields Required" );
	 	

$( "#tab0submit" ).button({
	margin:"0px",
	padding:"0px",
	iconPosition: "start",
	textPosition: "end"
	
  });
	//$( "#tab0submit" ).button( "option", "showLabel", true );



	//check for errors, then move to tab 1
	$("#tab0submit").click(function(){
		
		var ErrorClasses = document.getElementsByClassName("errorInput");
		errors=ErrorClasses.length;
		if (!$("#testForm0").valid()){errors=1;}
		
		if( errors<1 ){
			activeTab=1;
			$("#tabs").tabs({
				disabled: [ 0, 2, 3, 4 ],
				active: 1
			});
			var radios = document.getElementsByName("Experiance");

		
		
		//create the buttons
		$("#AddTeam").button();
		$( "#AddTeam" ).button( "option", "icon", "ui-icon-check" );
	$( "#AddTeam" ).button( "option", "showIcon", "true" );
	$( "#AddTeam" ).button( "option", "label", "&nbsp&nbsp&nbsp&nbsp Add Team" );
		$("#RemoveTeam").button();
		$( "#RemoveTeam" ).button( "option", "icon", "ui-icon-check" );
	$( "#RemoveTeam" ).button( "option", "showIcon", "true" );
	$( "#RemoveTeam" ).button( "option", "label", "&nbsp&nbsp&nbsp&nbsp Remove Team" );
		$('#date0').datepicker({
			onSelect:function(){$(this).addClass("goodInput");
			$(this).removeClass("errorInput");
			date = ($('#date0').datepicker("getDate"));
			
			$('#date1').datepicker("option","minDate",date);

		},
			changeMonth: true,
			changeYear: true,
			yearRange : "-120:+0",
			maxDate:0,
			dateFormat : 'm-dd-yy'
			
			
			
		});
		//create the datepicker
		$('#date1').datepicker({
			onSelect:function(){$(this).addClass("goodInput");
			date = ($('#date1').datepicker("getDate"));
			
			$('#date0').datepicker("option","maxDate",date);
			$(this).removeClass("errorInput")},
			changeMonth: true,
			changeYear: true,
			yearRange : "-120:+0",
			maxDate:0,
			minDate:date,
			
			dateFormat : 'm-dd-yy'
			
			
			
		});
		//when changing date 0, make that be the min date for date1
		$("#date0").change(function(){
			
			$('#date1').datepicker({
				minDate:$('#date0').datepicker("getDate"),

			}
			)


		})
		
		
		var InsuranceNo = document.getElementById('InsuranceNo');
		InsuranceNo.checked=true;
		//hide all fields to start

		$("#WFTDArow").hide();
		$("#fieldset").hide();
		$("#AddTeam").hide();
		$("#RemoveTeam").hide();
		var team_cnt=0;
		var ExperianceYes = document.getElementById('ExperianceYes');
		var ExperianceNo = document.getElementById('ExperianceNo');
		ExperianceNo.checked=true;
		var valuesset=0;
		$(document.getElementsByName("Experiance")).click(function(){
			//when dropping down the experiance box, add errors to all fields
			
			var Experiance= (document.querySelector('input[name="Experiance"]:checked').value);
			if (Experiance=="yes"){
				$("#fieldset").slideDown();
				$("#PreviousLeague1").addClass("errorInput");
				$("#PreviousTeam1").addClass("errorInput");
				$("#city").addClass("errorInput");
				$("#state").addClass("errorInput");
				$("#Position").addClass("errorInput");
				$("#JerseyNumber").addClass("errorInput");
				$("#date0").addClass("errorInput");
				$("#date1").addClass("errorInput");
			team_cnt=1;
			var stateSelect = document.getElementById("state");
			$("#state").addClass("errorInput");
			$("#PreviousLeague1").addClass("errorInput");
			//clone the first fieldset



				if (valuesset==0){
			for (let i = 0; i < states.length; i++) {
				const element = states[i];
				
				var option = document.createElement("option");
				option.text = element.name;
				option.value = element.code;
	
				stateSelect.appendChild(option);
	
			}

			var positionSelect = document.getElementById("Position");
			$("#Position").addClass("errorInput");
			for (let i = 0; i < position.length; i++) {
				const element1 = position[i];
				
				
				var option1 = document.createElement("option");
				option1.text = element1.name;
				option1.value = element1.code;
	
				positionSelect.appendChild(option1);
	
			}

			//add numbers to the number drop down
			
			var numberSelect = document.getElementById("JerseyNumber");

			var naOption=  document.createElement("Option");
			naOption.text="NA";
			naOption.value="NA";
			numberSelect.appendChild(naOption);
			var noOption=  document.createElement("Option");
			noOption.text="00";
			noOption.value="00";
			numberSelect.appendChild(noOption);

			for (let i=0;i<100;i++){
				var option2 = document.createElement("option");
				option2.text=i;
				option2.value=i;
				numberSelect.appendChild(option2);

			}
			//add leagues to the league drop down
			var leagueSelect = document.getElementById("PreviousLeague1");
			for (let i = 0; i < leagues.length; i++) {
				const element1 = leagues[i];
				
				
				var option1 = document.createElement("option");
				option1.text = element1.name;
				option1.value = element1.code;
	
				leagueSelect.appendChild(option1);
	
			}
		valuesset=1;  //set this to 1 becuase we only do this ont he first time this is dropped down
		}
		//add positions to the position drop down
			$("#Position").click(function(){
				var selectedPosition = $(this).val();
				
				if (selectedPosition!="NA"){
				$(this).addClass("goodInput");
				$(this).removeClass("errorInput");
				}
				else {
					$(this).removeClass("goodInput");
				$(this).addClass("errorInput");
	
				}
	
			})

			

			
			



			$("#JerseyNumber").addClass("errorInput");
			
			
			
			$("#fieldset input").each(function(){$(this).addClass('errorInput');})
			let fieldset_parent = $(".teamFieldSet:eq(0)").clone(true);
			$("#AddTeam").slideDown();
			var newTexts2=Array.from(document.getElementsByClassName("errorInput1"));
			
			for (i=0;i<newTexts2.length;i++){
		newTexts2[i].classList.add("errorInput");
		newTexts2[i].classList.remove("errorInput1");
		}
		}else {$("#fieldset").hide();
		//when hiding the new team inflow, clear off the errors
		$("#PreviousLeague1").removeClass("errorInput");
		$("#PreviousTeam1").removeClass("errorInput");
		$("#city").removeClass("errorInput");
		$("#state").removeClass("errorInput");
		$("#Position").removeClass("errorInput");
		$("#JerseyNumber").removeClass("errorInput");
		$("#date0").removeClass("errorInput");
		$("#date1").removeClass("errorInput");
			$("#AddTeam").hide();
			$("#fieldset input").each(function(){$(this).removeClass('errorInput');})
			$("#state").removeClass("errorInput");
			$("#PreviousLeague1").removeClass("errorInput");
		
		}
		})
		//validate that they picked a state, not the placeholder
		$("#state").click(function(){
			var selectedState = $(this).val();
			
			if (selectedState!="NA"){
			$(this).addClass("goodInput");
			$(this).removeClass("errorInput");
			}
			else {
				$(this).removeClass("goodInput");
			$(this).addClass("errorInput");

			}

		})
//validate that they picked a number, not the placeholder
		$("#JerseyNumber").click(function(){
			var selectednumber = $(this).val();
			
			if (selectednumber!="NA"){
			$(this).addClass("goodInput");
			$(this).removeClass("errorInput");
			}
			else {
				$(this).removeClass("goodInput");
			$(this).addClass("errorInput");

			}
		})
			//validate that they picked a League, not the placeholder
		$("#PreviousLeague1").click(function(){
			var selectedLeague = $(this).val();
			
			if (selectedLeague!="NA"){
			$(this).addClass("goodInput");
			$(this).removeClass("errorInput");
			}
			else {
				$(this).removeClass("goodInput");
			$(this).addClass("errorInput");

			}

		})
	
		var i_date = 0;
		$("#AddTeam").click(function () {
			//clone the fieldset
			for (var i=0, iLen=radios.length; i<iLen; i++) {
				radios[i].disabled = true;
		  	} 
			team_cnt++;
			$("ExperianceNo").enabled=false;
			
			$('input.date').datepicker("destroy");
			
			
			var fieldset_parent = $(".teamFieldSet:eq(0)").clone(true);
			
			
			$(".teamFieldSet:last").after($(fieldset_parent).clone(true));
			$(".teamFieldSet:last").find("input[type=text]").val('');
			$(".teamFieldSet:last").find("input[type=text]").addClass("errorInput");
			$(".teamFieldSet:last").find("input[type=text]").removeClass("goodInput");
			$(".teamFieldSet:last").find("input").val('');
			$(".teamFieldSet:last").find(".goodInput").addClass("errorInput");
			$(".teamFieldSet:last").find(".goodInput").removeClass("goodInput");

			var newTexts1=Array.from(document.getElementsByClassName("errorInput1"));
			
			for (i=0;i<newTexts1.length;i++){
		newTexts1[i].classList.add("errorInput");
		newTexts1[i].classList.remove("errorInput1");
		}
			$("#RemoveTeam").show("slide", { direction: "left" }, 100);
			
			$('.date').each(function () {
						
				
				$(this).attr("id", 'date' + i_date).datepicker({changeMonth: true,
					changeYear: true,
					yearRange : "-120:+0",
					maxDate: 0,
					dateFormat : 'm-dd-yy'});
				i_date++;
			});

		
			
		});
		$("#RemoveTeam").click(function () {
			
			team_cnt--;
			
		$(".teamFieldSet:last").remove();
		
			
			if (team_cnt==1){
				$("#RemoveTeam").hide();
				for (var i=0, iLen=radios.length; i<iLen; i++) {
					radios[i].disabled = false;
			  } 
			}
			
		});
				
	}
	});

	var datepickers = document.getElementsByClassName("date");
	
	for (i=0;i<datepickers.length;i++){
		
		var datepicker = datepickers[i];
		//datepicker.onclick=setDates();
		
		
}


	//create the button
	$("#tab1submit").button();
	$( "#tab1submit" ).button( "option", "icon", "ui-icon-check" );
	$( "#tab1submit" ).button( "option", "showIcon", "true" );
	$( "#tab1submit" ).button( "option", "label", "&nbsp&nbsp&nbsp&nbsp All Fields Required" );

	$("#tab1submit").click(function(){
		//if no errors, load tab2
		
		var ErrorClasses = document.getElementsByClassName("errorInput");
		errors=ErrorClasses.length;
		
		if( errors<1 ){
			activeTab=2;
			$("#tabs").tabs({
				disabled: [ 0, 1, 3, 4 ],
				active: 2
			});
				//create the drop down filled with sizes. also, the click function verifies they picked a size, not
				//a placeholder
			var helmSelect = document.getElementById("HelmDropDown");
			$("#HelmDropDown").addClass("errorInput");
			for (let i = 0; i < GearSize.length; i++) {
				const element1 = GearSize[i];
				
				
				var option1 = document.createElement("option");
				option1.text = element1.name;
				option1.value = element1.code;
	
				helmSelect.appendChild(option1);
	
			}
			$("#HelmDropDown").click(function(){
				var selectedSize = $(this).val();
				
				if (selectedSize!="NA"){
				$(this).addClass("goodInput");
				$(this).removeClass("errorInput");
				}
				else {
					$(this).removeClass("goodInput");
				$(this).addClass("errorInput");
	
				}
	
			})
				//create the drop down filled with sizes. also, the click function verifies they picked a size, not
				//a placeholder
			var wristSelect = document.getElementById("WristDropDown");
			$("#WristDropDown").addClass("errorInput");
			for (let i = 0; i < GearSize.length; i++) {
				const element1 = GearSize[i];
				
				
				var option1 = document.createElement("option");
				option1.text = element1.name;
				option1.value = element1.code;
	
				wristSelect.appendChild(option1);
	
			}
			$("#WristDropDown").click(function(){
				var selectedSize = $(this).val();
				
				if (selectedSize!="NA"){
				$(this).addClass("goodInput");
				$(this).removeClass("errorInput");
				}
				else {
					$(this).removeClass("goodInput");
				$(this).addClass("errorInput");
	
				}
	
			})
				//create the drop down filled with sizes. also, the click function verifies they picked a size, not
				//a placeholder
			var elbowSelect = document.getElementById("ElbowDropDown");
			$("#ElbowDropDown").addClass("errorInput");
			for (let i = 0; i < GearSize.length; i++) {
				const element1 = GearSize[i];
				
				
				var option1 = document.createElement("option");
				option1.text = element1.name;
				option1.value = element1.code;
	
				elbowSelect.appendChild(option1);
	
			}
			$("#ElbowDropDown").click(function(){
				var selectedSize = $(this).val();
				
				if (selectedSize!="NA"){
				$(this).addClass("goodInput");
				$(this).removeClass("errorInput");
				}
				else {
					$(this).removeClass("goodInput");
				$(this).addClass("errorInput");
	
				}
	
			})

			//create the drop down filled with sizes. also, the click function verifies they picked a size, not
				//a placeholder
			var KneeSelect = document.getElementById("KneeDropDown");
			$("#KneeDropDown").addClass("errorInput");
			for (let i = 0; i < GearSize.length; i++) {
				const element1 = GearSize[i];
				
				
				var option1 = document.createElement("option");
				option1.text = element1.name;
				option1.value = element1.code;
	
				KneeSelect.appendChild(option1);
	
			}
			$("#KneeDropDown").click(function(){
				var selectedSize = $(this).val();
				
				if (selectedSize!="NA"){
				$(this).addClass("goodInput");
				$(this).removeClass("errorInput");
				}
				else {
					$(this).removeClass("goodInput");
				$(this).addClass("errorInput");
	
				}
	
			})
			//create the drop down filled with sizes. also, the click function verifies they picked a size, not
				//a placeholder
			var SkateSelect = document.getElementById("SkateDropDown");
			
			var naoption = document.createElement("option");
			naoption.text="<Select A size>";
			naoption.value="NA";
			SkateSelect.appendChild(naoption);
		    for (let i=6;i<16;i++){
			var option2 = document.createElement("option");
				option2.text="Size "+i+" (Women's)";
				option2.value=i+"W";
				SkateSelect.appendChild(option2);
				var option3 = document.createElement("option");
				option3.text="Size "+i+ " (Men's)";
				option3.value=i+"M";
				SkateSelect.appendChild(option3);


			}
			$("#SkateDropDown").addClass("errorInput");

			$("#SkateDropDown").click(function(){
				var selectedSize = $(this).val();
				
				if (selectedSize!="NA"){
				$(this).addClass("goodInput");
				$(this).removeClass("errorInput");
				}
				else {
					$(this).removeClass("goodInput");
				$(this).addClass("errorInput");
	
				}
	
			})

			
			
			
		
		
		
		}
		
	});	
	//enable only sunday and wednesday on the datepicker
	function enableSUNandWED(date) {
		var day = date.getDay();
		return [(day == 0||day==3), ''];
	}
	//create the button
	$("#tab2submit").button();
	$( "#tab2submit" ).button( "option", "icon", "ui-icon-check" );
	$( "#tab2submit" ).button( "option", "showIcon", "true" );
	$( "#tab2submit" ).button( "option", "label", "&nbsp&nbsp&nbsp&nbsp All Fields Required" );
	$("#tab2submit").click(function(){
		//if no errors, load tab 3
		
		var ErrorClasses = document.getElementsByClassName("errorInput");
		errors=ErrorClasses.length;
		
		if( errors<1 ){
			activeTab=3;
			$("#tabs").tabs({
				disabled: [ 0, 1, 2, 4 ],
				active: 3
			});
			//create the datepicker
			$('#PracticeDate').datepicker({

				
				
				minDate: 0,
				changeMonth: true,
				changeYear: true,
				yearRange : "0:+1",
				numberOfMonths : 3,
				dateFormat : 'm-dd-yy',
				
				beforeShowDay: enableSUNandWED,
				defaultDate: '11-26-23' ,
				onSelect:  function(){
					$(this).addClass("goodInput");
				$(this).removeClass("errorInput");
				$(this).removeClass("errorInput2");
					
					var aDate = $(this).datepicker('getDate');
					if (aDate!==null){
					var dayNumber = aDate.getDay();
					
					
					//if sunday, show the vinton info
					if (dayNumber==0){
						$("#Vinton").slideDown();
				$("#GameOn").slideUp();	
				setTimeout(reload, 500, 'VintonMap');
				$( "#tab3submit" ).button( "option", "label", "&nbsp&nbsp&nbsp&nbsp Join us in Vinton!" );
	
					}
					//if wednesday, show the game on info
					if (dayNumber==3){
						$("#Vinton").slideUp();
				$("#GameOn").slideDown();	
				setTimeout(reload, 500, 'GameOnMap');
				$( "#tab3submit" ).button( "option", "label", "&nbsp&nbsp&nbsp&nbsp Join us in Cedar Rapids!" );
	
					}
					}
	
	
				}
				
				
				
			});
			$("#Vinton").hide();
			$("#GameOn").hide();	
		}
		
	});	
	//create the button
	$("#tab3submit").button();
	$( "#tab3submit" ).button( "option", "icon", "ui-icon-check" );
	$( "#tab3submit" ).button( "option", "showIcon", "true" );
	$( "#tab3submit" ).button( "option", "label", "&nbsp&nbsp&nbsp&nbsp All Fields Required" );
	$("#tab3submit").click(function(){
		//if no errors, load tab 4
		
		var ErrorClasses = document.getElementsByClassName("errorInput");
		errors=ErrorClasses.length;
		
		if( errors<1 ){
			activeTab=4;
			$("#tabs").tabs({
				disabled: [ 0, 1, 2, 3 ],
				active: 4
			});
			$("#p4name").addClass("errorInput");
		}
		else{ErrorClasses.forEach.classList.add("errorBorder");}
		
	});	

	// create the button
	$("#tab4submit").button();
	$( "#tab4submit" ).button( "option", "icon", "ui-icon-check" );
	$( "#tab4submit" ).button( "option", "showIcon", "true" );
	$( "#tab4submit" ).button( "option", "label", "&nbsp&nbsp&nbsp&nbsp All Fields Required" );
	$("#tab4submit").click(function(){
		//if no errors, show the final comfirm dialog
		var ErrorClasses = document.getElementsByClassName("errorInput");
		errors=ErrorClasses.length;
		if (!$("#testForm4").valid()){errors=1;}
		
		if( errors<1 ){
			
			
			// bring up a jqueryui confirm dialog
			$("#confirm").dialog( "open");
		}
		
	});
	function reload(id) 
{	
	//reload the map view 
    var buggyid = document.getElementById(id);
    buggyid.src = buggyid.src;
}
	function TestForZeroErrors(){var currentErrors = document.getElementsByClassName("errorInput");
LiveErrors=currentErrors.length;
if( LiveErrors<1 ){
	//have the submit button say "submit" if there are no errors
switch (activeTab){
	case 0: $( "#tab0submit" ).button( "option", "label", "&nbsp&nbsp&nbsp&nbsp Submit" ); break;
	case 1: $( "#tab1submit" ).button( "option", "label", "&nbsp&nbsp&nbsp&nbsp Submit" ); break;
	case 2: $( "#tab2submit" ).button( "option", "label", "&nbsp&nbsp&nbsp&nbsp Submit" ); break;
	case 3: break;
	case 4: $( "#tab4submit" ).button( "option", "label", "&nbsp&nbsp&nbsp&nbsp Submit" ); break;
	default:break;
}
}
else {
switch (activeTab){
	case 0: $( "#tab0submit" ).button( "option", "label", "&nbsp&nbsp&nbsp&nbsp All Fields Required" ); break;
	case 1: $( "#tab1submit" ).button( "option", "label", "&nbsp&nbsp&nbsp&nbsp All Fields Required" ); break;
	case 2: $( "#tab2submit" ).button( "option", "label", "&nbsp&nbsp&nbsp&nbsp All Fields Required" ); break;
	case 3: $( "#tab2submit" ).button( "option", "label", "&nbsp&nbsp&nbsp&nbsp All Fields Required" ); break;
	case 4: $( "#tab4submit" ).button( "option", "label", "&nbsp&nbsp&nbsp&nbsp All Fields Required" ); break;
	default:break;
	}

}	

	
}

document.body.addEventListener('click', TestForZeroErrors, true); 
document.body.addEventListener('keyup', TestForZeroErrors, true); 
});