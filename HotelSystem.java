import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

class Reservation {
    String name;
    int room, days;
    double bill;

    Reservation(String name, int room, int days) {
        this.name = name;
        this.room = room;
        this.days = days;
        this.bill = days * 1500;
    }

    public String toString() {
        return name + " | Room " + room + " | ₹" + bill;
    }
}

public class HotelSystem {

    static ArrayList<Reservation> list = new ArrayList<>();
    static boolean rooms[] = new boolean[10];

    public static void main(String[] args) {

        JFrame frame = new JFrame("Hotel Booking App");
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Gradient Panel
        JPanel mainPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(72, 61, 139),
                        0, getHeight(), new Color(123, 104, 238));
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        mainPanel.setLayout(new BorderLayout());
        frame.setContentPane(mainPanel);

        // Left Panel (Title)
        JPanel left = new JPanel();
        left.setOpaque(false);
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Hotel Booking App");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 28));

        JLabel subtitle = new JLabel("Easy room reservation");
        subtitle.setForeground(Color.LIGHT_GRAY);

        left.add(Box.createVerticalStrut(100));
        left.add(title);
        left.add(subtitle);

        mainPanel.add(left, BorderLayout.WEST);

        // Right Panel (Cards)
        JPanel right = new JPanel();
        right.setOpaque(false);
        right.setLayout(new GridLayout(2, 2, 20, 20));
        right.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        right.add(createCard("View Rooms", e -> viewRooms()));
        right.add(createCard("Book Room", e -> bookRoom()));
        right.add(createCard("Reservations", e -> viewReservations()));
        right.add(createCard("Exit", e -> System.exit(0)));

        mainPanel.add(right, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    static JPanel createCard(String text, ActionListener action) {
        JPanel card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setLayout(new BorderLayout());
        card.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel label = new JLabel(text, JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 16));

        JButton btn = new JButton("Open");
        btn.addActionListener(action);

        card.add(label, BorderLayout.CENTER);
        card.add(btn, BorderLayout.SOUTH);

        return card;
    }

    static void viewRooms() {
        StringBuilder sb = new StringBuilder("Available Rooms:\n");
        for (int i = 0; i < rooms.length; i++) {
            if (!rooms[i]) sb.append("Room ").append(i + 1).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    static void bookRoom() {
        String name = JOptionPane.showInputDialog("Name:");
        int room = Integer.parseInt(JOptionPane.showInputDialog("Room (1-10):"));
        int days = Integer.parseInt(JOptionPane.showInputDialog("Days:"));

        if (rooms[room - 1]) {
            JOptionPane.showMessageDialog(null, "Already booked!");
            return;
        }

        rooms[room - 1] = true;
        Reservation r = new Reservation(name, room, days);
        list.add(r);

        JOptionPane.showMessageDialog(null, "Booked! Bill ₹" + r.bill);
    }

    static void viewReservations() {
        if (list.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No reservations");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (Reservation r : list) {
            sb.append(r).append("\n");
        }

        JOptionPane.showMessageDialog(null, sb.toString());
    }
}