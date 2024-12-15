$(document).ready(function() {
    $(".explanationContainer").hide();
    $("#techskilldescription").slideUp();
    $("#designskilldescription").slideUp();


    $("#AWS").attr('title', 'This application runs on AWS services (ec2, rds, s3, ses)');
    $("#Azure").attr('title', 'This application ran on MS Azure services (hosting, database)');
    $("#Joomla").attr('title', 'Created using Joomla and Wordpress content management.');
    $("#cSharp").attr('title', 'Created using C Sharp programming language');
    $("#jakarta").attr('title', 'Uses Jakarta EE technology');
    $("#Servlets").attr('title', 'Uses servlets to run backend logic.');
    $("#JSP").attr('title', 'Uses Java Server Pages to display dynamic content');
    $("#razor").attr('title', 'Uses Razor Server Pages to display dynamic content');
    $("#Framework").attr('title', 'Created using JavaScript frameworks, such as Vue or jQuery');
    $("#Html").attr('title', 'Created using HTML for static content');
    $("#CSS").attr('title', 'Uses Cascading Style Sheets to style and layout webpage/ ');
    $("#JavaScript").attr('title', 'Uses JavaScript to create an interesting user experience');
    $("#Api").attr('title', 'Uses an API to retrieve data');
    $("#Multi").attr('title', 'Application is a multiuser/multi role application');
    $("#MySql").attr('title', 'Uses MySQL to store data');
    $("#T-SQL").attr('title', 'Uses T-SQL to store data');
    $("#Unity").attr('title', 'Made using Unity');
    $("#Git").attr('title', 'Uses Git/Github for source control');
    $("#UnitTests").attr('title', 'Created using test driven development');
    $("#Tech").attr('title', 'This project has extensive technical documentation');
    $("#Agile").attr('title', 'Created as a group project using the Agile software dev methodologies');
    $("#Solid").attr('title', 'utilizes the 5 SOLID principles of object oriented programming');
    $("#Interfaces").attr('title', 'Classes are coded to interface');
    $("#DataStruct").attr('title', 'Shows knowledge of various data structures');

    $(".skill").hover(function () {
        $("#techskilldescription").finish();
        $("#designskilldescription").finish();
        let parent = $(this).parent().parent()[0].id;
        if (parent === "technical") {
            $("#techskilldescription").slideDown();
            $("#techskilldescription").text(this.title);
        } else {
            $("#designskilldescription").slideDown();
            $("#designskilldescription").text(this.title);
        }
    }, function () {
        $("#techskilldescription").slideUp();
        $("#designskilldescription").slideUp();

    });


    $(".questionMark").hover(function () {
        $('.explanationContainer').finish();
        var self = $(this);
        var kiddos = self.siblings();

        for (var i = 0; i < kiddos.length; i++) {

            var element = kiddos[i];
            if ($(element).hasClass('explanationContainer')) {
                $(kiddos[i]).show('slow');
            }
        }
    }, function () {
        var self = $(this);
        var kiddos = self.siblings();
        for (var i = 0; i < kiddos.length; i++) {

            var element = kiddos[i];
            if ($(element).hasClass('explanationContainer')) {
                $(kiddos[i]).hide('slow');
            }
        }

    });

    //select derby
    $("#derby").hover(function () {
        $('.skill').finish();
        addskill("Joomla");


    }, function () {
        clearskills();
    });
    //end select derby_back

    //select derby
    $("#derby_back").hover(function () {
        $('.skill').finish();
        addskill("AWS");
        addskill("jakarta");
        addskill("Servlets");
        addskill("Servlets");
        addskill("Framework");
        addskill("Html");
        addskill("CSS");
        addskill("JavaScript");
        addskill("Api");
        addskill("Multi");
        addskill("MySql");
        addskill("Git");
        addskill("Tech");
        addskill("Solid");
        addskill("Interfaces");
        


    }, function () {
        clearskills();
    });
    //end select derby_back

    //select capstone
    $("#capstone").hover(function () {
        $('.skill').finish();

        addskill("cSharp");
        addskill("Html");
        addskill("CSS");
        addskill("Multi");
        addskill("T-SQL");
        addskill("Git");
        addskill("UnitTests");
        addskill("Tech");
        addskill("Agile");
        addskill("Solid");
        addskill("Interfaces");
        addskill("razor");


    }, function () {
        clearskills();
    });
    //end select capstone

    //select Java
    $("#java").hover(function () {
        $('.skill').finish();
        addskill("AWS");
        addskill("Azure");
        addskill("Servlets");
        addskill("JSP");
        addskill("Framework");
        addskill("Html");
        addskill("CSS");
        addskill("JavaScript");
        addskill("Api");
        addskill("Multi");
        addskill("Git");
        addskill("Solid");
        addskill("Jakarta");


    }, function () {
        clearskills();
    });
    //end select Java

    //select net
    $("#net").hover(function () {

        addskill("cSharp");
        addskill("Html");
        addskill("CSS");
        addskill("Multi");
        addskill("T-SQL");
        addskill("Git");
        addskill("UnitTests");

        addskill("Solid");
        addskill("Interfaces");
        addskill("razor");


    }, function () {
        clearskills();
    });
    //end select net

    //select client
    $("#client").hover(function () {
        addskill("Framework");
        addskill("Html");
        addskill("CSS");
        addskill("JavaScript");


    }, function () {
        clearskills();
    });
    //end select client

    //select honors
    $("#honors").hover(function () {
        addskill("cSharp");
        addskill("Servlets");
        addskill("JSP");
        addskill("Framework");
        addskill("Html");
        addskill("CSS");
        addskill("JavaScript");
        addskill("Api");

        addskill("MySql");
        addskill("T-SQL");

        addskill("Git");
        addskill("UnitTests");

        addskill("Interfaces");


    }, function () {
        clearskills();
    });
    //end select honors

    //select game
    $("#game").hover(function () {
        addskill("cSharp");
        addskill("Unity");

    }, function () {
        clearskills()
    });
    //end select game

    //select budget
    $("#budget").hover(function () {
        addskill("AWS");
        addskill("Azure");
        addskill("Servlets");
        addskill("JSP");
        addskill("Framework");
        addskill("Html");
        addskill("CSS");
        addskill("JavaScript");
        addskill("Api");
        addskill("MySql");
        addskill("Git");

    }, function () {
        clearskills()
    });
    //end select budget

    //select coursework
    $("#coursework").hover(function () {
        addskill("AWS");
        addskill("Azure");
        addskill("Servlets");
        addskill("JSP");
        addskill("Framework");
        addskill("Html");
        addskill("CSS");
        addskill("JavaScript");
        addskill("Api");
        addskill("Multi");
        addskill("MySql");
        addskill("Git");
        addskill("Tech");
        addskill("Agile");
        addskill("Solid");
        addskill("Jakarta");


    }, function () {
        clearskills()
    });
    //end select coursework

    //select pearson
    $("#pearson").hover(function () {


    }, function () {
        clearskills()
    });
    //end select pearson

    //select postfix
    $("#postfix").hover(function () {
        addskill("AWS");
        addskill("Azure");

        addskill("DataStruct");

    }, function () {
        clearskills()
    });
    //end postfix

    //select email
    $("#email").hover(function () {
        addskill("AWS");
        addskill("Azure");
        addskill("Servlets");
        addskill("JSP");
        addskill("Html");
        addskill("CSS");

    }, function () {
        clearskills()
    });
    //end select email

    //select apidemo
    $("#apidemo").hover(function () {

        addskill("Html");
        addskill("Api");

    }, function () {
        clearskills()
    });
    //end select apidemo

});

function addskill(skill) {
    var element = document.getElementById(skill);

    $(element).animate({
        backgroundColor: '#90EE90',
        color: '#000000'
    }, 'slow');

}

function clearskills() {
    $('.skill').finish();
    var skills = document.getElementsByClassName('skill');
    for (i = 0; i < skills.length; i++) {

        $(skills[i]).animate({
            backgroundColor: '#A3A3A3',
            color: '#FFFFFF'
        }, 'slow');

    }
}