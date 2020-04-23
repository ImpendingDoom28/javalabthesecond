<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Your chat</title>
    <script
            src="https://code.jquery.com/jquery-3.4.1.js"
            integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.min.js"></script>
    <script>
        var webSocket;
        function connect() {
            webSocket = new SockJS("http://localhost:8080/chat");
            document.cookie = 'path=/';

            webSocket.onmessage = function receiveMessage(response) {
                console.log(response.code);
                if(response.code === "403") {
                    $('.body').innerHTML = `<h1>Error during websocket registration, chat closed for you.</h1>`;
                } else {
                    let data = response['data'];
                    let json = JSON.parse(data);
                    $('#messagesList').last().after("<li>" + json['roomId'] + ': ' + json['text'] + "</li>")
                }
            }
        }

        function sendMessage(text, roomId) {
            let message = {
                text,
                roomId,
            };

            webSocket.send(JSON.stringify(message));
        }
    </script>
</head>
<body onload="connect()" class="body">
    <#if room??>
        <div>
            <input type="text" id="message" placeholder="Enter your message here!"/>
            <button onclick="sendMessage($('#message').val(), ${room.id})">Send message</button>
        </div>
        <ul id="messagesList">
            <#if room.messages??>
                <#if room.messages?size != 0>
                    <#list room.messages as message>
                        <li>${message.sender.id}: ${message.message}</li>
                    </#list>
                <#else>
                    <h3>Here you will see the history</h3>
                </#if>
            </#if>
        </ul>
    </#if>
</body>
</html>