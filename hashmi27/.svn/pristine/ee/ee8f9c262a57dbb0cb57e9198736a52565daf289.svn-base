/**
 * 
 */
package com.e3.products;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Muneeb Hashmi
 *
 */
public class ComicBookTest {

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {}

  /**
   * Test method for
   * {@link com.e3.products.ComicBook#ComicBook(java.lang.String, int, int, java.util.List)}.
   */
  @Test
  public void testComicBookConstructor_happyCondition() {
    List<String> illustrators = new ArrayList<String>();
    illustrators.add("Alice");
    illustrators.add("Bob");
    illustrators.add("Charlie");
    @SuppressWarnings("unused")
    ComicBook comic = new ComicBook("Naruto", 20, 21, illustrators);
  }

  @Test
  public void testComicBookConstructor_emptyList() {
    List<String> illustrators = new ArrayList<String>();
    @SuppressWarnings("unused")
    ComicBook comic = new ComicBook("Naruto", -20, -21, illustrators); // shouldn't be allowed; BUG
  }

  /**
   * Test method for {@link com.e3.products.ComicBook#getIllustrators()}.
   */
  @Test
  public void testGetIllustrators_basicTest() {
    List<String> illustrators = new ArrayList<String>();
    illustrators.add("Alice");
    illustrators.add("Bob");
    illustrators.add("Charlie");
    ComicBook comic = new ComicBook("Naruto", 20, 21, illustrators);
    assertEquals(illustrators, comic.getIllustrators());
  }

  /**
   * Test method for {@link com.e3.products.ComicBook#getNumberOfIllustration()}.
   */
  @Test
  public void testGetNumberOfIllustration_basicTest() {
    List<String> illustrators = new ArrayList<String>();
    illustrators.add("Alice");
    illustrators.add("Bob");
    illustrators.add("Charlie");
    ComicBook comic = new ComicBook("Naruto", 20, 21, illustrators);
    assertEquals(21, comic.getNumberOfIllustration());
  }

  /**
   * Test method for {@link com.e3.products.ComicBook#getIllustrationType()}.
   */
  @Test
  public void testGetIllustrationType_basicTest() {
    List<String> illustrators = new ArrayList<String>();
    illustrators.add("Alice");
    illustrators.add("Bob");
    illustrators.add("Charlie");
    ComicBook comic = new ComicBook("Naruto", 20, 21, illustrators);
    assertEquals(IllustrationTypes.INK, comic.getIllustrationType());
  }

}
