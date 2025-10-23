package bm.coder.shop.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bm.coder.shop.dtos.LoginRequest;
import bm.coder.shop.dtos.LoginResponse;
import bm.coder.shop.dtos.Msg;
import bm.coder.shop.dtos.OrderDto;
import bm.coder.shop.dtos.OrderItemDto;
import bm.coder.shop.dtos.RegisterRequest;
import bm.coder.shop.models.AppUser;
import bm.coder.shop.models.Category;
import bm.coder.shop.models.Childcat;
import bm.coder.shop.models.Order;
import bm.coder.shop.models.OrderItem;
import bm.coder.shop.models.Product;
import bm.coder.shop.models.Subcat;
import bm.coder.shop.models.Tag;
import bm.coder.shop.security.CustomUserDetailsService;
import bm.coder.shop.security.JwtUtil;
import bm.coder.shop.services.CategoryService;
import bm.coder.shop.services.ChildcatService;
import bm.coder.shop.services.OrderService;
import bm.coder.shop.services.ProductService;
import bm.coder.shop.services.SubcatService;
import bm.coder.shop.services.TagService;
import bm.coder.shop.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ApiController {

    @Autowired
    private final CategoryService categoryService;

    @Autowired
    private final SubcatService subcatService;
    @Autowired
    private final ChildcatService childcatService;

    @Autowired
    private final ProductService productService;

    @Autowired
    private final TagService tagService;

    @Autowired
    private final OrderService orderService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private final UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @CrossOrigin(origins = "http://localhost:4000")
    @GetMapping("/cats")
    public ResponseEntity<List<Category>> getCats() {
        List<Category> cats = categoryService.all();
        return ResponseEntity.ok(cats);
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @GetMapping("/subcats")
    public ResponseEntity<List<Subcat>> getSubcats() {
        List<Subcat> subcats = subcatService.all();
        return ResponseEntity.ok(subcats);
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @GetMapping("/childcats")
    public ResponseEntity<List<Childcat>> getChildcats() {
        List<Childcat> childubcats = childcatService.all();
        return ResponseEntity.ok(childubcats);
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @GetMapping("/tags")
    public ResponseEntity<List<Tag>> getTags() {
        List<Tag> tags = tagService.all();
        return ResponseEntity.ok(tags);
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = productService.all();
        return ResponseEntity.ok(products);
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @PostMapping("/orders")
    public ResponseEntity<Msg> addOrder(@RequestBody OrderDto orderDto, HttpServletRequest request) { // get token
        String username = jwtUtil.getUserForm(request);
        AppUser user = userService.findByName(username);
        if (username == null || user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new Msg("No user not found", HttpStatus.UNAUTHORIZED.value()));
        }
        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemDto item : orderDto.getItems()) {
            Product product = productService.get(item.getId());
            OrderItem orderItem = new OrderItem();
            orderItem.setCount(item.getCount());
            orderItem.setName(product.getName());
            orderItem.setPrice(product.getPrice());
            orderItem.setImages(product.getImages());
            orderItem.setProductId(product.getId());
            orderItems.add(orderService.addItem(orderItem));
        }
        Order order = new Order();
        order.setTotal(orderDto.getTotal());
        order.setOrderItems(orderItems);
        order.setBuyerId(user.getId());
        order.setBuyerName(user.getName());
        orderService.add(order);
        return ResponseEntity.ok(new Msg("Order added successfully", HttpStatus.OK.value()));
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getName(), request.getPassword()));
            UserDetails userdetails = userDetailsService.loadUserByUsername(request.getName());
            String jwt = jwtUtil.generateToken(userdetails);
            return ResponseEntity.ok(new LoginResponse(jwt, userdetails.getUsername(), "Login success"));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new Msg("Invalid username Password", HttpStatus.UNAUTHORIZED.value()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Msg("Login failed" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }

    }

    @CrossOrigin(origins = "http://localhost:4000")
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            AppUser existUser = userService.findByName(request.getName());
            if (existUser != null) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(new Msg("UserName already exist", HttpStatus.CONFLICT.value()));
            }
            AppUser newuser = new AppUser();
            newuser.setName(request.getName());
            newuser.setPhone(request.getPhone());
            newuser.setPassword(passwordEncoder.encode(request.getPassword()));
            userService.register(newuser);
            return ResponseEntity.ok(new Msg("Registeration successfylly", HttpStatus.OK.value()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Msg("Registeration failed" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }

    }

    @CrossOrigin(origins = "http://localhost:4000")
    @GetMapping("/myorders")
    public ResponseEntity<?> addOrder(HttpServletRequest request) { // get token
        String username = jwtUtil.getUserForm(request);
        AppUser user = userService.findByName(username);
        if (username == null || user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new Msg("No user not found", HttpStatus.UNAUTHORIZED.value()));
        }

        List<Order> myOrders = orderService.getByOrder(user.getId());
        return ResponseEntity.ok(myOrders);
    }
}
