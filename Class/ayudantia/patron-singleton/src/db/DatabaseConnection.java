package db;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private String connectionString;

    private DatabaseConnection() {
        connectionString = "Conexión establecida a la base de datos";
    }
    // Metodo estático responsable de crear la instancia de la clase pero solo UNA vez.
    // Si esta instancia existe, entonces devuelve la instancia ya creada.
    // Este patrón como pertenece a la clase, puede crear una instancia con el constructor privado.
    public static DatabaseConnection getInstance() {
        if(instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
    
    public String getConnection() {
        return connectionString;
    }

}

