public class NimAI {
    public static int optimalAIMove(int heapSize) {
        int maxRemove = heapSize / 2;
        if (maxRemove < 1) {
            maxRemove = 1;
        }

        // Find the largest P-position less than the current heap size
        int targetHeapSize = getPreviousPowerOfTwoMinusOne(heapSize);

        int remove = heapSize - targetHeapSize;

        if (remove < 1 || remove > maxRemove) {
            // If the calculated remove amount is invalid, remove 1 piece
            remove = 1;
        }

        return remove;
    }

    // Function to find the largest P-position less than the current heap size
    public static int getPreviousPowerOfTwoMinusOne(int heapSize) {
        int power = 1;
        while (power <= heapSize) {
            power *= 2;
        }
        return power / 2 - 1;
    }

    public static void setMaxRemove(int newHeapSize) {
        int maxRemove = newHeapSize;
    }
}
