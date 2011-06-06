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
	
	private def translate_race(stringXML : String) =
	{
		val nodes = this translate_string stringXML
		val place = (nodes \\ "RaceName").text
		val date = (nodes \\ "Date").text
		val time = (nodes \\ "Time").text
		
		List(place, date + " @ " + time)
	}	
	
	private def translate_results(stringXML : String) =
	{
		val nodes = this translate_string stringXML
		
		List("Results")
	}
	
	private def ask_for(params : String) = 
	{
		val url = "http://ergast.com/api/f1/" + params + ".xml"
		io.Source.fromURL(url).mkString
	}
	
	private def translate_string(string : String) =
	{
		XML loadString string
	}
}