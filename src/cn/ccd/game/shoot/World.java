package cn.ccd.game.shoot;

/**
 * 
 * 	@Project ShootDemo(正式版)
 * 	@Versions 1.2.2
 *	@Author CCD
 *	@CopyRight (C) 2019 CCD
 * 
 */

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/** 飞机大战游戏主类 */
public class World extends JPanel implements GameLevel{

	private static final String PROJECT = "ShootDemo";	//项目名称
	private static final String EDITION = "正式版";	//当前版次
	private static final String VERSIONS = "1.2.2";	//版本号
	private static final String AUTHOR = "CCD";	//作者

	protected static final int WIDTH = 1280;	//飞机大战主窗口宽
	protected static final int HEIGHT = 800;	//飞机大战主窗口高

	/*定义游戏状态*/
	private static final int START = 0;	//游戏开启前
	private static final int RUNNING = 1;	//游戏中
	private static final int PAUSE = 2;	//游戏暂停
	private static final int GAME_OVRE = 3;	//游戏结束

	/*游戏默认状态*/
	private int state = START;

	/*游戏默认难度文本*/
	private String gameLevelMode = "null";

	/*声明玩家得分*/
	private int score = 0;

	/*声明RunTime*/
	private int RunTime = 0;

	/*创建对象*/
	private Sky sky = new Sky();	//创建天空对象
	private Hero hero = new Hero();	//创建英雄机对象
	private FlyingObject[] enemies = {};	//创建敌机数组对象(向上造型)
	private Bullet[] bullet = {};	//创建子弹数组对象

	/*获取游戏难度等级*/
	public void getGameLevel(int level) {

		switch(level) {

		case LEVEL_1:	//1

			enemiesEnterActionNum = 13;	//设置敌人生成频率130毫秒1个
			bulletShootActionNum = 12;	//设置子弹生成频率120毫秒1个
			gameLevelMode = "初级难度";	//设置游戏难度文本并显示
			break;

		case LEVEL_2:	//2

			enemiesEnterActionNum = 5;	//设置敌人生成频率50毫秒1个
			bulletShootActionNum = 10;	//设置子弹生成频率100毫秒1个
			gameLevelMode = "高级难度";	//设置游戏难度文本并显示
			break;

		case LEVEL_3:	//3

			enemiesEnterActionNum = 2;	//设置敌人生成频率20毫秒1个
			bulletShootActionNum = 8;	//设置子弹生成频率80毫秒1个
			gameLevelMode = "变态难度";	//设置游戏难度文本并显示
			break;

		}

	}

	/*生成敌人类型并返回给调用方*/
	public FlyingObject generateTheEnemy(){

		Random ran = new Random();	//启用随机功能
		int type = ran.nextInt(23);	//随机值0~22

		/*控制3种敌机生成的概率*/
		if(type <= 2) {	// 0-2

			return new Bee();	//返回小蜜蜂

		}else if(type <= 12) {	// 3-12

			return new Airplane();	//返回小敌机

		}else {	// 13-22

			return new BigAirplane();	//返回大敌机

		}

	}

	private int enemiesEnterActionIndex = 0;	//敌机计数器
	private int enemiesEnterActionNum = 1;	//敌机数量控制器
	/*生成敌人并入场*/
	public void enemiesEnterAction() {	//生成敌人并入场

		enemiesEnterActionIndex++;	//方法每被调用一次,计数器加1	(由于此方法是每10毫秒运行一次，所以每10毫秒计数器才会加1)
		if(enemiesEnterActionIndex % enemiesEnterActionNum == 0) {	//生成敌人条件由 【敌机数量控制器】 控制出场数量

			FlyingObject tempEnemies = generateTheEnemy();	//调用生成敌人方法,并将返回结果存到tempEnemies临时数组中
			enemies = Arrays.copyOf(enemies, enemies.length+1);	//将敌人数组扩容
			enemies[enemies.length-1] = tempEnemies;	//将生成的敌人放入新扩容数组中的最后一位

		}

	}

