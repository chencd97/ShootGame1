package cn.ccd.game.shoot;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Images{

	public static BufferedImage sky;	//声明天空背景图片引用
	public static BufferedImage bullet;	//声明子弹图片引用
	public static BufferedImage[] heros;	//声明英雄机图片引用
	public static BufferedImage[] airplanes;	//声明小敌机图片引用
	public static BufferedImage[] bigAirplanes;	//声明大敌机图片引用
	public static BufferedImage[] bees;	//声明小蜜蜂(奖励机)图片引用
	public static BufferedImage start;	//声明游戏启动时的图片引用
	public static BufferedImage pause;	//声明游戏暂停时的图片引用
	public static BufferedImage gameOver;	//声明游戏结束时的图片引用

	/*设计静态块加载图片*/
	static {

		sky = loadImage("background.png");	//读取天空图片
		bullet = loadImage("bullet.png");	//读取子弹图片

		heros = new BufferedImage[] {	//读取英雄机图片
				loadImage("hero0.png"),	//英雄机图片1
				loadImage("hero1.png"),	//英雄机图片2
		};

		/*由于敌机图片数量较多(因为包含爆炸图片都至少5张)，所以设置成数组*/
		airplanes = new BufferedImage[5];	
		bigAirplanes = new BufferedImage[5];	
		bees = new BufferedImage[5];

		/*加载各敌机种所对应图片*/
		airplanes[0] = loadImage("airplane.png");
		bigAirplanes[0] = loadImage("bigplane.png");
		bees[0] = loadImage("bee.png");

		/*加载完后由于爆炸图片都是一样的就设计一个循环来快捷读取*/
		for(int i = 1, j = 0; i < airplanes.length; i++, j++){
			
			//每种敌机都加载4张爆炸图片存到数组中
			airplanes[i] = loadImage("airplane_ember"+j+".png");
			bigAirplanes[i] = loadImage("bigplane_ember"+j+".png");
			bees[i] = loadImage("bee_ember"+j+".png");

		}
		
		/*读取游戏状态图片*/
		start = loadImage("start.png");
		pause = loadImage("pause.png");
		gameOver = loadImage("gameover.png");

	}

	/*读取图片方法	【***】*/
	public static BufferedImage loadImage(String fileName){

		try{

			BufferedImage img = ImageIO.read(FlyingObject.class.getResource(fileName));	//在同包中读取图片
			return img;

		}catch(Exception e){

			e.printStackTrace();
			throw new RuntimeException(); 

		}

	}

}



