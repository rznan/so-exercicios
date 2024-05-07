package ex01.model;

public class Food {
    String name;
    String scientific;
    String group;
    String subGroup;

    public Food(String name, String scientific, String group, String subGroup) {
        this.name = name;
        this.scientific = scientific;
        this.group = group;
        this.subGroup = subGroup;
    }

    @Override
    public String toString() {
        return String.format("%s\t%s\t%s", name, scientific, subGroup);
    }
}
