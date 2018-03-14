import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;



public class FileHandler {




//check if file is a java file
	public boolean CheckFile (File file) throws FileNotFoundException, IOException
	{       //Extension desired is .java
		String wanted="java";
		//get the name of the file
		String fileName = file.getName();
		//check type
		String extension = "";

		int i = fileName.lastIndexOf('.');
		if (i > 0) {
		    extension = fileName.substring(i+1);
			//if type wanted return true
		if (extension.equals(wanted)) {
			return true;
		}
		}
		return false ; //else return false
	}
	
//returns a string version of a file
public String getFileContent (File file) throws FileNotFoundException, IOException
{  //read file
	BufferedReader br = new BufferedReader(new FileReader(file));
	//Create string version
	StringBuilder sb = new StringBuilder();
	String line = br.readLine();
	while (line != null) //loop till all lines are read
	{
		sb.append(line);
		sb.append(System.lineSeparator());
		line = br.readLine(); //build string representation of lines
	}
	br.close(); //close the reader
	return sb.toString(); //return string builder as string
}

}
