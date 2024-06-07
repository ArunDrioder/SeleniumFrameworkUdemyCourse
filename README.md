Please note that InOrder to Run the test cases via Maven in Windows Command line (Command Prompt)., Use the following Command
mvn test -P(profile Name)., for eg; if the profile name is Regression in the surefire plugin, then =====> mvn test -PRegression [No need of any Space between P & ProfileName]
In Case if we want to clean & test then use the below command : 
mvn clean test -P(profile Name)
