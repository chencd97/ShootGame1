package cn.ccd.game.shoot;

/**
 * 
 * 	@Project ShootDemo(��ʽ��)
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

/** �ɻ���ս��Ϸ���� */
public class World extends JPanel implements GameLevel{

	private static final String PROJECT = "ShootDemo";	//��Ŀ����
	private static final String EDITION = "��ʽ��";	//��ǰ���
	private static final String VERSIONS = "1.2.2";	//�汾��
	private static final String AUTHOR = "CCD";	//����

	protected static final int WIDTH = 1280;	//�ɻ���ս�����ڿ�
	protected static final int HEIGHT = 800;	//�ɻ���ս�����ڸ�

	/*������Ϸ״̬*/
	private static final int START = 0;	//��Ϸ����ǰ
	private static final int RUNNING = 1;	//��Ϸ��
	private static final int PAUSE = 2;	//��Ϸ��ͣ
	private static final int GAME_OVRE = 3;	//��Ϸ����

	/*��ϷĬ��״̬*/
	private int state = START;

	/*��ϷĬ���Ѷ��ı�*/
	private String gameLevelMode = "null";

	/*������ҵ÷�*/
	private int score = 0;

	/*����RunTime*/
	private int RunTime = 0;

	/*��������*/
	private Sky sky = new Sky();	//������ն���
	private Hero hero = new Hero();	//����Ӣ�ۻ�����
	private FlyingObject[] enemies = {};	//�����л��������(��������)
	private Bullet[] bullet = {};	//�����ӵ��������

	/*��ȡ��Ϸ�Ѷȵȼ�*/
	public void getGameLevel(int level) {

		switch(level) {

		case LEVEL_1:	//1

			enemiesEnterActionNum = 13;	//���õ�������Ƶ��130����1��
			bulletShootActionNum = 12;	//�����ӵ�����Ƶ��120����1��
			gameLevelMode = "�����Ѷ�";	//������Ϸ�Ѷ��ı�����ʾ
			break;

		case LEVEL_2:	//2

			enemiesEnterActionNum = 5;	//���õ�������Ƶ��50����1��
			bulletShootActionNum = 10;	//�����ӵ�����Ƶ��100����1��
			gameLevelMode = "�߼��Ѷ�";	//������Ϸ�Ѷ��ı�����ʾ
			break;

		case LEVEL_3:	//3

			enemiesEnterActionNum = 2;	//���õ�������Ƶ��20����1��
			bulletShootActionNum = 8;	//�����ӵ�����Ƶ��80����1��
			gameLevelMode = "��̬�Ѷ�";	//������Ϸ�Ѷ��ı�����ʾ
			break;

		}

	}

	/*���ɵ������Ͳ����ظ����÷�*/
	public FlyingObject generateTheEnemy(){

		Random ran = new Random();	//�����������
		int type = ran.nextInt(23);	//���ֵ0~22

		/*����3�ֵл����ɵĸ���*/
		if(type <= 2) {	// 0-2

			return new Bee();	//����С�۷�

		}else if(type <= 12) {	// 3-12

			return new Airplane();	//����С�л�

		}else {	// 13-22

			return new BigAirplane();	//���ش�л�

		}

	}

	private int enemiesEnterActionIndex = 0;	//�л�������
	private int enemiesEnterActionNum = 1;	//�л�����������
	/*���ɵ��˲��볡*/
	public void enemiesEnterAction() {	//���ɵ��˲��볡

		enemiesEnterActionIndex++;	//����ÿ������һ��,��������1	(���ڴ˷�����ÿ10��������һ�Σ�����ÿ10����������Ż��1)
		if(enemiesEnterActionIndex % enemiesEnterActionNum == 0) {	//���ɵ��������� ���л������������� ���Ƴ�������

			FlyingObject tempEnemies = generateTheEnemy();	//�������ɵ��˷���,�������ؽ���浽tempEnemies��ʱ������
			enemies = Arrays.copyOf(enemies, enemies.length+1);	//��������������
			enemies[enemies.length-1] = tempEnemies;	//�����ɵĵ��˷��������������е����һλ

		}

	}

