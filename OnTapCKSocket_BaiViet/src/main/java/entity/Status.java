package entity;

public enum Status {
    PENDDING("Pendding"), APPROVED("Approved"), REJECTED("Rejected");
    private String value;

    Status(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Status{" +
                "value='" + value + '\'' +
                '}';
    }
}
