package week7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.function.Function;


class SalesPerson {
    private String name;
    private String city;
    private double commission;
    private double totalSales;

    public SalesPerson(String name, String city, double commission, double totalSales) {
        this.name = name;
        this.city = city;
        this.commission = commission;
        this.totalSales = totalSales;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public double getCommission() {
        return commission;
    }

    public double getTotalSales() {
        return totalSales;
    }

    public double getTotalCommissionAmount() {
        return totalSales * commission;
    }

    public double getTotalEarnings() {
	//total earnings for the sales person 
        return totalSales + getTotalCommissionAmount();
    }

    @Override
    public String toString() {
        return "SalesPerson{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", commission=" + commission +
                ", totalSales=" + totalSales +
                '}';
    }
}
public class week12 {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java DBConnection <username> <password> <database>");
            return;
        }

        String username = args[0];
        String password = args[1];
        String database = args[2];

        String url = "jdbc:mariadb://localhost:3306/" + database;

        ArrayList<SalesPerson> salesPersonList = new ArrayList<>();

        String query =
                "SELECT \r\n"
                + "                s.name,\r\n"
                + "                s.city,\r\n"
                + "                s.commission,\r\n"
                + "                COALESCE(SUM(o.purchase_amt), 0) AS total_sales\r\n"
                + "            FROM salesman s\r\n"
                + "            LEFT JOIN orders o ON s.salesman_id = o.salesman_id\r\n"
                + "            GROUP BY s.salesman_id, s.name, s.city, s.commission\r\n"
                + "            ORDER BY s.name";

        try {
            Class.forName("org.mariadb.jdbc.Driver");

            try (Connection conn = DriverManager.getConnection(url, username, password);
                 PreparedStatement pstmt = conn.prepareStatement(query);
                 ResultSet rs = pstmt.executeQuery()) {

        	while (rs.next()) {
                    SalesPerson sp = new SalesPerson(
                        rs.getString("name"),
                        rs.getString("city"),
                        rs.getDouble("commission"),
                        rs.getDouble("total_sales")
                    );
                   salesPersonList.add(sp);
                    
                }
        	 System.out.println("\n=== SalesPersons Names and Total Earnings ===");
                 System.out.printf("%-20s %-20s%n", "Name", "Total Earnings");
                 System.out.println("------------------------------------------------");

                 salesPersonList.stream()
                     .forEach(sp -> System.out.printf(
                         "%-20s $%-19.2f%n",
                         sp.getName(),
                         sp.getTotalEarnings()
                     ));

                 System.out.println("\n=== SalesPersons Names and Total Commissions ===");
                 System.out.printf("%-20s %-20s%n", "Name", "Total Commission");
                 System.out.println("------------------------------------------------");

                 salesPersonList.stream()
                     .forEach(sp -> System.out.printf(
                         "%-20s $%-19.2f%n",
                         sp.getName(),
                         sp.getTotalCommissionAmount()
                     ));

                rs.close();
                pstmt.close();
                conn.close();
            }

        } catch (ClassNotFoundException e) {
            System.out.println("MariaDB JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database connection/query error.");
            e.printStackTrace();
        }
    }
}