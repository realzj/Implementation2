import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Recepcionist extends JDialog {
    private JPanel ReceptionistPanel;
    private JLabel NameLabel;
    private JLabel DescriptionLabel;
    private JLabel StatusLabel;
    private JLabel PCostLabel;
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
    private JButton printReportButton;
    private JTextField DescriptionText;
    private JTable table1;
    private JTextField NPlateText;
    private JLabel titleLabel;
    private JLabel descriptionLabel;
    private JLabel statusLabel;
    private JLabel predictedCostLabel;
    private JLabel predictedDurationLabel;
    private JLabel actualCostLabel;
    private JLabel actualDurationLabel;
    private JLabel numberPlateLabel;
    private JLabel NPlateLabel;
    private JLabel ADurationLabel;
    private JLabel ACostLabel;
    private JLabel PDurationLabel;


    public Recepcionist(JFrame parent) {
        super(parent);
        setTitle("Receptionist");
        setContentPane(ReceptionistPanel);
        setMinimumSize(new Dimension(500, 600));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //setVisible(true);


        addEntryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String SQL = "INSERT INTO team16Jobs(title, description, status, predictedCost, predictedDuration, actualCost, actualDuration, numberPlate) VALUES (?,?,?,?,?,?,?,?)";

                try {
                    String url = "jdbc:mysql://smcse-stuproj00.city.ac.uk/adbt079";
                    Connection conn = DriverManager.getConnection(url, "adbt079", "200006212");

                    PreparedStatement statement = conn.prepareStatement(SQL);
                    statement.setString(1,TitleText.getText());
                    statement.setString(2,DescriptionText.getText());
                    statement.setString(3,StatusText.getText());
                    statement.setString(4,PCostText.getText());
                    statement.setString(5,PDurationText.getText());
                    statement.setString(6,ACostText.getText());
                    statement.setString(7,ADurationText.getText());
                    statement.setString(8,NPlateText.getText());
                    statement.execute();
                    System.out.println("Done");

                }
                catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,ex);
                }
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
};
