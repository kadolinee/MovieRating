package test.encryptor;

import com.epam.kinorating.encryptor.Encryptor;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class EncryptorTest {
    @Test
    public void testConvertArrayToString() {
        byte[] array = {1, 2, 3};
        String expected =  "1 2 3";
        String actual = Encryptor.convertArrayToString(array);

        assertEquals(expected, actual);
    }

    @Test
    public void testConvertStringToArray() {
        String string = "1 2 3";
        byte[] expected = {1, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        byte[] actual = Encryptor.convertStringToArray(string);

        assertArrayEquals(expected, actual);

    }
}
