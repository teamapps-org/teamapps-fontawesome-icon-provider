package org.teamapps.icon.fontawesome;


import org.teamapps.server.jetty.embedded.TeamAppsJettyEmbeddedServer;

public class FontAwesomeIconTestServer {

	public static void main(String[] args) throws Exception {
		new TeamAppsJettyEmbeddedServer((sessionContext) -> {
			sessionContext.setDefaultStyleForIconClass(FontAwesomeIcon.class, FontAwesomeIconStyles.AMBER_600);
			sessionContext.showNotification(FontAwesomeIcon.BRANDS_ADOBE, "Hello!", null, false, 0, false);
			sessionContext.showNotification(FontAwesomeIcon.BRANDS_ANGULAR.withStyle(FontAwesomeIconStyles.RED_300), "Hello!", null, false, 0, false);
		}, 8082).start();
	}

}
