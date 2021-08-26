package be.paulhalleux.event.resources;

import be.paulhalleux.event.Event;

public class TestEvent extends Event {

    private boolean passed;

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public boolean isPassed() {
        return passed;
    }

}
