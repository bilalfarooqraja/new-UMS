import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Teacher {
    public void CreateTable(Connection conn){
        Statement statement;
        try{
            String query="create table Teacher"+"(empid SERIAL,name varchar(200),address varchar(200),Password varchar(200),primary key(empid));";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Query Executed");
        }catch (Exception e){
            System.out.println("Proble in creating teacher");
            System.out.println(e);
        }
    }

    public void InsertRows(Connection conn,String name,String address,String password){
        Statement statement;
        try {
            String query=String.format("Insert into %s(name,address,password) values('%s','%s','%s');","Teacher",name,address,password);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row Inserted");
        }catch (Exception e){
            System.out.println(e);
        }
    }
    //Input Student marks
    public void InsertStudentMarks(Connection conn,String marks,String Student_id,String Subject_id,String teacher_id){
        Statement statement;
        try {
            String query=String.format("Insert into %s(marks,subject_id,student_id,teacher_id) values('%s','%s','%s','%s');","marks",marks,Student_id,Subject_id,teacher_id);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row Inserted");
        }catch (Exception e){
            System.out.println(e);
        }
    }
    // Read Data
    public void readData(Connection conn){
        Statement statement;
        ResultSet rs=null;
        try {
            String query="select name,department,subject_name,marks,teacher_name\n" +
                    "from student\n" +
                    "inner join marks\n" +
                    "on marks.student_id = student.student_id\n" +
                    "inner join teacher\n" +
                    "on teacher.teacher_id = marks.teacher_id\n" +
                    "inner join subject\n" +
                    "on subject.subject_id= marks.subject_id\n" +
                    "inner join department\n" +
                    "on department.department_id = student.department_id;";
            statement = conn.createStatement();
            rs=statement.executeQuery(query);
            while (rs.next()){
                System.out.print("Student Name: "+rs.getString("name")+" ");
                System.out.print("Department: "+rs.getString("department")+" ");
                System.out.println("Subject Name: "+rs.getString("subject_name")+" ");
                System.out.println("Marks: "+rs.getString("marks")+" ");
                System.out.println("Teacher Name: "+rs.getString("teacher_name")+" ");
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public String[] searchByName(Connection conn, String name){
        Statement statement;
        ResultSet rs;
        try {
            String query="select * From "+"teacher"+ " Where teacher_name ='"+name+"'";
            statement = conn.createStatement();
            rs=statement.executeQuery(query);

            while (rs.next()) {
                String id = rs.getString("teacher_id");
                String Teachername = rs.getString("teacher_name");
                String Pass = rs.getString("teacher_password");
                return new String[]{Teachername, Pass, id};
            }

        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public String subjectName(Connection conn, String teacher_id){
        Statement statement;
        ResultSet rs;
        try {
            String query="select subject_name \n" +
                    "from subject\n" +
                    "inner join marks\n" +
                    "on marks.subject_id = subject.subject_id\n" +
                    "where teacher_id = '"+teacher_id+"'\n";
            statement = conn.createStatement();
            rs=statement.executeQuery(query);

            String subName;
            while (rs.next()) {
                String subject_name = rs.getString("subject_name");

                return subject_name;
            }

        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }


}
