<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Biblioteka Online</title>
</head>
<body>
<h1>Wyszukiwarka Książek</h1>

<form action="result.jsp" method="GET">
    <label for="title">Tytuł:</label>
    <input type="text" id="title" name="title">

    <label for="author">Autor:</label>
    <input type="text" id="author" name="author">

    <input type="submit" value="Szukaj">
</form>
<br/>
<a href="zbior-ksiazek">Zbior ksiazek</a>
</body>
</html>