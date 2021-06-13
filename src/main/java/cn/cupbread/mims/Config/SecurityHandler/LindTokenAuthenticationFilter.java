package cn.cupbread.mims.Config.SecurityHandler;

import cn.cupbread.mims.Entity.User;
import cn.cupbread.mims.Service.UserService;
import cn.cupbread.mims.Util.RedisUtil;
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
import java.util.Date;
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
    String tokenHeader = "Authorization";
    @Autowired
    private UserService userService;
    @Autowired
    private RedisUtil redisUtil;

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
        System.out.println("token:"+authHeader);
        if (authHeader != null) {
            String username = redisTemplate.opsForValue().get(authHeader);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                QueryWrapper<User> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("name", username);
                User user = userService.getOne(queryWrapper);
                UserDetail userDetail = new UserDetail();
                userDetail.setUsername(user.getName());
                userDetail.setPassword(user.getPassword());

                //可以校验token和username是否有效，目前由于token对应username存在redis，都以默认都是有效的
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetail, null, userDetail.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(
                        request));
                logger.info("authenticated user " + username + ", setting security context");
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        }
        filterChain.doFilter(request, response);
    }
}