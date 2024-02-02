package com.nprcz.dmcustomer.error;

import com.nprcz.dmcustomer.user.UserAlreadyExistsException;
import com.nprcz.dmcustomer.user.UserNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class UserLoginAndSignUpControllerTest {


  @PostMapping("/test/001")
  UserAlreadyExistsResponse userAlreadyExistsResponseTest(){
   throw new UserAlreadyExistsException("User with given e-mail already exists");
  }
 @PostMapping("/test/002")
 UserAlreadyExistsResponse userAlreadyExistsResponseTest_2(){
  throw new UserNotFoundException("");
 }
}
