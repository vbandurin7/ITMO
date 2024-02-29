import grammarParser.MyGrammarLexer;
import grammarParser.MyGrammarParser;
import model.Grammar;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import parser.Lab2Parser;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;


public class Lab2ParserTest {
    private static Grammar grammar;

    @BeforeAll
    public static void init() throws IOException {
        MyGrammarLexer lexer = new MyGrammarLexer(CharStreams.fromFileName("src/main/resources/lab2.txt"));
        MyGrammarParser parser = new MyGrammarParser(new CommonTokenStream(lexer));
        MyGrammarParser.Input_grammarContext inputGrammarContext = parser.input_grammar();
        grammar = inputGrammarContext.grammar;
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "a in b",
            "a not in b",
            "(a not in b)",
            "((c in d))",
            "(a   in b)     or (     c    not in         b  )",
            "not a not   in b     and (c in d) xor (b not in g)",
            "not not not not a in b",
            "(not (not a in b and c not in d))"
    })
    void validParseTest(String formula) {
        new Lab2Parser(grammar).parse(formula);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "hellof sdf",
            "not a",
            "()",
            "(a in) b",
            "(a in b",
            "ff in g",
            "1 in g",
            "f in g)",
            "(u in b))",
            "(f in g) and xor not c in d",
            "x in in y",
            "a in not in b",
            "(x in y) and and (a in b)",
            "b in a (x in y)",
            "a not in (b)",
            "B in c",
            "x in (a in b)",
            "b and c",
            "(a in b) ==> (c in d)"
    })
    void invalidParseTest(String formula) {
        assertThrows(RuntimeException.class, () -> new Lab2Parser(grammar).parse(formula));
    }
}
