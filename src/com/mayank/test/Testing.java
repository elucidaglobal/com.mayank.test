/**
 * 
 */
package com.mayank.test;

/**
 * @author HP PC
 *
 */

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;
public class Testing {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("-------- MySQL JDBC Connection Testing ------------");
		 
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return;
		}
	 
		System.out.println("MySQL JDBC Driver Registered!");
		Connection connection = null;
	 
		try {
			connection= DriverManager.getConnection("jdbc:mysql://elucidaglobal.com/elucida","restricted","N*i;PR9PV63n");
	 
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}
		try {
	        // The newInstance() call is a work around for some
	        // broken Java implementations

	        Class.forName("com.mysql.jdbc.Driver").newInstance();
	    } catch (Exception ex) {
	        // handle the error
	    }
		//clients\client_short_name\project_name\case_id+f_name+m_name+l_name\in\file_name
		if (connection != null) {
			System.out.println("You made it, take control your database now!");
			try {
				Statement stmt = (Statement) connection.createStatement();
				String query= "SELECT client.short_name, project.name, cases.case_id, cases.first_name,cases.middle_name,cases.last_name, file.name FROM project, client,cases,file WHERE cases.case_id=file.case_id AND cases.project_id=project.project_id AND client.client_id = project.client_id AND file.file_id>50";
				ResultSet status= stmt.executeQuery(query);
				String abc;
				while (status.next())
				{
					abc="clients\\"+status.getObject(1)+"\\"+status.getObject(2)+"\\"+status.getObject(3)+status.getObject(4)+status.getObject(5)+status.getObject(6)+"\\in\\"+status.getObject(7);
					//	abc= (String) status.getObject(7);	
					
							//int numColumns = status.getMetaData().getColumnCount();
					//for ( int i = 1 ; i <= numColumns ; i++ )
					//{
				//System.out.print(status.getObject(i));
					//}
					System.out.println(abc);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		} else {
			System.out.println("Failed to make connection!");
		}

	}

}
