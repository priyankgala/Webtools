<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/views/template/header.jsp"%>
<%
//This part is to check if user is authenticated even if the browsers back button or refresh is clicked
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");
	if (session.getAttribute("userType") == null) {
			response.sendRedirect("/priyav/login.htm");
	}else{
		System.out.println("User is authenticated");
	}
%>
<div class="container-wrapper">
	<div class="container">
		<div class="page-header">
			<h1>Add Product</h1>
			<p class="lead">Fill the below information to add a product:</p>
		</div>
		<form:form
			action="${pageContext.request.contextPath}/admin/productInventory/addProduct.htm"
			method="post" enctype="multipart/form-data" commandName="product">
			<div class="form-group">
				<label for="name">Name</label>
				<form:errors path="productName" cssStyle="color:#ff0000;" />
				<form:input path="productName" id="name" class="form-control" />
			</div>

			<div class="form-group">
				<label for="category">Category</label>
				<form:errors path="productCategory" cssStyle="color:#ff0000;" />
				<label class="checkbox-inline"> <form:radiobutton
						path="productCategory" id="category" value="mens" />Men's
					Umbrella
				</label> <label class="checkbox-inline"><form:radiobutton
						path="productCategory" id="category" value="ladies" />Ladies
					Umbrella</label> <label class="checkbox-inline"><form:radiobutton
						path="productCategory" id="category" value="kids" />Kids Umbrella</label>
			</div>

			<div class="form-group">
				<label for="description">Description</label>
				<form:errors path="productDescription" cssStyle="color:#ff0000;" />
				<form:textarea path="productDescription" id="description"
					class="form-control" />
			</div>

			<div class="form-group">
				<label for="price">Price</label>
				<form:errors path="productPrice" cssStyle="color:#ff0000;" />
				<form:input path="productPrice" id="price" class="form-control" />
			</div>

			<div class="form-group">
				<label for="unitInStock">Unit In Stock</label>
				<form:errors path="unitInStock" cssStyle="color:#ff0000;" />
				<form:input path="unitInStock" id="unitInStock" class="form-control" />
			</div>

			<div class="form-group">
				<label for="productImage">Upload Picture</label>
				<form:input path="productImage" id="productImage"
					name="productImage" type="file" class="form:input-large" /> 
			</div>

			<br />
			<br />

			<input type="submit" value="submit" class="btn btn-default">
			<a href="<c:url value="/admin/productInventory.htm" />"
				class="btn btn-default">Cancel</a>
		</form:form>
	</div>
</div>
<%@ include file="/WEB-INF/views/template/footer.jsp"%>