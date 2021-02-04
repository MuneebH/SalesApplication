package com.e3.products;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;

public class ChildrensBookAndBookTest {

  @Before
  public void setUp() throws Exception {}

  @Test
  public void testChildrensBookConstructor_negativePages() {
    @SuppressWarnings("unused")
    ChildrensBook book = new ChildrensBook("Muneeb Hashmi", "Pokemon!", -5); // bug; shouldn't work
  }

  @Test
  public void testChildrensBookConstructor_happyCondition() {
    @SuppressWarnings("unused")
    ChildrensBook book = new ChildrensBook("Muneeb Hashmi", "Pokemon!", 5); // should work, will use
                                                                            // for test cases
  }

  @Test
  public void testChildrensBookConstructor_zeroPages() {
    @SuppressWarnings("unused")
    ChildrensBook book = new ChildrensBook("Muneeb Hashmi", "Pokemon!", 0); // bad design
  }

  @Test
  public void testGetAuthor() {
    ChildrensBook book = new ChildrensBook("Muneeb Hashmi", "Pokemon!", 5);
    assertEquals("Muneeb Hashmi", book.getAuthor()); // test if the author is "Muneeb Hashmi"
  }

  @Test
  public void testGetAuthor_EmptyString() {
    ChildrensBook book = new ChildrensBook("", "Pokemon!", 5);
    assertEquals("", book.getAuthor());
  }

  @Test
  public void testGetAuthor_Null() {
    ChildrensBook book = new ChildrensBook(null, "Pokemon!", 4);
    assertNull(null, book.getAuthor()); // BUG; shouldn't be true
  }

  @Test
  public void testGetMinAge() {
    ChildrensBook book = new ChildrensBook("Albert Einstein", "How2Smart", 4);
    book.setMinAge(5);
    assertEquals(5, book.getMinAge());
  }

  @Test
  public void testGetMinAge_NoAge() {
    ChildrensBook book = new ChildrensBook("Swagger", "swag", 3);
    assertEquals(0, book.getMinAge()); // this is correct design, 0 should be the minimum age if no
                                       // age is given
  }

  @Test
  public void testSetMinAge_PositiveNumber() {
    testGetMinAge();
  }

  @Test
  public void testSetMinAge_NegativeNumber() {
    ChildrensBook book = new ChildrensBook("lol", "lolly", 3);
    book.setMinAge(-1); // set the minimum age to a negative number
    assertEquals(-1, book.getMinAge()); // BUG; shouldn't be allowed
  }

  // ================= test for Books.java UNDERNEATH =============================
  @Test
  public void testBook() {
    // can't be instanstiated
  }

  @Test
  public void testGetBookmarkedPage() {
    ChildrensBook book = new ChildrensBook("Author", "Book", 5);
    book.setBookmarkedPage(3); // set the bookmarked page to a 5 page book to #3 page
    assertEquals(3, book.getBookmarkedPage()); // test if its page 3
  }

  @Test
  public void testGetBookmarkedPage_NoBookmark() {
    ChildrensBook book = new ChildrensBook("Author", "Book", 2);
    assertEquals(1, book.getBookmarkedPage()); // test default bookmark page
  }

  @Test
  public void testGetBookmarkedPage_NoPages() {
    ChildrensBook book = new ChildrensBook("Author", "Book", 0);
    assertEquals(1, book.getBookmarkedPage()); // BUG how can it be page 1, when there are no pages
  }

  @Test
  public void testSetBookmarkedPage() {
    testGetBookmarkedPage(); // since it's the same way of testing
  }

  @Test
  public void testSetBookmarkedPage_negativeNumber() {
    ChildrensBook book = new ChildrensBook("Author", "Book", 5);
    book.setBookmarkedPage(-1); // set the bookmark page to -1
    assertEquals(-1, book.getBookmarkedPage()); // BUG
  }

  @Test
  public void testGetTitle() {
    ChildrensBook book = new ChildrensBook("Author", "Book Title", 5);
    assertEquals("Book Title", book.getTitle());
  }

  @Test
  public void testGetTitle_noTitleGiven() {
    ChildrensBook book = new ChildrensBook("", "", 2);
    assertEquals("", book.getTitle()); // this is ok
  }

  @Test
  public void testGetPageCount_basicTest() {
    ChildrensBook book = new ChildrensBook("Author", "Title", 2);
    assertEquals(2, book.getPageCount());
  }

  @Test
  public void testGetPageCount_negativeNumberOfPages() {
    ChildrensBook book = new ChildrensBook("Author", "Title", -1);
    assertEquals(-1, book.getPageCount()); // BUG cuz it works which it shouldn't
  }

  @Test
  public void testGetPageCount_noPages() {
    ChildrensBook book = new ChildrensBook("Author", "Title", 0);
    assertEquals(0, book.getPageCount());
  }

}
