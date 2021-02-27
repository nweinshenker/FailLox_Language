package main.com.interpreter.lox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public class FailLox {
  static boolean hadError = false;
  public static void main(String[] args) throws IOException {
    if (args.length > 1) {
      System.out.println(args[0]);
      System.out.println("Usage: jlox [script]");
      System.exit(64);
    } else if (args.length == 1) {
      runFile(args[0]);
    } else {
      runPrompt();
    }
  }

  /**
   * Usage of the command on someone entering the
   * 
   * @param path: String path telling the location of the faillox program
   * @throws IOException
   */
  public static void runFile(String path) throws IOException {
    byte[] bytes = Files.readAllBytes(Paths.get(path));
    run(new String(bytes, Charset.defaultCharset()));

    if (hadError) {
      System.exit(65);
    }
  }

  public static void runPrompt() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String line;

    for (;;) {
      System.out.println("> ");
      line = br.readLine();
      if (line == null) {
        break;
      }
      run(line);
      hadError = false;
    }

  }

  private static void run(String source) {
    Scanner scanner = new Scanner(source);
    List<Token> list = scanner.scanTokens(source);

    for (Token token: list) {
      System.out.println(token);
    }
  }

  static void error(int line, String message) {
    report(line, " ", message);
  }

  static void report(int line, String where, String message) {
    System.err.println("[line " + line + "] Error" + where + ": " + message);
    hadError = true;
  }

}