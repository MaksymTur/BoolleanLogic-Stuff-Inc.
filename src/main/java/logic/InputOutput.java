package logic;

import java.util.HashSet;
import java.util.Set;

public class InputOutput implements Input, Output{
    boolean value;
    Set<Input> connectedInputs = new HashSet<>();
    Output connectedOutput;

    @Override
    public void connectInput(Input input) {
        if(connectedInputs.contains(input))
            return;
        connectedInputs.add(input);
        input.connectOutput(this);
    }

    @Override
    public void disconnectInput(Input input) {
        if(!connectedInputs.contains(input))
            return;
        connectedInputs.remove(input);
        input.disconnectOutput();
    }

    @Override
    public void connectOutput(Output output) {
        if(connectedOutput != output)
            disconnectOutput();
        connectedOutput = output;
        output.connectInput(this);
    }

    @Override
    public void disconnectOutput() {
        if(connectedOutput != null) {
            connectedOutput.disconnectInput(this);
            connectedOutput = null;
        }
    }

    @Override
    public void setValue(boolean value) {
        this.value = value;
        distributeValue(value);
    }

    @Override
    public boolean getValue() {
        return value;
    }

    @Override
    public void distributeValue(boolean value) {
        for(Input input : connectedInputs)
            input.setValue(value);
    }

    @Override
    public Scheme getScheme() {
        return null;
    }
}
