package com.amazonaws.samples;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.amazonaws.services.sqs.AmazonSQS;

public class main {
	
public static void main(String[] args) {
	

	
	  // 主窗体
    JFrame f = new JFrame("消息队列");

    // 主窗体设置大小
    f.setSize(400, 300);

    // 主窗体设置位置
    f.setLocation(200, 200);

    // 主窗体中的组件设置为绝对定位
    f.setLayout(null);

    // 按钮组件
    JButton b = new JButton("发送消息");
    
    // 按钮组件
    JButton d = new JButton("清空对话框");
    
 
    

    // 同时设置组件的大小和位置
    b.setBounds(50, 50, 280, 30);
    d.setBounds(300, 50, 280, 30);


    // 把按钮加入到主窗体中
    f.add(b);
    f.add(d);
  
    
    JLabel l1 = new JLabel("发送进程1");
    //文字颜色
    l1.setForeground(Color.blue);
    l1.setBounds(50, 100, 280, 30);
 
    f.add(l1);
  

    
    JLabel l5 = new JLabel("发送给客户端：");
    //文字颜色
    l5.setForeground(Color.blue);
    l5.setBounds(50, 150, 280, 30);
 
    f.add(l5);
    
 // 输入框
    final JTextField text5 = new JTextField("");
   
    text5.setBounds(300, 150, 280, 30);
   
    f.add(text5);
  
    
    
    JLabel l2 = new JLabel("接收进程1");
    //文字颜色
    l2.setForeground(Color.blue);
    l2.setBounds(50, 200, 280, 30);
    
    
   
 
    f.add(l2);
    JLabel l3 = new JLabel("接收进程2");
    //文字颜色
    l3.setForeground(Color.blue);
    l3.setBounds(50, 300, 280, 30);
 
    f.add(l3);
    
    
    JLabel l4 = new JLabel("接收进程3");
    //文字颜色
    l4.setForeground(Color.blue);
    l4.setBounds(50, 400, 280, 30);
 
    f.add(l4);
   
    // 输入框
    final JTextField text1 = new JTextField("");
   
    text1.setBounds(300, 100, 280, 30);
   
    f.add(text1);
  
    
 // 输入框
    final JTextField text2 = new JTextField("");
    
    text2.setBounds(300, 200, 280, 30);
   
    f.add(text2);
   
 // 输入框
    final JTextField text3 = new JTextField("");
    
    text3.setBounds(300, 300, 280, 30);
   
    f.add(text3);
   
 // 输入框
    final JTextField text4 = new JTextField("");
    
    text4.setBounds(300, 400, 280, 30);
   
    f.add(text4);
   
   
    
    
    
  
  

    // 关闭窗体的时候，退出程序
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // 让窗体变得可见
    f.setVisible(true);
    
    Sqsuse q=new Sqsuse(); 
    q.start();
    final AmazonSQS sqs=q.Sqs;
    final String url=q.myurl;
    
			final MyQsender s1=new MyQsender();
        	
        	final MyQReceiver r1=new MyQReceiver("1",url,sqs);
        	//final MyQReceiver r2=new MyQReceiver("2",url,sqs);
        	//final MyQReceiver r3=new MyQReceiver("3",url,sqs);
        	
    // 给按钮 增加 监听
    b.addActionListener(new ActionListener() {

        // 当按钮被点击时，就会触发 ActionEvent事件
        // actionPerformed 方法就会被执行
        public void actionPerformed(ActionEvent e) {
        	
        //	r1.subscribe(s1);
        	String listener=text5.getText();
        	s1.setOnj(listener);
        	r1.setToken(listener);
        	
        	String msg1=text1.getText();
        	
        	s1.setMsg(msg1);
        	s1.sendmsg(url, sqs);
        	
        	
        	r1.recvmsg();
        if(r1.token.equals("1")) {
        	text2.setText(r1.msg);
        	
        }
        else if(r1.token.equals("2")) {
        	text3.setText(r1.msg);
        	
        }
        else {
        	
        	text4.setText(r1.msg);
        	
        }
        	
        	
        	
        	
        	
        	
        }
    });
    
 	
// 给按钮 增加 监听
d.addActionListener(new ActionListener() {

    // 当按钮被点击时，就会触发 ActionEvent事件
    // actionPerformed 方法就会被执行
    public void actionPerformed(ActionEvent e) {
    	
    	text1.setText("");
    	text2.setText("");
    	text3.setText("");
    	text4.setText("");
    	text5.setText("");
    
    	
    	
    	
    }
});



}

}
