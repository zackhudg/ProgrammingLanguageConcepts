package lisp;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.ExpressionFactory;

import main.lisp.parser.terms.SExpression;

public class FuncallEvaluator implements Evaluator{

	public FuncallEvaluator() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public SExpression eval(SExpression expr, Environment env) {
		// TODO Auto-generated method stub
		return ExpressionFactory.newInstance(expr.getTail().getHead().eval(env), main.lisp.parser.terms.NilAtomicExpressionFactory.newInstance()).eval(env);
	}

}
