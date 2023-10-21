package br.com.serratec.ecommerce.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter{

    @Autowired
    private JWTService jwtService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    //metodo principal onde toda sua requisicao bate antes de chegar no controler
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
                // 1 pegar o token da requisicao...
                String token = obterToken(request);

                // 2 pegar o id do token.
                Optional<Long> id = jwtService.obterIdDoUsuario(token);

                // 3 saber se o id existe, se veio algum id no token.
                if(id.isPresent()){

                    //pego o usuario dono do token pelo seu id
                    UserDetails usuario = customUserDetailsService.obterUsuarioPeloId(id.get());

                    //nesse ponto verificamos se o usuario esta autenticando ou nao
                    // aqui tambem poderiamos validar as permissoes
                    UsernamePasswordAuthenticationToken autenticacao = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

                    //mudo a  autenticacao para a propria requisicao
                    autenticacao.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    // repasso a autenticassao para o contexto do spring security
                    // apartir de agora o spring toma conta de tudo pra mim
                    SecurityContextHolder.getContext().setAuthentication(autenticacao); 
                }

                response.addHeader("Access-Control-Expose-Headers", "Authorization");
                // 4 filtrar regras do usuario  
                filterChain.doFilter(request, response);
    }
    
    private String obterToken(HttpServletRequest request){

        String token = request.getHeader("Authorization");

        
        if (token == null || !token.startsWith("Bearer ")) {
            return null;
        }

        //Bearer jkasfkjgasfjkg.alfhkklasf.jasflkih
        return token.substring(7);
    }
}
