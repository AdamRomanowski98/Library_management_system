package librarysystem;

import librarySystem.Author;
import librarySystem.Book;
import librarySystem.Library;
import librarySystem.User;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

import java.util.HashMap;


import static org.junit.jupiter.api.Assertions.*;


public class LibraryTestSuite {

    Library library = new Library();
    Book book = new Book(new Author("firstName1", "lastName1"), "title1", LocalDate.of(1,1,1), 1);
    User user = new User("firstName", "lastName", "login", "pass", 123);


    @BeforeEach
    public void beforeEach(){
        library.getBooks().add(book);
        library.getUsers().add(user);
    }


    @Test
    void testPrintBooksWithCopies(){
        HashMap<String, Integer> booksMap = library.printBooksWithCopies("title1");
        assertEquals(1, booksMap.size());
    }

    @Test
    void testFindBookObjectByIndex(){
        int index = library.findBook(book);
        assertEquals(0, index);
    }

    @Nested
    @DisplayName("Tests for finding by isbn")
    class FindByIsbn{
        @Test
        void testFindBookByIndexByIsbn(){
            int index = library.findBook(1);
            assertEquals(0, index);
        }

        @Test
        void testFindBookIfIsbnDoesntExist(){
            int index = library.findBook(2);
            assertEquals(-1, index);
        }
    }

    @Nested
    @DisplayName("Find book Object by title")
    class FindObjectByTitle{
        @Test
        void testFindBookObjectByTitle(){
            Book book1 = library.findBookObject("title1");
            assertNotNull(book1);
        }

        @Test
        void testFindBookObjectByTitleWhenTitleDoesntExist(){
            Book book1 = library.findBookObject("title5");
            assertNull(book1);
        }
    }

    @Nested
    @DisplayName("Find book object by isbn")
    class FindObjectByIsbn{
        @Test
        void testFindBookObjectByIsbn(){
            Book book1 = library.queryBook(1);
            assertNotNull(book1);
        }
        @Test
        void testFindBookObjectByIsbnWhenIsbnDoesntExist(){
            Book book1 = library.queryBook(2);
            assertNull(book1);
        }
    }

    @Nested
    @DisplayName("Test remove book")
    class RemoveBook{
        @Test
        void testRemoveBook(){
            boolean result = library.removeBook(1);
            assertTrue(result);
        }

        @Test
        void testRemoveBookWhenBookDoesntExist(){
            boolean result = library.removeBook(2);
            assertFalse(result);
        }
    }

        @Test
        void testUpdateBook(){
            Book book1 = new Book(new Author("firstName1", "lastName1"), "title1", LocalDate.of(1,1,1), 2);
            boolean result = library.updateBook(book, book1);
            assertTrue(result);
        }

    @Nested
    @DisplayName("Find user by login")
    class FindUserByLogin {
        @Test
        void testFindUserByLogin() {
            boolean result = library.findUser("login");
            assertTrue(result);
        }

        @Test
        void testFindUserByLoginWhenLoginDoesntExist(){
            boolean result = library.findUser("login1");
            assertFalse(result);
        }
    }

    @Nested
    @DisplayName("Find user Object")
    class FindUserObject{
        @Test
        void testFindUserObject(){
            User user1 = library.findUserObject("login");
            assertNotNull(user1);
        }
        @Test
        void testFindUserObjectWhenObjectDoesntExist(){
            User user1 = library.findUserObject("login1");
            assertNull(user1);
        }
    }






}
