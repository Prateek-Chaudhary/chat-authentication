package com.prateek.authentication.constants;

public class RegexBuilder {

    public static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    public static final String PHONE_REGEX = "^\\+?[1-9][0-9]{7,14}$";
    public static final String NAME_REGEX = "^[a-zA-Z\\s]+$";
    public static final String USERNAME_REGEX = "^(?=.{3,20}$)[a-zA-Z0-9](?:[a-zA-Z0-9._-]{1,18}[a-zA-Z0-9])?$";
    public static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).{8,}$";
}