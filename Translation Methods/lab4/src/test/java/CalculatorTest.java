import grammarParser.MyGrammarLexer;
import grammarParser.MyGrammarParser;
import model.Grammar;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import parser.Calculator;

import java.io.IOException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {
    private static Grammar grammar;

    @BeforeAll
    public static void init() throws IOException {
        MyGrammarLexer lexer = new MyGrammarLexer(CharStreams.fromFileName("src/main/resources/calculator.txt"));
        MyGrammarParser parser = new MyGrammarParser(new CommonTokenStream(lexer));
        MyGrammarParser.Input_grammarContext inputGrammarContext = parser.input_grammar();
        grammar = inputGrammarContext.grammar;
    }

    @Test
    void checkCalculatorEvaluations() {
        List<String> expressions = List.of(
                "(25 - 17) / 8 - 1",
                "2   + 2 * 2",
                "((55 - 10) * 7) + (2 + 8 * (16-2 *(74 *(99 + 3) * (11- 9) + 1)))",
                "2*2/5+8",
                "8     * 2                -4 -(4)",
                "-9",
                "-(-9)",
                "3 ^ 2",
                "2 ^ 3 ^ 2",
                "(4 ^ 2 + 16 + 2^2^3) * 2",
                "4 ^ (1/2)"
        );
        List<Double> answers = List.of(
                (25d - 17) / 8 - 1,
                6d,
                ((55d - 10) * 7) + (2 + 8 * (16-2 *(74 *(99 + 3) * (11- 9) + 1))),
                8.8d,
                8d,
                -9d,
                9d,
                9d,
                512d,
                576d,
                2d
                );

        Calculator calculator = new Calculator(grammar);
        for (int i = 0; i < expressions.size(); i++) {
            assertEquals(answers.get(i), calculator.parse(expressions.get(i)).val);
        }
        assertNull(calculator.parse(""));
    }
}
