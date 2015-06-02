package org.shujito.lualibs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;
import org.luaj.vm2.lib.TwoArgFunction;

public class FileLib extends TwoArgFunction
{
	public static final String TAG = FileLib.class.getSimpleName();
	
	@Override
	public LuaValue call(LuaValue module, LuaValue env)
	{
		LuaValue library = tableOf();
		library.set("open", new Open());
		LuaValue methods = tableOf();
		methods.set("__index", methods);
		methods.set("__gc", new Close());
		methods.set("close", new Close());
		methods.set("readline", new ReadLine());
		methods.set("read", new Read());
		library.set("Methods", methods);
		env.set("File", library);
		return library;
	}
	
	static final class Open extends TwoArgFunction
	{
		@Override
		public LuaValue call(LuaValue lib, LuaValue fn)
		{
			try
			{
				String fileName = fn.checkjstring();
				BufferedReader br = new BufferedReader(new FileReader(fileName));
				return userdataOf(br, lib.get("Methods"));
			}
			catch (Exception ex)
			{
				return error(ex.toString());
			}
		}
	}
	
	static final class ReadLine extends OneArgFunction
	{
		@Override
		public LuaValue call(LuaValue luaFile)
		{
			BufferedReader br = BufferedReader.class.cast(luaFile.checkuserdata(BufferedReader.class));
			try
			{
				String line = br.readLine();
				if (line == null)
					return NIL;
				else
					return valueOf(line);
			}
			catch (Exception e)
			{
				return error(e.toString());
			}
		}
	}
	
	static final class Read extends OneArgFunction
	{
		@Override
		public LuaValue call(LuaValue luaFile)
		{
			BufferedReader br = BufferedReader.class.cast(luaFile.checkuserdata(BufferedReader.class));
			try
			{
				return valueOf(br.read());
			}
			catch (Exception e)
			{
				return error(e.toString());
			}
		}
	}
	
	static final class Close extends OneArgFunction
	{
		@Override
		public LuaValue call(LuaValue luaFile)
		{
			try
			{
				BufferedReader br = BufferedReader.class.cast(luaFile.checkuserdata(BufferedReader.class));
				br.close();
			}
			catch (IOException e)
			{
				return error(e.toString());
			}
			return NIL;
		}
	}
}
