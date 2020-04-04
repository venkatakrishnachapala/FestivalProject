package com.cts.project.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.cts.project.bean.VisitorBean;
import com.cts.project.bean.VisitorLoginBean;
import com.cts.project.entity.EventEntity;
import com.cts.project.entity.VisitorEntity;
import com.cts.project.service.EventService;
import com.cts.project.service.VisitorServiceInterface;

@Controller
@RequestMapping(value = "/visitor")
public class VisitorController {

	@Autowired
	private VisitorServiceInterface service;

	@Autowired
	private EventService eService;

	/* login */
	@RequestMapping(value = "/logIn.html", method = RequestMethod.GET)
	public String setupForm(Model model) {
		VisitorLoginBean vLogin = new VisitorLoginBean();
		System.out.println("login bean");
		model.addAttribute("vLogin", vLogin);
		return "index";
	}

	@RequestMapping(value = "/searchVisitor.html", method = RequestMethod.POST)
	public String submitForm(@ModelAttribute("vLogin") VisitorLoginBean vLogin, BindingResult result,
			SessionStatus status, Model theModel) {
		// Validation code start
		boolean error = false;

		System.out.println(vLogin); // Verifying if information is same as input by user
		if (vLogin.getUserName().isEmpty() == true || vLogin.getPassWord().isEmpty() == true) {
			if (vLogin.getUserName().isEmpty()) {
				result.rejectValue("userName", "error.userName");
				error = true;
			}
			if (vLogin.getPassWord().isEmpty()) {
				result.rejectValue("passWord", "error.passWord");
				error = true;
			}
		}
		if ((vLogin.getUserName().isEmpty()) == false && (vLogin.getPassWord().isEmpty()) == false) {
			String out = service.validateVisitorLogin(vLogin.getUserName(), vLogin.getPassWord());

			if (out.equals("notFound")) {
				result.rejectValue("userName", "error.noUserFound");
				error = true;
			}
			if (out.equals("incorrect")) {
				result.rejectValue("passWord", "error.wrongPassWord");
				error = true;
			}
		}
		if (error) {
			return "index";
		}

		theModel.addAttribute("visitor", service.getVisitorObject(vLogin.getUserName()));
		theModel.addAttribute("allEvents", eService.showEvent());
		List<EventEntity> list = eService.getRegisteredEvent(service.getId(vLogin.getUserName()));
		if (list.isEmpty()) {
			theModel.addAttribute("messageEvent", "Currently you have not registered to any event.");
		} else {
			theModel.addAttribute("regEvents", list);
		}
		status.setComplete();
		return "visitormain";
	}

	/* registration */
	@RequestMapping(value = "/registration.html")
	public ModelAndView registerVisitor() {
		System.out.println("register visitor");
		return new ModelAndView("registration", "registrationAttribute", new VisitorBean());
	}

	@RequestMapping(value = "/saveVisitor.html", method = RequestMethod.POST)
	public ModelAndView getValues(@ModelAttribute("registrationAttribute") VisitorBean visitorBean, Model theModel) {
		System.out.println("save details");
		theModel.addAttribute("vLogin", new VisitorLoginBean());
		VisitorEntity vEntity = service.getVisitorObject(visitorBean.getUserName());
		if (vEntity != null) {
			theModel.addAttribute("isRegistered", "username already exists, login");
			return new ModelAndView("index");
		} else {
			service.saveVisitor(visitorBean);

			return new ModelAndView("index");
		}
	}

	/* update visitor details */
	@RequestMapping(value = "/updateDetail.html")
	public String updateVisistorDetail(@RequestParam("vName") String userName, Model theModel) {
		System.out.println("Inside UpdateVisitor Detail");
		VisitorEntity vEntity = service.getVisitorObject(userName);
		theModel.addAttribute("registrationAttribute", vEntity);

		return "updateRegistration";
	}

	@RequestMapping(value = "/updateVisitor.html", method = RequestMethod.POST)
	public String updateValues(@ModelAttribute("registrationAttribute") VisitorBean visitorBean, Model theModel) {
		theModel.addAttribute("visitor", service.updateVisitorObject(visitorBean));

		System.out.println(visitorBean);
		theModel.addAttribute("vLogin", new VisitorLoginBean());
		theModel.addAttribute("allEvents", eService.showEvent());
		List<EventEntity> list = eService.getRegisteredEvent(service.getId(visitorBean.getUserName()));
		if (list.isEmpty()) {
			theModel.addAttribute("messageEvent", "Currently you have not registered to any event.");
		} else {
			theModel.addAttribute("regEvents", list);
		}
		theModel.addAttribute("visitor", service.getVisitorObject(visitorBean.getUserName()));
		theModel.addAttribute("message", "Details updated Successfully");
		return "visitormain";
	}

	@RequestMapping(value = "/changePWD.html")
	public ModelAndView changePWD(@RequestParam("uName") String userName, Model theModel) {
		theModel.addAttribute("status", userName);
		return new ModelAndView("changePWD");
	}

