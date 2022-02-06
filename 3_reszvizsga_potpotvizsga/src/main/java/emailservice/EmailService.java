package emailservice;

import java.util.HashSet;
import java.util.Set;

public class EmailService {
    Set<User> users = new HashSet<>();

    public Set<User> getUsers() {
        return new HashSet<>(users);
    }

    public void registerUser(String emailAddress) {
        if (emailAddress.contains("@") && isValidEmailAddress(emailAddress)) {
            isNotTakenEmailAddress(emailAddress);
            users.add(new User(emailAddress.toLowerCase()));
        } else throw new IllegalArgumentException("Email address is not valid: " + emailAddress);

    }

    private void isNotTakenEmailAddress(String emailAddress) {
        for (User u : users) {
            if (u.getEmailAddress().equals(emailAddress.toLowerCase())) {
                throw new IllegalArgumentException("Email address is already taken!");
            }
        }
    }

    private boolean isValidEmailAddress(String emailAddress) {
        int atPos = Integer.MIN_VALUE;
        int dotPos = Integer.MIN_VALUE;
        for (int i = 0; i < emailAddress.length(); i++) {
            if (emailAddress.charAt(i) == '.') {
                dotPos = i;
            }
            if (emailAddress.charAt(i) == '@') {
                atPos = i;
            }
        }
        return (dotPos > atPos + 1 && atPos > 0 && dotPos > 0);
    }

    public void sendEmail(String from, String to, String subject, String content) {
        for (User u : users) {
            if (u.getEmailAddress().equals(from)) {
                for (User usr : users) {
                    if (usr.getEmailAddress().equals(to)) {;
                        u.sendEmail(subject, content, usr);
                    }
                }
            }
        }
    }

    public void sendSpam(String subject, String content) {
        for (User u : users) {
            u.getEmail(new Spam(u, subject, content));
        }
    }
}
