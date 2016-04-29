package chap8;
import java.lang.reflect.Method;
import stone.StoneException;
import stone.ast.ASTree;

//
// javaの関数を実行するためのオブジェクト
//
public class NativeFunction {
	protected Method method;
	protected String name;
	protected int numParams;	// 名前変じゃね
	
	public NativeFunction(String n,Method m) {
		name = n;
		method = m;
		numParams = m.getParameterTypes().length;
	}
	
	@Override public String toString() {
		return "<native:" + hashCode() + ">"; 
	}
	
	public int numOfParameters() { return numParams; }
	
	public Object invoke(Object[] args,ASTree tree) {
		try {
			return method.invoke(null, args);
		} catch (Exception e) {
			throw new StoneException("bad native function call: " + name,tree);
		}
	}
}
