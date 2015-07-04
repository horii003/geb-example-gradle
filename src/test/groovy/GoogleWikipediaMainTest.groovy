import geb.spock.GebReportingSpec
import GoogleHomePage
import GoogleResultsPage
import WikipediaPage

class GoogleWikipediaMainTest extends GebReportingSpec  {

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
