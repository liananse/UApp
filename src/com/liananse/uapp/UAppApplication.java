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
package com.liananse.uapp;

import android.app.Application;

import com.liananse.uapp.utils.UFontUtil;
import com.squareup.otto.Bus;

public class UAppApplication extends Application {

	public static Bus bus;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();

		bus = new Bus();
		
		// 字体初始化
		UFontUtil.initializeTypefaces(this);
	}

	public static Bus getBus() {
		if (bus == null) {
			bus = new Bus();
		}

		return bus;
	}

}
