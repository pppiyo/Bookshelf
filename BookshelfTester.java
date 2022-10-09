// Name: Yueqin Li
// USC NetID: yueqinli
// CSCI455 PA2
// Fall 2022

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class BookshelfTester
 *
 * A test class specifically designed to test the following methods of the
 * Bookshelf Class:
 *
 * constructor(): includes one test for an empty shelf,
 * one for a shelf created with an argument,
 * and the corresponding print result of each case.
 * addLast(): inserts book with specified height at the end of the Bookshelf.
 * addFront(): inserts book with specified height at the start of the Bookshelf.
 * removeLast(): removes book at the end of the Bookshelf and
 * returns the height of the removed book.
 * removeFront(): removes book at the front of the Bookshelf and returns
 * the height of the removed book.
 * isSorted(): returns true iff the books on this Bookshelf are in
 * non-decreasing order.
 * getHeight(): gets the height of the book at the given position.
 * size(): gets the number of books on a shelf.
 *
 * There is one test function for each method listed above, named by
 * test+[method name to be tested].
 *
 * In general each test case should print out both the expected and the
 * actual result, followed by a conclusion in the format of
 * "Test passed? [true/false]" in which case true or false depends on whether
 * the expected and the actual value are the same.
 */
public class BookshelfTester {
   /**
    * Test a series of methods defined in the Bookshelf class.
    * Each line corresponds to a test for a specific method,
    * see function comments for details.
    *
    * Each test case should print out both the expected and the actual result,
    * followed by a conclusion in the format of "Test passed? [true/false]"
    * in which case true or false depends on whether the
    * expected and the actual value are the same.
    *
    * To run test a specific method, uncomment the line for its corresponding
    * test method and comment all the other lines.
    */
   public static void main(String[] args) {
      // test the Bookshelf class's constructor
      testConstructor();
      // test the Bookshelf class's addLast() method
      testAddLast();
      // test the Bookshelf class's addFront() method
      testAddFront();
      // test the Bookshelf class's removeLast() method
      testRemoveLast();
      // test the Bookshelf class's removeFront() method
      testRemoveFront();
      // test the Bookshelf class's isSorted() method
      testIsSorted();
      // test the Bookshelf class's getHeight() method
      testGetHeight();
      // test the Bookshelf class's size() method
      testSize();
   }

   /**
    * Test the constructor of the Bookshelf class.
    *
    * The test includes one test for an empty shelf, one for a shelf created
    * with an argument, and the corresponding print result of each case.
    * Each test case should print out both the expected and the actual result,
    * followed by a conclusion in the format of "Test passed? [true/false]"
    * in which case true or false depends on whether the expected and the
    * actual value are the same.
    */
   public static void testConstructor() {
      System.out.println("Testing constructor and toString method...");
      String expected;
      String actual;

      // Test empty constructor of Bookshelf class. toString() method.
      // Should return [].
      Bookshelf emptyShelf = new Bookshelf();
      expected = "[]";
      actual = emptyShelf.toString();
      printResult(expected, actual);

      // Test constructor of Bookshelf [7, 6, 5, 4, 3]. toString() method.
      // Should return [7, 6, 5, 4, 3].
      ArrayList<Integer> books = new ArrayList<Integer>(Arrays.asList(7, 6, 5, 4, 3));
      Bookshelf shelf = new Bookshelf(books);
      expected = "[7, 6, 5, 4, 3]";
      actual = shelf.toString();
      printResult(expected, actual);
   }

   /**
    * Test the addLast() method which inserts book with specified height at the
    * end of the Bookshelf.
    *
    * Start by creating an empty shelf, invoke addLast on the same shelf three
    * times by adding three books to the end of the shelf. Each test case
    * should print out both the expected and the actual result, followed by a
    * conclusion in the format of "Test passed? [true/false]" in which case
    * true or false depends on whether the expected and the actual value
    * are the same.
    *
    * Note that the preconditions of addLast() is: height > 0
    * (height of book is always positive).
    */
   public static void testAddLast() {
      System.out.println("Testing addLast method...");
      String expected;
      String actual;

      // Case 1: add a book of height 7 to an empty shelf.
      // Should return [7].
      Bookshelf shelf = new Bookshelf();
      shelf.addLast(7);
      expected = "[7]";
      actual = shelf.toString();
      printResult(expected, actual);

      // Case 2: add a book of height 8 to the end of the same shelf.
      // Should return [7, 8].
      shelf.addLast(8);
      expected = "[7, 8]";
      actual = shelf.toString();
      printResult(expected, actual);

      // Case 3: continue to add a book (of height 10) to the end of
      // the same shelf. Should return [7, 8, 10].
      shelf.addLast(10);
      expected = "[7, 8, 10]";
      actual = shelf.toString();
      printResult(expected, actual);
   }

