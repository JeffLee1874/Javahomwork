package java½ø½×;

public interface CPU {
	public String name = "CPU";
	public int coreNum = 0;
	public int price = 0;
	
	default void CPU_work()
	{
		System.out.println("CPU work");
	}
}
