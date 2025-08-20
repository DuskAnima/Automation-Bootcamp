@login
Feature: Login de bibliotecario

  Scenario: Login exitoso
    Given que el bibliotecario abre la página de login
    When ingresa credenciales válidas
    Then accede al registro de préstamo

  Scenario: Login fallido
    Given que el bibliotecario abre la página de login
    When ingresa credenciales inválidas
    Then ve un mensaje de error de login
