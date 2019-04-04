import java.io.PrintWriter;

class Assignment6 {
  /**
   * Function to check to see if there is an element in the input array that fulfills the condition
   * described below
   * 
   * @param arr, the array we traverse to see if there is an element with d1 - element < eps eps, as
   *        described above d1, as described above
   * 
   * @return a boolean, based on the above condition
   * 
   * @pre array cannot be null array must be non-empty
   * 
   * @post if there exists an element 'd2' in 'arr' such that d1 - d2 < eps, return true else,
   *       return false
   */
  public static boolean contains(double[] arr, double eps, double d1) {
    // see if any element matches our condition
    for (double d2 : arr) {
      if (Math.abs(d2 - d1) < eps) {
        return true;
      } // if
    } // for

    return false;
  } // 'contains' function

  /**
   * Function to efficiently do modular exponentiation
   * 
   * @param x, y, and z, integers
   * 
   * @return an integer, equal to x^y mod m
   * 
   * @pre y must be a power of two (there must be an integer 'n' such that 2^n = y)
   * 
   * @post value returned is equal to x^y mod m
   */
  public static int fastModExp(int x, int y, int m) {
    // base case
    if (y <= 1) {
      return x % m;
    } else if (y % 2 == 0) {
      int temp = fastModExp(x, y / 2, m); // got the idea to store in a 'temp' variable from Giang,
                                          // makes analysis way easier
      return (temp * temp) % m;
    } else {
      return x * fastModExp(x, y - 1, m);
    } // else
  } // 'fastModExp' function


  /**
   * Function to return all possible pairs from an array of ints
   * 
   * @param arr, the array of ints
   * 
   * @return an array of IntPair objects (all possible pairs)
   * 
   * @pre 'arr' cannot be null
   * 
   * @post the returned array contains every permutation of length 2 of the items in 'arr'
   */
  public static IntPair[] allPairs(int[] arr) {
    if (arr == null) {
      throw new IllegalArgumentException();
    }

    IntPair[] pairs = new IntPair[arr.length * arr.length];
    int index = 0;

    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr.length; j++) {
        pairs[index++] = new IntPair(arr[i], arr[j]);
      } // for
    } // for

    return pairs;
  } // allPairs function

  /**
   * Function to take an array of strings and return a string with each element of the array
   * concatenated a certain number of times
   * 
   * @param arr, an array of strings n, how many times each string should be repeated
   * 
   * @return a string
   * 
   * @pre 'arr' cannot be null 'n' should be low enough so that there is enough memory for the
   *      returned string 'n' should be nonnegative
   * 
   * @post the returned string only contains strings from 'arr', and it contains every one at least
   *       once (unless n=0, where the return string is "") each string from 'arr' appears exactly
   *       'n' times, with strings staying in array order (first all copies of the 0th index, then
   *       all copies of the 1st index, etc.)
   */
  public static String concatAndReplicateAll(String[] arr, int n) throws IllegalArgumentException {
    if (arr == null) {
      throw new IllegalArgumentException();
    }

    String ret = "";
    for (String str : arr) {
      for (int i = 0; i < n; i++) {
        ret += str;
      } // for
    } // for

    return ret;
  } // concatAndReplicateAll function

  /**
   * Function to interleave two arrays of ints
   * 
   * @param arr1, first array of ints arr2, second array of ints
   * 
   * @return a third array of ints, the result of interleaving the first two
   * 
   * @pre arr1 and arr2 cannot be null
   * 
   * @post the returned array is alternating elements of arr1 and arr2, preserving array order every
   *       element of arr1 and arr2 is used exactly once once arr1 or arr2 runs out of elements, add
   *       in the rest of the elements of the other array
   */
  public static int[] interleave(int[] arr1, int[] arr2) {
    int i = 0;
    int j = 0;
    int[] ret = new int[arr1.length + arr2.length];

    // while both arrays still have elements, interleave
    while (i < arr1.length && j < arr2.length) {
      ret[i + j] = arr1[i];
      i++;
      ret[i + j] = arr2[j];
      j++;
    } // while

    // if all elements added from arr2, add elements from arr1 (if applicable)
    while (i < arr1.length) {
      ret[i + j] = arr1[i];
      i++;
    } // while

    // if arr2 all elements added from arr1, add elements from arr2 (if applicable)
    while (j < arr2.length) {
      ret[i + j] = arr2[j];
      j++;
    } // while

    return ret;
  } // interleave function

  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);

    double[] test = new double[] {4, 3, 2, 1};
    pen.println(contains(test, 0, 5));

    pen.println(fastModExp(3, 9, 5));

    IntPair[] pairs = allPairs(new int[] {3, 5, 9});
    for (int i = 0; i < pairs.length; i++) {
      pen.println("(" + pairs[i].getFst() + ", " + pairs[i].getSnd() + ")");
    } // for

    pen.println(concatAndReplicateAll(new String[] {"hello", "world", "!"}, 3));

    int[] test3 = interleave(new int[] {0, 1, 2, 6, 7, 8}, new int[] {3, 4, 5, 78});
    for (int i = 0; i < test3.length; i++) {
      pen.println(test3[i]);
    } // for

  } // main method
} // class Main
