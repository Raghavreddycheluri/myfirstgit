$(document).ready(function() {
	// alert("JQuery Loaded");
	loadRecords();
	$("#addBtn").click(insertRecord);
	$("#updateBtn").click(updateRecord);
});
