package com.fy.soap;

import javax.swing.*;

public class GUI {

    public static void main(String[] args) {

        JFrame f = new JFrame("发送邮件");
        f.setSize(400, 300);
        f.setLocation(200, 200);
        f.setLayout(null);
        JButton b = new JButton("发送单份邮件");
        b.setBounds(50, 50, 280, 30);
        f.add(b);
        JButton b1=new JButton("批量发送邮件");
        b1.setBounds(50,100,280,30);
        f.add(b1);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

    }

}
