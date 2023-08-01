package com.mycompany.url;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DomainName {
	public static void main(String[] args ) {
		try {
			List<String> customUserAgentSources = new ArrayList<>();
			customUserAgentSources.add("link.springer.com");
			customUserAgentSources.add("www.springer.com");
			customUserAgentSources.add("www.nature.com");
			customUserAgentSources.add("www.springeropen.com");
			customUserAgentSources.add("connect.springerpub.com");
			customUserAgentSources.add("www.springerpub.com");
			customUserAgentSources.add("www.scientificamerican.com");
			customUserAgentSources.add("www.biomedcentral.com");
			customUserAgentSources.add("www.ingentaconnect.com");
			customUserAgentSources.add("www.palgrave.com");
			customUserAgentSources.add("www.atlantis-press.com");
			customUserAgentSources.add("www.springeronline.com");
			customUserAgentSources.add("www.springerprofessional.de");
			customUserAgentSources.add("www.springermedizin.de");
			customUserAgentSources.add("bunsen.springernature.app");
			URL url = new URL("https://www.nature.com/articles/s41388-022-02542-0");
			
			if (customUserAgentSources.contains(url.getHost())) {
				System.out.println("SETTING CUSTOM USER AGENT FOR URL : " + url);
				
			} else {
				System.out.println("SETTING NORMAL USER AGENT FOR URL : " + url);
				
			}
			System.out.println(url.getHost());
		} catch(Exception ex) {
			ex.printStackTrace();
		}

	}

}
