package portifolio.project.Service;

import java.awt.RenderingHints.Key;
import java.util.Collection;
import java.util.Date;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.lang.Collections;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import portifolio.project.model.Usuario;

public class TokenUtil {

	private static final String HEADER = "Authorization";
	private static final String PREFIX = "Bearer";
	private static final long EXPIRATION = 60 * 60 * 1000;
	private static final String SECRET_KEY = "beaec28c-ef38-43c4-894d-20953434";
	private static final String EMISSOR = "DevNice";

	private static String createToken(Usuario usuario) {
		Key secretKey = (Key) Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

		String token = Jwts.builder().setSubject(usuario.getNome()).setIssuer(EMISSOR)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
				.signWith((java.security.Key) secretKey, SignatureAlgorithm.HS256).compact();

		return PREFIX + token;
	}

	private static boolean isExpierationValid(Date expiration) {
		return expiration.after(new Date(System.currentTimeMillis()));
	}

	private static boolean isEmissorValid(String emissor) {
		return emissor.equals(EMISSOR);
	}

	private static boolean isSubjectValid(String username) {
		return username != null && username.length() > 0;
	}

	public static UsernamePasswordAuthenticationToken validate(HttpServletRequest request) {
		String token = request.getHeader(HEADER);
		token = token.replace(PREFIX, "");
		
		Jws<Claims> jwsClaims = Jwts.parserBuilder().setSigningKey(SECRET_KEY.getBytes())
									.build()
									.parseClaimsJws(token);
		
		String username = jwsClaims.getBody().getSubject();
		String issuer = jwsClaims.getBody().getIssuer();
		Date expira = jwsClaims.getBody().getExpiration();
		
		if (isSubjectValid(username) && isEmissorValid(issuer) && isExpierationValid(expira)) {
			return new UsernamePasswordAuthenticationToken(username, null);
		}
		
		return null;
	}

}
