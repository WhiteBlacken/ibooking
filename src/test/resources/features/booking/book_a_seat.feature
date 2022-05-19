Feature: book a seat


  Scenario Outline: book a seat that is not open
    Given the userId is <stuId>,the id of seat to reserved is <seatId>,
    And  the beginTime is <beginTime>,the reserve hour is <hour>
    And the seat is not open
    Then the status of reserved result is <code>,message is <message>

    Examples:
      | stuId | seatId | beginTime       | hour | code | message |
      | 2     | 4      | "2024-11-23 13" | 4    | 500  | "该座位未开放"  |

  Scenario Outline: book a seat that is occupied
    Given the userId is <stuId>,the id of seat to reserved is <seatId>,
    And  the beginTime is <beginTime>,the reserve hour is <hour>
    And the seat is occupied
    Then the status of reserved result is <code>,message is <message>

    Examples:
      | stuId | seatId | beginTime       | hour | code | message |
      | 2     | 4      | "2024-11-23 13" | 4    | 500  | "该座位已被占用"  |

  Scenario Outline: user has more than one records in the same time
    Given the userId is <stuId>,the id of seat to reserved is <seatId>,
    And  the beginTime is <beginTime>,the reserve hour is <hour>
    And the user has other booking Records in the same time
    Then the status of reserved result is <code>,message is <message>

    Examples:
      | stuId | seatId | beginTime       | hour | code | message |
      | 2     | 4      | "2024-11-23 13" | 4    | 500  | "您在该时间段已有其他预约"  |

  Scenario Outline: book a seat that is success
    Given the userId is <stuId>,the id of seat to reserved is <seatId>,
    And  the beginTime is <beginTime>,the reserve hour is <hour>
    Then the status of reserved result is <code>,message is <message>

    Examples:
      | stuId | seatId | beginTime       | hour | code | message |
      | 2     | 4      | "2024-11-23 13" | 4    | 200  | "预定成功"  |