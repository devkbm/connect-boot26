package com.like;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import com.github.gavlyukovskiy.boot.jdbc.decorator.DataSourceDecoratorAutoConfiguration;
import com.like.system.core.p6spylog.P6spyConfig;

// 참조 - https://backtony.github.io/spring/2021-08-13-spring-log-1/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@DataJpaTest(showSql = false)
@ImportAutoConfiguration(DataSourceDecoratorAutoConfiguration.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Import(P6spyConfig.class)
public @interface CustomDataJpaTest {

}
