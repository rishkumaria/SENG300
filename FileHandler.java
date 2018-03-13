import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.eclipse.jdt.core.dom.CompilationUnit;


public class FileHandler {




//check if file is a java file
	public static boolean CheckFile (File file) throws FileNotFoundException, IOException
	{       //Extension desired is .java
		String wanted="java";
		//get the name of the file
		String fileName = file.getName();
		//check type
		String extension = "";

		int i = fileName.lastIndexOf('.');
		if (i > 0) {
		    extension = fileName.substring(i+1);
			//if type wanted ....
		if (extension.equals(wanted)) {
			System.out.println(fileName); //filler just testing, maybe call parser here
			return true;
		}
		}
		return false ;
	}
	
//returns a string version of a file
public String getFileContent (File file) throws FileNotFoundException, IOException
{
	BufferedReader br = new BufferedReader(new FileReader(file));
		StringBuilder sb = new StringBuilder();
	String line = br.readLine();
	while (line != null)
	{
		sb.append(line);
		sb.append(System.lineSeparator());
		line = br.readLine();
	}
	br.close();
	return sb.toString();
}
}
