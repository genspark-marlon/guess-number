import java.util.Scanner;

public class Game {
  // game number vars
  private int randomNum = (int) (Math.random() * 20 + 1);
  private boolean newNum;

  // user guess vars
  private int maxGuesses = 6;
  private int totalGuesses;

  // user input vars
  private int userGuess;
  private String userName;
  private String userDecision;

  // user guess method
  public void tooHighOrLow () {
    if (this.userGuess < this.randomNum) {
      System.out.println("Your guess is too low.");
    } else if (this.userGuess > this.randomNum) {
      System.out.println("Your guess is too high.");
    }
    this.totalGuesses++;
  }

  // user decision method
  public void endOrContinue () {
    if (this.userDecision.toLowerCase().equals("y")) {
      this.randomNum = (int) (Math.random() * 20 + 1);
      this.totalGuesses = 0;
      this.newNum = true;
    } else {
      this.userDecision = "n";
      System.out.println("Thanks for playing.");
    }
  }

  public static void gameOver () {
    System.out.println("Game is over.");
  }

  // initialize game method
  public void initGame () {
    Scanner userInput = new Scanner(System.in);
    System.out.println("Hello! What is your name?");
    this.userName = userInput.nextLine();
    System.out.println(String.format("Well, %s, I am thinking of a number between 1 and 20.", userName));

    try {
      do {
        System.out.println("Take a guess.");
        this.userGuess = userInput.nextInt();
        userInput.nextLine();

        if (this.userGuess < this.randomNum || this.userGuess > this.randomNum) {
          this.tooHighOrLow();
        } else if (this.userGuess == this.randomNum) {
          System.out.println(String.format("Good job, %s! You guessed my number in %s guesses!\nWould you like to play again? (y/n)", userName, totalGuesses + 1));
          this.userDecision = userInput.nextLine();
          this.endOrContinue();
        } else if (this.totalGuesses == 6) {
          System.out.println(String.format("Game over! You've failed %s times.\nPlay again? (y/n)", totalGuesses));
          this.userDecision = userInput.nextLine();
          this.endOrContinue();
        }
      } while (this.totalGuesses < this.maxGuesses && this.userDecision != "n");

    } catch (Exception e) {
      System.out.println("Error:" + e);
    } finally {
      userInput.close();
      this.gameOver();
    }
  }

  public static void main (String[] args) {
    Game guessNumberGame = new Game();
    guessNumberGame.initGame();
  }
}
