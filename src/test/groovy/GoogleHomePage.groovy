import geb.Page
import GoogleSearchModule

class GoogleHomePage extends Page {

	// pages can define their location, either absolutely or relative to a base
	static url = "http://google.com/ncr"

	// gat checkersh allow verifying that the browser is at the expected page
	static at = { title == "Google" }

	static content = {
		// include the previously defined module
		search { module GoogleSearchModule, buttonValue: "Google Search" }
	}

}
