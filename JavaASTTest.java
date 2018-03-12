import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.CompilationUnit;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;


public class JavaASTTest
{
	public static String getFileContent (String filePath) throws FilNotFoundException, IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		StringBuilder sb = new StringBuilder();
		String line = br.readLine();
		while (line != null)
		{
			sb.append(line);
			sb.append(System.lineSeparator());
			line = br.readLine();
		}
		return sb.toString();
	}

	public static  void DirectoryHandler(File directory) throws FileNotFoundException, IOException
, IllegalStateException{
	if (!(directory.isDirectory())) {
		throw new IllegalStateException("Path specified not a directory");
	}
	}

	public static void main(String[] args)
	{
		verifyinput(args); 
		File directory = new File(args[0]);
		DirectoryHandler(directory);
		String filePath = "Point.java";
		ASTParser parser = ASTParser.newParser(AST.JLS8);
		char[] fileContent = getFileContent(filePath).toCharArray();
		parser.setSource(fileContent);
		CompilationUnit cu = (CompilationUnit) parser.createAST(null);

		cu.accept(new ASTVisitor()
		{
			Set names = new HashSet();

			public boolean visit(VariableDeclarationFragment node) 			//change this to type Java.lang.string
			{
				SimpleName name = node.getName();
				int lineNumber = cu.getLineNumber(name.getStartPosition());

				System.out.println("Name: " + name.toString());
				System.out.println("Line: " + lineNumber);
				System.out.println("----------------------------");
				return false;

			}
		});
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











