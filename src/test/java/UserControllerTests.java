import controllers.UserController;
import models.User;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class UserControllerTests {
    UserController uc;
    String login;
    User user;

    @Before
    public void setUp() {
        this.uc = new UserController();
        this.user = new User();
        this.login = "Adam";
        this.user.setLogin(login);
    }

    @Test
    public void shouldBeFalseForAdam() {
        boolean actual = uc.checkUserExist(login);

        assertThat(actual).isFalse();
    }

    @Test
    public void shouldBeTrueForAdam() {
        User[] users = new User[]{user};
        uc.setUserList(Arrays.asList(users));

        boolean actual = uc.checkUserExist(login);

        assertThat(actual).isTrue();
    }
}
