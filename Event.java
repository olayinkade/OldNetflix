

public abstract class Event {
	/*********************************************************
	 * This method helps to process all events, that we need
	 * to handle
	 * @param line
	 */
	public static void processALLEvents(String line){
		String action = line.substring(0,3);
		String process = line.substring(4);
		String [] divided;
		if(action.equals("ACN")){

			Customer data = new Customer(process.substring(2)
					,Integer.parseInt( process.substring(0, 1)));
			Netflix.addCustomer(data);
		}
		else if(action.equals("ACQ")){
			DVD data = new DVD(process.substring(2) ,
					Integer.parseInt( process.substring(0, 1)));
			Netflix.addDVDtoN(data);
		}
		else if(action.equals("ADD")){
			divided  =delimiter(process,"FOR");
			Netflix.addDVDtoC(divided[0].trim(), divided[1].trim());


		}
		else if(action.equals("REM")){
			divided = delimiter(process,"FOR");
			Netflix.removeDVDfromC(divided[0].trim(), divided[1].trim());
		}
		else if(action.equals("RET")){
			divided = delimiter(process,"FROM");
			Netflix.returnDVD(divided[0].trim(), divided[1].trim());
		}
		else if(action.equals("LOS")){
			divided = delimiter(process,"FROM");
			Netflix.LoseDVD(divided[0].trim(), divided[1].trim());
		}
		else if(action.equals("RCN")){

			Netflix.cancelSub(new Customer(process,0));
		}


	}
	/******************************************************
	 *this function split a line(line) by a specific token(spilt)
	 * @param line
	 * @param split
	 * @return returns an array containing the parse string
	 */
	private static String[] delimiter(String line ,String split){
		String [] toreturn = new String [2];
		toreturn = line.split(split);

		return toreturn;
	}
}
