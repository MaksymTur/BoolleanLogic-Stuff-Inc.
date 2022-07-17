package logic;

public interface Input {
    Scheme getScheme();

    void connectOutput(Output output);

    void disconnectOutput();

    void setValue(boolean value);

    boolean getValue();
}
