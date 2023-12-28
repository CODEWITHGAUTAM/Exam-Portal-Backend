package com.exam.service.impl;

import java.util.Set;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.repo.RoleRepository;
import com.exam.repo.userRepository;
import com.exam.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private userRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository; 
	
	//Creating user
	public User createUser(User user,Set<UserRole> userRoles) 
	{
		User local=this.userRepository.findByUsername(user.getUsername());
		try {
		if(local !=null)
		{
			System.out.println("User is already there !!");
			throw new Exception("Alreday Preasent");
		}
		else
		{
			for(UserRole ur:userRoles)
			{
				roleRepository.save(ur.getRole());
			}
			user.getUserRoles().addAll(userRoles);
			local=this.userRepository.save(user);
			
		}
		}
		catch(Exception e)
		{
			e.getMessage();
			
		}
		
		return local; 
		
	}

	//get user by username
	@Override
	public User getUser(String username) {
		// TODO Auto-generated method stub
		return this.userRepository.findByUsername(username);
	}

	@Override
	public void deleteUser(Long userId) {
		// TODO Auto-generated method stub
		
		this.userRepository.deleteById(userId);
		
		
		
	}

}
