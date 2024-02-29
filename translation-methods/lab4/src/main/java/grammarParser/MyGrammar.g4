grammar MyGrammar;

@header {
package grammarParser;
import model.*;
import java.util.*;
import java.util.stream.Collectors;
import org.antlr.v4.runtime.Token;
import java.util.Collections;
}

input_grammar returns [model.Grammar grammar]
    : GRAMMAR FILE_NAME SEMICOLON
    attributes_declaration
    rules EOF {
    $grammar = new model.Grammar(
        $FILE_NAME.text,
        $rules.parserRules,
        $rules.lexerRules,
        $attributes_declaration.inheritedArgs,
        $attributes_declaration.synthesizedArgs
    );
    }
    ;

attributes_declaration
    returns [Map<String, List<String>> inheritedArgs, Map<String, List<String>> synthesizedArgs] @init {
    $inheritedArgs = new HashMap<>();
    $synthesizedArgs = new HashMap<>();
    }
    : (NONTERMINAL {
    $inheritedArgs.putIfAbsent($NONTERMINAL.text, new ArrayList<>());
    $synthesizedArgs.putIfAbsent($NONTERMINAL.text, new ArrayList<>());
    } LBRACKET (type TERMINAL COLON (INHERITED {
    $inheritedArgs.get($NONTERMINAL.text).add($type.text + " " + $TERMINAL.text);
    }
    |
    SYNTHESIZED {
    $synthesizedArgs.get($NONTERMINAL.text).add($type.text + " " + $TERMINAL.text);
    }) SEMICOLON)* RBRACKET)*
    ;

rules returns [List<ParserRule> parserRules, List<LexerRule> lexerRules]
    : rls+=singleRule* {
    $parserRules = $rls.stream().filter(r -> r.rule_ instanceof ParserRule).map(r -> (ParserRule) r.rule_).collect(Collectors.toList());
    $lexerRules = $rls.stream().filter(r -> r.rule_ instanceof LexerRule).map(r -> (LexerRule) r.rule_).collect(Collectors.toList());
    }
    ;

singleRule returns [Rule rule]
    : singleParserRule {
        $rule = $singleParserRule.rule;
    }
    | singleLexerRule {
        $rule = $singleLexerRule.rule;
    }
    ;

singleParserRule returns [ParserRule rule] @init {
        NonTerminal leftPart;
        List<Node> rightPart = new ArrayList<>();
    }
    : NONTERMINAL {
        leftPart = new NonTerminal($NONTERMINAL.text, "");
    }
    ARROW
    (
        (
            NONTERMINAL argsCode+=codeBlock? {
            if (!$argsCode.isEmpty()) {
            rightPart.add(new NonTerminal($NONTERMINAL.text, $codeBlock.code));
            } else {
            rightPart.add(new NonTerminal($NONTERMINAL.text, ""));
            }
            }
            | TERMINAL { rightPart.add(new Terminal($TERMINAL.text)); }
        )+
        | EPS { rightPart.add(new Terminal($EPS.text)); }
    ) SEMICOLON afterCode+=codeBlock? {
    if (!$afterCode.isEmpty()) {
        $rule = new ParserRule(leftPart, rightPart, $codeBlock.code);
    } else {
        $rule = new ParserRule(leftPart, rightPart, "");
    }
    }
    ;

codeBlock returns [String code]
    : JAVA_CODE {
    $code = $JAVA_CODE.text;
    }
    ;
singleLexerRule returns [LexerRule rule]
    : TERMINAL ARROW REGEX SEMICOLON {
    $rule = new LexerRule(new Terminal($TERMINAL.text), $REGEX.text.substring(1, $REGEX.text.length() - 1));
    }
    ;

type
    : INT | LONG | DOUBLE
    ;

INT       : 'int';
LONG      : 'long';
DOUBLE    : 'double';

LBRACE        : '{';
RBRACE        : '}';
LBRACKET      : '[';
RBRACKET      : ']';
GRAMMAR       : 'grammar';
SEMICOLON     : ';';
COMMA         : ',';
EPS           : 'eps';
COLON         : ':';
INHERITED     : 'inherited';
SYNTHESIZED   : 'synthesized';
ARROW         : '->';
REGEX: '"' ( ~'"' )* '"';
JAVA_CODE: LBRACE (JAVA_CODE | ~[{}])* RBRACE;

NONTERMINAL: [A-Z][A-Z0-9]*;
TERMINAL: [a-z][a-z0-9]*;
FILE_NAME : [A-Z][A-Za-z0-9_]*;
WS:[ \n\t\r]+ -> channel(HIDDEN);
ENDL : [\r\n]+;
