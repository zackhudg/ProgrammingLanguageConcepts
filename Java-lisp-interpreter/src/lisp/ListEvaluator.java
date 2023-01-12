package lisp;
import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.evaluator.basic.ConsEvaluator;
import main.lisp.parser.terms.ExpressionFactory;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;
import main.lisp.parser.terms.StringAtom;

public class ListEvaluator implements Evaluator {

	@Override
	public SExpression eval(SExpression expr, Environment env) {
		// TODO Auto-generated method stub
		expr = expr.getTail();
		SExpression tail = expr.getTail();
		if(tail instanceof NilAtom) return ExpressionFactory.newInstance(expr.getHead().eval(env), tail);
		return ExpressionFactory.newInstance(expr.getHead().eval(env), evalHelper(tail, env));

	}
	
	public SExpression evalHelper(SExpression expr, Environment env) {
		// TODO Auto-generated method stub
		//expr = expr.getTail();
		if(expr.getTail() instanceof NilAtom) return ExpressionFactory.newInstance(expr.getHead().eval(env), main.lisp.parser.terms.NilAtomicExpressionFactory.newInstance());
		return ExpressionFactory.newInstance(expr.getHead().eval(env), evalHelper(expr.getTail(), env));

	}
}
