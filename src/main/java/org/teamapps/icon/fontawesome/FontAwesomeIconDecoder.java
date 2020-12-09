/*-
 * ========================LICENSE_START=================================
 * TeamApps Font Awesome Icon Provider
 * ---
 * Copyright (C) 2014 - 2021 TeamApps.org
 * ---
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * =========================LICENSE_END==================================
 */
package org.teamapps.icon.fontawesome;

import org.teamapps.icons.IconDecoderContext;
import org.teamapps.icons.spi.IconDecoder;

public class FontAwesomeIconDecoder implements IconDecoder<FontAwesomeIcon> {
	@Override
	public FontAwesomeIcon decodeIcon(String encodedIconString, IconDecoderContext context) {
		int dotIndex = encodedIconString.indexOf('.');

		if (dotIndex < 0) {
			return FontAwesomeIcon.forName(encodedIconString);
		} else {
			String iconName = encodedIconString.substring(0, dotIndex);
			FontAwesomeIconStyle style = FontAwesomeIconStyle.decode(encodedIconString.substring(dotIndex + 1));
			return FontAwesomeIcon.forName(iconName).withStyle(style);
		}
	}
}
