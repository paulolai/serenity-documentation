@RunWith(SerenityRunner.class)
public class UserActivationProcessTest {
    @Steps
    MailActivationSteps steps;

    @Test
    public void should_activate_user_account() {
        // GIVEN
        final User user = new User("user.login", "user@mail.com");
        final ActivationMail activation = new ActivationMail(user.getEmail()
            , String.valueOf(ThreadLocalRandom.current().nextLong()));
        steps.given_activation_send(activation, user);
        // WHEN
        steps.when_user_enter_activation_code();
        // THEN
        steps.then_user_account_activated();
    }

    @Test
    public void should_send_notification_to_user() {
        // GIVEN
        final User user = new User("user.login", "user@mail.com");
        final ActivationMail activation = new ActivationMail(user.getEmail()
            , String.valueOf(ThreadLocalRandom.current().nextLong()));
        steps.given_activation_send(activation, user);
        // WHEN
        steps.when_activation_code_expired();
        // THEN
        steps.then_user_received_notification();
    }
}
