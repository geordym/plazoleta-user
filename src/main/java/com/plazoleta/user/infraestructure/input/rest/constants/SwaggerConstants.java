package com.plazoleta.user.infraestructure.input.rest.constants;

public class SwaggerConstants {

    public static final String AUTHENTICATE_USER_SUMMARY = "Authenticate User";
    public static final String AUTHENTICATE_USER_DESCRIPTION = "Authenticates a user based on the provided credentials and returns an authentication token.";

    public static final String AUTHENTICATE_USER_API_RESPONSES_200_DESCRIPTION = "Authentication successful, token issued.";
    public static final String AUTHENTICATE_USER_API_RESPONSES_404_DESCRIPTION = "User not found or credentials are incorrect.";
    public static final String AUTHENTICATE_USER_API_RESPONSES_401_DESCRIPTION = "Unauthorized, invalid credentials.";


    public static final String FIND_EMPLOYEE_BY_ID_SUMMARY = "Find Employee Info if you need know the restaurant who works use that";
    public static final String FIND_EMPLOYEE_BY_ID_DESCRIPTION_OK = "Employee exists and return the employee data";
    public static final String FIND_EMPLOYEE_BY_ID_DESCRIPTION_NOTFOUND = "Employee with the given id cannot be founded";
    public static final String OPERATION_FIND_USER_BY_ID = "Find user by id";

}
