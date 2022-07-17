import logic.SchemeShell;
import logic.SimpleInput;
import logic.NAND;
import logic.Scheme;
import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogicTests {

    static class AssertInput extends SimpleInput {
        boolean desiredValue;
        boolean filled = false;

        public AssertInput(boolean desiredValue) {
            super(null);
            this.desiredValue = desiredValue;
        }

        @Override
        public void setValue(boolean value) {
            assertEquals(value, desiredValue);
            filled = true;
        }

        public boolean isFilled(){
            return filled;
        }
    }

    static void checkTruthTable(Supplier<Scheme> schemeSupplier, Integer[][] truthTable){
        for(Integer[] row : truthTable){
            Scheme scheme = schemeSupplier.get();
            if(row.length != scheme.inputsCount() + scheme.outputsCount())
                throw new RuntimeException();
            AssertInput[] results = new AssertInput[scheme.outputsCount()];
            for(int i = 0; i < scheme.outputsCount(); ++i) {
                results[i] = new AssertInput(row[scheme.inputsCount() + i] == 1);
                results[i].connectOutput(scheme.getOutput(i));
            }
            for(int i = 0; i < scheme.inputsCount(); ++i)
                scheme.getInput(i).setValue(row[i] == 1);
            for(int i = 0; i < scheme.outputsCount(); ++i)
                assertTrue(results[i].isFilled());
        }
    }

    @Test
    public void testNAND(){
        checkTruthTable(NAND::new, new Integer[][]{
                {0, 0, 1},
                {0, 1, 1},
                {1, 0, 1},
                {1, 1, 0}});
    }

    @Test
    public void testShell(){
        checkTruthTable(() -> {
            SchemeShell shell = new SchemeShell(2, 1);
            NAND nand = new NAND();
            shell.getInput(0).connectInput(nand.getInput(0));
            shell.getInput(1).connectInput(nand.getInput(1));
            shell.getOutput(0).connectOutput(nand.getOutput(0));
            return shell;
        }, new Integer[][]{
                {0, 0, 1},
                {0, 1, 1},
                {1, 0, 1},
                {1, 1, 0}});
    }

    Supplier<Scheme> NOTSupplier = () -> {
        SchemeShell shell = new SchemeShell(1, 1);
        NAND nand = new NAND();
        shell.getInput(0).connectInput(nand.getInput(0));
        shell.getInput(0).connectInput(nand.getInput(1));
        shell.getOutput(0).connectOutput(nand.getOutput(0));
        return shell;
    };

    @Test
    public void testNOT(){
        checkTruthTable(NOTSupplier, new Integer[][]{
                {0, 1},
                {1, 0}});
    }

    @Test
    public void testAND(){
        checkTruthTable(() -> {
            SchemeShell shell = new SchemeShell(2, 1);
            Scheme nand = new NAND();
            Scheme not = NOTSupplier.get();
            shell.getInput(0).connectInput(nand.getInput(0));
            shell.getInput(1).connectInput(nand.getInput(1));
            not.getInput(0).connectOutput(nand.getOutput(0));
            shell.getOutput(0).connectOutput(not.getOutput(0));
            return shell;
        }, new Integer[][]{
                {0, 0, 0},
                {0, 1, 0},
                {1, 0, 0},
                {1, 1, 1},});
    }
}
