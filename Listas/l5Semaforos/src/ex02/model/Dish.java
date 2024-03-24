package ex02.model;

public enum Dish {
    SOPA_DE_CEBOLA("Sopa de Cebola", 500, 800),
    LASANHA_BOLONHESA("Lasanha Bolonhesa", 600, 1200);

    private final String description;
    private final int minPreparationTime;
    private final int maxPreparationTime;

    Dish(String description, int minPreparationTime, int maxPreparationTime) {
        this.description = description;
        this.minPreparationTime = minPreparationTime;
        this.maxPreparationTime = maxPreparationTime;
    }

    public String getDescription() { return description; }
    public int getMinTime() { return minPreparationTime; }
    public int getMaxTime() { return maxPreparationTime; }
}
