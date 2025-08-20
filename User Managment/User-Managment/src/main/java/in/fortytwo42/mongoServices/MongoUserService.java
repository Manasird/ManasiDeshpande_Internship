package in.fortytwo42.mongoServices;

import in.fortytwo42.documents.Attribute;
import in.fortytwo42.documents.User;
import in.fortytwo42.dtos.CreateResponse;

import java.util.List;

public interface MongoUserService {
    CreateResponse addUser(User user) throws Exception;
    List<User> searchUser(List<Attribute> searchAttribute);
    CreateResponse addAttribute(List<Attribute> searchAttribute, List<Attribute> newAttribute);
    CreateResponse deleteAttribute(List<Attribute> searchAttribute, List<Attribute> deleteAttribute);
    CreateResponse updateAttribute(List<Attribute> searchAttribute, String attributeName, String attributeValue, String newAttributeValue);
    CreateResponse deleteUser(String userName) throws Exception;
}
