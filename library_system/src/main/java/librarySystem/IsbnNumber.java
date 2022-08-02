package librarySystem;

import java.util.HashSet;
import java.util.Set;

public class IsbnNumber {

    private Set<Integer> isbn;

    public IsbnNumber(){
        this.isbn = new HashSet<>();
    }

    public Set<Integer> getIsbn() {
        return isbn;
    }
}
