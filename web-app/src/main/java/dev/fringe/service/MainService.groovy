package dev.fringe.service

import org.hibernate.Session
import org.hibernate.SessionFactory
import org.springframework.aop.ThrowsAdvice
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

import dev.fringe.dao.MainDao
import dev.fringe.model.MainModel
import dev.fringe.persistence.MainMapper

@Service
class MainService {

	@Autowired MainDao mainDao;
	@Autowired MainMapper mapper;

	@Transactional
	void save(MainModel main) {
		mainDao.save(main)
	}
}
