public class NimAI {
    public static int[] optimalAIMove(int[] heapSizes) {
        // Calculate Nim-sum of all heaps
        int nimSum = calculateNimSum(heapSizes);
        
        // If nimSum is 0, we're in a losing position - just take 1 from largest heap
        if (nimSum == 0) {
            int maxHeap = 0;
            for (int i = 0; i < heapSizes.length; i++) {
                if (heapSizes[i] > heapSizes[maxHeap]) {
                    maxHeap = i;
                }
            }
            return new int[]{maxHeap, 1};
        }

        // Find a heap where removing pieces improves our position
        for (int i = 0; i < heapSizes.length; i++) {
            if (heapSizes[i] == 0) continue;
            
            // Calculate what this heap should become
            int targetSize = heapSizes[i] ^ nimSum;
            if (targetSize < heapSizes[i]) {
                int remove = heapSizes[i] - targetSize;
                // Verify the removal is valid (not more than half)
                int maxRemove = heapSizes[i] / 2;
                if (maxRemove < 1) maxRemove = 1;
                if (remove <= maxRemove) {
                    return new int[]{i, remove};
                }
            }
        }

        // If we can't make an optimal move, take 1 from the largest heap
        int maxHeap = 0;
        for (int i = 0; i < heapSizes.length; i++) {
            if (heapSizes[i] > heapSizes[maxHeap]) {
                maxHeap = i;
            }
        }
        return new int[]{maxHeap, 1};
    }

    private static int calculateNimSum(int[] heapSizes) {
        int nimSum = 0;
        for (int heap : heapSizes) {
            nimSum ^= heap;
        }
        return nimSum;
    }
}
