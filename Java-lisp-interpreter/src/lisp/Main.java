package lisp;
import main.lisp.parser.terms.ExpressionFactory;
import main.lisp.evaluator.ExpressionEvaluatorFactory;
import main.lisp.evaluator.environment.EnvironmentFactory;
import  main.lisp.parser.terms.IdentifierAtomFactory;
public class Main{

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		OperationRegisterer.registerAll();
		ExpressionFactory.setClass(BasicExpression.class);
		EnvironmentFactory.setClass(EnvironmentImpl.class);
		IdentifierAtomFactory.setClass(VariableIdentifierAtom.class);
		ExpressionEvaluatorFactory.setClass(SExpressionWithLambdaApplicationEvaluator.class);
		main.Main.main(args);
	}
}
