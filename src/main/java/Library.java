import java.util.*;

enum Library {

    INSTANCE;
    private ArrayList<Book> books = new ArrayList<>();

    Library(){
        this.books = new ArrayList<>();
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    @Override
    public String toString() {
        return "Library{" +
                "books=" + books +
                '}';
    }


    public void printBooksWithNumberOfCopies(){
        HashMap<String, Integer> books1 = new HashMap<>();

        for(Book book : books){
            if(book.isBorrowed() == false){
                String value = book.getTitle();
                Integer count = books1.get(value);
                if(count == null){
                    books1.put(value, 1);
                }else
                    books1.put(value, count+1);
            }
        }

        for(Map.Entry<String, Integer> entry : books1.entrySet()){
            System.out.println("Title: " + entry.getKey() + " number of copies available: " +entry.getValue());
        }
    }
    public void printBooks(){
        for(int i = 0; i<books.size(); i++){
            System.out.println(this.books.get(i).getAuthor() + " " +this.books.get(i).getTitle() + " " + this.books.get(i).getDateOfPublication() + " " + this.books.get(i).getIsbnNumber() + " " + this.books.get(i).isBorrowed());
        }
    }

    public int findBook(Book book){
        return this.books.indexOf(book);
    }

    public void findBook(String title){
        for(int i = 0; i<this.books.size(); i++){
            if(this.books.get(i).getTitle().equals(title)){
                System.out.println(this.books.get(i).getAuthor() + " " +this.books.get(i).getTitle() + " " + this.books.get(i).getDateOfPublication() + " " + this.books.get(i).getIsbnNumber());
            }
        }
    }

    private int findBook(int isbn){
        for(int i = 0; i < this.books.size(); i++){
            Book book = this.books.get(i);
            if(book.getIsbnNumber() == isbn){
                return this.books.indexOf(book);
            }
        }
        return -1;
    }

    public Book findBookObject(String title){
        for (int i =0; i<this.books.size(); i++){
            Book book = this.books.get(i);
            if(book.getTitle().equals(title)){
                int position = this.books.indexOf(book);
                return this.books.get(position);
            }
        }
        return null;
    }

    public Book queryBook(int isbn){
        int position = findBook(isbn);
        if(position >= 0){
            return this.books.get(position);
        }
        return null;
    }

    public boolean removeBook(int isbn){
        int position = findBook(isbn);
        System.out.println(position);
        if(position < 0){
            System.out.println("ISBN number doesn't match any of the books");
            return false;
        }
        this.books.remove(position);
        System.out.println("Book has been successfully removed");
        return true;
    }

    public boolean updateBook(Book oldBook, Book newBook){
        int position = findBook(oldBook);
        if(position < 0){
            System.out.println("Book not found");
            return false;
        }
        this.books.set(position, newBook);
        return true;
    }






}
