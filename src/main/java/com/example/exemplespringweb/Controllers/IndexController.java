package com.example.exemplespringweb.Controllers;
import com.example.exemplespringweb.pojos.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.naming.Binding;
import java.util.Date;

@Controller
@RequestMapping("/")    // permet d'indiqué le chemin de base (sera concaténé avec le chemins des méthodes des controllers)
public class IndexController {

    @Autowired
    private HttpSession httpSession;

    @GetMapping({"", "index"})
    public String index(){

        // ajoute un utilisateur dans session
        httpSession.setAttribute("user", new User("Toto", "tata", "TT"));
        return "index";
    }

    @GetMapping("affiche_date")
    public String afficheDate(Model model){

        // met dans le model les données qui seront envoyées à la vue
        model.addAttribute("now", new Date());

        return "affiche_date";
    }

    @GetMapping("request_param")
    public String testRequestParam(Model model, @RequestParam String nom){
        model.addAttribute("nom", nom);

        return "request_param";
    }

    @GetMapping("request_header")
    public String testRequest_header(Model model, @RequestHeader String host)
    {
        model.addAttribute("host", host);

        return"request_header";
    }

    @GetMapping("path_variable/{id}")
    public String testPathVariable(Model model, @PathVariable int id)
    {

        model.addAttribute("id", id);

        return "path_variable";
    }

    @GetMapping("user")
    public String testSessionParam(Model model, @SessionAttribute User user)
    {
        model.addAttribute("user", user);
        return "user_detail";
    }

    @GetMapping("add_user")
    public String userForm(Model model, @ModelAttribute User user){
        model.addAttribute("user", user);

        return "add_user";
    }

    @PostMapping("add_user")
    public String addUser(Model model, @Validated @ModelAttribute User user, BindingResult bindingResult){

        // On réaffiche le formulaire parce qu'il y a eu des erreurs lors du premier remplissage
        if(bindingResult.hasErrors()){
            return "add_user";
        }

        model.addAttribute("user", user);
        return "user_detail";
    }

    @GetMapping("redirect_user")
    public String redirect_user(RedirectAttributes redirectAttributes){
        User user = new User("Dujardin", "Jean", "Jean-jean");
        redirectAttributes.addFlashAttribute("user",user);
        return"redirect:/add_user";
    }
}