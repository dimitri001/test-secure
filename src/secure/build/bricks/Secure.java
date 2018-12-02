package secure.build.bricks;

public class Secure {

	private static final String BRICK = "■■";
	private static final String HALF_BRICK = "■";
	private static final String MORTAR = "|";
	private static final String RETURN = System.lineSeparator();
	
		public static void main(String[] args){
		
			System.out.println("buildAWall(\"5\", \"5\")");
			System.out.println(buildAWall("5", "5"));
			System.out.println("");
			
			System.out.println("buildAWall(\"10\", \"10\")");
			System.out.println(buildAWall("10", "10"));
			
			System.out.println("");
			
			System.out.println("buildAWall(\"eight\", \"[3]\")");
			System.out.println(buildAWall("eight", "[3]"));
			System.out.println("");
			
			System.out.println("buildAWall(\"12\", \"-4\")");
			System.out.println(buildAWall("12", "-4"));
			System.out.println("");
			
			System.out.println("buildAWall(\"123\", \"987\")");
			System.out.println(buildAWall("123", "987"));
			System.out.println("");
			
			System.out.println("buildAWall(\"3\", \"strNBricks\")");
			System.out.println(buildAWall("3", "strNBricks"));
			System.out.println("");
			
			System.out.println("buildAWall(\"2\", \"2\")");
			System.out.println(buildAWall("2", "2"));
			System.out.println("");
		}
	
		public static String buildAWall(String strNRows, String strNBricks){
			
			String result= "";
			
			WrapInteger mutNRows = new WrapInteger();
			WrapInteger mutNBricks = new WrapInteger();
			WrapString mutResult = new WrapString();
			Boolean validInput = argsValidation(mutNRows, mutNBricks, strNRows, strNBricks, mutResult);
			
			if (validInput){
				String wall = "";
				Boolean wallCompleted = false;
				Integer rowCounter =0;
				WrapInteger mutRowCounter = new WrapInteger();
				//WrapInteger mutNBricks = new WrapInteger();
				
				mutRowCounter.setValue(rowCounter);
				//mutNBricks.setValue(nBricks);
				
				while (! wallCompleted){
					//add row ODD
					if (mutRowCounter.getValue() < mutNRows.getValue()){
					 wall = addRowOdd(wall, mutRowCounter, mutNBricks);
					}else {
						wallCompleted = true;
						break;
					}
					//add row EVEN
					if (mutRowCounter.getValue() < mutNRows.getValue()){
						wall = addRowEven(wall, mutRowCounter, mutNBricks);
					}else {
						wallCompleted = true;
						break;
					}
				}
				
				result = wall;

			}else{
				
				result = mutResult.getValue();
			}
			
			return result;
			
		}
		
		public static String addRowEven(String wall, WrapInteger rowCounter, WrapInteger mutNBricks){
			
			String row ="";
			for (int i=0; i< mutNBricks.getValue()-1;i++){
				row = row.concat(BRICK);
				row = row.concat(MORTAR);
			}
			row = HALF_BRICK.concat(MORTAR).concat(row).concat(HALF_BRICK);
			row = row.concat(RETURN);
			
			wall = row.concat(wall);
			rowCounter.setValue(rowCounter.getValue()+1);
			
			return wall;
		}
		
		public static String addRowOdd(String wall, WrapInteger rowCounter, WrapInteger mutNBricks){
			
			String row ="";
			for (int i=0; i< mutNBricks.getValue();i++){
				row = row.concat(BRICK);
				if(i!= mutNBricks.getValue() -1 ){
					row = row.concat(MORTAR);
				}
			}
			if(rowCounter.getValue()!= 0){
				row = row.concat(RETURN);
			}
			wall = row.concat(wall);
			rowCounter.setValue(rowCounter.getValue()+1);
			
			return wall;
		}

		public static Boolean argsValidation(WrapInteger mutNRows, WrapInteger mutNBricks,
				String strNRows, String strNBricks, WrapString mutResult){
				String result = "";
				Boolean validInput = false;
			try {
				mutNRows.setValue(Integer.valueOf(strNRows));
				mutNBricks.setValue(Integer.valueOf(strNBricks));
				
				if( mutNRows.getValue() == null || mutNRows.getValue() <=0 
					|| mutNBricks.getValue() == null || mutNBricks.getValue() <=0){
					result = "None";
				}else{
					
					Integer totalBricks = mutNRows.getValue() * mutNBricks.getValue();
					if (totalBricks > 10000){
						result= "Naah, too much...here's my resignation.";
					}else{
						validInput = true;
						
					}
				}
				
			} catch (Exception e) {
				//System.out.println("error - "+e);
				System.out.println("error");
				result =  "None - ".concat("Invalid Input");
			}
			
			mutResult.setValue(result);			
			return validInput;
		}
	
}
