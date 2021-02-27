# Objective

The goal of the scanner class to correctly tokenize our lexeme into a list of tokens that can be interpreted by our main program

## Ideas

One of the key elements of the lexer is to keep track off all the tokens seen from each
source line.

The main work horse of this class is the scanTokens(String source) method which creates
a seperate token for each of the following lexiemes. What is required to actaully preform this process is to provide a method for handling all different token types. This really is all encapsulated in one large switch statement for each individual character type.

## TO-DO

- [] Create more functionality for multiple token types
- [X] Add one or two lines characters such as "!, =, => , < "
- [] Handling the division operand "/" and differentiating it between "//" comments
- [] Run faillox example interpret to see if it works with single lines
