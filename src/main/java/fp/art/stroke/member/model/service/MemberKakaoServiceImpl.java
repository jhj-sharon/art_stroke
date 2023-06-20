//package fp.art.stroke.member.model.service;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.HashMap;
//
//import org.springframework.stereotype.Service;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
//
//
////0620 ey
//@Service
//public class MemberKakaoServiceImpl implements MemberKakaoService{
//
//	//토큰받아오기
//	@Override
//	public String getKakaoAccessToken(String code) {
//		 String accessToken = "";
//		    String refreshToken = "";
//		    String requestURL = "https://kauth.kakao.com/oauth/token";
//
//		    try {
//		        URL url = new URL(requestURL);
//		        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//
//		        conn.setRequestMethod("POST");
//		        // setDoOutput()은 OutputStream으로 POST 데이터를 넘겨 주겠다는 옵션이다.
//		        // POST 요청을 수행하려면 setDoOutput()을 true로 설정한다.
//		        conn.setDoOutput(true);
//
//		        // POST 요청에서 필요한 파라미터를 OutputStream을 통해 전송
//		        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
//		        String sb = "grant_type=authorization_code" +
//		                "&client_id=REST_API_KEY 입력" + // REST_API_KEY
//		                "&redirect_uri=http://localhost:8080/app/login/kakao" + // REDIRECT_URI
//		                "&code=" + code;
//		        bufferedWriter.write(sb);
//		        bufferedWriter.flush();
//
//		        int responseCode = conn.getResponseCode();
//		        System.out.println("responseCode : " + responseCode);
//
//		        // 요청을 통해 얻은 데이터를 InputStreamReader을 통해 읽어 오기
//		        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//		        String line = "";
//		        StringBuilder result = new StringBuilder();
//
//		        while ((line = bufferedReader.readLine()) != null) {
//		            result.append(line);
//		        }
//		        System.out.println("response body : " + result);
//
//		        JsonElement element = JsonParser.parseString(result.toString());
//
//		        accessToken = element.getAsJsonObject().get("access_token").getAsString();
//		        refreshToken = element.getAsJsonObject().get("refresh_token").getAsString();
//
//		        System.out.println("accessToken : " + accessToken);
//		        System.out.println("refreshToken : " + refreshToken);
//
//		        bufferedReader.close();
//		        bufferedWriter.close();
//		    } catch (IOException e) {
//		        e.printStackTrace();
//		    }
//
//		    return accessToken;
//	}
//
//	//사용자 로그인처리
//	@Override
//	public HashMap<String, Object> getUserInfo(String accessToken) {
//		 HashMap<String, Object> userInfo = new HashMap<>();
//		    String postURL = "https://kapi.kakao.com/v2/user/me";
//
//		    try {
//		        URL url = new URL(postURL);
//		        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//		        conn.setRequestMethod("POST");
//
//		        conn.setRequestProperty("Authorization", "Bearer " + accessToken);
//
//		        int responseCode = conn.getResponseCode();
//		        System.out.println("responseCode : " + responseCode);
//
//		        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//		        String line = "";
//		        StringBuilder result = new StringBuilder();
//
//		        while ((line = br.readLine()) != null) {
//		            result.append(line);
//		        }
//		        System.out.println("response body : " + result);
//
//		        JsonElement element = JsonParser.parseString(result.toString());
//		        JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
//		        JsonObject kakaoAccount = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
//
//		        String nickname = properties.getAsJsonObject().get("nickname").getAsString();
//		        String email = kakaoAccount.getAsJsonObject().get("email").getAsString();
//
//		        userInfo.put("nickname", nickname);
//		        userInfo.put("email", email);
//
//		    } catch (IOException exception) {
//		        exception.printStackTrace();
//		    }
//
//		    return userInfo;
//	}
//
//	@Override
//	public void updateKakaoToken(int userId) {
//		KakaoToken kakaoToken = loginProvider.getKakaoToken(userId);
//        String postURL = "https://kauth.kakao.com/oauth/token";
//        KakaoToken newToken = null;
//
//        try {
//            URL url = new URL(postURL);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("POST");
//            conn.setDoOutput(true);
//
//            // POST 요청에 필요한 파라미터를 OutputStream을 통해 전송
//            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
//            String sb = "grant_type=refresh_token" +
//                    "&client_id=REST_API_KEY 입력" + // REST_API_KEY
//                    "&refresh_token=" + kakaoToken.getRefresh_token() + // REFRESH_TOKEN
//                    "&client_secret=시크릿 키 입력";
//            bufferedWriter.write(sb);
//            bufferedWriter.flush();
//
//            // 요청을 통해 얻은 데이터를 InputStreamReader을 통해 읽어 오기
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//            String line = "";
//            StringBuilder result = new StringBuilder();
//
//            while ((line = bufferedReader.readLine()) != null) {
//                result.append(line);
//            }
//            System.out.println("response body : " + result);
//
//            JsonElement element = JsonParser.parseString(result.toString());
//
//            Set<String> keySet = element.getAsJsonObject().keySet();
//
//						// 새로 발급 받은 accessToken 불러오기
//            String accessToken = element.getAsJsonObject().get("access_token").getAsString();
//	          // refreshToken은 유효 기간이 1개월 미만인 경우에만 갱신되어 반환되므로,
//						// 반환되지 않는 경우의 상황을 if문으로 처리해주었다.
//						String refreshToken = "";
//            if(keySet.contains("refresh_token")) {
//                refreshToken = element.getAsJsonObject().get("refresh_token").getAsString();
//            }
//
//            if(refreshToken.equals("")) {
//                newToken = new KakaoToken(accessToken, kakaoToken.getRefresh_token());
//            } else {
//                newToken = new KakaoToken(accessToken, refreshToken);
//            }
//
//            bufferedReader.close();
//            bufferedWriter.close();
//
//        } catch (IOException exception) {
//            exception.printStackTrace();
//        }
//
//        try{
//            int result = 0;
//            if (newToken != null) {
//                result = loginDao.updateKakaoToken(userId, newToken);
//            }
//            if(result == 0){
//                throw new BaseException(UPDATE_FAIL_TOKEN);
//            }
//        } catch(Exception exception){
//            throw new BaseException(DATABASE_ERROR);
//        }
//
//		
//	}
//
//
//	
//	
//	
//	
//}
