<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Elf v2</title>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script type="text/javascript">
      function get(){

          $.ajax({
            url:'questions',
            type: 'POST',
            dataType: 'json',
            contentType: 'application/json',
            success: function (data) {
              console.log('s: json: ',JSON.stringify(data))
              $('#questions').html('')
              $('#questions').html(JSON.stringify(data))
            },
            error: function (data, status, error) {
              console.log('e: json: ',data)
              console.log('status:'+ status)
              console.log('error:' + error.statusText)
              $('#questions').html('Błąd podczas odbierania danych!')
            }
          })
      }
    </script>
  </head>
  <body>
  <button onclick="get()">Pobierz</button>
  <div id="questions">Elo</div>
  </body>
</html>