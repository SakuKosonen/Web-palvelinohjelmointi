package reservations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReservationController {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private HttpSession session;

    @PostMapping("/reservations")
    public String addReservation(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate reservationFrom,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate reservationTo) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        Reservation reservation = new Reservation();
        reservation.setReservationFrom(reservationFrom);
        reservation.setReservationTo(reservationTo);
        reservation.setUser(accountRepository.findByUsername(username));

        reservationRepository.save(reservation);

        return "redirect:/reservations";
    }

    @GetMapping("/reservations")
    public String getReservation(Model model) {
        String username = "make";
        String password = "jonne";
        Account a = accountRepository.findByUsername(username);
        if (a == null) {
            a = new Account(username, passwordEncoder.encode(password));
            accountRepository.save(a);
        }

        model.addAttribute("reservations", reservationRepository.findAll());

        return "reservations";
    }

}
