package cn.ccd.game.shoot;

/**奖励接口*/
public interface Award {
	
//	public static final int LIFE = 0;	//为1时奖励生命数量
//	public static final int DOUBLE_Fire = 1;	//为2时奖励火力值
	
	/*判断奖励类型，需要子类重写*/
	public abstract int getAwardType();

}
