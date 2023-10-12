package com.example.demo.controller;

import java.sql.Time;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.response;
import com.example.demo.service.DataService;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/usersdata")
public class ApiController {
	private final DataService ds;
	
	@Autowired
	public ApiController(DataService ds) {
		this.ds = ds;
	}
	
	//user
	
	//example url : http://localhost:8080/api/usersdata/createuserinfo?email=leungyulap87@gmail.com&password=a36255137&username=jchocoiii
	//test success 25/9 10:25
	@PostMapping("/createuserinfo")
	public ResponseEntity<String> createUserInfo(
			@RequestParam(name = "email") String email, @RequestParam(name = "password") String password, 
			@RequestParam(name = "username") String username
			) {
		ds.createUserInfo(email, password, username);
		return ResponseEntity.ok("User added successfully");
	}
	
	//http://localhost:8080/api/usersdata?email=jimmyjimmy26282@gmail.com&password=a36255137
	//test success 25/9 10:25
	@GetMapping 
	public response getUserInfo(@RequestParam(name = "email") String email, @RequestParam(name = "password") String password) {
		response r = ds.getUserInfo(email, password);
		return r;
	}
	
	//custom lists
	
	//http://localhost:8080/api/usersdata/addtodolist?listname=NewList
	//test success 25/9 10:28
	@PostMapping("/addtodolist")
    public ResponseEntity<String> addToDoList(@RequestParam(name = "listname") String listname, @RequestParam(name = "userid") Long userid) {
        ds.addToDoList(listname, userid);
        return ResponseEntity.ok("To-do list added successfully");
    }
	
	//test success 25/9 10:29
	@PostMapping("/modifytodolist")
	public ResponseEntity<String> modifyToDoList(@RequestParam(name = "listid") Long listid, @RequestParam(name = "listname") String listname) {
		ds.modifyToDoList(listid, listname);
		return ResponseEntity.ok("To-do list modified successfully");
	}
	
	//http://localhost:8080/api/usersdata/delToDoList?listid=5
	//test success 25/9 11:04
	@DeleteMapping("/delToDoList")
	public ResponseEntity<String> delToDoList(@RequestParam Long listid) {
		ds.delToDoList(listid);
	    return ResponseEntity.ok("To-do list deleted successfully");
	}
	
	//tasks inside lists
	
	//http://localhost:8080/api/usersdata/addtask?listid=1&title=Sample%20Task&location=Sample%20Location&from=2023-09-25T10:00&to=2023-09-25T12:00&traveltime=01:30:00&notes=Sample%20Notes
	//test success 25/9 10:48
	@PostMapping("/addtask")
    public ResponseEntity<String> addTask(
            @RequestParam(name = "listid") Long listid,
            @RequestParam(name = "title") String title,
            @RequestParam(name = "location") String location,
            @RequestParam(name = "from") LocalDateTime from,
            @RequestParam(name = "to") LocalDateTime to,
            @RequestParam(name = "traveltime") String traveltime,
            @RequestParam(name = "notes") String notes
    ) {
		Time sqlTraveltime = null; 

	    if (traveltime != null && !traveltime.isEmpty()) {
	        sqlTraveltime = Time.valueOf(traveltime);
	    }
        ds.addTask(listid, title, location, from, to, sqlTraveltime, notes);
        return ResponseEntity.ok("Task added successfully");
    }
	
	//test success 25/9 10:51
	@PostMapping("/modifytask")
	public ResponseEntity<String> modifyTask(
			@RequestParam(name = "taskid") Long taskid,
            @RequestParam(name = "title") String title,
            @RequestParam(name = "location") String location,
            @RequestParam(name = "from") LocalDateTime from,
            @RequestParam(name = "to") LocalDateTime to,
            @RequestParam(name = "traveltime") String traveltime,
            @RequestParam(name = "notes") String notes
	) {
		Time sqlTraveltime = null; 

	    if (traveltime != null && !traveltime.isEmpty()) {
	        sqlTraveltime = Time.valueOf(traveltime);
	    }
	    ds.modifyTask(taskid, title, location, from, to, sqlTraveltime, notes);
		return ResponseEntity.ok("Task modified successfully");
	}
	
	//test success 25/9 10:53
	@DeleteMapping("/delTask")
	public ResponseEntity<String> delTask(@RequestParam Long taskid) {
		ds.delTask(taskid);
		return ResponseEntity.ok("Task deleted successfully");
	}
}
