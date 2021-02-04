package com.e3.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import com.e3.containers.BookShelfTest;
import com.e3.containers.ShelfTest;
import com.e3.products.ChildrensBookAndBookTest;
import com.e3.products.ComicBookTest;
import com.e3.products.IllustratedChildrensBookTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ShelfTest.class, BookShelfTest.class, ChildrensBookAndBookTest.class,
    IllustratedChildrensBookTest.class, ComicBookTest.class})
public class TestSuite {
  // You can leave this blank.
  // When you run this class you will run all tests in the classes listed above
}
