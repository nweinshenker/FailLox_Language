package com.interpreter.lox;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import static com.interpreter.lox.TokenType.*;


/**
 * Starting at the first character of the source code, the Scanner object
 * figures out what lexeme it belongs to, and consumes it and any following
 * characters that are part of that lexeme.
 */
public class Scanner {
    private final List<Token> tokens = new ArrayList<Token>();
    private final String source;

    private static Map<String, TokenType> keyWords = new HashMap<String, TokenType>() {{
        keyWords.put("and", TokenType.AND);
        keyWords.put("or", TokenType.OR);
        keyWords.put("nil", TokenType.NIL);
    }};
    
    private int start = 0;
    private int current = 0;
    private int line = 1;

    Scanner(String source) {
        this.source = source;
    }

    /**
     * ScanTokens follows the principle of scanning until we reach the final
     * character of the source string. Each time updating start, current, line
     * to their respective postions and creating a new Token.
     * 
     * @param source String associate to a line of input
     * @return List<Token> that are generated from scanning
     */
    public List<Token> scanTokens(String source) {
        while (!isAtEnd()) {
            // We are at the beginning of the next lexeme.
            start = current;
            scanToken();
        }

        tokens.add(new Token(EOF, "", null, line));
        return tokens;
    }

    private void scanToken() {
        char c = advance();
        switch (c) {
            case '(':
                addToken(LEFT_PAREN);
                break;
            case ')':
                addToken(RIGHT_PAREN);
                break;
            case '{':
                addToken(LEFT_BRACE);
                break;
            case '}':
                addToken(RIGHT_BRACE);
                break;
            case ',':
                addToken(COMMA);
                break;
            case '.':
                addToken(DOT);
                break;
            case '-':
                addToken(MINUS);
                break;
            case '+':
                addToken(PLUS);
                break;
            case ';':
                addToken(SEMICOLON);
                break;
            case '*':
                addToken(STAR);
                break;
            case '!':
                addToken((match('=') ? BANG_EQUAL : BANG));
                break;
            case '<':
                addToken((match('=') ? LESS_EQUAL: LESS));
                break;
            case '>':
                addToken((match('=') ? GREATER_EQUAL : GREATER));
                break;
            case '=':
                addToken((match('=')) ? EQUAL_EQUAL : EQUAL);
                break;
            case '/':
                if (match('/')) {
                    // Continue till the ending line 
                    while(peek() != '\n' && !isAtEnd()){
                        advance();
                    }
                } else {
                    addToken(SLASH);
                }
                break;
            case ' ':
            case '\r':
            case '\t':
                // Ignore whitespace.
                break;
        
            case '\n':
                line++;
                break;
            case '"':   // String literal
                string();
                break;
            default:
                FailLox.error(line, "Unexpected Character");
                break;
        }
    }

    private void string() {
        while (peek() != '"' && !isAtEnd()) {
            if (peek() == '\n') {
                line++;
            }
        }

        if (isAtEnd()) {
            FailLox.error(line, "Unterminated Error");
        }

        // Closing "
        advance();
        String stringLiteral = source.substring(start + 1, current - 1);
        addToken(STRING, stringLiteral);
    }

    private char peek() {
        if (isAtEnd()) return '\0';
        return this.source.charAt(current);
    }

    /**
     * Checks to see if the next character is the expected character given it's not 
     * at the end of the string
     * @param expected  a character that is the expected next character
     * @return  boolean if the next character is the expected one
     */
    private boolean match(char expected) {
        if (isAtEnd()) return false;
        if (source.charAt(current) != expected) return false;

        current++;
        return true;
    }

    private char advance() {
        return source.charAt(current++);
    }

    private void addToken(TokenType type) {
        addToken(type, null);
    }
    
    private void addToken(TokenType type, Object literal) {
        String text = source.substring(start, current);
        tokens.add(new Token(type, text, literal, line));
    }

    private boolean isAtEnd() {
        return current >= this.source.length();
    }
}
