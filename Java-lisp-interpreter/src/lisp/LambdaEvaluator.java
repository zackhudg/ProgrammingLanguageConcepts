package lisp;

import java.util.ArrayList;
import java.util.List;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.evaluator.function.BasicLambda;
import main.lisp.evaluator.function.Lambda;
import main.lisp.evaluator.function.LambdaFactory;
import main.lisp.parser.terms.IdentifierAtom;
import main.lisp.parser.terms.ExpressionFactory;
import main.lisp.parser.terms.IdentifierAtomFactory;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.QuoteAtom;
import main.lisp.parser.terms.SExpression;
import main.lisp.scanner.tokens.TokenFactory;
import main.lisp.scanner.tokens.TokenType;

public class LambdaEvaluator implements Evaluator{

	public LambdaEvaluator() {
		// TODO Auto-generated constructor stub
	}

	public IdentifierAtom[] getParameterArray(SExpression expr, List<IdentifierAtom> list, Environment env) {
		if (expr instanceof NilAtom) return new IdentifierAtom[0];
		SExpression head = expr.getHead();
		list.add(IdentifierAtomFactory.newInstance(TokenFactory.newInstance(TokenType.IDENTIFIER, head.toString())));
		if(expr.getTail().isNIL()) return list.toArray(new IdentifierAtom[list.size()]);
		else return getParameterArray(expr.getTail(), list, env);
	}
	
//	public SExpression eval(Lambda lambda, SExpression expr, Environment env) {
//		
//		SExpression arguments = expr.getTail();
//		for(IdentifierAtom parameterName : lambda.getArgumentNames()) {
//			
//			env.put(parameterName, arguments.getHead().eval(env));
//			arguments = arguments.getTail();
//			System.out.println(parameterName.toString());
//		}
//		
//		//System.out.println(expr.toString());
//		return expr.getHead().getTail().getHead().eval(env);
//		
//	}

	
	public static SExpression eval(Lambda lambda, SExpression expr, Environment env) {
		SExpression arguments = expr.getTail();
		Environment childEnv = env.newChild();
		IdentifierAtom[] parameters = lambda.getArgumentNames();
		
		for(IdentifierAtom parameterName : parameters) {
			childEnv.assign(parameterName, arguments.getHead().eval(childEnv));
			arguments = arguments.getTail();
		}
		
		return lambda.eval(childEnv);
	}
	
	@Override
	public SExpression eval(SExpression expr, Environment env) {
		SExpression parameters = expr.getTail().getHead();
		SExpression lambdaBody = expr.getTail().getTail().getHead();
		IdentifierAtom[] parameterNames = getParameterArray(parameters, new ArrayList<IdentifierAtom>(), env);
		Lambda lambda = LambdaFactory.newInstance(parameterNames, ExpressionFactory.newInstance(lambdaBody, main.lisp.parser.terms.NilAtomicExpressionFactory.newInstance()));
		System.out.println(lambda.toString());
		return lambda;
		
		
		
		
		
		
		
		
		
		
		
		
		
//		// TODO Auto-generated method stub
//		//SExpression lambdaExpr = expr.getHead();
//		SExpression parameters = expr.getTail().getHead();
//		SExpression lambdaBody = expr.getTail().getTail().getHead();
////		SExpression arguments = expr.getTail();
//		IdentifierAtom[] parameterNames = getParameterArray(parameters, new ArrayList<IdentifierAtom>(), env);
////		System.out.println("// parameters = " + parameterNames[0] + " " + parameterNames[1]);
//		//System.out.println("// lambda body = " + lambdaBody.toString());
//
//		Lambda lambda = LambdaFactory.newInstance(parameterNames,new QuoteAtom(lambdaBody));
//		
//		//System.out.println("// lambda = " + lambda.toString());
//
////		SExpression arguments = expr.getTail();
////		for(IdentifierAtom parameterName : lambda.getArgumentNames()) {
////			env.put(parameterName, arguments.getHead().eval(env));
////			arguments = arguments.getTail();
////		}
//		
//		return lambda;
	}

}
