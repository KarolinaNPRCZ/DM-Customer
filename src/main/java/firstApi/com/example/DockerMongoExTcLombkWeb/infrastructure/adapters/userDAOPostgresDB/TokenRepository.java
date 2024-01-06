package firstApi.com.example.DockerMongoExTcLombkWeb.infrastructure.adapters.userDAOPostgresDB;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface TokenRepository extends JpaRepository<Token, Integer> {
    @Query(value = """
            select t from Token t inner join User u
            on t.user.id = u.id
            where u.id = :id and (t.expired = false and t.revoked = false)
            """)
    Optional<Token> getValidTokenByTokenId(Integer id);

    List<Token> getAllByIdAndExpiredIsFalseAndRevokedIsFalse(Integer id);
    Optional<Token> findTokenByUserJWTTokenChain(String userJWTTokenChain);


}
