package lisp;

import main.lisp.evaluator.BasicExpressionEvaluator;
import main.lisp.evaluator.BuiltinOperationManagerSingleton;
import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.IdentifierAtom;
import main.lisp.parser.terms.SExpression;
import main.lisp.evaluator.function.BasicLambda;
import main.lisp.evaluator.function.Lambda;


public class SExpressionWithLambdaApplicationEvaluator extends BasicExpressionEvaluator{

	public SExpressionWithLambdaApplicationEvaluator() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public SExpression eval(SExpression expr, Environment environment) {
		System.out.println(expr);
		//if (expr.getHead() instanceof Lambda)
		if (expr.getHead() instanceof VariableIdentifierAtom) {
			String operator = ((VariableIdentifierAtom)expr.getHead()).getValue();
			//System.out.println("//" + operator);
			Evaluator eval = BuiltinOperationManagerSingleton.get().getEvaluator(operator);
			if (eval == null) {
				throw new IllegalStateException("No evaluator registered for operator '" + operator + "'");
			}
			return eval.eval(expr, environment);
		}else {
			SExpression headEval = expr.getHead().eval(environment);
			if(headEval instanceof Lambda){
				//System.out.println("// INSTANCE OF LAMBDA");
				
				Environment childEnv = environment.newChild();
				
				return LambdaEvaluator.eval((Lambda)headEval, expr, childEnv);
				
			}else {
				throw new IllegalStateException("Expression does not start with an operator");
			}
		}
	}

}
