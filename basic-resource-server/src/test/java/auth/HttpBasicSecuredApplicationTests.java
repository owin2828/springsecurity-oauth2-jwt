package auth;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HttpBasicSecuredApplicationTests {
    @Autowired
    private WebApplicationContext context;
 
    private MockMvc mvc;
 
    @Before
    public void setup() {
        mvc = MockMvcBuilders
          .webAppContextSetup(context)
          .apply(springSecurity())
          .build();
	}
	
	// 해당 URL로 요청을 보냈을 때 권한 테스트
	@WithMockUser(username="user", password="1234", roles="USER")
	@Test
	public void contextLoads() throws Exception {
		mvc.perform(get("/secured"))
			.andDo(print())
			.andExpect(status().isOk());
	}

	// 로그인 테스트
	@Test
	public void testFormLogin() throws Exception {
		mvc.perform(formLogin().user("user").password("1234"))
			.andExpect(status().isFound())
			.andExpect(redirectedUrl("/secured"))
			.andExpect(authenticated().withRoles("USER"));
	}

	// 로그아웃 테스트
	@WithMockUser(username="user", password="1234", roles="USER")
	@Test
	public void testLogout() throws Exception {
	   /*
		* SecurityMockMvcRequestBuilders의 logout 메소드를 사용해 
		* '로그아웃 처리를 하는 경로(기본값 : /lgout)'를 지정하고 요청을 보내고, 이때 CSRF 토큰값도 함께 전송
		* 로그아웃 처리가 성공할 때의 리다이렉트 경로가 맞는지 검증
		* SecurityMockMvcResultMatchers의 unauthenticated 메소드를 사용해 인증 정보가 파기됐는지 검증
		*/
		mvc.perform(logout())
			.andExpect(status().isFound())
			.andExpect(redirectedUrl("/login"))
			.andExpect(unauthenticated());
	}

}
