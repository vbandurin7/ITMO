grammar Java;

classDeclaration
    : modifyer? CLASS ID classBodyDeclaration
    ;

modifyer
    : PUBLIC | PRIVATE | PROTECTED
    ;

classBodyDeclaration
    : LBRACE classBody RBRACE
    ;

classBody
    : (field | constructorDeclaration | methodDeclaration)*
    ;

field
    : modifyer? FINAL? type ID (ASSIGN LITERAL)? SEMICOLON
    ;


constructorDeclaration
    : modifyer? ID LPAREN args RPAREN LBRACE constructorBody RBRACE
    ;

constructorBody
    : (fieldInit | statement)*
    ;

methodDeclaration
    : modifyer? FINAL? (type | VOID) ID LPAREN args RPAREN LBRACE methodBody RBRACE
    ;

methodBody
    : (statement | RETURN expression SEMICOLON)*
    ;

statement
    : ifStatement | whileStatement | forStatement | assignment | methodCall
    ;

ifStatement
    : IF LPAREN condition RPAREN LBRACE methodBody RBRACE
    ;

whileStatement
    : WHILE LPAREN condition RPAREN LBRACE (methodBody | BREAK SEMICOLON | CONTINUE SEMICOLON) RBRACE
    ;

forStatement
    : FOR LPAREN assignment? SEMICOLON condition? SEMICOLON (assignment | methodCall)? RPAREN LBRACE methodBody RBRACE
    ;

expression
    : LPAREN expression RPAREN | expression BINARY_OP expression | NOT expression | methodCall | LITERAL | ID
    ;

condition
    : BOOL | booleanExpression
    ;

fieldInit
    : THIS '.' ID ASSIGN expression SEMICOLON
    ;

methodCall
    : ID ('.' ID)* LPAREN callArgs RPAREN SEMICOLON
    ;

booleanExpression
    : expression BOOLEAN_OP expression
    ;

assignment
    : type? ID ASSIGN (LPAREN type RPAREN)? (expression | LITERAL) SEMICOLON?
    ;

args
    : (arg (',' arg)*)?
    ;

arg
    : type ID
    ;

callArgs
    : (ID (',' ID)*)?
    ;

type
    : ID | INT | LONG | DOUBLE | BOOLEAN
    ;

BOOLEAN_OP
    : GT | LT | EQ | GEQ | LEQ | NEQ
    ;

BINARY_OP
    : ADD | SUB | DIV | MUL
    ;

ADD       : '+';
SUB       : '-';
DIV       : '/';
MUL       : '*';
NOT       : '!';
ASSIGN    : '=';
EQ        : '==';
GT        : '>';
LT        : '<';
GEQ       : '>=';
LEQ       : '<=';
NEQ       : '!=';

SEMICOLON : ';';
LBRACE    : '{';
RBRACE    : '}';
LPAREN    : '(';
RPAREN    : ')';

PUBLIC    : 'public';
PRIVATE   : 'private';
PROTECTED : 'protected';
CLASS     : 'class';
FINAL     : 'final';
THIS      : 'this';

INT       : 'int';
LONG      : 'long';
DOUBLE    : 'double';
BOOLEAN   : 'boolean';
VOID      : 'void';

LITERAL   : NULL | BOOL | ([0-9]+);

NULL      : 'null';
BOOL      : 'true' | 'false';
IF        : 'if';
FOR       : 'for';
WHILE     : 'while';
BREAK     : 'break';
CONTINUE  : 'continue';
RETURN    : 'return';

WS        : [ \t\r\n]+ -> channel(HIDDEN);
ID        : [a-zA-Z_$][a-zA-Z0-9_$]*;


