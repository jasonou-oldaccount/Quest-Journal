package com.example.messiah.questjournal;

/**
 * Created by Raul on 3/5/2016.
 */
public class ListElement {
    ListElement() {};

    ListElement(String title, String desc, String deadline, String diff, QuestObject questObject) {
        this.title = title;
        this.difficulty = diff;
        this.description = desc;
        this.deadline = deadline;
        this.questObject = questObject;
    }

    public String title;
    public String difficulty;
    public String description;
    public String deadline;
    public QuestObject questObject;
}
