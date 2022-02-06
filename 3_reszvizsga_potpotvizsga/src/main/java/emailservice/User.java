package emailservice;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String emailAddress;
    private List<Email> incoming = new ArrayList<>();
    private List<Email> sent = new ArrayList<>();
    private boolean hasSpamFilter;

    public User(String email) {
        this.emailAddress = email;
    }

    public String getEmailAddress(Email email) {
        getEmail(email);
        return email.getFrom().getEmailAddress();
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public List<Email> getIncoming() {
        return new ArrayList<>(incoming);
    }

    public List<Email> getSent() {
        return new ArrayList<>(sent);
    }

    public boolean hasSpamFilter() {
        return hasSpamFilter;
    }

    public void getEmail(Email email) {
        if (this.hasSpamFilter && email.getSubject().toLowerCase().contains("spam")) {
            throw new IllegalStateException("Be careful, tis is a spam!");
        } else if (email.isImportant()) {
            incoming.add(0, email);
        } else
            incoming.add(email);
    }

    public void sendEmail(String subject, String content, User to) {
        Email newNormalEmail = new Normal(this, to, subject, content);
        sent.add(newNormalEmail);
        to.getEmail(newNormalEmail);
    }

    public void spamFilterChange() {
        this.hasSpamFilter = !hasSpamFilter;
    }

    @Override
    public String toString() {
        return "User{" +
                "emailAddress='" + emailAddress + '\'' +

                '}';
    }
}
