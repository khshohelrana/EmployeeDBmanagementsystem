import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ShowSelectedDetails extends javax.swing.JFrame {
    private JPanel ShowSelectedDetails;
    private JButton Salarybutton1;
    private JButton districtButton;
    private JButton Blood_GroupButton;
    private JButton namebutton1;
    private JButton PhoneNobutton1;
    private JButton Dept_namebutton1;
    private JButton Emailbutton1;
    private JTable table1;
    private JButton Backbutton1;
    private JScrollPane tablejs;
    private JButton employeeIDButton;


                                      

    public ShowSelectedDetails(JFrame parent ) {

        super(String.valueOf(parent));
        setTitle("ShowSelectedDetails");
        setContentPane(ShowSelectedDetails);
        setMinimumSize(new Dimension(450,474));
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Connect();


        employeeIDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try
                {
                    pst = con.prepareStatement("select employeeID from employee_details");
                    ResultSet rs = pst.executeQuery();

                    table1.setModel(DbUtils.resultSetToTableModel(rs));
                }
                catch (SQLException ex)
                {
                    ex.printStackTrace();
                }
            }
        });


        namebutton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    pst = con.prepareStatement("select employeeID, name from employee_details");
                    ResultSet rs = pst.executeQuery();

                    table1.setModel(DbUtils.resultSetToTableModel(rs));
                }
                catch (SQLException ex)
                {
                    ex.printStackTrace();
                }
            }
        });

        Emailbutton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    pst = con.prepareStatement("select employeeID, name,Email from employee_details");
                    ResultSet rs = pst.executeQuery();

                    table1.setModel(DbUtils.resultSetToTableModel(rs));
                }
                catch (SQLException ex)
                {
                    ex.printStackTrace();
                }
            }
        });

        PhoneNobutton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    pst = con.prepareStatement("select employeeID, name,Email,PhoneNo from employee_details");
                    ResultSet rs = pst.executeQuery();

                    table1.setModel(DbUtils.resultSetToTableModel(rs));
                }
                catch (SQLException ex)
                {
                    ex.printStackTrace();
                }
            }
        });
        Dept_namebutton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    pst = con.prepareStatement("select employeeID, name,Email,PhoneNo, Dept_name from employee_details");
                    ResultSet rs = pst.executeQuery();

                    table1.setModel(DbUtils.resultSetToTableModel(rs));
                }
                catch (SQLException ex)
                {
                    ex.printStackTrace();
                }
            }
        });

        Salarybutton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    pst = con.prepareStatement("select employeeID, name,Email,PhoneNo, Dept_name,Salary from employee_details");
                    ResultSet rs = pst.executeQuery();

                    table1.setModel(DbUtils.resultSetToTableModel(rs));
                }
                catch (SQLException ex)
                {
                    ex.printStackTrace();
                }
            }
        });

        districtButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    pst = con.prepareStatement("select employeeID, name,Email,PhoneNo, Dept_name,Salary, District from employee_details");
                    ResultSet rs = pst.executeQuery();

                    table1.setModel(DbUtils.resultSetToTableModel(rs));
                }
                catch (SQLException ex)
                {
                    ex.printStackTrace();
                }
            }
        });

        Blood_GroupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    pst = con.prepareStatement("select employeeID, name,Email,PhoneNo, Dept_name,Salary, District, Blood_Group from employee_details");
                    ResultSet rs = pst.executeQuery();

                    table1.setModel(DbUtils.resultSetToTableModel(rs));
                }
                catch (SQLException ex)
                {
                    ex.printStackTrace();
                }
            }
        });

        Backbutton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    dispose();
                MainMenu mainmenu=new MainMenu(null);
            }
        });

        setVisible(true);
    }



    PreparedStatement pst;

    Connection con;
    public void Connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/project", "root", "");

        } catch (ClassNotFoundException ex) {

            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {

         ShowSelectedDetails updatedetails=new ShowSelectedDetails( null);

    }
}
