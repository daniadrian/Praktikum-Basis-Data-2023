package praktikum15;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class praktikum15 {

    public static void main(String[] args) {
        try {
            String connectionUrl = "jdbc:sqlserver://" + SQLTaker.instanceName +
                    "database=" + SQLTaker.dbName +
                    "user=" + SQLTaker.id +
                    "password=" + SQLTaker.pass +
                    "encrypt=false;" +
                    "trustServerCertificate=false;" +
                    "loginTimeout=10;";

            try (Connection connection = DriverManager.getConnection(connectionUrl)) {

                if (connection != null) {
                    System.out.println("Koneksi berhasil!");
                    praktikum15 praktikum = new praktikum15();
                    praktikum.statementQuery(connection);
                }

            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Koneksi gagal: " + e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void statementQuery(Connection conn) throws SQLException {
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT m.NIM, m.Nama, n.Nilai FROM Mahasiswa m JOIN Nilai n ON n.NIM = m.NIM");
        System.out.println("===============================================");
        System.out.println("|       NIM       |            NAMA            |   NILAI   |");
        System.out.println("===============================================");
        while (rs.next()) {
            String nim = rs.getString(1);
            String nama = rs.getString(2);
            Float nilai = rs.getFloat(3);
            System.out.printf("| %-15s | %-26s | %7s   |%n", nim, nama, nilai);
        }
        System.out.println("===============================================");
    }
}
