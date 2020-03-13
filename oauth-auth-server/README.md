## Spring Security OAuth2 Authorization Server

이 예제는 Spring Security OAuth2 OAuth2를 사용하여 Access Token을 발행하는 인증 서버입니다. 

#### Build & Run

1. Maven을 통해 다음과 같이 바로 실행할 수 있습니다.
```bash
$ mvn spring-boot:run
```

2. 혹은 Maven을 통해 빌드 후, jar 파일을 통해 실행할 수 있습니다.
```bash
mvn package
$ java -jar target/oauth-auth-server-0.0.1-SNAPSHOT.jar
```  

3. 혹은 IDE tool을 사용하여 직접 실행할 수 있습니다.  

#### Authorization Code Grant 방식

1. 브라우저에서 다음과 같은 인증 URL을 입력합니다.
```bash
http://localhost:9999/oauth/authorize?client_id=myclient&response_type=code&redirect_uri=http://localhost:9191/x
```

2. 정상적으로 실행이 된다면, 로그인 화면이 브라우저창에 나타나게 될 것이고 다음과 같이 입력합니다.
    * (ID, PW): (user, 1234)
    * (ID, PW): (admin, 1234)

3. 정보제공 동의를 묻는 화면이 나타나게 됩니다. `authorize`를 눌렀다는 가정하에, 다음과 같은 URL로 리다이렉트 되어 `code` 값을 받음을 알 수 있습니다.
    * http://localhost:9191/x&code=`code 값이 이곳에 응답됩니다.`

4. 성공적으로 응답받은 code 값을 기반으로 다음과 같은 `curl` 명령을 통해 access token 값을 획득할 수 있습니다.
```bash
$ curl localhost:9999/oauth/token \
     -H"Content-type: application/x-www-form-urlencoded" \
     -d'grant_type=authorization_code&redirect_uri=http://localhost:9191/x&code=응답받은 code 값' \
     -u myclient:secret
```

#### Implicit Grant 방식

1. 브라우저에서 다음과 같은 인증 URL을 입력합니다.

```bash
http://localhost:9999/oauth/authorize?client_id=myclient&response_type=token&redirect_uri=http://localhost:9191/x
```

2. 정상적으로 실행이 된다면, 로그인 화면이 브라우저창에 나타나게 될 것이고 다음과 같이 입력합니다.
    * (ID, PW): (user, 1234)
    * (ID, PW): (admin, 1234)

3. 정보제공 동의를 묻는 화면이 나타나게 됩니다. `authorize`를 눌렀다는 가정하에, 다음과 같은 URL로 리다이렉트 되어 `token` 값을 받음을 알 수 있습니다.
    * http://localhost:9191/x#access_token=`token 값이 이곳에 응답됩니다.`

#### Password Grant 방식

1. 다음과 같은 두명의 사용자가 존재합니다.
    * ID: user, PW: 1234
    * ID: admin, PW: 1234

2. 등록된 Client의 정보는 다음과 같습니다.  
    * Client ID: myclient
    * Client Secret: secret

3. `curl` 명령어를 통해 다음과 같이 token 획득을 할 수 있습니다.
```bash
$ curl http://localhost:9999/oauth/token \
    -d"grant_type=password&username=user&password=1234" \
    -H"Content-type:application/x-www-form-urlencoded; charset=utf-8" \
    -u myclient:secret
```

#### Client Credentials Grant 방식

1. `curl` 명령어를 통해 다음과 같이 token 획득을 할 수 있습니다.
``bash
$ curl http://localhost:9999/oauth/token \
    -d"grant_type=client_credentials" \
    -H"Content-type:application/x-www-form-urlencoded; charset=utf-8" \
    -u myclient:secret
```