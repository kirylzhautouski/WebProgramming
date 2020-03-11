package main;


import db.daos.Dao;
import db.daos.FacultyDao;
import db.daos.UserDao;
import db.models.Application;
import db.models.Faculty;
import db.models.Subject;
import db.models.User;


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


/**
 * Main class, containing an entry point of the application
 */
public class Main {

    /** All faculties names */
    private static List<String> facultyNames;

    /**
     * An entry point of the application
     * @param args
     *        Command-line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("UniversityDB");

        FacultyDao facultyDao = new FacultyDao(entityManagerFactory);
        UserDao userDao = new UserDao(entityManagerFactory);
        Dao dao = new Dao(entityManagerFactory);

        facultyNames = facultyDao.getAllFacultyNames();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter login: ");
        String login = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (userDao.login(login, password)) {
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
                        List<String> names = mapToUserNames(dao.getRegisteredEnrollees(readFaculty(scanner, facultyDao)));
                        System.out.println(names);
                        break;
                    }
                    case 2: {
                        List<String> names = mapToUserNames(dao.getEnrolleesAboveAverage(readFaculty(scanner, facultyDao)));
                        System.out.println(names);
                        break;
                    }
                    case 3: {
                        System.out.println(dao.getAverage(readFaculty(scanner, facultyDao)));
                        break;
                    }
                    case 4: {
                        List<String> names = mapToUserNames(dao.getAppliedEnrollees(readFaculty(scanner, facultyDao)));
                        System.out.println(names);
                        break;
                    }
                    case 5: {

                        System.out.print("Enter name of the enrollee:");
                        String name = scanner.nextLine();

                        System.out.print("Enter login of the enrollee:");
                        String enrolleeLogin = scanner.nextLine();

                        System.out.print("Enter password of the enrollee:");
                        String enrolleePassword = scanner.nextLine();

                        Faculty faculty = readFaculty(scanner, facultyDao);

                        Set<Subject> subjects = faculty.getSubjects();

                        User user = new User(name, false, enrolleeLogin, enrolleePassword);
                        for (Subject subject : subjects) {
                            System.out.print("Enter grade for " + subject.getName() + ": ");
                            int grade = scanner.nextInt();
                            user.addApplication(new Application(user, faculty, subject, grade));
                        }

                        dao.registerEnrollee(user);

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

    /**
     * Reads faculty from command-line
     * @param scanner scanner used to read from console
     * @param facultyDao dao used to retrieve an exact faculty
     * @return Faculty
     */
    private static Faculty readFaculty(Scanner scanner, FacultyDao facultyDao) {
        System.out.println(facultyNames);
        System.out.print("Enter name of the faculty: ");
        return facultyDao.getFaculty(scanner.nextLine());
    }

    /**
     * Get list of names from list of users
     * @param users Users
     * @return Names
     */
    private static List<String> mapToUserNames(Set<User> users) {
        List<String> names = new ArrayList<String>();
        for (User enrollee : users) {
            names.add(enrollee.getName());
        }

        return names;
    }

}

