package stone.ast;

import java.util.Iterator;
import java.util.ArrayList;
import stone.Token;

public class ASTLeaf extends ASTree {
	private static ArrayList<ASTree> empty = new ArrayList<ASTree>();
	protected Token token;
	
	// コンストラクタ
	public ASTLeaf(Token t) { token = t; };
	
	@Override
	public ASTree child(int i) { throw new IndexOutOfBoundsException(); }	// 葉ノードなので子ノードを持たない

	@Override
	public int numChildren() { return 0;}

	@Override
	public Iterator<ASTree> children() { return empty.iterator(); }

	public String toString() { return token.getText(); }
	
	@Override
	public String location() { return "at line" + token.getLineNumber(); }
	
	public Token token() { return token; }
	

}
