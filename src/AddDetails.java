import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class AddDetails extends javax.swing.JFrame{
    private JTextField EmployeeIdtextField1;
    private JTextField NametextField1;
    private JTextField EmailtextField1;
    private JTextField PhoneNotextField1;
    private JTextField DeptNametextField1;
    private JTextField SalarytextField1;
    private JTextField DistricttextField1;
    private JTextField BloodGrouptextField1;
    private JButton adddetailsbutton1;
    private JButton backButton;
    private JPanel AddDetailsPanel;

    public AddDetails(JFrame parent) {
        super(String.valueOf(parent));
        setTitle("Save Employee's Record");
        setContentPane(AddDetailsPanel);
        setMinimumSize(new Dimension(450,474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        adddetailsbutton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                      addDetails();
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MainMenu mainmenu=new MainMenu(null);
            }
        });
        setVisible(true);
    }

    private void setModal(boolean b) {
    }

    private void addDetails() {

        String employeeID= EmployeeIdtextField1.getText();
        String name=NametextField1.getText();
        String Email=EmailtextField1.getText();
        String PhoneNo= PhoneNotextField1.getText();
        String Dept_name= DeptNametextField1.getText();
        String Salary=SalarytextField1.getText();
        String District=DistricttextField1.getText();
        String Blood_Group= BloodGrouptextField1.getText();


        if (employeeID.isEmpty()||name.isEmpty()  || Email.isEmpty()||PhoneNo.isEmpty()||Dept_name.isEmpty()
                ||Salary.isEmpty()||District.isEmpty()||Blood_Group.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter all Fields",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }


        user = addUserToDatabase(employeeID,name,  Email, PhoneNo, Dept_name,Salary,District,Blood_Group);
        if (user != null) {
            EmployeeIdtextField1.setText("");
            NametextField1.setText("");
            EmailtextField1.setText("");
            PhoneNotextField1.setText("");
            DeptNametextField1.setText("");
            SalarytextField1.setText("");
            DistricttextField1.setText("");
            BloodGrouptextField1.setText("");
        }
        else {
            JOptionPane.showMessageDialog(this,
                    "Failed to Data insert",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public User user;
    private User addUserToDatabase(String employeeID,String name,  String Email, String PhoneNo,
                                   String Dept_name, String Salary, String District, String Blood_Group ) {
        User user = null;
        final String DB_URL = "jdbc:mysql://localhost/project?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try{
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            Statement stmt = conn.createStatement();
            String sql = ("INSERT INTO employee_details ( employeeID,name, Email, PhoneNo,Dept_name,Salary," +
                    "District,Blood_Group) " +
                    "VALUES ( ?, ?, ?, ?,?,?,?,?)");
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, employeeID);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, Email);
            preparedStatement.setString(4, PhoneNo);
            preparedStatement.setString(5, Dept_name);
            preparedStatement.setString(6, Salary);
            preparedStatement.setString(7, District);
            preparedStatement.setString(8, Blood_Group);


            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0) {
                user = new User();
                user.employeeID = employeeID;
                user.name = name;
                user.Email = Email;
                user.PhoneNo = PhoneNo;
                user.Dept_name = Dept_name;
                user.Salary = Salary;
                user.District = District;
                user.Blood_Group = Blood_Group;


            }

            stmt.close();
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }


        return user;
    }

    public static void main(String[] args) {

        AddDetails adddetails=new AddDetails(null);
    }
}
