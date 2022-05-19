Feature: Find qualified seats


  Scenario: search seats by condition
    Given Seat number, position and whether there is a socket
    When I search
    Then All the seats I got meet the query conditions

