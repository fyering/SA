import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class server {
	public static void main(String [] args) throws EOFException, ClassNotFoundException, SQLException {
		System.out.println("服务器运行...");
		UserBUS ub=new UserBUS();
		String rs=ub.handlekpData();
		System.out.println(rs);
		Thread thread=new Thread(()->{//这是lambda表达式，写在线程方法中非常方便
			
			try (
				ServerSocket serverSocket = new ServerSocket(8080);
				Socket socket=serverSocket.accept();
				
				DataInputStream inputStream=new DataInputStream(socket.getInputStream());
				DataOutputStream outputStream=new DataOutputStream(socket.getOutputStream());
				BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));
				){	
				while(true) {
					
				
					//写入输出流
					outputStream.writeUTF(rs);
					//将输出流刷新
					outputStream.flush();
					break;
					
					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("服务器停止");
		});
		thread.start();
	}

}
