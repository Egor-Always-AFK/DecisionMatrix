package ru.sbrf.demo.impl;

import lombok.SneakyThrows;
import ru.sbrf.demo.api.DecisionMatrix;
import ru.sbrf.demo.api.DecisionMatrixFactory;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MyDecisionMatrixFactory<Decision> implements DecisionMatrixFactory {
    private List<Decision> matrixRow;

    @SneakyThrows
    public MyDecisionMatrixFactory(InputStream inputStream, Class<Decision> decisionType) {
        matrixRow = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            while (reader.ready()) {
                matrixRow.add(newRow(decisionType, reader.readLine()));
            }
        }
    }

    @SneakyThrows
    private Decision newRow(Class<Decision> decisionClass, String line) {
        String[] factors = line.split(";");
        Decision decision = newInstance(decisionClass);
        int index = 0;
        for (Field factorField : decisionClass.getDeclaredFields()) {
            factorField.setAccessible(true);
            factorField.set(decision, castHelper(factors[index], factorField.getType()));
            index++;
        }
        return decision;
    }

    private Object castHelper(String value, Class<?> type) {
        if (type.isAssignableFrom(Interval.class)) {
            String[] interval = value.split("-");
            return new Interval(Long.valueOf(interval[0]), Long.valueOf(interval[1]));
        } else if (type.isAssignableFrom(Long.class)) {
            return Long.valueOf(value);
        } else if (type.isAssignableFrom(String.class)) {
            return value;
        } else if (type.isAssignableFrom(Double.class)) {
            return Double.valueOf(value);
        } else {
            throw new IllegalArgumentException("Data type is not supported: " + type);
        }
    }

    @SneakyThrows
    private Decision newInstance(Class<Decision> decisionClass) {
        Constructor<Decision> decisionConstructor = decisionClass.getConstructor();
        return decisionConstructor.newInstance();
    }

    @Override
    public <Case, Decision> DecisionMatrix<Case, Decision> getMatrix(Class<Case> caseType, Class<Decision> decisionType) {
        return new MyDecisionMatrix<>(caseType, decisionType, (List<Decision>) matrixRow);
    }
}
