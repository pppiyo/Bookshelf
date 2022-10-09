// Name: Yueqin Li
// USC NetID: yueqinli
// CSCI455 PA2
// Fall 2022


/**
 * Class Bookshelf
 * Implements idea of arranging books into a bookshelf.
 * Books on a bookshelf can only be accessed in a specific way so books don’t fall down;
 * You can add or remove a book only when it’s on one of the ends of the shelf.   
 * However, you can look at any book on a shelf by giving its location (starting at 0).
 * Books are identified only by their height; two books of the same height can be
 * thought of as two copies of the same book.
*/
import java.util.ArrayList;

public class Bookshelf {

    /**
      Representation invariant:

      <put rep. invar. comment here>
      1) Shelf is not null.
      2) Book height must be > 0.

   */

   private ArrayList<Integer> pileOfBooks;

   /**
    * Creates an empty Bookshelf object i.e. with no books
    */
   public Bookshelf() {
      pileOfBooks = new ArrayList<Integer>();
      assert isValidBookshelf();
   }

   /**
    * Creates a Bookshelf with the arrangement specified in pileOfBooks. Example
    * values: [20, 1, 9].
    *
    * PRE: pileOfBooks contains an array list of 0 or more positive numbers
    * representing the height of each book.
    */
   public Bookshelf(ArrayList<Integer> pileOfBooks) {
      this.pileOfBooks = new ArrayList<Integer>(pileOfBooks);
      assert isValidBookshelf();
   }

   /**
    * Inserts book with specified height at the start of the Bookshelf, i.e., it
    * will end up at position 0.
    *
    * PRE: height > 0 (height of book is always positive)
    */
   public void addFront(int height) {
      assert height > 0;
      pileOfBooks.add(0, height);
      assert isValidBookshelf();
   }

   /**
    * Inserts book with specified height at the end of the Bookshelf.
    *
    * PRE: height > 0 (height of book is always positive)
    */
   public void addLast(int height) {
      assert height > 0;
      pileOfBooks.add(height);
      assert isValidBookshelf();
   }

   /**
    * Removes book at the start of the Bookshelf and returns the height of the
    * removed book.
    *
    * PRE: this.size() > 0 i.e. can be called only on non-empty BookShelf
    */
   public int removeFront() {
      assert this.size() > 0;
      assert isValidBookshelf();
      return pileOfBooks.remove(0);
   }

   /**
    * Removes book at the end of the Bookshelf and returns the height of the
    * removed book.
    *
    * PRE: this.size() > 0 i.e. can be called only on non-empty BookShelf
    */
   public int removeLast() {
      assert this.size() > 0;
      assert isValidBookshelf();
      return pileOfBooks.remove(pileOfBooks.size() - 1);
   }

   /**
    * Gets the height of the book at the given position.
    *
    * PRE: 0 <= position < this.size()
    */
   public int getHeight(int position) {
      assert position >= 0 && position < this.size();
      assert isValidBookshelf();
      return pileOfBooks.get(position);
   }

   /**
    * Returns number of books on this Bookshelf.
    */
   public int size() {
      assert isValidBookshelf();
      return pileOfBooks.size();
   }

   /**
    * Returns string representation of this Bookshelf. Returns a string with the height of all
    * books on the bookshelf, in the order they are in on the bookshelf, using the format shown
    * by example here:  “[7, 33, 5, 4, 3]”
    */
   public String toString() {

      String strToPrint = "[";

      for (int i = 0; i < pileOfBooks.size(); i++) {
         strToPrint += pileOfBooks.get(i);
         if (i != pileOfBooks.size() - 1) {
            strToPrint += ", ";
         }
      }
      strToPrint += "]";
      assert isValidBookshelf();
      return strToPrint;
   }

   /**
    * Returns true iff the books on this Bookshelf are in non-decreasing order.
    * (Note: this is an accessor; it does not change the bookshelf.)
    */
   public boolean isSorted() {
      assert isValidBookshelf();

      for (int i = 0; i < pileOfBooks.size() - 1; i++) {
         if  (pileOfBooks.get(i) > pileOfBooks.get(i+1)) {
            return false;
         }
      }
      return true;
   }

   /**
    * Returns true iff the Bookshelf data is in a valid state.
    * (See representation invariant comment for more details.)
    */
   private boolean isValidBookshelf() {
      //shelf is not null;
      if (pileOfBooks == null) {
         return false;
      }

      //book height must be > 0.
      for (int i =  0; i < pileOfBooks.size(); i++) {
         if (pileOfBooks.get(i) < 0) {
            return false;
         }
      }
      return true;
   }

}
