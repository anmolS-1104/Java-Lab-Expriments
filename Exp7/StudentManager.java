import java.io.*;
import java.util.*;

public class StudentManager {
    private static final String FILE_NAME = "Students.csv";
    private static final String HEADER = "studentId,name,branch,marks1,marks2,marks3,marks4,marks5,percentage";

    public static void main(String[] args) {
        try {
            // 1. Create file and add initial rows
            initFile();
            displayFile("Initial State");

            // 2. Add 3 more rows via program (marks4 & marks5 = 0)
            addStudent("103", "Alice", "CS", 85, 90, 80, 0, 0);
            addStudent("104", "Bob", "IT", 70, 75, 65, 0, 0);
            addStudent("105", "Charlie", "ECE", 60, 55, 50, 0, 0);
            displayFile("After Adding 3 Rows");

            // 3. Update marks and calculate percentages
            updateAndCalculate();
            displayFile("After Updating Marks and Percentages");

            // 4. Delete a row (e.g., studentId 102)
            deleteStudent("102");
            displayFile("Final State (After Deleting Student 102)");

            // 5. Trigger Exception Condition
            System.out.println("\n--- Triggering Exception ---");
            readFromNonExistentFile("MissingFile.csv");

        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    // CREATE: Initialize file with header and a few rows
    private static void initFile() throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            bw.write(HEADER);
            bw.newLine();
            bw.write("101,John,CS,80,70,90,85,88,0");
            bw.newLine();
            bw.write("102,Jane,ME,75,80,70,60,65,0");
            bw.newLine();
        }
    }

    // CREATE: Append new student
    private static void addStudent(String id, String name, String branch, int m1, int m2, int m3, int m4, int m5) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            bw.write(String.format("%s,%s,%s,%d,%d,%d,%d,%d,0", id, name, branch, m1, m2, m3, m4, m5));
            bw.newLine();
        }
    }

    // UPDATE: Modify marks and calculate percentage
    private static void updateAndCalculate() throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line = br.readLine(); // Read Header
            lines.add(line);

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                // Update marks4 and marks5 if they were zero (demo update logic)
                if (data[6].equals("0")) data[6] = "75"; 
                if (data[7].equals("0")) data[7] = "82";

                // Calculate percentage
                double total = 0;
                for (int i = 3; i <= 7; i++) {
                    total += Double.parseDouble(data[i]);
                }
                double percentage = total / 5.0;
                data[8] = String.format("%.2f", percentage);
                
                lines.add(String.join(",", data));
            }
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (String l : lines) {
                bw.write(l);
                bw.newLine();
            }
        }
    }

    // DELETE: Remove a student by ID
    private static void deleteStudent(String studentId) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.startsWith(studentId + ",")) {
                    lines.add(line);
                }
            }
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (String l : lines) {
                bw.write(l);
                bw.newLine();
            }
        }
    }

    // READ: Helper to display file content
    private static void displayFile(String label) throws IOException {
        System.out.println("\n--- " + label + " ---");
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

    // EXCEPTION HANDLING: Forced failure
    private static void readFromNonExistentFile(String fileName) {
        try {
            FileReader fr = new FileReader(fileName);
        } catch (IOException e) {
            System.out.println("Caught Expected Exception: " + e);
        }
    }
}