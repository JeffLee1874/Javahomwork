package java½ø½×;

public class Computer implements CPU, mainboard, memory, disk{
	private String name = "";
	private String cpuname = "";
	private String diskname = "";
	private String memoryname = "";
	private String mainboardname = "";
	private int totalprice = 0;
	
	
	public Computer(String name, String CPU, String mainboard, String memory, String disk)
	{
		this.name = name;
		if(CPU.equals("Intel"))
		{
			Intel cpu = new Intel();
			this.cpuname = cpu.name;
			this.totalprice += cpu.price;
		}
		else
		{
			AMD cpu = new AMD();
			this.cpuname = cpu.name;
			this.totalprice += cpu.price;
		}
		
		if(mainboard.equals("Asus"))
		{
			Asus mb = new Asus();
			this.mainboardname = mb.name;
			this.totalprice += mb.price;
		}
		else
		{
			Gigabyte gb = new Gigabyte();
			this.mainboardname = gb.name;
			this.totalprice += gb.price;
		}
		
		if(disk.equals("Intel"))
		{
			WestDigitals di = new WestDigitals();
			this.diskname = di.name;
			this.totalprice += di.price;
		}
		else
		{
			Seagate di = new Seagate();
			this.diskname = di.name;
			this.totalprice += di.price;
		}
		
		if(memory.equals("Kingston"))
		{
			Kingston mem = new Kingston();
			this.memoryname = mem.name;
			this.totalprice += mem.price;
		}
		else
		{
			Samsung mem = new Samsung();
			this.memoryname = mem.name;
			this.totalprice += mem.price;
		}
	}
	
	public void showinfo()
	{
		CPU_work();
		board_work();
		disk_work();
		mem_work();
		System.out.println("This computer named: " + name);
		System.out.println("It contains: CPU-" + cpuname);
		System.out.println("             disk-" + diskname);
		System.out.println("             memory-" + memoryname);
		System.out.println("             mainborad-" + mainboardname);
		System.out.println("And the price is $" + totalprice);
		System.out.println();
	}
}
