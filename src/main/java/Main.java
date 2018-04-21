import java.util.Scanner;

public class Main {
    public enum State {
        INIT,
        REGISTER,
        LOGGING_IN,
        LOGGED_IN,
        EXIT
    }

    public static void main(String[] args) {
        State state = State.INIT;
        Scanner sc = new Scanner(System.in);

        while (state != State.EXIT) {
            switch (state) {
                case INIT:
                    System.out.println("Welcome to SDAllegro! Pick one:");
                    System.out.println("1 - Log in");
                    System.out.println("2 - Register");
                    System.out.println("0 - Quit");

                    String answer = sc.nextLine();

                    switch (answer) {
                        case ("1"):
                            state = State.REGISTER;
                            break;

                        case ("2"):
                            state = State.LOGGING_IN;
                            break;

                        case ("0"):
                            state = State.EXIT;
                            break;

                        default:
                            System.out.println("Wrong answer!");
                            state = State.INIT;
                            break;
                    }
                    break;

                case REGISTER: {

                }

                case LOGGING_IN: {
                    System.out.println("Input login");
                    String login = sc.nextLine();

                    System.out.println("Input password");
                    String password = sc.nextLine();

                    if (true) {
                        state = State.LOGGED_IN;
                    } else {
                        state = State.INIT;
                    }
                    break;
                }
            }
        }
    }
}
