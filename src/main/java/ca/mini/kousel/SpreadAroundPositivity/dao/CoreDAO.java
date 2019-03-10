package ca.mini.kousel.SpreadAroundPositivity.dao;

import ca.mini.kousel.SpreadAroundPositivity.entity.*;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CoreDAO extends MongoRepository<spreadaroundpositivity, String>{
	spreadaroundpositivity findByUsername(String username);
}
