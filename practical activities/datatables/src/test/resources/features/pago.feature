Feature: Validación de Formulario de Pago
  @pago
  Scenario Outline: Validar pago con diferentes montos y métodos
    Given que el usuario accede al formulario de pago
    When ingresa el monto <monto> y selecciona el medio <medio>
    Then el sistema debería mostrar el mensaje "<resultado>"

    Examples:
      | monto | medio         | resultado                     |
      | 500   | Tarjeta       | Pago procesado correctamente  |
      | -100  | Tarjeta       | Monto debe ser mayor a cero   |
      | 0     | Efectivo      | Monto debe ser mayor a cero   |
      | 150   | Transferencia | Pago procesado correctamente  |
      | 150   |               | Medio de pago no seleccionado |