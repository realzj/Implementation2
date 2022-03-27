import javax.swing.*;
import java.awt.*;

public class Recepcionist extends JDialog {
    private JPanel ReceptionistPanel;
    private JLabel TitleLabel;
    private JLabel DescriptionLabel;
    private JLabel StatusLabel;
    private JLabel PCostLabel;
    private JLabel PDurationLabel;
    private JLabel ACostLabel;
    private JLabel ADurationLabel;
    private JTextField TitleText;
    private JTextField StatusText;
    private JTextField PCostText;
    private JTextField PDurationText;
    private JTextField ACostText;
    private JTextField ADurationText;
    private JButton viewAllButton;
    private JButton addEntryButton;
    private JButton deleteEntryButton;
    private JButton searchButton;
    private JButton updateButton;
    private JButton printInvoiceButton;
    private JTextField DescriptionText;


    public Recepcionist(JFrame parent) {
        super(parent);
        setTitle("Receptionist");
        setContentPane(ReceptionistPanel);
        setMinimumSize(new Dimension(500, 600));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        }
    };
