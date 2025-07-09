import db.DatabaseConnection;

public class App {

    public static void main(String[] args) {
    DatabaseConnection databaseConnectionUno = DatabaseConnection.getInstance();
    DatabaseConnection databaseConnectionDos = DatabaseConnection.getInstance();
    System.out.println("DatabaseConnectionUno" + databaseConnectionUno.getConnection());
    System.out.println("DatabaseConnectionUno" + databaseConnectionDos.getConnection());
    if(databaseConnectionUno == databaseConnectionDos) {
        System.out.println("Ambas instancias son iguales (Singletón funciona)");
    } else {
        System.out.println("Las instancias son diferentes (Singletón no funciona)");
    }
}
}
