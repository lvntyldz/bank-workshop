import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Form1 {
    private JButton button1;
    private JPanel mainPanel;
    private JTextField searchTxt;
    private JList resultList;

    public Form1() {

        DefaultListModel<String> listModel = new DefaultListModel<>();
        listModel.addElement("USA");
        listModel.addElement("India");
        listModel.addElement("Vietnam");
        listModel.addElement("Canada");
        listModel.addElement("Denmark");
        listModel.addElement("France");
        listModel.addElement("Great Britain");
        listModel.addElement("Japan");
        resultList.setModel(listModel);

        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String searchData = searchTxt.getText();
                JOptionPane.showMessageDialog(null, "Button Clicked : " + searchData);
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
