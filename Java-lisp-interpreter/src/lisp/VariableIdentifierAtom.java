package lisp;

import java.util.Optional;

import main.lisp.evaluator.Environment;
import main.lisp.parser.terms.IdentifierAtom;
import main.lisp.parser.terms.SExpression;
import main.lisp.scanner.tokens.Token;

public class VariableIdentifierAtom extends IdentifierAtom{

	public VariableIdentifierAtom(Token token) {
		super(token);
		// TODO Auto-generated constructor stub
	}

	public SExpression eval(Environment env) {
		return env.get(this).orElse(null);
	}

}
