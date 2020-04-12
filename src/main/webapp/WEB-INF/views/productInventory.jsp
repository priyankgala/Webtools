<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ include file="/WEB-INF/views/template/header.jsp"%>


<script>
	$(document).ready(
			function() {
				$('.table').DataTable(
						{
							"lengthMenu" : [ [ 1, 2, 3, 5, 10, -1 ],
									[ 1, 2, 3, 5, 10, "All" ] ]
						});
			});
</script>

<div class="container-wrapper">
	<div class="container">
		<div class="page-header">
			<h1>Product Inventory Page</h1>
			<p class="lead">This is the product inventory page:</p>
			<table class="table table-striped table-hover">
				<thead>
					<tr class="bg-success">
						<th>Photo Thumb</th>
						<th>Product Name</th>
						<th>Category</th>
						<th>Condition</th>
						<th>Price</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${products}" var="product">
						<tr>
							<th><img src="<spring:url value="/resources/images/${product.productId}.png" />" alt="image" style="width: 50%" /></th>
							<th>${product.productName}</th>
							<th>${product.productCategory}</th>
							<th>${product.productDescription}</th>
							<th>${product.productPrice}USD</th>
							<th><a
								href="<spring:url value="/product/viewProduct/${product.productId}" />"><span
									class="glyphicon glyphicon-info-sign"></span></a> <a
								href="<spring:url value="/admin/product/deleteProduct/${product.productId}" />"><span
									class="glyphicon glyphicon-remove"></span></a> <a
								href="<spring:url value="/admin/product/editProduct/${product.productId}" />"><span
									class="glyphicon glyphicon-pencil"></span></a></th>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<a
				href="<spring:url value="/admin/productInventory/addProduct.htm" />"
				class="btn btn-primary">Add Product</a>
		</div>
	</div>
</div>
<%@ include file="/WEB-INF/views/template/footer.jsp"%>