package com.example.groundtransport.services;

import com.example.groundtransport.repository.BaseRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class BaseService<T, ID extends Serializable> {
    protected final BaseRepository<T, ID> repository;

    protected BaseService(BaseRepository<T, ID> repository) {
        this.repository = repository;
    }

    public T create(T entity) {
        return repository.save(entity);
    }

    public T update(ID id, T entity) {
        if (!repository.existsById(id)) throw new RuntimeException("Entity not found!");
        return repository.save(entity);
    }

    public void delete(ID id) {
        repository.deleteById(id);
    }

    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    public List<T> findAll() {
        return repository.findAll();
    }
}

