package cn.ccd.game.shoot;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Images{

	public static BufferedImage sky;	//������ձ���ͼƬ����
	public static BufferedImage bullet;	//�����ӵ�ͼƬ����
	public static BufferedImage[] heros;	//����Ӣ�ۻ�ͼƬ����
	public static BufferedImage[] airplanes;	//����С�л�ͼƬ����
	public static BufferedImage[] bigAirplanes;	//������л�ͼƬ����
	public static BufferedImage[] bees;	//����С�۷�(������)ͼƬ����
	public static BufferedImage start;	//������Ϸ����ʱ��ͼƬ����
	public static BufferedImage pause;	//������Ϸ��ͣʱ��ͼƬ����
	public static BufferedImage gameOver;	//������Ϸ����ʱ��ͼƬ����

	/*��ƾ�̬�����ͼƬ*/
	static {

		sky = loadImage("background.png");	//��ȡ���ͼƬ
		bullet = loadImage("bullet.png");	//��ȡ�ӵ�ͼƬ

		heros = new BufferedImage[] {	//��ȡӢ�ۻ�ͼƬ
				loadImage("hero0.png"),	//Ӣ�ۻ�ͼƬ1
				loadImage("hero1.png"),	//Ӣ�ۻ�ͼƬ2
		};

		/*���ڵл�ͼƬ�����϶�(��Ϊ������ըͼƬ������5��)���������ó�����*/
		airplanes = new BufferedImage[5];	
		bigAirplanes = new BufferedImage[5];	
		bees = new BufferedImage[5];

		/*���ظ��л�������ӦͼƬ*/
		airplanes[0] = loadImage("airplane.png");
		bigAirplanes[0] = loadImage("bigplane.png");
		bees[0] = loadImage("bee.png");

		/*����������ڱ�ըͼƬ����һ���ľ����һ��ѭ������ݶ�ȡ*/
		for(int i = 1, j = 0; i < airplanes.length; i++, j++){
			
			//ÿ�ֵл�������4�ű�ըͼƬ�浽������
			airplanes[i] = loadImage("airplane_ember"+j+".png");
			bigAirplanes[i] = loadImage("bigplane_ember"+j+".png");
			bees[i] = loadImage("bee_ember"+j+".png");

		}
		
		/*��ȡ��Ϸ״̬ͼƬ*/
		start = loadImage("start.png");
		pause = loadImage("pause.png");
		gameOver = loadImage("gameover.png");

	}

	/*��ȡͼƬ����	��***��*/
	public static BufferedImage loadImage(String fileName){

		try{

			BufferedImage img = ImageIO.read(FlyingObject.class.getResource(fileName));	//��ͬ���ж�ȡͼƬ
			return img;

		}catch(Exception e){

			e.printStackTrace();
			throw new RuntimeException(); 

		}

	}

}



