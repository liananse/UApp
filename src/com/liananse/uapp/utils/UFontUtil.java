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

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import android.app.Application;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.widget.TextView;

public class UFontUtil {
	private static boolean useAvenir = true;
	private static Map<Weight, FontStuff> weightToValues = new ConcurrentHashMap<Weight, FontStuff>();

	public static void initializeTypefaces(Application paramApplication) {
		try {
			try {
				weightToValues.put(
						Weight.LIGHT,
						new FontStuff(Typeface.createFromAsset(
								paramApplication.getAssets(),
								"fonts/avenir-light.ttf"), Typeface.NORMAL));
				weightToValues.put(
						Weight.BOOK,
						new FontStuff(Typeface.createFromAsset(
								paramApplication.getAssets(),
								"fonts/avenir-book.ttf"), Typeface.NORMAL));
				weightToValues.put(
						Weight.ROMAN,
						new FontStuff(Typeface.createFromAsset(
								paramApplication.getAssets(),
								"fonts/avenir-roman.ttf"), Typeface.NORMAL));
				weightToValues.put(
						Weight.MEDIUM,
						new FontStuff(Typeface.createFromAsset(
								paramApplication.getAssets(),
								"fonts/avenir-medium.ttf"), Typeface.NORMAL));
				weightToValues.put(
						Weight.HEAVY,
						new FontStuff(Typeface.createFromAsset(
								paramApplication.getAssets(),
								"fonts/avenir-heavy.ttf"), Typeface.NORMAL));
				weightToValues.put(
						Weight.BLACK,
						new FontStuff(Typeface.createFromAsset(
								paramApplication.getAssets(),
								"fonts/avenir-black.ttf"), Typeface.NORMAL));
				weightToValues.put(
						Weight.MISSION,
						new FontStuff(Typeface.createFromAsset(
								paramApplication.getAssets(),
								"fonts/mission_gothic.otf"), Typeface.NORMAL));
				weightToValues
						.put(Weight.MILI,
								new FontStuff(Typeface.createFromAsset(
										paramApplication.getAssets(),
										"fonts/mi_li_jian_zhi_ti.TTF"),
										Typeface.NORMAL));
				weightToValues.put(
						Weight.HUAKANG,
						new FontStuff(Typeface.createFromAsset(
								paramApplication.getAssets(),
								"fonts/hua_kang_shao_nv_ti.TTF"),
								Typeface.NORMAL));

				if (!useAvenir) {

				}
				return;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				while (true) {
					useAvenir = false;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
	}

	public static void setTypeface(TextView tv, Weight paramWeight) {
		FontStuff localFontStuff = (FontStuff) weightToValues.get(paramWeight);

		if (useAvenir) {
			tv.setPaintFlags(Paint.SUBPIXEL_TEXT_FLAG | tv.getPaintFlags());
		}

		tv.setTypeface(localFontStuff.typeface, localFontStuff.style);
	}

	public static class FontStuff {
		public final int style;
		public final Typeface typeface;

		public FontStuff(Typeface paramTypeface, int paramStyle) {
			this.typeface = paramTypeface;
			this.style = paramStyle;
		}
	}

	public static enum Weight {
		LIGHT, BOOK, ROMAN, MEDIUM, HEAVY, BLACK, MISSION, MILI, HUAKANG
	}
}
