$(document).ready(function() {

  $('#loginForm').submit(function(event) {
	  
	  var username = $('#username').val();
	  var password = $('#password').val();
	  var json = { "username" :username,"password" : password};

	  var loginUrl="http://localhost:8080/forum-api/login/checkLogin";
	  $.ajax({
		  url: loginUrl,
		  data: JSON.stringify(json),
		  type: "POST",
		  success: function(smartphone) {
			if(data.status == "success"){
				document.getElementById("employeeForm").action ="/forum-api/login/loginSuccess";
				document.getElementById("employeeForm").submit();
			}else{
			    alert("Incorrect login and password combination.");
			}
		  }
	  });
	  
	  event.preventDefault();
  });

});