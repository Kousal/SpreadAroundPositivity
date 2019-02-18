package ca.mini.kousel.SpreadAroundPositivity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



import ca.mini.kousel.SpreadAroundPositivity.utility.*;
import ca.mini.kousel.SpreadAroundPositivity.entity.spreadaroundpositivity;
import ca.mini.kousel.SpreadAroundPositivity.service.CoreService;

@RestController
@Controller
@RequestMapping("/spreadaroundpositivity")
public class CoreController {
	
	@Autowired
	CoreService coreservice;
	
	@RequestMapping(path="/getUserMessages/{user}", method=RequestMethod.GET)
	public ResponseEntity<JSONModel> getMessagesforUser(@PathVariable(name="user")String username) {
		ResponseEntity<JSONModel> resp = null;
		JSONModel jsonModel = null;
		try {
			
			spreadaroundpositivity usercomments = coreservice.getUserRecords(username);
			jsonModel = JSONModelHelper.processJSONModelForObject("200", "User created successfully", usercomments);
			resp = ResponseEntity.status(HttpStatus.OK).body(jsonModel);
		} catch (Exception e) {
			jsonModel = JSONModelHelper.processJSONModelForObject("500", "INVALID REQUEST", e.getMessage());
			resp = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonModel);
		}
		return resp;
	}
	
	@RequestMapping(path="/checkUsernameAvailable/{user}", method=RequestMethod.GET)
	public boolean checkUsernameAvailable(@PathVariable(name="user")String username)
	{
		if(coreservice.getUserRecords(username) == null)
			return true;
		else
			return false;
	}
	
	@RequestMapping(path="/createUser/{user}", method=RequestMethod.GET)
	public boolean createUser(@PathVariable(name="user") String username)
	{
		return coreservice.createUser(username);
	}
	
	@RequestMapping(path="/addComments", method=RequestMethod.POST)
	public ResponseEntity<JSONModel> addComments(@RequestBody spreadaroundpositivity usercommentstoadd)
	{
		String username = usercommentstoadd.getUsername();
		
		ResponseEntity<JSONModel> resp = null;
		JSONModel jsonModel = null;
		try {
			
			if(coreservice.addComments(usercommentstoadd))
			{
			spreadaroundpositivity usercomments = coreservice.getUserRecords(username);
			jsonModel = JSONModelHelper.processJSONModelForObject("200", "User created successfully", usercomments);
			resp = ResponseEntity.status(HttpStatus.OK).body(jsonModel);
			}
			else
			{
				jsonModel = JSONModelHelper.processJSONModelForObject("500", "Comment cannot be added", "Comments not added");
				resp = ResponseEntity.status(HttpStatus.OK).body(jsonModel);
			}
		} catch (Exception e) {
			jsonModel = JSONModelHelper.processJSONModelForObject("500", "INVALID REQUEST", e.getMessage());
			resp = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonModel);
		}
		return resp;
	}
}

