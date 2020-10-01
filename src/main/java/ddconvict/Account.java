package ddconvict;

public class Account {
    private String prof, login, password;

    public Account(String prof, String login, String password) {
        this.prof = prof;
        this.login = login;
        this.password = password;
    }

    public String getProf() {
        return prof;
    }

    public void setProf(String prof) {
        this.prof = prof;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return this.prof + "\n" + this.login + "\n" + this.password + "\n";
    }
}

