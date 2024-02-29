import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class Main {
    private static final String FILE_LOCATION_PREFIX = "src/main/resources/";

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void main(String[] args) throws IOException {
        List<String> testFiles = List.of("1", "2", "3");

        for (String testFile : testFiles) {
            JavaLexer lexer = new JavaLexer(CharStreams.fromFileName(FILE_LOCATION_PREFIX + testFile));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            JavaParser javaParser = new JavaParser(tokens);
            ParseTreeWalker parseTreeWalker = new ParseTreeWalker();
            JavaParser.ClassDeclarationContext tree = javaParser.classDeclaration();
            MyJavaListener myJavaListener = new MyJavaListener(tokens);

            parseTreeWalker.walk(myJavaListener, tree);
            try (FileChannel fileChannel = FileChannel.open(Path.of(FILE_LOCATION_PREFIX + testFile + ".res"),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.READ,
                    StandardOpenOption.WRITE)) {
                fileChannel.write(ByteBuffer.wrap(myJavaListener.getObfuscatedResult().getBytes(StandardCharsets.UTF_8)));
            }
        }
    }
}
