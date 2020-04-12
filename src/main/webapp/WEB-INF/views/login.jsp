<%@ include file="/WEB-INF/views/template/header.jsp" %>

<div class="container-wrapper">
    <div class="container">
<!--         <div id="login-box"> -->
            <h2>Login with Username and Password</h2>
            <form name="loginForm" action="loginsuccess.htm" method="post" id="theForm">
                <c:if test="${not empty error}">
                    <div class="error" style="color: #ff0000;">${error}</div>
                </c:if>

                <div class="form-group">
                    <label for="username">User: </label>
                    <input type="text" id="username" name="username" class="form-control" />
                </div>
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" class="form-control" />
                </div>
                <input type="submit" value="Submit" class="btn btn-default">
               
            </form>
<!--         </div> -->
    </div>
</div>

<%@ include file="/WEB-INF/views/template/footer.jsp" %>
