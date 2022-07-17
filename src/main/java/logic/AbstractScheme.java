package logic;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractScheme implements Scheme {

    List<Input> inputs = new ArrayList<>();
    List<Output> outputs = new ArrayList<>();

    int filledInputs = 0;

    @Override
    public Input createInput() {
        Input input = new SimpleInput(this);
        inputs.add(input);
        return input;
    }

    @Override
    public Output createOutput() {
        Output output = new SimpleOutput(this);
        outputs.add(output);
        return output;
    }

    @Override
    public Input getInput(int i){
        return inputs.get(i);
    }

    @Override
    public Output getOutput(int i){
        return outputs.get(i);
    }

    @Override
    public int inputsCount() {
        return inputs.size();
    }

    @Override
    public int outputsCount() {
        return outputs.size();
    }

    public void inputFilled(){
        ++filledInputs;
        if(filledInputs == inputs.size()) {
            calculate();
            filledInputs = 0;
        }
    }

    abstract protected void calculate();
}
