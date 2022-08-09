package librarysystem;

import librarySystem.Author;
import librarySystem.Book;
import librarySystem.Library;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LibraryTestSuite {


   public static Library library = new Library();


   @BeforeEach
   public static void generateBooks(){
       for(int i = 0; i<10; i++){
           Book book = new Book(new Author("firstName"+i, "lastName"+i), "title" +i, LocalDate.of(1,1,1), i);
           library.getBooks().add(book);
       }
   }



    @Test
    void testAddBooks(){
       assertEquals(10, library.getBooks().size());
    }

    @Test
    void testPrintBooksWithCopies(){
       assertEquals(1,  library.printBooksWithCopies("title1").size());
    }



}
