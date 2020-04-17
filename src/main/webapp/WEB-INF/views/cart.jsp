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
<!-- My -->
<div class="container-wrapper">
	<div class="container">
		<section>
			<div class="jumbotron">
				<div class="container">
					<h1>Cart</h1>
					<p>All the selected products in your shopping cart</p>
				</div>
			</div>
		</section>

		<section class="container">

			<div>

				<div>
					<a class="btn btn-danger pull-left"><span
						class="glyphicon glyphicon-remove-sign"></span> Clear Cart</a>
<%-- 					<c:if test="${cart.cart.cartId} == null }"> --%>
						<a href="<c:url value="/customer/order/" />"
							class="btn btn-success pull-right"><span
							class="glyphicon glyphicon-shopping-cart"></span> Check out</a>
<%-- 					</c:if> --%>
				</div>

				<br /> <br /> <br />

				<table class="table table-hover">
					<thead>
						<tr class="bg-success">
							<th>Photo</th>
							<th>Quantity</th>
							<th>Price</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${cart}" var="cart">
							<tr>
								<th><img
									src="<spring:url value="/resources/images/${cart.product.productId}.png" />"
									alt="image" style="width: 50%" /></th>
								<th>${cart.quantity}</th>
								<th>${cart.totalPrice}USD</th>
								<th><a
									href="<spring:url value="/customer/removeFromCart/${cart.cartItemId}" />"
									class="label label-danger"><span
										class="glyphicon glyphicon-remove"></span>remove</a></th>
							</tr>
						</c:forEach>
					</tbody>
					<tr>
						<th></th>
						<th>Grand Total</th>
						<th><c:out value="${grandTotal}USD"></c:out></th>
						<th></th>
					</tr>
				</table>

				<a href="<spring:url value="/customer/product/all" />"
					class="btn btn-default">Continue Shopping</a>
			</div>
		</section>
	</div>
</div>

<%@ include file="/WEB-INF/views/template/footer.jsp"%>