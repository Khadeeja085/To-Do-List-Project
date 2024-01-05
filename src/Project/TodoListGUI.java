package Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.PriorityQueue;

    // Main class of the project to run 

public class TodoListGUI {
    public static void main(String[] args) {
    	// Method call
        SwingUtilities.invokeLater(TodoListApp::new);
    }
}

