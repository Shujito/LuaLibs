package org.shujito.lualibs;

import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;
import org.luaj.vm2.lib.TwoArgFunction;

public class PersonLib extends TwoArgFunction
{
	@Override
	public LuaValue call(LuaValue module, LuaValue env)
	{
		LuaValue library = tableOf();
		library.set("new", new New());
		LuaValue methods = tableOf();
		methods.set("name", new SetName());
		methods.set("lastname", new SetLastName());
		methods.set("age", new SetAge());
		methods.set("hello", new Hello());
		methods.set("greet", new Greet());
		methods.set("meet", new Meet());
		methods.set("__tostring", new ToString());
		//methods.set("__index", methods);
		library.set("Methods", methods);
		env.set("Person", library);
		return library;
	}
	
	static class New extends OneArgFunction
	{
		@Override
		public LuaValue call(LuaValue lib)
		{
			// the methods
			LuaValue methods = lib.get("Methods");
			// where to place arbitrary values (useful for js-like tricks)
			LuaValue values = tableOf();
			// use this metatable for the values table
			LuaValue valuesMetatable = tableOf();
			// use this metatable for each user
			LuaValue metatable = tableOf();
			// get the value or try getting the method if it exists
			valuesMetatable.set("__index", methods);
			values.setmetatable(valuesMetatable);
			// get a value
			metatable.set("__index", values);
			// set a value
			metatable.set("__newindex", values);
			return userdataOf(new Person(), metatable);
		}
	}
	
	static class SetName extends TwoArgFunction
	{
		@Override
		public LuaValue call(LuaValue luaPerson, LuaValue name)
		{
			Person p = Person.class.cast(luaPerson.checkuserdata(Person.class));
			p.setName(name.checkjstring());
			return luaPerson;
		}
	}
	
	static class SetLastName extends TwoArgFunction
	{
		@Override
		public LuaValue call(LuaValue luaPerson, LuaValue lastName)
		{
			Person p = Person.class.cast(luaPerson.checkuserdata(Person.class));
			p.setLastName(lastName.checkjstring());
			return luaPerson;
		}
	}
	
	static class SetAge extends TwoArgFunction
	{
		@Override
		public LuaValue call(LuaValue luaPerson, LuaValue age)
		{
			Person p = Person.class.cast(luaPerson.checkuserdata(Person.class));
			p.setAge(age.checkint());
			return luaPerson;
		}
	}
	
	static class Hello extends OneArgFunction
	{
		@Override
		public LuaValue call(LuaValue luaPerson)
		{
			Person p = Person.class.cast(luaPerson.checkuserdata(Person.class));
			return valueOf(p.hello());
		}
	}
	
	static class Greet extends OneArgFunction
	{
		@Override
		public LuaValue call(LuaValue luaPerson)
		{
			Person p = Person.class.cast(luaPerson.checkuserdata(Person.class));
			return valueOf(p.greet());
		}
	}
	
	static class Meet extends TwoArgFunction
	{
		@Override
		public LuaValue call(LuaValue luaPerson1, LuaValue luaPerson2)
		{
			Person p1 = Person.class.cast(luaPerson1.checkuserdata(Person.class));
			Person p2 = Person.class.cast(luaPerson2.checkuserdata(Person.class));
			return valueOf(p1.meet(p2));
		}
	}
	
	static class ToString extends OneArgFunction
	{
		@Override
		public LuaValue call(LuaValue luaPerson)
		{
			Person p = Person.class.cast(luaPerson.checkuserdata(Person.class));
			return valueOf(String.format("Person @%x", p.hashCode()));
		}
	}
}