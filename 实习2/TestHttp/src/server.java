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
		System.out.println("����������...");
		UserBUS ub=new UserBUS();
		String rs=ub.handlekpData();
		System.out.println(rs);
		Thread thread=new Thread(()->{//����lambda���ʽ��д���̷߳����зǳ�����
			
			try (
				ServerSocket serverSocket = new ServerSocket(8080);
				Socket socket=serverSocket.accept();
				
				DataInputStream inputStream=new DataInputStream(socket.getInputStream());
				DataOutputStream outputStream=new DataOutputStream(socket.getOutputStream());
				BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));
				){	
				while(true) {
					
				
					//д�������
					outputStream.writeUTF(rs);
					//�������ˢ��
					outputStream.flush();
					break;
					
					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("������ֹͣ");
		});
		thread.start();
	}

}
