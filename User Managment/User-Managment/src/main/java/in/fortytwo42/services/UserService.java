package in.fortytwo42.services;

import in.fortytwo42.dtos.CreateResponse;
import in.fortytwo42.entities.Attribute;
import in.fortytwo42.entities.User;
//import org.hibernate.query.Query;

import java.util.List;

public interface UserService {
    public CreateResponse addUser(User user) throws Exception;

    // public String addUser(addUserRequest req)
    CreateResponse deleteUser(String username) throws Exception;

  CreateResponse addAttribute(List<Attribute> searchAttribute, List<Attribute> newAttribute);

    List<User> searchUser(List<Attribute> searchAttribute);
    CreateResponse updateAttribute(List<Attribute> searchAttribute, String attributeName, String attributeValue, String newAttributeValue);
    CreateResponse deleteAttribute(List<Attribute> searchAttribute, List<Attribute> deleteAttribute);

   // Query createQuery(String string, Class<User> userClass);
}
