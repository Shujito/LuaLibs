package org.shujito.experiments;

public class Person
{
	public static final String TAG = Person.class.getSimpleName();
	private String name;
	private String lastName;
	private int age;
	
	public String getName()
	{
		return this.name;
	}
	
	public String getLastName()
	{
		return this.lastName;
	}
	
	public int getAge()
	{
		return this.age;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	
	public void setAge(int age)
	{
		this.age = age;
	}
	
	public String hello()
	{
		return "Hello, I'm " + this.name;
	}
	
	public String greet()
	{
		return "Hi there, my name is " + this.name + " " + this.lastName + ", and I'm " + this.age + " years old";
	}
	
	public String meet(Person person)
	{
		if (person == this)
			return "Hey, it's me! " + this.name;
		return "Hi, " + person.name + ". I'm " + this.name + " " + this.lastName;
	}
}
