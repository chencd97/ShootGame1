package cn.ccd.game.shoot;

import java.awt.image.BufferedImage;

/**大敌机*/
public class BigAirplane extends FlyingObject implements Enemy{
	
	private int speed;	//大敌机移动速度
	
	/*大敌机构造器*/
	public BigAirplane() {
		
		super(69, 99);	//大敌机数据初始化
		this.speed = 2;	//大敌机移动速度初始化
		
	}
	
	/*重写超类方法，大敌机移动方法*/
	public void step() {
		
		this.y += this.speed;	//大敌机根据移动速度向下移动
		
	}
	
	private int index = 1;//给getImages方法使用的变量
	/*重写超类方法，获取大敌机图片*/
	public BufferedImage getImages() {

		if(isLife()) {	//判断若活着的，直接返回bigAirplanes[0]即可
			
			return Images.bigAirplanes[0];		//加载Images类中读取到的大敌机图片
			
		}else if(isDead()) {	//若死了，返回爆炸图片1-4，第4张后返回空图片
			
			BufferedImage img = Images.bigAirplanes[index++];	//加载Images类中读取到的爆炸图片，index = 1.2.3.4
			if(index == Images.bigAirplanes.length) {	//index = 2.3.4.5，当他到5时将state状态设置为REMOVE，这样下次就不会进入到此if中
				state = REMOVE;
			}
			return img;	//返回爆炸图片1-4
			
		}
		
		return null;	//返回空图片
		
	}

	/*返回得分*/
	public int getScore() {

		return 3;
		
	}

}
