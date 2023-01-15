package military.controllers;

import military.dao.MainDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/military")
public class MainController {
	private final MainDao dao;

	@Autowired
	public MainController(MainDao dao) {
		this.dao = dao;
	}

	@GetMapping("/task1")
	public String showTask1(@RequestParam(name = "number", required = false) Integer number, Model model) {
		if (number == null) {
			model.addAttribute("result", dao.task1());
		} else {
			model.addAttribute("result", dao.task1filtered(number));
		}
		return "tasks/task1";
	}

	@GetMapping("/task2")
	public String showTask2(@RequestParam(name = "rank", required = false) String rank, Model model) {
		if (rank == null) {
			model.addAttribute("result", dao.task2());
		} else {
			model.addAttribute("result", dao.task2filtered(rank));
		}
		return "tasks/task2";
	}

	@GetMapping("/task3")
	public String showTask3(@RequestParam(name = "type", required = false) String type, Model model) {
		if (type == null) {
			model.addAttribute("result", dao.task3());
		} else {
			model.addAttribute("result", dao.task3filtered(type));
		}
		return "tasks/task3";
	}

	@GetMapping("/task5")
	public String showTask5(Model model) {
		model.addAttribute("result", dao.task5());
		return "tasks/task5";
	}

	@GetMapping("/task6")
	public String showTask6(
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "num", required = false) Integer num,
			Model model) {
		if (type == null && num == null) {
			System.out.println(1);
			model.addAttribute("result", dao.task6());
		} else if (type == null) {
			System.out.println(2);
			model.addAttribute("result", dao.task6(num));
		} else if (num == null && ! type.isEmpty()) {
			System.out.println(3);
			model.addAttribute("result", dao.task6(type));
		} else {
			System.out.println(4);
			model.addAttribute("result", dao.task6(type, num));
		}
		return "tasks/task6";
	}

	@GetMapping("/task7")
	public String showTask7(
			Model model
	) {
		return "tasks/task7";
	}
	@GetMapping("/task8")
	public String showTask8(
			Model model
	) {
		return "tasks/task8";
	}
	@GetMapping("/task9")
	public String showTask9(
			Model model
	) {
		return "tasks/task9";
	}
	@GetMapping("/task10")
	public String showTask10(
			Model model
	) {
		return "tasks/task10";
	}
	@GetMapping("/task11")
	public String showTask11(
			Model model
	) {
		return "tasks/task11";
	}
	@GetMapping("/task12")
	public String showTask12(
			Model model
	) {
		return "tasks/task12";
	}
	@GetMapping("/task13")
	public String showTask13(
			Model model
	) {
		return "tasks/task13";
	}
	@GetMapping()
	public String taskList() {
		return "tasks/index";
	}

}