   /**
    * Test the addFront() method which inserts book with specified height at
    * the start of the Bookshelf.
    *
    * Start by creating an empty shelf, invoke addLast on the same shelf three
    * times by adding three books to the front of the shelf. Each test case
    * should print out both the expected and the actual result, followed by a
    * conclusion in the format of "Test passed? [true/false]" in which case
    * true or false depends on whether the expected and the actual value
    * are the same.
    *
    * Note that the preconditions of addFront() is: height > 0
    * (height of book is always positive)
    */
   public static void testAddFront() {
      System.out.println("Testing addFront method...");
      String expected;
      String actual;

      // Case 1: add a book of height 7 to an empty shelf.
      Bookshelf shelf = new Bookshelf();
      shelf.addFront(7);
      expected = "[7]";
      actual = shelf.toString();
      printResult(expected, actual);

      // Case 2: add a book of height 8 to the front of the same shelf.
      // Should return [8, 7]].
      shelf.addFront(8);
      expected = "[8, 7]";
      actual = shelf.toString();
      printResult(expected, actual);

      // Case 3: continue to add a book (of height 10) to the front of
      // the same shelf. Should return [10, 8, 7].
      shelf.addFront(10);
      expected = "[10, 8, 7]";
      actual = shelf.toString();
      printResult(expected, actual);
   }

   /**
    * Test the removeLast() method which removes book at the end of the
    * Bookshelf and returns the height of the removed book.
    *
    * Start by creating a shelf with three books, invoke removeLast on the same
    * shelf three times. Each test case should print out both the expected and
    * the actual result, followed by a conclusion in the format of "Test
    * passed? [true/false]" in which case true or false depends
    * on whether the expected and the actual value are the same.
    *
    * Note the precondition of removeLast() is: BookShelf is non-empty.
    */
   public static void testRemoveLast() {
      System.out.println("Testing removeLast method...");
      String expected;
      String actual;

      // Case 1: remove the last book from shelf [7, 8, 9].
      // Should return [7, 8].
      ArrayList<Integer> books = new ArrayList<Integer>(Arrays.asList(7, 8, 9));
      Bookshelf shelf = new Bookshelf(books);
      shelf.removeLast();
      expected = "[7, 8]";
      actual = shelf.toString();
      printResult(expected, actual);

      // Case 2: continue to remove the last book from the same shelf.
      // Should return [7].
      shelf.removeLast();
      expected = "[7]";
      actual = shelf.toString();
      printResult(expected, actual);

      // Case 3: continue to remove the last book from the same shelf.
      // This shelf should now be empty. Hence, should return [].
      shelf.removeLast();
      expected = "[]";
      actual = shelf.toString();
      printResult(expected, actual);
   }

   /**
    * Test the removeFront() method which removes book at the front of the
    * Bookshelf and returns the height of the removed book.
    *
    * Start by creating a shelf with three books, invoke removeFront on the
    * same shelf three times. Each test case should print out both the expected
    * and the actual result, followed by a conclusion in the format of
    * "Test passed? [true/false]" in which case true or false depends
    * on whether the expected and the actual value are the same.
    *
    * Note the precondition of removeFront() is: BookShelf is non-empty.
    */
   public static void testRemoveFront() {
      System.out.println("Testing removeFront method...");
      String expected;
      String actual;

      // Case 1: remove the first book from shelf [7, 8, 9].
      // Should return [8, 9].
      ArrayList<Integer> books = new ArrayList<Integer>(Arrays.asList(7, 8, 9));
      Bookshelf shelf = new Bookshelf(books);
      shelf.removeFront();
      expected = "[8, 9]";
      actual = shelf.toString();
      printResult(expected, actual);

      // Case 2: continue to remove the last book from the same shelf.
      // Should return [9].
      shelf.removeFront();
      expected = "[9]";
      actual = shelf.toString();
      printResult(expected, actual);

      // Case 3: continue to remove the last book from the same shelf.
      // This shelf should now be empty. Hence, should return [].
      shelf.removeFront();
      expected = "[]";
      actual = shelf.toString();
      printResult(expected, actual);
   }

   /**
    * Test the isSorted() method which returns true iff the books on this
    * Bookshelf are in non-decreasing order.
    *
    * Tested cases include: unordered shelf, ordered shelf, ordered shelf with
    * duplicates, shelf with only one book, empty shelf. Each test case should
    * print out both the expected and the actual result, followed by a conclusion
    * in the format of "Test passed? [true/false]" in which case true or false
    * depends on whether the expected and the actual value are the same.
    */
   public static void testIsSorted() {
      System.out.println("Testing isSorted method...");
      boolean expected;
      boolean actual;

      // Case 1: [7, 3, 5, 10], unordered shelf, should return false.
      ArrayList<Integer> unorderedBooks = new ArrayList<Integer>(Arrays.asList(7, 3, 5, 10));
      Bookshelf unorderedShelf = new Bookshelf(unorderedBooks);
      expected = false;
      actual = unorderedShelf.isSorted();
      printResult(expected, actual);

      // Case 2: [7, 8, 11, 15], ordered shelf, should return true.
      ArrayList<Integer> orderedBooks = new ArrayList<Integer>(Arrays.asList(7, 8, 11, 15));
      Bookshelf orderedShelf = new Bookshelf(orderedBooks);
      expected = true;
      actual = orderedShelf.isSorted();
      printResult(expected, actual);

      // Case 3: [7, 8, 11, 11, 15], ordered shelf with duplicate books, should return true.
      orderedBooks = new ArrayList<Integer>(Arrays.asList(7, 8, 11, 11, 15));
      orderedShelf = new Bookshelf(orderedBooks);
      expected = true;
      actual = orderedShelf.isSorted();
      printResult(expected, actual);

      // Case 4: [7], shelf with only one book, should return true.
      orderedBooks = new ArrayList<Integer>(Arrays.asList(7));
      orderedShelf = new Bookshelf(orderedBooks);
      expected = true;
      actual = orderedShelf.isSorted();
      printResult(expected, actual);

      // Case 5: [], empty shelf, should return true.
      Bookshelf emptyShelf = new Bookshelf();
      expected = true;
      actual = emptyShelf.isSorted();
      printResult(expected, actual);
   }

