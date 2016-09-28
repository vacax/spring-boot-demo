<!DOCTYPE html>
<html>
<head>
    <title>Login Customizado.....</title>
</head>
<body>
<form role="form" action="/login" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <div>
        <label for="username">Usuario</label>
        <input type="text" name="username" id="username" required autofocus>
    </div>
    <div>
        <label for="password">Password</label>
        <input type="password" name="password" id="password" required>
    </div>
    <div>
        <label for="remember-me">Recordar...</label>
        <input type="checkbox" name="remember-me" id="remember-me">
    </div>
    <button type="submit">Sign in</button>
</form>

<#if error.isPresent()>
<p>usuario no existe....</p>
</#if>
</body>
</html>