package cl.taskmanager.service;

import org.junit.jupiter.api.*;

import cl.taskmanager.model.Task;

import static org.assertj.core.api.Assertions.*;


public class TaskManagerTest {
    private TaskManager manager; // Se crea el servicio

    @BeforeEach
    void setUp() {
        manager = new TaskManager();
    }

    @Test // Pruebas de creación de tareas
    void shouldCreateTask() {
        // El servicio requiere de un modelo para poder registrarlo en la base de datos.
        Task task = manager.createTask("Lorem ipsum", "Dolor sit amet"); 
        // Se comprueba que el modelo posee el id correcto basado en iteraciones, 1 en este caso
        assertThat(task.getId()).isEqualTo(1);
        // Llama directamente al serivicio para verificar el anexamiento a la lista de tareas.
        assertThat(manager.getList()).hasSize(1); 
    }

    @Test // Prueba de edición de tareas
    void shouldUpdateTask() {
        Task task = manager.createTask("Lorem ipsum", "Dolor sit amet");  // Modelo 
        boolean updateHeader = manager.updateHeader(task.getId(), "Nuevo Título"); // Se altera título
        boolean updateParagraph = manager.updateParagraph(task.getId(), "Nuevo Párrafo"); // Se altera descripción
        assertThat(updateHeader).isTrue(); // El header fue actualizado?
        assertThat(updateParagraph).isTrue(); // El párrafo fue actualizado?
        assertThat(task.getHeader()).isEqualTo("Nuevo Título"); // Devuelve título de la tarea?
        assertThat(task.getParagraph()).isEqualTo("Nuevo Párrafo"); // Devuelve párrafo de la tarea?
    }

    @Test // Prueba de eliminación de tareas
    void shoudDeleteTask() {
        Task task = manager.createTask("Lorem ipsum", "Dolor sit amet"); // Modelo
        boolean removed = manager.deleteTask(task.getId()); // Se elimina tarea de la lista
        assertThat(removed).isTrue(); // La tarea fue eliminada? 
        assertThat(manager.getList()).isEmpty(); // La lista está vacía?
    }
} 
