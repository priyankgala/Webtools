<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ include file="/WEB-INF/views/template/header.jsp"%>

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
					<a class="btn btn-danger pull-left" ng-click="clearCart()"><span
						class="glyphicon glyphicon-remove-sign"></span> Clear Cart</a> <a
						href="<spring:url value="/order/${cartId}" />"
						class="btn btn-success pull-right"><span
						class="glyphicon glyphicon-shopping-cart"></span> Check out</a>
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
							</tr>
						</c:forEach>
					</tbody>
					<tr>
						<th></th>
						<th>Grand Total</th>
						<th ><c:out value="${grandTotal}USD"></c:out></th>
						<th></th>
					</tr>
				</table>

				<a href="<spring:url value="/productList" />"
					class="btn btn-default">Continue Shopping</a>
			</div>
		</section>
	</div>
</div>

<%@ include file="/WEB-INF/views/template/footer.jsp"%>