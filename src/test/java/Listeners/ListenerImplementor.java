package Listeners;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.TestCaseFinished;
import io.cucumber.plugin.event.TestCaseStarted;


public class ListenerImplementor implements ConcurrentEventListener {

    @Override
    public void setEventPublisher(EventPublisher eventPublisher) {
        eventPublisher.registerHandlerFor(TestCaseStarted.class, this::TestCaseStarted);
        eventPublisher.registerHandlerFor(TestCaseFinished.class, this::TestCaseFinished);

    }

    private void TestCaseStarted(TestCaseStarted event) {
        System.out.println("TestCaseStarted: " + event.getTestCase().getName());
    }

    private void TestCaseFinished(TestCaseFinished event) {
        System.out.println("TestCaseFinished: " + event.getTestCase().getName());
        System.out.println("Result: " + event.getResult().getStatus());
    }
}
