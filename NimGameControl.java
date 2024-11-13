import java.util.Scanner;
import java.util.Random;

public class NimGameControl {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Initialize three heaps with random sizes between 3 and 10
        int[] heapSizes = new int[3];
        for (int i = 0; i < 3; i++) {
            heapSizes[i] = random.nextInt(8) + 3;  // generates numbers from 3 to 10
        }
        
        // AI goes first
        boolean isAITurn = false;

        System.out.println("Welcome to the Three-Heap Nim Game!");
        System.out.println("Starting heaps: A:" + heapSizes[0] + " B:" + heapSizes[1] + " C:" + heapSizes[2]);
        System.out.println("On each turn, you can remove pieces from one heap (no more than half that heap's size).");
        System.out.println("Whoever takes the last piece loses the game.");
        System.out.println("You will go first.\n");

        while (true) {
            // Display current state
            System.out.println("Current heaps: A:" + heapSizes[0] + " B:" + heapSizes[1] + " C:" + heapSizes[2]);

            if (isAITurn) {
                // AI's turn
                int[] move = NimAI.optimalAIMove(heapSizes);
                int heapIndex = move[0];
                int remove = move[1];
                heapSizes[heapIndex] -= remove;
                System.out.println("AI removes " + remove + " piece(s) from heap " + (char)('A' + heapIndex));
            } else {
                // User's turn
                System.out.print("\nYour turn. Which heap do you want to remove from? (A/B/C): ");
                char heapChoice = scanner.next().toUpperCase().charAt(0);
                int heapIndex = heapChoice - 'A';
                
                while (heapIndex < 0 || heapIndex > 2 || heapSizes[heapIndex] == 0) {
                    System.out.print("Invalid heap. Please choose an available heap (A/B/C): ");
                    heapChoice = scanner.next().toUpperCase().charAt(0);
                    heapIndex = heapChoice - 'A';
                }

                int maxRemove = heapSizes[heapIndex] / 2;
                if (maxRemove < 1) maxRemove = 1;
                
                System.out.print("How many pieces will you remove? (1-" + maxRemove + "): ");
                int remove;
                while (true) {
                    try {
                        remove = scanner.nextInt();
                        if (remove >= 1 && remove <= maxRemove) {
                            break;
                        }
                        System.out.print("Invalid number. You must remove between 1 and " + maxRemove + " pieces: ");
                    } catch (java.util.InputMismatchException e) {
                        System.out.print("Please enter a valid number between 1 and " + maxRemove + ": ");
                        scanner.next(); // Clear the invalid input
                    }
                }
                heapSizes[heapIndex] -= remove;
            }

            // Check if game is over
            if (heapSizes[0] + heapSizes[1] + heapSizes[2] == 0) {
                if (isAITurn) {
                    System.out.println("\nAI took the last piece. AI loses! You win!");
                } else {
                    System.out.println("\nYou took the last piece. You lose! AI wins!");
                }
                break;
            }

            isAITurn = !isAITurn;
            System.out.println();
        }

        scanner.close();
    }
}
