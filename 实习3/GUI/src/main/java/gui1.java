import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class gui1 {
    public void init(){

        // 主窗体
        JFrame f = new JFrame("批量发送邮件");
        f.setSize(700, 500);
        f.setLocation(200, 200);
        f.setLayout(null);
        JButton b=new JButton("批量发送邮件");
        b.setBounds(50,50,280,30);
        f.add(b);
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                DAO data=new DAO();
                try {
                    ResultSet rt=data.searchUser1();
                    List<String>list=new ArrayList<String>();
                    while(rt.next()) {
                       String user=rt.getString("username");
                       list.add(user);
                    }
                     rt.close();
                    JaxWsDynamicClientFactory factory = JaxWsDynamicClientFactory.newInstance();
                    Client client = factory.createClient("http://localhost:8080/ws/soap/Email?wsdl");
                    try {
                        Object[] results = client.invoke("sendEmailBatch",list,"订阅消息");
                        System.out.println(results[0]);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

            }
        });

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);



    }




}
