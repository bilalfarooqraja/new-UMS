import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        DBConnection db= new DBConnection();
        Connection conn=db.connectToDb("postgres","postgres","admin");

        Teacher t= new Teacher();
        Student s= new Student();


//        t.CreateTable(conn);
//        s.CreateStudentTable(conn);

        Scanner input = new Scanner(System.in);
        System.out.println("Press 1 if you are Teacher\nPress 2 if you are Student");

        int num = Integer.parseInt(input.nextLine());


        if(num==1){
            System.out.println("Please Enter Your Name");
            String N=input.nextLine();
            System.out.println("Please Enter Your password");
            String P=(input.nextLine());
            String[] result = t.searchByName(conn,N);


            if(result[0].equals(N) && result[1].equals(P)){
                System.out.println("Welcome "+N);
                boolean loop = true;
               // String subName=t.subjectName(conn,result[2]);

                while(loop) {
                    System.out.println("Press 1 to enter record\nPress 2 See students Record\n Press 3 Exit");
                    int num1 = Integer.parseInt(input.nextLine());


                    if (num1 == 1) {
                        System.out.println("Enter Student rollNu ");
                        Scanner inputsp = new Scanner(System.in);
                        String student_id = inputsp.nextLine();
                        System.out.println("Student Existing Record");
                        s.searchById(conn,student_id);
                        System.out.println("Enter marks");
                        Scanner inputs = new Scanner(System.in);
                        String marks = inputs.nextLine();
                        System.out.println("Press 1 to enter oop marks\nPress 2 to enter itcp marks\nPress 3 to enter maths marks\nPress 4 to enter network marks\n");
                        Scanner inputsub = new Scanner(System.in);
                        String subject_id = inputsub.nextLine();

                        String array[] = s.searchSubject(conn,student_id);
                        if(array!=null){
                            for(int i=0;i<array.length; i++){
                                if(subject_id == "1" && array[i]=="oop"){
                                    System.out.println("This subjecy marks alreadt exist ");
                                    break;
                                } else if (subject_id == "2" && array[i]=="itcp") {
                                    System.out.println("This subjecy marks alreadt exist ");
                                    break;
                                }else if (subject_id == "3" && array[i]=="maths") {
                                    System.out.println("This subjecy marks alreadt exist ");
                                    break;
                                }else if (subject_id == "4" && array[i]=="network") {
                                    System.out.println("This subjecy marks alreadt exist ");
                                    break;
                                }
                            }
                        }

                        String teacher_id=result[2];
                        //Insert into %s(marks,subject_id,student_id,teacher_id)
                        t.InsertStudentMarks(conn, marks,subject_id,student_id,teacher_id);
                    }
                    if (num1 == 2){
                        t.readData(conn);
                    }
                    if (num1 == 3){
                        loop=false;
                    }

                }
            }
        }
        if (num==2){
            System.out.println("Enter your name to see your result");
            String N=input.nextLine();
            s.searchByName(conn,N);
        }
    }
}
