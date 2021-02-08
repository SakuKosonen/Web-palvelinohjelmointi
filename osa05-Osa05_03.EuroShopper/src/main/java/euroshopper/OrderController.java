package euroshopper;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderController {
    
    @Autowired
    ShoppingCart cart;
    
    @Autowired
    private ItemRepository itemRepository;
    
    @Autowired
    private OrderRepository orderRepository;
    
    @RequestMapping("/orders")
    public String list(Model model) {
        model.addAttribute("orders", orderRepository.findAll());
        return "orders";
    }
    
    @PostMapping("/orders")
    public String order(@RequestParam String name, @RequestParam String address) {
        
        Order order = new Order();
        order.setName(name);
        order.setAddress(address);
       
        List<OrderItem> lista = new ArrayList();
        for (Item item : cart.getItems().keySet()) {
            OrderItem oi = new OrderItem();
            oi.setItem(item);
            oi.setItemCount(cart.getItems().get(item));
            lista.add(oi);
        }
        order.setOrderItems(lista);
        cart.resetItems();
        orderRepository.save(order);
        
        return "redirect:/orders";
    }
}
