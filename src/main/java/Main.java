import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
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

  // io streams
  private Scanner iStream;
  private PrintStream pStream;

  public Main (InputStream iStream, PrintStream pStream) {
    this.iStream = new Scanner(iStream);
    this.pStream = pStream;
  }

  // user guess method
  public String tooHighOrLow (int guess) {
    String response = "";
    if (guess < this.randomNum) {
      response = "Your guess is too low.";
    } else if (guess > this.randomNum) {
      response = "Your guess is too high.";
    }
    this.totalGuesses++;
    this.pStream.println(response);
    return response;
  }

  // user decision method
  public String endOrContinue (String decision) {
    String response;
    if (decision.toLowerCase().equals("y")) {
      this.randomNum = (int) (Math.random() * 20 + 1);
      this.totalGuesses = 0;
      this.newNum = true;
      response = "Continuing the game...";
    } else {
      this.userDecision = "n";
      response = "Thanks for playing...";
    }

    this.pStream.println(response);
    return response;
  }

  public String gameOver () {
    String response = "Game is over.";
    this.pStream.println(response);
    return response;
  }

  // initialize game method
  public void initGame () {
    this.pStream.println("Hello! What is your name?");
    this.userName = this.iStream.nextLine();
    this.pStream.println(String.format("Well, %s, I am thinking of a number between 1 and 20.", userName));

      do {
        this.pStream.println("Take a guess.");
        this.userGuess = this.iStream.nextInt();
        this.iStream.nextLine();

        try {
          if (this.userGuess < this.randomNum || this.userGuess > this.randomNum) {
            this.tooHighOrLow(this.userGuess);
          } else if (this.userGuess == this.randomNum) {
            this.pStream.println(String.format("Good job, %s! You guessed my number in %s guesses!\nWould you like to play again? (y/n)", userName, totalGuesses + 1));
            this.userDecision = this.iStream.nextLine();
            this.endOrContinue(this.userDecision);
          }
          
          if (this.totalGuesses == this.maxGuesses) {
            this.pStream.println(String.format("You lose! You've failed %s times.\nPlay again? (y/n)", totalGuesses));
            this.userDecision = this.iStream.nextLine();
            this.endOrContinue(this.userDecision);
          }
        } catch (InputMismatchException e) {
          this.pStream.println("Error:" + e);
        }
      } while (this.totalGuesses < this.maxGuesses && this.userDecision != "n");

    this.iStream.close();
    this.gameOver();
  }

  // strictly for testing method
  public void setRandomNumForTest (int num) {
    this.randomNum = num;
  }

  // strictly for testing method
  public void setMaxGuessesForTest (int num) {
    this.maxGuesses = num;
  }

  public static void main (String[] args) {
    Main GuessNumberGame = new Main(System.in, System.out);
    GuessNumberGame.initGame();
  }
}
