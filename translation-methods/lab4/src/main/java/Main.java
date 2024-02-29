import generator.ParserGenerator;
import grammarParser.MyGrammarLexer;
import grammarParser.MyGrammarParser;
import model.Grammar;
import model.Tree;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;



public class Main {
    public static void main(String[] args) throws IOException {
//        List<String> parserGrammarDescFiles = List.of("lab2.txt", "calculator.txt");
        List<String> parserGrammarDescFiles = List.of("calculator.txt");
        for (String grammarName : parserGrammarDescFiles) {
            MyGrammarLexer lexer = new MyGrammarLexer(CharStreams.fromFileName("src/main/resources/" + grammarName));
            MyGrammarParser parser = new MyGrammarParser(new CommonTokenStream(lexer));
            MyGrammarParser.Input_grammarContext inputGrammarContext = parser.input_grammar();
            Grammar grammar = inputGrammarContext.grammar;
            ParserGenerator parserGenerator = new ParserGenerator(grammar);
            try (PrintWriter printWriter = new PrintWriter(Files.newOutputStream(Path.of("src/main/java/parser/%s.java".formatted(grammar.getName()))))) {
                printWriter.write(parserGenerator.generateParser());
            } catch (IOException e) {
                System.err.println("An I/O error occured during parser generation: " + e.getLocalizedMessage());
            }
        }
    }
}