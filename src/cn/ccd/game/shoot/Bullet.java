package cn.ccd.game.shoot;

import java.awt.image.BufferedImage;

/**子弹*/
public class Bullet extends FlyingObject{
	
	private int speed;	//子弹移动速度
	
	/*子弹构造器*/
	public Bullet(int x, int y){
		
		super(8, 14, x, y);	//子弹数据初始化(由于子弹是随着英雄机移动而改变生成位置，所以暂时不定义)
		this.speed = 8;	//子弹移动速度初始化
		
	}
	
	/*重写超类方法，子弹移动方法*/
	public void step() {
		
		this.y -= this.speed;	//子弹向上移动
		
	}
	
	/*重写超类方法，获取子弹图片*/
	public BufferedImage getImages() {

		if(isLife()) {	//判断若活着的，直接返回bullet即可
			
			return Images.bullet;		//加载Images类中读取到的子弹图片
			
		}else if(isDead()) {	//若死了，直接标记为删除，并返回空图片
			
			state = REMOVE;	//将state值改为REMOVE
			
		}
		
		return null;	//返回空图片
		
	}
	
	/*判断子弹是否出界*/
	public boolean outOfBounds() {
		
		return this.y < -this.height;	//如果子弹的Y轴数值超过了窗口的最大值+他本身的高度 (负的，因为在子弹会在窗口上方出界),则判断为出界
		
	}
	
}
