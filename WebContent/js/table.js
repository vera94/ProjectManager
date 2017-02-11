function filter() {
  // Declare variables 
  var input, filter, table, tr, td, i;
  input = document.getElementById("filterValue");
  filter = input.value.toUpperCase();
  table = document.getElementById("ProjectsTable");
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

	var table = $("#ProjectsTable");
    $.ajax({
        url : "http://localhost:8080/wwwHomework/user/vboi4eva/projects",
        type : "GET",
        dataType : "json",
        success : function(data) {
            renderTable(data.project, table);
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
    row.append($("<td>" + rowData.topic + "</td>"));
    row.append($("<td>" + rowData.description + "</td>")); 
    row.append($("<td>" + rowData.status + "</td>"));
    //if (isUserAuthenticated && rowData.amount > 0) {
    var detailsTd = $("<td />");
    var link = $("<button>details</button>");
    detailsTd.append(link);
    row.append(detailsTd);
    link.click(function(){
    	window.location.replace("Details.html"+'?'+ rowData.id);
    	//window.location.search= rowData.id;
    });
//        link.click(function() {
//            $.ajax({
//                url: 'user',
//                type: "PUT",
//                dataType: "json",
//                success: alert("wooho")
//            });
//        });
    
}



});