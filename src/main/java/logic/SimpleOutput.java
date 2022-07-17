package logic;

import java.util.HashSet;
import java.util.Set;

public class SimpleOutput implements Output{
    Set<Input> connectedInputs = new HashSet<>();
    Scheme scheme;

    public SimpleOutput(Scheme scheme){
        this.scheme = scheme;
    }

    public Scheme getScheme(){
        return scheme;
    }

    public void connectInput(Input input) {
        if(connectedInputs.contains(input))
            return;
        connectedInputs.add(input);
        input.connectOutput(this);
    }

    public void disconnectInput(Input input) {
        if(!connectedInputs.contains(input))
            return;
        connectedInputs.remove(input);
        input.disconnectOutput();
    }

    public void distributeValue(boolean value){
        for(Input input : connectedInputs)
            input.setValue(value);
    }
}
