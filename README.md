Build the project with the below script;

mvn clean install

AFTER BUILD AND RUN PROJECT, YOU CAN TEST THE APIS WITH IMPORTING "requestExamplesForIngCustomerLoanAPI.postman_collection.json" TO POSTMAN 

!!! Please read userGuideV**.doc file to test the API !!!


!!! OPTIONAL !!!
If you deploy the war to another environment, please define following properties in application.properties;

spring.datasource.jndi-name=java:comp/env/jdbc/LoanDB