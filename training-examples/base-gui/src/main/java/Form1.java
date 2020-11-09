import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Form1 {
    private JButton button1;
    private JPanel mainPanel;

    public Form1() {
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Button Clicked");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Form1");
        frame.setContentPane(new Form1().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(600, 400));
        frame.pack();
        frame.setVisible(true);
    }
}
