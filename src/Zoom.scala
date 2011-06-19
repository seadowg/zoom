object Zoom 
{
	def main(args : Array[String]) =
	{	
		var data : List[String] = null
		
	 	args(0) match 
		{
			case "next" => data = Track ask_for_race
			case "results" => data = Track ask_for_results
			//case "standings" => data = Track ask_for_standings
			
			case _ => data = List("'" + args(0) + "' is not a valid zoom command!")
		} 
		
		Screen display data
	}
}