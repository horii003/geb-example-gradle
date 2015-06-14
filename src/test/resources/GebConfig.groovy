/*
	This is the Geb configuration file.
	
	See: http://www.gebish.org/manual/current/configuration.html
*/


import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.phantomjs.PhantomJSDriver

import org.openqa.selenium.phantomjs.PhantomJSDriver
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.Dimension
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.ie.InternetExplorerDriver
import geb.driver.SauceLabsDriverFactory
import java.io.File
import org.apache.commons.lang.SystemUtils

//reports setting
//reportsDir = "bin/geb-reports"
// キャプチャー出力 true：失敗時のみ false：全部出力
//reportOnTestFailureOnly = true

waiting {
  timeout = 10
  retryInterval = 0.5
  presets {
        slow {
            timeout = 20
            retryInterval = 1
        }
        quick {
            timeout = 5
        }
    }
}

atCheckWaiting = true
baseNavigatorWaiting = true


def downloadDriver = { File file, String path ->
    if (!file.exists()) {
        def ant = new AntBuilder()
        ant.get(src: path, dest: 'driver.zip')
        ant.unzip(src: 'driver.zip', dest: file.parent)
        ant.delete(file: 'driver.zip')
        ant.chmod(file: file, perm: '700')
    }
}

driver = {
    def d = new PhantomJSDriver(new DesiredCapabilities())
    d.manage().window().setSize(new Dimension(1024, 768))
    d
}

def sauceDriver = { def browserCaps ->
    def username = "horii03"
    assert username
    def accessKey = "1dff5a82-1c94-4bec-bbe9-f37b0cb3ffa8"
    assert accessKey
    def caps = [:]
    caps << browserCaps
    caps.put('name', 'GebGoogleWikipediaTest')
    caps.put('build', "git rev-parse HEAD".execute().text)
    driver = {
       new SauceLabsDriverFactory().create(username, accessKey, caps)
    }
}

// This property comes from the gradle geb-saucelabs plugin
def sauceLabsBrowser = System.getProperty("geb.saucelabs.browser")
if (sauceLabsBrowser) {
  def browserCaps = new Properties()
  browserCaps.load(new StringReader(sauceLabsBrowser.replaceAll(',','\n')))
  sauceDriver(browserCaps)
} else {

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

baseUrl = "http://gebish.org"
