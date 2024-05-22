package entity;

import java.io.Serializable;

public enum Type implements Serializable {
    APPETIZER("appetizer"), MAIN_COURSE("main_course"), DESSERT("dessert");
    private String value;

    Type(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Type{" +
                "value='" + value + '\'' +
                '}';
    }
}
