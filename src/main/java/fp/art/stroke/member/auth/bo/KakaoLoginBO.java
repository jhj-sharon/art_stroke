package fp.art.stroke.member.auth.bo;


import java.util.UUID;


import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;



import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

import fp.art.stroke.member.KakaoOAuthApi;



public class KakaoLoginBO {

	
	@Value("${kakao.clientId}")
  private String KAKAO_CLIENT_ID ;
	
	@Value("${kakao.clientSecret}")
private String KAKAO_CLIENT_SECRET ;
	@Value("${kakao.redirectUri}")
  private String KAKAO_REDIRECT_URI ;
	
	@Value("${kakao.sessionState}")
  private String KAKAO_SESSION_STATE ;
	
	@Value("${kakao.profileApiUrl}")
  private String KAKAO_PROFILE_API_URL ;
	
	
	
	public String getAuthorizationUrl(HttpSession session) {
		String state=generateRandomString();
		setSession(session,state);
		OAuth20Service oauthService=new ServiceBuilder()
				.apiKey(KAKAO_CLIENT_ID)
				.apiSecret(KAKAO_CLIENT_SECRET)
				.callback(KAKAO_REDIRECT_URI)
				.state(state).build(KakaoOAuthApi.instance());
		
		
		return oauthService.getAuthorizationUrl();
		        
		       
	}
	
	public OAuth2AccessToken getAccessToken(HttpSession session,String code,String state)throws Exception {
		String sessionState=getSession(session);
		if(StringUtils.pathEquals(sessionState, state)) {
			OAuth20Service oauthService=new ServiceBuilder()
					.apiKey(KAKAO_CLIENT_ID)
					.apiSecret(KAKAO_CLIENT_SECRET)
					.callback(KAKAO_REDIRECT_URI)
					.state(state).build(KakaoOAuthApi.instance());
			OAuth2AccessToken accessToken =oauthService.getAccessToken(code);
			return accessToken;
			
		}
		return null;
	}

public String getUserProfile(OAuth2AccessToken oauthToken)throws Exception {
	OAuth20Service oauthService=new ServiceBuilder()
			.apiKey(KAKAO_CLIENT_ID)
			.apiSecret(KAKAO_CLIENT_SECRET)
			.callback(KAKAO_REDIRECT_URI)
			.build(KakaoOAuthApi.instance());
	OAuthRequest request=new OAuthRequest(Verb.GET,KAKAO_PROFILE_API_URL,oauthService);
	oauthService.signRequest(oauthToken, request);
	Response response=request.send();
	return response.getBody();
	
	
	
	
	}

 private String generateRandomString() {
	 return UUID.randomUUID().toString();
	 
 }
 private void setSession(HttpSession session,String state) {
	 session.setAttribute(KAKAO_SESSION_STATE, state);
 }
 
 private String getSession(HttpSession session) {
	 return (String)session.getAttribute(KAKAO_SESSION_STATE);
 }
}
