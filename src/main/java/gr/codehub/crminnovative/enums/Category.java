package gr.codehub.crminnovative.enums;

public enum Category {
    DEFAULT("Undefined"),
    FRESH("Fresh"),
    DAIRY("Dairy"),
    SNACK("Snack");

    private String fullName;

    Category(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

}
