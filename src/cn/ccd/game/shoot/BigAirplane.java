package cn.ccd.game.shoot;

import java.awt.image.BufferedImage;

/**��л�*/
public class BigAirplane extends FlyingObject implements Enemy{
	
	private int speed;	//��л��ƶ��ٶ�
	
	/*��л�������*/
	public BigAirplane() {
		
		super(69, 99);	//��л����ݳ�ʼ��
		this.speed = 2;	//��л��ƶ��ٶȳ�ʼ��
		
	}
	
	/*��д���෽������л��ƶ�����*/
	public void step() {
		
		this.y += this.speed;	//��л������ƶ��ٶ������ƶ�
		
	}
	
	private int index = 1;//��getImages����ʹ�õı���
	/*��д���෽������ȡ��л�ͼƬ*/
	public BufferedImage getImages() {

		if(isLife()) {	//�ж������ŵģ�ֱ�ӷ���bigAirplanes[0]����
			
			return Images.bigAirplanes[0];		//����Images���ж�ȡ���Ĵ�л�ͼƬ
			
		}else if(isDead()) {	//�����ˣ����ر�ըͼƬ1-4����4�ź󷵻ؿ�ͼƬ
			
			BufferedImage img = Images.bigAirplanes[index++];	//����Images���ж�ȡ���ı�ըͼƬ��index = 1.2.3.4
			if(index == Images.bigAirplanes.length) {	//index = 2.3.4.5��������5ʱ��state״̬����ΪREMOVE�������´ξͲ�����뵽��if��
				state = REMOVE;
			}
			return img;	//���ر�ըͼƬ1-4
			
		}
		
		return null;	//���ؿ�ͼƬ
		
	}

	/*���ص÷�*/
	public int getScore() {

		return 3;
		
	}

}
