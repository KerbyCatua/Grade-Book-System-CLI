import java.io.*;
import java.util.*;

public class Services {

    // global
    private final String filePath = "C:\\Users\\Kerby\\Documents\\JAVA OOP CLI\\Grade-Book-System-CLI\\src\\DataBase.txt";
    private final File file = new File(filePath);

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
                    addAssignment(scanner);
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

            // create the txt file path, read/write
            FileWriter fileWriter = new FileWriter(file, true);

            // get user input
            System.out.print("Enter student name: ");
            String studentName = scanner.nextLine();

            // check if characters only
            String regexString = "[a-zA-Z\s]{2,30}";
            boolean isStudentNameValid = false;
            if(studentName.matches(regexString)){
                isStudentNameValid = true;
            }

            // final checking user input
            if(isStudentNameValid == true){
                System.out.println("Student added.\n");
                fileWriter.append("\n\nName: " + studentName 
                                  + "\nAssignment: "
                                  + "\nGrade: "
                                  + "\n----------------------");
            }else{
                System.out.println("Student name contains letters only (min 2, max 30)\n");
            }

            fileWriter.close();
        } catch (Exception e) { // file handling
            System.out.println("File doesnt exist");
        }

    }

    public void addAssignment(Scanner scanner){

        try {
            // create the txt file path, read/write
            FileWriter fileWriter = new FileWriter(file, true);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // check if has student in database, else return
            if(bufferedReader.readLine() == null){
                System.out.println("No students Added!\n");
                bufferedReader.close();
                fileWriter.close();
                return;
            }
            
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

            System.out.println();
            fileWriter.close();
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println("File doesnt exist");
        }

    }

}