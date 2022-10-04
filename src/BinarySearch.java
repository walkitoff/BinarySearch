import java.security.SecureRandom;
import java.util.Arrays;

public class BinarySearch {

    public static void main(String[] args) {
    //testing a commit issue with Github ...
        SecureRandom oRand = new SecureRandom();
        int iasWordsTotal = 10;  //Scalable
        String[] asWords = new String[iasWordsTotal];
        String sTargetString;
        int iTargetIndex;
        int iIndexReturned;
        long lTimeStarted;
        long lTimeEnded;

        //for fun
        // Fill array with random stings w/length from  5 to 20
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb;

        for(int i = 0; i < asWords.length; i++){
            sb = new StringBuilder();
            for(int j = 0; j < oRand.nextInt(16) + 5; j++) {
                int index = oRand.nextInt(alphabet.length());
                char randomChar = alphabet.charAt(index);
                if( oRand.nextInt(100) < 30){  // 30% chance to be lower case
                   randomChar = Character.toLowerCase(randomChar);
                }
                sb.append(randomChar);
            }
            asWords[i] = sb.toString();
        }

        System.out.println(Arrays.toString(asWords)); //unsorted
        // sort the array to enable binary search
        Arrays.sort(asWords);
        System.out.println(Arrays.toString(asWords)); //sorted

        // get target String to pass to our search algorithm
        iTargetIndex = oRand.nextInt(asWords.length); //0 - 9
        sTargetString = asWords[iTargetIndex];
        System.out.println("\nTarget Word's index is: [" + iTargetIndex + "]\n");

        BinarySearch oBinarySearch = new BinarySearch();

        //Begin Binary Search Test
        lTimeStarted = System.nanoTime();
        iIndexReturned = oBinarySearch.findNumberBinarySearch(asWords, sTargetString, (iasWordsTotal-1), 0);
        lTimeEnded = System.nanoTime();
        System.out.println("Binary Search Returned Index: [" + iIndexReturned + "]" );
        System.out.println("Binary Search Elapse Time: " + (lTimeEnded - lTimeStarted));

        //BEGIN Linear Search Test
        lTimeStarted = System.nanoTime();
        iIndexReturned = oBinarySearch.findStringLinearSearch(asWords, sTargetString);
        lTimeEnded = System.nanoTime();
        System.out.println("Linear Search Elapse Time: " + (lTimeEnded - lTimeStarted));
        System.out.println("Linear Search Returned Index: [" + iIndexReturned + "]" );

    }

    private int findNumberBinarySearch(String[] asWords, String sTargetString, int iHighIndex, int iLowIndex){
        //is 1st guess == sTargetString
       int iMiddleIndex = (iHighIndex + iLowIndex) / 2;

       //check if sTargetString is @ iMiddleIndex
       if(asWords[iMiddleIndex].equals(sTargetString)){
           return iMiddleIndex;
       }

       //check if sTargetString is greater than string @ iMiddleIndex
       else if(sTargetString.compareTo(asWords[iMiddleIndex]) > 0){
          return  findNumberBinarySearch(asWords, sTargetString, iHighIndex, (iMiddleIndex + 1)); //recursive

        }
       //check if sTargetString is less than string @ iMiddleIndex
       else {
           return findNumberBinarySearch(asWords, sTargetString, (iMiddleIndex - 1), iLowIndex); //recursive
        }


    }


    //Linear Search returns index of target string
    private int findStringLinearSearch(String[] asWords, String sTargetString){
        for(int i = 0; i < asWords.length; i++){
            if(asWords[i].equals(sTargetString)){
                return i;
            }
        }

        return -1;
    }


}