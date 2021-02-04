package com.e3.containers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import com.e3.exceptions.ShelfTooSmallException;
import com.e3.products.Book;
import com.e3.products.ChildrensBook;

public class BookShelfTest {

  @Before
  public void setUp() throws Exception {}

  @Test
  public void testBookShelfConstructor_negativeNumber() {
    @SuppressWarnings("unused")
    BookShelf<Book> bookShelf = new BookShelf<Book>(-1, -2); // shuoldn't be working; bug
  }

  @Test
  public void testBookShelfConstructor_happyCondition() {
    @SuppressWarnings("unused")
    BookShelf<Book> bookShelf = new BookShelf<Book>(1, 2); // should work
  }

  @Test
  public void testBookShelfConstructor_zeroShelf() {
    @SuppressWarnings("unused")
    BookShelf<Book> bookShelf = new BookShelf<Book>(0, 4); // bad design, shouldn't allow this hence
                                                           // a bug
  }

  @Test
  public void testGetNumberOfBooks_ZeroBooks() {
    BookShelf<Book> bookShelf = new BookShelf<Book>(1, 2); // initialize a bookshelf
    assertEquals(0, bookShelf.getNumberOfBooks()); // should get 0 number of books since i added
                                                   // nothing
  }

  @Test
  public void testGetNumberOfBooks_OneBook() {
    BookShelf<Book> bookShelf = new BookShelf<Book>(1, 1); // bookshelf with 1 shelf, 1 book
                                                           // capacity
    ChildrensBook item = new ChildrensBook("Muneeb", "Book Title", 5); // a book
    bookShelf.addItem(item); // add a book
    assertEquals(1, bookShelf.getNumberOfBooks()); // test if it works
  }

  @Test
  public void testGetNumberOfBooks_ManyBooks() {
    BookShelf<Book> bookShelf = new BookShelf<Book>(1, 3); // make a shelf with 3 book capacity
    ChildrensBook book = new ChildrensBook("Muneeb", "Book Title", 5);
    ChildrensBook bookTwo = new ChildrensBook("Muneeb", "Book #2", 5);
    ChildrensBook bookThree = new ChildrensBook("Muneeb", "Book #3", 5);
    bookShelf.addItem(book);
    bookShelf.addItem(bookTwo);
    bookShelf.addItem(bookThree); // add all of them and test
    assertEquals(3, bookShelf.getNumberOfBooks());
  }

  @Test
  public void testAddItemT_addNull() {
    BookShelf<Book> bookShelf = new BookShelf<Book>(1, 3);
    bookShelf.addItem(null);
    assertNotNull(bookShelf.removeItem(0)); // bug; this returns null, when it should do nothing
  }

  @Test(expected = ShelfTooSmallException.class)
  public void testAddItemT_ShelfTooSmallNoShelfAtAll() {
    BookShelf<Book> bookShelf = new BookShelf<Book>(0, 5); // make no shelf but 5 book capacity
                                                           // (doesn't make sense)
    ChildrensBook book = new ChildrensBook("Author", "Book Title", 5);
    bookShelf.addItem(book); // should raise an error of ShelfTooSmall
  }

  @Test(expected = ShelfTooSmallException.class)
  public void testAddItemT_ShelfTooSmallNoBookSpace() {
    BookShelf<Book> bookShelf = new BookShelf<Book>(1, 1); // make a shelf and one book capacity
    ChildrensBook book = new ChildrensBook("Author", "Book Title", 5);
    ChildrensBook bookTwo = new ChildrensBook("Author", "Book", 4);
    bookShelf.addItem(book);
    bookShelf.addItem(bookTwo); // add two books, should raise ShelfTooSmallException, since there
                                // was only one book capacity
  }

  @Test
  public void testAddItemT_addOneItem() {
    testGetNumberOfBooks_OneBook(); // since it's the same thing, same goes for many books
  }

  @Test
  public void testRemoveItem_basicTest() {
    BookShelf<Book> bookShelf = new BookShelf<Book>(1, 1);
    ChildrensBook book = new ChildrensBook("Author", "Book Title", 3);
    bookShelf.addItem(book);
    assertEquals(book, bookShelf.removeItem(0));
  }

  @Test
  public void testRemoveItem_checkIndexing() {
    BookShelf<Book> bookShelf = new BookShelf<Book>(1, 2);
    ChildrensBook book = new ChildrensBook("Author", "Book Title", 3);
    ChildrensBook bookTwo = new ChildrensBook("Author #2", "Book Title #2", 3);
    bookShelf.addItem(book);
    bookShelf.addItem(bookTwo);
    assertEquals(book, bookShelf.removeItem(0)); // should remove book, and replace with bookTWo in
                                                 // 0th index
    assertEquals(bookTwo, bookShelf.removeItem(0)); // test
  }

  @Test
  public void testRemoveItem_removeSecondFirstAndCheckIndexing() {
    BookShelf<Book> bookShelf = new BookShelf<Book>(1, 2);
    ChildrensBook book = new ChildrensBook("Author", "Book Title", 3);
    ChildrensBook bookTwo = new ChildrensBook("Author #2", "Book Title #2", 3);
    bookShelf.addItem(book);
    bookShelf.addItem(bookTwo);
    assertEquals(bookTwo, bookShelf.removeItem(1)); // check if bookTwo is returned with the first
                                                    // index
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void testRemoveItem_noItem() {
    BookShelf<Book> bookShelf = new BookShelf<Book>(1, 2);
    bookShelf.removeItem(0); // should raise a error indexoutofboudns
  }
}
