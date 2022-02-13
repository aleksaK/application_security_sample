package lv.kalashnikov.security_app.core.domain;

public class Password {

    private String password;

    public Password() {}

    public Password(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}