package com.plazoleta.user.domain.util;

public class ExceptionConstants {

    private ExceptionConstants() {
    }

    public static final String EMAIL_ERROR = "Email error";
    public static final String EMAIL_MESSAGE = "An error has been detected in the email, please check it";


    public static final String EMAIL_ALREADY_TAKEN_MESSAGE = "This email is been using by another user, please use other or restore your password";


    public static final String IDENTITY_ERROR = "Identity error document";
    public static final String IDENTITY_INVALID_MESSAGE = "Identity document does not have the correct length";
    public static final String IDENTITY_ALREADY_TAKEN_MESSAGE = "Identity document already been taken by another user, please use other";


    public static final String PHONE_NUMBER_ERROR = "Invalid phone number.";
    public static final String PHONE_NUMBER_MESSAGE = "Please enter a valid phone number.";

    public static final String MINOR_AGE_ERROR = "You must be of legal age.";
    public static final String MINOR_AGE_MESSAGE = "You must be at least 18 years old to register.";

    public static final String USER_EMAIL_ALREADY_TAKEN_ERROR = "Email is already in use.";
    public static final String USER_EMAIL_ALREADY_TAKEN_MESSAGE = "Please use another email address.";

    public static final String USER_DOESNOT_EXIST_ERROR = "User not found";
    public static final String USER_DOESNOT_EXIST_MESSAGE = "We cannot found the user do you want to use.";

    public static final String INVALID_USERNAME_PASSWORD_ERROR = "invalid username or password exception";
    public static final String INVALID_USERNAME_PASSWORD_MESSAGE = "invalid user name or password exception.";

    public static final String EXTERNAL_CONNECTION_ERROR = "External connection error";
    public static final String EXTERNAL_CONNECTION_MESSAGE = "We cannot connect with an required external service please try more later.";


    public static final String OWNERSHIP_VIOLATION_ERROR = "You cannot do this operation in that restaurant";
    public static final String OWNERSHIP_VIOLATION_MESSAGE = "Verify if you have access to that restaurant.";

    public static final String EMPLOYEE_DOESNOT_EXIST_ERROR = "The employee cannot be founded";
    public static final String EMPLOYEE_DOESNOT_EXIST_MESSAGE = "The employee with the given id cannot be founded, please try with another.";

    public static final String RESTAURANT_NOT_FOUND_ERROR = "The restaurant cannot be founded";
    public static final String RESTAURANT_NOT_FOUND_ERROR_MESSAGE = "That restaurant cannot be founded.";

}
