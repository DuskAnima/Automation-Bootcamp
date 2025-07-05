package cl.taskmanager.service;

import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.*;

public class TaskManagerTest {
    private TaskManager manager; // Se crea el servicio

    @BeforeEach
    void setUp() {
        manager = new TaskManager();
    }

    @Test
    void shouldCreateTask() {
        Task task = manager.createTask("Lorem ipsum", "Dolor sit amet"); 
        //El servicio requiere de un modelo para poder registrarlo en la base de datos.
        assertThat(task.getId()).isEquaTo(1);
    }
} 
