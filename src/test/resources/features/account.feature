Feature: Gestion de cuentas bancarias
  Como cliente
  Quiero consultar y depositar dinero en mi cuenta bancaria
  Para mantener actualizado mi saldo
  Scenario: Consultar saldo de una cuenta existente
    Given que existe una cuenta de "Jose" con saldo 1600
    When consulto el saldo de "Jose"
    Then el sistema debe devolver un saldo de 1600

  Scenario Outline: Depositar dinero en una cuenta existente
    Given que existe una cuenta de "<nombre>" con saldo <saldo_inicial>
    When deposito <monto> en la cuenta de "<nombre>"
    Then el saldo de la cuenta de "<nombre>" debe ser <saldo_final>

    Examples:
      | nombre | saldo_inicial | monto | saldo_final |
      | Jose   | 1600          | 200   | 1800        |
      | Maria  | 1990          | 300   | 2290        |