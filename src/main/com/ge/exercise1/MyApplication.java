package com.ge.exercise1;

import java.util.Collection;
import java.util.Vector;

public class MyApplication extends Application{
	
	private Collection<User> users;
	private Collection<Group> groups;
	
	public MyApplication(String id, String name) {
		super(id, name);
		users = new Vector<User>();
		groups = new Vector<Group>();
	}
	
	public Collection<User> getUsers()
	{
		return users;
	}

    public User getUser(String userId)
    {
    	//User user = new user();
    	for(User user : users)
    	{
    		if(userId.equals(user.getId()))
    		{
    			return user;
    		}
    	}
    	return new NewUser("", "");
    }

    public Collection<Group> getGroups()
    {
    	return groups;
    }

    public Group getGroup(String groupId)
    {
    	for(Group group : groups )
    	{
    		if(groupId.equals(group.getId()))
    		{
    			return group;
    		}
    	}
    	return new NewGroup("", "");
    }
    
    public void addUser(User user)
    {
    	User existingUser = getUser(user.getId());
    	if(existingUser.getId().equals(""))
    	{
    		users.add(user);
    	}
    }
    
    public void addGroup(Group group)
    {
    	Group existingGrp = getGroup(group.getId());
    	if(existingGrp.getId().equals(""))
    	{
    		groups.add(group);
    	}
    }
}
