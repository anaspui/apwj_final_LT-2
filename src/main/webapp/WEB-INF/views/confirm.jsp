<%@ page contentType="text/html" %>
<%@ page isELIgnored="false" %>
<html>
<head></head>
<body>
<h3>Registration Success</h3>

<p>Your registration is successful. Please visit login page.</p>

<p>Full Name: ${user.fullname}</p>
<p>Email: ${user.email}</p>
<p>Date Of Birth: ${user.dateOfBirth}</p>

<hr>

<a href="${pageContext.request.contextPath}">Home</a>

</body>
</html>