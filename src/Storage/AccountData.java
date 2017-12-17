package Storage;

import java.io.Serializable;

class AccountData implements Serializable {
    private String username, password;

    AccountData(String username, String password, AccountSystem system) {
        this.username = username;
        this.password = password;
        system.add(this);
    }

    String getUsername() {
        return username;
    }

    boolean verifyAccount(String password) {
        return this.password.equals(password);
    }
}
