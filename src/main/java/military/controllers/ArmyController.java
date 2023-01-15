package military.controllers;

import military.dao.ArmyDAO;
import military.models.Army;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/armies")
public class ArmyController {
	private final ArmyDAO armyDao;

	@Autowired
	public ArmyController(ArmyDAO armyDao) {
		this.armyDao = armyDao;
	}

	@GetMapping()
	public String index(Model model) {
		List<Army> armies = armyDao.index();
		System.out.println();
		model.addAttribute("armies", armies);
		return "army/index";
	}
}
