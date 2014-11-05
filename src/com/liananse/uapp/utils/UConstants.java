/*
 * Copyright 2014 zenghui.wang.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.liananse.uapp.utils;

public class UConstants {
	// 应用名称
	public static final String APP_NAME = "uappname";
	// 应用渠道
	public static final String APP_CHANNEL = "uchannelname";

	// 一些开关 开始
	// 是否显示请求返回的信息
	public static final boolean isDataLoaderDebug = false;

	public static final boolean useFont = false;

	// 一些开关 结束
	// 请求返回正确
	public static int SUCCESS = 0;
	// 请求返回错误
	public static int FAILURE = 1;
	// 已失效，已过期
	public static int INVALID = 2;
	
	/**
	 * SharedPreferences name
	 */
	// base SharedPreference name if no others, use this
	public static final String BASE_PREFS_NAME = "base_prefs";

	/**
	 * SharedPreferences item
	 */
	/**
	 * 用户ID
	 */
	public static final String SELF_USER_ID = "self_user_id";
	/**
	 * accessToken
	 */
	public static final String SELF_ACCESS_TOKEN = "self_access_token";
}
