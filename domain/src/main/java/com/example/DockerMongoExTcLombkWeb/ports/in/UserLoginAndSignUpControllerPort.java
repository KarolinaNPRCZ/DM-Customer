package com.example.DockerMongoExTcLombkWeb.ports.in;


public interface UserLoginAndSignUpControllerPort<RP, RQ> {

    RP createUser(RQ rq);

    RP getUserByUserEmail(String email);
}
