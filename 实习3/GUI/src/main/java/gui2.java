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
import java.util.Random;

public class gui2 {
    String vernumber="";
    public static String getRandomNumCode(int number){
        String codeNum = "";
        int [] numbers = {0,1,2,3,4,5,6,7,8,9};
        Random random = new Random();
        for (int i = 0; i < number; i++) {
            int next = random.nextInt(10000);//目的是产生足够随机的数，避免产生的数字重复率高的问题
//    			System.out.println(next);
            codeNum+=numbers[next%10];
        }
        System.out.println(codeNum);

        return codeNum;
    }


    public void init(){

        // 主窗体
        JFrame f = new JFrame("注册");
        f.setSize(700, 500);
        f.setLocation(200, 200);
        f.setLayout(null);

        final JLabel jl_admin=new JLabel("用户名");
        jl_admin.setBounds(20, 50, 60, 50);
        f.add(jl_admin);


        JLabel jl_password=new JLabel("密码");
        jl_password.setBounds(20, 120, 60, 50);
        f.add(jl_password);

        final JLabel jl_vertify=new JLabel("验证码");
        jl_vertify.setBounds(20, 190, 60, 50);
        f.add(jl_vertify);


        JButton bt1=new JButton("注册");         //更改成loginButton
        bt1.setBounds(90, 250, 100, 50);
        f.add(bt1);


        JButton bt2=new JButton("发送验证码");
        bt2.setBounds(250, 250, 100, 50);
        f.add(bt2);

        //加入文本框
        final JTextField jtext1=new JTextField("root");
        jtext1.setBounds(150, 50, 250, 50);


        final JTextField jtext2=new JPasswordField("123456");//密码输入框
        jtext2.setBounds(150, 120, 250, 50);

        final JTextField jtext3=new JPasswordField("123456");//密码输入框
        jtext3.setBounds(150, 190, 250, 50);

        f.add(jtext1);
        f.add(jtext2);
        f.add(jtext3);


        bt1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String vert=jtext3.getText();
                if(vert.equals(vernumber)){

                    DAO d=new DAO();
                    String u=jtext1.getText();
                    String p=jtext2.getText();
                    try {
                        if(d.searchUser(u,p)){
                            //使用消息提示框输出信息
                            JOptionPane.showMessageDialog(null, "该账号已经注册", "提示", JOptionPane.PLAIN_MESSAGE);
                        }
                        else{
                            d.insertUser(u,p);
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }

                    //使用消息提示框输出信息
                    JOptionPane.showMessageDialog(null, "注册成功", "提示", JOptionPane.PLAIN_MESSAGE);
                }
                else{
                    //使用消息提示框输出信息
                    JOptionPane.showMessageDialog(null, "验证码错误", "提示", JOptionPane.PLAIN_MESSAGE);
                }

            }


        });


        bt2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                JaxWsDynamicClientFactory factory = JaxWsDynamicClientFactory.newInstance();
                Client client = factory.createClient("http://localhost:8080/ws/soap/Email?wsdl");
                String user=jtext1.getText();
                System.out.println(user);
                vernumber =getRandomNumCode(4);
                try {
                    Object[] results = client.invoke("sendEmail",user,vernumber);
                    System.out.println(results[0]);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }


        });

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
