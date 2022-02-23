package br.com.alura.mvc.mudi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;
import br.com.alura.mvc.mudi.services.PedidoService;

@Controller
@RequestMapping("/home")
public class HomeController {

	@Autowired
	private PedidoService service;

	@GetMapping()
	public String home(Model model) {
		Sort sort = Sort.by("data").descending();

		PageRequest pr = PageRequest.of(0, 10, sort);

		List<Pedido> pedidos = service.findByStatus(StatusPedido.ENTREGUE, pr);
		model.addAttribute("pedidos", pedidos);
		return "home";
	}

}