package dev.fringe.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class MainController {
	@RequestMapping("/")
	String main(Model m) {
		String title = '''
t
dff
fdf
fsf
fdff
'''
		println title
		m.addAttribute("message", "dfdf")
		m.addAttribute("title", title)
		return "index"
	}
}
