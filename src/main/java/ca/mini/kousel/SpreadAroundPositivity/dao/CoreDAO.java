package ca.mini.kousel.SpreadAroundPositivity.dao;

import javax.transaction.Transactional;
import ca.mini.kousel.SpreadAroundPositivity.entity.*;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface CoreDAO extends MongoRepository<spreadaroundpositivity, String>{
	spreadaroundpositivity findByUsername(String username);
}