	private int bulletShootActionIndex = 0;	//子弹计数器
	private int bulletShootActionNum = 1;	//子弹生成控制器
	/*生成子弹并入场*/
	public void bulletShootAction() {

		bulletShootActionIndex++;	//方法每被调用一次,计数器加1	(由于此方法是每10毫秒运行一次，所以每10毫秒计数器才会加1,所以这里根据【子弹生成控制器】生成子弹)
		if(bulletShootActionIndex % bulletShootActionNum == 0) {	//如果计数器取余后的条件成立则生成子弹,否则不生成

			FlyingObject[] tempBullet = hero.generateTheBullet();	//调用生成子弹方法,并将返回结果存到tempBullet临时数组中
			bullet = Arrays.copyOf(bullet, bullet.length + tempBullet.length);	//将子弹数组扩容
			System.arraycopy(tempBullet, 0, bullet, bullet.length - tempBullet.length, tempBullet.length);	//将临时数组中的数据复制到扩容数组中

		}

	}

	/*飞行物移动功能*/
	public void stepAction() {

		sky.step();	//调用天空step方法让天空背景动起来
		for(int i = 0; i < enemies.length; i++) {	//遍历敌人数组

			enemies[i].step();	//调用敌人数组中各元素的step方法让敌人动起来

		}
		for(int i = 0; i < bullet.length; i++) {	//遍历子弹数组

			bullet[i].step();	//调用子弹数组中各元素的step方法让子弹动起来

		}

	}

	/*飞行物出界回收*/
	public void outOfBoundsAction() {

		/*敌机出界回收*/
		int outOfBoundsIndex = 0;	//敌机计数器/下标指示器
		FlyingObject[] newEnemies = new FlyingObject[enemies.length];	//先新建一个 临时数组 把原来的敌人数组长度复制过来
		for(int i = 0; i < enemies.length; i++) {	//遍历原敌人数组(enemies[])

			if(!enemies[i].outOfBounds() && !enemies[i].isRemove()) {	//调用敌人数组检测出界功能和敌人当前状态检测功能,如果没出界且敌人当前状态不为REMOVE则进入内部流程

				newEnemies[outOfBoundsIndex] = enemies[i];	//将没出界的敌人存入临时数组中的指定位置
				outOfBoundsIndex++;	//敌机计数器加1

			}

		}
		enemies = Arrays.copyOf(newEnemies, outOfBoundsIndex);	//遍历完成后将临时数组中的所有数据重新赋给原数组(enemies[]),防止长时间运行使数组过长导致卡顿 (防止内存泄漏)

		/*子弹出界回收*/
		outOfBoundsIndex = 0;	//子弹计数器/下标指示器
		Bullet[] newBullet = new Bullet[bullet.length];	//先新建一个 临时数组 把原来的子弹数组长度复制过来
		for(int i = 0; i < bullet.length; i++) {	//遍历原子弹数组(bullet[])

			if(!bullet[i].outOfBounds() && !bullet[i].isRemove()) {	//调用子弹数组检测出界功能和子弹当前状态检测功能,如果没出界且子弹当前状态不为REMOVE则进入内部流程

				newBullet[outOfBoundsIndex] = bullet[i];	//将没出界的子弹存入临时数组中的指定位置
				outOfBoundsIndex++;	//子弹计数器加1

			}

		}
		bullet = Arrays.copyOf(newBullet, outOfBoundsIndex);	//遍历完成后将临时数组中的所有数据重新赋给原数组(bullet[]),防止长时间运行使数组过长导致卡顿 (防止内存泄漏)

	}

