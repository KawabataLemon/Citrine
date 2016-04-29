package chap7;

import chap7.FuncEvaluator.EnvEx;
import java.util.HashMap;

import chap6.Environment;

public class NestedEnv implements Environment {
	protected HashMap<String,Object> values;
	protected Environment outer;
	public NestedEnv() { this(null); }
	public NestedEnv(Environment e) {
		values = new HashMap<String,Object>();
		outer = e;
	}
	public void setOuter(Environment e) { outer = e; }

	@Override
	public Object get(String name) {
		Object v = values.get(name);
		// 見つからなければスコープ外から取得する
		if(v == null && outer != null) {
			return outer.get(name);
		} else {
			return v;
		}
	}
	public void putNew(String name, Object value) { values.put(name, value); }
	
	@Override
	public void put(String name, Object value) {
		Environment e = where(name);
		if(e == null) {						// どこにも変数が見つからなかったら
			e = this;						// 自分のスコープに取り込んで
		}
		((EnvEx)e).putNew(name,value);	// あたらしく変数を追加
	}
	
	public Environment where(String name) {
		if(values.get(name) != null) {
			return this;				// 自分のスコープに存在
		} else if (outer == null) {
			return null;				// 外部スコープなし、変数もなし
		} else {
			return ((EnvEx)outer).where(name);	// 外部スコープのwhereをよぶ
		}
	}
}
