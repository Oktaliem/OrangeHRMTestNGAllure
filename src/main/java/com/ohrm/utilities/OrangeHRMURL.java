package com.ohrm.utilities;

public class OrangeHRMURL {
    private OrangeHRMURL() {throw new IllegalStateException("OrangeHRMURL class"); }

    public static final String BASE_URL = "http://127.0.0.1/orangehrm-4.0/symfony/web/index.php";
    public static final String LOGIN_URL = BASE_URL + "/auth/login";
    public static final String ADMIN_PAGE = BASE_URL + "/admin/viewSystemUsers";
    public static final String PIM_PAGE = BASE_URL + "/pim/viewEmployeeList";
    public static final String LEAVE_PAGE = BASE_URL + "/leave/defineLeavePeriod";
    public static final String TIME_PAGE = BASE_URL + "/time/defineTimesheetPeriod";
    public static final String RECRUITMENT_PAGE = BASE_URL + "/recruitment/viewCandidates";
    public static final String PERFORMANCE_PAGE = BASE_URL + "/recruitment/viewCandidates#";
    public static final String DASHBOARD_PAGE = BASE_URL + "/dashboard";
    public static final String DIRECTORY_PAGE = BASE_URL+"/directory/viewDirectory/reset/1";
}
