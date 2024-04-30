import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class EmployeeAttendanceRegister {
    private Map<String, Boolean> attendance;

    public EmployeeAttendanceRegister() {
        attendance = new HashMap<>();
    }

    // Method to mark attendance for a given employee
    public void markAttendance(String employeeName, boolean isPresent) {
        attendance.put(employeeName, isPresent);
    }

    // Method to check if a given employee is present
    public boolean isPresent(String employeeName) {
        return attendance.containsKey(employeeName) && attendance.get(employeeName);
    }

    // Method to print the attendance list
    public void printAttendance() {
        System.out.println("Attendance Register:");
        for (Map.Entry<String, Boolean> entry : attendance.entrySet()) {
            System.out.println(entry.getKey() + ": " + (entry.getValue() ? "Present" : "Absent"));
        }
    }

    public static void main(String[] args) {
        EmployeeAttendanceRegister attendanceRegister = new EmployeeAttendanceRegister();
        Scanner scanner = new Scanner(System.in);

        boolean continueMarking = true;
        System.out.println("Welcome to Attendance Register!");
        while (continueMarking) {
            System.out.println("Enter employee name:");
            String employeeName = scanner.nextLine();

            System.out.println("Is " + employeeName + " present? (Y/N):");
            String input = scanner.nextLine();
            boolean isPresent = input.equalsIgnoreCase("Y");

            attendanceRegister.markAttendance(employeeName, isPresent);

            System.out.println("Do you want to mark attendance for another employee? (Y/N):");
            input = scanner.nextLine();
            continueMarking = input.equalsIgnoreCase("Y");
        }

        attendanceRegister.printAttendance();
    }
}
