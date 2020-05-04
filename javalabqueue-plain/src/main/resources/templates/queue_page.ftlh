<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script>
        function changeName() {
            var name = document.getElementById("name_input").value;
            document.getElementById('name').innerText = "" + name;
        }
    </script>
</head>
<body>
    <div id="name">1</div>
    <br>
    <form>
        <input type="text" id="name_input">
        <button onclick="changeName()">Сменить имя</button>
    </form>
    <br>
    <form action="/queue" method="post">
        <input type="text" name="queueName" placeholder="Имя очереди">
        <input type="submit" value="Отправить">
    </form>
</body>
</html>