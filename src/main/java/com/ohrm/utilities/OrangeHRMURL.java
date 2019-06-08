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

    public static final String RECRUITMENT_PAGE_BASE = ".\\baseimages\\RecruitmentPageFalse.png";
    public static final String RECRUITMENT_PAGE_DIFF = ".\\screenshots\\RecruitmentPage_Diff.png";
    public static final String RECRUITMENT_PAGE_ACTUAL = ".\\screenshots\\RecruitmentPage.png";
    public static final String DIRECTORY_PAGE_BASE =".\\baseimages\\DirectoryPage.png" ;
    public static final String DIRECTORY_PAGE_DIFF =".\\screenshots\\DirectoryPage_Diff.png";
    public static final String DIRECTORY_PAGE_ACTUAL =".\\screenshots\\DirectoryPage.png";
    public static final String TIME_PAGE_BASE =".\\baseimages\\TimePage.png";
    public static final String TIME_PAGE_ACTUAL =".\\screenshots\\TimePage.png";
    public static final String TIME_PAGE_DIFF =".\\screenshots\\TimePage_Diff.png";
    public static final String LEAVE_PAGE_ACTUAL =".\\screenshots\\LeavePage.png";
    public static final String LEAVE_PAGE_DIFF =".\\screenshots\\LeavePage_Diff.png";
    public static final String DASHBOARD_PAGE_BASE =".\\baseimages\\DashboardPage.png";
    public static final String DASHBOARD_PAGE_ACTUAL=".\\screenshots\\DashboardPage.png";
    public static final String DASHBOARD_PAGE_DIFF =".\\screenshots\\DashboardPage_Diff.png";

    public static final String IMAGE_MAGICK ="C:\\Program Files\\ImageMagick-7.0.8-Q16";
}
