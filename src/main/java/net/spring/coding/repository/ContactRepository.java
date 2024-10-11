package net.spring.coding.repository;

import java.util.List;

public interface ContactRepository<T> {

/*  public List<Object[]> read(); */
    public T create(T object);
    public List<T> reads();
    public T readById(Long id);
    public void update(T object , Long id);
    public void delete(Long id);


}
