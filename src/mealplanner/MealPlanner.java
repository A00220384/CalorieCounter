package mealplanner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MealPlanner { 
static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
static final String DB_URL = "jdbc:mysql://localhost:3306/foodsql";
static final String USER = "root";
static final String PASS = "admin";	
	public static Connection init_db()
	{
             Connection con= null; 
                try{
                    try {
                        Class.forName(JDBC_DRIVER);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(MealPlanner.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        System.out.println("connecting to the database");
			con = DriverManager.getConnection(DB_URL, USER, PASS);        
		}
		catch(SQLException err)
		{
                        System.out.println("error connecting to the database");
			System.out.println(err.getMessage());
		}   
            return con;
	}
    public static void addToMeal(Meal newMeal)
	{           
		try{
			Class.forName(JDBC_DRIVER);
                        Connection connect = DriverManager.getConnection(DB_URL, USER, PASS);
                        //Statement st = connect.createStatement();
			String str = "INSERT INTO meal VALUES(?,?,?,?,?,?)";
			PreparedStatement pstmt = connect.prepareStatement(str);
                        
			pstmt.setInt(1, newMeal.getWeight());//maybe shoube be int 2
			pstmt.setString(2, newMeal.getName());
			pstmt.setFloat(3, newMeal.getTotalCarbs());
			pstmt.setFloat(4, newMeal.getTotalProtein());
                        pstmt.setFloat(5, newMeal.getTotalFat());
			pstmt.setFloat(6, newMeal.getTotalCalories());
			pstmt.executeUpdate();
			System.out.println("Added to meal");
                        connect.close();

		}
		catch(Exception e)
		{
			System.out.println("Error adding meal");
			e.printStackTrace();
		}
    }
	 
    public static void addToFood(Food food)
    {
		try{
			Class.forName(JDBC_DRIVER);
                        Connection connect = DriverManager.getConnection(DB_URL, USER, PASS);
                        //Statement st = connect.createStatement();
			/*String str = "INSERT INTO food VALUES("+food.getName()+","+food.getCarbsPerGram()+","+food.getProteinPerGram()+
                        ","+food.getFatPerGram()+","+food.getCaloriesPerGram()+")";*/
                        String str = "INSERT INTO food VALUES(?,?,?,?,?)";
			PreparedStatement pstmt = connect.prepareStatement(str);
 
			pstmt.setString(1, food.getName());
                        pstmt.setFloat(2, food.getCarbsPerGram());
			pstmt.setFloat(3, food.getProteinPerGram());
			pstmt.setFloat(4, food.getFatPerGram());
                        pstmt.setFloat(5, food.getCaloriesPerGram());
                        pstmt.executeUpdate();
			System.out.println("Added to food");
                        connect.close();
		}
		catch(Exception e)
		{
			System.out.println("Error adding food");
			e.printStackTrace();
		}
	} 
}
