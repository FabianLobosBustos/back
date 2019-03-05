package Mingeso.Proyecto.utilities;

import Mingeso.Proyecto.model.Variable;
import Mingeso.Proyecto.utilities.ToPythonCodeStrategy;

public class VariableEnteraToPythonCode implements ToPythonCodeStrategy {

    @Override
    public String toPythonCode(Variable variable) {
        int number = (int)variable.getLowerNumberLimit() + (int)(Math.random() * ((variable.getUpperNumberLimit() - variable.getLowerNumberLimit()) + 1));
        return variable.getName() + " = " + String.valueOf(number) + System.lineSeparator();
    }
}
