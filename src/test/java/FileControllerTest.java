import controllers.FileController;
import org.junit.After;
import org.junit.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public class FileControllerTest {

    private String filepath = "src/main/resources/test.txt";

    @After
    public void flush() {
        File file = new File(filepath);
        boolean delete = file.delete();
    }

    @Test
    public void shouldCreateNewFile() {
        FileController.writeToUsersFile("", filepath);

        File file = new File(filepath);
        assertThat(file).exists();
    }

    @Test
    public void shouldContainTest() {
        String text = "test";
        String separator = ";";
        FileController.writeToUsersFile(text, filepath);

        File file = new File(filepath);
        assertThat(file).hasContent(text + separator);
    }

    @Test
    public void shouldAppendFile() {
        String text = "test;";
        String separator = ";";
        FileController.writeToUsersFile(text, filepath);
        FileController.writeToUsersFile(text, filepath);

        File file = new File(filepath);
        assertThat(file).hasContent(text + separator + text + separator);
    }
}