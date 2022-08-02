package librarySystem;

import java.util.HashSet;
import java.util.Set;

public class Login {

    private Set<String> logins;

    public Login(){
        this.logins = new HashSet<>();
    }

    public Set<String> getLogins() {
        return logins;
    }
}
