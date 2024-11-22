package test.task.todolist.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Status {
        NEW,
        ACTIVE,
        COMPLETED,
        CANCELLED;

        @JsonCreator
        public static Status fromValue(String value) {
            return Status.valueOf(value.toUpperCase());
        }
    }