package dev.fringe.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.hibernate.SessionFactory
import org.mybatis.spring.SqlSessionFactoryBean
import org.mybatis.spring.SqlSessionTemplate
import org.mybatis.spring.annotation.MapperScan
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.core.io.ClassPathResource
import org.springframework.jdbc.datasource.init.DataSourceInitializer
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator
import org.springframework.orm.hibernate5.HibernateTransactionManager
import org.springframework.orm.hibernate5.LocalSessionFactoryBean
import org.springframework.transaction.annotation.EnableTransactionManagement

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@MapperScan(basePackages = 'dev.fringe.persistence')
@PropertySource('classpath:database.properties')
@EnableTransactionManagement
@ComponentScan( basePackages = ['dev.fringe.service','dev.fringe.dao'])
public class MainConfig {

    @Value('${db.driverClassName}')
    private String driverClassName;
    @Value('${db.url}')
    private String url;
    @Value('${db.username}')
    private String username;
    @Value('${db.password}')
    private String password;

    @Bean
    DataSource dataSource() {
    	HikariConfig c = new HikariConfig();
    	c.setDriverClassName(driverClassName);
    	c.setJdbcUrl(url);
        c.setUsername(username);
        c.setPassword(password);
        return new HikariDataSource(c);
    }
    
    @Bean
    SqlSessionFactory sqlSessionFactory(@Qualifier('dataSource') DataSource d) throws Exception {
        SqlSessionFactoryBean s = new SqlSessionFactoryBean();
        s.setDataSource(d);
        s.setTypeAliasesPackage('dev.fringe.model');
        return s.getObject();
    }

    @Bean
    SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory s) {
        return new SqlSessionTemplate(s);
    }
	
//	@Bean
//	DataSourceInitializer dataSourceInitializer(@Qualifier('dataSource') DataSource d) {
//	    ResourceDatabasePopulator p = new ResourceDatabasePopulator();
//	    p.addScript(new ClassPathResource('/data.sql'));
//	    DataSourceInitializer i = new DataSourceInitializer();
//	    i.setDataSource(d);
//	    i.setDatabasePopulator(p);
//	    return i;
//	}
	@Bean
	LocalSessionFactoryBean getSessionFactory() {
		LocalSessionFactoryBean l = new LocalSessionFactoryBean();
		l.setDataSource(dataSource());
		l.setPackagesToScan("dev.fringe.model");
		Properties p = new Properties();
		p.put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
		p.put("hibernate.hbm2ddl.auto", "create");
		l.setHibernateProperties(p);
		return l;
	}
	
	@Bean(name = "transactionManager")
	@Autowired
	HibernateTransactionManager transactionManager(SessionFactory s) {
		HibernateTransactionManager t = new HibernateTransactionManager();
		t.setSessionFactory(s);
		return t;
	}

}
