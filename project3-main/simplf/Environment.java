package simplf;

public class Environment {
    private final Environment enclosing;
    private final AssocList assocList;

    // Constructor for a global environment (no enclosing environment)
    public Environment() {
        this.enclosing = null;
        this.assocList = null;
    }

    // Constructor for a new environment with an enclosing parent environment
    public Environment(Environment enclosing) {
        this.enclosing = enclosing;
        this.assocList = null;
    }

    // Constructor for a new environment with a specific association list and enclosing environment
    private Environment(AssocList assocList, Environment enclosing) {
        this.assocList = assocList;
        this.enclosing = enclosing;
    }

    // Define a new variable in the current environment by creating a new association list node
    public Environment define(Token name, String varName, Object value) {
        return new Environment(new AssocList(varName, value, assocList), this);
    }
    
    

    // Retrieve the value of a variable from the environment, checking enclosing environments if needed
    public Object get(Token name) {
        for (AssocList current = assocList; current != null; current = current.next) {
            if (current.name.equals(name.lexeme)) {
                return current.value;
            }
        }

        // If not found in the current scope, check the enclosing environment
        if (enclosing != null) return enclosing.get(name);

        throw new RuntimeError(name, "Undefined variable '" + name.lexeme + "'.");
    }

    // Assign a new value to an existing variable, checking enclosing environments if needed
    public void assign(Token name, Object value) {
        for (AssocList current = assocList; current != null; current = current.next) {
            if (current.name.equals(name.lexeme)) {
                current.value = value;
                return;
            }
        }

        // If not found in the current scope, check the enclosing environment
        if (enclosing != null) {
            enclosing.assign(name, value);
            return;
        }

        throw new RuntimeError(name, "Undefined variable '" + name.lexeme + "'.");
    }
}
