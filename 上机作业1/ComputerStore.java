package java½ø½×;

public class ComputerStore {
	public static void main(String[] args)
	{
		Computer a = new Computer("A", "Intel", "Asus", "Samsung", "Seagate");
		Computer b = new Computer("B", "AMD", "Gigabyte", "Samsung", "WestDigital");
		Computer c = new Computer("C", "Intel", "Gigabyte", "Kingston", "Seagate");
		a.showinfo();
		b.showinfo();
		c.showinfo();
	}
}
