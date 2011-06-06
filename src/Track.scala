import scala.xml._

object Track
{	
	def ask_for_race() =
	{
		var data = this ask_for "current/next"
		this translate_race data
	}
	
	def ask_for_results() =
	{
		var data = this ask_for "current/last/results"
		this translate_results data
	}
	
	def translate_race(stringXML : String) =
	{
		val nodes = this translate_string stringXML
		val place = (nodes \\ "RaceName").text
		val date = (nodes \\ "Date").text
		val time = (nodes \\ "Time").text
		
		List(place, date + " @ " + time)
	}	
	
	def translate_results(stringXML : String) =
	{
		val nodes = this translate_string stringXML
		
		List("Results")
	}
	
	def ask_for(params : String) = 
	{
		val url = "http://ergast.com/api/f1/" + params + ".xml"
		io.Source.fromURL(url).mkString
	}
	
	def translate_string(string : String) =
	{
		XML loadString string
	}
}