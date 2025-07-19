import java.io.*;
import java.util.*;

public class Services {

    // global path
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
                    addGrade(scanner);
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

            // check students database
            if(bufferedReader.readLine() == null){
                System.out.println("No students Added!\n");
                bufferedReader.close();
                fileWriter.close();
                return;
            }
            
            // first buffer loop
            // print all student(s) name
            String line;
            int i = 1;
            while ((line = bufferedReader.readLine()) != null) {
                if(line.contains("Name: ")){
                    System.out.printf("Student %d %s\n", i, line);
                    i++;
                }
            }

            // reset the index of database
            bufferedReader.close();
            bufferedReader = new BufferedReader(new FileReader(file));

            // get user input search
            System.out.print("Enter name of the student: ");
            String studentName = scanner.nextLine();

            // algorithm for search
            String line2;
            boolean hasSearch = false;
            while ((line2 = bufferedReader.readLine()) != null) {
                if(line2.equals("Name: " + studentName) ||
                   line2.toUpperCase().equals("NAME: " + studentName.toUpperCase())){
                    hasSearch = true;
                    break;
                }
            }

            // check if has search student
            if(hasSearch == true){

                // check if student already have an assignment
                String lineAssignment = bufferedReader.readLine().replace("Assignment: ", "");
                if(lineAssignment.equals("")){ // no assignment

                    // get assignment
                    System.out.printf("Add Assignment to %s: ", studentName);
                    String addAssignment = scanner.nextLine();

                    // reset database index
                    bufferedReader.close();
                    bufferedReader = new BufferedReader(new FileReader(file));

                    // convert database into array list to modified
                    List<String> allLines = new ArrayList<>();
                    String lineString;
                    while ((lineString = bufferedReader.readLine()) != null) {
                        allLines.add(lineString);
                    }

                    // modifying array list
                    for(int index = 0; index < allLines.size(); index++){
                        if(allLines.get(index).equals("Name: " + studentName) || 
                           allLines.get(index).equals("NAME: " + studentName.toUpperCase())){
                            // add assignment
                            allLines.set(index + 1, "Assignment: " + addAssignment);
                            break;
                        }
                    }

                    // clear the current database
                    FileWriter fileWriterFalse = new FileWriter(file, false);
                    fileWriterFalse.close();
                    
                    // add all lines of array list into cleared database
                    for(String eachLine : allLines){
                        fileWriter.append(eachLine + "\n");
                    }

                }else{ // already have assignment
                    System.out.println(studentName + " Already have assignment");
                }

            }else{ // no search name found
                System.out.println("No student name " + studentName);
            }

            // new line
            System.out.println();
            fileWriter.close();
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println("File doesnt exist\n");
        }

    }

    public void addGrade(Scanner scanner){

        try {

            // TODO check first if the student have an assignment before giving grade 
            // TODO assignment is require before giving grade

            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            List<String> lines = new ArrayList<>();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            } 

            bufferedReader.close();


            int num = 1;
            for(int i = 0; i < lines.size(); i ++){
                if(lines.get(i).startsWith("Name: ")){
                    System.out.printf("Student %d %s\n", num, lines.get(i).replace("Name: ", ""));
                    num++;
                }
            }

            System.out.print("Enter student name: ");
            String studentName = scanner.nextLine();

            //check if the student already have a assignment
            boolean studentHasAssignment = false;
            for(int i = 0; i < lines.size(); i++){
                if(lines.get(i).startsWith("Name: " + studentName)){
                    if(lines.get(i + 1).replace("Assignment: ", "") == ""){
                        System.out.println(studentName + " Doesnt have assignment yet\n");
                        return;
                    }
                }
            }

            bufferedReader = new BufferedReader(new FileReader(file));

            boolean hasFound = false;
            String line1;
            while((line1 = bufferedReader.readLine()) != null){
                if(line1.equals("Name: " + studentName) ||
                   line1.toUpperCase().equals("Name: ".toUpperCase() + studentName.toUpperCase())){
                    hasFound = true;
                    break;
                }
            }

            if(hasFound){

                for(int i = 0; i < lines.size(); i ++){
                    if(lines.get(i).equals("Name: " + studentName) &&
                       !(lines.get(i + 2).replace("Grade: ", "").isEmpty())){
                        System.out.println(studentName + " already has a grade.\n");
                        bufferedReader.close();
                        return;
                    }
                }

                System.out.printf("Enter grade for %s: ", studentName);
                String grade = scanner.nextLine();

                for(int i = 0; i < lines.size(); i ++){
                    if(lines.get(i).equals("Name: " + studentName) ||
                       lines.get(i).equals("NAME: " + studentName.toUpperCase())){
                        lines.set(i + 2, "Grade: " + grade);
                        break;
                    }
                }

                // clear the txt file
                FileWriter fileWriter = new FileWriter(file, false);
                for(String tempLine : lines){
                    fileWriter.append(tempLine + "\n");
                }
                fileWriter.close();

                System.out.println("Grade Added.");
                
            }else{
                System.out.println("No student found named " + studentName);
            }

            System.out.println();
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println("File does not exist");
        }

    }

}