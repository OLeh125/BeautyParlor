package org.fp.beauty.parlor.model.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Review {
    private int id;
    private LocalDateTime dateTime;
    private String dateTimeFormat;
    private String content;
    private Client client;

    public static class Builder{
        private Review newReview;

        public Builder() {
            newReview = new Review();
        }

        public Builder withId(int id){
            newReview.id = id;
            return this;
        }

        public Builder withContent(String content){
            newReview.content = content;
            return this;
        }
        public Builder withDateTime(LocalDateTime dateTime){
            newReview.dateTime = dateTime;
            newReview.dateTimeFormat =dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            return this;
        }

        public Builder withClient(Client client){
            newReview.client = client;
            return this;
        }

        public Review build(){
            return newReview;
        }

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getDateTimeFormat() {
        return dateTimeFormat;
    }

    public void setDateTimeFormat(String dateTimeFormat) {
        this.dateTimeFormat = dateTimeFormat;
    }
}
