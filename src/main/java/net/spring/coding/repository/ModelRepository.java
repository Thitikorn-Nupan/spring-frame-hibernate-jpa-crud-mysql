package net.spring.coding.repository;

import java.util.List;

public interface ModelRepository<T> {
    T create(T object);
    List<T> creates(List<T> object);
    List<T> reads();
    T readById(Long id);
    void update(T object , Long id);
    void delete(Long id);
}
