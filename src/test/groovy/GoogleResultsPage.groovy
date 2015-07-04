import geb.Page
import GoogleSearchModule

class GoogleResultsPage extends Page {

	static at = { title.endsWith "Google Search" }
	static content = {
		// reuse our previously defined module
		search { module GoogleSearchModule, buttonValue: "Search" }

		// content definitions can compose and build from other definitions
		results { $("li.g") }
		result { i -> results[i] }
		resultLink { i -> result(i).find("a") }
		firstResultLink { resultLink(0) }
	}

}
