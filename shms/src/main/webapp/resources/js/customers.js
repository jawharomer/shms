function getAddingCustomer() {
	console.log("getAddingCustomer->fired");
	$.ajax({
		type : "GET",
		url : $$ContextURL + "/customers/add",
		contentType : "application/json",
		success : function(response) {
			$("#modal-body").html(response);
			$("#modal").modal("show");
		},
		error : function(response) {
			$("#modal-body").html(response.responseText);
			$("#modal").modal("show");
		}
	});

}

function getEditingCustomer(id) {
	console.log("getEditingCustomer->fired");
	console.log("id=" + id);
	$.ajax({
		type : "GET",
		url : $$ContextURL + "/customers/edit/" + id,
		contentType : "application/json",
		success : function(response) {
			$("#modal-body").html(response);
			$("#modal").modal("show");
		},
		error : function(response) {
			$("#modal-body").html(response.responseText);
			$("#modal").modal("show");
		}
	});

}

function deleteCustomer(id) {
	console.log("deleteCustomer->fired");
	console.log("id=" + id);
	$.ajax({
		type : "POST",
		url : $$ContextURL + "/customers/delete/" + id,
		contentType : "application/json",
		success : function(response) {
			$("#modal-body").html(response);
			$("#modal").modal("show");
		},
		error : function(response) {
			$("#modal-body").html(response.responseText);
			$("#modal").modal("show");
		}
	});

}