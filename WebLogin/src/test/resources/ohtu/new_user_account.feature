Feature: A new user account can be created if a proper unused username and password are given

    Scenario: creation succesful with correct username and password
        Given new user is selected
        When  a valid username "reino" and a valid password "RepoM1es" and a correct password confirmation "RepoM1es" is given
        Then  a new user account is created

    Scenario: creation fails with too short username and valid passord
        Given new user is selected
        When  a too short username "re" and a valid password "RepoM1es" and a correct password confirmation "RepoM1es" is given
        Then user is not created and error "username should have at least 3 characters" is reported   

    Scenario: creation fails with correct username and too short password
        Given new user is selected
        When  a valid username "reino" and a too short password "RepoM1e" and a correct password confirmation "RepoM1e" is given
        Then user is not created and error "password should have at least 8 characters" is reported   

    Scenario: creation fails with correct username and password consisting of letters
        Given new user is selected
        When  a valid username "reino" and a password containing only letters "RepoMies" and a correct password confirmation "RepoMies" is given
        Then user is not created and error "password can not contain only letters" is reported 

    Scenario: creation fails with already taken username and valid pasword
        Given new user is selected
        When  a already taken username "jukka" and a valid password "RepoM1es" and a correct password confirmation "RepoM1es" is given
        Then user is not created and error "username is already taken" is reported 

    Scenario: creation fails when password and password confirmation do not match
        Given new user is selected
        When  a valid username "reino" and a valid password "RepoM1es" and an incorrect password confirmation "RepoM1ess" is given
        Then user is not created and error "password and password confirmation do not match" is reported