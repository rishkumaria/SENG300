import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class DeclarationCounter {
	private int count=0;
	
	public int getCount(){
		return count;
	}
	
	
	public void updateCounter(CompilationUnit cu, String type) {
		
		cu.accept(new ASTVisitor() {
			// Count Declarations
			public boolean visit(TypeDeclaration node) {
				String qualifiedName = node.getName().getFullyQualifiedName();
				
				if (type.equals(qualifiedName)) {
					count++;
				}
				return true;
			}
		});
	}

}
