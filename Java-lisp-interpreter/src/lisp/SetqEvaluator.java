package lisp;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.IdentifierAtomFactory;
import main.lisp.parser.terms.SExpression;
import main.lisp.scanner.tokens.TokenFactory;
import main.lisp.scanner.tokens.TokenType;

public class SetqEvaluator implements Evaluator{

	@Override
	public SExpression eval(SExpression expr, Environment env) {
		// TODO Auto-generated method stub
		//System.out.println("///////" + expr.toString());
		SExpression exprList = expr.getTail();
		SExpression head = exprList.getHead();
		SExpression tail = exprList.getTail();
		//System.out.println("head " + head.toString() + " tail " +tail.toString());
		env.assign(IdentifierAtomFactory.newInstance(TokenFactory.newInstance(TokenType.IDENTIFIER, head.toString())), tail.getHead().eval(env));
		return tail.getHead().eval(env);
	}

	
}
