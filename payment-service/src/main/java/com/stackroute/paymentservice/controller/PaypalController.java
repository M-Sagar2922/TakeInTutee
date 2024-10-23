package com.stackroute.paymentservice.controller;

//import com.example.payment.entity.Order;
//import com.example.payment.service.PaypalService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.stackroute.paymentservice.model.Order;
import com.stackroute.paymentservice.service.PaypalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
//@Controller
@RequestMapping("/v2/checkout/orders")
public class PaypalController {

    @Autowired
    private PaypalService paypalService;

    public static final String successUrl = "pay/success";
    public static final String cancelUrl = "pay/cancel";


    @GetMapping("/home")
    public String home(){
        return "Welcome to payment gateway";
    }

    @PostMapping("/pay")
    public String payment(@RequestBody Order theOrder) throws PayPalRESTException {
        try {

            Payment thePayment = paypalService.createPayment(theOrder.getPrice(), theOrder.getCurrency(),
                    theOrder.getPayment_method(), theOrder.getIntent(), theOrder.getDescription(),
                    "http://localhost:8989/v2/checkout/orders/"+cancelUrl,
                    "http://localhost:8989/v2/checkout/orders/"+successUrl);
            System.out.println(thePayment);
            for (Links links : thePayment.getLinks()) {
                if (links.getRel().equals("approval_url")) {
//                    System.out.println(links.getRel());
                    return "redirect:" + links.getHref();
                }
            }
        } catch (PayPalRESTException payPalRESTException) {
            payPalRESTException.printStackTrace();
        }
        return "redirect:/";
    }

    @GetMapping(value = cancelUrl)
    public String cancelPay() {
        return "cancel";
    }

//    @GetMapping(value = successUrl)
//    public String successPay() {
//        return "success";
//    }

    @GetMapping(value = successUrl)
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
                return "<!DOCTYPE html>\n" +
                        "<style>\n" +
                        "div {\n" +
                        "height: 200px;\n" +
                        "width: 400px;\n" +
                        "background: white;\n" +
                        "\n" +
                        "position: fixed;\n" +
                        "top: 50%;\n" +
                        "left: 50%;\n" +
                        "margin-top: -100px;\n" +
                        "margin-left: -200px;\n" +
                        "}\n" +
                        "</style>\n" +
                        "<html>\n" +
                        "<head>\n" +
                        "    <meta charset=\"UTF-8\" />\n" +
                        "    <title>Payment Status</title>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "\n" +
                        "<div>\n" +
                        "<h1 style=\"background-color:powderblue;\"><center>Payment Success</center> </h1>\n" +
                        "</div>\n" +
                        "</body>\n" +
                        "</html>";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/";
    }

}
