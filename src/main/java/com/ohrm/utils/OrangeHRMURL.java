package com.ohrm.utils;

public class OrangeHRMURL {

    private OrangeHRMURL(){}

    public static final String BASE_URL = "http://127.0.0.1/orangehrm-4.0/symfony/web/index.php";
    public static final String LOGIN_URL = BASE_URL+"/auth/login";
    public static final String ADMIN_PAGE = BASE_URL+"/admin/viewSystemUsers";
    public static final String PIM_PAGE = BASE_URL+"/pim/viewEmployeeList";
    public static final String LEAVE_PAGE = BASE_URL+"/leave/defineLeavePeriod";
}
