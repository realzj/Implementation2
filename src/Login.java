import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JDialog {
    private JPanel LoginPanel;
    private JTextField UserText;
    private JPasswordField PassText;
    private JButton logInButton;
    private JButton exitButton;
    private JPanel JPanel;
    private JComboBox comboBox1;
    private JPanel panel1;
    Connectivity connectivity;


    public Login(JFrame parent) {
        super(parent);
        setTitle("Login");
        setContentPane(LoginPanel);
        setMinimumSize(new Dimension(500, 600));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = UserText.getText();
                String password = String.valueOf(PassText.getPassword());
                String role = comboBox1.getSelectedItem().toString();

                user = getAuthenticatedUser(username, password, role);

                if (user != null) {
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(Login.this, "Username or Password is invalid",
                            "Try Again",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }

    public User user;

    private User getAuthenticatedUser(String username, String password, String role) {
        User user = null;

        try {
            // database connection string
            // MySQL jdbc:mysql://{HOST}[:{PORT}][/{DB}]
            String url = "jdbc:mysql://smcse-stuproj00.city.ac.uk/adbt079";
            // username and password
            Connection conn = DriverManager.getConnection(url, "adbt079", "200006212");
            System.out.println("Connected");

            Statement statement = conn.createStatement();
            String sql = "SELECT * FROM team16Logins WHERE login=? AND password=? AND role =?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, role.valueOf(comboBox1.getSelectedItem()));

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String s1 = resultSet.getString("role");
                if (role.equalsIgnoreCase("Administrator")) {
                    Admin admin = new Admin(null);
                    admin.setVisible(true);

                } else if (role.equalsIgnoreCase("Receptionist")) {
                    Recepcionist recepcionist = new Recepcionist(null);
                    recepcionist.setVisible(true);
                    setVisible(false);
                } else if (role.equalsIgnoreCase("Mechanic")) {
                    try {
                        conn = DriverManager.getConnection(url, "adbt079", "200006212");
                        System.out.println("Connected");

                        String query = "SELECT * from team16Jobs";

                        Statement stm = conn.createStatement();
                        ResultSet res = stm.executeQuery(query);

                        String columns[] = {"jobsID", "title", "description", "status", "predictedCost", "predictedDuration", "actualCost", "actualDuration", "numberPlate"};
                        String data[][] = new String[50][3];

                        int i = 0;
                        while (res.next()) {
                            int jobsID = res.getInt("jobsID");
                            String title = res.getString("title");
                            String description = res.getString("description");
                            String status = res.getString("status");
                            String predictedCost = res.getString("predictedCost");
                            String predictedDuration = res.getString("predictedDuration");
                            String actualCost = res.getString("actualCost");
                            String actualDuration = res.getString("actualDuration");
                            String numberPlate = res.getString("numberPlate");
                            data[i][0] = String.valueOf(jobsID);
                            data[i][1] = title;
                            data[i][2] = description;
                            data[i][3] = status;
                            data[i][4] = predictedCost;
                            data[i][5] = predictedDuration;
                            data[i][6] = actualCost;
                            data[i][7] = actualDuration;
                            data[i][8] = numberPlate;
                            i++;
                        }

                        DefaultTableModel model = new DefaultTableModel(data, columns);
                        JTable table = new JTable(model);
                        table.setShowGrid(true);
                        table.setShowVerticalLines(true);
                        JScrollPane pane = new JScrollPane(table);
                        JFrame f = new JFrame("Populate JTable from Database");
                        JPanel panel = new JPanel();
                        panel.add(pane);
                        f.add(panel);
                        f.setSize(500, 250);
                        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        f.setVisible(true);
                        setVisible(false);


                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }

            statement.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return user;
    }

    public static void main(String[] args) {
        Login login = new Login(null);
    }
};