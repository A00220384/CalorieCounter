package mealplanner;

public class Food {	
	private String name;
        private float carbsPerGram;
        private float proteinPerGram;
	private float fatPerGram;
        private float caloriesPerGram;
        
	public Food(String name, float carbsPerGram, float proteinPerGram, float fatPerGram, float caloriesPerGram)
	{		
		this.name = name;
		this.carbsPerGram = carbsPerGram;
		this.proteinPerGram = proteinPerGram;
                this.fatPerGram = fatPerGram;
                this.caloriesPerGram = caloriesPerGram;
	}
	
        public float getCarbsPerGram() {
		return carbsPerGram;
	}
        public String getName() {
		return name;
	}
        public float getProteinPerGram() {
		return proteinPerGram;
	}
        public float getFatPerGram() {
		return fatPerGram;
	}
        public float getCaloriesPerGram() {
		return caloriesPerGram;
	}       
}
