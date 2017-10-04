<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hola ${nombre}!</title>
</head>
<body>
<h2>${saludo} ${nombre}!</h2>
<h2>${con_atributo}</h2>
<footer>
    <#-- Incluyendo internacionalización-->
    <@spring.message "derecho_autor" />
</footer>
</body>
</html>