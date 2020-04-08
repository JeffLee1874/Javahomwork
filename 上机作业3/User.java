package Annotation_Reflection;
import Annotation_Reflection.*;

@Table("user")
public class User {
	@Column("id")
    private int id;
	private boolean isIdSet = false;
	
    @Column("username")
    private String username;
    
    @Column("age")
    private int age;
    private boolean isAgeSet = false;

    @Column("email")
    private String email;

    @Column("telephone")
    private String telephone;

    public int getid()
    {
		return id;
    }
    
    public String getusername()
    {
		return this.username;
    }
    
    public int getage()
    {
		return age;
    }
    
    
    public String getemail()
    {
		return this.email;
    }
    
    public String gettelephone()
    {
    	return this.telephone;
    }

    public void setid(int i)
    {
    	this.id = i;
    	this.isIdSet = true;
    }
    
    public void setusername(String i)
    {
    	this.username = i;
    }
    
    public void setage(int i)
    {
    	this.age = i;
    	this.isAgeSet = true;
    }
    public void setemail(String i)
    {
    	this.email = i;
    }
    public void settelephone(String i)
    {
    	this.telephone = i;
    }
}
