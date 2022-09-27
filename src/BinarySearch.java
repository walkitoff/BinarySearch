import java.security.SecureRandom;
import java.util.Arrays;

public class BinarySearch {

    public static void main(String[] args) {

        SecureRandom oRand = new SecureRandom();
        int iNumTotal = 100000000;
        int[] aiNums = new int[iNumTotal];
        int iTargetNum;
        int iTargetNumIndex;
        int iIndexReturned;
        long lTimeStarted;
        long lTimeEnded;

        // Fill array with random nums
        for(int i = 0; i < iNumTotal; i++){
            aiNums[i] = oRand.nextInt();
        }

        // sort the array to enable binary search
        Arrays.sort(aiNums);

        // get target num to pass to our search algorithm
        iTargetNumIndex = oRand.nextInt(iNumTotal); //0 - 99
        iTargetNum = aiNums[iTargetNumIndex];
        System.out.println("Target num's index is: [" + iTargetNumIndex + "]\n");

        BinarySearch oBinarySearch = new BinarySearch();

        //Begin Binary Search Test
        lTimeStarted = System.nanoTime();
        iIndexReturned = oBinarySearch.findNumberBinarySearch(aiNums, iTargetNum, (iNumTotal-1), 0);
        lTimeEnded = System.nanoTime();
        System.out.println("Binary Search Returned Index: [" + iIndexReturned + "]" );
        System.out.println("Binary Search Elapse Time: " + (lTimeEnded - lTimeStarted));

        //BEGIN Linear Search Test
        lTimeStarted = System.nanoTime();
        iIndexReturned = oBinarySearch.findNumberLinearSearch(aiNums, iTargetNum);
        lTimeEnded = System.nanoTime();
        System.out.println("Linear Search Elapse Time: " + (lTimeEnded - lTimeStarted));
        System.out.println("Linear Search Returned Index: [" + iIndexReturned + "]" );

    }

    private int findNumberBinarySearch(int[] aiNums, int iTargetNum, int iHighIndex, int iLowIndex){
        //is 1st guess == iTargetNum
       int iMiddleIndex = (iHighIndex + iLowIndex) / 2;

       //check if targetNum is @ iMiddleIndex
       if(aiNums[iMiddleIndex] == iTargetNum){
           return iMiddleIndex;
       }

       //check if target num is greater than num @ iMiddleIndex
       else if(iTargetNum > aiNums[iMiddleIndex]){
          return  findNumberBinarySearch(aiNums, iTargetNum, iHighIndex, (iMiddleIndex + 1));

        }
       //check if target num is less than num @ iMiddleIndex
       else {
           return findNumberBinarySearch(aiNums, iTargetNum, (iMiddleIndex - 1), iLowIndex);
        }


    }


    //Linear Search
    private int findNumberLinearSearch(int[] aiNums, int iTargetNum){
        for(int i = 0; i < aiNums.length; i++){
            if(aiNums[i] == iTargetNum){
                return i;
            }
        }

        return -1;
    }


}