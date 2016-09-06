package mealplanner;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

public class GuiFrame extends JFrame {
    
	public GuiFrame() {
       
        JTabbedPane tabbedPane = new JTabbedPane();
         
        JComponent readPanel = new ReadFoodPanel();
        tabbedPane.addTab("Find food", readPanel);
        
        JComponent addFoodPanel = new AddFoodPanel();
        tabbedPane.addTab("Add Food", addFoodPanel);
        
        JComponent readMeal = new ReadMeal();
        tabbedPane.add(new JScrollPane(readMeal), "View Meal");
        
        //tabbedaPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        add(tabbedPane); 
    }
    public static void main(String[] args) {
    	
    	 //Create and set up the window.
        JFrame frame = new GuiFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
        //Display the window.
        frame.setSize(565, 180);
        frame.setVisible(true);
    }
}