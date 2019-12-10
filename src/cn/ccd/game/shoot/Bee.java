package cn.ccd.game.shoot;

import java.awt.image.BufferedImage;
import java.util.Random;

/**С�۷�(������)*/
public class Bee extends FlyingObject implements Award{
	
	private int xSpeed;	//С�۷���Ϊ�������ƶ��������������ƶ��ٶȣ������X����ƶ��ٶ�
	private int ySpeed;	//�����Y����ƶ��ٶ�
	private int ranX;	//�ƶ�����ָʾ��
	private int awardType;	//����ֵ
	
	/*С�۷�(������)������*/
	public Bee() {
		
		super(60, 50);	//С�۷����ݳ�ʼ��
		Random ran = new Random();	//�������Ȩ��
		ranX = ran.nextInt(2);	//��������ƶ�����
		this.xSpeed = 2;	//С�۷�X����ƶ��ٶȳ�ʼ��
		this.ySpeed = 2;	//С�۷�Y����ƶ��ٶȳ�ʼ��
		this.awardType = ran.nextInt(101);	//��awardType��ֵ�����ֵΪ0~100
		
	}
	
	/*��д���෽����С�۷��ƶ�����*/
	public void step() {
		
		if(ranX == 0) {	//�жϳ�ʼ�ƶ�����
			
			this.x += this.xSpeed;	//С�۷������ƶ�
			
		}else {
			
			this.x -= this.xSpeed;	//С�۷������ƶ�
			
		}
		this.y += ySpeed;	//С�۷�����ƶ��ٶ������ƶ�
		
		/*�ж�С�۷��Ƿ��������ڱ�Ե*/
		if(this.x <= 0 || this.x >= World.WIDTH - this.width) {
			
			this.xSpeed *= -1;	//��x���ƶ���������Ϊ������
			
		}
		
	}
	
	private int index = 1;//��getImages����ʹ�õı���
	/*��д���෽������ȡС�۷�ͼƬ*/
	public BufferedImage getImages() {

		if(isLife()) {	//�ж������ŵģ�ֱ�ӷ���bees[0]����
			
			return Images.bees[0];		//����Images���ж�ȡ����С�۷�ͼƬ
			
		}else if(isDead()) {	//�����ˣ����ر�ըͼƬ1-4����4�ź󷵻ؿ�ͼƬ
			
			BufferedImage img = Images.bees[index++];	//����Images���ж�ȡ���ı�ըͼƬ��index = 1.2.3.4
			if(index == Images.bees.length) {	//index = 2.3.4.5��������5ʱ��state״̬����ΪREMOVE�������´ξͲ�����뵽��if��
				state = REMOVE;
			}
			return img;	//���ر�ըͼƬ1-4
			
		}
		
		return null;	//���ؿ�ͼƬ
		
	}

	/*���ؽ�������*/
	public int getAwardType() {

		return awardType;
		
	}

}
