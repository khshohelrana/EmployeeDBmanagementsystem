import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class RegisterForm extends javax.swing.JFrame{
    private JTextField nametextField1;
    private JTextField usernametextField1;
    private JPasswordField passwordField1;
    private JButton registerButton;
    private JButton backButton;
    private JButton cancelButton;
    private JPasswordField passwordField2;
    private JPanel RegisterPanel;

     public  RegisterForm(JFrame parent){

         super(String.valueOf(parent));
         setTitle("Create an account");
         setContentPane(RegisterPanel);
         setMinimumSize(new Dimension(450,474));
         setLocationRelativeTo(parent);
         setDefaultCloseOperation(DISPOSE_ON_CLOSE);

         registerButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 registerUser();
             }
         });
         cancelButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                   dispose();
             }
         });
         backButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 dispose();
                 JFrame parent = null;
                 LoginForm loginForm=new LoginForm(parent);
             }
         });

         setVisible(true);

     }

    private void registerUser() {

         String name=nametextField1.getText();
         String username=usernametextField1.getText();
         String password= String.valueOf(passwordField1.getPassword());
         String confrirm= String.valueOf(passwordField2.getPassword());

        if (name.isEmpty() || username.isEmpty() || password.isEmpty()||confrirm.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter all Fields",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!password.equals(confrirm)) {
            JOptionPane.showMessageDialog(this,
                    "Confirm Password does not match",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        user = addUserToDatabase(name, username, password, confrirm);
        if (user != null) {
            nametextField1.setText("");
            usernametextField1.setText("");
            passwordField1.setText("");
            passwordField2.setText("");
            JOptionPane.showMessageDialog(this,
                    "Successful Registration",
                    "Go ahead",
                    JOptionPane.ERROR_MESSAGE);
        }
        else {
            JOptionPane.showMessageDialog(this,
                    "Failed to register",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public User user;
    private User addUserToDatabase(String name, String username, String password, String confirm) {
        User user = null;
        final String DB_URL = "jdbc:mysql://localhost/project?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try{
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            String sql = ("INSERT INTO loging_register (name, username, password, confirm) " +
                    "VALUES ( ?, ?, ?, ?)");
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, confirm);


            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0) {
                user = new User();
                user.name = name;
                user.username = username;
                user.password = password;
                user.confirm = confirm;


            }

            stmt.close();
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }


        return user;
    }

    public static void main(String[] args) {

        RegisterForm registerform=new RegisterForm(null);


    }
}
