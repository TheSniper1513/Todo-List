package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MainFrame extends JFrame {
    private JPanel taskPanelContainer;
    private JTextField taskInputField; // Text field for entering tasks

    public MainFrame() {
        setTitle("To-Do List");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false); 

        // Task list container with scrolling
        taskPanelContainer = new JPanel();
        taskPanelContainer.setLayout(new BoxLayout(taskPanelContainer, BoxLayout.Y_AXIS));
        taskPanelContainer.setBackground(new Color(240, 240, 240));

        JScrollPane scrollPane = new JScrollPane(taskPanelContainer);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(scrollPane, BorderLayout.CENTER);

        // Input field and button panel
        JPanel inputPanel = new JPanel(new BorderLayout());
        taskInputField = new JTextField();
        JButton addTaskButton = new JButton("Add Task");

        inputPanel.add(taskInputField, BorderLayout.CENTER);
        inputPanel.add(addTaskButton, BorderLayout.EAST);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(inputPanel, BorderLayout.NORTH);

        // Add task button action
        addTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String taskText = taskInputField.getText().trim();
                if (!taskText.isEmpty()) {
                    addTaskPanel(taskText);
                    taskInputField.setText(""); // Clear input field
                }
            }
        });

        setVisible(true);
    }

    private void addTaskPanel(String taskName) {
        JPanel taskPanel = new JPanel(new BorderLayout());
        taskPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        taskPanel.setPreferredSize(new Dimension(350, 50));
        taskPanel.setBackground(Color.WHITE);
        taskPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        JCheckBox completeCheckBox = new JCheckBox();
        JLabel taskLabel = new JLabel(taskName);
        JButton removeButton = new JButton("X");

        // Make task label more readable
        taskLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        taskLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));

        // Customize remove button
        removeButton.setForeground(Color.RED);
        removeButton.setFont(new Font("Arial", Font.BOLD, 12));

        // Add components to task panel
        taskPanel.add(completeCheckBox, BorderLayout.WEST);
        taskPanel.add(taskLabel, BorderLayout.CENTER);
        taskPanel.add(removeButton, BorderLayout.EAST);

        // Checkbox action (strike-through)
        completeCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (completeCheckBox.isSelected()) {
                    taskLabel.setText("<html><strike>" + taskName + "</strike></html>");
                } else {
                    taskLabel.setText(taskName);
                }
            }
        });

        // Remove task action
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taskPanelContainer.remove(taskPanel);
                taskPanelContainer.revalidate();
                taskPanelContainer.repaint();
            }
        });

        taskPanelContainer.add(taskPanel);
        taskPanelContainer.revalidate();
        taskPanelContainer.repaint();
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
