import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class gui {
    private static int count=0;
    private static JButton bt1;//登陆按钮
    private static JButton bt2;//忘记密码按钮
    private static JLabel jl_1;//登录的版面
    private static JFrame jf_1;//登陆的框架
    private static JTextField jtext1;//用户名
    private static JPasswordField jtext2;//密码
    private static JLabel jl_admin;
    private static JLabel jl_password;
    public void init() {

        Font font =new Font("黑体", Font.PLAIN, 20);//设置字体
        jf_1=new JFrame("登陆界面");
        jf_1.setSize(450, 400);
        //给登陆界面添加背景图片
        ImageIcon background =new ImageIcon("bg.jpg");//test为当前的类，jpanel_pci.jpg自动添加到当前项目的包中
        background.setImage(
                background.getImage().
                        getScaledInstance(background.getIconWidth(),background.getIconHeight(), Image.SCALE_DEFAULT));


        jl_1=new JLabel();
        jl_1.setIcon(background);


        jl_admin=new JLabel("用户名");
        jl_admin.setBounds(20, 50, 60, 50);
        jl_admin.setFont(font);

        jl_password=new JLabel("密码");
        jl_password.setBounds(20, 120, 60, 50);
        jl_password.setFont(font);

        bt1=new JButton("登陆");         //更改成loginButton
        bt1.setBounds(90, 250, 100, 50);
        bt1.setFont(font);

        bt2=new JButton("注册");
        bt2.setBounds(250, 250, 100, 50);
        bt2.setFont(font);

        //加入文本框
        jtext1=new JTextField("root");
        jtext1.setBounds(150, 50, 250, 50);
        jtext1.setFont(font);

        jtext2=new JPasswordField("123456");//密码输入框
        jtext2.setBounds(150, 120, 250, 50);
        jtext2.setFont(font);

        jl_1.add(jtext1);
        jl_1.add(jtext2);

        jl_1.add(jl_admin);
        jl_1.add(jl_password);
        jl_1.add(bt1);
        jl_1.add(bt2);

        jf_1.add(jl_1);
        jf_1.setVisible(true);
        jf_1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf_1.setLocation(300,400);

       bt1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usern=jtext1.getText();
                String pw=jtext2.getText();
                DAO d=new DAO();
                try {
                    if( d.searchUser(usern,pw)){
                        gui1 g1=new gui1();
                        g1.init();
                    }
                    else{

                        //使用消息提示框输出信息
                        JOptionPane.showMessageDialog(null, "请先注册", "提示", JOptionPane.PLAIN_MESSAGE);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

            }
        });

        bt2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               gui2 g2=new gui2();
               g2.init();
            }
        });


    }

    public static void main(String[] args) {
       gui g=new gui();
       g.init();

    }
}
