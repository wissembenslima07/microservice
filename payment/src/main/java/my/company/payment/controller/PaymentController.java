package my.company.payment.controller;


import  my.company.payment.model.Payment;
import  my.company.payment.repository.PaymentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentRepository repo;

    public PaymentController(PaymentRepository repo) {
        this.repo = repo;
    }

    @PostMapping("/pay")
    public ResponseEntity<Payment> pay(@RequestBody Payment p) {
        p.setStatus("COMPLETED");
        p.setDate(new Date());
        Payment saved = repo.save(p);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/<built-in function id>")
    public ResponseEntity<Payment> get(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
