import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class readDataBase {
	private java.sql.Connection  ct;
	void dbconnect() throws SQLException {
		
		String userName = "fy";  //Ĭ���û���
		 String userPwd = "bigbang66";   //����
		 String url="jdbc:sqlserver://mydatabase.c7wpej4d1lwp.us-east-1.rds.amazonaws.com:1433;DatabaseName=mydatabase";
		  ct = DriverManager.getConnection(url, userName, userPwd);
		


		
	}
	 private java.sql.Connection openConnection() throws SQLException, ClassNotFoundException {
		 
		
			
		 String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";//SQL���ݿ�����
	     String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=fy";//����Դ  ��������ע�������ּ��ػ����������ݿ�ʧ��һ���������������
	     String Name="sa";
	     String Pwd="bigbang66";
	 
	   Class.forName(driverName);
	   	ct=DriverManager.getConnection(dbURL,Name,Pwd);
	   System.out.println("�������ݿ�ɹ�");
			
		 
		  return ct;
	 }
 public ResultSet executeSelectQuery(String _query) throws SQLException, ClassNotFoundException {
	 		//����һ������sql���Ķ���
			PreparedStatement ps = null;
			//����һ�����ڱ��������Ķ���
			ResultSet rs = null;
			//������
			java.sql.Connection conn=openConnection();
			//��ѯ����
			ps = conn.prepareStatement(_query);
			//�ռ������
			rs = ps.executeQuery();
			

	 return rs;
 }
	
}
