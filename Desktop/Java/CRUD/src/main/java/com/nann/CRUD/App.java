package com.nann.CRUD;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.table.DefaultTableModel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Hello world!
 *
 */
public class App 
{
    
    public static boolean processCreate(String user, String name, String email, String d) {
        try
        {
        	Map<String, Object> myNewList = new HashMap<String,Object>();
            myNewList.put("username", user);
            myNewList.put("name", name);
            myNewList.put("email", email);
            myNewList.put("dob", d);
            ListDao.create("/account", myNewList);
           // id++;
            return true;
        }
        catch(Exception e)
        {
        	
        	e.printStackTrace();
        	return false;
        }
    }
	

    @SuppressWarnings({ "unchecked" })
	public static DefaultTableModel processRead() {
    	DefaultTableModel model = new DefaultTableModel();
		
		
		
		Object[] field = {"Name", "Username", "email", "Date Of Birth", "Delete", "Update"};
		model.setColumnIdentifiers(field);
		
        String listStr = ListDao.read("/account");
        try {
            List<Map<String, Object>> list = new ObjectMapper().readValue(listStr, List.class);
            for (Map<String,Object> map : list) {
            {
            	String name = map.get("name").toString();	
            	String username = map.get("username").toString();
            	String email = map.get("email").toString();
            	String dob = map.get("dob").toString();
            	//String id = map.get("id").toString();
            	model.addRow(new Object[] {name, username, email, dob, "Delete", "Updae"});
            }
            }
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return model;
    }

    public static boolean processUpdate(String username, String name, String email, String dob) {
        try
        {
        	Map<String, Object> listToBeUpdated = new HashMap<String,Object>();
            listToBeUpdated.put("username", username);
            listToBeUpdated.put("name", name);
            listToBeUpdated.put("email", email);
            listToBeUpdated.put("dob", dob);
            ListDao.update("/account", username, listToBeUpdated);
            return true;
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        	return false;
        }
	}

    @SuppressWarnings("unused")
	public static boolean processDelete(String username) {
        try
        {
        	ListDao.delete("/account", username);
        	return true;
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        	return false;
        }
    }

}
