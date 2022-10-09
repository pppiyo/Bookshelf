// Name: Yueqin Li
// USC NetID: yueqinli
// CSCI455 PA2
// Fall 2022

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Allow the user to perform a series of pickPos and putHeight operations on
 * a bookshelf in an interactive mode with user commands called pick and put.
 * Can also be run in a batch mode by using input and output redirection.
 *
 * PRE: book height >= 0, explicit parameter of pickPos method should not
 * exceed the number of books - 1. input book height serie must be in a
 * non-decreasing order. Except for bookshelf initializations, user input is
 * confined to "pick <index>", "put <height>" and "end".
 *
 * Program will print out a corresponding error message and exit if any of the
 * preconditions are violated during user input. Program will also exit upon
 * receiving the "end" command from the user.
 */
public class BookshelfKeeperProg {

   /**
    * Allow the user to first put in a series of number to initiate a
    * bookshelf. If user input is in violations with the preconditions of
    * Bookshelf and BookshelfKeeper classes, the corresponding error message
    * will be print out and the program will exit. If user input is valid,
    * program will continue to ask the user to give further commands which
    * are confined to "pick <index>", "put <height>" or "end".
    * Any input other than these will cause the program
    * to print an error message and exit. This program can also be run in a
    * batch mode by using input and output redirection.
    */
   public static void main(String[] args) {
      Scanner in = new Scanner(System.in);

      BookshelfKeeper bsk = takeIniBookArrangement(in);
      if (bsk != null) {
         System.out.println("Type pick <index> or put <height> followed by " +
               "newline. Type end to exit.");
         while (bsk != null ) {
            bsk = userDrivedOperations(in, bsk);
         }
      }
      System.out.println("Exiting Program.");
   }

   /**
    * Takes in user input which will represent the initial state of an ordered
    * bookshelf and returns the corresponding BookshelfKeeper instance.
    * In case of taking in empty values or strings with no integers, an empty
    * BookshelfKeeper will be created and returned. Returns null in case of
    * inputs that would render the BookshelfKeeper invalid (i.e. contains
    * non-positive numbers or not in non-decreasing order).
    *
    * @param in a Scanner instance, used to take in user input for the
    *           program to carry out logic operations.
    * @return a valid BookshelfKeeper instance upon valid user input, null
    * upon invalid input (i.e. contains non-positive numbers or not in
    * non-decreasing order).
    */
   private static BookshelfKeeper takeIniBookArrangement(Scanner in) {
      ArrayList<Integer> books = new ArrayList<>();
      System.out.println("Please enter initial arrangement of books followed " +
            "by newline:");
      String userInput = in.nextLine();
      Scanner bookScanner = new Scanner(userInput);

      while (bookScanner.hasNextInt()) {
         int height = bookScanner.nextInt();
         if (height <= 0) {
            printErrInvalidHeight();
            return null;
         }
         if (books.size() != 0) {
            if (height <= books.get(books.size() - 1)) {
               printErrNotOrdered();
               return null;
            }
         }
         books.add(height);
      }

      Bookshelf shelf = new Bookshelf(books);
      BookshelfKeeper shelfKeeper = new BookshelfKeeper(shelf);
      printShelfKeeper(shelfKeeper);
      return shelfKeeper;
   }

   /**
    * Carry out operations on a bookshelf as a bookshelf keeper (bsk) based on
    * user commands. Will invoke pickPos(index) in bsk upon "pick <index>"
    * command, putHeight(height) upon user's "put <height>" command. Will
    * return null on user input "end" or any invalid input. Apart from that,
    * in the case of an invalid input, a corresponding error message will be
    * printed out. *Definition of invalid input see below (@return).
    *
    * @param in a Scanner instance, used to take in user input for the
    *           program to carry out logic operations.
    * @param bsk an BookshelfKeeper instance to carry out operations on.
    * @return a valid BookshelfKeeper instance upon valid user input, null
    * upon invalid input (i.e. contains non-positive numbers or pick
    * position not in valid range, or any command that does not confine
    * to any of the following format: "pick <index>", "put <height>" and
    * "end").
    */
   private static BookshelfKeeper userDrivedOperations(Scanner in, BookshelfKeeper bsk) {
      String userInput = in.nextLine();
      Scanner commandScanner = new Scanner(userInput);

      while(commandScanner.hasNext()) {
         String command = commandScanner.next();
         if (!(command.equals("put") || command.equals("pick") || command.equals("end"))){
            printErrInvalidCommand();
            return null;
         }
         if (command.equals("put")) {
            int height = commandScanner.nextInt();
            if (height <= 0) {
               printErrInvalidHeight();
               return null;
            }
            bsk.putHeight(height);
            printShelfKeeper(bsk);
         }
         if (command.equals("pick")) {
            int position = commandScanner.nextInt();
            if (position >= bsk.getNumBooks()) {
               printErrInvalidPick();
               return null;
            }
            bsk.pickPos(position);
            printShelfKeeper(bsk);
         }
         if (command.equals("end")) {
            return null;
         }
      }
      return bsk;
   }

   /**
    * Prints the string representation of this BookshelfKeeper: A string
    * containing height of all books present in the bookshelf in the order they
    * are on the bookshelf, followed by the number of bookshelf mutator
    * calls made to perform the last pick or put operation, followed by the
    * total number of such calls made since we created this BookshelfKeeper.
    *
    * Example of string showing required format: “[1, 3, 5, 7, 33] 4 10”
    *
    * @param bsk the BookshelfKeeper instance to print.
    */
   private static void printShelfKeeper(BookshelfKeeper bsk) {
      System.out.println(bsk.toString());
   }

   /**
    * Prints an error message to alarm user that the input is a
    * non-positive number.
    */
   private static void printErrInvalidHeight() {
      System.out.println("ERROR: Height of a book must be positive.");
   }

   /**
    * Prints an error message to alarm user that the input array of integers
    * is not in a non-descending order.
    */
   private static void printErrNotOrdered() {
      System.out.println("ERROR: Heights must be specified in non-decreasing order.");
   }

   /**
    * Prints an error message to alarm user that the input is not "pick, put, or end".
    */
   private static void printErrInvalidCommand() {
      System.out.println("ERROR: Invalid command. Valid commands are pick, put, or end.");
   }

   /**
    * Prints an error message to alarm user that the input position for
    * pickPos is not within the valid range.
    */
   private static void printErrInvalidPick() {
      System.out.println("ERROR: Entered pick operation is invalid on this " +
            "shelf.");
   }
}
