package db.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Application.class)
public abstract class Application_ {

	public static volatile SingularAttribute<Application, Subject> subject;
	public static volatile SingularAttribute<Application, Integer> grade;
	public static volatile SingularAttribute<Application, Long> id;
	public static volatile SingularAttribute<Application, User> user;
	public static volatile SingularAttribute<Application, Faculty> faculty;

	public static final String SUBJECT = "subject";
	public static final String GRADE = "grade";
	public static final String ID = "id";
	public static final String USER = "user";
	public static final String FACULTY = "faculty";

}

