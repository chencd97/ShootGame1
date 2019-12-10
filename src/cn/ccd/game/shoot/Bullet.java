package cn.ccd.game.shoot;

import java.awt.image.BufferedImage;

/**�ӵ�*/
public class Bullet extends FlyingObject{
	
	private int speed;	//�ӵ��ƶ��ٶ�
	
	/*�ӵ�������*/
	public Bullet(int x, int y){
		
		super(8, 14, x, y);	//�ӵ����ݳ�ʼ��(�����ӵ�������Ӣ�ۻ��ƶ����ı�����λ�ã�������ʱ������)
		this.speed = 8;	//�ӵ��ƶ��ٶȳ�ʼ��
		
	}
	
	/*��д���෽�����ӵ��ƶ�����*/
	public void step() {
		
		this.y -= this.speed;	//�ӵ������ƶ�
		
	}
	
	/*��д���෽������ȡ�ӵ�ͼƬ*/
	public BufferedImage getImages() {

		if(isLife()) {	//�ж������ŵģ�ֱ�ӷ���bullet����
			
			return Images.bullet;		//����Images���ж�ȡ�����ӵ�ͼƬ
			
		}else if(isDead()) {	//�����ˣ�ֱ�ӱ��Ϊɾ���������ؿ�ͼƬ
			
			state = REMOVE;	//��stateֵ��ΪREMOVE
			
		}
		
		return null;	//���ؿ�ͼƬ
		
	}
	
	/*�ж��ӵ��Ƿ����*/
	public boolean outOfBounds() {
		
		return this.y < -this.height;	//����ӵ���Y����ֵ�����˴��ڵ����ֵ+������ĸ߶� (���ģ���Ϊ���ӵ����ڴ����Ϸ�����),���ж�Ϊ����
		
	}
	
}
