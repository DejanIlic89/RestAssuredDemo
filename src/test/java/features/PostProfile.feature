# new feature
# Tags: optional

Feature: PostProfile
  Test POST operation using REST-assured library

  @smoke
  Scenario: Verify Post operation for Profile
    Given I perform POST operation for "/posts/{profileNo}/profile" with body
      | name | prifile |
      | Sams | 2       |
    Then I should see the body has name as "Sams"