package Storage;

import data.Notes;

import java.io.Serializable;

public class UserData implements Serializable {
    public Notes notes;
    private String firstName, lastName, email;
    private AccountData accountData;

    public UserData(String username, String password, String firstName, String lastName, String email, AccountSystem system) {
        accountData = new AccountData(username, password, system);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        notes = new Notes();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return accountData.getUsername();
    }
}
