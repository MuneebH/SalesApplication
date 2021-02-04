package com.e3.products;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import java.util.List;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Muneeb
 *
 */
public class IllustratedChildrensBookTest {

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {}

  /**
   * Test method for
   * {@link com.e3.products.IllustratedChildrensBook#IllustratedChildrensBook(java.lang.String, java.lang.String, int, java.util.List, com.e3.products.IllustrationTypes)}.
   */
  @Test
  public void testIllustratedChildrensBook_emptyList() {
    List<String> illustrators = new ArrayList<String>();
    @SuppressWarnings("unused")
    IllustratedChildrensBook book = new IllustratedChildrensBook("Book Title", "Author", 5,
        illustrators, IllustrationTypes.DIGITAL); // BUG
    fail("Shouldn't work");
  }

  @Test
  public void testIllustratedChildrensBook_negativePage() {
    List<String> illustrators = new ArrayList<String>();
    illustrators.add("Illustrator");
    IllustratedChildrensBook book = new IllustratedChildrensBook("Book Title", "Author", -5,
        illustrators, IllustrationTypes.DIGITAL); // initiailized a negative number of pages,
                                                  // shouldn't be allowed
    fail("Shouldnlt work");
  }

  @Test
  public void testIllustratedChildrensBook() {
    List<String> illustrators = new ArrayList<String>();
    illustrators.add("Illustrator");
    IllustratedChildrensBook book = new IllustratedChildrensBook("Book Title", "Author", 5,
        illustrators, IllustrationTypes.DIGITAL); // perfect constructor
  }

  @Test
  public void testGetAuthor_basicTest() {
    List<String> illustrators = new ArrayList<String>();
    illustrators.add("Illustrator");
    IllustratedChildrensBook book = new IllustratedChildrensBook("Book Title", "Author", 5,
        illustrators, IllustrationTypes.DIGITAL);
    assertEquals("Author", book.getAuthor());
  }

  /**
   * Test method for {@link com.e3.products.IllustratedChildrensBook#setEdition(int)}.
   */
  @Test
  public void testSetEdition_basicTest() {
    List<String> illustrators = new ArrayList<String>();
    illustrators.add("Illustrator");
    IllustratedChildrensBook book = new IllustratedChildrensBook("Book Title", "Author", 5,
        illustrators, IllustrationTypes.PAINT);
    book.setEdition(4);
    assertEquals(4, book.getEdition());
  }

  @Test
  public void testSetEdition_setNegativeNumber() {
    List<String> illustrators = new ArrayList<String>();
    illustrators.add("Illustrator");
    IllustratedChildrensBook book = new IllustratedChildrensBook("Book Title", "Author", 5,
        illustrators, IllustrationTypes.PAINT);
    book.setEdition(-1);
    assertEquals(-1, book.getEdition());
  }

  public void testSetEdition_setZero() {
    List<String> illustrators = new ArrayList<String>();
    illustrators.add("Illustrator");
    IllustratedChildrensBook book = new IllustratedChildrensBook("Book Title", "Author", 5,
        illustrators, IllustrationTypes.DIGITAL);
    book.setEdition(0);
    assertEquals(0, book.getEdition());

  }

  /**
   * Test method for {@link com.e3.products.IllustratedChildrensBook#getEdition()}.
   */
  @Test
  public void testGetEdition_basicTest() {
    testSetEdition_basicTest(); // same way of testing
  }

  /**
   * Test method for {@link com.e3.products.IllustratedChildrensBook#length()}.
   */
  @Test
  public void testLength_basicTest() {
    List<String> illustrators = new ArrayList<String>();
    illustrators.add("Illustrator");
    IllustratedChildrensBook book = new IllustratedChildrensBook("Book Title", "Author", 5,
        illustrators, IllustrationTypes.DIGITAL);
    assertEquals(5, book.length());
  }

  /**
   * Test method for {@link com.e3.products.IllustratedChildrensBook#getIllustrators()}.
   */
  @Test
  public void testGetIllustrators_basicTest() {
    List<String> illustrators = new ArrayList<String>();
    illustrators.add("Alice");
    IllustratedChildrensBook book = new IllustratedChildrensBook("Book Title", "Author", 5,
        illustrators, IllustrationTypes.DIGITAL);
    assertEquals(illustrators, book.getIllustrators());
  }

  @Test
  public void testGetIllustrators_noIllustrator() {
    List<String> illustrators = new ArrayList<String>();
    IllustratedChildrensBook book = new IllustratedChildrensBook("Book Title", "Author", 5,
        illustrators, IllustrationTypes.DIGITAL);
    assertEquals(illustrators, book.getIllustrators());
  }

  /**
   * Test method for {@link com.e3.products.IllustratedChildrensBook#getNumberOfIllustration()}.
   */
  @Test
  public void testGetNumberOfIllustration() {
    testGetAndSetNumberofIllustrations_basicTest(); // same way of testing
  }

  /**
   * Test method for {@link com.e3.products.IllustratedChildrensBook#getIllustrationType()}.
   */
  @Test
  public void testGetIllustrationType() {
    List<String> illustrators = new ArrayList<String>();
    illustrators.add("Alice");
    IllustratedChildrensBook book = new IllustratedChildrensBook("Book Title", "Author", 5,
        illustrators, IllustrationTypes.DIGITAL);
    assertEquals(IllustrationTypes.DIGITAL, book.getIllustrationType());
  }

  /**
   * Test method for {@link com.e3.products.IllustratedChildrensBook#setNumberofIllustrations(int)}.
   */
  @Test
  public void testGetAndSetNumberofIllustrations_basicTest() {
    List<String> illustrators = new ArrayList<String>();
    illustrators.add("Alice");
    IllustratedChildrensBook book = new IllustratedChildrensBook("Book Title", "Author", 5,
        illustrators, IllustrationTypes.DIGITAL);
    book.setNumberofIllustrations(4);
    assertEquals(4, book.getNumberOfIllustration());
  }

}
