package in.fortytwo42.daos;

import in.fortytwo42.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserDao extends JpaRepository<User,Integer> {

    Optional<User> findByUserName(String userName);
//
@Query("""
        SELECT u FROM User u
        JOIN u.attributes a
        WHERE (a.attributeName, a.attributeValue) IN :pairs
        GROUP BY u
        HAVING COUNT(DISTINCT a) = :attrCount
    """)
List<User> findUsersByAttributes(
        @Param("pairs") List<Object[]> pairs,
        @Param("attrCount") long attrCount
);
    //void deleteAttribute(List<Attribute> searchAttribute, List<Attribute> deleteAttribute);
    int deleteByUserName(String userName);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.attributes a where a.attributeName IN (:attributeNames)")
    List<User> findAllUserWithAttributeNames(List<Object[]> attributeNames);


}
