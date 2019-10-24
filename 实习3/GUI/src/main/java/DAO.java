import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO {
    private Data conn;
    public DAO() {

        conn=new Data();
    }

    public boolean searchUser(String user,String ps) throws SQLException, ClassNotFoundException {
        String query="select *  from user_info";
        ResultSet rs =conn.executeSelectQuery(query);
        boolean flag=false;
        while(rs.next())
        {

            String name=rs.getString("username");
            String pw=rs.getString("password");
            if(user.equals(name)&&ps.equals(pw)){
                flag=true;
                break;
            }
        }
        rs.close();
        conn.close();

        return flag;

    }
    public void  insertUser(String user,String pw ) throws SQLException, ClassNotFoundException {
        String sql="insert into user_info (username,password) values('" + user + "','" + pw + "')";
         conn.executeInsertQuery(sql);
         conn.close();

    }
    public ResultSet searchUser1() throws SQLException, ClassNotFoundException {
        String query="select *  from user_info";
        ResultSet rs =conn.executeSelectQuery(query);

        rs.close();
        conn.close();

        return rs;

    }

}
