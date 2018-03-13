import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;



public class TypeCounter
{
	
	public static void main(String[] args) throws FileNotFoundException, IllegalStateException, IOException
	{
		verifyinput(args); 
		File directory = new File(args[0]);
		//create a parser
		ASTParser parser = ASTParser.newParser(AST.JLS8);
	    parser.setKind(ASTParser.K_COMPILATION_UNIT);
	    parser.setResolveBindings(true);
	    parser.setBindingsRecovery(true);
		Map options = JavaCore.getOptions();
		parser.setCompilerOptions(options);
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
		        	String filename= file.getName();
		        	parser.setUnitName(filename);
		        	String strfile;
		        	strfile=fhandle.getFileContent(file);
		        	parser.setSource(strfile.toCharArray());
		        	String filepath= fhandle.getFilePath(args[0], file);
		        	String [] patharray= {filepath};
		        	String classpath= args[0];
		        	String [] arrayclasspath= {classpath};
		        	//parser.setEnvironment(arrayclasspath, patharray, null, false);
		    	    CompilationUnit cu = (CompilationUnit)parser.createAST(null);
		    	    if (cu.getAST().hasBindingsRecovery()) {
		    			System.out.println("Binding activated.");
		    		}
		        }
		       }
		   }
		  
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











