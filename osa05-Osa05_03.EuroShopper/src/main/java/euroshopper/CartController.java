/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package euroshopper;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartController {

    @Autowired
    ShoppingCart cart;

    @Autowired
    ItemRepository itemRepo;

    @GetMapping("/cart")
    public String list(Model model) {

        model.addAttribute("items", cart.getItems());
        model.addAttribute("sum", 10);
        return "cart";
    }

    @PostMapping("/cart/items/{id}")
    public String order(@PathVariable Long id) {
        Item item = itemRepo.getOne(id);

        cart.addToCart(item);

        return "redirect:/cart";
    }

}
