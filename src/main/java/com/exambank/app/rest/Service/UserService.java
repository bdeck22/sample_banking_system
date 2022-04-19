// Service

package com.exambank.app.rest.Service;

import com.exambank.app.rest.Model.User;
import com.exambank.app.rest.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Adding Users to database
    public User save(User user) {
        return userRepository.save(user);
    }

    // Get the data where the user_name and password is equals to the parameters
    public User getByUserNameAndPassword(String userName, String password) {
        return userRepository.findByUserNameAndPassword(userName, password);
    }

    public User getByIdAndFirstNameAndLastName(Long id, String firstName, String lastName) {
        return userRepository.findByIdAndFirstNameAndLastName(id, firstName, lastName);
    }

}
