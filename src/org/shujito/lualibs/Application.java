package org.shujito.lualibs;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LoadState;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.compiler.LuaC;
import org.luaj.vm2.lib.DebugLib;
import org.luaj.vm2.lib.PackageLib;
import org.luaj.vm2.lib.StringLib;
import org.luaj.vm2.lib.jse.JseBaseLib;

public class Application
{
	public static final String TAG = Application.class.getSimpleName();
	
	public static void main(String[] args)
	{
		//JsePlatform.debugGlobals();
		Globals globals = new Globals();
		globals.load(new JseBaseLib());
		globals.load(new PackageLib());
		globals.load(new StringLib());
		//globals.load(new TableLib());
		globals.load(new DebugLib());
		// custom lib!
		globals.load(new PersonLib());
		LoadState.install(globals);
		LuaC.install(globals);
		//String script = "return function() local alice = Person:new():name('Alice'):lastname('Margatroid'):age(25) print(alice:greet()) end";
		//LuaValue chunk = globals.load(script);
		LuaValue chunk = globals.loadfile("script.lua");
		LuaValue fn = chunk.call();
		fn.call();
		//LuaValue lv = fn.call(CoerceJavaToLua.coerce(th));
		//LuaValue lv = fn.call();
		//Person person = Person.class.cast(lv.checkuserdata(Person.class));
		//System.out.println("lv:" + lv);
		//Person pk = Person.class.cast(CoerceLuaToJava.coerce(lv, Person.class));
		//System.out.println(person.greet());
	}
}
