<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Register</title>
</head>
<body>
    <form method="POST" action="/register">
        <input type="text" placeholder="Enter your nickname" name="nickname"/>
        <input type="email" placeholder="Enter your e-mail" name="email"/>
        <input type="password" placeholder="Enter your password" name="password"/>
        <input type="password" placeholder="Confirm password" name="confirmPassword"/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <input type="submit" value="Register"/>
    </form>
</body>
</html>