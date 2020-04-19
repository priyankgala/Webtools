<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ include file="/WEB-INF/views/template/header.jsp"%>

<%
	//This part is to check if user is authenticated even if the browsers back button or refresh is clicked
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setHeader("Expires", "0");
if (session.getAttribute("userType") == null) {
	response.sendRedirect("/priyav/login.htm");
} else {
	System.out.println("User is authenticated");
}
%>
<div class="container-wrapper">
	<div class="container">
		<div class="page-header">
			<h1>Customer Management Page</h1>

			<p class="lead">This is the customer management page!</p>
		</div>

		<table class="table table-striped table-hover">
			<thead>
				<tr class="bg-success">
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Phone</th>
					<th>Address</th>
				</tr>
			</thead>
			<c:forEach items="${customerList}" var="customer">
				<tr>
					<th>${customer.custFName}</th>
					<th>${customer.custLName}</th>
					<th>${customer.custEmail}</th>
					<th>${customer.custPhone}</th>
					<th><a
						href="/priyav/admin/customerList/viewOrder/${customer.custId}">View
					</a></th>
				</tr>
			</c:forEach>
		</table>

		<%@ include file="/WEB-INF/views/template/footer.jsp"%>