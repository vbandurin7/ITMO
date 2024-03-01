import grammarParser.MyGrammarLexer;
import grammarParser.MyGrammarParser;
import model.Grammar;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import parser.Lab2Parser;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

public class Visualizer {
    private static final String EXAMPLE_FORMULA = "(a in b) or not (c not in d xor (a not in b))";

    public static void main(String[] args) throws IOException {
        MyGrammarLexer lexer = new MyGrammarLexer(CharStreams.fromFileName("src/main/resources/lab2.txt"));
        MyGrammarParser grammarParser = new MyGrammarParser(new CommonTokenStream(lexer));
        MyGrammarParser.Input_grammarContext inputGrammarContext = grammarParser.input_grammar();
        Grammar grammar = inputGrammarContext.grammar;

        Lab2Parser parser = new Lab2Parser(grammar);
        Lab2Parser.E parsed = parser.parse(EXAMPLE_FORMULA);
        try (
                PrintWriter printWriter = new PrintWriter(Files.newOutputStream(
                        Path.of("src/main/resources/tree/lab2output.dot")))
        ) {
            printWriter.write(parsed.toDotGraph());
        } catch (IOException e) {
            System.err.println("An I/O error occured during .dot file generation: " + e.getLocalizedMessage());
        }
    }
}
