package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //function
        //inputData();
        //simpleArray();
        //sortPetson();

        //Array
        //int[] arr = new int[5];

        //create db table
        String userName = "root";
        String password = "";
        String host = "localhost";
        String port = "3306";
        String database = "java_vpd111";
        String strConn = "jdbc:mariadb://"+host+":"+port+"/"+database;
        //createDbTable(strConn, userName, password);
        insertCategory(strConn, userName, password);
    }

    private static void insertCategory(String strConn, String userName, String password) {
        Scanner input = new Scanner(System.in);
        CategoryCreate categoryCreate = new CategoryCreate();
        System.out.println("Category Name: ");
        categoryCreate.setName(input.nextLine());
        System.out.println("Category Description: ");
        categoryCreate.setDescription(input.nextLine());

        try(Connection conn = DriverManager.getConnection(strConn,userName,password)) {
            Statement statement = conn.createStatement();
            String insertQuery = "INSERT INTO categories (name, description) VALUES (?, ?)";
            // Create a PreparedStatement
            PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);
            // Set values for the placeholders
            preparedStatement.setString(1, categoryCreate.getName());
            preparedStatement.setString(2, categoryCreate.getDescription());
            // Execute the SQL query
            int rowsAffected = preparedStatement.executeUpdate();
            // Close the resources
            preparedStatement.close();
            System.out.println("Rows affected: " + rowsAffected);
            System.out.println("Category inserted successfully.");
        }
        catch(Exception ex) {
            System.out.println("Помилка створення категорії: "+ex.getMessage());
        }
    }
    private static void createDbTable(String strConn, String userName, String password) {
        try(Connection con = DriverManager.getConnection(strConn, userName, password)) {
            Statement statement = con.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS categories ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "name VARCHAR(255) NOT NULL,"
                    + "description TEXT"
                    + ")";
            statement.execute(sql);
            statement.close();
            System.out.println("----------Success! The table was created----------");
        }
        catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
    private static void sortPetson() {
        //Class
        //Person p = new Person("Maks", "Naumchuk");
        //p.setFirstName("Marko");
        //p.setLastName("Masda");
        //System.out.println(p);

        Person[] list = {
                new Person("Maks", "Maksich"),
                new Person("Mikola", "Maksich"),
                new Person("Anna", "Maksich"),
                new Person("Nastia", "Maksich")
        };
        for (var item : list) {
            System.out.println(item);
        }
        System.out.println("...........................");
        //Arrays.sort(list, new Comparator<Person>() {
        //    @Override
        //    public int compare(Person o1, Person o2) {
        //        return o1.getFirstName().compareTo(o2.getLastName());
        //    }
        // });
        Arrays.sort(list);

        for (var item : list) {
            System.out.println(item);
        }
    }
    private static void simpleArray() {
        //Random

        int n = 10;
        int [] mas = new int[n];
        Random rand = new Random();
        for (int i = 0; i < n; i++){
            mas[i] = getRandom(10, -10);
        }
        //sort
        Arrays.sort(mas);

        //foreach
        for (var item : mas) {
            System.out.printf("%d\t", item);
        }

        int count = 0;
        for (var item : mas) {
            if (item>0) {
                count++;
            }
        }
        System.out.println(">0 numbers: " + count);
    }
    private static int getRandom(int max, int min) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
    private static void inputData() {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("What's your name?");
        String name = scanner.nextLine();
        System.out.printf("Hi %s!\n", name);
        //System.out.println("Hi " + name + "!");
    }
}