package com.ge.exercise1;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Collection;
import java.util.Vector;
public class MyParser implements Parser {
	
    public Application parseApplicationData(String data)
    {  		
        Pattern structuredTextPattern = Pattern.compile("Application\\(id:\\ (.*),name:\\ (.*),users:\\[(.*)\\],groups:\\[(.*)\\]\\)$");
        Matcher patternGroups = structuredTextPattern.matcher(data);
        MyApplication newApp = null;
        if(patternGroups.matches())
        {
        	newApp = new MyApplication(patternGroups.group(1), patternGroups.group(2));
    
        	//Get all application users
        	String usersData = patternGroups.group(3);
        	Collection<NewUser> users = GetUsers(usersData);
        	//Add users to application
        	for(User newUser : users)
        	{
        		newApp.addUser(newUser);
        	}
        	
        	//Get all Groups
        	String groupData = patternGroups.group(4);
        	Collection<NewGroup> groups = GetGroups(groupData);
        	for(Group newGroup : groups)
        	{
        		newApp.addGroup(newGroup);
        	}	
    	}
        return newApp;
      
    }
    
    public Collection<NewUser>  GetUsers(String usersData)
    {
    	Collection<NewUser> userObjects = new Vector<NewUser>();
    	String[] users = usersData.split(",\\ ");
    	for(String user : users)
    	{
    		Pattern p = Pattern.compile("User\\(id:\\ (.*),name:\\ (.*)\\)$");
    		Matcher extractIdName = p.matcher(user);
    		extractIdName.matches();
    		userObjects.add(new NewUser(extractIdName.group(1), extractIdName.group(2)));
    	}
    	
    	return userObjects;
    }
    
    public Collection<NewGroup> GetGroups(String GroupData)
    {
    	Collection<NewGroup> applicationGrps = new Vector<NewGroup>();
    	String[] Groups = GroupData.split("(?<=]\\),\\ )");
    	for(String grp : Groups)
    	{
    		Pattern grpPattern = Pattern.compile("Group\\(id:\\ (.*),name:\\ (.*),users:\\[(.*)\\]\\).*");
    		Matcher grpData = grpPattern.matcher(grp);
    		grpData.matches();
    		NewGroup group = new NewGroup(grpData.group(1), grpData.group(2));
    		Collection<NewUser> grpUsers = GetUsers(grpData.group(3));
    		for(User user : grpUsers)
    		{
    			group.AddUser(user);
    		}
    		applicationGrps.add(group);
    	}
    	
    	return applicationGrps;
    } 
}


