package com.gulukal.service;
import com.gulukal.dto.request.DoLoginRequestDto;
import com.gulukal.dto.request.RegisterRequestDto;
import com.gulukal.dto.response.DoLoginResponseDto;
import com.gulukal.mapper.UserMapper;
import com.gulukal.repository.IUserRepository;
import com.gulukal.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    IUserRepository iUserRepository;

    @Autowired
    UserMapper  userMapper;


    /**
     * Kullanıcıyı kayıt eder ve kayıtedilen kullanıcının id bilgisi alınarak geri döndürülür.
     * @param dto
     * @return
     */
    public User saveReturnUser(RegisterRequestDto dto){
        User user = userMapper.toUser(dto);
        iUserRepository.save(user);
        return user;
    }

    public void save(User user){
        iUserRepository.save(user);
    }

    public void update(User user){
        iUserRepository.save(user);
    }

    public void delete(User user){
        iUserRepository.delete(user);
    }

    public List<User> findAll(){
        return iUserRepository.findAll();
    }

    public DoLoginResponseDto findByUsernameAndPassword(DoLoginRequestDto dto){
        Optional<User> user = iUserRepository
                .findByUsernameAndPassword(dto.getUsername(), dto.getPassword());
        if (user.isPresent())
            return userMapper.toDoLoginResponseDto(user.get());
        return new DoLoginResponseDto();
    }

    public boolean isUser(String username,String password){
       Optional<User> user = iUserRepository.findByUsernameAndPassword(username, password);
       if (user.isPresent())
           return true;
        return false;
    }


}
