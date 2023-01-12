package lisp;
import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.SExpression;
import main.lisp.parser.terms.TAtom;
import main.lisp.parser.terms.Atom;
import main.lisp.parser.terms.DecimalAtom;
import main.lisp.parser.terms.NilAtom;

public class LessThanOrEqualToEvaluator implements Evaluator{


	@Override
	public SExpression eval(SExpression expr, Environment env) {
		// TODO Auto-generated method stub
		expr = expr.getTail();
		if((long) ((Atom) expr.getHead().eval(env)).getValue() <= (long) ((Atom) expr.getTail().getHead().eval(env)).getValue()) return main.lisp.parser.terms.TAtomicExpressionFactory.newInstance();
		else return main.lisp.parser.terms.NilAtomicExpressionFactory.newInstance();
		
	}

}
