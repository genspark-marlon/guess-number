import java.util.Scanner;

public class Main {
  public static void main (String[] args) {
    int randomNum = (int) (Math.random() * 20 + 1);
    int totalGuesses = 0;
    int maxGuesses = 6;
    int userGuess;
    String userName;
    Scanner userInput = new Scanner(System.in);

    System.out.println("Hello! What is your name?");

    userName = userInput.nextLine();

    System.out.println(String.format("Well, %s, I am thinking of a number between 1 and 20.", userName));

    do {
      System.out.println("Take a guess.");

      userGuess = userInput.nextInt();
      userInput.nextLine();

      if (userGuess < randomNum) {
	System.out.println("Your guess is too low.");
        totalGuesses++;

      } else if (userGuess > randomNum) {
        System.out.println("Your guess is too high.");
        totalGuesses++;

      } else if (userGuess == randomNum) {
        System.out.println(String.format("Good job, %s! You guessed my number in %s guesses!\nWould you like to play again? (y/n)", userName, totalGuesses + 1));
        String userDecision1 = userInput.nextLine();

        if (userDecision1.toLowerCase().equals("y")) {
          randomNum = (int) (Math.random() * 20 + 1);
          totalGuesses = 0;
        } else {
          System.out.println("Thanks for playing. See you next time!");
          totalGuesses = 6;
          userInput.close();
        }

      }

      if (userGuess != randomNum && totalGuesses == 6) {
        System.out.println(String.format("Game over! You've failed %s times.\nPlay again? (y/n)", totalGuesses));
        String userDecision2 = userInput.nextLine();

        if (userDecision2.toLowerCase().equals("y")) {
          randomNum = (int) (Math.random() * 20 + 1);
          totalGuesses = 0;
        } else {
          System.out.println("Thanks for playing.");
          userInput.close();
        }
      }
    } while (totalGuesses < maxGuesses);
  }
}
