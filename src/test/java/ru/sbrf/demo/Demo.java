package ru.sbrf.demo;

import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;
import ru.sbrf.demo.api.DepositRequest;
import ru.sbrf.demo.impl.Case;
import ru.sbrf.demo.impl.Decision;
import ru.sbrf.demo.impl.MyDecisionMatrix;
import ru.sbrf.demo.impl.MyDecisionMatrixFactory;

import java.io.FileInputStream;

public class Demo {

    @Test
    @SneakyThrows
    public void execute_demo_test() {
        FileInputStream fileInputStream = new FileInputStream("src/test/resources/test.txt");
        MyDecisionMatrixFactory<Decision> factory = new MyDecisionMatrixFactory<Decision>(fileInputStream, Decision.class);
        MyDecisionMatrix<Case, Decision> matrix = (MyDecisionMatrix<Case, Decision>) factory.getMatrix(Case.class, Decision.class);

        Case aCase = new Case("RUB", 10000, 12);

        Assert.assertEquals(1, matrix.getDecision(aCase).size());
        fileInputStream.close();
    }
}
