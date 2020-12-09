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

import org.apache.commons.io.IOUtils;
import org.teamapps.icons.IconLoaderContext;
import org.teamapps.icons.IconResource;
import org.teamapps.icons.IconType;
import org.teamapps.icons.spi.IconLoader;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class FontAwesomeLoader implements IconLoader<FontAwesomeIcon> {
	@Override
	public IconResource loadIcon(FontAwesomeIcon icon, int size, IconLoaderContext context) {
		String svg = getSVG(icon.getName(), icon.getStyle());
		return svg != null ? new IconResource(svg.getBytes(StandardCharsets.UTF_8), IconType.SVG) : null;
	}

	private String getSVG(String iconName, FontAwesomeIconStyle style) {
		if (!iconName.endsWith(".svg")) {
			iconName += ".svg";
		}

		String path = "/org/teamapps/icon/fontawesome/" + iconName;

		try (InputStream inputStream = getClass().getResourceAsStream(path)) {
			if (inputStream == null) {
				return null;
			}
			String svg = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
			svg = style.applyStyle(svg);
			return svg;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
