package java½ø½×;

public interface disk {
	public String name = "disk";
	public int speed = 0;
	public int price = 0;
	
	default void disk_work()
	{
		System.out.println("disk work");
	}
}
