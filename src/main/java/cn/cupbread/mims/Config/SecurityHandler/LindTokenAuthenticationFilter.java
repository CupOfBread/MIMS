package cn.cupbread.mims.Config.SecurityHandler;

import cn.cupbread.mims.Entity.User;
import cn.cupbread.mims.Service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Cup Of Bread
 * @version : 1.0.0
 * @date : 2021/6/11
 * @description :
 */

@Component
public class LindTokenAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    RedisTemplate<String, String> redisTemplate;
    String tokenHead = "Bearer ";
    String tokenHeader = "Authorization";
    @Autowired
    private UserService userService;

    /**
     * token filter.
     *
     * @param request     .
     * @param response    .
     * @param filterChain .
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader(this.tokenHeader);
        if (authHeader != null && authHeader.startsWith(tokenHead)) {
            final String authToken = authHeader.substring(tokenHead.length()); // The part after "Bearer "
            if (authToken != null && redisTemplate.hasKey(authToken)) {
                String username = redisTemplate.opsForValue().get(authToken);
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("name", username);
                    User userDetails = this.userService.getOne(queryWrapper);
                    //可以校验token和username是否有效，目前由于token对应username存在redis，都以默认都是有效的

                    List<GrantedAuthority> auth = new ArrayList<>();

                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, auth);
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(
                            request));
                    logger.info("authenticated user " + username + ", setting security context");
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}