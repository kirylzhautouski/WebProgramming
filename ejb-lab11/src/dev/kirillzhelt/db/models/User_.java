package dev.kirillzhelt.db.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, String> name;
	public static volatile SingularAttribute<User, Long> id;
	public static volatile SingularAttribute<User, Boolean> isAdmin;
	public static volatile SingularAttribute<User, String> login;
	public static volatile ListAttribute<User, Application> applications;

	public static final String PASSWORD = "password";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String IS_ADMIN = "isAdmin";
	public static final String LOGIN = "login";
	public static final String APPLICATIONS = "applications";

}

