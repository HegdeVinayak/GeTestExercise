package com.ge.exercise1;

import java.util.Collection;
import java.util.Vector;

public class NewGroup extends Group{
	
	private Collection<User> users; 

	public NewGroup(String id, String name) {
		super(id, name);
		users = new Vector<User>();
		size = users.size();
	}
	
	public String GetUser(String Id)
	{
		for(User user : users )
		{
			if(Id.equals(user.getId()))
			{
				return user.getName();
			}
		}
		
		return "";
	}
	
	public void AddUser(User user)
	{
		if(0 == GetUser(user.getId()).length())
		{
			users.add(user);
			size = users.size();
		}
	}
}
