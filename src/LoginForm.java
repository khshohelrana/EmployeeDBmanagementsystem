import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginForm extends javax.swing.JFrame{
    private JTextField usernametextField1;
    private JPasswordField passwordField1;
    private JButton loginButton;
    private JButton registerButton;
    private JPanel loginpanel;
    private JButton exitButton;

    public LoginForm(JFrame parent){

        super(String.valueOf(parent));
        setTitle("Login");
        setContentPane(loginpanel);
        setMinimumSize(new Dimension(450,474));
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username=usernametextField1.getText();
                String password= String.valueOf(passwordField1.getPassword());

                user = getAuthenticatedUser(username, password);

                if (user != null) {
                    dispose();
                    MainMenu mainmenu=new MainMenu(null);
                }
                else {
                    JOptionPane.showMessageDialog(LoginForm.this,
                            "Username or Password Invalid",
                            "Try again",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                RegisterForm registerform=new RegisterForm(null);
                
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
    private User getAuthenticatedUser(String username, String password) {
        User user = null;

        final String DB_URL = "jdbc:mysql://localhost/project?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try{
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM loging_register WHERE username=? AND password=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.username = resultSet.getString("username");
                user.password = resultSet.getString("password");
            }

            stmt.close();
            conn.close();

        }catch(Exception e){
            e.printStackTrace();
        }


        return user;
    }

    public static void main(String[] args) {

        LoginForm loginForm=new LoginForm(null );


    }
}
