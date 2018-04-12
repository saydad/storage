package self.start.test.daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author lyongy.liu on 下午 8:21.
 */
public class Test {

    public static void main(String[] args) throws IOException {
        try (
                InputStream resourceAsStream = Test.class.getResourceAsStream("/test.txt");
                InputStreamReader inputStreamReader = new InputStreamReader(resourceAsStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        ) {
            String s = bufferedReader.readLine();
        }
    }
}
