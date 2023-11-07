import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;

public class Main {
    private static final String EXAMPLE_FORMULA = "(a in b) or not (c not in d xor (a not in b))";

    public static void main(String[] args) throws ParseException {
        Parser parser = new Parser();
        Tree parsed = parser.parse(new ByteArrayInputStream(EXAMPLE_FORMULA.getBytes()));
        try (PrintWriter printWriter = new PrintWriter(Files.newOutputStream(Path.of("output.dot")))) {
            printWriter.write(parsed.toDotGraph());
        } catch (IOException e) {
            System.err.println("An I/O error occured during .dot file generation: " + e.getLocalizedMessage());
        }
    }
}