	private int bulletShootActionIndex = 0;	//�ӵ�������
	private int bulletShootActionNum = 1;	//�ӵ����ɿ�����
	/*�����ӵ����볡*/
	public void bulletShootAction() {

		bulletShootActionIndex++;	//����ÿ������һ��,��������1	(���ڴ˷�����ÿ10��������һ�Σ�����ÿ10����������Ż��1,����������ݡ��ӵ����ɿ������������ӵ�)
		if(bulletShootActionIndex % bulletShootActionNum == 0) {	//���������ȡ�������������������ӵ�,��������

			FlyingObject[] tempBullet = hero.generateTheBullet();	//���������ӵ�����,�������ؽ���浽tempBullet��ʱ������
			bullet = Arrays.copyOf(bullet, bullet.length + tempBullet.length);	//���ӵ���������
			System.arraycopy(tempBullet, 0, bullet, bullet.length - tempBullet.length, tempBullet.length);	//����ʱ�����е����ݸ��Ƶ�����������

		}

	}

	/*�������ƶ�����*/
	public void stepAction() {

		sky.step();	//�������step��������ձ���������
		for(int i = 0; i < enemies.length; i++) {	//������������

			enemies[i].step();	//���õ��������и�Ԫ�ص�step�����õ��˶�����

		}
		for(int i = 0; i < bullet.length; i++) {	//�����ӵ�����

			bullet[i].step();	//�����ӵ������и�Ԫ�ص�step�������ӵ�������

		}

	}

	/*������������*/
	public void outOfBoundsAction() {

		/*�л��������*/
		int outOfBoundsIndex = 0;	//�л�������/�±�ָʾ��
		FlyingObject[] newEnemies = new FlyingObject[enemies.length];	//���½�һ�� ��ʱ���� ��ԭ���ĵ������鳤�ȸ��ƹ���
		for(int i = 0; i < enemies.length; i++) {	//����ԭ��������(enemies[])

			if(!enemies[i].outOfBounds() && !enemies[i].isRemove()) {	//���õ�����������繦�ܺ͵��˵�ǰ״̬��⹦��,���û�����ҵ��˵�ǰ״̬��ΪREMOVE������ڲ�����

				newEnemies[outOfBoundsIndex] = enemies[i];	//��û����ĵ��˴�����ʱ�����е�ָ��λ��
				outOfBoundsIndex++;	//�л���������1

			}

		}
		enemies = Arrays.copyOf(newEnemies, outOfBoundsIndex);	//������ɺ���ʱ�����е������������¸���ԭ����(enemies[]),��ֹ��ʱ������ʹ����������¿��� (��ֹ�ڴ�й©)

		/*�ӵ��������*/
		outOfBoundsIndex = 0;	//�ӵ�������/�±�ָʾ��
		Bullet[] newBullet = new Bullet[bullet.length];	//���½�һ�� ��ʱ���� ��ԭ�����ӵ����鳤�ȸ��ƹ���
		for(int i = 0; i < bullet.length; i++) {	//����ԭ�ӵ�����(bullet[])

			if(!bullet[i].outOfBounds() && !bullet[i].isRemove()) {	//�����ӵ���������繦�ܺ��ӵ���ǰ״̬��⹦��,���û�������ӵ���ǰ״̬��ΪREMOVE������ڲ�����

				newBullet[outOfBoundsIndex] = bullet[i];	//��û������ӵ�������ʱ�����е�ָ��λ��
				outOfBoundsIndex++;	//�ӵ���������1

			}

		}
		bullet = Arrays.copyOf(newBullet, outOfBoundsIndex);	//������ɺ���ʱ�����е������������¸���ԭ����(bullet[]),��ֹ��ʱ������ʹ����������¿��� (��ֹ�ڴ�й©)

	}

	/*ʵ���ӵ�����˵���ײ*/
	public void bulletBangAction(){

		for(int i = 0; i < bullet.length; i++) {	//�����ӵ�����õ����ӵ�

			for(int j = 0; j < enemies.length; j++) {	//������������õ�������

				/*�ж��ӵ��Ƿ���е���(��Ҫ�жϵ�ǰ���ӵ��͵��˵�״̬������������Ĵ��״̬�ͽ����ڲ�)*/
				if(bullet[i].isLife() && enemies[j].isLife() && enemies[j].hit(bullet[i])) {

					bullet[i].goDead();	//�����ӵ����е�goDead()��������ǰ�ӵ�״̬����Ϊ����
					enemies[j].goDead();	//���õ������е�goDead()��������ǰ����״̬����Ϊ����

					if(enemies[j] instanceof Enemy) {	//�жϵ�ǰ�����Ƿ�ΪEnemy����

						Enemy temp = (Enemy)enemies[j];	//�����򽫵�ǰ��������ǿתΪEnemy����
						score += temp.getScore();	//����Enemy�ӿ��е�getScore()�����ӷ�

					}

					if(enemies[j] instanceof Award) {	//�жϵ�ǰ�����Ƿ�ΪAward����

						Award temp = (Award)enemies[j];	//�����򽫵�ǰ��������ǿתΪAward����

						/*����Award�ӿ��е�getAwardType()�����жϽ�������*/
						if(temp.getAwardType() >= 51) {	//50%

							hero.addDoubleFire();	//Ӣ�ۻ����˫������ֵ

						}else if(temp.getAwardType() >= 5) {	//45%

							hero.addLife();	//Ӣ�ۻ��������ֵ����

						}else {	//5%

							hero.addSuperFire();	//Ӣ�ۻ���ó�������ֵ

						}

					}

				}

			}

		}

	}

