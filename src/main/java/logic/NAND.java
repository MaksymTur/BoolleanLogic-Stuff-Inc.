package logic;

public class NAND extends AbstractScheme{

    public NAND(){
        createInput();
        createInput();
        createOutput();
    }

    @Override
    protected void calculate() {
        boolean a = getInput(0).getValue();
        boolean b = getInput(1).getValue();
        getOutput(0).distributeValue(!(a&b));
    }
}
