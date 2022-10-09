// Name: Yueqin Li
// USC NetID: yueqinli
// CSCI455 PA2
// Fall 2022


/**
 * Class BookshelfKeeper 
 *
 * Enables users to perform efficient putPos or pickHeight operation on a bookshelf of books kept in 
 * non-decreasing order by height, with the restriction that single books can only be added 
 * or removed from one of the two *ends* of the bookshelf to complete a higher level pick or put 
 * operation.  Pick or put operations are performed with minimum number of such adds or removes.
 */
public class BookshelfKeeper {

   /**
    * Representation invariant:
    *
    * <put rep. invar. comment here>
    *    1) bookshelf must not be null.
    *    2) contained bookshelf is always in non-decreasing order.
    *    3) currCnt should be a non-negative integer.
    *    4) totalMutCnt should be a non-negative integer &&
    *    should not be smaller than currCnt.
    */

   // <add instance variables here>
   private Bookshelf shelf; // the bookshelf to carry out operations on.
   private int totalMutCnt; // number of total operations carried out, short for total mutator count.
   private int currCnt; // number of operations carried out after a certain move, short for current (operation) count.
   public static final int NOT_FOUND = -1;

   /**
    * Creates a BookShelfKeeper object with an empty bookshelf
    */
   public BookshelfKeeper() {
      shelf = new Bookshelf();
      totalMutCnt = 0;
      currCnt = 0;
      assert isValidBookshelfKeeper();
   }

   /**
    * Creates a BookshelfKeeper object initialized with the given sorted bookshelf.
    * Note: method does not make a defensive copy of the bookshelf.
    *
    * PRE: sortedBookshelf.isSorted() is true.
    */
   public BookshelfKeeper(Bookshelf sortedBookshelf) {
      shelf = sortedBookshelf;
      totalMutCnt = 0;
      currCnt = 0;
      assert isValidBookshelfKeeper();
   }

   /**
    * Removes a book from the specified position in the bookshelf and keeps
    * bookshelf sorted after picking up the book.
    * 
    * Returns the number of calls to mutators on the contained bookshelf used
    * to complete this operation. This must be the minimum number to complete
    * the operation.
    * 
    * PRE: 0 <= position < getNumBooks()
    */
   public int pickPos(int position) {
      assert position >= 0 && position < getNumBooks();

      resetCurrCnt(); // reset current count to zero.

      int endPos = getNumBooks() - 1;
      double absMid = endPos / 2.0;

      if ((double) position <= absMid) {
         int start = 0;
         int end = position;
         currCnt = removeHighestfromLowEnd(shelf, start, end);
      }
      else { // operate from the end side.
         int start = position;
         int end = endPos;
         currCnt = removeLowestfromHighEnd(shelf, start, end);
      }

      totalMutCnt += currCnt; // update total mutator count.
      assert isValidBookshelfKeeper();
      return currCnt;
   }


   /**
    * Inserts book with specified height into the shelf.
    * Keeps the contained bookshelf sorted after the insertion.
    * 
    * Returns the number of calls to mutators on the contained bookshelf used
    * to complete this operation. This must be the minimum number to complete
    * the operation.
    * 
    * PRE: height > 0
    */
   public int putHeight(int height) {
      assert height > 0;

      resetCurrCnt();

      currCnt += optimizedInsert(shelf, height);

      totalMutCnt += currCnt; // update total mutator count.
      assert isValidBookshelfKeeper();
      return currCnt;
   }


   /**
    * Returns the total number of calls made to mutators on the contained
    * bookshelf so far, i.e., all the ones done to perform all of the pick
    * and put operations that have been requested up to now.
    */
   public int getTotalOperations() {
      assert isValidBookshelfKeeper();
      return totalMutCnt;
   }



   /**
    * Returns the number of books on the contained bookshelf.
    */
   public int getNumBooks() {
      assert isValidBookshelfKeeper();
      return shelf.size();
   }

   /**
    * Returns string representation of this BookshelfKeeper. Returns a String
    * containing height of all books present in the bookshelf in the order they
    * are on the bookshelf, followed by the number of bookshelf mutator
    * calls made to perform the last pick or put operation, followed by the
    * total number of such calls made since we created this BookshelfKeeper.
    * 
    * Example return string showing required format: “[1, 3, 5, 7, 33] 4 10”
    * 
    */
   public String toString() {
      assert isValidBookshelfKeeper();
      return shelf.toString() + " " + currCnt + " " + totalMutCnt;
   }

   /**
    * Returns true iff the BookshelfKeeper data is in a valid state.
    * (See representation invariant comment for details.)
    */
   private boolean isValidBookshelfKeeper() {
      return (shelf != null) && (currCnt >= 0) && (totalMutCnt >= currCnt) && (shelf.isSorted());
   }

   // add any other private methods here
   /**
    * Set currCnt to zero.
    * Simply call this function at the beginning of each mutator to
    * make sure currCnt only counts the operations happened within the method.
    *
    * Note: helper function. Mutator.
    */
   private void resetCurrCnt() {
      this.currCnt = 0;
      assert isValidBookshelfKeeper();
   }

