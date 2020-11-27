<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html>

<head>
	<title>Student Registration Form</title>
</head>

<body>

	<form:form action="processForm" modelAttribute="student">

		First name: <form:input path="firstName" />
		<form:errors path="firstName" cssClass="error" />

		<br><br>

		Last name: <form:input path="lastName" />
		<form:errors path="lastName" cssClass="error" />

		<br><br>

		<input type="submit" value="Submit" />

	</form:form>


</body>

</html>