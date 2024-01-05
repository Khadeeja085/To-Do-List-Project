package Project;

import java.awt.BorderLayout;// arranges components in a five regions: 
import java.awt.Color;
//North, West, Center, East, and South.
import java.awt.FlowLayout;// arranges components in a left-to-right flow, 
import java.awt.Font;
import java.awt.Graphics;
//adding them to a new row when the current row is full.
import java.awt.GridLayout;//arranges components in a grid of rows and columns.
import java.awt.Image;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;//represents a specific instant in time.
import java.util.PriorityQueue;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;//used for creating a graphical user interface (GUI).
class ImagePanel extends JPanel {
    private Image backgroundImage;

    public ImagePanel(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this);
    }
}
public class TodoListApp {
    private PriorityQueue<Task> taskQueue = new PriorityQueue<>((t1, t2) -> {
        int priorityOrder = t1.priority.compareTo(t2.priority);
        if (priorityOrder == 0) {
            return t1.date.compareTo(t2.date);
        }
        return priorityOrder;
    });//priority queue is initialized with a custom comparator 
    //that compares tasks based on priority and date.

    private JFrame frame;
    private JPanel mainPanel;

    public TodoListApp() {
    	// Constructor sets up the initial frame and main panel for the application.
        frame = new JFrame("Todo List");
        frame.setSize(1280, 680);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load the background image
        ImageIcon backgroundImageIcon = new ImageIcon("C:\\Users\\UNODC-45\\Desktop\\Khadeeja\\3rd Semester\\Data Structures\\3.png");
        Image backgroundImage = backgroundImageIcon.getImage();

        mainPanel = new ImagePanel(backgroundImage);
        showMainMenu();

//        mainPanel = new JPanel();
//        mainPanel.setBackground(Color.WHITE);
//        showMainMenu();

        frame.add(mainPanel);
        frame.setVisible(true);
    }
   //the showMainMenu method sets up the main menu for the TodoList application by
    //creating and adding three buttons ("Add Task," "Show Tasks," and "Delete Task") 
    //to the mainPanel.
    

