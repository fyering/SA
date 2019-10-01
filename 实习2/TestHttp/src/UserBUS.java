import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class UserBUS {
	private UserDAO _userdao;
	public UserBUS() {
		
		_userdao=new UserDAO();
		
		
	}
	public Vector<UserVO>getkp() throws SQLException, ClassNotFoundException{
		
		 Vector<UserVO> _uservos=new Vector<UserVO>();
		 ResultSet rt=null;
		 rt=_userdao.searchkpPrice();
		 while(rt.next()) {
			 UserVO vo=new UserVO();
			 vo.setkp(rt.getString("开盘价"));
			
			_uservos.add(vo);
		 
		 }
	return _uservos;
	}
	public Vector<UserVO>getsp() throws SQLException, ClassNotFoundException{
		
		 Vector<UserVO> _uservos=new Vector<UserVO>();
		 ResultSet rt=null;
		 rt=_userdao.searchspPrice();
		 while(rt.next()) {
			 UserVO vo=new UserVO();
			 vo.setsp(rt.getString("收盘价"));
			_uservos.add(vo);
		 
		 }
	return _uservos;
		
	}
	public String handlekpData() throws ClassNotFoundException, SQLException {
		
		 Vector<UserVO>uvs=new Vector<UserVO>();
		
		uvs=getkp();
		String rs="";
		int size=uvs.size();
		
		for(int i=0;i<size;i++) {
			
			rs=rs+uvs.get(i).getkp()+",";
			
		}
		return rs;
		
	}
	public String handlespData() throws ClassNotFoundException, SQLException {
		
		 Vector<UserVO>uvs=new Vector<UserVO>();
		
		uvs=getsp();
		String rs="";
		int size=uvs.size();
		
		for(int i=0;i<size;i++) {
			
			rs=rs+uvs.get(i).getsp()+",";
			
		}
		return rs;
	}

}
