<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/views/template/header.jsp"%>

<%
	//This part is to check if user is authenticated even if the browsers back button or refresh is clicked
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setHeader("Expires", "0");
if (session.getAttribute("userType") == null) {
	if (request.getAttribute("URI").equals("/priyav/") || request.getAttribute("URI").equals("/priyav/logout.htm")) {
		System.out.println("Inside this if loop");
	} else {
		System.out.println("Inside this else loop");
		response.sendRedirect("login.htm");
	}
} else {
	System.out.println("User is authenticated");
}
%>
<!-- Carousel
        ================================================== -->
<div id="myCarousel" class="carousel slide" data-ride="carousel">
	<!-- Indicators -->
	<ol class="carousel-indicators">
		<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
		<li data-target="#myCarousel" data-slide-to="1"></li>
		<li data-target="#myCarousel" data-slide-to="2"></li>
	</ol>
	<div class="carousel-inner" role="listbox">
		<div class="item active">
			<img class="first-slide home-image"
				src="<c:url value="/resources/images/c7.jpg" />" alt="First slide">
			<div class="container">
				<div class="carousel-caption">
					<h1>Welcome to my Umbrella Store</h1>
					<p>Here you can browse and buy Umbrellas or customize your
						own!!</p>
				</div>
			</div>
		</div>
		<div class="item">
			<img class="second-slide home-image"
				src="<c:url value="/resources/images/c8.jpg" />" alt="Second slide">
			<div class="container">
				<div class="carousel-caption">
					<h1>Facts You Should know About Music</h1>
					<p>Music brings jey, to all of our hearts. It's one of those,
						emotional arts.</p>
				</div>
			</div>
		</div>
		<div class="item">
			<img class="third-slide home-image "
				src="<c:url value="/resources/images/c10.jpg" />" alt="Third slide">
			<div class="container">
				<div class="carousel-caption">
					<h1>It's A Jazz Affair</h1>
					<p>Through ups and downs, Somehow I manage to survive in life.</p>
				</div>
			</div>
		</div>
	</div>
	<a class="left carousel-control" href="#myCarousel" role="button"
		data-slide="prev"> <span class="glyphicon glyphicon-chevron-left"
		aria-hidden="true"></span> <span class="sr-only">Previous</span>
	</a> <a class="right carousel-control" href="#myCarousel" role="button"
		data-slide="next"> <span class="glyphicon glyphicon-chevron-right"
		aria-hidden="true"></span> <span class="sr-only">Next</span>
	</a>
</div>
<!-- /.carousel -->


<!-- Marketing messaging and featurettes
        ================================================== -->
<!-- Wrap the rest of the page in another container to center all the content. -->

<div class="container marketing">

	<!-- Three columns of text below the carousel -->
	<c:if test="${sessionScope.userType} != admin }">
		<div class="row">
			<div class="col-lg-4">
				<a class="btn btn-default"
					href="<c:url value="/product/productListByCategory?searchCondition=MENS" />"
					role="button"> <img class="img-circle"
					src="<c:url value="/resources/images/back11.jpg"/>"
					alt="Instrument Image" width="140" height="140">
				</a>

				<h2>MENS:</h2>
				<p>Looking for mens umbrellas? Buy a stylish gents umbrella made
					with the very latest in umbrella technology! Take a look today!</p>

			</div>



			<div class="col-lg-4">
				<a class="btn btn-default"
					href="<c:url value="/product/productListByCategory?searchCondition=LADIES" />"
					role="button"> <img class="img-circle"
					src="<c:url value="/resources/images/back12.jpg"/>"
					alt="Instrument Image" width="140" height="140">
				</a>

				<h2>Ladies:</h2>
				<p>From compact folding to ladies long walking style and
					everything in between. Dip into one of our 10 different ladies
					umbrella sub-categories</p>

			</div>


			<div class="col-lg-4">
				<a class="btn btn-default"
					href="<c:url value="/product/productListByCategory?searchCondition=KIDS" />"
					role="button"> <img class="img-circle"
					src="<c:url value="/resources/images/back13.jpg"/>"
					alt="Instrument Image" width="140" height="140">
				</a>

				<h2>KIDS:</h2>
				<p>Unique Designs, Durable Quality. Give a Twist to Your Child's
					Style!</p>

			</div>
		</div>
	</c:if>
	<%@ include file="/WEB-INF/views/template/footer.jsp"%>