import java.io.*;
import java.util.*;

public class Services {

    public void landingPage(Scanner scanner){

        while(true){
            System.out.println("Welcome to the GradeBook CLI\n\n"
                                + "1. Add Student\n"
                                + "2. Add Assignment\n"
                                + "3. Enter Grade\n"
                                + "4. View Report\n"
                                + "5. Exit\n");

            System.out.print("Select: ");
            String userSelect = scanner.nextLine();
            switch (userSelect) {
                case "1" -> { // add student
                    addStudent(scanner);
                }case "2" -> { // add assignment
                    
                }case "3" -> { // enter grade
                    
                }case "4" -> { // view report
                    
                }case "5" -> { // exit
                    System.out.println("Program Exited...");
                    System.exit(0);
                }default -> {
                    System.out.println("Invalid Choice!\n");
                }
            }
        }

    }

    public void addStudent(Scanner scanner){

        try {
            String filePath = "C:\\Users\\Kerby\\Documents\\JAVA OOP CLI\\Grade-Book-System-CLI\\src\\DataBase.txt";
            File file = new File(filePath);
            FileWriter fileWriter = new FileWriter(file);

            System.out.print("Enter student name: ");
            String studentName = scanner.nextLine();

            String regexString = "[a-zA-Z\s]{2,30}";
            boolean isStudentNameValid = false;
            if(studentName.matches(regexString)){
                isStudentNameValid = true;
            }

            if(isStudentNameValid == true){
                System.out.println("Student added.\n");
                fileWriter.append(studentName);
            }else{
                System.out.println("Student name contains letters only (min 2, max 30)\n");
            }

            fileWriter.close();
        } catch (Exception e) {
            System.out.println("File doesnt exist");
        }

    }

}