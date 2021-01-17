$(document).ready(function(e) {
		$("#userEmail").blur(function(event) {
			$("#dupEmail").html("");
			var emailId = $("#userEmail").val();
			$.ajax({
				url : 'validateEmail?email=' + emailId,
				success : function(abc) {
					if (abc == 'Duplicate') {
						$("#dupEmail").html("Email already registered");
						$("#userEmail").focus();
					}
				}
			});
		});
	});
