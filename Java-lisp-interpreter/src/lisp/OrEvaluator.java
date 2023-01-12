package lisp;
import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;
import main.lisp.parser.terms.TAtom;

public class OrEvaluator implements Evaluator {

	@Override
	public SExpression eval(SExpression expr, Environment env) {
		// TODO Auto-generated method stub
		expr = expr.getTail();
		return evalHelper(expr, env);
	}

	public SExpression evalHelper(SExpression expr, Environment env) {
		if(expr instanceof NilAtom) return main.lisp.parser.terms.NilAtomicExpressionFactory.newInstance();
		else if (!(expr.getHead().eval(env) instanceof NilAtom)) return expr.getHead().eval(env);
		else return evalHelper(expr.getTail(), env);
	}

}
