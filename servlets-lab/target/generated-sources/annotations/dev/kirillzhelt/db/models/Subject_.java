package dev.kirillzhelt.db.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Subject.class)
public abstract class Subject_ {

	public static volatile SingularAttribute<Subject, String> name;
	public static volatile SingularAttribute<Subject, Long> id;
	public static volatile SetAttribute<Subject, Faculty> faculties;
	public static volatile SetAttribute<Subject, Application> applications;

	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String FACULTIES = "faculties";
	public static final String APPLICATIONS = "applications";

}

