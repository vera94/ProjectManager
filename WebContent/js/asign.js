function addAssignee() {
	var search= window.location.search; 
	var taskId = document.getElementById("taskID05").innerHTML;    
	 var email = $("#userEmail")[0].value;
	$.ajax({
	    url:"user/"+email+ "/addTask/"+taskId,
	    type: "POST"
	})
	.success(function(data) {
		alert("Task was assigned successfully!");
		//window.location.replace("Details.html"+'?'+ projectId);
	})
	.fail(function(data) {
		alert("Task was NOT assigned!");
	})
	
}