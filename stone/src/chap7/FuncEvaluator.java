package chap7;
import java.util.List;

import chap6.BasicEvaluator;
import chap6.BasicEvaluator.ASTreeEx;
import chap6.BasicEvaluator.BlockEx;
import chap6.Environment;
import javassist.gluonj.Require;
import javassist.gluonj.Reviser;
import stone.StoneException;
import stone.ast.ASTree;
import stone.ast.Arguments;
import stone.ast.DefStmnt;
import stone.ast.ParameterList;
import stone.ast.Postfix;
import stone.ast.PrimaryExpr;

@Require(BasicEvaluator.class)
@Reviser public class FuncEvaluator {
	@Reviser public static interface EnvEx extends Environment {
		void putNew(String name,Object value);	// 変数の設定を行う
		Environment where(String name);			// 変数の存在する環境を取得する
		void setOuter(Environment e); 			// 外の空間を設定
	}
	@Reviser public static class DefStmntEx extends DefStmnt {
		public DefStmntEx(List<ASTree> c) { super(c); }
		public Object eval(Environment env) {
			((EnvEx)env).putNew(name(), new Function(parameters(),body(),env));
			return name();
		}
	}
	@Reviser public static class PrimaryEx extends PrimaryExpr {
		public PrimaryEx(List<ASTree> c) { super(c); }
		public ASTree operand() { return child(0); }
		public Postfix postfix(int nest) {
			return (Postfix)child(numChildren() - nest - 1);
		}
		public boolean hosPostfix(int nest) { return numChildren() - nest > 1; }
		public Object eval(Environment env) {
			return evalSubExpr(env,0);
		}
		public Object evalSubExpr(Environment env,int nest) {
			if(hosPostfix(nest))
			{
				Object target = evalSubExpr(env, nest + 1);
				return ((PostfixEx)postfix(nest)).eval(env,target);
			}
			else {
				return ((ASTreeEx)operand()).eval(env);
			}
		}
	}
	@Reviser public static abstract class PostfixEx extends Postfix {
		public PostfixEx(List<ASTree> c) { super(c); }
		public abstract Object eval(Environment env,Object value);
	}
	@Reviser public static class ArgumentsEx extends Arguments {
		public ArgumentsEx(List<ASTree> c) { super(c); }
		public Object eval(Environment callerEnv,Object value) {
			if(!(value instanceof Function)) {
				throw new StoneException("bad function",this);
			}
			
			Function func = (Function)value;
			ParameterList params = func.parameters();
			if(size() != params.size()) throw new StoneException("ba number of arguments",this);	// 引数の不一致
			
			Environment newEnv = func.makeEnv();
			int num = 0;
			for(ASTree a : this) {
				((ParamsEx)params).eval(newEnv,num++,((ASTreeEx)a).eval(callerEnv));
			}
			
			return ((BlockEx)func.body()).eval(newEnv);
		}
	}
	
	@Reviser public static class ParamsEx extends ParameterList {
		public ParamsEx(List<ASTree> c) { super(c); }
		public void eval(Environment env, int index ,Object value) {
			((EnvEx)env).putNew(name(index), value);
		}
	}
}