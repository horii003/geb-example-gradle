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

def browserstackDriver = { def browserCaps ->
    def username = "osahorii1"
    assert username
    def accessKey =  "vUSTRxfRvv8z9dt7gqmE"
    assert accessKey
    def caps = [:]
    caps << browserCaps
    caps.put('os', 'Windows')
    caps.put('os_version', '8')
    caps.put('browser', 'firefox')
    caps.put('browser_version', '32')
    caps.put('name', 'BrowserStack firefox32 Windows8 TEST')
    driver = {
       new BrowserStackDriverFactory().create('firefox_Win8_32', username, accessKey, '', caps)
    }
}
//WebDriver create(String specification, String username, String password, String localId, Map<String, Object> capabilities = [:])

//def browserStackBrowser = "firefox_Win8_32"
def browserStackBrowser = System.getProperty("geb.browserstack.browser")
if (browserStackBrowser) {
  def browserCaps = new Properties()
  browserCaps.load(new StringReader(browserStackBrowser.replaceAll(',','\n')))
  browserstackDriver(browserCaps)
}  else {

environments {
	// run via “./gradlew chromeTest”
	// See: http://code.google.com/p/selenium/wiki/ChromeDriver
	chrome {
		driver = { new ChromeDriver() }
	}
	// run via “./gradlew firefoxTest”
	// See: http://code.google.com/p/selenium/wiki/FirefoxDriver
	firefox {
		driver = { new FirefoxDriver() }
	}
    phantomJs {
        driver = { new PhantomJSDriver() }
    }
}

}

// To run the tests with all browsers just run “./gradlew test”

//baseUrl = "http://gebish.org"
