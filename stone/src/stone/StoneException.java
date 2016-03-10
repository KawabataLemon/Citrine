package stone;
import stone.ast.ASTree;

// 言語stoneの実行時例外
public class StoneException extends RuntimeException {
	public StoneException(String m) { super(m); };
	public StoneException(String m, ASTree t) {
		super(m + " " + t.location());
	}
}
