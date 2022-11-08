import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Student {

    public void CreateStudentTable(Connection conn) {
        Statement statement;
        try {
            String query = "create table " + "Student" + "(empid SERIAL,name varchar(200),ITCP varchar(200),Calculus varchar(200),OOP varchar(200), DataBase varchar(200),Maths varchar(200),primary key(empid));";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Query Executed");
        } catch (Exception e) {
            System.out.println("Masla in Student");
            System.out.println(e);
        }
    }

    public void searchByName(Connection conn, String name) {
        Statement statement;
        ResultSet rs = null;
        try {
            String query = "select name,department,subject_name,marks,teacher_name\n" +
                    "from student\n" +
                    "inner join marks\n" +
                    "on marks.student_id = student.student_id\n" +
                    "inner join teacher\n" +
                    "on teacher.teacher_id = marks.teacher_id\n" +
                    "inner join subject\n" +
                    "on subject.subject_id= marks.subject_id\n" +
                    "inner join department\n" +
                    "on department.department_id = student.department_id\n" +
                    "WHERE name = '"+name+"';";
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                System.out.print("Student Name: "+rs.getString("name") + " ");
                System.out.print("Department: "+rs.getString("department") + " ");
                System.out.println("Subject Name: "+rs.getString("subject_name") + " ");
                System.out.println("Marks: "+rs.getString("marks") + " ");
                System.out.println("Teacher Name: "+rs.getString("teacher_name") + " ");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void searchById(Connection conn, String studentId) {
        Statement statement;
        ResultSet rs = null;
        try {
            String query = "select name,department,subject_name,marks,teacher_name\n" +
                    "from student\n" +
                    "inner join marks\n" +
                    "on marks.student_id = student.student_id\n" +
                    "inner join teacher\n" +
                    "on teacher.teacher_id = marks.teacher_id\n" +
                    "inner join subject\n" +
                    "on subject.subject_id= marks.subject_id\n" +
                    "inner join department\n" +
                    "on department.department_id = student.department_id\n" +
                    "WHERE student.student_id = '"+studentId+"';";
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                System.out.print("Student Name: "+rs.getString("name") + " ");
                System.out.print("Department: "+rs.getString("department") + " ");
                System.out.println("Subject Name: "+rs.getString("subject_name") + " ");
                System.out.println("Marks: "+rs.getString("marks") + " ");
                System.out.println("Teacher Name: "+rs.getString("teacher_name") + " ");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public String[] searchSubject(Connection conn, String studentId) {
        Statement statement;
        ResultSet rs = null;

        try {
            String query = "select subject_name\n" +
                    "from subject\n" +
                    "inner join marks\n" +
                    "on marks.subject_id = subject.subject_id\n" +
                    "where student_id = "+studentId;
            statement = conn.createStatement();
            rs = statement.executeQuery(query);

            int tem=0;
            while (rs.next()) {
                String subname = rs.getString("subject_name");
                return new String[]{subname};
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

}
