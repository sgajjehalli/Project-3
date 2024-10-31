package simplf;
 
import java.util.List;

class SimplfFunction implements SimplfCallable {

    SimplfFunction(Stmt.Function declaration, Environment closure) {
        throw new UnsupportedOperationException("TODO: implement functions");
    }

    public void setClosure(Environment environment) {
        throw new UnsupportedOperationException("TODO: implement functions");
    }

    @Override
    public Object call(Interpreter interpreter, List<Object> args) {
        throw new UnsupportedOperationException("TODO: implement functions");
    }

    @Override
    public String toString() {
        return "<fn >";
    }

}