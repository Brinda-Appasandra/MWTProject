package com.mwt.html.repo;
 
import org.springframework.data.repository.CrudRepository;

import com.mwt.html.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}
