package Project;

import java.util.Date;// imports the Date class from the java.util package. 
//The Date class is used to represent a specific point in time.

public class Task {
    String name;
    Date date;
    String priority;

    public Task(String name, Date date, String priority) {
        this.name = name;
        this.date = date;
        this.priority = priority;
    }
}