import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
private readDataBase conn;
public UserDAO() {
	
	conn=new readDataBase();
}

public ResultSet searchkpPrice( ) throws SQLException, ClassNotFoundException {
	String query="select ���̼�  from data";
	return conn.executeSelectQuery(query);	
	
}
public ResultSet searchspPrice( ) throws SQLException, ClassNotFoundException {
	String query="select ���̼� from data";
	return conn.executeSelectQuery(query);	
	
}
}
