package com.example.firstapp.services.transaction;

import com.example.firstapp.models.transaction.Transaction;
import com.example.firstapp.models.transaction.TransactionRepository;
import com.example.firstapp.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class TransactionService {
    @Autowired
    private TransactionRepository repository;
    @Transactional(readOnly = true)
    public CustomResponse<List<Transaction>> getAll() {
        return new CustomResponse<>(
                this.repository.findAll(), false, 200, "OK"
        );
    }
    @Transactional(readOnly = true)
    public CustomResponse<Transaction> getOne(Long id) {
        boolean exists = this.repository.existsById(id);
        if (exists) {
            return new CustomResponse<>(
                    this.repository.findById(id).get(), false, 200, "OK"
            );
        } else {
            return new CustomResponse<>(
                    null, true, 400, "This transaction does not exists"
            );
        }
    }
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Transaction> insert (Transaction transaction){
        if(this.repository.existsById(transaction.getId()))
            return new CustomResponse<>(
                    null, true,400, "This transaction already exists"
            );
        return new CustomResponse<>(
                this.repository.saveAndFlush(transaction),
                false, 200, "OK"
        );
    }
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Transaction> delete (long id){
        boolean exists = this.repository.existsById(id);
        if (exists) {
            this.repository.deleteById(id);
            return new CustomResponse<>(
                    null, false, 200, "OK"
            );
        } else {
            return new CustomResponse<>(
                    null, true, 400, "This transaction does not exists"
            );
        }
    }
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Transaction> update (Transaction transaction){
        if(!this.repository.existsById(transaction.getId()))
            return new CustomResponse<>(
                    null, true,400, "This transaction does not exists"
            );
        return new CustomResponse<>(
                this.repository.saveAndFlush(transaction),
                false, 200, "OK"
        );
    }
}
