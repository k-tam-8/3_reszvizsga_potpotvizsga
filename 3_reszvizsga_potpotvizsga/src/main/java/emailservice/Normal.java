package emailservice;

import java.util.Locale;

public class Normal implements Email{

    private User from;
    private User to;
    private String subject;
    private String content;

    public Normal(User from, User to, String subject, String content) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.content = content;
    }

    @Override
    public User getFrom() {
        return this.from;
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
        return this.subject.toLowerCase().contains("important");
    }
}
