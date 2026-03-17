package week7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

class Sales {
    int orderNumber;
    String customerName;
    String customerCity;
    String salesmanName;
    double amount;
    double commissionAmount;

    public Sales(int orderNumber, String customerName, String customerCity,
                 String salesmanName, double amount, double commissionAmount) {
        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.customerCity = customerCity;
        this.salesmanName = salesmanName;
        this.amount = amount;
        this.commissionAmount = commissionAmount;
    }

    @Override
    public String toString() {
        return "Sales{" +
                "orderNumber=" + orderNumber +
                ", customerName='" + customerName + '\'' +
                ", customerCity='" + customerCity + '\'' +
                ", salesmanName='" + salesmanName + '\'' +
                ", amount=" + amount +
                ", commissionAmount=" + commissionAmount +
                '}';
    }
}

public class DBConnection {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java DBConnection <username> <password> <database>");
            return;
        }

        String username = args[0];
        String password = args[1];
        String database = args[2];

        String url = "jdbc:mariadb://localhost:3306/" + database;

        ArrayList<Sales> salesList = new ArrayList<>();

        String query =
                "SELECT o.order_no, c.customer_name, c.city AS customer_city, " +
                "       s.name AS salesman_name, o.purchase_amt, " +
                "       (o.purchase_amt * s.commission) AS commission_amount " +
                "FROM orders o " +
                "JOIN customer c ON o.customer_id = c.customer_id " +
                "JOIN salesman s ON o.salesman_id = s.salesman_id " +
                "ORDER BY o.order_no";

        try {
            Class.forName("org.mariadb.jdbc.Driver");

            try (Connection conn = DriverManager.getConnection(url, username, password);
                 PreparedStatement pstmt = conn.prepareStatement(query);
                 ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    Sales sale = new Sales(
                            rs.getInt("order_no"),
                            rs.getString("customer_name"),
                            rs.getString("customer_city"),
                            rs.getString("salesman_name"),
                            rs.getDouble("purchase_amt"),
                            rs.getDouble("commission_amount")
                    );
                    
                    salesList.add(sale);
                }

                System.out.println("Connected successfully to database: " + database);
                System.out.println("Sales records retrieved:\n");

                for (Sales sale : salesList) {
                    System.out.println(sale);
                }
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