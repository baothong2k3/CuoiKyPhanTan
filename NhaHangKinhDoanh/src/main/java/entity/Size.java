package entity;

import java.io.Serializable;

public enum Size implements Serializable {
    LARGE("large"), MEDIUM("medium"), SMALL("small");
    private String value;

    Size(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Size{" +
                "value='" + value + '\'' +
                '}';
    }
}
