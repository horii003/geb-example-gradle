import geb.Module
import GoogleResultsPage

class GoogleSearchModule extends Module {

	// a parameterised value set when the module is included
	def buttonValue

	// the content DSL
	static content = {

		// name the search input control gfieldh, defining it with the jQuery like navigator
		field { $("input", name: "q") }

		// the search button declares that it takes us to the results page, and uses the
		// parameterised buttonValue to define itself
		button(to: GoogleResultsPage) {
			$("input", value: buttonValue)
		}
	}

}
