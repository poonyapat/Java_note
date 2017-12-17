package Storage;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class AccountSystem implements Serializable {
    private Map<String, AccountData> accountMap;

    public AccountSystem() {
        accountMap = new HashMap<>();
    }

    public void add(AccountData accountData) {
        accountMap.put(accountData.getUsername(), accountData);
    }

    public boolean verifyAccount(String username, String password) {
        if (accountMap.get(username) == null) {
            return false;
        }
        return accountMap.get(username).verifyAccount(password);
    }

    public boolean contains(String username) {
        return accountMap.containsKey(username);
    }

}
