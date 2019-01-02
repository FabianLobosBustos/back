package Mingeso.Proyecto.utilities;

import Mingeso.Proyecto.model.Variable;

public class ToPythonCodeContext {
    private ToPythonCodeStrategy strategy;

    public ToPythonCodeStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(ToPythonCodeStrategy strategy) {
        this.strategy = strategy;
    }

    public String toPythonCode(Variable variable){
       return strategy.toPythonCode(variable);
    }
}
