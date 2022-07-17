package logic;

public class SimpleInput implements Input {
    Output connectedOutput;
    Scheme scheme;
    boolean value;

    public SimpleInput(Scheme scheme){
        this.scheme = scheme;
    }

    public Scheme getScheme(){
        return scheme;
    }

    public void connectOutput(Output output) {
        if(connectedOutput != output)
            disconnectOutput();
        connectedOutput = output;
        output.connectInput(this);
    }

    public void disconnectOutput() {
        if(connectedOutput != null) {
            connectedOutput.disconnectInput(this);
            connectedOutput = null;
        }
    }

    public void setValue(boolean value){
        this.value = value;
        scheme.inputFilled();
    }

    public boolean getValue(){
        return value;
    }
}
