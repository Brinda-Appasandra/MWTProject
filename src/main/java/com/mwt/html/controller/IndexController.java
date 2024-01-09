package com.mwt.html.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import com.mwt.html.model.User;
import com.mwt.html.repo.UserRepository;

@Controller
public class IndexController {
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/")
	public String index() {
		
		return "index";
	}
	
	@PostMapping("/register")
	public String userRegistration(@ModelAttribute User user, Model model) {
		System.out.println(user.toString());
		// validate 
		System.out.println(user.getFname());
		System.out.println(user.getLname());
		System.out.println(user.getDob());
		System.out.println(user.getEmail());
		model.addAttribute("firstname", user.getFname());
		model.addAttribute("lastname", user.getLname());
		model.addAttribute("username",user.getUsername());
		model.addAttribute("dob",user.getDob());
		model.addAttribute("gender",user.getGender());
		
		User userInserted=userRepository.save(user);
		model.addAttribute("id",userInserted.getId());
		
		model.addAttribute("message", userInserted.getFname()+" is inserted");
		return "welcome";
	}
	@PostMapping("/edit")
	public String userEdit(@ModelAttribute User question, Model model) {
	
	
Optional<User> entity = userRepository.findById(question.getId());
User ques = entity.get();
ques.setUsername(question.getEdituser());
User userInserted=userRepository.save(ques);
model.addAttribute("firstname", ques.getFname()+ques.getLname());
model.addAttribute("lastname", ques.getLname());
model.addAttribute("username", ques.getUsername());
model.addAttribute("dob", ques.getDob());
model.addAttribute("gender",ques.getGender());
model.addAttribute("id",ques.getId());
question.setId(userInserted.getId());
model.addAttribute("message", "Username is edited. Below are the updated details");
return "welcome";
	}
	
	@PostMapping("/delete")
	public String deleteEntoty(@ModelAttribute User question, Model model) {
		System.out.print("done"+question.getId());
		  userRepository.deleteById(question.getId());
	
	        return "delete";
	    }
}
