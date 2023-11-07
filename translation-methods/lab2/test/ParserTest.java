import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "",
            "(a in b) => (b in c)",
            "(b in c) => (c in d) => (d in f)",
            "(b in c) => ((c in d) => (d in f))",
            "a in b",
            "a not in b",
            "(a not in b)",
            "((c in d))",
            "(a   in b)     or (     c    not in         b  )",
            "not a not   in b     and (c in d) xor (b not in g)",
            "not not not not a in b",
            "(not (not a in b and c not in d))"
    })
    void validParseTest(String formula) throws ParseException {
        new Parser().parse(new ByteArrayInputStream(formula.getBytes()));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "hellof sdf",
            "a => b",
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
        assertThrows(ParseException.class, () -> new Parser().parse(new ByteArrayInputStream(formula.getBytes())));
    }
}
