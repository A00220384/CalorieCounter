package mealplanner;

public class Meal {	
	private int weight;
	private String name;
        private float totalCarbs;
        private float totalProtein;
	private float totalFat;
        private float totalCalories;
        
	public Meal(int weight, String name, float totalCarbs, float totalProtein, float totalFat, float totalCalories)
	{
		this.weight = weight;
		this.name = name;
		this.totalCarbs = totalCarbs;
		this.totalProtein = totalProtein;
                this.totalFat = totalFat;
                this.totalCalories = totalCalories;
	}
	
	public int getWeight() {
		return weight;
	}
        public float getTotalCarbs() {
		return totalCarbs;
	}
        public String getName() {
		return name;
	}
        public float getTotalProtein() {
		return totalProtein;
	}
        public float getTotalFat() {
		return totalFat;
	}
        public float getTotalCalories() {
		return totalCalories;
	}        
}
