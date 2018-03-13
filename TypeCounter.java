import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.jdt.core.ICompilationUnit;
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
		        	fhandle.getFileContent(file);
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
	
	
	public static CompilationUnit parse(String file) 
	{
	    ASTParser parser = ASTParser.newParser(AST.JLS8);

	    parser.setKind(ASTParser.K_COMPILATION_UNIT);
	    parser.setSource(file.toCharArray());

	    CompilationUnit cu = (CompilationUnit)parser.createAST(null);
	    return cu;
	}




	public int declarationCounter(AST ast)
	{	int count=0;
		
		
		
	   return count;
	}

	public boolean visit(VariableDeclarationStatement node) 
	{
		for (Iterator iter = node.fragments().iterator(); iter.hasNext();) {
			VariableDeclarationFragment fragment = (VariableDeclarationFragment) iter.next();
			// ... store these fragments somewhere
		}
		return false; // prevent that SimpleName is interpreted as reference
	}
	
}











