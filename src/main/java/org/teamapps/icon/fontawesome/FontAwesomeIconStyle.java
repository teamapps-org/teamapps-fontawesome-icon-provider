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

import org.teamapps.common.format.Color;

public class FontAwesomeIconStyle {

    private final Color color;

    public FontAwesomeIconStyle(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public String applyStyle(String svg) {
        String styledSvg = "<svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 512 512\">\n";
        styledSvg += "<g style=\"fill:" + color.toHtmlColorString()  + "\">";
        styledSvg += svg;
        styledSvg += "\n</g></svg>";
        return styledSvg;
    }

    public String encode() {
        return color.toHtmlColorString();
    }

    public static FontAwesomeIconStyle decode(String string) {
        return new FontAwesomeIconStyle(Color.fromHtmlString(string));
    }
}
