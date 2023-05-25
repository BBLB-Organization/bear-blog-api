package bear.blog.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Slf4j
public class LoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{
        log.info("DATE: "+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM-dd-yyyy h:mm:ssa")) +" REQUEST BY: " + request.getRemoteAddr() + " " + request.getMethod() + " " + request.getRequestURI() + " received: " + response.getStatus());
        filterChain.doFilter(request,response);
    }

    @Override
    protected boolean shouldNotFilterAsyncDispatch(){
        return false;
    }

    @Override
    protected boolean shouldNotFilterErrorDispatch(){
        return false;
    }

}