	/*实现子弹与敌人的碰撞*/
	public void bulletBangAction(){

		for(int i = 0; i < bullet.length; i++) {	//遍历子弹数组得单个子弹

			for(int j = 0; j < enemies.length; j++) {	//遍历敌人数组得单个敌人

				/*判断子弹是否击中敌人(还要判断当前的子弹和敌人的状态，如果是正常的存活状态就进入内部)*/
				if(bullet[i].isLife() && enemies[j].isLife() && enemies[j].hit(bullet[i])) {

					bullet[i].goDead();	//调用子弹类中的goDead()方法将当前子弹状态设置为死亡
					enemies[j].goDead();	//调用敌人类中的goDead()方法将当前敌人状态设置为死亡

					if(enemies[j] instanceof Enemy) {	//判断当前敌人是否为Enemy类型

						Enemy temp = (Enemy)enemies[j];	//若是则将当前敌人类型强转为Enemy类型
						score += temp.getScore();	//调用Enemy接口中的getScore()方法加分

					}

					if(enemies[j] instanceof Award) {	//判断当前敌人是否为Award类型

						Award temp = (Award)enemies[j];	//若是则将当前敌人类型强转为Award类型

						/*调用Award接口中的getAwardType()方法判断奖励类型*/
						if(temp.getAwardType() >= 51) {	//50%

							hero.addDoubleFire();	//英雄机获得双倍火力值

						}else if(temp.getAwardType() >= 5) {	//45%

							hero.addLife();	//英雄机获得生命值奖励

						}else {	//5%

							hero.addSuperFire();	//英雄机获得超级火力值

						}

					}

				}

			}

		}

	}

	/*实现英雄机与敌人的碰撞*/
	public void herotBangAction(){

		for(int j = 0; j < enemies.length; j++) {	//遍历敌人数组得单个敌人

			/*判断英雄机是与敌人碰撞(还要判断当前的英雄机和敌人的状态，如果是正常的存活状态就进入内部)*/
			if(hero.isLife() && enemies[j].isLife() && enemies[j].hit(hero)) {

				enemies[j].goDead();	//调用敌人类中的goDead()方法将当前敌人状态设置为死亡
				hero.subtractLife();	//调用英雄机的subtractLife()方法实现英雄机减少一条生命值
				hero.clearDoubleFire();		//调用英雄机的clearDoubleFire()方法实现英雄机清空火力值

			}

		}

	}

	/*检测游戏是否结束*/
	public void checkGamoOvreAction() {

		if(hero.getLife() <= 0) {	//如果检测到英雄机的生命值小于等于0

			state = GAME_OVRE;	//设置当前游戏状态为GAME_OVRE(游戏结束状态)

		}

	}

	/*运行核心*/
	public void action() {

		/*鼠标事件帧听器	【***】	*/
		MouseAdapter l = new MouseAdapter() {

			/*帧听鼠标点击事件*/
			public void mouseClicked(MouseEvent e) {

				switch(state) {	//获取当前游戏状态

				case START:	//如果为启动状态

					state = RUNNING;	//将游戏改为运行状态
					break;

				case GAME_OVRE:	//如果为游戏结束状态则要将游戏内的所有内容恢复为初始状态供下次使用

					score = 0;	//重置游戏分数
					hero = new Hero();	//重置英雄机状态
					enemies = new FlyingObject[0];	//重置敌机状态
					bullet = new Bullet[0];	//重置子弹状态
					state = START;	//重置游戏状态
					break;

				}

			}

			/*帧听鼠标移动事件*/
			public void mouseMoved(MouseEvent e) {

				if(state == RUNNING) {	//判断如果当前游戏状态为运行状态

					hero.moveTo(e.getX(), e.getY());	//将捕获到的数据传给英雄机，使英雄机跟随鼠标移动

				}

			}

			/*帧听鼠标移出事件*/
			public void mouseExited(MouseEvent e) {

				if(state == RUNNING) {	//如果当前游戏为运行状态

					state = PAUSE;	//将当前游戏状态设置为暂停状态

				}

			}

			/*帧听鼠标移入事件*/
			public void mouseEntered(MouseEvent e) {

				if(state == PAUSE) {	//如果当前游戏为暂停状态

					state = RUNNING;	//将当前游戏状态设置为运行状态

				}

			}

		};

		this.addMouseListener(l);
		this.addMouseMotionListener(l);

		/*定时任务触发器	【***】	*/
		Timer timer = new Timer();
		int time = 10;	//设定任务触发器运行时间间隔(毫秒)
		timer.schedule(new TimerTask() {	//添加任务

			/*创建一个匿名内部类*/
			public void run() {	//在内部类中重写run()方法

				if(state == RUNNING) {	//当游戏处状态处于 [运行中] 时启动游戏内容

					enemiesEnterAction();	//调用敌人入场功能
					bulletShootAction();	//调用子弹入场功能
					stepAction();	//启动飞行物移动功能
					outOfBoundsAction();	//启动飞行物出界检测功能
					bulletBangAction();	//启动子弹与敌机碰撞功能
					herotBangAction();	//启动英雄机与敌机碰撞功能
					checkGamoOvreAction();	//启动游戏结束判断功能

				}

				repaint();	//重复执行以上代码

			}

		}, time, time);	//程序运行后等待time秒后执行此操作,每次操作运行间隔time秒
		//timer.schedule( 任务列表,  程序运行后多久执行,   每次运行间隔 )
	}

