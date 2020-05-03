package MainBody;

import java.io.Serializable;

public class User implements Serializable {
    private String order; //например пароль, секретный вопрос
    private Long id;
    private String name;
    private String pass;
    private String sec_q;
    private String answer;
    private Integer cash;
    private String choose; //команды как login, forgotSearch
    private boolean choice; //сервер возвращает правильно или нет
    private Integer integer;

    public User() {
    }

    public User(Long id, String name, String pass, String sec_q, String answer) {
        this.id = id;
        this.name = name;
        this.pass = pass;
        this.sec_q = sec_q;
        this.answer = answer;
    }

    public User(Long id, String name, String pass, String sec_q, String answer, Integer cash) {
        this.id = id;
        this.name = name;
        this.pass = pass;
        this.sec_q = sec_q;
        this.answer = answer;
        this.cash = cash;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getChoose() {
        return choose;
    }

    public void setChoose(String choose) {
        this.choose = choose;
    }

    public boolean getChoice() {
        return choice;
    }

    public void setChoice(boolean choice) {
        this.choice = choice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getSec_q() {
        return sec_q;
    }

    public void setSec_q(String sec_q) {
        this.sec_q = sec_q;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getCash() {
        return cash;
    }

    public void setCash(Integer cash) {
        this.cash = cash;
    }

    public boolean isChoice() {
        return choice;
    }

    public Integer getInteger() {
        return integer;
    }

    public void setInteger(Integer integer) {
        this.integer = integer;
    }

    @Override
    public String toString() {
        return "User{" + " id='" + id + '\'' +
                "order='" + order + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", pass='" + pass + '\'' +
                ", sec_q='" + sec_q + '\'' +
                ", answer='" + answer + '\'' +
                ", cash=" + cash +
                ", choose='" + choose + '\'' +
                ", choice=" + choice +
                '}';
    }
}
