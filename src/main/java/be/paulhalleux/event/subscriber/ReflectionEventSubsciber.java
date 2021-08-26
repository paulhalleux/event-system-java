package be.paulhalleux.event.subscriber;

import be.paulhalleux.event.Event;
import be.paulhalleux.event.Listener;
import org.reflections.Reflections;

import java.util.Set;

public class ReflectionEventSubsciber implements IEventSubscriber {

    private final Reflections reflections;
    private final IEventSubscriber backSubsrciber;

    public ReflectionEventSubsciber(IEventSubscriber backSubsrciber, Reflections reflections) throws ReflectiveOperationException {
        this.backSubsrciber = backSubsrciber;
        this.reflections = reflections;
        handleReflection();
    }

    @Override
    public boolean subscribe(Listener listener) {
        return backSubsrciber.subscribe(listener);
    }

    @Override
    public void call(Event event) {
        backSubsrciber.call(event);
    }

    private void handleReflection() throws ReflectiveOperationException {
        Set<Class<? extends Listener>> items = reflections.getSubTypesOf(Listener.class);

        for (Class<? extends Listener> t : items) {
            Listener item = t.getDeclaredConstructor().newInstance();
            subscribe(item);
        }
    }

}
