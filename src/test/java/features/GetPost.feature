Feature:
  Verify different GET operations using REST-assured

  Scenario: Verify one author of the post
    Given I perform GET operation for "/posts"
    Then I should see the author name as "Dejan Ilic"

  Scenario: Verify collection of authors in the post
    Given I perform GET operation for "/posts"
    Then I should see the author names

  Scenario: Verify Parameter of Get
    Given I perform GET operation for "/posts"
    Then I should verity GET Parameter

  Scenario: Verify Post operation
    Given I perform POST operation for "/posts"

  @smoke
  Scenario: Verify GET operation with bearer authentication token
    Given I perform authentication operation for "/auth/login" with body
      | email           | password |
      | bruno@email.com | bruno    |
    Given I perform GET operation for "/posts/1"
    Then I should see the author name as "Dejan Ilic"

  @smoke
  Scenario: Verify GET operation with bearer authentication token for json schema validation
    Given I perform authentication operation for "/auth/login" with body
      | email           | password |
      | bruno@email.com | bruno    |
    Given I perform GET operation for "/posts/1"
    Then I should see the author name as "Dejan Ilic" with json validation