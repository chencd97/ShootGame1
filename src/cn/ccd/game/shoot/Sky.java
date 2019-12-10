package cn.ccd.game.shoot;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**��ձ���*/
public class Sky extends FlyingObject{

	private int speed;	//��ձ����ƶ��ٶ�
	private int y2;	//��ձ����ڶ�������

	/*��ձ���������*/
	public Sky() {	

		super(World.WIDTH, World.HEIGHT, 0, 0);	//������ݳ�ʼ��
		this.speed = 1;	//��ձ����ƶ��ٶȳ�ʼ��
		this.y2 = -this.height;	//��յڶ��ű������������ڵ�һ�ŵ�����

	}

	/*��д���෽������ձ����ƶ�����*/
	public void step() {

		this.y += speed;	//��ձ���1�����ƶ��ٶ������ƶ�
		this.y2 += speed;	//��ձ���2�����ƶ��ٶ������ƶ�
		
		if(y >= World.HEIGHT) {	//�����ձ���1���ƶ��������⣬���������»ص����Ϸ�
			
			y = -World.HEIGHT;	//�趨���1�����ص����Ϸ�
			
		}
		
		if(y2 >= World.HEIGHT) {	//�����ձ���2���ƶ��������⣬���������»ص����Ϸ�
			
			y2 = -World.HEIGHT;	//�趨���2�����ص����Ϸ�
			
		}

	}

	/*��д���෽������ȡ���ͼƬ*/
	public BufferedImage getImages() {

		return Images.sky;	//����Images���ж�ȡ������ձ���ͼƬ

	}

	/*��д���෽��	�����*/
	public void paintObject(Graphics g) {

		g.drawImage(this.getImages(), this.x, this.y, null);	//����յ�һ��
		g.drawImage(this.getImages(), this.x, this.y2, null);	//����յڶ���

	}

}