    private void showMainMenu() {
        mainPanel.removeAll();
      mainPanel.setLayout(new FlowLayout());
     
   // Create a panel for the heading
      JPanel headingPanel = new JPanel();
      headingPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));  // Align the heading to the upper right

   
      
     // Load the image
       ImageIcon originalIcon = new ImageIcon("C:\\Users\\UNODC-45\\Desktop\\Khadeeja\\3rd Semester\\Data Structures\\9.jpg");

        // Set the desired size for the image
      int desiredWidth = 800;  // Replace with your preferred width
      int desiredHeight =600; // Replace with your preferred height

        // Resize the image
      Image resizedImage = originalIcon.getImage().getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        // Create a label to display the resized image
       JLabel imageLabel = new JLabel(resizedIcon);
        // Add the image label to the main panel
        mainPanel.add(imageLabel);
        
        
        JButton addTaskButton = new JButton("Add Task");
        JButton showTasksButton = new JButton("Show Tasks");
        JButton deleteTaskButton = new JButton("Delete Task");
        // Set background and text colors for the Add Task button
        addTaskButton.setBackground(Color.BLACK);
        addTaskButton.setForeground(Color.WHITE);

        // Set background and text colors for the Show Tasks button
        showTasksButton.setBackground(Color.BLACK);
        showTasksButton.setForeground(Color.WHITE);

        // Set background and text colors for the Delete Task button
        deleteTaskButton.setBackground(Color.BLACK);
        deleteTaskButton.setForeground(Color.WHITE);

        addTaskButton.addActionListener(e -> showAddTaskWindow());
        showTasksButton.addActionListener(e -> showTasks());
        deleteTaskButton.addActionListener(e -> showDeleteTaskDialog());
        Font buttonFont = new Font("Impact", Font.PLAIN, 24); 
        
        addTaskButton.setFont(buttonFont);
        showTasksButton.setFont(buttonFont);
        deleteTaskButton.setFont(buttonFont);
        
        mainPanel.add(addTaskButton);
        mainPanel.add(showTasksButton);
        mainPanel.add(deleteTaskButton);
        
        mainPanel.revalidate();
        mainPanel.repaint();
    }
    //this method sets up a window for adding tasks with input fields for task name,
    //date, and priority. The user can fill in the details, click the "Save" button
    //to add the task to the queue, or click the "Back" button to return to the main menu.
    private void showAddTaskWindow() {
        mainPanel.removeAll();
        mainPanel.setLayout(new GridLayout(8, 2));

        JLabel nameLabel = new JLabel("Task Name:");
        JTextField nameField = new JTextField();

        JLabel dateLabel = new JLabel("Task Date:");
        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JComboBox<String> dayComboBox = new JComboBox<>(getDays());
        JComboBox<String> monthComboBox = new JComboBox<>(getMonths());
        JComboBox<String> yearComboBox = new JComboBox<>(getYears());
        datePanel.add(dayComboBox);
        datePanel.add(monthComboBox);
        datePanel.add(yearComboBox);
     // Set font size and style for "Task Date" label
        Font labelFont = new Font("Arial", Font.BOLD, 20);
        dateLabel.setFont(labelFont);

        // Set font size and style for "Task Date" components
        Font componentFont = new Font("Arial", Font.PLAIN, 20);
        dayComboBox.setFont(componentFont);
        monthComboBox.setFont(componentFont);
        yearComboBox.setFont(componentFont);

        JLabel priorityLabel = new JLabel("Task Priority:");
        JPanel priorityPanel = new JPanel();
        JRadioButton highPriorityButton = new JRadioButton("High");
        JRadioButton mediumPriorityButton = new JRadioButton("Medium");
        JRadioButton lowPriorityButton = new JRadioButton("Low");
        ButtonGroup priorityGroup = new ButtonGroup();
        priorityGroup.add(highPriorityButton);
        priorityGroup.add(mediumPriorityButton);
        priorityGroup.add(lowPriorityButton);
        priorityPanel.add(highPriorityButton);
        priorityPanel.add(mediumPriorityButton);
        priorityPanel.add(lowPriorityButton);
        // Set font size and style for "Task Priority" label
        priorityLabel.setFont(labelFont);

        // Set font size and style for "Task Priority" components
        highPriorityButton.setFont(componentFont);
        mediumPriorityButton.setFont(componentFont);
        lowPriorityButton.setFont(componentFont);

        JButton saveButton = new JButton("Save");
        JButton backButton = new JButton("Back");
         
        saveButton.setBackground(Color.WHITE);
        backButton.setBackground(Color.WHITE);
     // Set font size for labels and text fields
        Font labelFont1 = new Font("Arial", Font.BOLD, 25); // You can adjust the size (14 is just an example)
        nameLabel.setFont(labelFont1);
        nameField.setFont(labelFont1);
        Font buttonFont = new Font("Arial", Font.BOLD, 25); // You can adjust the size (16 is just an example)
        saveButton.setFont(buttonFont);
        backButton.setFont(buttonFont);
        
        saveButton.addActionListener(e -> {
            String taskName = nameField.getText();
            Date taskDate = getSelectedDate(dayComboBox, monthComboBox, yearComboBox);
            String taskPriority = getSelectedPriority(highPriorityButton, mediumPriorityButton, lowPriorityButton);

            if (taskName.isEmpty() || taskDate == null || taskPriority.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Task task = new Task(taskName, taskDate, taskPriority);
            taskQueue.add(task);
            showMainMenu();
        });

        backButton.addActionListener(e -> showMainMenu());

        mainPanel.add(nameLabel);
        mainPanel.add(nameField);
        mainPanel.add(dateLabel);
        mainPanel.add(datePanel);
        mainPanel.add(priorityLabel);
        mainPanel.add(priorityPanel);
        mainPanel.add(saveButton);
        mainPanel.add(backButton);

        mainPanel.revalidate();
        mainPanel.repaint();
    }
    //this method generates an array of strings representing 
    //the days of the month from 1 to 31.
    private String[] getDays() {
        String[] days = new String[31];
        for (int i = 1; i <= 31; i++) {
            days[i - 1] = String.valueOf(i);
        }
        return days;
    }
   //this method simplifies the process of getting an array representing the months of the year.
    private String[] getMonths() {
        return new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    }
  // this method generates an array of strings representing a range of 100 years. 
    private String[] getYears() {
        String[] years = new String[100];
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 0; i < 100; i++) {
            years[i] = String.valueOf(currentYear + i);
        }
        return years;
    }
 //this method attempts to construct a Date object from the selected day, month, and year values
    private Date getSelectedDate(JComboBox<String> dayComboBox, JComboBox<String> monthComboBox, JComboBox<String> yearComboBox) {
        try {
            String day = (String) dayComboBox.getSelectedItem();
            String month = String.valueOf(monthComboBox.getSelectedIndex() + 1);
            String year = (String) yearComboBox.getSelectedItem();

            String dateString = String.format("%s/%s/%s", month, day, year);
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            return sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
//this method determines the selected priority level based
   //on the state of three radio buttons (high, medium, and low)
    private String getSelectedPriority(JRadioButton high, JRadioButton medium, JRadioButton low) {
        if (high.isSelected()) {
            return "High";
        } else if (medium.isSelected()) {
            return "Medium";
        } else if (low.isSelected()) {
            return "Low";
        }
        return "";
    }
//this method sets up a display for the tasks, showing each task's name, priority, 
 //and date in a JTextArea. The "Back" button is added at the bottom, allowing 
    //the user to return to the main menu
    private void showTasks() {
        mainPanel.removeAll();
        mainPanel.setLayout(new BorderLayout());

        JTextArea tasksTextArea = new JTextArea();
        tasksTextArea.setEditable(false); 

        for (Task task : taskQueue) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            tasksTextArea.append(task.name + " - Priority: " + task.priority + " - Date: " + dateFormat.format(task.date) + "\n");
        }

        JButton backButton = new JButton("Back");
        backButton.setBackground(Color.WHITE);
        Font buttonFont = new Font("Arial", Font.BOLD, 25); // You can adjust the size (16 is just an example)
        backButton.setFont(buttonFont);
        
        // Set font size and style for the tasksTextArea
        Font textAreaFont = new Font("Consolas", Font.PLAIN, 24);
        tasksTextArea.setFont(textAreaFont);

        backButton.addActionListener(e -> showMainMenu());
        

        mainPanel.add(new JScrollPane(tasksTextArea), BorderLayout.CENTER);
        mainPanel.add(backButton, BorderLayout.SOUTH);

        mainPanel.revalidate();
        mainPanel.repaint();
    }
//these methods work together to prompt the user to enter the name of a task to be deleted.
//The deleteTask method attempts to remove the task with the provided name from the taskQueue
    private void showDeleteTaskDialog() {
        String taskName = JOptionPane.showInputDialog(frame, "Enter Task Name to Delete:");
        if (taskName != null) {
            deleteTask(taskName);
        }
    }
// this method removes the task with the provided name from the taskQueue
 private void deleteTask(String taskName) {
    boolean found = taskQueue.removeIf(task -> task.name.equals(taskName));
    if (found) {
        JOptionPane.showMessageDialog(frame, "Task deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
    } else {
        JOptionPane.showMessageDialog(frame, "Task not found", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

}

