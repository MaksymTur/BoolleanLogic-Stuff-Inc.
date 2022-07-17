package logic;

import java.util.ArrayList;
import java.util.List;

public class SchemeShell implements Scheme{
    List<InputOutput> inputs = new ArrayList<>();
    List<InputOutput> outputs = new ArrayList<>();

    public SchemeShell(int inputs, int outputs){
        for(int i = 0; i < inputs; ++i)
            createInput();
        for(int i = 0; i < outputs; ++i)
            createOutput();
    }

    @Override
    public InputOutput createInput() {
        InputOutput input = new InputOutput();
        inputs.add(input);
        return input;
    }

    @Override
    public InputOutput createOutput() {
        InputOutput output = new InputOutput();
        outputs.add(output);
        return output;
    }

    @Override
    public int inputsCount() {
        return inputs.size();
    }

    @Override
    public int outputsCount() {
        return outputs.size();
    }

    @Override
    public InputOutput getInput(int i) {
        return inputs.get(i);
    }

    @Override
    public InputOutput getOutput(int i) {
        return outputs.get(i);
    }

    @Override
    public void inputFilled() {}
}
