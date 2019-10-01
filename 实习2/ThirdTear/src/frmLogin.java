import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class frmLogin extends JFrame {
int res[]=new int[20];
public frmLogin(String rs) {
	for(int i=0;i<20;i++) {
		
		res[i]=0;
	}
	List<String> result = Arrays.asList(rs.split(","));
	int size=result.size();
	for(int i=0;i<size;i++) {
		int index=(int) Math.floor(Double.valueOf(result.get(i)));
		res[index]+=1;
		
	}
	
}
public void ShowImage() {
	JPanel panel=new JPanel(new BorderLayout());
	
	
	String urlString="LineChart.jpeg";
	JLabel label=new JLabel(new ImageIcon(urlString));
	
	

	panel.add(label,BorderLayout.CENTER);
	
	this.getContentPane().setLayout(new BorderLayout());
	this.getContentPane().add(panel,BorderLayout.CENTER);
	
	
	this.setSize(400, 300);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setTitle("��ʾͼ��");
	this.setVisible(true);
}
public  void handle() throws SQLException, ClassNotFoundException, IOException {
	
	
	 DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();
	for(int i=0;i<20;i++) {
		
		System.out.println(res[i]);
		String col=String.valueOf(i)+"-"+String.valueOf(i+1);
		 line_chart_dataset.addValue( res[i] , "���̼�" , col );
		
	}    

     JFreeChart lineChartObject = ChartFactory.createLineChart(
        "���̼�����","price",
        "Count",
        line_chart_dataset,PlotOrientation.VERTICAL,
        true,true,false);

     int width = 640; /* Width of the image */
     int height = 480; /* Height of the image */ 
     File lineChart = new File( "LineChart.jpeg" ); 
     ChartUtilities.saveChartAsJPEG(lineChart ,lineChartObject, width ,height);//ԭ�ĳ��ԡ��װٽ̡̳�����ҵת������ϵ���߻����Ȩ������ҵ�뱣��ԭ�����ӣ�https://www.yiibai.com/jfreechart/jfreechart_line_chart.html
     
      ShowImage();
	
	
}
}
