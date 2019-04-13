import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;

public class HiveJdbcClient {
    private static final String DRIVER_NAME = "org.apache.hive.jdbc.HiveDriver";

    /**
     * @param args
     * @throws SQLException
     */
    public static void main(String[] args) throws SQLException {
        try {
            Class.forName(DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.exit(1);
        }
        String principal = "hive/localhost.localdomain@EXAMPLE.COM";
        String kerberosAuthType = "fromSubject";
        //replace "hive" here with the name of the user the queries should run as
        Connection con = DriverManager.getConnection("jdbc:hive2://localhost:10000/default",principal,kerberosAuthType);
        Statement stmt = con.createStatement();
        String tableName = "cpuobjwithts";

        // select * query
        String sql = "select * from " + tableName;
        System.out.println("Running: " + sql);
        ResultSet res = stmt.executeQuery(sql);
        while (res.next()) {
            System.out.println(String.valueOf(res.getInt(1)) + "\t" + res.getString(2));
        }

    }
}