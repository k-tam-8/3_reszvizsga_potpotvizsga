package emailservice;

public class Spam implements Email{

    private User to;
    private String subject;
    private String content;

    public Spam(User to, String subject, String content) {
        this.to = to;
        this.subject = subject;
        this.content = content;
    }

    @Override
    public User getFrom() {
        return new User("spam@spam.com");
    }

    @Override
    public User getTo() {
        return this.to;
    }

    @Override
    public String getSubject() {
        return this.subject;
    }

    @Override
    public String getContent() {
        return this.content;
    }

    @Override
    public boolean isImportant() {
        return Email.super.isImportant();
    }
}
