package mealplanner;

import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static mealplanner.ReadFoodPanel.JDBC_DRIVER;

public class ReadMeal extends JPanel{
private static final int FIELD_WIDTH = 20;
static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
static final String DB_URL = "jdbc:mysql://localhost:3306/foodsql";
static final String USER = "root";
static final String PASS = "admin";
public ReadMeal() {   
    setLayout(new GridLayout(0,6));
        try {
            Class.forName(JDBC_DRIVER);
            Connection connect = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery("Select * from meal");
            while (rs.next()) {
                String weight = Integer.toString(rs.getInt("weight"));
                add(new JLabel("Weight:"));
                add(new JLabel(weight));
                String name = rs.getString("name");
                add(new JLabel("Name:"));
                add(new JLabel(name));
                String carbs = Float.toString(rs.getFloat("totalCarbs"));
                add(new JLabel("Carbs:"));
                add(new JLabel(carbs));
                String protein = Float.toString(rs.getFloat("totalProtein"));
                add(new JLabel("Protein:"));
                add(new JLabel(protein));
                String fat = Float.toString(rs.getFloat("totalFat"));
                add(new JLabel("Fat:"));
                add(new JLabel(fat));
                String cal = Float.toString(rs.getFloat("totalCal"));
                add(new JLabel("Calories:"));
                add(new JLabel(cal));
                add(new JLabel(""));
                add(new JLabel(""));
                add(new JLabel(""));
                add(new JLabel(""));
                add(new JLabel(""));
                add(new JLabel(""));               
            }
        } 
        catch (Exception ex) {
        }                        
    }
}
