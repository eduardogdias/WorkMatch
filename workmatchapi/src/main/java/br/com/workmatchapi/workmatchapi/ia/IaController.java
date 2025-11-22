package br.com.workmatchapi.workmatchapi.ia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ia")
public class IaController {

    @Autowired
    private OllamaService iaService;

    public IaController(OllamaService iaService) {
        this.iaService = iaService;
    }

    @GetMapping("/perguntar")
    public String perguntar(@RequestParam String texto) {
        return iaService.perguntar(texto);
    }

}
