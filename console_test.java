import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class console_test {

    public static void main(String args[]) {
          
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose option:");
        System.out.println("1. Input/Output on Console");
        System.out.println("2. Input/Output on GUI");
        System.out.print("Your choice: ");
        int choice = sc.nextInt();
        
        if (choice == 1) {
            runConsoleVersion();
        } else if (choice == 2) {
            gui_quiz.main(null); // GUI version
        } else {
            System.out.println("Invalid choice.");
        }
        
        sc.close();
    }
        
       private static void runConsoleVersion() {
    Scanner sc = new Scanner(System.in);
    System.out.println("\n== Console Version ==");
    Connection conn = null;
    try {
        conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/m7_database", "root", "");
        while (true) {
            int code;
            while (true) {
                System.out.print("Enter student code: ");
                code = sc.nextInt();
                PreparedStatement checkPs = conn.prepareStatement("SELECT COUNT(*) FROM students WHERE code = ?");
                checkPs.setInt(1, code);
                var rsCheck = checkPs.executeQuery();
                rsCheck.next();
                int count = rsCheck.getInt(1);
                rsCheck.close();
                checkPs.close();
                if (count > 0) {
                    System.out.println("This code is already exist. Please enter a different code.");
                } else {
                    break; 
                }
            }

            System.out.print("Enter student name: ");
            sc.nextLine(); // consume newline
            String name = sc.nextLine();

            System.out.print("Enter midterm score: ");
            double midterm = sc.nextDouble();

            System.out.print("Enter final score: ");
            double finalScore = sc.nextDouble();

            double avg = (midterm + finalScore) / 2.0;
            String grade;
            if (avg >= 90) grade = "A";
            else if (avg >= 80) grade = "B";
            else if (avg >= 70) grade = "C";
            else if (avg >= 60) grade = "D";
            else grade = "F";

            // Insert data
            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO `students`(`code`, `name`, `midterm`, `final_score`, `average`, `grade`) VALUES (?, ?, ?, ?, ?, ?)"
            );
            ps.setInt(1, code);
            ps.setString(2, name);
            ps.setDouble(3, midterm);
            ps.setDouble(4, finalScore);
            ps.setDouble(5, avg);
            ps.setString(6, grade);
            ps.executeUpdate();
            ps.close();

            System.out.println("\n--- Result Inserted ---");
            System.out.println("Code: " + code);
            System.out.println("Name: " + name);
            System.out.println("Average Score: " + avg);
            System.out.println("Grade: " + grade);

            // Display all students
            ps = conn.prepareStatement("SELECT * FROM students");
            var rs = ps.executeQuery();
            System.out.println("\n== Students in Database ==");
            while (rs.next()) {
                System.out.printf("Code = %d | Name = %s | Midterm = %.2f | Final = %.2f | Average = %.2f | Grade = %s\n",
                    rs.getInt("code"),
                    rs.getString("name"),
                    rs.getDouble("midterm"),
                    rs.getDouble("final_score"),
                    rs.getDouble("average"),
                    rs.getString("grade")
                );
            }
            rs.close();
            ps.close();

            System.out.print("\nPress any key to continue or 'no' to Exit: ");
            sc.nextLine();
            String next = sc.nextLine();
            if (next.equalsIgnoreCase("no")) {
                break;
            }
        }
        System.out.println("\n== End of Input ==");
    } catch (SQLException ex) {
        ex.printStackTrace();
    } finally {
        try {
            if (conn != null) conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
}