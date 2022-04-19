// Repository

package com.exambank.app.rest.Repository;

import com.exambank.app.rest.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    // Get the data where the user_name and password is equals to the parameters
    User findByUserNameAndPassword(String userName, String password);

    User findByIdAndFirstNameAndLastName(Long id, String firstName, String lastName);

}
