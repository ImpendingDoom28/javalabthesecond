<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Чат</title>
    <script
            src="https://code.jquery.com/jquery-3.4.1.js"
            integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
    <script>
        var webSocket;

        function connect() {
            webSocket = new WebSocket('ws://localhost:8080/jlmq');
        }

        function sendMessage(queueName, email, username, realName) {
            let payload = {
                "command": "send",
                "queueName": queueName,
                "body": {
                    "email": email,
                    "userData": {
                        "username": username,
                        "realname": realName
                    }
                }
            };

            let message = {
                "header": "send",
                "payload": payload
            };

            webSocket.send(JSON.stringify(message));
        }
    </script>
</head>
<body onload="connect()">
<div>
    <p>Создать задание:</p>
    <form>
        <input type="text" id="queueName" placeholder="Имя очереди" required>
        <input type="email" id="email" placeholder="E-mail" required>
        <input type="text" id="username" placeholder="Логин" required>
        <input type="text" id="realname" placeholder="Ваше имя" required>
        <button onclick="sendMessage($('#queueName').val(), $('#email').val(), $('#username').val(), $('#realname').val(),)">Отправить</button>
    </form>

</div>
</body>
</html>