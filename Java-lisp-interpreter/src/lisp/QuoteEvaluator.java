package lisp;
import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.SExpression;
import main.lisp.parser.terms.StringAtom;
import main.lisp.parser.terms.QuoteAtom;


public class QuoteEvaluator implements Evaluator {

	@Override
	public SExpression eval(SExpression expr, Environment env) {
		return expr.getTail().getHead();
	}

	
}
