package jcombinators.primitive;

import jcombinators.Parser;
import jcombinators.ParserTest;
import org.junit.Test;

import static jcombinators.common.StringParser.literal;

public final class FlatMapTest extends ParserTest {

    private final Parser<String> wordParser = literal("hello");
    private final Parser<Integer> lengthParser = wordParser.flatMap(word -> literal(word).map(String::length));

    @Test
    public void flatMapSuccessTest() {
        assertSuccess(lengthParser, 5, "hellohello");
    }

    @Test
    public void flatMapFailureOuterTest() {
        assertFailure(lengthParser, "syntax error in Test 'flatMapFailureOuterTest' at line 1 and character 1: unexpected character '1', expected the literal 'hello'", "123");
    }

    @Test
    public void flatMapFailureInnerTest() {
        assertFailure(lengthParser, "syntax error in Test 'flatMapFailureInnerTest' at line 1 and character 6: unexpected character ' ', expected the literal 'hello'", "hello 123");
    }

    @Test
    public void flatMapEmptyInputTest() {
        assertFailure(lengthParser, "syntax error in Test 'flatMapEmptyInputTest' at line 1 and character 1: unexpected end of input, expected the literal 'hello'", "");
    }

}
