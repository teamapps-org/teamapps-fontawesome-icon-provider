/*-
 * ========================LICENSE_START=================================
 * TeamApps Font Awesome Icon Provider
 * ---
 * Copyright (C) 2014 - 2019 TeamApps.org
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
import org.teamapps.common.format.Color;
import org.teamapps.icons.api.IconStyle;
import org.teamapps.icons.provider.SvgIconProvider;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FontAwesomeIconProvider implements SvgIconProvider<IconStyle> {

    public static final String LIBRARY_ID = "fontawesome";


    private Map<String, SingleColorStyle> styleById = new HashMap<>();
    private Map<String, String> svgByStyleAndName = new HashMap<>();

    public FontAwesomeIconProvider() {
        FontAwesomeIconStyles.getBaseStyles().forEach(this::addIconStyle);
    }

    public void addIconStyle(SingleColorStyle iconStyle) {
        styleById.put(iconStyle.getStyleId(), iconStyle);
    }

    public void addStyle(String styleId, Color color) {
        addIconStyle(new SingleColorStyle(styleId, color));
    }


    @Override
    public String getInnerSvg(IconStyle style, String iconName) {
        return null;
    }

    @Override
    public byte[] getIcon(String styleId, int size, String iconName) {
        String svg = getSVG(styleId, iconName);
        if (svg != null) {
            return svg.getBytes(StandardCharsets.UTF_8);
        } else {
            return null;
        }
    }

    private String getSVG(String styleId, String iconName) {
        if (!iconName.endsWith(".svg")) {
            iconName += ".svg";
        }
        SingleColorStyle iconStyle = styleById.get(styleId);
        if (iconStyle == null) {
            iconStyle = styleById.get(getDefaultDesktopStyle().getStyleId());
        }


        InputStream inputStream = getClass().getResourceAsStream("/org/teamapps/icon/fontawesome/" + iconName);
        if (inputStream == null) {
            return null;
        }
        try {
            String svg = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            svg = iconStyle.applyStyle(svg);
            return svg;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public String getIconLibraryId() {
        return LIBRARY_ID;
    }

    @Override
    public Set<Integer> getAvailableIconSizes() {
        return null;
    }

    @Override
    public Set<IconStyle> getAvailableIconStyles() {
        return new HashSet<>(styleById.values());
    }

    @Override
    public IconStyle getDefaultDesktopStyle() {
        return FontAwesomeIconStyles.BLUE_600;
    }

    @Override
    public IconStyle getDefaultMobileStyle() {
        return FontAwesomeIconStyles.BLUE_600;
    }

    @Override
    public IconStyle getDefaultSubIconStyle() {
        return FontAwesomeIconStyles.BLUE_600;
    }

}
