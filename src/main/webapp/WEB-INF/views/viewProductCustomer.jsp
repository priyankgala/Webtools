<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ include file="/WEB-INF/views/template/header.jsp"%>


<div class="container-wrapper">
	<div class="container">
		<p class="lead">
			<c:out value="${requestScope.cartAdd}" />
		</p>

		<div class="page-header">
			<h1>Product Detail</h1>
			<c:if test="${sessionScope.userType == null}">
				<p class="lead" style="color: #ff0000;">Please Register/ Login
					to buy this product</p>
			</c:if>

			<c:if test="${sessionScope.userType != null}">
				<p class="lead">Here is the detail information of the product:</p>
			</c:if>

		</div>

		<div class="container">
			<div class="row">
				<div class="col-md-5">
					<img
						src="<c:url value="/resources/images/${product.productId}.png" />"
						alt="image" style="width: 70%" />
				</div>
				<div class="col-md-5">
					<h3>${product.productName}</h3>
					<p>
						<strong>Manufacturer</strong>Priyav Umbrella's
					</p>
					<p>
						<strong>Category</strong>: ${product.productCategory}
					</p>
					<p>
						<strong>Description</strong>: ${product.productDescription}
					</p>
					<p>${product.productPrice}USD</p>

					<br />

					<c:if test="${sessionScope.userType == 'admin'}">
						<a href="<c:url value = "/admin/productInventory.htm" />"
							class="btn btn-default">Back</a>
						<a
							href="<spring:url value="/admin/product/updateProduct/${product.productId}" />"
							class="btn btn-warning btn-large"> <span
							class="glyphicon glyphicon-remove"></span>Update Product
						</a>
					</c:if>

					<c:if test="${sessionScope.userType == 'customer'}">
						<a href="<c:url value = "/customer/product/all" />" class="btn btn-default">Back</a>
						<a
							href="<spring:url value="/customer/product/addCart/${product.productId}" />"
							class="btn btn-warning btn-large"><span
							class="glyphicon glyphicon-shopping-cart"></span> Order Now</a>
						<a href="<spring:url value="/customer/cart.htm" />" class="btn btn-default"><span
							class="glyphicon glyphicon-hand-right"></span> View Cart</a>
					</c:if>

<%-- 					<c:if test="${sessionScope.userType == null}"> --%>
<%-- 						<a href="<c:url value = "/customer/product/all" />" class="btn btn-default">Back</a> --%>
<%-- 					</c:if> --%>

				</div>
			</div>
		</div>
	</div>
</div>


<!-- My -->
<script src="<c:url value="/resources/js/controller.js?v3" /> "></script>

<%@ include file="/WEB-INF/views/template/footer.jsp"%>