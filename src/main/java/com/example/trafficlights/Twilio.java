package com.example.trafficlights;

import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.format.annotation.NumberFormat;
import javax.annotation.MatchesPattern;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Twilio {
    public static final String ACCOUNT_SID = System.getenv("ACCOUNT_SID");
    public static final String AUTH_TOKEN = System.getenv("AUTH_TOKEN");
    public static final String FROM_NUMBER = System.getenv("FROM_NUMBER");

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @NumberFormat(pattern = "###.###.####")
    @NotNull
    private String to;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @MatchesPattern("\\b(?:red|yellow|green|off)\\b")
    @NotNull
    private String body;

    private Message previousMessage;

    public Twilio() {
        com.twilio.Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    public Message sendMessage() {
        Message message = Message.creator(new PhoneNumber(to), new PhoneNumber(FROM_NUMBER), body).create();
        return message;
    }

    public ResourceSet<Message> getMessages() {
        ResourceSet<Message> message = Message.reader().limit(23).read();
        // messages.forEach(message -> System.out.println(message.toString()));
        // ArrayList<String> messages = Message.reader().limit(1).toString();

        message.iterator().next();
        return message;
    }


}