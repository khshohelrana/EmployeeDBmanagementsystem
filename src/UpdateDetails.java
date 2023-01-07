import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class UpdateDetails extends javax.swing.JFrame{
    private JTextField SearchbyIDtextField1;
    private JTextField EmployeeIDtextField1;
    private JTextField NametextField1;
    private JTextField EmailtextField1;
    private JTextField PhoneNotextField1;
    private JTextField DeptNametextField1;
    private JTextField SalarytextField1;
    private JTextField DistricttextField1;
    private JTextField BloodGrouptextField1;
    private JButton backButton;
    private JButton updateDetailsButton;

    private JPanel UpdateDetailsPanel;
    private JButton searchByEmpIDButton;

    public UpdateDetails(JFrame parent) {
        super(String.valueOf(parent));
        setTitle("Update Employee's Record");
        setContentPane(UpdateDetailsPanel);
        setMinimumSize(new Dimension(450,474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Connect();
        searchByEmpIDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    pst = con.prepareStatement("select * from employee_details where employeeID=?");
                    int id = Integer.parseInt(SearchbyIDtextField1.getText());
                    pst.setInt(1, id);
                    ResultSet rs1 = pst.executeQuery();
                    if(rs1.next()==false)
                    {

                        EmployeeIDtextField1.setText("");
                        NametextField1.setText("");
                        EmailtextField1.setText("");
                        PhoneNotextField1.setText("");
                        DeptNametextField1.setText("");
                        SalarytextField1.setText("");
                        DistricttextField1.setText("");
                        BloodGrouptextField1.setText("") ;
                    }
                    else
                    {

                        EmployeeIDtextField1.setText(rs1.getString("employeeID"));
                        NametextField1.setText(rs1.getString("name"));
                        EmailtextField1.setText(rs1.getString("Email"));
                        PhoneNotextField1.setText(rs1.getString("PhoneNo"));
                        DeptNametextField1.setText(rs1.getString("Dept_name"));
                        SalarytextField1.setText(rs1.getString("Salary"));
                        DistricttextField1.setText(rs1.getString("District"));
                        BloodGrouptextField1.setText(rs1.getString("Blood_Group"));
                    }

                } catch (SQLException ex) {

                }
            }
        });


        
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 dispose();
                MainMenu mainmenu=new MainMenu(null);
            }
        });


        updateDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String employeeID= EmployeeIDtextField1.getText();
                String name=NametextField1.getText();
                String Email=EmailtextField1.getText();
                String PhoneNo= PhoneNotextField1.getText();
                String Dept_name= DeptNametextField1.getText();
                String Salary=SalarytextField1.getText();
                String District=DistricttextField1.getText();
                String Blood_Group= BloodGrouptextField1.getText();
                int id = Integer.parseInt(SearchbyIDtextField1.getText());
                try
                {
                    pst = con.prepareStatement("UPDATE employee_details SET name=?,Email=? ,PhoneNo=?, Dept_name=?,Salary=?,District=?,Blood_Group=? where employeeID =?");

                    pst.setString(1,name );
                    pst.setString(2,Email );
                    pst.setString(3,PhoneNo );
                    pst.setString(4,Dept_name );
                    pst.setString(5, Salary);
                    pst.setString(6,District );
                    pst.setString(7,Blood_Group );
                    pst.setString(8,employeeID);

                    pst.executeUpdate();

                        JOptionPane.showMessageDialog(null, "Record Updated");
                        EmployeeIDtextField1.setText("");
                        NametextField1.setText("");
                        EmailtextField1.setText("");
                        PhoneNotextField1.setText("");
                        DeptNametextField1.setText("");
                        SalarytextField1.setText("");
                        DistricttextField1.setText("");
                        BloodGrouptextField1.setText("");


                }
                catch (SQLException ex)
                {

                }
            }
        });

        setVisible(true);
    }

    private void setModal(boolean b) {
    }

    PreparedStatement pst;

    Connection con;
    public void Connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/project", "root", "");

        } catch (ClassNotFoundException ex) {

        } catch (SQLException ex) {

        }
    }


    public static void main(String[] args) {

        UpdateDetails updatedetails=new UpdateDetails(null );
    }
}
