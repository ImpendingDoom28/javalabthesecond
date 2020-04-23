<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Rooms</title>
</head>
<body>
    <form method="post" action="/rooms">
        <input type="text" name="name" placeholder="Enter room name" required/>
        <input type="submit" value="Create">
    </form>
    <#if rooms?? && rooms?size != 0>
        <#list rooms as room>
            <div>
                <h2 style="display: inline-block">Room name: </h2><span>${room.name} </span>
                <h2 style="display: inline-block">Users online: </h2><span><#if room.clients?size != 0> 0 <#else> ${room.clients?size} </#if></span>
                <a href="rooms/${room.id}">Enter room</a>
            </div>
        </#list>
    <#else>
        <h2>No rooms created yet :(</h2>
    </#if>
</body>
</html>