package Annotation_Reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SqlUtil {
	private StringBuilder stringbuilder = new StringBuilder();
	private int flag = 0;   //判断是否是table类型，不是则拒绝调用
	private Class<? extends Object> cl;   //类对象
	private Field[] fields;
	private boolean field_flag;
	private Column column;
	private String field_name;
	private Method menthod;
	private Object val;
	private Object object;
	
	
	public String query(Object object) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		StringBuilder sb = new StringBuilder();
		Class j = object.getClass();
		if(j.isAnnotationPresent(Table.class))
		{
			cl = j;
			flag = 1;
			fields = cl.getDeclaredFields();
			this.object = object;
		}
		if(flag == 1)
		{
			Table table = (Table) cl.getDeclaredAnnotation(Table.class);
			sb.append("SELECT * FROM ").append(table.value());
			for(Field i : fields)
			{
				field_flag = i.isAnnotationPresent(Column.class);
	            if (!field_flag) {
	                continue;
	            }
	            column = i.getDeclaredAnnotation(Column.class);
	            field_name = i.getName();
	            menthod = cl.getDeclaredMethod("get" + field_name);
	            val = menthod.invoke(object);
	            // 判断该字段是否为空，不为空，才进行append
	            if (!Objects.isNull(val)) {
	                if((val instanceof Integer) && (Integer) val == 0){
	                    continue;
	                }
	                sb.append(" AND ").append(column.value()).append(" = ");
	                sb.append(val);
	            }
			}
			
		}
		return sb.toString();
	}
	
	public String insert(Object object) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		if(object.getClass() == ArrayList.class)
		{
			int count = 0;
			StringBuilder sb = new StringBuilder();
			String str = "";
			StringBuilder temp = new StringBuilder();
			for(Object ob : (List)object)
			{
				Class j = ob.getClass();
				if(j.isAnnotationPresent(Table.class))
				{
					cl = j;
					flag = 1;
					fields = cl.getDeclaredFields();
					this.object = ob;
				}
				if(flag == 1)
				{
					Table table = (Table) cl.getDeclaredAnnotation(Table.class);
					if(count == 0)
					{
						sb.append("INSERT INTO ").append(table.value()).append("(");
						for(Field i : fields)
						{
							field_flag = i.isAnnotationPresent(Column.class);
				            if (!field_flag) {
				                continue;
				            }
				            column = i.getDeclaredAnnotation(Column.class);
				            field_name = i.getName();
				            menthod = cl.getDeclaredMethod("get" + field_name);
				            val = menthod.invoke(ob);
				            // 判断该字段是否为空，不为空，才进行append
				            if (!Objects.isNull(val)) {
				                if((val instanceof Integer) && (Integer) val == 0){
				                    continue;
				                }
				                sb.append(field_name + ", ");
				            }
						}
						str += sb.toString().substring(0, sb.length()-2);
						str += ") VALUES (";
						count++;
					}
					for(Field i : fields)
					{
						field_flag = i.isAnnotationPresent(Column.class);
			            if (!field_flag) {
			                continue;
			            }
			            column = i.getDeclaredAnnotation(Column.class);
			            field_name = i.getName();
			            menthod = cl.getDeclaredMethod("get" + field_name);
			            val = menthod.invoke(ob);
			            // 判断该字段是否为空，不为空，才进行append
			            if (!Objects.isNull(val)) {
			                if((val instanceof Integer) && (Integer) val == 0){
			                    continue;
			                }
			                temp.append(val + ", ");
			            }
					}
					temp.delete(temp.lastIndexOf(","), temp.length());
					temp.append("), (");
				}
			}
			str += temp.toString().substring(0, temp.length()-3);
			return str;
		}
		else
		{
			StringBuilder sb = new StringBuilder();
			String str = "";
			StringBuilder temp = new StringBuilder();
			Class j = object.getClass();
			if(j.isAnnotationPresent(Table.class))
			{
				cl = j;
				flag = 1;
				fields = cl.getDeclaredFields();
				this.object = object;
			}
			if(flag == 1)
			{
				Table table = (Table) cl.getDeclaredAnnotation(Table.class);
				sb.append("INSERT INTO ").append(table.value()).append("(");
				temp.append(") VALUES (");
				for(Field i : fields)
				{
					field_flag = i.isAnnotationPresent(Column.class);
		            if (!field_flag) {
		                continue;
		            }
		            column = i.getDeclaredAnnotation(Column.class);
		            field_name = i.getName();
		            menthod = cl.getDeclaredMethod("get" + field_name);
		            val = menthod.invoke(object);
		            // 判断该字段是否为空，不为空，才进行append
		            if (!Objects.isNull(val)) {
		                if((val instanceof Integer) && (Integer) val == 0){
		                    continue;
		                }
		                sb.append(field_name + ", ");
		                temp.append(val + ", ");
		            }
				}
				str += sb.toString().substring(0, sb.length()-2);
				str += temp.toString().substring(0, temp.length()-2);
			}
			return str+")";
		}
	}
	
	public String delete(Object object) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		StringBuilder sb = new StringBuilder();
		Class j = object.getClass();
		if(j.isAnnotationPresent(Table.class))
		{
			cl = j;
			flag = 1;
			fields = cl.getDeclaredFields();
			this.object = object;
		}
		if(flag == 1)
		{
			Table table = (Table) cl.getDeclaredAnnotation(Table.class);
			sb.append("DELETE FROM ").append(table.value());
			for(Field i : fields)
			{
				field_flag = i.isAnnotationPresent(Column.class);
	            if (!field_flag) {
	                continue;
	            }
	            column = i.getDeclaredAnnotation(Column.class);
	            field_name = i.getName();
	            menthod = cl.getDeclaredMethod("get" + field_name);
	            val = menthod.invoke(object);
	            // 判断该字段是否为空，不为空，才进行append
	            if (!Objects.isNull(val)) {
	                if((val instanceof Integer) && (Integer) val == 0){
	                    continue;
	                }
	                sb.append(" WHERE ").append(column.value()).append(" = ");
	                sb.append(val);
	            }
			}
			
		}
		return sb.toString();
	}

	public String update(Object object) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		StringBuilder sb = new StringBuilder();
		int count = 0;
		Class j = object.getClass();
		if(j.isAnnotationPresent(Table.class))
		{
			cl = j;
			flag = 1;
			fields = cl.getDeclaredFields();
			this.object = object;
		}
		if(flag == 1)
		{
			Table table = (Table) cl.getDeclaredAnnotation(Table.class);
			sb.append("UPADTE ").append(table.value());
			for(Field i : fields)
			{
				field_flag = i.isAnnotationPresent(Column.class);
	            if (!field_flag) {
	                continue;
	            }
	            column = i.getDeclaredAnnotation(Column.class);
	            field_name = i.getName();
	            menthod = cl.getDeclaredMethod("get" + field_name);
	            val = menthod.invoke(object);
	            // 判断该字段是否为空，不为空，才进行append
	            if (!Objects.isNull(val) && count == 0) {
	                if((val instanceof Integer) && (Integer) val == 0){
	                    continue;
	                }
	                sb.append(" SET ").append(column.value()).append(" = ");
	                sb.append(val + " ");
	                count++;
	            }
	            else if(!Objects.isNull(val) && count == 1)
	            {
	            	if((val instanceof Integer) && (Integer) val == 0){
	                    continue;
	                }
	            	sb.append("WHERE ").append(column.value()).append(" = ");
	                sb.append(val);
	            }
			}
			
		}
		return sb.toString();
	}
}
