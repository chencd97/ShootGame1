package cn.ccd.game.shoot;

import java.awt.Graphics;
import java.util.Random;
import java.awt.image.BufferedImage;

/**超类*/
public abstract class FlyingObject {

	protected static final int LIFE = 0;	//设置物体 存在(活着) 时的值
	protected static final int DEAD = 1;	//设置物体 死亡(或正在死亡) 时的值
	protected static final int REMOVE = 2;	//设置物体 死亡后 时的值

	protected int state = LIFE;	///设置物体 当前 状态值(默认为活着)

	protected int width;	//所有对象-宽
	protected int height;	//所有对象-高
	protected int x;	//所有对象-x轴
	protected int y;	//所有对象-y轴

	/*超类构造器1：仅提供给天空，英雄机，子弹使用*/
	public FlyingObject(int width, int height, int x, int y) {	//需要对象传参使用

		this.width = width;	//初始宽由派生类对象初始化
		this.height = height;	//初始高由派生类对象初始化
		this.x = x;	//初始X轴由派生类对象初始化
		this.y = y;	//初始Y轴由派生类对象初始化

	}

	/*超类构造器2：仅提供给敌机使用*/
	public FlyingObject(int width, int height) {	//需要对象传参使用

		this.width = width;	//初始宽由派生类对象初始化
		this.height = height;	//初始高由派生类对象初始化
		Random ran = new Random();	//申请随机权限
		this.x = ran.nextInt(World.WIDTH-this.width);	//初始X轴位置固定由系统自动生成
		this.y = -this.height;	//初始Y轴位置由超类固定生成

	}

	/*飞行物移动测试，需由子类重写，所以设计为抽象方法*/
	public abstract void step();

	/*判断飞行物当前状态方法(存在/存活/活的)*/
	public boolean isLife() {

		return state == LIFE;	//判断state的值是否为LIFE

	}

	/*判断飞行物当前状态方法(死亡/死亡中)*/
	public boolean isDead() {

		return state == DEAD;	//判断state的值是否为DEAD

	}

	/*判断飞行物当前状态方法(已死亡就将它标记为删除)*/
	public boolean isRemove() {

		return state == REMOVE;	//判断state的值是否为REMOVE

	}

	/*获取由Images类读取到的图片，由于每个对象获取的图片内容不一样所以设计成抽象方法*/
	public abstract BufferedImage getImages();

	/*画对象，由于每个对象画的方式一样所以设置为普通方法(除天空外)	【***】*/
	public void paintObject(Graphics g) {

		g.drawImage(this.getImages(), this.x, this.y, null);

	}
	
	/*判断飞行物是否出界*/
	public boolean outOfBounds() {
		
		return this.y > World.HEIGHT;	//如果飞行物的Y轴数值超过了窗口的最大值则判断为出界
		
	}

	/*子弹、英雄机与敌机碰撞检测*/
	public boolean hit(FlyingObject other) {
		
		/*设定判断范围*/
		int x1 = this.x - other.width;
		int x2 = this.x + this.width;
		int y1 = this.y - other.height;
		int y2 = this.y + this.height;
		
		/*return判断结果*/
		return other.x >= x1 && other.x <= x2 && other.y >= y1 && other.y <= y2;
		
	}
	
	/*设定飞行物状态为 死亡/死亡中*/
	public void goDead() {
		
		this.state = DEAD;	//设定飞行物状态为 死亡/死亡中 状态
		
	}
	
}
