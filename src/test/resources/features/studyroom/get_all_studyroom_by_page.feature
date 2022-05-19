Feature: get all studyRooms by page
  show all studyRooms by page size and page number

  Scenario Outline: get all studyRooms by page
    Given page size is <pageSize>
    And page number is <pageNum>
    When I get all studyRooms
    Then I should be given studyRooms of right num

  Examples:
    |pageSize|pageNum|
    |1       |1      |