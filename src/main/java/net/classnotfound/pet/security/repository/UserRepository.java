package net.classnotfound.pet.security.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import net.classnotfound.pet.security.domain.User;

public interface UserRepository extends CrudRepository<User, String>, Repository<User, String> {

}
