function loadRecords() {
	$("#listTbl tbody").html("");
	var url = "http://localhost:8080/course";

	$.getJSON(url, function(data) {
		// console.log(JSON.stringify(data));

		for (i in data) {
			var object = data[i];

			var deleteRecordfn = "deleteRecord('" + object.code + "')";
			var showRecordfn = "showRecord('" + object.code + "','"
					+ object.name + "','" + object.description + "')";
			// console.log(showRecordfn);
			var c = '<tr><td>' + object.code + '</td><td>' + object.name
					+ '</td><td>' + object.description
					+ '</td><td><button onClick=' + deleteRecordfn
					+ '>DELETE</button> </td><td><button onClick="'
					+ showRecordfn + '">SHOW</button> </td></tr>';

			$("#listTbl tbody").append(c);

		}
	});
}

function insertRecord() {
	var code = $("#code").val();
	var name = $("#name").val();
	var description = $("#description").val();
	var course = {
		"code" : code,
		"name" : name,
		"description" : description
	};
	// console.log("Input Data: " + JSON.stringify(course));
	var url = "http://localhost:8080/course";
	/*
	 * var request = $.ajax({ url : url, type : "POST", data :
	 * JSON.stringify(course), contentType : "application/json", dataType :
	 * "json" });
	 * 
	 * request.done(function(msg) { console.log("Response: " +
	 * JSON.stringify(msg)); loadRecords(); });
	 */
	postJSON(url, course, function(msg) {
		// console.log("Response: " + JSON.stringify(msg));
		loadRecords();
	});
}

function postJSON(url, data, callback) {
	$.ajax({
		url : url,
		type : "POST",
		data : JSON.stringify(data),
		contentType : "application/json",
		dataType : "json"
	}).done(callback);
}
function deleteRecord(code) {
	// console.log("Delete Records: " + code);
	var url = "http://localhost:8080/course/" + code;
	var request = $.ajax({
		url : url,
		type : "DELETE"
	});
	request.done(function(msg) {
		// console.log(msg);
		loadRecords();
	});
}
function showRecord(code, name, description) {
	// console.log("Show Button Clicked")
	$("#code").val(code);
	$("#name").val(name);
	$("#description").val(description);
	$("#addBtn").hide();
}
function updateRecord(code, name, description) {
	// console.log("Update Records: " + code);
	// alert($("#code").val() + " " + $("#name").val() + " " +
	// $("#description").val());

	var code = $("#code").val();
	var name = $("#name").val();
	var description = $("#description").val();
	var course = {
		"code" : code,
		"name" : name,
		"description" : description
	};
	// console.log(course);
	var url = "http://localhost:8080/course/" + code;
	var request = $.ajax({
		url : url,
		type : "PUT",
		data : JSON.stringify(course),
		contentType : "application/json",
		dataType : "json"

	});
	request.done(function(msg) {
		// console.log(msg);
		loadRecords();
		$("#addBtn").show();
	});

}