package com.qa.opencart.constants;

import java.util.Arrays;
import java.util.List;

public class AppConstants {
	
	public static final String LOGIN_PAGE_TITLE = "Account Login";
	public static final String ACCOUNT_PAGE_TITLE = "My Account11";
	
	public static final String LOGIN_PAGE_URL_FRACTION = "route=account/login";
	public static final String ACC_PAGE_URL_FRACTION = "route=account/account";
	
	public static final int SHORT_DEFAULT_WAIT =5;
	public static final int MEDIUM_DEFAULT_WAIT = 10;
	public static final int LARGE_DEFAULT_WAIT = 15;
	public static final int POLLING_DEFAULT_WAIT = 2;
	public static final Object ACC_PAGE_HEADERS_COUNT = 4;
	
	public static final List<String> ACC_PAGE_HEADERS_LIST =Arrays.asList("My Account", "My Orders", "My Affiliate Account", "Newsletter");

	public static final String REGISTERATION_SUCCESS_MSG = "Your Account Has Been Created!";
	public static final String REGISTERATION_DATA_SHEET_NAME = "registeration";
	public static final String PRODUCT_DATA_SHEET_NAME = "productinfo";
	
}
