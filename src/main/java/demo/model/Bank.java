package demo.model;

import java.util.ArrayList;
import java.util.List;

public class Bank {

    private List<Loan> required = new ArrayList<>();
    private List<Loan> processed = new ArrayList<>();
    private boolean hasWork;

    public boolean getHasWork() {
        return hasWork;
    }

    public void setHasWork(boolean hasWork) {
        this.hasWork = hasWork;
    }

    public List<Loan> getRequired() {
        return required;
    }

    public void addLoanToQueue(Loan loan) {

        required.add(loan);
    }

    public List<Loan> getProcessed() {
        return processed;
    }

    public void addLoanToProcessed(Loan loan) {
        processed.add(loan);
    }

}
