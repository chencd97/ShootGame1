package cn.ccd.game.shoot;

import java.awt.image.BufferedImage;
import java.util.Random;

/**С�л�*/
public class Airplane extends FlyingObject implements Enemy{

	private int xSpeed;	//С�л�X����ƶ���
	private int ySpeed;	//С�л�Y����ƶ���
	private int ranX;	//�ƶ�����ָʾ��
	
	/*С�л�������*/
	public Airplane() {
		
		super(49, 36);	//С�л����ݳ�ʼ��
		Random ran = new Random();	//�������Ȩ��
		ranX = ran.nextInt(2);	//��������ƶ�����
		this.xSpeed = 1;	//С�л�X����ƶ��ٳ�ʼ��
		this.ySpeed = 2;	//С�л�Y����ƶ��ٳ�ʼ��
	}
	
	/*��д���෽����С�л��ƶ�����*/
	public void step() {

		if(ranX == 0) {	//�жϳ�ʼ�ƶ�����
			
			this.x += this.xSpeed;	//С�л������ƶ�
			
		}else {
			
			this.x -= this.xSpeed;	//С�л������ƶ�
			
		}
		this.y += this.ySpeed;	//С�л������ƶ��ٶ������ƶ�
		
		/*�ж�С�л��Ƿ��������ڱ�Ե*/
		if(this.x <= 0 || this.x >= World.WIDTH - this.width) {
			
			this.xSpeed *= -1;	//��x���ƶ���������Ϊ������
			
		}
		
	}
	
	private int index = 1;//��getImages����ʹ�õı���
	/*��д���෽������ȡС�л�ͼƬ*/
	public BufferedImage getImages() {

		if(isLife()) {	//�ж������ŵģ�ֱ�ӷ���airplanes[0]����
			
			return Images.airplanes[0];		//����Images���ж�ȡ����С�л�ͼƬ
			
		}else if(isDead()) {	//�����ˣ����ر�ըͼƬ1-4����4�ź󷵻ؿ�ͼƬ
			
			BufferedImage img = Images.airplanes[index++];	//����Images���ж�ȡ���ı�ըͼƬ��index = 1.2.3.4
			if(index == Images.airplanes.length) {	//index = 2.3.4.5��������5ʱ��state״̬����ΪREMOVE�������´ξͲ�����뵽��if��
				state = REMOVE;
			}
			return img;	//���ر�ըͼƬ1-4
			
		}
		
		return null;	//���ؿ�ͼƬ
		
	}

	/*���ص÷�*/
	public int getScore() {
		
		return 1;
		
	}
	
}
