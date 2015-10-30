import com.sun.tools.javac.util.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.spbstu.appmath.bazaliy.ExpBuilder;
import ru.spbstu.appmath.bazaliy.ExpTree;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by admin on 30/10/15.
 */
@RunWith(Parameterized.class)
public class CalcTest {
    private static final Object[][] TEST_DATA = {
            {"1+2+3",0.0,6.0},
            {"1/x + 1 ",2.0, 1.5},
            {"-1 + 2 - x",1.0,0.0},
            {"x*x - x +1.23 ",2.0, 3.23},
            {"150*x - 2/x +3.14",2.0,302.14 }
    };

    private String expression;
    private Double variable;
    private Double result;
    private static final Double epsilon = Math.pow(10, -15);

   // @Rule
   // public ExpectedException thrown= ExpectedException.none();

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return Arrays.asList(TEST_DATA);
    }

    public CalcTest(String expression, Double variable, Double result) {
        this.expression = expression;
        this.variable = variable;
        this.result = result;
    }
    @Test
    public void test() {
        try {
            ExpBuilder exp = new ExpBuilder(expression, true);
            ExpTree expTree = exp.getExp();
            Double countedValue = expTree.execute(variable);
            Assert.check(Math.abs(countedValue - result) < epsilon, "Wrong answer");
        } catch (Exception e) {
            System.err.print(e.getMessage());

        }
    }

}
