package main.java.Models;

public class Email {
    public Email(String from, String to, String subject, String content) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.content = content;
    }

    public String from;
    public String to;

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public String subject;
    public String content;

    public boolean equals(Email email) {
        Boolean res = true;
        if ((!this.from.equals(email.getFrom())) ||
                (!this.to.equals(email.getTo())) ||
                (!this.content.equals(email.getContent())) ||
                (!this.subject.equals(email.getSubject()))) {
            res = false;
        }
        return res;
    }
}
