<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Support</title>
    <script
            src="https://code.jquery.com/jquery-3.4.1.js"
            integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
            crossorigin="anonymous"></script>
    <script>
        function sendMessage(pageId, message) {
            let body = {
                pageId,
                message
            };
            
            $.ajax({
                url: "/messages",
                method: "POST",
                data: JSON.stringify(body),
                contentType: "application/json",
                dataType: "json",
                complete: function () {
                    if(message === 'Login') {
                        receiveMessage(pageId);
                    }
                }
            })
        }

        function receiveMessage(pageId) {
            $.ajax({
                url: "/messages?pageId=" + pageId,
                method: "GET",
                dataType: "json",
                contentType: "application/json",
                success: function (response) {
                    $('#messages').last().after('<tr><td>' + response[0]['pageId'] + '</td><td>' + response[0]['text'] + '</td></tr>');
                    receiveMessage(pageId);
                }
            })
        }
    </script>
</head>
<body onload="sendMessage('${pageId}', 'Login')">
    <h1>Chat with support</h1>
    <h3>Here you can chat with support in order to ask some questions!!!</h3>
    <input type="text" placeholder="Enter your message here" id="message"/>
    <button onclick="sendMessage('${pageId}', $('#message').val())">Send</button>
    <table>
        <thead>
        <tr>
            <td>Said:</td>
            <td>Message:</td>
        </tr>
        </thead>
        <tbody>
            <#if messages??>
                <#if messages?size != 0>
                    <#list messages as message>
                        <#if message?is_last>
                            <tr id="messages">
                                <td>${message.pageId}</td>
                                <td>${message.message}</td>
                            </tr>
                        <#else>
                            <tr>
                                <td>${message.pageId}</td>
                                <td>${message.message}</td>
                            </tr>
                        </#if>
                    </#list>
                <#else>
                    <tr id="messages">
                        <td>New messages will appear below</td>
                    </tr>
                </#if>
            </#if>
        </tbody>
    </table>
</body>
</html>