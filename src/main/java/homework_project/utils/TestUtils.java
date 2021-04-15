package homework_project.utils;

import lombok.NoArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.assertj.core.api.Assertions;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Helper class for tests
 */
@NoArgsConstructor
public class TestUtils {
    public static <T> String readResourceFile(Class<T> tClass, String path){
        try{
            InputStream inputStream = tClass.getClassLoader().getResourceAsStream(path);
            return IOUtils.toString(inputStream, StandardCharsets.UTF_8.name());
        } catch (IOException ex){
            Assertions.fail("Can't open test data file from" + path);
        }
        return null;
    }

}
