package drawimages;
import java.awt.*;   
import java.awt.image.*;   
import java.io.*;   

import javax.imageio.*;   
import javax.swing.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;   

public class CreateImage{   
	public static int imgWidth=722;
	public static int imgHeight=826;
	
	public static void main(String[] args) throws Exception{
		// 创建BufferedImage对象
		// 获取Graphics2D
		/*
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
		*/
		BufferedImage ni = ImageIO.read(new File("IMG_1011.PNG"));
		BufferedImage image = new BufferedImage(ni.getWidth(), ni.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
		for (int i = ni.getMinY(); i < ni.getWidth(); i++) {
			for (int j = ni.getMinX(); j < ni.getHeight(); j++) {
				image.setRGB(i, j, ni.getRGB(i, j));
			}
		}
		
		JFrame frame = new JFrame("JFrameDemo");  
		JLabel jlb=new JLabel(new ImageIcon("IMG_1012.JPG"));

		JPanel pb=new JPanel();
		pb.add(jlb);
		frame.setContentPane(pb);

		JPanel pt = new JPanel();  
		pt.setLayout(new BorderLayout());  
		pt.setSize(imgWidth,imgHeight);
		pt.setOpaque(false);
		JLmask jl=new JLmask(image);
		pt.add(jl);
		jlb.add(pt);

		//frame.pack();  
		frame.setSize(imgWidth,imgHeight);
		frame.setVisible(true);
	}   


	static class JLmask extends JLabel{
		BufferedImage image;
		public JLmask(BufferedImage bi){
			super(new ImageIcon(bi));
			image=bi;
			addMouseMotionListener(new MouseMotionAdapter(){
				public void mouseDragged(MouseEvent e){
					int x = e.getX();
					int y = e.getY();
					int mini=image.getMinX();
					int minj=image.getMinY();
					int maxi=image.getHeight();
					int maxj=image.getWidth();
					for(int i=x-50;i<x+50;i++){
						for(int j=y-50;j<y+50;j++){
							if(i>mini && i<maxi && j>minj && j<maxj){
								int rgb=image.getRGB(i,j);
								int alpha=(rgb>>24) & 0xff;
								//if(alpha==0) alpha=0xff;
								System.out.println(alpha);
								int d2=(i-x)*(i-x)+(j-y)*(j-y);
								if(d2<=400) image.setRGB(i, j, ((alpha/4 & 0xff)<<24) | (rgb & 0x00ffffff));
								if(d2<=1600) image.setRGB(i, j, ((alpha/2 & 0xff)<<24) | (rgb & 0x00ffffff));
								if(d2<=2500) image.setRGB(i, j, ((alpha*3/4 & 0xff)<<24) | (rgb & 0x00ffffff));
							}
						}
					}
					setIcon(new ImageIcon(image));
				}

			});
		}

	}
}