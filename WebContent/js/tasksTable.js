function filter() {
  // Declare variables 
  var input, filter, table, tr, td, i;
  input = document.getElementById("filterValue");
  filter = input.value.toUpperCase();
  table = document.getElementById("ProjectTasksTable");
  tr = table.getElementsByTagName("tr");

  // Loop through all table rows, and hide those who don't match the search query
  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[1];
    if (td) {
      if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    } 
  }
}

$(document).ready(function() {
	var search= window.location.search; 
	var projectId = search.substring(1);
	var table = $("#ProjectTasksTable");
    $.ajax({
    	 url : "http://localhost:8080/wwwHomework/user/vboi4eva/tasks",
         type : "GET",
         dataType : "json",
         success : function(data) {
             renderTable(data.task, table);
         }
    });
  
function renderTable(data, table) {
    
    var tableThings = data.length;
    for (var i = 0; i < tableThings; i++) {
        renderRow(data[i], table);
    }
}
function renderRow(rowData, table) {
    var row = $("<tr />");
    table.append(row);
    row.append($("<td>" + rowData.id + "</td>"));
    row.append($("<td>" + rowData.name + "</td>"));
    row.append($("<td>" + rowData.description + "</td>")); 
    row.append($("<td>" + rowData.state + "</td>"));
    //if (isUserAuthenticated && rowData.amount > 0) {
    var detailsTd = $("<td />");
    var link = $("<button>Set to completed</button>");
    detailsTd.append(link);
    row.append(detailsTd);
    link.click(function(){
    	setToCompleted(rowData.id);
    });
    var deleteTd = $("<td />");
    var link2 = $("<button>Delete</button>");
    deleteTd.append(link2);
    row.append(deleteTd);
    link2.click(function(){
    	deleteTask(rowData.id);
    });
    var assignTd = $("<td />");
    var link3 = $("<button>Assign</button>");
    assignTd.append(link3);
    row.append(assignTd);
    link3.click(function(){
    	document.getElementById("taskID05").innerHTML= rowData.id;    	
    	document.getElementById('id05').style.display='block';
    });
    
}
function deleteTask(taskId) {
	var search= window.location.search; 
	var projectId = search.substring(1);    
	$.ajax({
	    url: "task/"+ taskId +"/delete",
	    type: "DELETE"
	})
	.success(function(data) {
		alert("Task was deleted successfully!");
		//window.location.replace("Details.html"+'?'+ projectId);
	})
	.fail(function(data) {
		alert("Task was NOT deleted!");
	})
	
}

function setToCompleted(taskId) {
	var search= window.location.search; 
	var projectId = search.substring(1);   
    
	$.ajax({
	    url: "task/"+ taskId +"/completed",
	    type: "PUT"
	})
	.success(function(data) {
		alert("Task was set to completed successfully!");
		//window.location.replace("Details.html"+'?'+ projectId);
	})
	.fail(function(data) {
		alert("Task was NOT set to completed!");
	})
	
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


});