package vehiculo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/vehicleForm")
public class VehicleFormController {

	private VehicleDAO vehicledao;
	
	@Autowired
	public VehicleFormController(VehicleDAO vehicledao) {
		// TODO Auto-generated constructor stub
		this.vehicledao = vehicledao;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String setupForm(Model model){
		
		Vehicle vehicle = new Vehicle();
		model.addAttribute("vehicle", vehicle);
		return "vehicleForm";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String submitForm(@ModelAttribute("vehicle") Vehicle vehicle){
		vehicledao.insert(vehicle);
		return "vehicleForm";
	}

}
