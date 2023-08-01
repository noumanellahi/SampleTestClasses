package com.mycompany.chromedevtoolos;

import org.jsoup.Jsoup;

import com.github.kklisura.cdt.launch.ChromeLauncher;
import com.github.kklisura.cdt.protocol.commands.Page;
import com.github.kklisura.cdt.protocol.commands.Runtime;
import com.github.kklisura.cdt.protocol.types.runtime.Evaluate;
import com.github.kklisura.cdt.services.ChromeDevToolsService;
import com.github.kklisura.cdt.services.ChromeService;
import com.github.kklisura.cdt.services.types.ChromeTab;

/**
 * The following example dumps the index html from github.com.
 *
 * @author Kenan Klisura
 */
public class DumpHtmlFromPageExample {
	public static void main(String[] args) {
		// Create chrome launcher.
		final ChromeLauncher launcher = new ChromeLauncher();

		// Launch chrome either as headless (true) or regular (false).
		final ChromeService chromeService = launcher.launch(true);

		// Create empty tab ie about:blank.
		final ChromeTab tab = chromeService.createTab();

		// Get DevTools service to this tab
		final ChromeDevToolsService devToolsService = chromeService.createDevToolsService(tab);

		// Get individual commands
		final Page page = devToolsService.getPage();
		final Runtime runtime = devToolsService.getRuntime();

		// Wait for on load event
		page.onLoadEventFired(event -> {
			// Evaluate javascript
			Evaluate evaluation = runtime.evaluate("document.documentElement.outerHTML");
//			System.out.println(evaluation.getResult().getValue());
			
			System.out.println(Jsoup.parse(evaluation.getResult().getValue().toString()).select("#article_2BB3ED602260CC1762BF0D966EF804E8 > div > article:nth-child(1) > div"));
			

			// Close devtools.
			devToolsService.close();
		});

		// Enable page events.
		page.enable();

		// Navigate to github.com.
		page.navigate("https://www.paab.ca/resources.htm");

		// Wait until devtools is closed.
		devToolsService.waitUntilClosed();

		// Close tab.
		chromeService.closeTab(tab);
	}
}
