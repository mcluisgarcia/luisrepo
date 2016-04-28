package com.apress.springrecipes.court.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.apress.springrecipes.court.domain.Player;
import com.apress.springrecipes.court.domain.Reservation;
import com.apress.springrecipes.court.domain.ReservationValidator;
import com.apress.springrecipes.court.domain.SportType;
import com.apress.springrecipes.court.service.ReservationService;

@Controller
@RequestMapping("/reservationForm")
@SessionAttributes("reservation")
public class ReservationFormController {

	private ReservationService reservationService;
	private ReservationValidator reservationValidator;

	@Autowired
	// /sirve para inyectar dependencias
	public ReservationFormController(ReservationService reservationService,
			ReservationValidator reservationValidator) {
		this.reservationService = reservationService;
		this.reservationValidator = reservationValidator;
	}

	@ModelAttribute("sportTypes")//variable que se encuentra en el formulario ${sportTypes}
	public List<SportType> populateSportTypes() {
		return reservationService.getAllSportTypes();
	}

	/*
	 * @RequestMapping(method = RequestMethod.GET) public String setupForm(Model
	 * model) { Reservation reservation = new Reservation();
	 * model.addAttribute("reservation", reservation); return "reservationForm";
	 * }
	 */

	@RequestMapping(method = RequestMethod.POST)
	public String submitForm(
			@ModelAttribute("reservation") Reservation reservation,
			BindingResult result, SessionStatus status, Model model) {
		reservationValidator.validate(reservation, result);
		if (result.hasErrors()) {
			model.addAttribute("reservation", reservation);
			return "reservationForm";
		} else {
			reservationService.make(reservation);
			return "reservationSuccess";
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(
			@RequestParam(required = false, value = "username") String username,
			Model model) {
		//commnadClass
		Reservation reservation = new Reservation();
		reservation.setPlayer(new Player(username, null));
		
		model.addAttribute("reservation", reservation);//commandObject
		return "reservationForm";
	}

}
