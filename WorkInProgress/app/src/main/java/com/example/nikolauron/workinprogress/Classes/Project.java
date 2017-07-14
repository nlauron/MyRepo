package com.example.nikolauron.workinprogress.Classes;

import java.io.Serializable;

/**
 * Created by Niko Lauron on 7/10/2017.
 */

public class Project implements Serializable {

    private int id;
    private String project;
    private String description;
    private int users;
    private int tasks;

    public Project (int id, String project, String description, int users, int tasks) {
        this.id = id;
        this.project = project;
        this.description = description;
        this.users = users;
        this.tasks = tasks;
    }

    public void setId(int id) {this.id = id;}
    public void setProject(String project) {this.project = project;}
    public void setDescription(String description) {this.description = description;}
    public void setUsers(int users) {this.users = users;}
    public void setTasks(int tasks) {this.tasks = tasks;}

    public int getId() {return id;}
    public String getProject() {return project;}
    public String getDescription() {return description;}
    public int getUsers() {return users;}
    public int getTasks() {return tasks;}
}