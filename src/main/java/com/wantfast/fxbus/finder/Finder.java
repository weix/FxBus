package com.wantfast.fxbus.finder;


import com.wantfast.fxbus.entity.EventType;
import com.wantfast.fxbus.entity.ProducerEvent;
import com.wantfast.fxbus.entity.SubscriberEvent;

import java.util.Map;
import java.util.Set;

/**
 * Finds producer and subscriber methods.
 */
public interface Finder {

    Map<EventType, ProducerEvent> findAllProducers(Object listener);

    Map<EventType, Set<SubscriberEvent>> findAllSubscribers(Object listener);


    Finder ANNOTATED = new Finder() {
        @Override
        public Map<EventType, ProducerEvent> findAllProducers(Object listener) {
            return AnnotatedFinder.findAllProducers(listener);
        }

        @Override
        public Map<EventType, Set<SubscriberEvent>> findAllSubscribers(Object listener) {
            return AnnotatedFinder.findAllSubscribers(listener);
        }
    };
}
