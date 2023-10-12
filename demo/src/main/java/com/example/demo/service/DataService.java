package com.example.demo.service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.Lists;
import com.example.demo.dto.ListsTasks;
import com.example.demo.dto.UserInfo;
import com.example.demo.dto.response;
import com.example.demo.repos.ListsRepo;
import com.example.demo.repos.ListsTasksRepo;
import com.example.demo.repos.UserInfoRepo;

@Service
public class DataService {

    @Autowired
    private UserInfoRepo userInfoRepo;

    @Autowired
    private ListsRepo listsRepo;

    @Autowired
    private ListsTasksRepo listsTasksRepo;
    
    private List<UserInfo> userinfo = new ArrayList<>();
    
    //verify user
    public boolean verification(String email, String password) {
    	userinfo = userInfoRepo.findByEmailAndPassword(email, password);
    	if (userinfo != null && userinfo.size() == 1) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    
    //user CR
    public void createUserInfo(String email, String password, String username) {
    	UserInfo userinfo = new UserInfo();
    	userinfo.setEmail(email);
    	userinfo.setPassword(password);
    	userinfo.setUsername(username);
    	
    	userInfoRepo.save(userinfo);
    }

    public response getUserInfo(String email, String password) {
        // Verify user credentials
        boolean isUserValid = verification(email, password);

        // Create a new response object for each request
        response r = new response();

        if (isUserValid) {
            // If the user is valid, fetch user information
            userinfo = userInfoRepo.findByEmailAndPassword(email, password);

            if (userinfo != null) {
                UserInfo info = userinfo.get(0);
                r.setUserInfo(userinfo);

                // Fetch lists related to the user
                List<Lists> lists = listsRepo.findByUserid(info.getUserId());

                if (!lists.isEmpty()) {
                    r.setLists(lists);

                    for (Lists list : lists) {
                        // Fetch tasks related to each list
                        List<ListsTasks> liststasks = listsTasksRepo.findByListid(list.getListid());
                        r.addListsTasksInto(liststasks);
                    }
                }
            }
        } else {
            // If user credentials are not valid, return a null response
            return null;
        }

        return r;
    }

    
    //custom lists CUD
    public void addToDoList(String listname, Long userid) {
    	
    	//if frontend setup we no longer need to read again here cuz it received fulldata, can send userid
    	//userinfo = userInfoRepo.findByEmailAndPassword(email, password);
    	
    	Lists newLists = new Lists();
	    newLists.setUserid(userid);
	    newLists.setListname(listname);
	    newLists.setIsdefault(false);
	    
	    listsRepo.save(newLists);
    }
    
    public void modifyToDoList(Long listid, String listname) {
    	Lists listToModify = listsRepo.findByListid(listid);
    	listToModify.setListname(listname);
    	listsRepo.save(listToModify);
    }
    
    @Transactional
    public void delToDoList(Long listId) {
    	listsTasksRepo.deleteByListid(listId);
    	listsRepo.deleteByListid(listId);
    }
    
    //tasks inside lists CUD
    public void addTask(Long listid, String title, String location, LocalDateTime from, LocalDateTime to, java.sql.Time traveltime, String notes) {
    	ListsTasks liststasks = new ListsTasks();
    	liststasks.setListid(listid);
    	liststasks.setTitle(title);
    	liststasks.setLocation(location);
    	liststasks.setFrom(from);
    	liststasks.setTo(to);
    	liststasks.setTraveltime(traveltime);
    	liststasks.setNotes(notes);
    	listsTasksRepo.save(liststasks);
    }
    
    public void modifyTask(Long taskid, String title, String location, LocalDateTime from, LocalDateTime to, java.sql.Time traveltime, String notes) {
    	ListsTasks taskToModify = new ListsTasks();
        
        // Load the existing task with taskid 9 from the database
        ListsTasks existingTask = listsTasksRepo.findById(taskid).orElse(null);
        
        if (existingTask != null) {
            // Set the listid based on the existing task
            taskToModify.setListid(existingTask.getListid());
            
            // Set other properties
            taskToModify.setTaskid(taskid);
            taskToModify.setTitle(title);
            taskToModify.setLocation(location);
            taskToModify.setFrom(from);
            taskToModify.setTo(to);
            taskToModify.setTraveltime(traveltime);
            taskToModify.setNotes(notes);
            
            // Save the modified task
            listsTasksRepo.save(taskToModify);
        } else {
            // Handle the case where the task with taskid 9 does not exist
            // You can throw an exception or handle it as needed
        }
    }
    
    @Transactional
    public void delTask(Long taskId) {
    	listsTasksRepo.deleteByTaskid(taskId);
    }
      
}
