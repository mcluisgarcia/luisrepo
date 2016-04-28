package controller;

import java.util.Date;
import java.util.List;

import modelo.SumaServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/suma")
public class SummaController {

	private SumaServicio sumaService;
	
	@Autowired
	public void SummaController(SumaServicio sumaService) {
		this.sumaService = sumaService;
	}
		
	@RequestMapping(method = RequestMethod.GET)
	public void setupForm() {
	
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String sumbitForm(@RequestParam("n1") int n1, @RequestParam("n2") int n2,Model model) {
		int res = 0;
		if (n1 != 0 && n2 != 0) {
			res = sumaService.suma(n1, n2);
		}
		model.addAttribute("suma", "La suma es: "+res);
		return "suma";
	}

}
