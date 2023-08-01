package com.mycompany.chromedevtoolos;

import com.github.kklisura.cdt.launch.ChromeArguments;
import com.github.kklisura.cdt.launch.ChromeLauncher;
import com.github.kklisura.cdt.services.ChromeService;

public class Launch {
	public static void main(String[] args) {
//		final ChromeLauncher launcher = new ChromeLauncher();
		
//		ChromeArguments chromeArguments = ChromeArguments.builder().noFirstRun(false).build();;
		
		ChromeArguments chromeArguments = ChromeArguments.builder()
				.noFirstRun(false)
	            .noDefaultBrowserCheck()
	            .disableBackgroundNetworking()
	            .disableBackgroundTimerThrottling()
	            .disableClientSidePhishingDetection()
	            .disableDefaultApps()
	            .disableExtensions()
	            .disableHangMonitor()
	            .disablePopupBlocking()
	            .disablePromptOnRepost()
	            .disableSync()
	            .disableTranslate()
	            .metricsRecordingOnly()
	            .safebrowsingDisableAutoUpdate()
	            .headless(true)
	            .disableGpu(true)
	            .hideScrollbars(true)
	            .muteAudio(true)
	            .build();
		
		System.out.println(chromeArguments.getNoFirstRun());
		System.out.println(chromeArguments.getDisableBackgroundNetworking());
		
		

	}
}
