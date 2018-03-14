import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.junit.Test;


public class TestProject {
	
	//instances for test cases
	FileHandler fhandle= new FileHandler();
	DeclarationCounter dcounter= new DeclarationCounter();
	ReferenceCounter rcounter=new ReferenceCounter();
	
	//Variables for test cases
	String basedir="C:\\Users\\Jocelyn\\Desktop\\Program";
	File javafile = new File (basedir+"\\"+"file.java");
	File notjavafile= new File (basedir+"\\"+"file.txt");
	ASTParser parser = ASTParser.newParser(AST.JLS8);
	
	//Tests for FileHandler
	@Test //ensure knows if a file is java file
	public void testfilecheckerjavafile() throws FileNotFoundException, IOException {
		boolean check= fhandle.CheckFile(javafile);
		assertEquals(true, check);
	}
	
	@Test //ensure knows if a file is not a java file
	public void testfilecheckernotjavafile() throws FileNotFoundException, IOException {
		boolean check= fhandle.CheckFile(notjavafile);
		assertEquals(false, check);
	}
	
	@Test //ensure file can get filecontent
	public void testgetfilecontent() throws IOException {
		try (PrintWriter out = new PrintWriter(notjavafile)) {
		    out.print("");
		    out.close();
		}
		String filecontent=fhandle.getFileContent(notjavafile);
		boolean samecontent= filecontent.equals("");
	    assertEquals(true, samecontent);
	}

	//Test for DeclarationCounter
	@Test //check counter is initially set to 0
	public void getInitialDcount() {
		int dcount=dcounter.getCount();
		assertEquals(0, dcount);
	}
	
	@Test //check counter can update
	public void updateDeclarationCounter() {
	parser.setKind(ASTParser.K_COMPILATION_UNIT);
	String source="public class B {}";
	parser.setSource(source.toCharArray());
	String type="B";
	CompilationUnit cu= (CompilationUnit) parser.createAST(null);
	dcounter.updateCounter(cu, type);
	int dcount=dcounter.getCount();
	assertEquals(1, dcount);
	}
	
	//Tests for ReferenceCounter
	@Test //check counter is initially set to 0
	public void getInitialRcount() {
		int rcount=rcounter.getcount();
		assertEquals(0, rcount);
	}
	
	@Test //check counter can update
	public void updateReferenceCounter() {
	parser.setKind(ASTParser.K_COMPILATION_UNIT);
	String source="public class fake {B b= new B();}";
	parser.setSource(source.toCharArray());
	String type="B";
	CompilationUnit cu= (CompilationUnit) parser.createAST(null);
	rcounter.updateCounter(cu, type);
	int rcount=rcounter.getcount();
	assertEquals(1, rcount);
	}
	
	//Test for class Typecounter
	@Test(expected=IllegalStateException.class) 
	public void testNoCommandlineargs() throws FileNotFoundException, IllegalStateException, IOException {
		String[] arg= {};
		TypeCounter.main(arg);
	}
	
	@Test(expected=IllegalStateException.class)
	public void testNotaDirectory() throws FileNotFoundException, IllegalStateException, IOException {
		String[] arg= {"not a directory", "whatever"};
		TypeCounter.main(arg);
	}
	
	//System tests
	@Test
	public void testTypethatisntreal() throws FileNotFoundException, IllegalStateException, IOException
	{
		String[] arg= {basedir, "This is not a type"};
		System.out.println("Test Case for fake type: ");
		TypeCounter.main(arg);
		
	}
	
	@Test  
	public void testrealtypewithdeclartionandreference() throws IllegalStateException, IOException
	{
		try (PrintWriter out = new PrintWriter(javafile)) {
		    out.print("public class B {} B b= new B();");
		    out.close();
		}
		String[] arg= {basedir, "B"};
		System.out.println("Test case for real declaration and reference: ");
		TypeCounter.main(arg); 
	}

}
