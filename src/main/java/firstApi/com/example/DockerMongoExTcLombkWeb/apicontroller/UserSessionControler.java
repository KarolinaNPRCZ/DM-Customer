package firstApi.com.example.DockerMongoExTcLombkWeb.apicontroller;

import netscape.javascript.JSObject;
import org.bson.json.JsonObject;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserSessionControler {
    @Autowired
    private firstApi.com.example.DockerMongoExTcLombkWeb.apicontroller.UserSessionService UserSessionService;


    @GetMapping
    public List<UserSession> findAll() {
        return UserSessionService.findAll();
    }

    @GetMapping("/{id}")
    public UserSession findById(@PathVariable String id) {
        return UserSessionService.findById(id);
    }

    @PostMapping
    public UserSession create(@RequestBody UserSession userSession) {
        return UserSessionService.save(userSession);
    }

    @PutMapping("/{id}")
    public String update(@RequestBody String messageFromFrontend, @PathVariable String id) throws IOException, InterruptedException {

        // set conversation from frontend
        UserSessionService.update(id, messageFromFrontend.replaceAll("\"", ""));
        System.out.println(messageFromFrontend);

        // save answer to db
        UserSessionService.update(id, "answer");

        // return answer to frontend
        return JSONObject.quote("answer");
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        UserSessionService.deleteById(id);
    }

}
