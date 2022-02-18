package lv.kalashnikov.security_app.core.domain;

public class Filter {

    private String field;
    private String operator;
    private int target;

    public Filter() {}

    public Filter(String field, String operator, int target) {
        this.field = field;
        this.operator = operator;
        this.target = target;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

}