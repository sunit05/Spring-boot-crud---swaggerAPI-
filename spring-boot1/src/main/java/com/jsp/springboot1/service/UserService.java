package com.jsp.springboot1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.jsp.springboot.exception.NoSuchUserIdFoundException;
import com.jsp.springboot1.dao.UserDao;
import com.jsp.springboot1.dto.ResponseStructure;
import com.jsp.springboot1.dto.User;

@Service
public class UserService {

	@Autowired
	UserDao userDao;
	
	public User validateUser(String email,String password) {
		return userDao.validateUser(email, password);
	}
	
	public ResponseStructure<User> saveUser(User user) {
		
		if(user!=null) {
			ResponseStructure<User> responseStructure=new ResponseStructure<>();
			responseStructure.setData(userDao.saveUser(user));
			responseStructure.setStatus_code(HttpStatus.CREATED.value());
			responseStructure.setMessage("user saved successfully");
			return responseStructure;
		}
		else {
			ResponseStructure<User> responseStructure=new ResponseStructure<>();
			responseStructure.setData(null);
			responseStructure.setMessage("User not found to save");
			responseStructure.setStatus_code(400);
			return responseStructure;
		}
	}
	
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}
	
	public ResponseStructure<User> getUserById(int id) throws NoSuchUserIdFoundException {
		User user=userDao.getUserById(id);
		ResponseStructure<User> responseStructure=new ResponseStructure<>();
		if(id>0) {
			responseStructure.setData(user);
			responseStructure.setMessage("user found");
			responseStructure.setStatus_code(HttpStatus.CREATED.value());
			return responseStructure;
		}
		else {
			throw new NoSuchUserIdFoundException();
		}
	}
	
	public boolean deleteUserById(int id) {
		return userDao.deleteUserById(id);
	}
	
	public User UpdateUserById(int id,User user) {
		User u= userDao.getUserById(id);
		if (u!=null){
			u.setName(user.getName());
			u.setEmail(user.getEmail());
			return userDao.UpdateUserById(id, u);
		}
		else {
			return null;
		}
	}
}
