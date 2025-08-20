@prestamo
Feature: Registro de préstamo

  Scenario Outline: Registro exitoso de préstamo
    Given que el bibliotecario está en la página de préstamo
    When ingresa los datos del préstamo
      | socioId | isbn         | fechaPrestamo | FechaDevolucion |
      | 0001    | 97800000000  | 2025-08-01    | 2025-08-10      |
      | 0002    | 97811111111  | 2025-08-02    | 2025-08-12      |
    Then ve el mensaje de éxito