import scala.xml._
import scala.collection.mutable.ListBuffer

object Track
{	
	def ask_for_race() =
	{
		val data = this ask_for "next"
		this translate_race data
	}
	
	def ask_for_results() =
	{
		val data = this ask_for "last/results"
		this translate_results data
	}
	
	def ask_for_standings() =
	{
		val data = this ask_for "driverStandings"
		this translate_standings data
	}
	
	private def translate_race(stringXML : String) =
	{
		val nodes = this translate_string stringXML
		val place = (nodes \\ "RaceName").text
		val date = (nodes \\ "Date").text
		val time = (nodes \\ "Time").text
		
		List("", place, date + " @ " + time, "")
	}	
	
	private def translate_results(stringXML : String) =
	{	
		val nodes = this translate_string stringXML
		val list = new ListBuffer[String]()
		
		list append ""
		list append (nodes \\ "RaceName").text
		list append ""
		
		var i = 1
		(nodes \\ "FamilyName") foreach { 
			node => list append mk_pos(node.text, i); i += 1 }
		list append ""
		
		list toList
	}
	
	private def translate_standings(stringXML : String) =
	{
		
	}
	
	private def ask_for(params : String) = 
	{
		val url = "http://ergast.com/api/f1/current/" + params + ".xml"
		io.Source.fromURL(url).mkString
	}
	
	private def translate_string(string : String) =
	{
		XML loadString string
	}
	
	private def mk_pos(string : String, i : Int) =
	{
		i.toString() + ". " + string
	}
}