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

<!-- Angular.JS -->
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.1/angular.min.js"></script>

<!-- JQuery -->
<script src="https://code.jquery.com/jquery-2.2.1.min.js"></script>
<script
	src="https://cdn.datatables.net/1.10.10/js/jquery.dataTables.min.js"></script>

<!-- Bootstrap core CSS -->
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">

<!-- My carousel CSS -->
<link href="<c:url value="/resources/css/carousel.css" />"
	rel="stylesheet">

<!-- Main CSS -->
<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">

<link
	href="https://cdn.datatables.net/1.10.10/css/jquery.dataTables.min.css"
	rel="stylesheet">


</head>
<!-- NAVBAR
================================================== -->
<body>
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
							<c:if test="${sessionScope.userType != 'admin'}">
								<li><a href="<c:url value="/product/all" />">Products</a></li>
							</c:if>

							<li><a href="<c:url value="/about.htm" />">About Us</a></li>
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