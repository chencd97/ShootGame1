package cn.ccd.game.shoot;

import java.awt.image.BufferedImage;
import java.util.Random;

/**小敌机*/
public class Airplane extends FlyingObject implements Enemy{

	private int xSpeed;	//小敌机X轴的移动速
	private int ySpeed;	//小敌机Y轴的移动速
	private int ranX;	//移动方向指示器
	
	/*小敌机构造器*/
	public Airplane() {
		
		super(49, 36);	//小敌机数据初始化
		Random ran = new Random();	//申请随机权限
		ranX = ran.nextInt(2);	//设置随机移动方向
		this.xSpeed = 1;	//小敌机X轴的移动速初始化
		this.ySpeed = 2;	//小敌机Y轴的移动速初始化
	}
	
	/*重写超类方法，小敌机移动方法*/
	public void step() {

		if(ranX == 0) {	//判断初始移动方向
			
			this.x += this.xSpeed;	//小敌机向右移动
			
		}else {
			
			this.x -= this.xSpeed;	//小敌机向左移动
			
		}
		this.y += this.ySpeed;	//小敌机根据移动速度向下移动
		
		/*判断小敌机是否碰到窗口边缘*/
		if(this.x <= 0 || this.x >= World.WIDTH - this.width) {
			
			this.xSpeed *= -1;	//将x轴移动方向设置为反方向
			
		}
		
	}
	
	private int index = 1;//给getImages方法使用的变量
	/*重写超类方法，获取小敌机图片*/
	public BufferedImage getImages() {

		if(isLife()) {	//判断若活着的，直接返回airplanes[0]即可
			
			return Images.airplanes[0];		//加载Images类中读取到的小敌机图片
			
		}else if(isDead()) {	//若死了，返回爆炸图片1-4，第4张后返回空图片
			
			BufferedImage img = Images.airplanes[index++];	//加载Images类中读取到的爆炸图片，index = 1.2.3.4
			if(index == Images.airplanes.length) {	//index = 2.3.4.5，当他到5时将state状态设置为REMOVE，这样下次就不会进入到此if中
				state = REMOVE;
			}
			return img;	//返回爆炸图片1-4
			
		}
		
		return null;	//返回空图片
		
	}

	/*返回得分*/
	public int getScore() {
		
		return 1;
		
	}
	
}
