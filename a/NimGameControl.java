import java.util.Scanner;

public class NimGameControl {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize heap (you can change the starting number of pieces here)
        int heapSize = 50; // Up to 20 game pieces

        // AI goes first
        boolean isAITurn = false;

        System.out.println("Welcome to the Custom Nim Game!");
        System.out.println("Starting with " + heapSize + " game pieces on the table.");
        System.out.println("On each turn, you can remove at least 1 piece but no more than half the pieces on the table.");
        System.out.println("Whoever takes the last piece loses the game.");
        System.out.println("You will go first.\n");

        while (heapSize > 0) {
            System.out.println("Current number of pieces on the table: " + heapSize);

            if (isAITurn) { 
                // AI's turn
                int remove = NimAI.optimalAIMove(heapSize);
                heapSize -= remove;
                System.out.println("AI removes " + remove + " piece(s).");
            } else {
                // User's turn
                System.out.print("\nYour turn. How many pieces will you remove? ");
                int maxRemove = heapSize / 2;
                if (maxRemove < 1) {
                    maxRemove = 1;
                }
                int remove = scanner.nextInt();
                while (remove < 1 || remove > maxRemove) {
                    System.out.print("Invalid number. You must remove between 1 and " + maxRemove + " pieces: ");
                    remove = scanner.nextInt();
                }
                heapSize -= remove;
            }

            if (heapSize == 0) {
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
