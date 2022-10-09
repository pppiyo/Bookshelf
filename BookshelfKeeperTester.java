// Name: Yueqin Li
// USC NetID: yueqinli
// CSCI455 PA2
// Fall 2022

import java.util.ArrayList;
import java.util.Arrays;

public class BookshelfKeeperTester {
   /**
    * Juxtapose expected string value with the actual value.
    * Prints out the test result: "Test passed? ture" if expected equals actual, "Test passed? false" otherwise.
    */
   private static void printResult(String expected, String actual) {
      System.out.println("Result expected to be: " + expected + "\nActual result is:      " + actual);
      System.out.println("Test passed? " + expected.equals(actual));
   }

   /**
    * Juxtapose expected int value with the actual value.
    * Prints out the test result: "Test passed? ture" if expected equals actual, "Test passed? false" otherwise.
    */
   private static void printResult(int expected, int actual) {
      System.out.println("Result expected to be: " + expected + ", actual result is: " + actual);
      System.out.println("Test passed? " + (expected == actual));
   }

   /**
    * Juxtapose expected boolean value with the actual boolean value.
    * Prints out the test result: "Test passed? ture" if expected equals actual, "Test passed? false" otherwise.
    */
   private static void printResult(boolean expected, boolean actual) {
      System.out.println("Result expected to be: " + expected + ", actual result is: " + actual);
      System.out.println("Test passed? " + (expected == actual));
   }

   public static void testPickPos() {
      System.out.println("=========Testing the pickPos method=========");
      // Create a BookshelfKeeper with ordered books [1, 2, 3, 4, 5]
      Bookshelf shelf = new Bookshelf(new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5)));
      BookshelfKeeper bsk = new BookshelfKeeper(shelf);

      // Case 1: pick up the book in the middle (@postion 2). Should return [1, 2, 4, 5] 5 5.
      bsk.pickPos(2);
      String expected = "[1, 2, 4, 5] 5 5";
      String actual = bsk.toString();
      printResult(expected, actual);

      // Case 2: pick up the book in the middle (@postion 2). Should return [1, 2, 5] 3 8.
      bsk.pickPos(2);
      expected = "[1, 2, 5] 3 8";
      actual = bsk.toString();
      printResult(expected, actual);

      // Case 3: continue to pick up the book in the front (@postion 0) from the same shelf. Should return [2, 5] 1 9.
      bsk.pickPos(0);
      expected = "[2, 5] 1 9";
      actual = bsk.toString();
      printResult(expected, actual);

      // Case 4: continue to pick up the book in the back (@postion 1) from the same shelf. Should return [2] 1 10.
      bsk.pickPos(1);
      expected = "[2] 1 10";
      actual = bsk.toString();
      printResult(expected, actual);

      // Case 5: pick up a book from a shelf with only one book inside. Should return [] 1 11
      bsk.pickPos(0);
      expected = "[] 1 11";
      actual = bsk.toString();
      printResult(expected, actual);
   }

   public static void testConstructor() {
      System.out.println("=========Testing the constructor of the BookshelfKeeper class (empty input)=========");
      BookshelfKeeper shelfKeeper = new BookshelfKeeper();
      printResult("[] 0 0", shelfKeeper.toString());

      System.out.println("=========Testing the constructor of the BookshelfKeeper class (sorted input)=========");
      Bookshelf shelf = new Bookshelf(new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5)));
      shelfKeeper = new BookshelfKeeper(shelf);
      printResult("[1, 2, 3, 4, 5] 0 0", shelfKeeper.toString());
   }

   public static void testPutHeight() {
      System.out.println("=========Testing the putHeight method=========");
      // Create an empty BookshelfKeeper
      BookshelfKeeper bsk = new BookshelfKeeper();

      // Case 1: put a book of height 4 onto the shelf. Should return [4] 1 1
      bsk.putHeight(4);
      String expected = "[4] 1 1";
      String actual = bsk.toString();
      printResult(expected, actual);

      // Case 2: put a book of height 7 onto the shelf. Should return [4, 7] 1 2
      bsk.putHeight(7);
      expected = "[4, 7] 1 2";
      actual = bsk.toString();
      printResult(expected, actual);

      // Case 3: put a book of height 8 onto the shelf. Should return [4, 7, 8] 1 3
      bsk.putHeight(8);
      expected = "[4, 7, 8] 1 3";
      actual = bsk.toString();
      printResult(expected, actual);

      // Case 4: put a book of height 5 onto the shelf. Should return [4, 5, 7, 8] 3 6
      bsk.putHeight(5);
      expected = "[4, 5, 7, 8] 3 6";
      actual = bsk.toString();
      printResult(expected, actual);

      // Case 5: put a book of height 6 onto the shelf. Should return [4, 5, 6, 7, 8] 5 11
      bsk.putHeight(6);
      expected = "[4, 5, 6, 7, 8] 5 11";
      actual = bsk.toString();
      printResult(expected, actual);
   }

   public static void testGetTotalOperations() {
      System.out.println("=========Testing the getTotalOperations method=========");

   }

   public static void testGetNumBooks() {
      System.out.println("=========Testing the getNumBooks method=========");

   }

   public static void main(String[] args) {
//      testConstructor();
//      testGetNumBooks();
//      testPickPos();
      testPutHeight();
//      testGetTotalOperations();



   }
}
