package lisp;
import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;
import main.lisp.parser.terms.TAtom;

public class NotEvaluator implements Evaluator {

	@Override
	public SExpression eval(SExpression expr, Environment env) {
		// TODO Auto-generated method stub
		expr = expr.getTail().getHead();
		if(expr instanceof NilAtom) return main.lisp.parser.terms.TAtomicExpressionFactory.newInstance();
		else return main.lisp.parser.terms.NilAtomicExpressionFactory.newInstance();
	}



}
