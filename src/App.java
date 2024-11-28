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

        JTable stundenplan = new JTable();
        stundenplan.setLayout(new GridLayout(14, 5));

        setTitle("LSF");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLayout(new FlowLayout());

         currentMonday = LocalDate.now().with(DayOfWeek.MONDAY);

        panel = new JPanel();
        panel.setLayout(new GridLayout(1, 6, 10, 10));
        JPanel zeit = new JPanel();
        zeit.setLayout(new GridLayout(15, 1, 10, 10));


        for(int i = 7; i <= 19; i++) {
            while(i == 7) {
                zeit.add(new JLabel("Zeit"));
                zeit.add(new JLabel("vor 8"));
                i++;
            }
            zeit.add(new JLabel("" + i));
        }
        zeit.add(new JLabel("ab 20"));

        JButton prevWeek = new JButton("←");
        prevWeek.addActionListener(_ -> {
            currentMonday = currentMonday.minusWeeks(1);
            updateWeek();
        });


        JButton nextWeek = new JButton("→");
        nextWeek.addActionListener(_ -> {
            currentMonday = currentMonday.plusWeeks(1);
            updateWeek();
        });

        add(prevWeek, BorderLayout.WEST);
        add(panel, BorderLayout.CENTER);
        add(zeit, BorderLayout.WEST);
        JScrollPane scrollPane = new JScrollPane(panel);
        add(scrollPane, BorderLayout.CENTER);
        add(nextWeek, BorderLayout.EAST);
        add(stundenplan, BorderLayout.SOUTH);
        updateWeek();

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