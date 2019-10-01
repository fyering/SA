import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class readDataBase {
	private java.sql.Connection  ct;
	void dbconnect() throws SQLException {
		
		String userName = "fy";  //默认用户名
		 String userPwd = "bigbang66";   //密码
		 String url="jdbc:sqlserver://mydatabase.c7wpej4d1lwp.us-east-1.rds.amazonaws.com:1433;DatabaseName=mydatabase";
		  ct = DriverManager.getConnection(url, userName, userPwd);
		


		
	}
	 private java.sql.Connection openConnection() throws SQLException, ClassNotFoundException {
		 
		
			
		 String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";//SQL数据库引擎
	     String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=fy";//数据源  ！！！！注意若出现加载或者连接数据库失败一般是这里出现问题
	     String Name="sa";
	     String Pwd="bigbang66";
	 
	   Class.forName(driverName);
	   	ct=DriverManager.getConnection(dbURL,Name,Pwd);
	   System.out.println("连接数据库成功");
			
		 
		  return ct;
	 }
 public ResultSet executeSelectQuery(String _query) throws SQLException, ClassNotFoundException {
	 		//创建一个发送sql语句的对象
			PreparedStatement ps = null;
			//创建一个用于保存结果集的对象
			ResultSet rs = null;
			//打开连接
			java.sql.Connection conn=openConnection();
			//查询操作
			ps = conn.prepareStatement(_query);
			//收集结果集
			rs = ps.executeQuery();
			

	 return rs;
 }
	
}
