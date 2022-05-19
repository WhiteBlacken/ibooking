@张三
Feature: 计算器计算功能
  As a [学生]
  I want [计算]
  So that [使用计算器]

  @Test
  Scenario: 相加计算
    Given 两个数a=1、b=1
    When 相加计算
    Then 计算结果

  @Test
  Scenario Outline: 相减计算
    Given 两个数"<a>"、"<b>"
    When 相减计算
    Then 验证结果"<计算结果>"

    Examples:
      | a | b | 计算结果 |
      | 2 | 1 | 1    |
      | 5 | 2 | 3    |