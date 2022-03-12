package cheese.cheese.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static cheese.cheese.dto.Enum.Consts.FAIL_LOGIN;

@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException e) throws IOException {

        log.info(request.getHeader("Authorization"));
        log.error("Responding with unauthorized error. Message - {}", e.getMessage());

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, FAIL_LOGIN);
    }
}