   /**
    * Removes the highest book of an ordered shelf by starting to operate from
    * the lower side. Uses a temporary bookshelf to hold the temporary removed
    * books and keep them in order until put them back in order to the original
    * shelf.
    *
    * @param shelf bookshelf to be operated on.
    * @param low start position to carry out operations.
    * @param high end position to carry out operations.
    *             A.K.A position of the book which is about to be moved.
    * @return number of mutating operations on the given shelf.
    *
    * PRE: shelf is not null && should not be empty. low >= 0 && high >= low.
    * Note: Helper function of pickPos() method. Mutator.
    */
   private int removeHighestfromLowEnd(Bookshelf shelf, int low, int high) {
      assert shelf != null && shelf.size() >= 0 && low >= 0 && low <= high;
      int count = 0; // count the number of mutating operations on the given shelf.
      Bookshelf tempShelf = new Bookshelf(); // temporary shelf to help organize temporarily removed books before putting them back.

      // remove all the books from low to high. Hold all the books except the highest one in the temporary bookshelf.
      for (int i = low; i <= high; i++) {
         int removed = shelf.removeFront();
         count += 1; // update operations already carried out.
         if (i != high) {
            tempShelf.addLast(removed); // if not the last book add it to the temporary bookshelf.
         }
      }

      // put all the temporarily held books back to the original bookshelf.
      int tempShelfSize = tempShelf.size();
      for (int i = 0; i < tempShelfSize; i++) {
         int removed = tempShelf.removeLast();
         shelf.addFront(removed);
         count += 1; // update operations already carried out.
      }

      assert isValidBookshelfKeeper();
      return count;
   }

   /**
    * Removes the lowest book of an ordered shelf by starting to operate from
    * the higher side. Uses a temporary bookshelf to hold the temporary removed
    * books and keep them in order until putting them back in order to the original shelf.
    *
    * @param shelf bookshelf to be operated on.
    * @param high start position to carry out operations.
    * @param low end position to carry out operations.
    *            A.K.A position of the book which is about to be moved.
    * @return number of mutating operations on the given shelf.
    *
    * PRE: shelf is not null && should not be empty. low >= 0 && high >= low.
    * Note: Helper function of pickPos() method. Mutator.
    */
   private int removeLowestfromHighEnd(Bookshelf shelf, int low, int high) {
      assert shelf != null && shelf.size() >= 0 && low >= 0 && low <= high;

      int count = 0; // count the number of mutating operations on the given shelf.
      Bookshelf tempShelf = new Bookshelf(); // temporary shelf to help organize temporarily removed books before putting them back.

      // remove all the books from high to low. Hold all the books except the lowest one in the temporary bookshelf.
      for (int i = high; i >= low; i--) {
         int removed = shelf.removeLast();
         count += 1; // update operations already carried out.
         if (i != low) {
            tempShelf.addFront(removed); // if not the lowest book add it to the temporary bookshelf.
         }
      }

      // put all the temporarily held books back to the original bookshelf.
      int tempShelfSize = tempShelf.size();
      for (int i = 0; i < tempShelfSize; i++) {
         int removed = tempShelf.removeFront();
         shelf.addLast(removed);
         count += 1; // update operations already carried out.
      }

      assert isValidBookshelfKeeper();
      return count;
   }


   /**
    * Find the best insert position on a shelf based on the height of the book.
    *
    * Traverse a bookshelf from the low end to the high end, keep track of
    * 1) the first index of book whose height is not smaller than @param height;
    * 2) the first index of the book that is larger than @param height.
    * decide which end to operate from based on whichever position is the
    * nearest to an end of the bookshelf. Add the new book to the end of the
    * shelf when 1) or 2) are NOT_FOUND.
    *
    * @param shelf Bookshelf to carry out operations on.
    * @param height height of the book to find the best insert location for.
    * @return the number of operations carried out to the shelf in question.
    *
    * PRE: height > 0, shelf != null
    * Note: helper function of putHeight() method. Mutator.
    */
   private int optimizedInsert(Bookshelf shelf, int height) {
      int count = 0;
      int lastIndex = shelf.size() - 1;

      int indexNoSmaller = findNoSmallerIndex(shelf, height);
      if (indexNoSmaller == NOT_FOUND) { // if all the books on the shelf are lower than height, simply add height to the end.
         shelf.addLast(height);
         count += 1;
         return count;
      }

      int indexLarger = findLargerIndex(shelf, height);
      if (indexLarger == NOT_FOUND) { // if all the books on the shelf are not higher than height, simply add height to the end.
         shelf.addLast(height);
         count += 1;
         return count;
      }

      int disToLeftEnd = indexNoSmaller; // number of books lower than height.
      int disToRightEnd = lastIndex - indexLarger + 1; // number of books higher than height.

      if (disToLeftEnd <= disToRightEnd) {
         int start = 0;
         int end = indexNoSmaller - 1;
         count += putHeightFromLowerEnd(shelf, height, start, end);
      }
      else {
         int start = indexLarger;
         int end = lastIndex;
         count += putHeightFromHigherEnd(shelf, height, start, end);
      }

      assert isValidBookshelfKeeper();
      return count;
   }

