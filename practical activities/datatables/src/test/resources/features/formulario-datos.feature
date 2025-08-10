Feature: Formulario de datos de Sofia
    Scenario: Completar datos de Sofia en el formulario usando DataTable
    Given que el usuario accede al formulario de datos
    When completa los siguientes datos:
    | campo    | valor        |
    | nombre   | Sofia        |
    | apellido | Araya        |
    | email    | sofia@qa.com |
    | edad     | 27           |
    Then los datos ingresados deben mostrarse correctamente