package logic;

public interface Scheme {
    Input createInput();
    Output createOutput();

    int inputsCount();
    int outputsCount();

    Input getInput(int i);

    Output getOutput(int i);

    void inputFilled();
}
