package ru.sbrf.demo.api;

import java.util.List;

/**
 *
 * @param <Case> тип класса условие
 * @param <Decision> тип класса решение
 */
public interface DecisionMatrix<Case, Decision> {
    List<Decision> getDecision(Case byCase);
}