   /**
    * Find the index of the first book which is not lower than the given height.
    * return NOT_FOUND if fail to find such book.
    * @param shelf an ordered bookshelf to traverse through.
    * @param height height of the book to compare each one on the shelf to.
    * @return index of the first book which is not lower than the given height.
    * return NOT_FOUND if fail to find such book.
    *
    * PRE: shelf cannot be null && height > 0
    * Note: helper function of the optimizedInsert() method. Accessor.
    */
   private int findNoSmallerIndex(Bookshelf shelf, int height) {
      int result = NOT_FOUND;

      for (int i = 0; i < shelf.size(); i++) {
         if (shelf.getHeight(i) >= height) {
            return i;
         }
      }

      return result;
   }

   /**
    * Find the index of the first book which is higher than the given height.
    * return NOT_FOUND if fail to find such book.
    * @param shelf an ordered bookshelf to traverse through.
    * @param height height of the book to compare each one on the shelf to.
    * @return index of the first book which is higher than the given height.
    * return NOT_FOUND if fail to find such book.
    *
    * PRE: shelf cannot be null && height > 0
    * Note: helper function of the optimizedInsert() method. Accessor
    */
   private int findLargerIndex(Bookshelf shelf, int height) {
      int result = NOT_FOUND;

      for (int i = 0; i < shelf.size(); i++) {
         if (shelf.getHeight(i) > height) {
            return i;
         }
      }

      return result;
   }

   /**
    * Put a book of a certain height (the book) onto a designated shelf by
    * removing the books from low to high to a temporary bookshelf first,
    * then add the book to the front of the rest books,
    * then put back all the books on the temporary shelf in the right order.
    * @param shelf bookshelf to be operated on.
    * @param low start position to carry out operations.
    * @param high end position to carry out operations.
    * @return number of mutating operations on the given shelf.
    *
    * PRE: shelf != null && shelf.size() >= 0 && low >= 0 && low <= high
    * Note: helper function of optimizedInsert() method. Mutator.
    */
   private int putHeightFromLowerEnd(Bookshelf shelf, int height, int low, int high) {
      assert shelf != null && shelf.size() >= 0 && low >= 0 && low <= high;

      int count = 0; // count the number of mutating operations on the given shelf.
      Bookshelf tempShelf = new Bookshelf(); // temporary shelf to help organize temporarily removed books before putting them back.

      // remove all the books from low to high. Hold all the books in the temporary bookshelf.
      for (int i = low; i <= high; i++) {
         int removed = shelf.removeFront();
         count += 1; // update operations already carried out.
         tempShelf.addLast(removed); // add all removed books to the temporary bookshelf.
      }

      shelf.addFront(height);
      count += 1;

      // put all the temporarily held books back to the original bookshelf.
      int tempShelfSize = tempShelf.size();
      for (int i = 0; i < tempShelfSize; i++) {
         int removed = tempShelf.removeLast();
         shelf.addFront(removed);
         count += 1; // update operations already carried out.
      }

      assert isValidBookshelfKeeper();
      return count;
   }

   /**
    * Put a book of a certain height (the book) onto a designated shelf by
    * removing the books from high to low to a temporary bookshelf first,
    * then add the book to the end of the rest books,
    * then put back all the books on the temporary shelf in the right order.
    * @param shelf bookshelf to be operated on.
    * @param low start position to carry out operations.
    * @param high end position to carry out operations.
    * @return number of mutating operations on the given shelf.
    *
    * PRE: shelf != null && shelf.size() >= 0 && low >= 0 && low <= high
    * Note: helper function of optimizedInsert() method. Mutator.
    */
   private int putHeightFromHigherEnd(Bookshelf shelf, int height, int low, int high) {
      assert shelf != null && shelf.size() >= 0 && low >= 0 && low <= high;

      int count = 0; // count the number of mutating operations on the given shelf.
      Bookshelf tempShelf = new Bookshelf(); // temporary shelf to help organize temporarily removed books before putting them back.

      // remove all the books from low to high. Hold all the books in the temporary bookshelf.
      for (int i = high; i >= low; i--) {
         int removed = shelf.removeLast();
         count += 1; // update operations already carried out.
         tempShelf.addLast(removed); // add all removed books to the temporary bookshelf.
      }

      shelf.addLast(height);
      count += 1;

      // put all the temporarily held books back to the original bookshelf.
      int tempShelfSize = tempShelf.size();
      for (int i = 0; i < tempShelfSize; i++) {
         int removed = tempShelf.removeFront();
         shelf.addLast(removed);
         count += 1; // update operations already carried out.
      }

      assert isValidBookshelfKeeper();
      return count;
   }

}
