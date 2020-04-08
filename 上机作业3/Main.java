package Annotation_Reflection;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		 SqlUtil util = new SqlUtil();
	     // test query1
	     User user = new User();
	     user.setid(175);
	     System.out.println(util.query(user));
	     // print: SELECT * FROM user WHERE id = 175

	     // test query2
	     user = new User();
	     user.setusername(" ∑»Ÿ’Í");
	     System.out.println(util.query(user));
	     // print: SELECT * FROM `user` WHERE `username` LIKE ' ∑»Ÿ’Í';

	     // test insert
	     user = new User();
	     user.setusername("user");
	     user.settelephone("12345678123");
	     user.setemail("user@123.com");
	     user.setage(20);
	     System.out.println(util.insert(user));
	     // print: INSERT INTO `user` (`username`, `telephone`, `email`, `age`) VALUES ('user', '12345678123', 'user@123.com', 20)
	        
	     // test insert list
	     User user2 = new User();
	     user2.setusername("user2");
	     user2.settelephone("12345678121");
	     user2.setemail("user2@123.com");
	     user2.setage(20);
	     List<User> list = new ArrayList<>();
	     list.add(user);
	     list.add(user2);
	     System.out.println(util.insert(list));
	     // print: INSERT INTO `user` (`username`, `telephone`, `email`, `age`) VALUES ('user', '12345678123', 'user@123.com', 20), ('user2', '12345678121', 'user2@123.com', 20)
	        
	     // test update
	     user = new User();
	     user.setid(1);
	     user.setemail("change@123.com");
	     System.out.println(util.update(user));
	     // print: UPDATE `user` SET `email` = 'change@123.com' WHERE `id` = 1;
	        
	     // test delete
	     user = new User();
	     user.setid(1);
	     System.out.println(util.delete(user));
	     // print: DELETE FROM `user` WHERE `id` = 1;
	}
}
