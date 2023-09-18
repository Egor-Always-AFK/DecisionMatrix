package ru.sbrf.demo;

import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;
import ru.sbrf.demo.impl.MyCase;
import ru.sbrf.demo.impl.MyDecision;
import ru.sbrf.demo.impl.MyDecisionMatrix;
import ru.sbrf.demo.impl.MyDecisionMatrixFactory;

import java.io.FileInputStream;

public class Demo {

    @Test
    @SneakyThrows
    public void execute_demo_test() {
        FileInputStream fileInputStream = new FileInputStream("src/test/resources/test.txt");
        MyDecisionMatrixFactory<MyDecision> factory = new MyDecisionMatrixFactory<MyDecision>(fileInputStream, MyDecision.class);
        MyDecisionMatrix<MyCase, MyDecision> matrix = (MyDecisionMatrix<MyCase, MyDecision>) factory.getMatrix(MyCase.class, MyDecision.class);

        MyCase aCase = new MyCase("RUB", 1000, 8);

        Assert.assertEquals(1, matrix.getDecision(aCase).size());
        fileInputStream.close();
    }
}
