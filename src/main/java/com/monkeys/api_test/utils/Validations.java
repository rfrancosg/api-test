package com.monkeys.api_test.utils;

import com.monkeys.api_test.exceptions.AttributeNotPresentException;


public class Validations {

    private static final Clause TRUE_CLAUSE = new Clause(true);
    private static final Clause FALSE_CLAUSE = new Clause(false);

    private Validations() {
    }

    public static Clause when(boolean condition) {
        return condition ? TRUE_CLAUSE : FALSE_CLAUSE;
    }
    public static class Clause {
        private final boolean conditionFailed;

        public Clause(boolean conditionFailed) {
            this.conditionFailed = conditionFailed;
        }

        public void throwIllegalArgument(String message) throws AttributeNotPresentException {
            if (conditionFailed) {
                throw new AttributeNotPresentException(message);
            }
        }

    }
}

