# Vertx Zero Up Framework

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/cn.vertxup/vertx-zero/badge.svg?style=plastic)](https://maven-badges.herokuapp.com/maven-central/cn.vertxup/vertx-zero/)
[![Apache License 2](https://img.shields.io/badge/license-ASF2-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0.txt)
[![Build Status](https://travis-ci.org/silentbalanceyh/vertx-zero.svg?branch=master)](https://travis-ci.org/silentbalanceyh/vertx-zero) 
[![Gitter](https://badges.gitter.im/JoinChat.svg)](https://gitter.im/vertx-up/Lobby)
[![Maintainability](https://api.codeclimate.com/v1/badges/d2d08e521276a496a94e/maintainability)](https://codeclimate.com/github/silentbalanceyh/vertx-zero/maintainability)
[![Waffle.io - Columns and their card count](https://badge.waffle.io/silentbalanceyh/vertx-zero.svg?columns=all)](https://waffle.io/silentbalanceyh/vertx-zero)


This project is based on Vert.x, the idea came from Spring-Boot framework. It could help developers focus on business requirements instead of more details of Vert.x. The project contains two parts "Up" and "Zero". "Up" means running up, "Zero" means no configuration provided, you could run your project with default configuration only. 

Micro Service architecture is a future focused method to design and build mature system and help more companies to implement project faster, based on this idea, Vertx Zero Up Framework came out. It's micro-service oriented framework and will be verified by real projects. Also it provide a tool set to help developers to do correct things. The last point is that because of Event Driven Model, it's high performance framework. 

This framework contains four sub-projects

* `vertx-co`: Core Library of Zero Up Framework
* `vertx-tp`: Third part integration components in Zero Up Framework
* `vertx-up`: Zero Up Engine with nested vert.x web container
* `vertx-rx`: Zero Up Engine with nested rxjava web container instead of web container
* `vertx-zeus`: Zero Examples, it will be removed in real project implementation. 

## 1. Envrionment

If you want to use Zero framework, you can add following dependency into you `pom.xml` to use Zero:

```xml
        <dependency>
            <groupId>cn.vertxup</groupId>
            <artifactId>vertx-up</artifactId>
            <version>0.4.4</version>
        </dependency>
```

## 2. Boot Up

In your project, you can provide main entry only as following to run Zero ( Annotated with `@Up` ) .

```java
import io.vertx.up.VertxApplication;
import io.vertx.up.annotations.Up;

@Up
public class Driver {

    public static void main(final String[] args) {
        VertxApplication.run(Driver.class);
    }
}
```

Once the Zero is up, you can see following logs in your console ( The default port is 6083 ):

```
[ ZERO ] ZeroHttpAgent Http Server has been started successfully. \
	Endpoint: http://0.0.0.0:6083/
```

## 3. Documentation

### 3.1. Zero Documents

1. [Getting Start](doc/zero-starter.md)
2. Environment
	1. [Docker Environment](doc/zero-docker.md)
	2. [Istio Environment](doc/zero-istio.md)
3. Parameters
	1. [Interface annotation ( Non Event )](doc/zero-interface.md)
	2. [Restful Api, @Path usage](doc/zero-path.md)
	3. [ ( JSR311 Extend ) @BodyParam usage](doc/zero-param.md)
	4. [Set POJO as parameters](doc/zero-pojo.md)
4. Mime Supported Matrix
	1. [Parameter type supported](doc/zero-typed.md)
	2. [Mime type Matrix](doc/zero-mime.md)
5. Request Flow
	1. [How to enable Event Bus](doc/zero-worker.md)
	2. [(Recommend) Event Bus sample version](doc/zero-ebs.md)
	2. [Request Workflow Introduction](doc/zero-mode.md)
	3. [How to use Envelop ( Uniform Resource Model )](doc/zero-envelop.md)
	4. [(Recommend) **Example 1**：Advanced usage for Envelop](doc/zero-uniform.md)
6. Plugin
	1. [How to use MongoClient in Zero](doc/zero-mongo.md)
	2. [How to use MySqlClient in Zero](doc/zero-mysql.md)
7. Dependency Injection
	1. [How to use Dependency Injection ( JSR330 )](doc/zero-di.md)
8. Exception ( Error )
	1. [How to define WebException](doc/zero-error.md)
	2. [How to validate request ( JSR 303 with Hibernate-Validator )](doc/zero-validate.md)
	3. [Advanced Validation ( Rule for @BodyParam )](doc/zero-verify.md)
9. Function style ( Fn )
	1. [Function Programming: fling](doc/zero-fling.md)
	2. [Function Programming: safe](doc/zero-safe.md)
	3. [Function Programming: trans, shunt](doc/zero-trans-shunt.md)
	4. [Function Programming: get](doc/zero-get.md)
	5. [Function Programming: it](doc/zero-it.md)
	6. [Function Programming: pool/packet/zipper](doc/zero-pool.md)
	7. [Function Programming: nullFlow](doc/zero-flow.md)

### 3.2. Useful Related Documents

1. [Issue resolutions (1)](doc/issues/README.md)

### 3.3. Some Notes

1. [RxJs](doc/rx/README.md)

## 4. Logging in Zero

You also could use following function in your coding to get Logger component.

```java
// Zero Logger initialized, connect to vert.x logging system directly but uniform managed by zero.
import io.vertx.up.log.Annal;

// Then in your class
public final class Statute {

    private static final Annal LOGGER = Annal.get(Statute.class);
    ......
}
```
 
## 5. Dependency Library

*Lombok is standalone library, you can ignore this library in your project and write pojo with pure java. But we recommend you to use this library to simplify the POJO writting.*

* [Vert.x (3.5.0)](http://www.mvnrepository.com/artifact/io.vertx) 
	* core
	* web
	* config
	* grpc
	* config-yml
	* unit,
	* zookeeper
	* hazelcast
	* web-api-contract
* [ ( JSR311 ) Jws Rs (2.1)](http://mvnrepository.com/artifact/javax.ws.rs/javax.ws.rs-api) 
* [ ( JSR330 ) Inject (1) ](https://mvnrepository.com/artifact/javax.inject/javax.inject)
* [ ( JSR303 ) Validation (2.0.0.Final) ](https://mvnrepository.com/artifact/javax.validation/validation-api)
* [Hibernate Validator (6.0.5.Final)](https://mvnrepository.com/artifact/org.hibernate.validator/hibernate-validator)
* [Jersey Common (2.26)](http://mvnrepository.com/artifact/org.glassfish.jersey.core/jersey-common) ( For media type parsing )
* Logback
	* [logback-classic (1.2.3)](http://mvnrepository.com/artifact/ch.qos.logback/logback-classic)
	* [logback-core (1.2.3)](http://mvnrepository.com/artifact/ch.qos.logback/logback-core)
	* [jul-to-slf4j (1.7.25)](http://mvnrepository.com/artifact/org.slf4j/jul-to-slf4j)
	* [log4j-slf4j-impl (1.7.25)](https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-slf4j-impl)
* [Junit (4.12)](http://mvnrepository.com/artifact/junit/junit)
* [ReflectAsm (1.11.3)](http://www.mvnrepository.com/artifact/com.esotericsoftware/reflectasm/)
* [Jackson-Yaml (2.9.2)](http://www.mvnrepository.com/artifact/com.fasterxml.jackson.dataformat/jackson-dataformat-yaml)
* [Lombok (1.16.18)](http://mvnrepository.com/artifact/org.projectlombok/lombok)
* [Feign Client (8.18.0)](https://mvnrepository.com/artifact/com.netflix.feign/feign-core)
