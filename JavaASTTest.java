/**
* SENG 300 Iteration 1 
* @author Rishabh Kumaria, Nathan Ou, Jocelyn Donnelly 
* Version 1.0
* 
*/




import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.eclipse.jdt.core.dom.*;

public class JavaASTTest {
	public File[] javaFiles;
	private String type;
	public String typeSimple;
	public int dec;
	public int ref;

	public static void main(String[] args) {
		
		
		
		JavaASTTest ASTTest = new JavaASTTest(args[0], args[1]);
	
		
		
			String sourceCode="public class A { int A;  \\n b = 2; \\n ArrayList<Integer> al = new ArrayList<Integer>(); int j=1000; int i = 10;}";
			// if contents successfully read then parse the contents
			ASTTest.parse(sourceCode);
		
		
		// print result
		ASTTest.printResult();
		String a = "abc";
		int i = 20;
		float f = 20.2f;
		System.out.println(((Object)i).getClass().getName());
		System.out.println(((Object)f).getClass().getName());
		System.out.println(a.getClass().getName());
		
		return;
	}
	
	/**
	 * 
	 * Takes pathName and typeName 
	 * then initializes global variables
	 */
	public JavaASTTest(String pathName, String typeName) {
		
		type = typeName;
		dec = 0;
		ref = 0;
		
		String[] types = type.split("\\.");
		if (types.length >= 1) {
			typeSimple = types[types.length-1];
		} else {
			typeSimple = type;
		}
	}
	
	
	
	/**
	 * Parses given source code and increments dec and ref if necessary.
	 */
	@SuppressWarnings("deprecation")
	public void parse(String sourceCode) {
		ASTParser parser = ASTParser.newParser(AST.JLS8);

		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setSource(sourceCode.toCharArray());

		CompilationUnit cu = (CompilationUnit) parser.createAST(null);

		cu.accept(new ASTVisitor() {
			// Count Declarations
			public boolean visit(TypeDeclaration node) {
				String qualifiedName = node.getName().getFullyQualifiedName();
				
				if (typeSimple.equals(qualifiedName)) {
					dec++;
				}
				return true;
			}
			
			// Count References
			public boolean 	visit(FieldDeclaration node) {
				String qualifiedName = node.getType().toString();
				
				if (typeSimple.equals(qualifiedName)) {
					ref++;
				}
				return true;
			}
		});
	}
	
	/**
	 * 
	 * Prints the output string
	 */
	public void printResult() {
		System.out.println(type + "; Declarations found: " + dec + "; References found: " + ref + ".");
	}
}
