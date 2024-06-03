import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class URLShortener{
    private final Map<String, String> urlMapping;
    private final String baseUrl;
    public URLShortener(){
        urlMapping = new HashMap<>();
        baseUrl = "http://short.url/";
    }
    public String encode(String longUrl){
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(longUrl.getBytes());
            String base64Hash = Base64.getUrlEncoder().encodeToString(hash);
            String shortId = base64Hash.substring(0,6);

            while(urlMapping.containsKey(shortId)){
                shortId = base64Hash.substring(0,6);
            }

            urlMapping.put(shortId,longUrl);
            return baseUrl+shortId;
        }catch(NoSuchAlgorithmException e){
            throw new RuntimeException(e);
        }
    }

    public String decode(String shortUrl){
        String shortId = shortUrl.replace(baseUrl, "");
        return urlMapping.getOrDefault(shortId,null);
    }
}