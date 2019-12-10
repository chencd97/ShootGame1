package cn.ccd.game.shoot;

import java.awt.image.BufferedImage;

/**英雄机*/
public class Hero extends FlyingObject{

	private int life;	//生命数量
	private int doubleFire;	//双倍火力值
	private int superFire;	//超级火力值

	/*英雄机构造器*/
	public Hero() {

		super(97, 124, World.WIDTH/2-97/2, World.HEIGHT-300);	//英雄机数据初始化
		this.life = 3;	//设置默认生命数3条
		this.doubleFire = 0;	//设置双倍火力值初始值0
		this.superFire = 0;	//设置超级火力值初始值0

	}

	/*英雄机跟随鼠标移动	【***】*/
	public void moveTo(int x, int y) {

		//设定英雄机中心点随着鼠标移动
		this.x = x - this.width/2;
		this.y = y - this.height/2;

	}

	/*重写超类方法，英雄机移动方法*/
	public void step() {
		//由于英雄机是根据鼠标移动的所以无需填写移动方法，但是不能删除该方法，原因是因为超类中有该方法，不重写会编译错误
	}

	private int index = 0;	//给getImages方法使用的变量
	/*重写超类方法，获取英雄机图片*/
	public BufferedImage getImages() {

		return Images.heros[index++ % Images.heros.length];	//加载Images类中读取到的英雄机图片

	}

	/*生成子弹方法*/
	public Bullet[] generateTheBullet(){

		int x = this.width/4;	//固定子弹位置，让子弹出现在英雄机宽的1/4位置
		if(this.superFire != 0) {	//检测超级火力值，如果火力值不为0
			
			Bullet[] tempBullet = new Bullet[World.WIDTH];	//创建一个Bullet类型的临时数组tempBullet，由于是超级火力，所以数组长度设置为窗口长度
			for(int i = 0, j = 0; i < tempBullet.length; i++, j+=10) {	//创建这个数组
				
				tempBullet[i] = new Bullet(j, World.HEIGHT);	//创建这个数组
				
			}
			this.superFire = 0;	//重置超级火力值
			return tempBullet;	//返回这个数组
			
		}else if(doubleFire <= 0) {	//检测火力值，如果火力值为0则为单倍火力

			Bullet[] tempBullet = new Bullet[1];	//创建一个Bullet类型的临时数组tempBullet，由于是单倍火力，所以数组长度设置为1
			tempBullet[0] = new Bullet(this.x + x * 2, this.y);	//new一个新的Bullet对象存到tempBullet[0]中，子弹位置设定在英雄机的中间部分
			return tempBullet;	//返回这个数组

		}else {	//检测火力值，如果火力值大于则为双倍火力

			Bullet[] tempBullet = new Bullet[2];	//创建一个Bullet类型的临时数组tempBullet，由于是双倍火力，所以数组长度设置为2
			//new两个新的Bullet对象分别存到tempBullet的[0]和[1]中，子弹位置设定在英雄机的左右两边
			tempBullet[0] = new Bullet(this.x + x, this.y);
			tempBullet[1] = new Bullet(this.x + x * 3, this.y);
			doubleFire -= 2;	//每发射一次双倍火力就减少一次双倍火力值
			return tempBullet;	//返回这个数组

		}
		
	}

	/*增加生命数*/
	public void addLife() {

		this.life++;

	}

	/*减少生命数*/
	public void subtractLife() {

		if(this.life > 0) {
			
			this.life--;
			
		}

	}

	/*获得火力值*/
	public void addDoubleFire() {

		this.doubleFire += 40;

	}

	/*清空火力值*/
	public void clearDoubleFire() {

		this.doubleFire = 0;

	}

	/*获取当前生命值*/
	public int getLife() {

		return this.life;

	}

	/*获取当前火力值状态*/
	public String getDoubleFire() {

		if(doubleFire > 0) {

			return "双倍火力";

		}else {

			return "单倍火力";

		}

	}

	/*获取当前火力值*/
	public int getDoubleFireNum() {

		return doubleFire;

	}
	
	/*获得超级火力值*/
	public void addSuperFire() {
		
		this.superFire++;
		
	}
	
}
