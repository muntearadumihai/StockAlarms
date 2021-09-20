package com.devm8.stockalarms.decoder;

import com.devm8.stockalarms.exception.NotFoundException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.json.JSONObject;

import javax.xml.bind.DatatypeConverter;
import java.util.Base64;

public class UserJWTDecoder {
    private static final String SECRET_KEY = "Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=";

    private static final String TOKEN_REGEX = "\\.";
    private static final String ID_NOT_FOUND_MESSAGE = "Id not found!";
    private static final String ROLE_TOKEN = "userId";

    private static final Base64.Decoder decoder = Base64.getDecoder();

    public static int getUserIdFromToken(String token) {
        String[] tokenParts = token.split(TOKEN_REGEX);
        String body = new String(decoder.decode(tokenParts[1]));
        JSONObject jsonObject = new JSONObject(body);
        try {
            return jsonObject.getInt(ROLE_TOKEN);
        } catch (IllegalArgumentException exception) {
            throw new NotFoundException(ID_NOT_FOUND_MESSAGE);
        }
    }
    public static Claims decodeJWT(String jwt) {
        return Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(jwt).getBody();
    }
}
