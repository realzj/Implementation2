import javax.sql.StatementEvent;
import javax.swing.*;
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
    private JPanel panel1;

    public Login(JFrame parent){
        super(parent);
        setTitle("Login");
        setContentPane(LoginPanel);
        setMinimumSize(new Dimension(500,600));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = UserText.getText();
                String password = String.valueOf(PassText.getPassword());

                user = getAuthenticatedUser(username,password);
            }
        });
        setVisible(true);
    }
    public User user;

    private User getAuthenticatedUser(String username, String password){
        User user = null;

        String DB_URL = "jdbc:mysql://smcse.city.ac.uk/phpmyadmin/db_sql.php?db=adbt079";
        String USERNAME = "adbt079";
        String PASSWORD = "200006212";

        try{

            //Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);

            Statement statement = conn.createStatement();
            String sql = "SELECT * FROM team16Logins WHERE username=? AND password=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                System.out.println("Yes");
                //setVisible(true);
            }
            statement.close();
            conn.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return user;
    }
    public static void main(String[] args) {
        Login login = new Login(null);
        User user = login.user;
        if (user != null){
            System.out.println("Succesfull");
        }else{
            System.out.println("No");
        }
    }
}
