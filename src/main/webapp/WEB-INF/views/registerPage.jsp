<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>Priyav Umbrella Mart</title>

<!-- JQuery -->
<script src="https://code.jquery.com/jquery-2.2.1.min.js"></script>
<script
	src="https://cdn.datatables.net/1.10.10/js/jquery.dataTables.min.js"></script>

<!-- Bootstrap core CSS -->
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">

<!-- My carousel CSS -->
<link href="resources/css/carousel.css" rel="stylesheet">

<!-- Main CSS -->
<link href="resources/css/main.css" rel="stylesheet">

<link
	href="https://cdn.datatables.net/1.10.10/css/jquery.dataTables.min.css"
	rel="stylesheet">
<script>
	function validateEmail() {
		var emailinput = document.getElementById("emailId").value;
		var correctemailpattern = new RegExp(
				"^([A-Za-z0-9])([A-Za-z0-9_])*\\@([A-Za-z0-9])+\\.([A-Za-z]{2,4})");
		if (correctemailpattern.test(emailinput) == true) {
			document.getElementById("emailcorrection").innerHTML = "";
		} else {
			document.getElementById("emailcorrection").innerHTML = "Please enter valid email address";
		}
	}
	function validateMyForm() {
		// 		<!-- first name validation-->
		var fname = document.getElementById("firstName").value;
		if (fname == " " || fname == "") {
			document.getElementById("requiredFname").innerHTML = "Firstname cannot be empty, please enter first name";
			return false;
		} else if (!isNaN(fname)) {
			document.getElementById("requiredFname").innerHTML = "Name cannot be numeric";
			return false;
		} else {
			document.getElementById("requiredFname").innerHTML = "";
		}

		//<!--Last name validations -->
		var lname = document.getElementById("lastName").value;
		if (lname == " " || lname == "") {
			document.getElementById("requiredLname").innerHTML = "Lastname cannot be empty, please enter last name";
			return false;
		} else if (!isNaN(lname)) {
			document.getElementById("requiredLname").innerHTML = "Name cannot be numeric";
			return false;
		} else {
			document.getElementById("requiredLname").innerHTML = "";
		}

		//<!--Email validation -->
		var emailinput = document.getElementById("emailId").value;
		var correctemailpattern = new RegExp(
				"^([A-Za-z0-9])([A-Za-z0-9_])*\\@([A-Za-z0-9]{4,9})+\\.([A-Za-z]{2,4})");
		if (correctemailpattern.test(emailinput) == true) {
			document.getElementById("emailcorrection").innerHTML = "";
		} else {
			document.getElementById("emailcorrection").innerHTML = "Please enter valid email address";
			return false;
		}

		// 		<!-- Phone validation-->
		var numinput = document.getElementById("phoneNumber").value;
		var numpattern = /^\d{3}-\d{3}-\d{4}$/g;
		if (numpattern.test(numinput) == true) {
			document.getElementById("phonecorrection").innerHTML = "";
		} else {
			document.getElementById("phonecorrection").innerHTML = "Please enter number in xxx-xxx-xxxx format";
			return false;
		}

		// 		<!--Password matching -->
		var password = $("#password").val();
		if (fname == " " || fname == "") {
			document.getElementById("requiredFname").innerHTML = "Firstname cannot be empty, please enter first name";
			return false;
		} else if (!isNaN(fname)) {
			document.getElementById("requiredFname").innerHTML = "Name cannot be numeric";
			return false;
		} else {
			document.getElementById("requiredFname").innerHTML = "";
		}

		var confirmPassword = $("#cpassword").val();
		if (fname == " " || fname == "") {
			document.getElementById("requiredFname").innerHTML = "Firstname cannot be empty, please enter first name";
			return false;
		} else if (!isNaN(fname)) {
			document.getElementById("requiredFname").innerHTML = "Name cannot be numeric";
			return false;
		} else {
			document.getElementById("requiredFname").innerHTML = "";
		}

		if (password != confirmPassword) {
			document.getElementById("passwordCheck").innerHTML = "Passwords do not match!";
			return false;
		} else {
			document.getElementById("passwordCheck").innerHTML = "";
		}

		//Checking billing addresss
		var street = document.getElementById("billingStreet").value;
		if (street == " " || street == "") {
			document.getElementById("requiredStreet").innerHTML = "Street cannot be empty, please enter Street name";
			return false;
		} else {
			document.getElementById("requiredStreet").innerHTML = "";
		}

		var aptNo = document.getElementById("billingAptNo").value;
		if (aptNo == " " || aptNo == "") {
			document.getElementById("requiredAptNo").innerHTML = "Apartment number cannot be empty, please enter apartment numbers";
			return false;
		} else {
			document.getElementById("requiredAptNo").innerHTML = "";
		}

		var state = document.getElementById("billingState").value;
		if (state == " " || state == "") {
			document.getElementById("requiredState").innerHTML = "State cannot be empty, please enter State name";
			return false;
		} else if (!isNaN(state)) {
			document.getElementById("requiredState").innerHTML = "State cannot be numeric";
			return false;
		} else {
			document.getElementById("requiredState").innerHTML = "";
		}

		var city = document.getElementById("billingCity").value;
		if (city == " " || city == "") {
			document.getElementById("requiredCity").innerHTML = "City cannot be empty, please enter City name";
			return false;
		} else if (!isNaN(city)) {
			document.getElementById("requiredCity").innerHTML = "City cannot be numeric";
			return false;
		} else {
			document.getElementById("requiredCity").innerHTML = "";
		}

		var zipcode = document.getElementById("billingZip").value;
		var zippattern = /^[0-9]{5}$/i
		if (zippattern.test(zipcode) == true) {
			document.getElementById("requiredZip").innerHTML = "";
		} else {
			document.getElementById("requiredZip").innerHTML = "Please enter zip code in xxxxx format";
		}

	}
