package lisp;
import main.lisp.parser.terms.SExpression;
import main.lisp.parser.terms.Atom;
public class BasicExpression extends main.lisp.parser.terms.BasicExpression{

	public BasicExpression(SExpression head, SExpression tail) {
		// TODO Auto-generated constructor stub
		super(head,tail);
	}

	public boolean isList() {
		SExpression tail = this.getTail();
		if(tail.isNIL()) return true;
		else if (tail instanceof Atom) return false;
		else return tail.isList();
		
	}
	
	public String toStringAsSExpressionDeep() {
		SExpression head = this.getHead();
		SExpression tail = this.getTail();
		//if (this instanceof Atom) return ((Atom) this).getValue().toString();
		//if (head == null) return "nil";
		return "("+head.toStringAsSExpressionDeep() + " . " + tail.toStringAsSExpressionDeep() + ")";
	}
	
	public String toStringAsSExpression() {
		SExpression head = this.getHead();
		SExpression tail = this.getTail();
		//
		if (tail.toString() == "NIL") return "("+head.toString() + ")";
		return "("+head.toString() + " . " + tail.toString() + ")";
	}
	
	public String toStringAsListPublicHelper(SExpression head, SExpression tail) {
		String result = head.toString();
		if(!tail.isNIL()) result += " " + toStringAsListPublicHelper(tail.getHead(), tail.getTail());
		return result;
	}
	
	public String toStringAsList() {
		SExpression head = this.getHead();
		SExpression tail = this.getTail();
		//if (this instanceof Atom) return ((Atom) this).getValue().toString();
		return "(" + toStringAsListPublicHelper(head,tail) + ")";
	}
	

	public String toString() {
		if(this.isList()) return this.toStringAsList();
		else return this.toStringAsSExpression();
	}
}
