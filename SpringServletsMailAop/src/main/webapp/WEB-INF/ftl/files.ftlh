<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script type="application/javascript">
        function sendFile() {
            let formData = new FormData();
            let files = ($('#file'))[0]['files'];
            [].forEach.call(files, function (file, i, files) {
                formData.append("file", file);
            });

            $.ajax({
                type: "POST",
                url: "/files",
                data: formData,
                processData: false,
                contentType: false
            })
                .done(function (response) {
                    alert(response)
                })
                .fail(function () {
                    alert('Error')
                });
        }
    </script>
</head>
<body>
<div>
    <#--    <form enctype="multipart/form-data">-->
    <#--        <input type="file" id="file" name="file" placeholder="Имя файла..." formenctype="multipart/form-data"/>-->
    <#--        <input type="button" onclick="sendFile()" placeholder="Загрузить файл"/>-->
    <#--    </form>-->
    <#--    <input type="hidden" id="file_hidden">-->
    <#--    <div class="filename"></div>-->
    <form method="POST" enctype="multipart/form-data" action="/files">
        <table>
            <tr>
                <td>File to upload:</td>
                <td><input type="file" name="file"/></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Upload"/></td>
            </tr>
        </table>
    </form>
    <#if file??>
        <div>
            I found your file! Check out:
            <div style="font-size: x-large">
                ${file}
            </div>
        </div>
    </#if>
</div>
<script
        src="https://code.jquery.com/jquery-3.4.1.js"
        integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
        crossorigin="anonymous"></script>
</body>
</html>