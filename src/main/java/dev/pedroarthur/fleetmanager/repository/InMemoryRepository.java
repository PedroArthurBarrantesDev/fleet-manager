package dev.pedroarthur.fleetmanager.repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.ToLongFunction;

public abstract class InMemoryRepository<T> {

    private final Map<Long, T> items = new LinkedHashMap<>();
    private final ToLongFunction<T> idOf;

    protected InMemoryRepository(ToLongFunction<T> idOf) {
        this.idOf = idOf;
    }

    public void register(T item) {
        Objects.requireNonNull(item, "item must not be null");

        long id = idOf.applyAsLong(item);

        if (items.containsKey(id)) {
            throw new IllegalArgumentException("id " + id + " is already registered");
        }

        items.put(id, item);
    }

    public Optional<T> findById(long id) {
        return Optional.ofNullable(items.get(id));
    }

    public List<T> findAll() {
        return List.copyOf(items.values());
    }
}
