package cn.ccd.game.shoot;

import java.awt.image.BufferedImage;
import java.util.Random;

/**小蜜蜂(奖励机)*/
public class Bee extends FlyingObject implements Award{
	
	private int xSpeed;	//小蜜蜂因为会左右移动，所以有两个移动速度，这个是X轴的移动速度
	private int ySpeed;	//这个是Y轴的移动速度
	private int ranX;	//移动方向指示器
	private int awardType;	//奖励值
	
	/*小蜜蜂(奖励机)构造器*/
	public Bee() {
		
		super(60, 50);	//小蜜蜂数据初始化
		Random ran = new Random();	//申请随机权限
		ranX = ran.nextInt(2);	//设置随机移动方向
		this.xSpeed = 2;	//小蜜蜂X轴的移动速度初始化
		this.ySpeed = 2;	//小蜜蜂Y轴的移动速度初始化
		this.awardType = ran.nextInt(101);	//将awardType的值随机赋值为0~100
		
	}
	
	/*重写超类方法，小蜜蜂移动方法*/
	public void step() {
		
		if(ranX == 0) {	//判断初始移动方向
			
			this.x += this.xSpeed;	//小蜜蜂向右移动
			
		}else {
			
			this.x -= this.xSpeed;	//小蜜蜂向左移动
			
		}
		this.y += ySpeed;	//小蜜蜂根据移动速度向下移动
		
		/*判断小蜜蜂是否碰到窗口边缘*/
		if(this.x <= 0 || this.x >= World.WIDTH - this.width) {
			
			this.xSpeed *= -1;	//将x轴移动方向设置为反方向
			
		}
		
	}
	
	private int index = 1;//给getImages方法使用的变量
	/*重写超类方法，获取小蜜蜂图片*/
	public BufferedImage getImages() {

		if(isLife()) {	//判断若活着的，直接返回bees[0]即可
			
			return Images.bees[0];		//加载Images类中读取到的小蜜蜂图片
			
		}else if(isDead()) {	//若死了，返回爆炸图片1-4，第4张后返回空图片
			
			BufferedImage img = Images.bees[index++];	//加载Images类中读取到的爆炸图片，index = 1.2.3.4
			if(index == Images.bees.length) {	//index = 2.3.4.5，当他到5时将state状态设置为REMOVE，这样下次就不会进入到此if中
				state = REMOVE;
			}
			return img;	//返回爆炸图片1-4
			
		}
		
		return null;	//返回空图片
		
	}

	/*返回奖励类型*/
	public int getAwardType() {

		return awardType;
		
	}

}
