package java½ø½×;

public interface memory {
	public String name = "memory";
	public int volume = 0;
	public int price = 0;
	
	default void mem_work()
	{
		System.out.println("memory work");
	}
}
