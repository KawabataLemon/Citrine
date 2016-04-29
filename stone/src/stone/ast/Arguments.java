package stone.ast;
import java.util.List;

//
// Arguments ( BNF def : expr { "," expr })
// 
public class Arguments extends Postfix {
	public Arguments(List<ASTree> c) { super(c); }
	public int size() { return numChildren(); }
}