</script>
<style>
/* label { */
/* 	float: left; */
/* 	width: 150px; */
/* } */
#requiredFname, #requiredLname, #emailcorrection, #phonecorrection,
	#passwordCheck, #requiredStreet, #requiredAptNo, #requiredCity,
	#requiredState, #requiredZip {
	color: #ff0000;
}
</style>
</head>
<!-- NAVBAR
================================================== -->
<body>
	<%
		//This part is to check if user is authenticated even if the browsers back button or refresh is clicked
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");
	if (session.getAttribute("userType") == null) {
		if (request.getAttribute("URI").equals("/priyav/register.htm") ) {
			System.out.println("Inside this if loop");
			response.reset();
		} else {
			System.out.println("Inside this else loop");
			response.sendRedirect("login.htm");
		}
	} else {
		System.out.println("User is authenticated");
	}
	%>
	<div class="navbar-wrapper">
		<div class="container">
			<nav class="navbar navbar-inverse navbar-static-top">
				<div class="container">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed"
							data-toggle="collapse" data-target="#navbar"
							aria-expanded="false" aria-controls="navbar">
							<span class="sr-only">Toggle navigation</span> <span
								class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href="<c:url value="/" />"">Priyav
							Umbrella's</a>
					</div>
					<div id="navbar" class="navbar-collapse collapse">
						<ul class="nav navbar-nav">
							<li><a href="<c:url value="/" />">Home</a></li>
							<li><a href="<c:url value="/product/all" />">Products</a></li>
							<li><a href="<c:url value="/about" />">About Us</a></li>
						</ul>
						<ul class="nav navbar-nav pull-right">
							<c:if test="${sessionScope.userType != null}">
								<li><a>Welcome: ${sessionScope.userType}</a></li>
								<li><a href="<c:url value="logout.htm" />">Logout</a></li>

								<c:if test="${sessionScope.userType != 'admin'}">
									<li><a href="<c:url value="/customer/cart" />">Cart</a></li>
								</c:if>

								<c:if test="${sessionScope.userType == 'admin'}">
									<li><a href="<c:url value="admin.htm" />">Admin</a></li>
								</c:if>

							</c:if>

							<c:if test="${sessionScope.userType == null}">
								<li><a href="<c:url value="login.htm" />">Login</a></li>
								<li><a href="<c:url value="register.htm" />">Register</a></li>
							</c:if>
						</ul>
					</div>
				</div>
			</nav>

		</div>
	</div>
	<div class="container-wrapper">
		<div class="container">
			<div class="page-header">
				<h1>Register Customer</h1>
				<p class="lead">Please fill below information below:</p>
			</div>
			<form action="registersuccess.htm" method="post" name="myForm"
				id="theForm" onsubmit="return validateMyForm()">
				<h3>Basic Information:</h3>

				<!-- First name -->
				<div class="form-group">
					<label for="name">FirstName</label> <input type="text"
						class="form-control" name="firstName" id="firstName"
						placeholder="First Name" required /> <span id="requiredFname"></span>
				</div>

				<!-- Last name -->
				<div class="form-group">
					<label for="name">LastName</label> <input type="text"
						name="lastName" id="lastName" placeholder="Last Name" required
						class="form-control" /> <span id="requiredLname"></span>
				</div>

				<div class="form-group">
					<label for="email">Email</label> <input type="email" name="emailId"
						id="emailId" placeholder="yourname@domain.com" required
						pattern="^([A-Za-z0-9])([A-Za-z0-9_])*@([A-Za-z0-9])+.([A-Za-z]{2,4})"
						class="form-control" oninput="validateEmail()" /> <span
						id="emailcorrection"></span> <span style="color: #ff0000">${emailMsg}</span>
				</div>

				<div class="form-group">
					<label for="phone">Phone</label> <input type="text"
						name="phoneNumber" id="phoneNumber" placeholder="xxx-xxx-xxxx"
						required pattern="^\d{3}-\d{3}-\d{4}$" class="form-control" /> <span
						id="phonecorrection"></span>
				</div>

				<div class="form-group">
					<label for="password">Password</label> <input type="password"
						name="password" id="password" placeholder="Password"
						class="form-control" required /> <span id="requiredPassword"></span>
				</div>

				<div class="form-group">
					<label for="cpassword">Confirm Password</label> <input
						type="password" name="cpassword" id="cpassword"
						placeholder="Re-type Password" required class="form-control" /> <span
						id="passwordCheck"></span>
				</div>
				<br />

				<h3>Billing Address:</h3>

				<div class="form-group">
					<label for="billingStreet">Street Name</label> <input type="text"
						id="billingStreet" name="street" class="form-control" required />
					<span id="requiredStreet"></span>
				</div>

				<div class="form-group">
					<label for="billingApartmentNumber">Apartment Number</label> <input
						type="number" id="billingAptNo" name="aptNo" class="form-control"
						required /> <span id="requiredAptNo"></span>
				</div>

				<div class="form-group">
					<label for="billingCity">City</label> <input tyep="text"
						id="billingCity" class="form-control" name="city" required /> <span
						id="requiredCity"></span>
				</div>

				<div class="form-group">
					<label for="billingState">State</label> <input type="text"
						id="billingState" class="form-control" name="state" required /> <span
						id="requiredState"></span>
				</div>

				<div class="form-group">
					<label for="billingZip">Zipcode</label> <input type="text"
						id="billingZip" class="form-control" name="zipcode" required /> <span
						id="requiredZip"></span>
				</div>

				<br /> <input type="submit" value="submit" class="btn btn-default">
				<a href="<c:url value="/" />" class="btn btn-default">Cancel</a>

			</form>
		</div>
	</div>

	<%@ include file="/WEB-INF/views/template/footer.jsp"%>