package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/user")
public class UserResource {
	/*
	 * 
	 * private UserRepository userRepository;
	 * 
	 * public UserResource(UserRepository userRepository) { this.userRepository =
	 * userRepository; }
	 * 
	 * @GetMapping("/add/{id}/{name}") public ResponseEntity<User>
	 * add(@PathVariable("id") final String id, @PathVariable("name") final String
	 * name, HttpServletResponse response) {
	 * 
	 * userRepository.save(new User(id, name, 2000L));
	 * 
	 * Optional<User> entity = Optional.of(userRepository.findById(id));
	 * 
	 * return entity.isPresent() ? ResponseEntity.ok(entity.get()) :
	 * ResponseEntity.notFound().build();
	 * 
	 * ResponseCookie springCookie = ResponseCookie.from("user-id",
	 * "c2FtLnNtaXRoQGV4YW1wbGUuY29t").httpOnly(false)
	 * .secure(true).path("/").maxAge(60).domain("example.com").build();
	 * 
	 * return ResponseEntity .ok() // .header(HttpHeaders.SET_COOKIE,
	 * springCookie.toString()) .build(); }
	 * 
	 * @GetMapping("/update/{id}/{name}") public User update(@PathVariable("id")
	 * final String id, @PathVariable("name") final String name) {
	 * 
	 * userRepository.update(new User(id, name, 2000L)); return
	 * userRepository.findById(id); }
	 * 
	 * @GetMapping("/all") public Map<String, User> all() { return
	 * userRepository.findAll(); }
	 */}
