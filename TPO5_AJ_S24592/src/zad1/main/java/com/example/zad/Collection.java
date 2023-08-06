package com.example.zad;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@WebServlet(name = "zbiorKsiazek", value = "/zbior-ksiazek")
public class Collection extends HttpServlet {
    private DataSource dataSource;

    public void init() throws ServletException {
        try {
            Context init = new InitialContext();
            Context contx = (Context) init.lookup("java:comp/env");
            dataSource = (DataSource) contx.lookup("jdbc/ksidb");
        } catch (NamingException exc) {
            throw new ServletException("Nie mogę uzyskać źródła java:comp/env/jdbc/ksidb", exc);
        }
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String query = "SELECT pozycje.isbn, autor.name AS AUTOR, wydawca.name AS WYDAWCA, pozycje.tytul, pozycje.rok, pozycje.cena " +
                "FROM pozycje" +
                " JOIN autor ON pozycje.autid = autor.autid" +
                " JOIN wydawca ON pozycje.wydid = wydawca.wydid";

        PrintBooks.print(request, response, dataSource, query);
    }

}