package com.example.zad;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PrintBooks {
    public static void print(HttpServletRequest request, HttpServletResponse response, DataSource dataSource,String sql) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            if (!resultSet.next()) {
                out.print("Brak wyników\n\n");
            }
            else {
                out.println("<html><body>");
                out.println("<table border='1'>");
                out.println("<tr>");
                out.println("<th>ISBN</th>");
                out.println("<th>Tytuł</th>");
                out.println("<th>Autor</th>");
                out.println("<th>Wydawca</th>");
                out.println("<th>Rok</th>");
                out.println("<th>Cena</th>");
                out.println("</tr>");


                while (resultSet.next()) {
                    String isbn = resultSet.getString("ISBN");
                    String aut = resultSet.getString("AUTOR");
                    String pub = resultSet.getString("WYDAWCA");
                    int year = resultSet.getInt("ROK");
                    String title = resultSet.getString("TYTUL");
                    double price = resultSet.getDouble("CENA");

                    out.println("<tr>");
                    out.println("<td>" + isbn + "</td>");
                    out.println("<td>" + title + "</td>");
                    out.println("<td>" + aut + "</td>");
                    out.println("<td>" + pub + "</td>");
                    out.println("<td>" + year + "</td>");
                    out.println("<td>" + price + "</td>");
                    out.println("</tr>");
                }
                out.println("</table>");
                out.println("</body></html>");
            }

            out.println("<a href=\"index.jsp\">Powrot do strony glownej</a>");

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
