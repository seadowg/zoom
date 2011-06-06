object Zoom 
{
	def main(args : Array[String]) =
	{	
		var data : List[String] = null
		
	 	args(0) match 
		{
			case "next-race" => data = Track ask_for_race
			case "results" => data = Track ask_for_results
		} 
		
		Screen display data
	}
}