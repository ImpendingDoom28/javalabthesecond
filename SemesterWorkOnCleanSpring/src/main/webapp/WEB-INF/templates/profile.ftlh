<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Profile</title>
</head>
<body>
 <!--Теги во фримаркере начинаются с решётки -->
 <!--Условное выражение во фримаркере: Если user не null, то выполняем -->
 <#if user??>
     <!--Выполняем вывод никнейна и почты пользователя -->
     ${user.nickname} ${user.email}
 <#else>
     <!-- Иначе пишем, что пользователя нет -->
     <h1>User not found</h1>
 </#if>
 ${authUser}
</body>
</html>