package com.cts.project.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cts.project.entity.EventEntity;
import com.cts.project.service.EventService;
@Controller
@RequestMapping("/event")
public class EventController {
	@Autowired
	private EventService service;
	
	@RequestMapping(value="/goToEvent.html")
	public ModelAndView goToEvent()
	{ 	System.out.println("In event Registeration");
		return new ModelAndView("eventForm", "eventAttribute", new EventEntity());
	}
	@RequestMapping(value="/eventAdd.html", method = RequestMethod.POST)
	public ModelAndView eventCreated(@ModelAttribute("eventAttribute") EventEntity eventEntity)
	{
		service.saveEvent(eventEntity);
		
		return new ModelAndView("eventAddSuccess");
	}
	@RequestMapping(value="/showEvent.html")
	public String showEventCatalog(Model theModel)
	{
		List list = service.showEvent();
		theModel.addAttribute("allEvents", list);
		return "eventsCatalog";
	}
	@RequestMapping("/login.html")
	public String login()
	{
		return "redirect:/visitor/logIn.html";
	}
	
	@RequestMapping("/logOut.html")
	public String logout()
	{
		return "redirect:/visitor/logOut.html";
	}
	
	@RequestMapping("/about.html")
	public String about()
	{
		return "redirect:/visitor/about.html";
	}
	
}
