package it.eg.cookbook.error;

import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;

public class BatchFailureAnalyzer extends AbstractFailureAnalyzer<BatchException> {

    @Override
    protected FailureAnalysis analyze(Throwable rootFailure, BatchException cause) {
        String description = String.format("  An exception occurred: %s - %s", cause.getCode(), cause.getMessage() == null ? cause.getCode().getMessage() : cause.getCode().getMessage() + " - " + cause.getMessage());
        String action = "  Try: java -jar ./application-runner-advanced.jar --help";

        return new FailureAnalysis(description, action, cause);
    }

}