function addProject() {
	    var formUrl = $("#addProject").attr("action");
	    var topic = $("#topic")[0].value;
	    var description = $("#description")[0].value;
	    
	    var data = { project : {
		            topic : topic,
		            description : description
    			}
	    }
	    
		$.ajax({
		    url: 'user/vboi4eva/addProject',
		    type: "POST",
		    contentType: "application/json;charset=UTF-8",
		    data: JSON.stringify(data)
		})
		.success(function(data) {
		    $("#register_form").attr("action", "MyProjects.html");
		})
		.fail(function(data) {
		    $("#register_form").attr("action", "MyProjects.html");
		})
		.always(function() {
                    $("#addProject").submit();
        });
    }


function addTask() {
	var search= window.location.search; 
	var projectId = search.substring(1);
    var formUrl = $("#addTask").attr("action");
    var name = $("#name")[0].value;
    var description = $("#description")[0].value;
    
    var data = { task : {
	            name : name,
	            description : description
			}
    }
    
	$.ajax({
	    url: "project/"+ projectId +"/addTask",
	    type: "POST",
	    contentType: "application/json;charset=UTF-8",
	    data: JSON.stringify(data)
	})
	.success(function(data) {
		//window.location.replace("Details.html"+'?'+ projectId);
	})
	.fail(function(data) {
	    $("#register_form").attr("action", "MyProjects.html");
	})
	.always(function() {
                $("#addTask").submit();
    });
}
    function isPasswordValid(password) {
        var confirmPassword = $("#conf_pwd")[0].value;
        if (password == "" || confirmPassword == "") {
            alert("Please fill both fields for password and try again.");
            return false;
        }
        if (password != confirmPassword) {
            alert("Two passwords do not match. Please correct the values and try again.");
            return false;
        }
        return true;
    }