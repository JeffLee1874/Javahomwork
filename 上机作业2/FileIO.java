package IO_homework;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;

public class FileIO {
	HashMap<String, Integer> counts = new HashMap<String, Integer>();
	public void count()
	{
		Scanner scan = null;
		try 
		{
			File file = new File("C:\\Users\\Administrator\\Downloads\\了不起的盖茨比英文.txt");
			scan = new Scanner(new FileInputStream(file));
			while(scan.hasNext())
			{
				String str = scan.next();
				if(counts.containsKey(str))
					counts.put(str, counts.get(str)+1);
				else
					counts.put(str, 1);
			}
		}catch(Exception e)
		{
			e.toString();
		}finally
		{
			try {
				scan.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void OutputResult()
	{
		String path = "C:\\Users\\Administrator\\Downloads\\result.txt";
		BufferedWriter bw = null;
		try
		{
			File file = new File(path);
			if(!file.exists())
				file.getParentFile().mkdirs();
			file.createNewFile();
			
			//wirte
			FileWriter fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			
			List<HashMap.Entry<String,Integer>> list = new ArrayList<>(counts.entrySet());
			Collections.sort(list,new Comparator<HashMap.Entry<String, Integer>>() {
				@Override
				public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
					return o2.getValue()-o1.getValue(); //重写排序规则，小于0表示升序，大于0表示降序
				}
			});
			
			Iterator<HashMap.Entry<String,Integer>> i = list.iterator();
			while(i.hasNext())
			{
				Entry<String,Integer> ii= i.next();
				System.out.println(ii.getKey() + " " + ii.getValue());
				bw.write(ii.getKey() + " " + ii.getValue());
				bw.newLine();

			}
			bw.flush();
			
		}catch(Exception e)
		{
			e.toString();
		}finally
		{
			try {
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args)
	{
		FileIO io = new FileIO();
		io.count();
		io.OutputResult();
	}
}
