package ru.sbrf.demo.impl;

import lombok.SneakyThrows;
import ru.sbrf.demo.annotation.Factor;
import ru.sbrf.demo.api.DecisionMatrix;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MyDecisionMatrix<Case, Decision> implements DecisionMatrix<Case, Decision> {
    private final List<Decision> decisions;

    private final Map<String, Field> requestFields = new HashMap<>();
    private final Map<String, Field> tariffFields = new HashMap<>();

    public MyDecisionMatrix(Class<Case> request, Class<Decision> tariff, List<Decision> decisions) {
        this.decisions = decisions;
        for (Field field : request.getDeclaredFields()) {
            if (field.isAnnotationPresent(Factor.class)) {
                field.setAccessible(true);
                requestFields.put(field.getName(), field);
            }
        }

        for (Field field : tariff.getDeclaredFields()) {
            if (field.isAnnotationPresent(Factor.class)) {
                field.setAccessible(true);
                tariffFields.put(field.getName(), field);
            }
        }
    }


    @SneakyThrows
    private boolean comparator(Decision row, Case byCase) {
        for (Map.Entry<String, Field> tariff : tariffFields.entrySet()) {
            Field factorField = tariff.getValue();
            String factorFieldName = tariff.getKey();
            Object tariffFactorValue = factorField.get(row);

            Field requestField = requestFields.get(factorFieldName);
            Object caseFieldValue = requestField.get(byCase);
            if (!compareField(tariffFactorValue, caseFieldValue)) {
                return false;
            }
        }
        return true;
    }

    private boolean compareField(Object tariffFactorValue, Object caseFieldValue) {
        if (tariffFactorValue instanceof Interval) {
            Interval interval = (Interval) tariffFactorValue;
            return interval.toRange((Comparable) caseFieldValue);
        }
        return Objects.equals(tariffFactorValue, caseFieldValue);
    }

    @Override
    public List<Decision> getDecision(Case byCase) {
        return decisions
                .stream()
                .filter(row -> comparator(row, byCase))
                .toList();
    }
}