	/*画窗口内容方法	【***】	*/
	public void paint(Graphics g) {

		sky.paintObject(g);	//画天空
		hero.paintObject(g);	//画英雄机

		for(int i = 0; i < enemies.length; i++) {	//遍历敌人

			enemies[i].paintObject(g);	//画敌机

		}

		for(int i = 0; i < bullet.length; i++) {	//遍历子弹

			bullet[i].paintObject(g);	//画子弹

		}

		switch(state) {	//根据当前游戏状态画图片

		case START:	//如果是游戏启动状态

			g.drawImage(Images.start, 0, 0, null);	//画游戏启动图
			break;

		case PAUSE:	//如果是游戏暂停状态

			g.drawImage(Images.pause, 0, 0, null);	//画游戏暂停图
			break;

		case GAME_OVRE:	//如果是游戏结束状态

			g.drawImage(Images.gameOver, 0, 0, null);	//画游戏结束图
			break;

		}

		/*用户窗口显示信息*/
		g.drawString("当前得分：[ "+score+" ]", World.WIDTH/2-80, 20);
		g.drawString("当前游戏难度：[ "+gameLevelMode+" ]", 10, 20);
		g.drawString("剩余生命："+hero.getLife(), 10, 40);
		g.drawString("当前同屏敌人数量："+enemies.length, 10, 60);
		g.drawString("当前同屏子弹数量："+bullet.length, 10, 80);
		g.drawString("当前开火模式："+hero.getDoubleFire(), World.WIDTH/2-80, World.HEIGHT-60);
		if(hero.getDoubleFire().equals("双倍火力")) {
			g.drawString("剩余火力值："+hero.getDoubleFireNum(), World.WIDTH/2-69, World.HEIGHT-80);
		}

		/*版本信息*/
		g.drawString(PROJECT+" ( "+EDITION+" )", 10, World.HEIGHT-140);
		g.drawString("Versions "+VERSIONS, 10, World.HEIGHT-120);
		g.drawString("Author "+AUTHOR, 10, World.HEIGHT-100);
		g.drawString("CopyRight (C) 2019 "+AUTHOR, 10, World.HEIGHT-80);
		g.drawString("RunTime：["+(RunTime++)+"]", 10, World.HEIGHT-60);

	}

	/*游戏主方法(游戏入口)*/
	public static void main(String[] ccd) {

		World world = new World();
		Scanner scan = new Scanner(System.in);	//开启用户输入
		System.out.println("【欢迎游玩飞机大战游戏】"+VERSIONS);
		System.out.println();
		System.out.println("开发此游戏目的只是为了代码研究测试用");
		System.out.println("在游戏中出现某些BUG属于正常现象");
		System.out.println("此版本不是最终版本，但也不代表后续会进行更新，可能随时弃坑");
		System.out.println();
		System.out.println("请输入数字选择游戏难度 [难度越高敌机越多]");
		System.out.println("1.【初级难度】");
		System.out.println("2.【高级难度】");
		System.out.println("3.【变态难度】");
		System.out.println("请选择：");

		/*循环判断玩家输入并设置对应难度*/
		do {

			int i = scan.nextInt();

			if(i == 1) {

				world.getGameLevel(i);
				break;

			}else if(i == 2) {

				world.getGameLevel(i);
				break;

			}else if(i == 3) {

				world.getGameLevel(i);
				break;

			}else {

				System.out.println("输入错误，请重新选择：");

			}

		}while(true);

		scan.close();	//关闭用户输入

		/* 【***】 */
		JFrame frame = new JFrame();
		frame.add(world);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(World.WIDTH, World.HEIGHT);
		frame.setLocationRelativeTo(null); 
		frame.setVisible(true); 

		world.action();	//调用运行核心

	}

}
