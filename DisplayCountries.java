package week7;

import java.sql.*;
import java.util.Scanner;

public class DisplayCountries {

    public static void main(String[] args) {

        String url = "jdbc:mariadb://localhost:3306/nation";
        String user = "root";
        String password = "1234";

        Scanner input = new Scanner(System.in);

        System.out.print("Enter a language: ");
        String language = input.nextLine();

        try {
            Class.forName("org.mariadb.jdbc.Driver");

            Connection conn = DriverManager.getConnection(url, user, password);

            String sql =
                "SELECT c.name, cl.official " +
                "FROM countries c " +
                "JOIN country_languages cl ON c.country_id = cl.country_id " +
                "JOIN languages l ON cl.language_id = l.language_id " +
                "WHERE l.language = '"+ language+"'";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, language);

            ResultSet rs = stmt.executeQuery();

            System.out.println("\nCountries where " + language + " is spoken:\n");

            while (rs.next()) {

                String country = rs.getString("name");
                boolean official = rs.getBoolean("official");

                if (official)
                    System.out.println(country + " (Official)");
                else
                    System.out.println(country + " (Unofficial)");
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        input.close();
    }
}