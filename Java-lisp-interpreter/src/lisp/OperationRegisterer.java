package lisp;
import main.lisp.evaluator.BuiltinOperationManagerSingleton;

public class OperationRegisterer implements main.lisp.evaluator.OperationRegisterer{

	public static void registerQuote() {
		BuiltinOperationManagerSingleton.get().registerEvaluatorIfNew("quote", new QuoteEvaluator());
	}
	
	public static void registerEval() {
		BuiltinOperationManagerSingleton.get().registerEvaluatorIfNew("eval", new EvalEvaluator());
	}
	
	public static void registerLoad() {
		BuiltinOperationManagerSingleton.get().registerEvaluatorIfNew("load", new LoadEvaluator());
	}
	
	public static void registerCond() {
		BuiltinOperationManagerSingleton.get().registerEvaluatorIfNew("cond", new CondEvaluator());
	}
	
	public static void registerLessThan() {
		BuiltinOperationManagerSingleton.get().registerEvaluatorIfNew("<", new LessThanEvaluator());
	}
	
	public static void registerGreaterThan() {
		BuiltinOperationManagerSingleton.get().registerEvaluatorIfNew(">", new GreaterThanEvaluator());
	}
	
	public static void registerLessThanOrEqualTo() {
		BuiltinOperationManagerSingleton.get().registerEvaluatorIfNew("<=", new LessThanOrEqualToEvaluator());
	}
	
	public static void registerGreaterThanOrEqualTo() {
		BuiltinOperationManagerSingleton.get().registerEvaluatorIfNew(">=", new GreaterThanOrEqualToEvaluator());
	}
	
	public static void registerAnd() {
		BuiltinOperationManagerSingleton.get().registerEvaluatorIfNew("and", new AndEvaluator());
	}
	
	public static void registerOr() {
		BuiltinOperationManagerSingleton.get().registerEvaluatorIfNew("or", new OrEvaluator());
	}
	
	public static void registerNot() {
		BuiltinOperationManagerSingleton.get().registerEvaluatorIfNew("not", new NotEvaluator());
	}

	public static void registerList() {
		BuiltinOperationManagerSingleton.get().registerEvaluatorIfNew("list", new ListEvaluator());
	}
	
	public static void registerSetq() {
		BuiltinOperationManagerSingleton.get().registerEvaluatorIfNew("setq", new SetqEvaluator());
	}
	
	public static void registerLambda() {
		BuiltinOperationManagerSingleton.get().registerEvaluatorIfNew("lambda", new LambdaEvaluator());
	}
	
	public static void registerFuncall() {
		BuiltinOperationManagerSingleton.get().registerEvaluatorIfNew("funcall", new FuncallEvaluator());
	}
	
	public static void registerAll() {
		registerQuote();
		registerEval();
		registerLoad();
		registerCond();
		registerLessThan();
		registerGreaterThan();
		registerLessThanOrEqualTo();
		registerGreaterThanOrEqualTo();
		registerAnd();
		registerOr();
		registerNot();
		registerList();
		registerSetq();
		registerLambda();
		registerFuncall();
	}
	
	@Override
	public void registerOperations() {
		// TODO Auto-generated method stub
		registerAll();
	}

}
