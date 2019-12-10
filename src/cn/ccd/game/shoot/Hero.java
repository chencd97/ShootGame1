package cn.ccd.game.shoot;

import java.awt.image.BufferedImage;

/**Ӣ�ۻ�*/
public class Hero extends FlyingObject{

	private int life;	//��������
	private int doubleFire;	//˫������ֵ
	private int superFire;	//��������ֵ

	/*Ӣ�ۻ�������*/
	public Hero() {

		super(97, 124, World.WIDTH/2-97/2, World.HEIGHT-300);	//Ӣ�ۻ����ݳ�ʼ��
		this.life = 3;	//����Ĭ��������3��
		this.doubleFire = 0;	//����˫������ֵ��ʼֵ0
		this.superFire = 0;	//���ó�������ֵ��ʼֵ0

	}

	/*Ӣ�ۻ���������ƶ�	��***��*/
	public void moveTo(int x, int y) {

		//�趨Ӣ�ۻ����ĵ���������ƶ�
		this.x = x - this.width/2;
		this.y = y - this.height/2;

	}

	/*��д���෽����Ӣ�ۻ��ƶ�����*/
	public void step() {
		//����Ӣ�ۻ��Ǹ�������ƶ�������������д�ƶ����������ǲ���ɾ���÷�����ԭ������Ϊ�������и÷���������д��������
	}

	private int index = 0;	//��getImages����ʹ�õı���
	/*��д���෽������ȡӢ�ۻ�ͼƬ*/
	public BufferedImage getImages() {

		return Images.heros[index++ % Images.heros.length];	//����Images���ж�ȡ����Ӣ�ۻ�ͼƬ

	}

	/*�����ӵ�����*/
	public Bullet[] generateTheBullet(){

		int x = this.width/4;	//�̶��ӵ�λ�ã����ӵ�������Ӣ�ۻ����1/4λ��
		if(this.superFire != 0) {	//��ⳬ������ֵ���������ֵ��Ϊ0
			
			Bullet[] tempBullet = new Bullet[World.WIDTH];	//����һ��Bullet���͵���ʱ����tempBullet�������ǳ����������������鳤������Ϊ���ڳ���
			for(int i = 0, j = 0; i < tempBullet.length; i++, j+=10) {	//�����������
				
				tempBullet[i] = new Bullet(j, World.HEIGHT);	//�����������
				
			}
			this.superFire = 0;	//���ó�������ֵ
			return tempBullet;	//�����������
			
		}else if(doubleFire <= 0) {	//������ֵ���������ֵΪ0��Ϊ��������

			Bullet[] tempBullet = new Bullet[1];	//����һ��Bullet���͵���ʱ����tempBullet�������ǵ����������������鳤������Ϊ1
			tempBullet[0] = new Bullet(this.x + x * 2, this.y);	//newһ���µ�Bullet����浽tempBullet[0]�У��ӵ�λ���趨��Ӣ�ۻ����м䲿��
			return tempBullet;	//�����������

		}else {	//������ֵ���������ֵ������Ϊ˫������

			Bullet[] tempBullet = new Bullet[2];	//����һ��Bullet���͵���ʱ����tempBullet��������˫���������������鳤������Ϊ2
			//new�����µ�Bullet����ֱ�浽tempBullet��[0]��[1]�У��ӵ�λ���趨��Ӣ�ۻ�����������
			tempBullet[0] = new Bullet(this.x + x, this.y);
			tempBullet[1] = new Bullet(this.x + x * 3, this.y);
			doubleFire -= 2;	//ÿ����һ��˫�������ͼ���һ��˫������ֵ
			return tempBullet;	//�����������

		}
		
	}

	/*����������*/
	public void addLife() {

		this.life++;

	}

	/*����������*/
	public void subtractLife() {

		if(this.life > 0) {
			
			this.life--;
			
		}

	}

	/*��û���ֵ*/
	public void addDoubleFire() {

		this.doubleFire += 40;

	}

	/*��ջ���ֵ*/
	public void clearDoubleFire() {

		this.doubleFire = 0;

	}

	/*��ȡ��ǰ����ֵ*/
	public int getLife() {

		return this.life;

	}

	/*��ȡ��ǰ����ֵ״̬*/
	public String getDoubleFire() {

		if(doubleFire > 0) {

			return "˫������";

		}else {

			return "��������";

		}

	}

	/*��ȡ��ǰ����ֵ*/
	public int getDoubleFireNum() {

		return doubleFire;

	}
	
	/*��ó�������ֵ*/
	public void addSuperFire() {
		
		this.superFire++;
		
	}
	
}
