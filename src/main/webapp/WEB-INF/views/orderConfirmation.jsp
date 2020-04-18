<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<jsp:useBean id="now" class="java.util.Date" />

<div class="container-wrapper">
	<div class="container">
		<div class="page-header">
			<h1>Order</h1>
			<p class="lead">Order confirmation</p>
		</div>
		<div class="container">
			<div class="row">
				<form:form action="/priyav/customer/order/submitorder.htm" class="form-horizontal">
					<div
						class="well col-xs-10 col-sm-10 col-md-6 col-xs-offset-1 col-sm-offset-1 col-md-offset-3">
						<div class="txt-center">
							<h1>Receipt</h1>
						</div>
						<div class="row">
							<div class="col-xs-6 col-sm-6 col-md-6">
								<address>
									<strong>Billing Address</strong><br />
									${order.customer.billingAddress.streetName} <br />
									${order.customer.billingAddress.city},
									${order.customer.billingAddress.state} <br />
									${order.customer.billingAddress.zipCode}
								</address>
							</div>
						</div>

						<div class="row">
							<table class="table table-hover">
								<thead>
									<tr>
										<th>Product</th>
										<th>#</th>
										<th class="text-center">Price</th>
										<th class="text-center">Total</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="cartItem" items="${order.cart.cartItems}">
										<tr>
											<td class="col-md-9"><em>${cartItem.product.productName}</em>
											</th>
											<td class="col-md-1" style="text-align: center">${cartItem.quantity}
											</th>
											<td class="col-md-1" style="text-align: center">${cartItem.product.productPrice}
											</th>
											<td class="col-md-1" style="text-align: center">${cartItem.totalPrice}
											</th>
										</tr>
									</c:forEach>

									<tr>
										<th></th>
										<th></th>
										<th class="text-right">
											<h4>
												<strong>Grand Total:</strong>
											</h4>
										</th>
										<th class="text-center text-danger">
											<h4>
												<strong>${grandTotal}</strong>
											</h4>
										</th>
									</tr>

								</tbody>
							</table>
						</div>
						<a
							href="<c:url value="/customer/cart/cart.htm" />"
							class="btn btn-default">Back</a> <input type="submit"
							value="Submit Order" class="btn btn-default" /> <a
							href="<c:url value="/customer/product/all" />"
							class="btn btn-default">Cancel</a>
					</div>
				</form:form>
			</div>
		</div>

		<%@ include file="/WEB-INF/views/template/footer.jsp"%>