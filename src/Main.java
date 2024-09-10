//import presentation.ConsoleUI;


import DB.DatabaseConnection;
import presentation.ConsoleUI;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
       ConsoleUI consoleUI = new ConsoleUI();
        consoleUI.start();


    }
}