package com.plazoleta.user.domain.util;

public class ExceptionConstants {

    private ExceptionConstants() {
    }

    public static final String EMAIL_ERROR = "Email error";
    public static final String EMAIL_MESSAGE = "An error has been detected in the email, please check it";


    public static final String EMAIL_ALREADY_TAKEN_MESSAGE = "This email is been using by another user, please use other or restore your password";


    public static final String IDENTITY_ERROR = "Identity error document";
    public static final String IDENTITY_ALREADY_TAKEN_MESSAGE = "Identity document already been taken by another user, please use other";


    public static final String PHONE_NUMBER_ERROR = "Invalid phone number.";
    public static final String PHONE_NUMBER_MESSAGE = "Please enter a valid phone number.";

    public static final String MINOR_AGE_ERROR = "You must be of legal age.";
    public static final String MINOR_AGE_MESSAGE = "You must be at least 18 years old to register.";

    public static final String USER_EMAIL_ALREADY_TAKEN_ERROR = "Email is already in use.";
    public static final String USER_EMAIL_ALREADY_TAKEN_MESSAGE = "Please use another email address.";

    public static final String USER_DOESNOT_EXIST_ERROR = "User not found";
    public static final String USER_DOESNOT_EXIST_MESSAGE = "We cannot found the user do you want to use.";



}
