Feature: Cucumber Testing

  @skip
  Scenario: Server is up & running
    When test api is hit
    Then response status should be 200
    And see "server up & running." as response


  Scenario: publish to Kafka & verify message
    When publish api is hit
    Then response status should be 200
    And verify published kafka message

  @skip
  Scenario: test kafka consumer
    Then read all kafka messages

