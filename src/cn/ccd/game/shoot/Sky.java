package cn.ccd.game.shoot;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**天空背景*/
public class Sky extends FlyingObject{

	private int speed;	//天空背景移动速度
	private int y2;	//天空背景第二张坐标

	/*天空背景构造器*/
	public Sky() {	

		super(World.WIDTH, World.HEIGHT, 0, 0);	//天空数据初始化
		this.speed = 1;	//天空背景移动速度初始化
		this.y2 = -this.height;	//天空第二张背景坐标生成在第一张的上面

	}

	/*重写超类方法，天空背景移动方法*/
	public void step() {

		this.y += speed;	//天空背景1根据移动速度向下移动
		this.y2 += speed;	//天空背景2根据移动速度向下移动
		
		if(y >= World.HEIGHT) {	//如果天空背景1的移动到窗口外，则让它重新回到最上方
			
			y = -World.HEIGHT;	//设定天空1背景回到最上方
			
		}
		
		if(y2 >= World.HEIGHT) {	//如果天空背景2的移动到窗口外，则让它重新回到最上方
			
			y2 = -World.HEIGHT;	//设定天空2背景回到最上方
			
		}

	}

	/*重写超类方法，获取天空图片*/
	public BufferedImage getImages() {

		return Images.sky;	//加载Images类中读取到的天空背景图片

	}

	/*重写超类方法	画天空*/
	public void paintObject(Graphics g) {

		g.drawImage(this.getImages(), this.x, this.y, null);	//画天空第一张
		g.drawImage(this.getImages(), this.x, this.y2, null);	//画天空第二张

	}

}
