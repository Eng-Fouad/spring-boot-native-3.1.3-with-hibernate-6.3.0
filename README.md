This repo demonstrates that Hibernate 6.3.0 fails to work in native mode with Spring boot 3.1.3.

- Build the native image:

```
./gradlew bootBuildImage
```

- Run the native image:

```
docker run --rm spring-boot-native-3.1.3-with-hibernate-6.3.0:0.0.1-SNAPSHOT
```

- Exception:

```
org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'entityManagerFactory': [PersistenceUnit: default] Unable to build Hibernate SessionFactory; nested exception is org.hibernate.HibernateException: Using the ReflectionOptimizer is not possible when the configured BytecodeProvider is 'none'. Use a different BytecodeProvider
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1770) ~[io.fouad.spring.demos.SpringDemoApplication:6.0.11]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:598) ~[io.fouad.spring.demos.SpringDemoApplication:6.0.11]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:520) ~[io.fouad.spring.demos.SpringDemoApplication:6.0.11]
        at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:326) ~[io.fouad.spring.demos.SpringDemoApplication:6.0.11]
        at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:234) ~[io.fouad.spring.demos.SpringDemoApplication:6.0.11]
        at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:324) ~[io.fouad.spring.demos.SpringDemoApplication:6.0.11]
        at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:200) ~[io.fouad.spring.demos.SpringDemoApplication:6.0.11]
        at org.springframework.context.support.AbstractApplicationContext.getBean(AbstractApplicationContext.java:1155) ~[io.fouad.spring.demos.SpringDemoApplication:6.0.11]
        at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:932) ~[io.fouad.spring.demos.SpringDemoApplication:6.0.11]
        at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:608) ~[io.fouad.spring.demos.SpringDemoApplication:6.0.11]
        at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:734) ~[io.fouad.spring.demos.SpringDemoApplication:3.1.3]
        at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:436) ~[io.fouad.spring.demos.SpringDemoApplication:3.1.3]
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:312) ~[io.fouad.spring.demos.SpringDemoApplication:3.1.3]
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:1306) ~[io.fouad.spring.demos.SpringDemoApplication:3.1.3]
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:1295) ~[io.fouad.spring.demos.SpringDemoApplication:3.1.3]
        at io.fouad.spring.demos.SpringDemoApplication.main(SpringDemoApplication.java:23) ~[io.fouad.spring.demos.SpringDemoApplication:na]
Caused by: jakarta.persistence.PersistenceException: [PersistenceUnit: default] Unable to build Hibernate SessionFactory; nested exception is org.hibernate.HibernateException: Using the ReflectionOptimizer is not possible when the configured BytecodeProvider is 'none'. Use a different BytecodeProvider
        at org.springframework.orm.jpa.AbstractEntityManagerFactoryBean.buildNativeEntityManagerFactory(AbstractEntityManagerFactoryBean.java:421) ~[io.fouad.spring.demos.SpringDemoApplication:6.0.11]
        at org.springframework.orm.jpa.AbstractEntityManagerFactoryBean.afterPropertiesSet(AbstractEntityManagerFactoryBean.java:396) ~[io.fouad.spring.demos.SpringDemoApplication:6.0.11]
        at org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean.afterPropertiesSet(LocalContainerEntityManagerFactoryBean.java:352) ~[io.fouad.spring.demos.SpringDemoApplication:6.0.11]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.invokeInitMethods(AbstractAutowireCapableBeanFactory.java:1817) ~[io.fouad.spring.demos.SpringDemoApplication:6.0.11]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1766) ~[io.fouad.spring.demos.SpringDemoApplication:6.0.11]
        ... 15 common frames omitted
Caused by: org.hibernate.HibernateException: Using the ReflectionOptimizer is not possible when the configured BytecodeProvider is 'none'. Use a different BytecodeProvider
        at org.hibernate.bytecode.internal.none.BytecodeProviderImpl.getReflectionOptimizer(BytecodeProviderImpl.java:48) ~[io.fouad.spring.demos.SpringDemoApplication:6.3.0.Final]
        at org.hibernate.metamodel.internal.EntityRepresentationStrategyPojoStandard.resolveReflectionOptimizer(EntityRepresentationStrategyPojoStandard.java:289) ~[na:na]
        at org.hibernate.metamodel.internal.EntityRepresentationStrategyPojoStandard.<init>(EntityRepresentationStrategyPojoStandard.java:160) ~[na:na]
        at org.hibernate.metamodel.internal.ManagedTypeRepresentationResolverStandard.resolveStrategy(ManagedTypeRepresentationResolverStandard.java:62) ~[na:na]
        at org.hibernate.persister.entity.AbstractEntityPersister.<init>(AbstractEntityPersister.java:519) ~[io.fouad.spring.demos.SpringDemoApplication:6.3.0.Final]
        at org.hibernate.persister.entity.SingleTableEntityPersister.<init>(SingleTableEntityPersister.java:135) ~[io.fouad.spring.demos.SpringDemoApplication:6.3.0.Final]
        at java.base@17.0.8/java.lang.reflect.Constructor.newInstanceWithCaller(Constructor.java:499) ~[io.fouad.spring.demos.SpringDemoApplication:na]
        at java.base@17.0.8/java.lang.reflect.Constructor.newInstance(Constructor.java:480) ~[io.fouad.spring.demos.SpringDemoApplication:na]
        at org.hibernate.persister.internal.PersisterFactoryImpl.createEntityPersister(PersisterFactoryImpl.java:92) ~[io.fouad.spring.demos.SpringDemoApplication:6.3.0.Final]
        at org.hibernate.persister.internal.PersisterFactoryImpl.createEntityPersister(PersisterFactoryImpl.java:75) ~[io.fouad.spring.demos.SpringDemoApplication:6.3.0.Final]
        at org.hibernate.metamodel.model.domain.internal.MappingMetamodelImpl.processBootEntities(MappingMetamodelImpl.java:243) ~[na:na]
        at org.hibernate.metamodel.model.domain.internal.MappingMetamodelImpl.finishInitialization(MappingMetamodelImpl.java:181) ~[na:na]
        at org.hibernate.internal.SessionFactoryImpl.initializeMappingModel(SessionFactoryImpl.java:323) ~[na:na]
        at org.hibernate.internal.SessionFactoryImpl.<init>(SessionFactoryImpl.java:273) ~[na:na]
        at org.hibernate.boot.internal.SessionFactoryBuilderImpl.build(SessionFactoryBuilderImpl.java:450) ~[na:na]
        at org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl.build(EntityManagerFactoryBuilderImpl.java:1479) ~[na:na]
        at org.springframework.orm.jpa.vendor.SpringHibernateJpaPersistenceProvider.createContainerEntityManagerFactory(SpringHibernateJpaPersistenceProvider.java:75) ~[na:na]
        at org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean.createNativeEntityManagerFactory(LocalContainerEntityManagerFactoryBean.java:376) ~[io.fouad.spring.demos.SpringDemoApplication:6.0.11]
        at org.springframework.orm.jpa.AbstractEntityManagerFactoryBean.buildNativeEntityManagerFactory(AbstractEntityManagerFactoryBean.java:409) ~[io.fouad.spring.demos.SpringDemoApplication:6.0.11]
        ... 19 common frames omitted
```