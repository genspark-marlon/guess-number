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
    Main GuessNumberGameTest = new Main(System.in, System.out);
    GuessNumberGameTest.setRandomNumForTest(5);
    String expected = "Your guess is too high.";
    String actual = GuessNumberGameTest.tooHighOrLow(10);
    assertEquals(expected, actual);
  }

  @Test
  public void tooHighOrLowTestHigh() {
    Main GuessNumberGameTest = new Main(System.in, System.out);
    GuessNumberGameTest.setRandomNumForTest(15);
    String expected = "Your guess is too low.";
    String actual = GuessNumberGameTest.tooHighOrLow(10);
    assertEquals(expected, actual);
  }

  @Test
  public void endOrContinueTestY() {
    Main GuessNumberGameTest = new Main(System.in, System.out);
    String expected = "Continuing the game...";
    String actual = GuessNumberGameTest.endOrContinue("Y");
    assertEquals(expected, actual);
  }

  @Test
  public void endOrContinueTestN() {
    Main GuessNumberGameTest = new Main(System.in, System.out);
    String expected = "Thanks for playing...";
    String actual = GuessNumberGameTest.endOrContinue("N");
    assertEquals(expected, actual);
  }

  @Test
  public void gameOverTest() {
    Main GuessNumberGameTest = new Main(System.in, System.out);
    String expected = "Game is over.";
    String actual = GuessNumberGameTest.gameOver();
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
