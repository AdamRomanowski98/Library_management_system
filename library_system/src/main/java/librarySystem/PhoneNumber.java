package librarySystem;

import java.util.HashSet;
import java.util.Set;

public class PhoneNumber {

    private Set<Integer> phoneNumbers;

    public PhoneNumber(){
        this.phoneNumbers = new HashSet<>();
    }

    public Set<Integer> getPhoneNumbers() {
        return phoneNumbers;
    }
}
