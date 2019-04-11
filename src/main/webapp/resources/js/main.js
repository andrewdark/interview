function searchFilteredInterviews() {

    var position = jQuery("#position").val();
    var status = jQuery("#status option:selected").val();
    var date = jQuery("#date").val();
    var firstName = jQuery("#firstName").val();
    var lastName = jQuery("#lastName").val();
    var email = jQuery("#email").val();

    if (position === '') {
        position = null;
    }
    if (status === '') {
        status = null;
    }
    if (date === '') {
        date = null;
    }


    if (firstName === '') {
        firstName = null;
    }
    if (lastName === '') {
        lastName = null;
    }
    if (email === '') {
        email = null;
    }

    var FilterInterviewDto = {
        id: null,
        position: position,
        status: status,
        date: date,
        firstName: firstName,
        lastName: lastName,
        email: email
    };

    $.ajax({
        type: "POST",
        url: "/interview/api/v1/filtered_interviews", //your valid url (/url)
        contentType: "application/json", //this is required for spring 3 - ajax to work (at least for me)
        data: JSON.stringify(FilterInterviewDto), //json object or array of json objects
        success: function (result) {
            var tmp = "";
            for (j = 0; j < result.length; j++) {
                var ddd = result[j].date;
                tmp = tmp + "<tr><td>" + result[j].firstName + "</td>" +
                    "<td>" + result[j].lastName + "</td>" +
                    "<td>" + ddd + "</td>" +
                    "<td>" + result[j].position + "</td>" +
                    "<td>" + result[j].status + "</td>" +
                    "<td><a href = '' >View</a>&nbsp;<a href=''>Conduct</a>&nbsp;<a href = '' >Edit</a></td></tr>";
            }

            $(".inner3").html("<table class='interview'>" +
                "<caption>Interviews</caption><tr>" +
                "<th class='col'>First Name</th>" +
                "<th class='col'>Last Name</th>" +
                "<th class='col'>Date</th>" +
                "<th class='col'>Position</th>" +
                "<th class='col'>Status</th>" +
                "<th class='col'>Actions</th></tr>" + tmp +
                "</table>");

            //do nothing
        },
        error: function (jqXHR, textStatus, errorThrown) {
            $(".information").append(Client);
            alert('Error: ' + jqXHR.status + ' ' + jqXHR.responseText);
        }
    });

}

function editInterview() {
    alert("Not support yet");
}

function nsy() {
    alert("Not support yet");
}