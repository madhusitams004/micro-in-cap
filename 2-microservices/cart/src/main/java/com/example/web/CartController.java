package com.example.web;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.CartLine;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private RedisTemplate<String, CartLine> redisTemplate;

	private HashMap<Integer, CartLine> cart = new HashMap<>(); // 

	@PostMapping("/{id}")
	public ResponseEntity<CartLine> add(@PathVariable(name="id") String id, @RequestBody CartLine cartLine) {
		HashOperations<String, Integer, CartLine> hashOps = redisTemplate.opsForHash();
		hashOps.put(id, cartLine.getItem().getId(), cartLine);
//		cart.put(cartLine.getQty(), cartLine);
		return new ResponseEntity<CartLine>(cartLine, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> get(@PathVariable String id) {
		HashOperations<String, Integer, CartLine> hashOps = redisTemplate.opsForHash();
		List<CartLine> cartLines = hashOps.values(id);
//		List<CartLine> cartLines=cart.keySet()
//		.stream()
//		.map(key->cart.get(key))
//		.collect(Collectors.toList());
		return new ResponseEntity<>(cartLines, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) {
//		redisTemplate.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
