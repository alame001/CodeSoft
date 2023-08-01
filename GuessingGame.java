import java.util.Random;
import java.util.Scanner;

public class GuessingGame
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        Random rd= new Random();

        int minNo = 1;
        int maxNo = 100;
        int maxAttempts = 10;
        int score = 0;

        boolean playAgain = true;

        System.out.println("Welcome to the Guessing Number Game: ");

        while (playAgain)
        {
            int generatedNumber = rd.nextInt(maxNo - minNo + 1) + minNo;
            int attempts = 0;
            boolean correctGuess = false;

            System.out.println("\nGuess a Number between " + minNo + "and" + maxNo + ".");

            while(attempts < maxAttempts && !correctGuess)
            {
                System.out.println("Attempts: "+ (attempts + 1) + "\nEnter Your Guess No:");
                int userGuessNo = sc.nextInt();
                sc.nextLine();

                if(userGuessNo == generatedNumber)
                {
                    System.out.println(" Congratulations! You Guessed The Correct Number: " + generatedNumber);
                    correctGuess = true;
                    score++;
                }
                else if (userGuessNo < generatedNumber)
                {
                    System.out.println("Too Low! Try Again...");
                }
                else
                {
                    System.out.println("Too High! Try Again...");
                }

                attempts++;
            }
            if(!correctGuess)
            {
                System.out.println("Out of Attempts! The Correct Number Was:" + generatedNumber);
            }
            System.out.println("Do You Want To Play Again? (YES/NO): ");
            String playAgaingResponse = sc.nextLine().toLowerCase();

            if(!playAgaingResponse.equals("YES"))
            {
                playAgain = false;
            }
        }
        System.out.println("Game Over!!");
        System.out.println("Your Score: "+score);
        sc.close();
    }
}