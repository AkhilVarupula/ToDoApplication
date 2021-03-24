package com.course.springboot.web.controller;

import java.text.SimpleDateFormat;

import java.util.Date;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.course.springboot.web.model.Todo;
import com.course.springboot.web.service.TodoService;

@Controller
public class TodoController {
	
	//LoginService service = new LoginService();
		@Autowired
		TodoService service;
		
		@InitBinder
		public void initBinder(WebDataBinder binder) {
			//date-dd/mm/yyyy
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
		}
	
		@RequestMapping(value="/list-todos", method=RequestMethod.GET)
		//@ResponseBody 
		public String showTodos( ModelMap model) {
			String name =getLoggedInUserName(model);
			model.put("todos", service.retrieveTodos(name));
			return "list-todos";
		}

		private String getLoggedInUserName(ModelMap model) {
			Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if(principal instanceof UserDetails) {
				return ((UserDetails) principal).getUsername();
			}
			return principal.toString();
		}
		
		@RequestMapping(value="/add-todos", method=RequestMethod.GET)
		//@ResponseBody 
		public String showaddTodos( ModelMap model) {

			model.addAttribute("todo", new Todo(0,getLoggedInUserName(model),"",new Date(),false ));
			return "add-todos";
		}
		@RequestMapping(value="/delete-todo", method=RequestMethod.GET)
		//@ResponseBody 
		public String deleteTodo(@RequestParam int id) {
			service.deleteTodo(id);
			return "redirect:/list-todos";
		}
		@RequestMapping(value="/update-todo", method=RequestMethod.GET)
		//@ResponseBody 
		public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
			Todo todo=service.retrieveTodo(id);
			model.put("todo", todo);
			return "add-todos";
		}
		@RequestMapping(value="/update-todo", method=RequestMethod.POST)
		//@ResponseBody 
		public String updateTodo( ModelMap model,@Valid Todo todo, BindingResult result) {
			if(result.hasErrors()) return "add-todos";
			todo.setUser(getLoggedInUserName(model));
			service.updateTodo(todo);
			return "redirect:/list-todos";
		}
		@RequestMapping(value="/add-todos", method=RequestMethod.POST)
		//@ResponseBody 
		public String addTodos( ModelMap model, @Valid Todo todo, BindingResult result) {
			if(result.hasErrors()) return "add-todos";
			service.addTodo(getLoggedInUserName(model), todo.getDesc(), todo.getTargetDate(), false);
			return "redirect:/list-todos";
		
		}
	}