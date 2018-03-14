import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;


public class TypeCounter
{
	
	public static void main(String[] args) throws FileNotFoundException, IllegalStateException, IOException
	{
		//Check number of inputs 
		verifyinput(args); 
		//intialize counters 
		DeclarationCounter dcounter=new DeclarationCounter();
		ReferenceCounter rcounter=new ReferenceCounter();
		//store command line arguments
		String type= args[1];
		File directory = new File(args[0]);
		//create a parser
		ASTParser parser = ASTParser.newParser(AST.JLS8);
	    parser.setKind(ASTParser.K_COMPILATION_UNIT);
	    parser.setResolveBindings(true);
	    parser.setBindingsRecovery(true);
		Map options = JavaCore.getOptions();
		parser.setCompilerOptions(options);
		String path=args[0];
		String [] patharray= {path};
		parser.setEnvironment(patharray, patharray, null, false);
	     //check files in directory
		if (!(directory.isDirectory())) {
			throw new IllegalStateException("Path specified is not a directory");
		}
		//get all the files from a directory
		   File[] fileList = directory.listFiles();
		   //instantiate file checker
		   //find java files and parse
		   FileHandler fhandle=new FileHandler();
		   for (File file : fileList){
		       if (file.isFile()){
		    	boolean isjavafile;
		        isjavafile=fhandle.CheckFile(file);
		        if (isjavafile)
		        {
		        	parser.setUnitName(args[0]);
		        	String strfile;
		        	strfile=fhandle.getFileContent(file);
		        	parser.setSource(strfile.toCharArray()); 
		    	    CompilationUnit cu = (CompilationUnit)parser.createAST(null);
		    	    dcounter.updateCounter(cu, type);
		    	    rcounter.updateCounter(cu, type);
		    		}
		        }
		       } 
		   //outside for loop
		   int dcount=dcounter.getCount();
		   int rcount=rcounter.getcount();
		   System.out.println(type + "; Declarations found: " + dcount + "; References found: " + rcount + ".");
		   
		  
	}
	
		
	//Ensure user entered correct number of command line arguments
	//throws IllegalStateException if there are not two arguments
	private static void verifyinput(String[] args)throws IllegalStateException{
	   if (args.length!=2) {
	     throw new IllegalStateException("Usage: 'Program name' 'Directoy Path' 'Fully qualified java type'");
	   }
	   return;
	   }
	
	
}