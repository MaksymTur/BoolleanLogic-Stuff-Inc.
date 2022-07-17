package logic;

public interface Output {
    Scheme getScheme();

    void connectInput(Input input);

    void disconnectInput(Input input);

    void distributeValue(boolean value);
}
