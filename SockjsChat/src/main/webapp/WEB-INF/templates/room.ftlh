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
            document.cookie = "path=/";

            webSocket.onmessage = function receiveMessage(response) {
                debugger;
                let data = response['data'];
                let json = JSON.parse(data);
                $('#messagesList').last().after("<li>" + json['sender'] + ': ' + json['text'] + "</li>")
            }
        }

        function sendMessage(text, roomId) {
            let message = {
                "text": text,
                "roomId": roomId,
                "sender": getCookie("USER_CHECK")
            };
            debugger;
            webSocket.send(JSON.stringify(message));
        }

        function getCookie(cname) {
            let name = cname + "=";
            let decodedCookie = decodeURIComponent(document.cookie);
            let ca = decodedCookie.split(';');
            for(let i = 0; i <ca.length; i++) {
                let c = ca[i];
                while (c.charAt(0) === ' ') {
                    c = c.substring(1);
                }
                if (c.indexOf(name) === 0) {
                    return c.substring(name.length, c.length);
                }
            }
            return "";
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