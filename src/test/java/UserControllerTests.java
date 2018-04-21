import controllers.UserController;
import models.User;
import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class UserControllerTests {

    @Test
    public void shouldBeFalseForAdam() {
        UserController uc = new UserController();
        User user = new User();
        String login = "Adam";
        user.setLogin(login);

        boolean actual = uc.checkUserExist(login);

        assertThat(actual).isFalse();
    }

    @Test
    public void shouldBeTrueForAdam(){
        UserController uc = new UserController();
        User user = new User();
        String login = "Adam";
        user.setLogin(login);
        User[] users = new User[]{user};
        uc.setUserList(Arrays.asList(users));

        boolean actual = uc.checkUserExist(login);

        assertThat(actual).isTrue();
    }
}