	@RequestMapping(value = "/updatePWD.html", method = RequestMethod.POST)
	public ModelAndView updatePwd(@RequestParam("status") String userName, @RequestParam("password") String pwd,
			Model theModel) {
		boolean out = service.changePassword(userName, pwd);
		if (out == true) {
			theModel.addAttribute("message", "Password Changed SuccessFully");
		} else {
			theModel.addAttribute("message", "something Happened, unable to change password");

		}
		List<EventEntity> list = eService.getRegisteredEvent(service.getId(userName));
		if (list.isEmpty()) {
			theModel.addAttribute("messageEvent", "Currently you have not registered to any event.");
		} else {
			theModel.addAttribute("regEvents", list);
		}
		theModel.addAttribute("visitor", service.getVisitorObject(userName));
		return new ModelAndView("visitormain");

	}

	@RequestMapping(value = "/eventreg.html")
	public ModelAndView toRegisterEent(@RequestParam("visitorId") int visitorId, @RequestParam("eventId") int eventId,
			Model theModel) {
		if (!eService.isAlreadyRegistered(visitorId, eventId)) {
			theModel.addAttribute("event", eService.showEvent(eventId));
			theModel.addAttribute("visitorId", visitorId);
			return new ModelAndView("registerEvent");
		} else
			theModel.addAttribute("visitor", service.getVisitor(visitorId));
		List<EventEntity> list = eService.getRegisteredEvent(visitorId);
		if (list.isEmpty()) {
			theModel.addAttribute("messageEvent", "Currently you have not registered to any event.");
		} else {
			theModel.addAttribute("regEvents", list);
		}
		theModel.addAttribute("message", "You have already booked tickets for this event");
		theModel.addAttribute("allEvents", eService.showEvent());
		return new ModelAndView("visitormain");
	}

	@RequestMapping(value = "/confirmSeats.html", method = RequestMethod.POST)
	public String confirmBooking(@RequestParam("visitorId") String visitorId, @RequestParam("eventId") String eventId,
			@RequestParam("noOfSeats") String seats, Model theModel) {

		boolean out = eService.registeredToevent(Integer.parseInt(eventId), Integer.parseInt(visitorId),
				Integer.parseInt(seats));
		List<EventEntity> list = eService.getRegisteredEvent(Integer.parseInt(visitorId));

		if (out == true) {
			if (list.isEmpty()) {
				theModel.addAttribute("messageEvent", "Currently you have not registered to any event.");
			} else {
				theModel.addAttribute("regEvents", list);

			}
			theModel.addAttribute("regEvents", list);
			theModel.addAttribute("visitor", service.getVisitor(Integer.parseInt(visitorId)));
			theModel.addAttribute("message", "Tickets Booked for the Event");
			theModel.addAttribute("allEvents", eService.showEvent());
			return "visitormain";
		}
		theModel.addAttribute("visitor", service.getVisitor(Integer.parseInt(visitorId)));
		theModel.addAttribute("allEvents", eService.showEvent());
		theModel.addAttribute("message", "Unable to book tickets, try again after sometime");
		return "visitormain";

	}

	@RequestMapping("/eventunreg.html")
	public String cancelTicket(@RequestParam("") String visitorId, @RequestParam("") String eventId, Model theModel) {
		boolean out = eService.cancelEventTicket(Integer.parseInt(visitorId), Integer.parseInt(eventId));

		List<EventEntity> list = eService.getRegisteredEvent(Integer.parseInt(visitorId));

		if (list.isEmpty()) {
			theModel.addAttribute("messageEvent", "Currently you have not registered to any event.");
		} else {
			theModel.addAttribute("regEvents", list);

		}
		// theModel.addAttribute("regEvents", list);
		if (out == true) {
			theModel.addAttribute("message", "Tickets canceled succesfully");
		} else {
			theModel.addAttribute("message", "Unable to cancel the tickets, try again after sometime");
		}
		theModel.addAttribute("visitor", service.getVisitor(Integer.parseInt(visitorId)));
		theModel.addAttribute("allEvents", eService.showEvent());
		return "visitormain";

	}
	@RequestMapping("/about.html")
	public String aboutPage()
	{
	return "about";	
	}
	@RequestMapping("/catalog.html")
	public String eventcatalog(Model theModel)
	{
		theModel.addAttribute("allEvents", eService.showEvent());

		return "eventCatalog";
	}
	
	@RequestMapping("/logOut")
	public String logout(Model theModel)
	{
		theModel.addAttribute("message", "logged Out SuccessFully");
		return "index";
	}
	
	@RequestMapping(value="/myportal.html")
	public String vismain()
	{
		return "visitormain";
	}
	
	@RequestMapping(value="/getout.html")
	public String out(Model theModel)
	{
		theModel.addAttribute("message", "logged Out SuccessFully");
		return "index";
	}

}
