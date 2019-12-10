package cn.ccd.game.shoot;

/**游戏难度接口*/
public interface GameLevel {
	
	public static final int LEVEL_1 = 1;
	public static final int LEVEL_2 = 2;
	public static final int LEVEL_3 = 3;
	
	public abstract void getGameLevel(int level);

}
