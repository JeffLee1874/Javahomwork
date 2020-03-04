package java½ø½×;

public interface mainboard {
	public String name = "mainboard";
	public int speed = 0;
	public int price = 0;
	
	default void board_work()
	{
		System.out.println("mainborad work");
	}
}
