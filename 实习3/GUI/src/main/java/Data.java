import java.sql.*;

public class Data {
    private java.sql.Connection  ct;
    private void  openConnection() throws ClassNotFoundException, SQLException {


        Connection conn = null;
        String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String USER = "sa";
        String PASS = "bigbang66";

        String url = "jdbc:sqlserver://localhost:1433;DatabaseName=fy";
        // 注册 JDBC 驱动
        Class.forName(JDBC_DRIVER);

        // 打开链接
        System.out.println("连接数据库...");
        conn = DriverManager.getConnection(url,USER,PASS);


        ct=conn;




    }
    public ResultSet executeSelectQuery(String _query) throws SQLException, ClassNotFoundException {
        openConnection() ;
        Statement stmt = ct.createStatement();
        ResultSet rs = stmt.executeQuery(_query);

        return rs;
    }
    public void executeInsertQuery(String _query) throws SQLException, ClassNotFoundException {
        openConnection();
        Statement stmt = ct.createStatement();
        stmt.executeUpdate(_query);




    }
    public void close() throws SQLException {
        ct.close();
    }



}
