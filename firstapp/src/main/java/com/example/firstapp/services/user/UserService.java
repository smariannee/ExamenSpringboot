package com.example.firstapp.services.user;

import com.example.firstapp.models.user.UserRepository;
import com.example.firstapp.models.user.User;
import com.example.firstapp.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository repository;
    @Transactional(readOnly = true)
    public CustomResponse<List<User>> getAll() {
        return new CustomResponse<>(
                this.repository.findAll(), false, 200, "OK"
        );
    }
    @Transactional(readOnly = true)
    public CustomResponse<User> getOne(Long id) {
        boolean exists = this.repository.existsById(id);
        if (exists) {
            return new CustomResponse<>(
                    this.repository.findById(id).get(), false, 200, "OK"
            );
        } else {
            return new CustomResponse<>(
                    null, true, 400, "This user does not exists"
            );
        }
    }
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<User> insert (User user){
        if(this.repository.existsById(user.getId()))
            return new CustomResponse<>(
                    null, true,400, "This user already exists"
            );
        return new CustomResponse<>(
                this.repository.saveAndFlush(user),
                false, 200, "OK"
        );
    }
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<User> delete (long id){
        boolean exists = this.repository.existsById(id);
        if (exists) {
            this.repository.deleteById(id);
            return new CustomResponse<>(
                    null, false, 200, "OK"
            );
        } else {
            return new CustomResponse<>(
                    null, true, 400, "This user does not exists"
            );
        }
    }
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<User> update (User user){
        if(!this.repository.existsById(user.getId()))
            return new CustomResponse<>(
                    null, true,400, "This user does not exists"
            );
        return new CustomResponse<>(
                this.repository.saveAndFlush(user),
                false, 200, "OK"
        );
    }
}
