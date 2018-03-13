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



public class JavaASTTest
{
	//check if file is a java file
	public static void CheckFile (File file) throws FileNotFoundException, IOException
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
			String str = getFileContent(file);
			CompilationUnit cu=parse(str);
		}
		}
		return;
	}
	
	public static String getFileContent (File file) throws FileNotFoundException, IOException
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
	
	
public static  void DirectoryHandler(File directory) throws FileNotFoundException, IOException
, IllegalStateException{
	if (!(directory.isDirectory())) {
		throw new IllegalStateException("Path specified is not a directory");
	}
	//get all the files from a directory
    File[] fileList = directory.listFiles();
    for (File file : fileList){
        if (file.isFile()){
        	CheckFile(file);
        }
    }
    return;
}

	public static void main(String[] args) throws FileNotFoundException, IllegalStateException, IOException
	{
		verifyinput(args); 
		File directory = new File(args[0]);
		DirectoryHandler(directory);
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











