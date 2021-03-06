package com.apress.springrecipes.court.web;

import java.util.List;

import com.apress.springrecipes.court.domain.Reservation;
import com.apress.springrecipes.court.service.ReservationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/reservationQuery")
public class ReservationQueryController {
	
	private ReservationService reservationService;
	
	@Autowired
	public void ReservationQueryController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public void setupForm() {
	
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String sumbitForm(@RequestParam("courtName") String courtName,Model model) {
		List<Reservation> reservations = java.util.Collections.emptyList();
		if (courtName != null) {
			reservations = reservationService.query(courtName);
		}
		model.addAttribute("reservations", reservations);
		return "reservationQuery";
	}
}