	/*ʵ��Ӣ�ۻ�����˵���ײ*/
	public void herotBangAction(){

		for(int j = 0; j < enemies.length; j++) {	//������������õ�������

			/*�ж�Ӣ�ۻ����������ײ(��Ҫ�жϵ�ǰ��Ӣ�ۻ��͵��˵�״̬������������Ĵ��״̬�ͽ����ڲ�)*/
			if(hero.isLife() && enemies[j].isLife() && enemies[j].hit(hero)) {

				enemies[j].goDead();	//���õ������е�goDead()��������ǰ����״̬����Ϊ����
				hero.subtractLife();	//����Ӣ�ۻ���subtractLife()����ʵ��Ӣ�ۻ�����һ������ֵ
				hero.clearDoubleFire();		//����Ӣ�ۻ���clearDoubleFire()����ʵ��Ӣ�ۻ���ջ���ֵ

			}

		}

	}

	/*�����Ϸ�Ƿ����*/
	public void checkGamoOvreAction() {

		if(hero.getLife() <= 0) {	//�����⵽Ӣ�ۻ�������ֵС�ڵ���0

			state = GAME_OVRE;	//���õ�ǰ��Ϸ״̬ΪGAME_OVRE(��Ϸ����״̬)

		}

	}

	/*���к���*/
	public void action() {

		/*����¼�֡����	��***��	*/
		MouseAdapter l = new MouseAdapter() {

			/*֡��������¼�*/
			public void mouseClicked(MouseEvent e) {

				switch(state) {	//��ȡ��ǰ��Ϸ״̬

				case START:	//���Ϊ����״̬

					state = RUNNING;	//����Ϸ��Ϊ����״̬
					break;

				case GAME_OVRE:	//���Ϊ��Ϸ����״̬��Ҫ����Ϸ�ڵ��������ݻָ�Ϊ��ʼ״̬���´�ʹ��

					score = 0;	//������Ϸ����
					hero = new Hero();	//����Ӣ�ۻ�״̬
					enemies = new FlyingObject[0];	//���õл�״̬
					bullet = new Bullet[0];	//�����ӵ�״̬
					state = START;	//������Ϸ״̬
					break;

				}

			}

			/*֡������ƶ��¼�*/
			public void mouseMoved(MouseEvent e) {

				if(state == RUNNING) {	//�ж������ǰ��Ϸ״̬Ϊ����״̬

					hero.moveTo(e.getX(), e.getY());	//�����񵽵����ݴ���Ӣ�ۻ���ʹӢ�ۻ���������ƶ�

				}

			}

			/*֡������Ƴ��¼�*/
			public void mouseExited(MouseEvent e) {

				if(state == RUNNING) {	//�����ǰ��ϷΪ����״̬

					state = PAUSE;	//����ǰ��Ϸ״̬����Ϊ��ͣ״̬

				}

			}

			/*֡����������¼�*/
			public void mouseEntered(MouseEvent e) {

				if(state == PAUSE) {	//�����ǰ��ϷΪ��ͣ״̬

					state = RUNNING;	//����ǰ��Ϸ״̬����Ϊ����״̬

				}

			}

		};

		this.addMouseListener(l);
		this.addMouseMotionListener(l);

		/*��ʱ���񴥷���	��***��	*/
		Timer timer = new Timer();
		int time = 10;	//�趨���񴥷�������ʱ����(����)
		timer.schedule(new TimerTask() {	//�������

			/*����һ�������ڲ���*/
			public void run() {	//���ڲ�������дrun()����

				if(state == RUNNING) {	//����Ϸ��״̬���� [������] ʱ������Ϸ����

					enemiesEnterAction();	//���õ����볡����
					bulletShootAction();	//�����ӵ��볡����
					stepAction();	//�����������ƶ�����
					outOfBoundsAction();	//��������������⹦��
					bulletBangAction();	//�����ӵ���л���ײ����
					herotBangAction();	//����Ӣ�ۻ���л���ײ����
					checkGamoOvreAction();	//������Ϸ�����жϹ���

				}

				repaint();	//�ظ�ִ�����ϴ���

			}

		}, time, time);	//�������к�ȴ�time���ִ�д˲���,ÿ�β������м��time��
		//timer.schedule( �����б�,  �������к���ִ��,   ÿ�����м�� )
	}

