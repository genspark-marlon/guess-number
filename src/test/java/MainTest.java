import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {
  private ByteArrayInputStream iStream;
  private ByteArrayOutputStream oStream;
  private PrintStream pStream;
  private Main GuessNumberGameTest;

  @BeforeEach
  public void setupTest() {
    this.oStream = new ByteArrayOutputStream();
    this.pStream = new PrintStream(this.oStream);
  }

  @Test
  public void tooHighOrLowTestLow() {
    this.GuessNumberGameTest = new Main(System.in, System.out);
    this.GuessNumberGameTest.setRandomNumForTest(5);
    String expected = "Your guess is too high.";
    String actual = this.GuessNumberGameTest.tooHighOrLow(10);
    assertEquals(expected, actual);
  }

  @Test
  public void tooHighOrLowTestHigh() {
    this.GuessNumberGameTest = new Main(System.in, System.out);
    this.GuessNumberGameTest.setRandomNumForTest(15);
    String expected = "Your guess is too low.";
    String actual = this.GuessNumberGameTest.tooHighOrLow(10);
    assertEquals(expected, actual);
  }

  @Test
  public void endOrContinueTestY() {
    this.GuessNumberGameTest = new Main(System.in, System.out);
    String expected = "Continuing the game...";
    String actual = this.GuessNumberGameTest.endOrContinue("Y");
    assertEquals(expected, actual);
  }

  @Test
  public void endOrContinueTestN() {
    this.GuessNumberGameTest = new Main(System.in, System.out);
    String expected = "Thanks for playing...";
    String actual = this.GuessNumberGameTest.endOrContinue("N");
    assertEquals(expected, actual);
  }

  @Test
  public void gameOverTest() {
    this.GuessNumberGameTest = new Main(System.in, System.out);
    String expected = "Game is over.";
    String actual = this.GuessNumberGameTest.gameOver();
    assertEquals(expected, actual);
  }

  @Test
  public void initGameTestWin() {
    this.iStream = new ByteArrayInputStream("Marlon\n\n10\nn".getBytes());
    this.GuessNumberGameTest = new Main(this.iStream, this.pStream);
    this.GuessNumberGameTest.setRandomNumForTest(10);
    this.GuessNumberGameTest.initGame();
    String greeting = "Hello! What is your name?\nWell, Marlon, I am thinking of a number between 1 and 20.\nTake a guess.\n";
    String gResponse = "Good job, Marlon! You guessed my number in 1 guesses!\nWould you like to play again? (y/n)\n";
    String dResponse = "Thanks for playing...\nGame is over.\n";
    String expected = greeting + gResponse + dResponse;
    String actual = this.oStream.toString();
    assertEquals(expected, actual);
  }

  @Test
  public void initGameTestLose() {
    this.iStream = new ByteArrayInputStream("Marlon\n\n15\nn".getBytes());
    this.GuessNumberGameTest = new Main(this.iStream, this.pStream);
    this.GuessNumberGameTest.setRandomNumForTest(10);
    this.GuessNumberGameTest.setMaxGuessesForTest(1);
    this.GuessNumberGameTest.initGame();
    String greeting = "Hello! What is your name?\nWell, Marlon, I am thinking of a number between 1 and 20.\nTake a guess.\n";
    String gResponse = "Your guess is too high.\nYou lose! You've failed 1 times.\nPlay again? (y/n)\n";
    String dResponse = "Thanks for playing...\nGame is over.\n";
    String expected = greeting + gResponse + dResponse;
    String actual = this.oStream.toString();
    assertEquals(expected, actual);
  }

  @AfterEach
  public void tearDownTest() {
    this.iStream = null;
    this.oStream = null;
    this.pStream = null;
    this.GuessNumberGameTest = null;
  }
}
