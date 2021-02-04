/**
 * 
 */
package com.e3.containers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Syed Muneeb Hashmi
 *
 */
public class ShelfTest {

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {

  }

  /*
   * Test method for {@link com.e3.containers.Shelf#Shelf()}.
   */
  @Test
  public void testAddItem_addObjectType() {
    Shelf<Object> shelf = new Shelf<Object>(); // make a shelf that holds type Object
    Object item = new Object(); // make new object
    shelf.addItem(item); // add the object to shelf
    Object result = shelf.removeItem(0); // remove the item at the 0th index
    assertEquals(result, item); // test if its the same
  }

  @Test
  public void testAddItem_addNull() {
    Shelf<String> shelf = new Shelf<String>(); // make a shelf of type String
    shelf.addItem(null); // add an null type
    assertNotNull(shelf.removeItem(0)); // remove the first item, RETURNS NULL that means it's a
                                        // bug!
  }

  @Test
  public void testRemoveItem_basicTest() {
    Shelf<Integer> shelf = new Shelf<Integer>(); // make a shelf of Type Integer for testing
                                                 // purposes
    shelf.addItem(1); // add int 1
    shelf.addItem(2); // add int 2
    shelf.addItem(3); // add int 3
    int first_item = shelf.removeItem(0); // remove the first item (0th index) which should put the
                                          // second item to 0th index
    assertEquals(1, first_item); // test it
    int second_item = shelf.removeItem(0);
    assertEquals(2, second_item); // test if second item was moved to 0th index after first item was
                                  // removed
    int third_item = shelf.removeItem(0);
    assertEquals(3, third_item); // same for third
  }

  @Test
  public void testRemoveItem_checkIndexing() {
    Shelf<Integer> shelf = new Shelf<Integer>();
    shelf.addItem(1);
    shelf.addItem(2);
    int secondItem = shelf.removeItem(1);
    assertEquals(2, secondItem); // test if it removes from the proper index
  }

  @Test(expected = ArrayIndexOutOfBoundsException.class)
  public void testRemoveItem_ArrayOutOfBounds() {
    Shelf<Integer> shelf = new Shelf<Integer>();
    shelf.addItem(1);
    shelf.addItem(2);
    shelf.removeItem(-1); // should raise ArrayIndexOutOfBoudndsException
  }


}
