package db.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Faculty.class)
public abstract class Faculty_ {

	public static volatile SetAttribute<Faculty, Subject> subjects;
	public static volatile SingularAttribute<Faculty, String> name;
	public static volatile SingularAttribute<Faculty, Long> id;
	public static volatile SingularAttribute<Faculty, Integer> plan;
	public static volatile SetAttribute<Faculty, Application> applications;

	public static final String SUBJECTS = "subjects";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String PLAN = "plan";
	public static final String APPLICATIONS = "applications";

}

