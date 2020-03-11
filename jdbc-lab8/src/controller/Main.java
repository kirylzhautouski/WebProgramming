package controller;


import db.Dao;
import db.exceptions.DaoException;
import db.model.Enrollee;
import db.model.ExamResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * Main class, containing an entry point of the application
 */
public class Main {

    /**
     * An entry point of the application
     * @param args
     *        Command-line arguments
     * @throws DaoException
     */
    public static void main(String[] args) throws DaoException {
//        System.out.println(dao.getRegisteredEnrollees("famcs"));
//        System.out.println(dao.getAverage("famcs"));
//        System.out.println(dao.getEnrolleesAboveAverage("famcs"));
//        System.out.println(dao.getAppliedEnrollees("famcs"));
//
//        List<ExamResult> examResults = new ArrayList<ExamResult>() {{
//            add(new ExamResult("maths", 10));
//            add(new ExamResult("physics", 10));
//            add(new ExamResult("russian", 10));
//        }};
//
//        Enrollee enrollee = new Enrollee("Volodya Mikhaluk", "famcs", examResults,
//            "volodya", "volodya");
//        dao.registerEnrollee(enrollee);
//

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter login: ");
        String login = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        Dao dao = new Dao();
        if (dao.login(login, password)) {
            while (true) {
                System.out.println("1) Print all enrollees for a specific faculty;");
                System.out.println("2) Print enrollees above average for a specific faculty;");
                System.out.println("3) Print average grade for a specific faculty;");
                System.out.println("4) Print applied enrollees for a specific faculty;");
                System.out.println("5) Register new enrollee;");
                System.out.println("6) Exit.");

                System.out.print("Enter operation number: ");
                int operation = scanner.nextInt();
                scanner.nextLine();

                switch (operation) {
                    case 1: {
                        System.out.println(dao.getAllFaculties());
                        System.out.print("Enter name of the faculty: ");
                        String faculty = scanner.nextLine();
                        System.out.println(dao.getRegisteredEnrollees(faculty));
                        break;
                    }
                    case 2: {
                        System.out.println(dao.getAllFaculties());
                        System.out.print("Enter name of the faculty: ");
                        String faculty = scanner.nextLine();
                        System.out.println(dao.getEnrolleesAboveAverage(faculty));
                        break;
                    }
                    case 3: {
                        System.out.println(dao.getAllFaculties());
                        System.out.print("Enter name of the faculty: ");
                        String faculty = scanner.nextLine();
                        System.out.println(dao.getAverage(faculty));
                        break;
                    }
                    case 4: {
                        System.out.println(dao.getAllFaculties());
                        System.out.print("Enter name of the faculty: ");
                        String faculty = scanner.nextLine();
                        System.out.println(dao.getAppliedEnrollees(faculty));
                        break;
                    }
                    case 5: {
                        System.out.print("Enter name of the enrollee:");
                        String name = scanner.nextLine();

                        System.out.println(dao.getAllFaculties());
                        System.out.print("Enter faculty: ");
                        String facultyName = scanner.nextLine();

                        System.out.print("Enter login of the enrollee:");
                        String enrolleeLogin = scanner.nextLine();

                        System.out.print("Enter password of the enrollee:");
                        String enrolleePassword = scanner.nextLine();

                        List<String> subjects = dao.getSubjectsForFaculty(facultyName);
                        List<ExamResult> examResults = new ArrayList<>();
                        for (String subject : subjects) {
                            System.out.print("Enter grade for " + subject + ": ");
                            examResults.add(new ExamResult(subject, scanner.nextInt()));
                        }

                        Enrollee enrollee = new Enrollee(name, facultyName, examResults, enrolleeLogin, enrolleePassword);
                        dao.registerEnrollee(enrollee);
                        break;
                    }
                    case 6: {
                        scanner.close();
                        System.exit(0);
                    }
                    default: {
                        scanner.close();
                        System.exit(-1);
                    }
                }
            }
        }

        scanner.close();
    }

}