	/*���������ݷ���	��***��	*/
	public void paint(Graphics g) {

		sky.paintObject(g);	//�����
		hero.paintObject(g);	//��Ӣ�ۻ�

		for(int i = 0; i < enemies.length; i++) {	//��������

			enemies[i].paintObject(g);	//���л�

		}

		for(int i = 0; i < bullet.length; i++) {	//�����ӵ�

			bullet[i].paintObject(g);	//���ӵ�

		}

		switch(state) {	//���ݵ�ǰ��Ϸ״̬��ͼƬ

		case START:	//�������Ϸ����״̬

			g.drawImage(Images.start, 0, 0, null);	//����Ϸ����ͼ
			break;

		case PAUSE:	//�������Ϸ��ͣ״̬

			g.drawImage(Images.pause, 0, 0, null);	//����Ϸ��ͣͼ
			break;

		case GAME_OVRE:	//�������Ϸ����״̬

			g.drawImage(Images.gameOver, 0, 0, null);	//����Ϸ����ͼ
			break;

		}

		/*�û�������ʾ��Ϣ*/
		g.drawString("��ǰ�÷֣�[ "+score+" ]", World.WIDTH/2-80, 20);
		g.drawString("��ǰ��Ϸ�Ѷȣ�[ "+gameLevelMode+" ]", 10, 20);
		g.drawString("ʣ��������"+hero.getLife(), 10, 40);
		g.drawString("��ǰͬ������������"+enemies.length, 10, 60);
		g.drawString("��ǰͬ���ӵ�������"+bullet.length, 10, 80);
		g.drawString("��ǰ����ģʽ��"+hero.getDoubleFire(), World.WIDTH/2-80, World.HEIGHT-60);
		if(hero.getDoubleFire().equals("˫������")) {
			g.drawString("ʣ�����ֵ��"+hero.getDoubleFireNum(), World.WIDTH/2-69, World.HEIGHT-80);
		}

		/*�汾��Ϣ*/
		g.drawString(PROJECT+" ( "+EDITION+" )", 10, World.HEIGHT-140);
		g.drawString("Versions "+VERSIONS, 10, World.HEIGHT-120);
		g.drawString("Author "+AUTHOR, 10, World.HEIGHT-100);
		g.drawString("CopyRight (C) 2019 "+AUTHOR, 10, World.HEIGHT-80);
		g.drawString("RunTime��["+(RunTime++)+"]", 10, World.HEIGHT-60);

	}

	/*��Ϸ������(��Ϸ���)*/
	public static void main(String[] ccd) {

		World world = new World();
		Scanner scan = new Scanner(System.in);	//�����û�����
		System.out.println("����ӭ����ɻ���ս��Ϸ��"+VERSIONS);
		System.out.println();
		System.out.println("��������ϷĿ��ֻ��Ϊ�˴����о�������");
		System.out.println("����Ϸ�г���ĳЩBUG������������");
		System.out.println("�˰汾�������հ汾����Ҳ�������������и��£�������ʱ����");
		System.out.println();
		System.out.println("����������ѡ����Ϸ�Ѷ� [�Ѷ�Խ�ߵл�Խ��]");
		System.out.println("1.�������Ѷȡ�");
		System.out.println("2.���߼��Ѷȡ�");
		System.out.println("3.����̬�Ѷȡ�");
		System.out.println("��ѡ��");

		/*ѭ���ж�������벢���ö�Ӧ�Ѷ�*/
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

				System.out.println("�������������ѡ��");

			}

		}while(true);

		scan.close();	//�ر��û�����

		/* ��***�� */
		JFrame frame = new JFrame();
		frame.add(world);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(World.WIDTH, World.HEIGHT);
		frame.setLocationRelativeTo(null); 
		frame.setVisible(true); 

		world.action();	//�������к���

	}

}
