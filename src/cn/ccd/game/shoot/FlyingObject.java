package cn.ccd.game.shoot;

import java.awt.Graphics;
import java.util.Random;
import java.awt.image.BufferedImage;

/**����*/
public abstract class FlyingObject {

	protected static final int LIFE = 0;	//�������� ����(����) ʱ��ֵ
	protected static final int DEAD = 1;	//�������� ����(����������) ʱ��ֵ
	protected static final int REMOVE = 2;	//�������� ������ ʱ��ֵ

	protected int state = LIFE;	///�������� ��ǰ ״ֵ̬(Ĭ��Ϊ����)

	protected int width;	//���ж���-��
	protected int height;	//���ж���-��
	protected int x;	//���ж���-x��
	protected int y;	//���ж���-y��

	/*���๹����1�����ṩ����գ�Ӣ�ۻ����ӵ�ʹ��*/
	public FlyingObject(int width, int height, int x, int y) {	//��Ҫ���󴫲�ʹ��

		this.width = width;	//��ʼ��������������ʼ��
		this.height = height;	//��ʼ��������������ʼ��
		this.x = x;	//��ʼX��������������ʼ��
		this.y = y;	//��ʼY��������������ʼ��

	}

	/*���๹����2�����ṩ���л�ʹ��*/
	public FlyingObject(int width, int height) {	//��Ҫ���󴫲�ʹ��

		this.width = width;	//��ʼ��������������ʼ��
		this.height = height;	//��ʼ��������������ʼ��
		Random ran = new Random();	//�������Ȩ��
		this.x = ran.nextInt(World.WIDTH-this.width);	//��ʼX��λ�ù̶���ϵͳ�Զ�����
		this.y = -this.height;	//��ʼY��λ���ɳ���̶�����

	}

	/*�������ƶ����ԣ�����������д���������Ϊ���󷽷�*/
	public abstract void step();

	/*�жϷ����ﵱǰ״̬����(����/���/���)*/
	public boolean isLife() {

		return state == LIFE;	//�ж�state��ֵ�Ƿ�ΪLIFE

	}

	/*�жϷ����ﵱǰ״̬����(����/������)*/
	public boolean isDead() {

		return state == DEAD;	//�ж�state��ֵ�Ƿ�ΪDEAD

	}

	/*�жϷ����ﵱǰ״̬����(�������ͽ������Ϊɾ��)*/
	public boolean isRemove() {

		return state == REMOVE;	//�ж�state��ֵ�Ƿ�ΪREMOVE

	}

	/*��ȡ��Images���ȡ����ͼƬ������ÿ�������ȡ��ͼƬ���ݲ�һ��������Ƴɳ��󷽷�*/
	public abstract BufferedImage getImages();

	/*����������ÿ�����󻭵ķ�ʽһ����������Ϊ��ͨ����(�������)	��***��*/
	public void paintObject(Graphics g) {

		g.drawImage(this.getImages(), this.x, this.y, null);

	}
	
	/*�жϷ������Ƿ����*/
	public boolean outOfBounds() {
		
		return this.y > World.HEIGHT;	//����������Y����ֵ�����˴��ڵ����ֵ���ж�Ϊ����
		
	}

	/*�ӵ���Ӣ�ۻ���л���ײ���*/
	public boolean hit(FlyingObject other) {
		
		/*�趨�жϷ�Χ*/
		int x1 = this.x - other.width;
		int x2 = this.x + this.width;
		int y1 = this.y - other.height;
		int y2 = this.y + this.height;
		
		/*return�жϽ��*/
		return other.x >= x1 && other.x <= x2 && other.y >= y1 && other.y <= y2;
		
	}
	
	/*�趨������״̬Ϊ ����/������*/
	public void goDead() {
		
		this.state = DEAD;	//�趨������״̬Ϊ ����/������ ״̬
		
	}
	
}
