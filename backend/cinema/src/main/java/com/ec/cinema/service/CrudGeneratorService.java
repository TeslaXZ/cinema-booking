package com.ec.cinema.service;

import java.util.List;

public interface CrudGeneratorService<E,I>{
    List<E> findAll();
    E findById(I id);
    E create(E e);
    E update(E e);
    void delete(I id);

}
