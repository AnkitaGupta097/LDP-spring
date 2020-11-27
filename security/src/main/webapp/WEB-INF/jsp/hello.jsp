<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>

<head>
	<title> Home Page</title>
</head>

<body>
	<h2>Home Page</h2>
	<hr>

	<p>
	Welcome to the  home page!
	</p>

	<hr>

	<!-- display user name and role -->

	<p>
		User: <security:authentication property="principal.username" />
		<br><br>
		Role(s): <security:authentication property="principal.authorities" />
	</p>

	<hr>


	<security:authorize access="hasRole('MANAGER')">


    		<p>
    			<a href="/managers">Leadership Page</a>
    			(Only for Manager peeps)
    		</p>

    	</security:authorize>


    	<security:authorize access="hasRole('ADMIN')">


    		<p>
    			<a href="/admins">IT Systems Page</a>
    			(Only for Admin peeps)
    		</p>

    	</security:authorize>


	<!-- Add a logout button -->
	<form:form action="/logout" method="POST">

		<input type="submit" value="Logout" />

	</form:form>

</body>

</html>