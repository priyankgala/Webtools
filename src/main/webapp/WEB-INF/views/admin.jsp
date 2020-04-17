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
%><div class="container-wrapper">
	<div class="container">
		<div class="page-header">
			<h1>Administrator page</h1>
			<c:if test="${sessionScope.userType != null}">
				<h2>
					Welcome:
					<c:out value="${sessionScope.userName}" />
					| <a href="<c:url value="logout.htm"/>">Logout</a>
				</h2>
			</c:if>
		</div>
		<h3>
			<a href="<c:url value="/admin/productInventory.htm" /> ">Product
				Inventory</a>
		</h3>
		<p>Here you can view, check and modify the product inventory!</p>
		<br /> <br />
		<h3>
			<a href="<c:url value="/admin/customerList/all.htm" /> ">Customer Management</a>
		</h3>
		<p>Here you can view the customer information!</p>
	</div>
</div>
<%@ include file="/WEB-INF/views/template/footer.jsp"%>