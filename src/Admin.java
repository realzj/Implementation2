import javax.swing.*;
import java.awt.*;

public class Admin extends JDialog {

    private JPanel AdminPanel;
    private JTextField nameField;
    private JTextField SurnameField2;
    private JTextField DoBField3;
    private JTextField EmailField4;
    private JLabel NameLabel;
    private JLabel SurnameLabel;
    private JLabel doBLabel;
    private JLabel emailLabel;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JTextArea textArea1;

    public Admin(JFrame parent) {
        super(parent);
        setTitle("Receptionist");
        setContentPane(AdminPanel);
        setMinimumSize(new Dimension(500, 600));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //setVisible(true);

    }
}