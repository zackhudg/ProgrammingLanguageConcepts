package lisp;
import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.Atom;
import main.lisp.parser.terms.SExpression;

public class EvalEvaluator implements Evaluator{


	@Override
	public SExpression eval(SExpression expr, Environment env) {
		// TODO Auto-generated method stub
		expr = expr.getTail().getHead();
//		SExpression tailEvaled = eval(expr, env);
		return expr.eval(env).eval(env);
	}

}
