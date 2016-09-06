package mealplanner;

import javax.swing.JButton;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.NumberFormat;
import javax.swing.text.NumberFormatter;

public class ReadFoodPanel extends JPanel {
        static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        static final String DB_URL = "jdbc:mysql://localhost:3306/foodsql"; 
        static final String USER = "root";
        static final String PASS = "admin";
        public ReadFoodPanel() {
		setLayout(new GridLayout(0,4));
 
                NumberFormat format = NumberFormat.getInstance();
                NumberFormatter formatter = new NumberFormatter(format);
                formatter.setValueClass(Integer.class);
                formatter.setMinimum(0);
                formatter.setMaximum(Integer.MAX_VALUE);
                formatter.setAllowsInvalid(false);
                formatter.setCommitsOnValidEdit(true);
                JFormattedTextField weightField = new JFormattedTextField(formatter);
                        
		final JComboBox combo = new JComboBox();
		final JTextField nameField = new JTextField();
                final JTextField carbField = new JTextField();
                final JTextField proteinField = new JTextField();
                final JTextField fatField = new JTextField();
                final JTextField calorieField = new JTextField();
                carbField.setEditable(false);
                proteinField.setEditable(false);
                fatField.setEditable(false);
                calorieField.setEditable(false);
                               
		nameField.addKeyListener(new KeyAdapter() {
                        @Override
			public void keyReleased(KeyEvent e) {
		        String ch = nameField.getText();
			combo.removeAllItems();
			if (ch.equals("")) {
                            combo.setVisible(false);
			} 
                        else {
                            System.out.println(ch);
                            try {
                                Class.forName(JDBC_DRIVER);
                                Connection connect = DriverManager.getConnection(DB_URL, USER, PASS);
                                Statement st = connect.createStatement();
                                ResultSet rs = st.executeQuery("Select Name from food where name like '"+ ch + "%'");
                                while (rs.next()) {
                                    String name = rs.getString("name");
                                    if (name.equals("")) {
                                        combo.addItem("");
                                        combo.setVisible(false);
                                    } 
                                    else {
                                        combo.addItem(rs.getString("name"));
                                        System.out.println(rs.getString("name"));
                                        combo.setVisible(true);
                                        
                                    }
                                }
                                connect.close();
                            } 
                            catch (Exception ex) {
                            }
			}                      
                    }
		});
                combo.addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent arg0) {
                        String value = combo.getSelectedItem().toString();
                        int weight;
                        Float carb;
                        Float fat;
                        Float protein;
                        Float cal;
                        if( weightField.getText().equals("")){
                            weight = 0;
                        }
                        else{
                            weight = Integer.parseInt(weightField.getText());
                        }
			try {
                            Class.forName(JDBC_DRIVER);
                            Connection connect = DriverManager.getConnection(DB_URL, USER, PASS);
                            Statement st = connect.createStatement();
                            ResultSet rs = st.executeQuery("Select * from food where name = '"+ value + "'");
                            while (rs.next()) {                                
                                carb = rs.getFloat("carbpergram");
                                protein = rs.getFloat("proteinpergram");
                                fat = rs.getFloat("fatpergram");
                                cal = rs.getFloat("calpergram");
                                if(weight > 0 ){
                                    Float finalValue = weight * cal;
                                    Float finalValue2 = weight * carb;
                                    Float finalValue3 = weight * fat;
                                    Float finalValue4 = weight * protein;
                                    calorieField.setText(finalValue.toString());
                                    carbField.setText(finalValue2.toString());
                                    fatField.setText(finalValue3.toString());
                                    proteinField.setText(finalValue4.toString());
                                }
                                else{ calorieField.setText("Enter a weight");
                                    carbField.setText("Enter a weight");
                                    fatField.setText("Enter a weight");
                                    proteinField.setText("Enter a weight");}                               
                            }
                            connect.close();
			} 
                        catch (Exception ex) {
         		}
                    }                   
                });
                //1,1
                JLabel label = new JLabel("Enter Weight(g):");
                add(label);
                add(weightField);
                //1,3
                label = new JLabel("Total Carbs");
                add(label);
                add(carbField);
                //2,1
                label = new JLabel("Enter food name.");
                add(label);
                add(nameField);
                //2,3
                label = new JLabel("Total Protein");
                add(label);
                add(proteinField);
                //3,1
                label = new JLabel("Select food Name");
                add(label);
                add(combo);
                //3,3
                label = new JLabel("Total Fat");
                add(label);
                add(fatField);
                //4,1
                JButton addToMeal = new JButton("Add to meal");
                add(addToMeal);      
                //4,2
                label = new JLabel("");
                add(label);
                //4,3
                label = new JLabel("Total Calories");
                add(label);
                add(calorieField);
                 setVisible(true);
                 
                addToMeal.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        if( weightField.getText().equals("") || carbField.getText().equals("") 
                            || proteinField.getText().equals("") || fatField.getText().equals("") ||
                            calorieField.getText().equals("")) {
                                carbField.setText("Fill relevent boxes");
                                fatField.setText("Fill relevent boxes");
                                proteinField.setText("Fill relevent boxes");
                        }
                        else{
                            final int weight = Integer.parseInt(weightField.getText());
                            final String name =  combo.getSelectedItem().toString();
                            final float carbs = Float.parseFloat(carbField.getText());
                            final float protein = Float.parseFloat(proteinField.getText());
                            final float fat = Float.parseFloat(fatField.getText());
                            final float calories = Float.parseFloat(calorieField.getText());
                            //Create Book object to encapsulate info
                            Meal newMeal = new Meal(weight, name, carbs, protein, fat, calories);
                            //Pass object to VideoDAO to save in database
                            MealPlanner.addToMeal(newMeal);
                        }

                    }
                });

	}
}
