import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class Mechanic extends javax.swing.JFrame {
    private JPanel panel1;
    private JTable table1;

    public static void main(String[] args) {

        try {
            // database connection string
            // MySQL jdbc:mysql://{HOST}[:{PORT}][/{DB}]
            String url = "jdbc:mysql://smcse-stuproj00.city.ac.uk/adbt079";
            // username and password
            Connection conn = DriverManager.getConnection(url, "adbt079", "200006212");
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


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}