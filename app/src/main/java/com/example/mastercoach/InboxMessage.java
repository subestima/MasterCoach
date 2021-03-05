package com.example.mastercoach;

public class InboxMessage
{
    //Class data

    String author, subject, body;

    int mailIndex;

    Team interestedTeam;

    //Constructor

    public InboxMessage(String author, String subject, String body, int mailIndex, Team interestedTeam)
    {
        this.author = author;
        this.subject = subject;
        this.body = body;
        this.mailIndex = mailIndex;
        this.interestedTeam = interestedTeam;
    }

    //Getters

    String getAuthor()
    {
        return this.author;
    }

    String getSubject()
    {
        return this.subject;
    }

    String getBody()
    {
        return this.body;
    }

    int getMailIndex()
    {
        return this.mailIndex;
    }

    Team getInterestedClub()
    {
        return this.interestedTeam;
    }
}