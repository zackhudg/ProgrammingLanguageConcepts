package lisp;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.interpreter.InterpreterModel;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;
import main.lisp.parser.terms.TAtom;


public class LoadEvaluator implements Evaluator{

	private boolean readAllLines(String path) {
		//Path wiki_path = Paths.get("C:/tutorial/wiki", "wiki.txt");
		List<String> lines = null;
	    Charset charset = Charset.forName("ISO-8859-1");
	    boolean flag = true;
	    path = path.replace("\"", "");
	    try {
	      lines = Files.readAllLines(Paths.get(path), charset);
	      InterpreterModel interpreter = main.lisp.interpreter.InterpreterModelSingleton.get();
	      for(String line : lines) {
	    	  interpreter.newInput(line);
	      }
	    } catch (IOException e) {
	        System.out.println(e);
	        flag = false;
	    }
	    return flag;
	}
	
	
	@Override
	public SExpression eval(SExpression expr, Environment env) {
		// TODO Auto-generated method stub
		expr = expr.getTail().getHead();
		if(readAllLines(expr.toString())) {
			return main.lisp.parser.terms.TAtomicExpressionFactory.newInstance();
		}else {
			return main.lisp.parser.terms.NilAtomicExpressionFactory.newInstance();
		}
		
	}

}
