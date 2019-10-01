import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;

public class client {
	
	
	public static void main(String [] args) throws EOFException{
		System.out.println("�ͻ�������...");
		String rs;
		
		Thread thread=new Thread(()->{
			try (
				Socket socket=new Socket("127.0.0.1",8080);
				DataInputStream inputStream=new DataInputStream(socket.getInputStream());
				DataOutputStream outputStream=new DataOutputStream(socket.getOutputStream());
				BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));
				){
				while(true) {
					
					
					String iString=inputStream.readUTF();
					System.out.println(iString);
					frmLogin fr=new frmLogin(iString);
					try {
						fr.handle();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
					break;
				}
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			} 
			catch (ConnectException e) {
				System.out.println("������δ������");
				}catch (IOException e) {//����������е�catch�����
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("�ͻ���ֹͣ");
		});
		thread.start();
		
		
	}


}
