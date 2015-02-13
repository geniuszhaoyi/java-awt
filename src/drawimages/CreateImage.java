package drawimages;
import java.awt.*;   
import java.awt.image.*;   
import java.io.*;   

import javax.imageio.*;   
import javax.swing.*;

import java.awt.font.*;   
import java.awt.geom.*;   
  
public class CreateImage    
{   
    public static void main(String[] args) throws Exception   
    {   
        File file = new File("/home/zhaoyi/image.png");   
        
        int width = 400;
        int height = 300;
        // 创建BufferedImage对象
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        // 获取Graphics2D

        int alpha = 0;
        
        for (int i = image.getMinY(); i < image.getHeight(); i++) {
        	for (int j = image.getMinX(); j < image.getWidth(); j++) {
        		int rgb = image.getRGB(j, i);
        		int key=((i+j)/30)%4;
        		if(key==0) rgb= 0xffff0000; 
        		if(key==1) rgb= 0xff00ff00; 
        		if(key==2) rgb= 0xff0000ff; 
        		if(key==3) rgb= ((alpha+1)<<24) | 0x00ffffff;
        		image.setRGB(j, i, rgb); 
        	}
        }
        // 保存文件
        ImageIO.write(image, "png", file);   
        
        JFrame frame = new JFrame("JFrameDemo");  
        JLabel jlb=new JLabel(new ImageIcon("/home/zhaoyi/background.png"));
   
        JPanel pb=new JPanel();
        pb.add(jlb);
        frame.setContentPane(pb);
   
        JPanel pt = new JPanel();  
        pt.setLayout(new BorderLayout());  
        pt.setSize(400,300);
        pt.setOpaque(false);
        JLabel jl=new JLabel(new ImageIcon(image));
        pt.add(jl);
        jlb.add(pt);
   
        //frame.pack();  
        frame.setSize(400,300);
        frame.setVisible(true);
        
        jl=new JLabel(new ImageIcon("/home/zhaoyi/background.png"));
        frame.setVisible(true);
    }   
}    