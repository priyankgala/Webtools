<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ include file="/WEB-INF/views/template/header.jsp"%>


<div class="container-wrapper">
	<div class="container">
		<div class="page-header">
			<h1>Product Detail</h1>


			<p class="lead">Here is the detail information of the product:</p>
		</div>

		<div class="container" ng-app="cartApp">
			<div class="row">
				<div class="col-md-5">
					<img
						src="<spring:url value="/resources/images/${product.productId}.png" />"
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

					<%--                     <c:set var = "role" scope="page" value="${param.role}" /> --%>
					<%--                     <c:set var = "url" scope="page" value="/product/productList" /> --%>
					<%--                     <c:if test="${role='admin'}"> --%>
					<%--                         <c:set var="url" scope="page" value="/admin/productInventory" /> --%>
					<%--                     </c:if> --%>

					<c:if test="${sessionScope.userType == 'admin'}">
						<a href="<c:url value = "/admin/productInventory.htm" />"
							class="btn btn-default">Back</a>
						<a href="<spring:url value="/admin/product/updateProduct/${product.productId}" />" class="btn btn-warning btn-large"> <span
							class="glyphicon glyphicon-remove"></span>Edit
						</a>
					</c:if>

					<c:if test="${sessionScope.userType == 'customer'}">
						<a href="<c:url value = "/product/all" />" class="btn btn-default">Back</a>
						<a href="#" class="btn btn-warning btn-large"><span
							class="glyphicon glyphicon-shopping-cart"></span> Order Now</a>
						<a href="<spring:url value="/cart" />" class="btn btn-default"><span
								class="glyphicon glyphicon-hand-right"></span> View Cart</a>
					</c:if>

					<!-- 					<p ng-controller="cartCtrl"> -->
					<%-- 						<a href="<c:url value = "/admin/productInventory.htm" />" --%>
					<!-- 							class="btn btn-default">Back</a> <a href="#" -->
					<!-- 							class="btn btn-warning btn-large" -->
					<%-- 							ng-click="addToCart('${product.productId}')"><span --%>
					<!-- 							class="glyphicon glyphicon-shopping-cart"></span> Order Now</a> <a -->
					<%-- 							href="<spring:url value="/cart" />" class="btn btn-default"><span --%>
					<!-- 							class="glyphicon glyphicon-hand-right"></span> View Cart</a> -->
					<!-- 					</p> -->

				</div>
			</div>
		</div>


		<!-- My -->
		<script src="<c:url value="/resources/js/controller.js?v3" /> "></script>

		<%@ include file="/WEB-INF/views/template/footer.jsp"%>