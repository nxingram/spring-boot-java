package com.generation.fileupload.mvcctrl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.generation.fileupload.entity.Veicolo;


@Controller
@RequestMapping("/")
public class IndexCtrl {

	@GetMapping
	public String index(Model model) {
		model.addAttribute("veicolo", new Veicolo());
		return "veicolo-form";
	}
}
