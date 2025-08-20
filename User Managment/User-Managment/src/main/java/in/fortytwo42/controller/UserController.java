package in.fortytwo42.controller;

import in.fortytwo42.dtos.AttributeDto;
import in.fortytwo42.dtos.CreateResponse;
import in.fortytwo42.entities.Attribute;
import in.fortytwo42.entities.User;
import in.fortytwo42.services.UserService;
//import org.hibernate.query.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {
    @Autowired
    UserService userService;

    public UserController(UserService userService ) {
        this.userService=userService;
//
    }
    @PostMapping("/users")
    public CreateResponse addUser(@RequestBody User user) throws Exception {
        CreateResponse savedUser=userService.addUser(user);
        return new CreateResponse(
                "user created successfully.",
                savedUser.getUserId()
        );
    }
    @PostMapping("/users/search")
    public List<User> searchUser(@RequestBody AttributeDto dto){
        List<Attribute> searchAttribute= dto.getSearchAttribute();
        if(searchAttribute == null || searchAttribute.isEmpty()){
            return  new ArrayList<>();
        }
        return userService.searchUser(searchAttribute);
    }



    @PostMapping("/users/attributes/add")
    public ResponseEntity<CreateResponse> addAttribute(@RequestBody Map<String,List<Attribute>> request){
        List<Attribute> searchAttribute= request.get("searchAttribute");
        List<Attribute> newAttribute = request.get("newAttribute");
        CreateResponse response=userService.addAttribute(searchAttribute,newAttribute);
        if ("User not found".equals(response.getMessage())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }
    @DeleteMapping("/users/attributes/delete")
    public ResponseEntity<CreateResponse> deleteAttribute(@RequestBody Map<String,List<Attribute>> request){
        List<Attribute> searchAttribute= request.get("searchAttribute");
        List<Attribute> deleteAttribute=request.get("deleteAttribute");
        CreateResponse response=userService.deleteAttribute(searchAttribute,deleteAttribute);
        if ("User not found".equals(response.getMessage())||"No matching attribute found.".equalsIgnoreCase(response.getMessage())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PutMapping("users/attributes/update")
    public ResponseEntity<CreateResponse> updateAttribute(@RequestBody Map<String,Object> request){
       List<Map<String,String>> searchAttributeList= (List<Map<String, String>>) request.get("searchAttribute");
       List<Attribute> searchAttribute=new ArrayList<>();
       for(Map<String,String> map:searchAttributeList){
           Attribute attr=new Attribute();
           attr.setAttributeName(map.get("attributeName"));
           attr.setAttributeValue(map.get("attributeValue"));
           searchAttribute.add(attr);
       }
        String attributeName =(String) request.get("attributeName");
        String oldAttributeValue =(String) request.get("oldAttributeValue");
        String newAttributeValue =(String) request.get("newAttributeValue");
        CreateResponse response=userService.updateAttribute(searchAttribute,attributeName,oldAttributeValue,newAttributeValue);
        if ("User not found".equals(response.getMessage())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @DeleteMapping("/{username}")
    public ResponseEntity<CreateResponse> deleteUser(@PathVariable String username) throws Exception{
        CreateResponse a= userService.deleteUser(username);
        if ("User deleted successfully.".equals(a.getMessage())) {
            return ResponseEntity.ok().body(a);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(a);
        }
    }
}
