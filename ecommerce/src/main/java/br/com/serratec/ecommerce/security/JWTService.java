package br.com.serratec.ecommerce.security;

import java.util.Date;
import java.util.Optional;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import br.com.serratec.ecommerce.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWTService {

    //chave secreta utilizada pela jwt para codificar e decodificar o token
    private static final String SECURITY_KEY = "ChaveSecreta";

    /**
     * Metodo para retornar o token JWT
     * @param authentication Autenticaçao de usuario
     * @author Leonardo Lucas
     * @return Token JWT
     */
    public String gerarToken(Authentication authentication){

        // 1 dia em mllisegundos(pode variar de acordo com a regra de negocio)
        int tempoExpiracao = 86400000;

        //gera uma data com um dia a mais
        Date dataExpiracao = new Date(new Date().getTime()+tempoExpiracao);

        //aqui pega o usuario atual da autenticacao
        Usuario usuario = (Usuario) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(usuario.getId().toString())//identificacao unico do usuario
                .setIssuedAt(new Date()) // data de geracacao de token
                .setExpiration(dataExpiracao) //data de expiracao do token
                .signWith(SignatureAlgorithm.HS256, SECURITY_KEY) // algoritimo de criptografia
                .compact(); // aqui pega tudo que gerou no token e compact
    }

    /**
     * Metodo para retornar o id do usuario dono do token
     * @param token token do usuario
     * @return Id do usuario
     */
    public Optional<Long> obterIdDoUsuario(String token){
        try {
            //Aqui pego a clain do token para achar o usuario dono dele
            Claims claims = Jwts.parser().setSigningKey(SECURITY_KEY).parseClaimsJws(token).getBody();
            
            //Se achou o id dentro do claim, ele devolve, se nao ele devolve um null
            return Optional.ofNullable(Long.parseLong(claims.getSubject()));

        } catch (Exception e) {

            return Optional.empty();

        }
    }
}
