import geb.spock.GebReportingSpec
import GoogleHomePage
import GoogleResultsPage
import WikipediaPage
import geb.Browser

class GoogleWikipediaMainTest extends GebReportingSpec  {

def setupSpec() {
    println "Inside setupSpec()..."
    browser.config.autoClearCookies = true
}

def cleanupSpec() {
    println "Inside cleanupSpec()..."
    println "Quitting browser..."
    browser.quit()
}

	def "first result for wikipedia search should be wikipedia"() {
		given:
		to GoogleHomePage

		expect:
		at GoogleHomePage

		when:
		search.field.value("wikipedia")

		then:
		waitFor { at GoogleResultsPage }

		and:
		firstResultLink.text() == "Wikipedia"

		when:
		firstResultLink.click()

		then:
		waitFor { at WikipediaPage }
	}

}
