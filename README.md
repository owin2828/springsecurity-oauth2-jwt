## Spring Security Oauth2 + JWT 예제

이 예제는 OAuth2를 구현한 간단한 예제로써,  
Spring Security의 하위 프로젝트인 Spring Security OAuth2를 기반하고 있습니다.

다음과 같이 크게 인증을 3가지 방법으로 구분하여, 각 방법의 차이점을 소스코드를 통해 알 수 있도록 구현했습니다.  

1. Only Spring Security
2. Spring Security OAuth2
3. Spring Security OAuth2 + JWT  

모든 소스코드는 Spring Boot application으로 구성되어 있으며, OAuth2는 다음과 같이 구성됩니다.  

* auth-server: 인증 서버  
* resource-server: 인증이 필요한 API를 제공하는 OAuth2 리소스 서버  

자세한 사항은 각 소스코드 안의 README를 참조하시기 바라며, OAuth2의 대한 사항은 wiki를 참조해 주시기 바랍니다.