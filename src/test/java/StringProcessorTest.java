import org.example.StringProcessor;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StringProcessorTest {

    StringProcessor sp = new StringProcessor();

    @Test
    void repeatThreeTimes() {
        assertEquals("zvzvzv", sp.repeat("zv", 3));
    }

    @Test
    void repeatZeroTimes() {
        assertEquals("", sp.repeat("zv", 0));
    }

    @Test
    void repeatNegativeThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> sp.repeat("zv", -1));
    }

    @Test
    void countOccurrencesNormal() {
        assertEquals(2, sp.countOccurrences("abcabc", "abc"));
    }

    @Test
    void countOccurrencesNotFound() {
        assertEquals(0, sp.countOccurrences("hello", "xyz"));
    }

    @Test
    void countOccurrencesEmptyPartThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> sp.countOccurrences("abc", ""));
    }

    @Test
    void countOccurrencesNullPartThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> sp.countOccurrences("abc", null));
    }

    @Test
    void replaceDigitsNormal() {
        assertEquals("aодинbтри", sp.replaceDigits("a1b3"));
    }

    @Test
    void removeEverySecondNormal() {
        StringBuilder sb = new StringBuilder("abcdef");
        sp.removeEverySecond(sb);
        assertEquals("ace", sb.toString());
    }

    @Test
    void reverseWordsExample() {
        assertEquals(" dd cc bbb aaa", sp.reverseWords(" aaa bbb cc dd"));
    }

    @Test
    void reverseWordsManySpaces() {
        assertEquals("c  b   a  ", sp.reverseWords("a  b   c  "));
    }

    @Test
    void reverseWordsOneWord() {
        assertEquals("abc", sp.reverseWords("abc"));
    }

    @Test
    void reverseWordsOnlySpaces() {
        assertEquals("   ", sp.reverseWords("   "));
    }

    @Test
    void reverseWordsEmpty() {
        assertEquals("", sp.reverseWords(""));
    }

    @Test
    void replaceHexExample() {
        assertEquals("Васе 16 лет", sp.replaceHex("Васе 0x00000010 лет"));
    }

    @Test
    void replaceHexTwoNumbers() {
        assertEquals("16 и 255", sp.replaceHex("0x00000010 и 0x000000FF"));
    }

    @Test
    void replaceHexBigNumber() {
        assertEquals("4294967295", sp.replaceHex("0xFFFFFFFF"));
    }

    @Test
    void replaceHexNoNumbers() {
        assertEquals("просто текст", sp.replaceHex("просто текст"));
    }

    @Test
    void replaceHexTooShortStaysTheSame() {
        assertEquals("0x12", sp.replaceHex("0x12"));
    }
}