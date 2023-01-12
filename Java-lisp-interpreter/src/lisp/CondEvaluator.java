package lisp;
import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;

public class CondEvaluator implements Evaluator{

	@Override
	public SExpression eval(SExpression expr, Environment env) {
		//System.out.println("///////////" + expr.toString());

		SExpression exprList = expr.getTail();
		return evalHelper(exprList, env);
	}
	
	public SExpression evalHelper(SExpression exprList, Environment env) {
		// TODO Auto-generated method stub
		SExpression cond = exprList.getHead();
		SExpression clause = cond.getHead();
		SExpression result = cond.getTail();
		
		if(result.isNIL()) return clause.eval(env);
		
		if(!(clause.eval(env) instanceof NilAtom)) {
			//System.out.println("///////////" + result.toString());
			return result.getHead().eval(env);
		}
		else if(exprList.getTail() instanceof NilAtom) return main.lisp.parser.terms.NilAtomicExpressionFactory.newInstance();
		else return evalHelper(exprList.getTail(), env);
	}

//	public SExpression evalHelper(SExpression exprList, Environment env) {
//		// TODO Auto-generated method stub
//		if(exprList instanceof NilAtom) return main.lisp.parser.terms.NilAtomicExpressionFactory.newInstance();
//		if(exprList instanceof Atom) return exprList.eval(env);
//		SExpression cond = exprList.getHead();
//		SExpression clause = cond.getHead();
//		if(cond.getTail() instanceof NilAtom) return main.lisp.parser.terms.NilAtomicExpressionFactory.newInstance();
//		else if (cond.getTail() instanceof Atom) return cond.getTail().eval(env);
//		SExpression result = cond.getTail().getHead();
//		
//		if(!(clause.eval(env) instanceof NilAtom)) return result.eval(env);
//		else if(exprList.getTail() instanceof NilAtom) return main.lisp.parser.terms.NilAtomicExpressionFactory.newInstance();
//		else return evalHelper(exprList.getTail(), env);
//	}

}
