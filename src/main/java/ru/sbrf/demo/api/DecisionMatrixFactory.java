package ru.sbrf.demo.api;


public interface DecisionMatrixFactory {

    <Case, Decision> DecisionMatrix<Case, Decision> getMatrix(Class<Case> caseType, Class<Decision> decisionType);
}