   /**
    * Test the getHeight() method which gets the height of the book at the
    * given position.
    *
    * Test by getting the book height at different positions on a shelf.
    * Each test case should print out both the expected and the actual result,
    * followed by a conclusion in the format of "Test passed? [true/false]"
    * in which case true or false depends on whether the expected and the
    * actual value are the same.
    *
    * Note the precondition of getHeight() is: 0 <= position < this.size()
    */
   public static void testGetHeight() {
      System.out.println("Testing getHeight method...");
      int expected;
      int actual;
      ArrayList<Integer> books = new ArrayList<Integer>(Arrays.asList(7, 8, 5, 4, 3));
      Bookshelf shelf = new Bookshelf(books); // Create bookshelf: [7, 8, 5, 4, 3]

      // Case 1: get the first book height, should return 7.
      expected = 7;
      actual = shelf.getHeight(0);
      printResult(expected, actual);

      // Case 2: get the second book height, should return 8.
      expected = 8;
      actual = shelf.getHeight(1);
      printResult(expected, actual);

      // Case 3: get the last book height, should return 3.
      expected = 3;
      actual = shelf.getHeight(4);
      printResult(expected, actual);

      // Case 4: test bookshelf with only one book and get the book's height.
      // Should return 7.
      books = new ArrayList<Integer>(Arrays.asList(7)); // bookshelf: [7]
      shelf = new Bookshelf(books);
      expected = 7;
      actual = shelf.getHeight(0);
      printResult(expected, actual);
   }

   /**
    * Test the size() method which gets the number of books on a shelf.
    *
    * Test by getting the book size of different shelves.
    * Each test case should print out both the expected and the actual result,
    * followed by a conclusion in the format of "Test passed? [true/false]"
    * in which case true or false depends on whether the expected and the
    * actual value are the same.
    */
   public static void testSize() {
      System.out.println("Testing size method...");
      int expected;
      int actual;

      // Case 1: test a bookshelf with five books in it. Should return 5.
      ArrayList<Integer> books = new ArrayList<Integer>(Arrays.asList(7, 11, 5, 4, 3));
      Bookshelf shelf = new Bookshelf(books);
      expected = 5;
      actual = shelf.size();
      printResult(expected, actual);

      // Case 2: test a bookshelf with three books in it. Should return 3.
      books = new ArrayList<Integer>(Arrays.asList(5, 4, 3));
      shelf = new Bookshelf(books);
      expected = 3;
      actual = shelf.size();
      printResult(expected, actual);

      // Case 3: test an empty bookshelf. Should return 0.
      shelf = new Bookshelf();
      expected = 0;
      actual = shelf.size();
      printResult(expected, actual);

      // Case 4: test a bookshelf with only one book in it. Should return 1.
      books = new ArrayList<Integer>(Arrays.asList(7));
      shelf = new Bookshelf(books);
      expected = 1;
      actual = shelf.size();
      printResult(expected, actual);
   }

   /**
    * Juxtapose expected string value with the actual string value.
    * Prints out the test result: "Test passed? ture" if expected equals actual,
    * "Test passed? false" otherwise.
    */
   private static void printResult(String expected, String actual) {
      System.out.println("Result expected to be: " + expected +
                        "\nActual result is:      " + actual);
      System.out.println("Test passed? " + expected.equals(actual));
   }

   /**
    * Juxtapose expected int value with the actual int value.
    * Prints out the test result: "Test passed? ture" if expected equals actual,
    * "Test passed? false" otherwise.
    */
   private static void printResult(int expected, int actual) {
      System.out.println("Result expected to be: " + expected +
                        "\nActual result is:      " + actual);
      System.out.println("Test passed? " + (expected == actual));
   }

   /**
    * Juxtapose expected boolean value with the actual boolean value.
    * Prints out the test result: "Test passed? ture" if expected equals actual,
    * "Test passed? false" otherwise.
    */
   private static void printResult(boolean expected, boolean actual) {
      System.out.println("Result expected to be: " + expected +
                        "\nActual result is:      " + actual);
      System.out.println("Test passed? " + (expected == actual));
   }

}