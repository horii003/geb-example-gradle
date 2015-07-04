/*
	This is the Geb configuration file.
	
	See: http://www.gebish.org/manual/current/configuration.html
*/


import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.phantomjs.PhantomJSDriver

waiting {
	timeout = 2
}

//def browserStackBrowser = "firefox_Win8_32"
def browserStackBrowser = System.getProperty("geb.browserstack.browser")
if (browserStackBrowser) {
    driver = {
       def username = "osahorii1"
       assert username
       def accessKey = "vUSTRxfRvv8z9dt7gqmE"
       assert accessKey
       new BrowserStackDriverFactory().create(browserStackBrowser, username, accessKey)
    }
}

//environments {
	// run via “./gradlew chromeTest”
	// See: http://code.google.com/p/selenium/wiki/ChromeDriver
//	chrome {
//		driver = { new ChromeDriver() }
//	}
	// run via “./gradlew firefoxTest”
	// See: http://code.google.com/p/selenium/wiki/FirefoxDriver
//	firefox {
//		driver = { new FirefoxDriver() }
//	}
//    phantomJs {
//        driver = { new PhantomJSDriver() }
//    }
//}

// To run the tests with all browsers just run “./gradlew test”

baseUrl = "http://gebish.org"
