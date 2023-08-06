<%@ page import="com.example.tpo5_aj_s24592.PrintBooks" %>
<%@ page import="javax.sql.*" %>
<%@ page import="javax.naming.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="static javax.swing.text.html.HTML.Tag.SELECT" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Wyszukiwarka Ksiazek - Wyniki</title>
</head>
<body>

<% String title = request.getParameter("title"); %>
<% String author = request.getParameter("author"); %>
<% String sql = "SELECT pozycje.isbn, autor.name AS AUTOR, wydawca.name AS WYDAWCA, pozycje.tytul, pozycje.rok, pozycje.cena " +
        "FROM pozycje " +
        "JOIN autor ON pozycje.autid = autor.autid " +
        "JOIN wydawca ON pozycje.wydid = wydawca.wydid " +
        "WHERE pozycje.tytul LIKE '%" + title + "%' AND autor.name LIKE '%" + author + "%'"; %>
<% DataSource dataSource = null; %>
<% try {
    Context init = new InitialContext();
    Context context = (Context) init.lookup("java:comp/env");
    dataSource = (DataSource) context.lookup("jdbc/ksidb");
} catch (NamingException exc) {
    throw new ServletException("Nie mogę uzyskać źródła danych", exc);
} %>

<% PrintBooks.print(request, response, dataSource, sql); %>
</body>
</html>
