## Spring Security OAuth2 Resource Server

이 예제는 Spring Security OAuth2와 Spring MVC를 사용하여 Access Token으로 인증이 필요한 API를 제공하는 리소스 서버입니다.

#### Build & Run

1. Maven을 통해 다음과 같이 바로 실행할 수 있습니다.
```bash
$ mvn spring-boot:run
```

2. 혹은 Maven을 통해 빌드 후, jar 파일을 통해 실행할 수 있습니다.
```bash
mvn package
$ java -jar target/oauth-resource-server-0.0.1-SNAPSHOT.jar
```  

3. 혹은 IDE tool을 사용하여 직접 실행할 수 있습니다.  

##### API  

이 어플리케이션이 제공하는 API는 다음과 같이 2개의 endpoints를 지닙니다.  
1. `/unsecured`: Access Token 없이 접근 가능
2. `/secured`: Access Token이 있어야 접근 가능

위 API는 `curl` 명령을 통하여 다음과 같이 접근할 수 있습니다.

```bash
$ curl localhost:8080/unsecured
# 다음과 같은 결과가 돌아온다.
This is an unsecured resource
```

만약, Access Token 없이 `/secured`에 접근하려고 하면 다음과 같은 에러가 돌아오게 됩니다.

```bash
curl localhost:8080/secured
# 다음과 같은 에러가 돌아온다.
{"error":"unauthorized","error_description":"Full authentication is required to access this resource"}
```

성공적으로 위의 endpoint에 접근하기 위해서는 유효한 access token을 `Authorization` 헤더에 담아 요청을 보내야 한다. `jwt-auth-server`의 README를 진행하였다면, access token값을 획득할 수 있고, 다음과 같은 명령을 통하여 요청을 보낼 수 있다. 

```bash
$ curl localhost:8080/secured -H"Authorization: Bearer {{Access Token 값}}"
# 다음과 같은 응답이 돌아온다.
This is a SECURED resource. Authentication: user; Authorities: [ROLE_ADMIN, ROLE_USER]
```