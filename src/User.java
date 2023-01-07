public class User {
    public String name;
    public  String username;
    public  String password;
    public String confirm;
    public  String employeeID;
    public  String Email;
    public  String  PhoneNo;
    public  String Dept_name;
    public  String Salary;
    public  String District;
    public String Blood_Group;







    public User(String employeeID,String name,String Email,String PhoneNo,String Dept_name,String Salary,String District,String Blood_Group)
    {


        this.employeeID = employeeID;
        this.name = name;
        this.Email = Email;
        this.PhoneNo = PhoneNo;
        this.Dept_name = Dept_name;
        this.Salary = Salary;
        this.District = District;
        this.Blood_Group = Blood_Group;
    }

    public User() {

    }
public String getemployeeID() {
    return employeeID;
}
    public Object getname() {
        return  name;
    }


    public String getEmail()
    {
        return Email;
    }

    public String getPhoneNo()
    {
        return PhoneNo;
    }



    public Object getDept_name() {
        return Dept_name;
    }

    public Object getSalary() {
        return Salary;
    }



    public Object getDistrict() {
        return  District;
    }

    public Object getBlood_Group() {
        return  Blood_Group;
    }
}




