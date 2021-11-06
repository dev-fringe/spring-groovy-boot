package dev.fringe.dao

import org.hibernate.Session
import org.hibernate.SessionFactory
import org.springframework.aop.ThrowsAdvice
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

import dev.fringe.model.MainModel
import dev.fringe.persistence.MainMapper

@Repository
class MainDao {

	@Autowired SessionFactory sessionFactory;

	void save(MainModel main) {
		sessionFactory.getCurrentSession().save(main);
	}
}
