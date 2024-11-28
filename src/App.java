import javax.swing.*;
import java.awt.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class App extends JFrame {
    private LocalDate currentMonday;
    JPanel panel;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public App() {

        setTitle("LSF");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLayout(new FlowLayout());

         currentMonday = LocalDate.now().with(DayOfWeek.MONDAY);

        panel = new JPanel();
        panel.setLayout(new GridLayout(1, 5, 10, 10));
        updateWeek();

        JButton prevWeek = new JButton("←");
        prevWeek.addActionListener(e -> {
            currentMonday = currentMonday.minusWeeks(1);
            updateWeek();
        });


        JButton nextWeek = new JButton("→");
        nextWeek.addActionListener(e -> {
            currentMonday = currentMonday.plusWeeks(1);
            updateWeek();
        });

        JPanel clockPanel = new JPanel();
        clockPanel.setLayout(new GridLayout(15, 1, 10, 10));
        JLabel clockLabel = new JLabel();
        for(int i = 7; i <= 19; i++) {
            if(i == 7) {
                clockPanel.add(new JLabel("Zeit"));
                clockPanel.add(new JLabel("vor 8"));
                i++;

            }
            clockPanel.add(new JLabel(String.valueOf(i)));

        }
        clockLabel.setText("ab 20");
        clockPanel.add(clockLabel);
        add(clockPanel);

        add(prevWeek, BorderLayout.WEST);
        add(panel, BorderLayout.CENTER);
        JScrollPane scrollPane = new JScrollPane(panel);
        add(scrollPane, BorderLayout.CENTER);
        add(nextWeek, BorderLayout.EAST);

    }

    private void updateWeek() {
        panel.removeAll();

        String[] tage = {"Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag"};

        for (int i = 0; i < tage.length; i++) {
            LocalDate date = currentMonday.plusDays(i);
            JLabel label = new JLabel(tage[i] + ", " + date.format(formatter));
            label.setHorizontalAlignment(SwingConstants.CENTER);
            panel.add(label);
        }
        panel.revalidate();
        panel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            App app = new App();
            app.setVisible(true);
        });
    }
}