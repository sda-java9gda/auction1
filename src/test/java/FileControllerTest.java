import controllers.FileController;
import org.junit.After;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FileControllerTest {

    private String filepath = "src/main/resources/test.txt";

    @After
    public void flush() {
        try {
            Files.delete(Paths.get(filepath));
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    @Test
    public void shouldNotBePresent(){
        boolean actual = FileController.checkIfLoginPresent("login",filepath);

        assertThat(actual).isFalse();
    }

    @Test
    public void shouldBePresent(){
        String login = "login";
        FileController.writeToUsersFile(login,filepath);

        boolean actual = FileController.checkIfLoginPresent(login,filepath);

        assertThat(actual).isTrue();
    }

    @Test
    public void shouldNotBeNullForReadFile(){
        List actual = FileController.readFromFile(filepath);

        assertThat(actual).isNotNull();
    }

    @Test
    public void shouldReadFromFile(){
        String filepath = "src/main/resources/testingDb.txt";
    }
}