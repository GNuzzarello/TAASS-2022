package com.example.TAASS2022.controller;

import com.example.TAASS2022.dto.OrderTicketDto;
import com.example.TAASS2022.exception.ResourceNotFoundException;
import com.example.TAASS2022.model.Order;
import com.example.TAASS2022.model.OrderStatus;
import com.example.TAASS2022.model.OrderTicket;
import com.example.TAASS2022.service.OrderService;
import com.example.TAASS2022.service.OrderTicketService;
import com.example.TAASS2022.service.TicketService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    TicketService ticketService;
    OrderService orderService;
    OrderTicketService orderTicketService;

    public OrderController(TicketService ticketService, OrderService orderService, OrderTicketService orderTicketService) {
        this.ticketService = ticketService;
        this.orderService = orderService;
        this.orderTicketService = orderTicketService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public @NotNull Iterable<Order> list() {
        return this.orderService.getAllOrders();
    }

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody OrderForm form) {
        List<OrderTicketDto> formDtos = form.getTicketOrders();
        //validateTicketsExistence(formDtos);
        Order order = new Order();
        order.setStatus(OrderStatus.PAID.name());
        order = this.orderService.create(order);
        //arrivati a questo punto ho creato la prima tabella nel db order(id, data, stato="PAID")

        List<OrderTicket> orderTickets = new ArrayList<>();
        for (OrderTicketDto dto : formDtos) {
            orderTickets.add(orderTicketService.create(new OrderTicket(order, ticketService.getTicket(dto.getTicket().getId()),
                    dto.getQuantity())));
        }

        order.setOrderTickets(orderTickets);

        this.orderService.update(order);

        String uri = ServletUriComponentsBuilder
                .fromCurrentServletMapping()
                .path("/orders/{id}")
                .buildAndExpand(order.getId())
                .toString();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", uri);

        return new ResponseEntity<>(order, headers, HttpStatus.CREATED);
    }

    private void validateTicketsExistence(List<OrderTicketDto> orderTickets) {
        List<OrderTicketDto> list = orderTickets
                .stream()
                .filter(op -> Objects.isNull(ticketService.getTicket(op.getTicket().getId())))
                .collect(Collectors.toList());

        if (!CollectionUtils.isEmpty(list)) {
            new ResourceNotFoundException("Ticket not found");
        }
    }

    public static class OrderForm {

        private List<OrderTicketDto> ticketOrders;

        public List<OrderTicketDto> getTicketOrders() {
            return ticketOrders;
        }

        public void setTicketOrders(List<OrderTicketDto> ticketOrders) {
            this.ticketOrders = ticketOrders;
        }
    }
}