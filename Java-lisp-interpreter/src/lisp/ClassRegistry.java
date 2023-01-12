package lisp;
import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.IdentifierAtom;
//import main.lisp.evaluator.OperationRegisterer;
import main.lisp.parser.terms.SExpression;

public class ClassRegistry implements gradingTools.comp524f21.assignment6.ClassRegistryA6{

	public ClassRegistry() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Class<? extends Evaluator> getAndEvaluator() {
		// TODO Auto-generated method stub
		return AndEvaluator.class;
	}

	@Override
	public Class<? extends Evaluator> getCondEvaluator() {
		// TODO Auto-generated method stub
		return CondEvaluator.class;
	}

	@Override
	public Class<? extends main.lisp.evaluator.OperationRegisterer> getCustomOperationRegisterer() {
		// TODO Auto-generated method stub
		return OperationRegisterer.class;
	}

	@Override
	public Class<? extends Evaluator> getEvalEvaluator() {
		// TODO Auto-generated method stub
		return EvalEvaluator.class;
	}

	@Override
	public Class<? extends Evaluator> getGTEEvaluator() {
		// TODO Auto-generated method stub
		return GreaterThanOrEqualToEvaluator.class;
	}

	@Override
	public Class<? extends Evaluator> getGTEvaluator() {
		// TODO Auto-generated method stub
		return GreaterThanEvaluator.class;
	}

	@Override
	public Class<? extends Evaluator> getLTEEvaluator() {
		// TODO Auto-generated method stub
		return LessThanOrEqualToEvaluator.class;
	}

	@Override
	public Class<? extends Evaluator> getLTEvaluator() {
		// TODO Auto-generated method stub
		return LessThanEvaluator.class;
	}

	@Override
	public Class<? extends Evaluator> getListEvaluator() {
		// TODO Auto-generated method stub
		return ListEvaluator.class;
	}

	@Override
	public Class<? extends Evaluator> getLoadEvaluator() {
		// TODO Auto-generated method stub
		return LoadEvaluator.class;
	}

	@Override
	public Class<? extends Evaluator> getNotEvaluator() {
		// TODO Auto-generated method stub
		return NotEvaluator.class;
	}

	@Override
	public Class<? extends Evaluator> getOrEvaluator() {
		// TODO Auto-generated method stub
		return OrEvaluator.class;
	}

	@Override
	public Class<? extends Evaluator> getQuoteEvaluator() {
		// TODO Auto-generated method stub
		return QuoteEvaluator.class;
	}

	@Override
	public Class<? extends SExpression> getStringFormattingSExpression() {
		// TODO Auto-generated method stub
		return BasicExpression.class;
	}

	@Override
	public Class<?> getMain() {
		// TODO Auto-generated method stub
		return Main.class;
	}

	@Override
	public Class<? extends SExpression> getFunctionCallingSExpression() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<? extends Evaluator> getFunctionEvaluator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<? extends Evaluator> getLetEvaluator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<? extends Evaluator> getBasicFuncallEvaluator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<? extends VariableIdentifierAtom> getIdentifierAtomWithLookup() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<? extends SExpression> getLambdaCallingSExpression() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<? extends Evaluator> getLambdaEvaluator() {
		// TODO Auto-generated method stub
		return LambdaEvaluator.class;
	}

	@Override
	public Class<? extends Environment> getNestedLexicalEnvironment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<? extends Evaluator> getSetqEvaluator() {
		// TODO Auto-generated method stub
		return SetqEvaluator.class;
	}

	@Override
	public Class<? extends main.lisp.evaluator.OperationRegisterer> getStatefulOperationRegisterer() {
		// TODO Auto-generated method stub
		return null;
	}
}
