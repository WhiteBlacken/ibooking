Feature: Search seat available by page
  Scenario: Search seat available by page,page_size is 4
    Given I have at least one seat available
    When I search seats by page size of 4
    Then I can see seats more than 0 and less than 5
