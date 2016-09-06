package mealplanner;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

public class AddFoodPanel extends JPanel {
	private static final int FIELD_WIDTH = 20;
	public AddFoodPanel()
	{
                NumberFormat format = NumberFormat.getInstance();
                NumberFormatter formatter = new NumberFormatter(format);
                formatter.setValueClass(Float.class);
                format.setMinimumFractionDigits(2);
                format.setMaximumFractionDigits(2);
                
                formatter.setAllowsInvalid(false);             
                formatter.setCommitsOnValidEdit(true);
                      
		setLayout(new GridLayout(0, 2));		
		//Create Labels and textFields
		add(new JLabel("Name: "));
		final JTextField nameInput = new JTextField();
		add(nameInput);

		add(new JLabel("Carbs per gram: "));
		final JFormattedTextField carbInput = new JFormattedTextField(formatter);
		add(carbInput);

		add(new JLabel("Protein per gram: "));
		final JFormattedTextField proteinInput = new JFormattedTextField(formatter);
		add(proteinInput);
                        
                add(new JLabel("Fat per gram: "));
		final JFormattedTextField fatInput = new JFormattedTextField(formatter);
		add(fatInput);
                
                add(new JLabel("Calories per gram: "));
		final JFormattedTextField calorieInput = new JFormattedTextField(formatter);
		add(calorieInput);
                
		class AddFoodListener implements ActionListener
		{
                        @Override
			public void actionPerformed(ActionEvent e)
			{
                        if( carbInput.getText().equals("") 
                        || proteinInput.getText().equals("") || fatInput.getText().equals("") ||
                        calorieInput.getText().equals("")) {
                        carbInput.setText("Fill relevent boxes");
                        fatInput.setText("Fill relevent boxes");
                        proteinInput.setText("Fill relevent boxes");            
                        }
                        else{
				//Get data from textFields
				String name = nameInput.getText();
				float carbs = Float.parseFloat(carbInput.getText());
                                float protein = Float.parseFloat(proteinInput.getText());
                                float fat = Float.parseFloat(fatInput.getText());
                                float calories = Float.parseFloat(calorieInput.getText());
				//Create Book object to encapsulate info
				Food food = new Food(name, carbs, protein, fat, calories);
				//Pass object to VideoDAO to save in database
				MealPlanner.addToFood(food);
                        }
			}
		}
		//Create and add 'Add Book' Button
		JButton addFood = new JButton("Add Food");
		addFood.addActionListener(new AddFoodListener());
		add(new JLabel(""));//dummy label
		add(addFood);
	}	
}
