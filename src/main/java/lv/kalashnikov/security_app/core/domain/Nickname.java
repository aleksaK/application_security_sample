package lv.kalashnikov.security_app.core.domain;

public class Nickname {

    private String nickname;

    public Nickname() {
    }

    public Nickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

}