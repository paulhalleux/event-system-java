package be.paulhalleux.event;

import be.paulhalleux.event.resources.TestEvent;
import be.paulhalleux.event.subscriber.IEventSubscriber;
import be.paulhalleux.event.subscriber.MethodEventSubscriber;
import be.paulhalleux.event.subscriber.ReflectionEventSubsciber;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import static org.junit.jupiter.api.Assertions.*;

class ReflectionEventSubscriberTests {

    @Test
    void testSubscriberConstruction(){
        assertDoesNotThrow(() -> {
            IEventSubscriber subscriber = new MethodEventSubscriber();
            IEventSubscriber reflectionSubscriber = new ReflectionEventSubsciber(subscriber, new Reflections("be.paulhalleux.event.resources", SubTypesScanner.class));
        });
    }

    @Test
    void testSubscriberCallEvent(){
        try {
            IEventSubscriber subscriber = new MethodEventSubscriber();
            IEventSubscriber reflectionSubscriber = new ReflectionEventSubsciber(subscriber, new Reflections("be.paulhalleux.event.resources", SubTypesScanner.class));
            TestEvent event = new TestEvent();
            reflectionSubscriber.call(event);
            if(!event.isPassed()) assertTrue(false, "L'évenement n'a pas été envoyé à ses écouteurs.");
            else assertTrue(true);
        } catch (Exception ignored) {}
    }

}
