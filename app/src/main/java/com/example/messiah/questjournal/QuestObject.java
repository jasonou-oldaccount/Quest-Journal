package com.example.messiah.questjournal;

import java.util.Date;

/**
 * Created by Raul on 3/4/2016.
 */
public class QuestObject {
    private String title;
    private int difficulty;
    private String description;
    private int deadline;

    public QuestObject(){}

    public QuestObject(String title, int difficulty, String description, int deadline) {
        this.title = title;
        this.difficulty = difficulty;
        this.description = description;
        this.deadline = deadline;
    }

    public String getTitle() {
        return title;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public String getDiscription() {
        return description;
    }

    public int getDeadline() {
        return deadline;
    }
}
