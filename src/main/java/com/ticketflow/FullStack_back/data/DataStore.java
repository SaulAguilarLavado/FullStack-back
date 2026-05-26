package com.ticketflow.FullStack_back.data;

import com.ticketflow.FullStack_back.models.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class DataStore {
    private static final Map<String, User> users = new ConcurrentHashMap<>();
    private static final Map<String, Role> roles = new ConcurrentHashMap<>();
    private static final Map<String, Event> events = new ConcurrentHashMap<>();
    private static final Map<String, TicketType> ticketTypes = new ConcurrentHashMap<>();
    private static final Map<String, Ticket> tickets = new ConcurrentHashMap<>();
    private static final Map<String, Venue> venues = new ConcurrentHashMap<>();
    private static final Map<String, Order> orders = new ConcurrentHashMap<>();
    private static final Map<String, OrderItem> orderItems = new ConcurrentHashMap<>();

    static {
        Role adminRole = new Role(1, "ADMIN", "Administrator role");
        Role userRole = new Role(2, "USER", "Standard user role");
        roles.put(adminRole.getName(), adminRole);
        roles.put(userRole.getName(), userRole);

        User defaultUser = new User();
        defaultUser.setId("user_1");
        defaultUser.setFirstName("Juan");
        defaultUser.setLastName("Pérez");
        defaultUser.setEmail("usuario@test.com");
        defaultUser.setPhone("3001234567");
        defaultUser.setPwdHash("123456");
        defaultUser.setRole(userRole);
        users.put(defaultUser.getEmail(), defaultUser);

        User defaultAdmin = new User();
        defaultAdmin.setId("admin_1");
        defaultAdmin.setFirstName("Admin");
        defaultAdmin.setLastName("Sistema");
        defaultAdmin.setEmail("admin@test.com");
        defaultAdmin.setPhone("3009999999");
        defaultAdmin.setPwdHash("admin123");
        defaultAdmin.setRole(adminRole);
        users.put(defaultAdmin.getEmail(), defaultAdmin);

        Venue defaultVenue = new Venue("venue_1", "Gran Teatro", "Calle 1 #2-3", "Bogotá", 1200);
        venues.put(defaultVenue.getId(), defaultVenue);

        TicketType vipTicketType = new TicketType("tickettype_1", "VIP", "Entrada preferencial", 150.0);
        TicketType generalTicketType = new TicketType("tickettype_2", "General", "Entrada general", 50.0);
        ticketTypes.put(vipTicketType.getId(), vipTicketType);
        ticketTypes.put(generalTicketType.getId(), generalTicketType);

        Event defaultEvent = new Event(
                "event_1",
                "Concierto de Rock",
                "Experiencia musical en vivo",
                LocalDateTime.now().plusDays(10),
                defaultVenue.getId(),
                vipTicketType.getId(),
                500
        );
        events.put(defaultEvent.getId(), defaultEvent);
    }

    public static Collection<User> getAllUsers() {
        return users.values();
    }

    public static Collection<User> getAllAdmins() {
        return users.values().stream()
                .filter(user -> user.getRole() != null && "ADMIN".equalsIgnoreCase(user.getRole().getName()))
                .collect(Collectors.toList());
    }

    public static User getUserByEmail(String email) {
        return users.get(email);
    }

    public static User getUserById(String id) {
        return users.values().stream().filter(user -> id.equals(user.getId())).findFirst().orElse(null);
    }

    public static User saveUser(User user) {
        users.put(user.getEmail(), user);
        return user;
    }

    public static void deleteUserByEmail(String email) {
        users.remove(email);
    }

    public static Collection<Role> getAllRoles() {
        return roles.values();
    }

    public static Role getRoleByName(String name) {
        return roles.get(name);
    }

    public static Role saveRole(Role role) {
        roles.put(role.getName(), role);
        return role;
    }

    public static Collection<Event> getAllEvents() {
        return events.values();
    }

    public static Event getEventById(String id) {
        return events.get(id);
    }

    public static Event saveEvent(Event event) {
        events.put(event.getId(), event);
        return event;
    }

    public static void deleteEventById(String id) {
        events.remove(id);
    }

    public static Collection<TicketType> getAllTicketTypes() {
        return ticketTypes.values();
    }

    public static TicketType getTicketTypeById(String id) {
        return ticketTypes.get(id);
    }

    public static TicketType saveTicketType(TicketType ticketType) {
        ticketTypes.put(ticketType.getId(), ticketType);
        return ticketType;
    }

    public static void deleteTicketTypeById(String id) {
        ticketTypes.remove(id);
    }

    public static Collection<Venue> getAllVenues() {
        return venues.values();
    }

    public static Venue getVenueById(String id) {
        return venues.get(id);
    }

    public static Venue saveVenue(Venue venue) {
        venues.put(venue.getId(), venue);
        return venue;
    }

    public static void deleteVenueById(String id) {
        venues.remove(id);
    }

    public static Collection<Ticket> getAllTickets() {
        return tickets.values();
    }

    public static Collection<Ticket> getTicketsByEventId(String eventId) {
        return tickets.values().stream().filter(ticket -> eventId.equals(ticket.getEventId())).collect(Collectors.toList());
    }

    public static Ticket getTicketById(String id) {
        return tickets.get(id);
    }

    public static Ticket saveTicket(Ticket ticket) {
        tickets.put(ticket.getId(), ticket);
        return ticket;
    }

    public static void deleteTicketById(String id) {
        tickets.remove(id);
    }

    public static Collection<Order> getAllOrders() {
        return orders.values();
    }

    public static Collection<Order> getOrdersByUserId(String userId) {
        return orders.values().stream().filter(order -> userId.equals(order.getUserId())).collect(Collectors.toList());
    }

    public static Order getOrderById(String id) {
        return orders.get(id);
    }

    public static Order saveOrder(Order order) {
        orders.put(order.getId(), order);
        return order;
    }

    public static void deleteOrderById(String id) {
        orders.remove(id);
    }

    public static Collection<OrderItem> getAllOrderItems() {
        return orderItems.values();
    }

    public static OrderItem getOrderItemById(String id) {
        return orderItems.get(id);
    }

    public static OrderItem saveOrderItem(OrderItem item) {
        orderItems.put(item.getId(), item);
        return item;
    }

    public static void deleteOrderItemById(String id) {
        orderItems.remove(id);
    }
}
