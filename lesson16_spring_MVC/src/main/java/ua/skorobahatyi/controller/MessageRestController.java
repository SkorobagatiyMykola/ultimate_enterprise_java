package ua.skorobahatyi.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.skorobahatyi.dto.Message;
import ua.skorobahatyi.exception.MessageNotFoundException;

import java.net.URI;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/messages")
public class MessageRestController {
    private AtomicInteger idGenerator = new AtomicInteger();
    private Map<Integer, Message> messageMap = new ConcurrentHashMap<>();

    @PostMapping
    public ResponseEntity<Message> addMessage(@RequestBody Message messageRequestEntity) {
        //Optional.ofNullable(m)
        var message = addNewMessage(messageRequestEntity);

        return ResponseEntity.status(HttpStatus.CREATED)
                .location(URI.create("http://localhost:8080/v16/messages/" + message.getId()))
                .body(message);
    }

    @GetMapping("/{id}")
    private Message getMessageById(@PathVariable Integer id) {
        return Optional.ofNullable(messageMap.get(id))
                .orElseThrow(()->new MessageNotFoundException("Cannot find message by id = "+id));
    }

    @GetMapping
    public Collection<Message> getAll() {
        return messageMap.values();
    }

    private Message addNewMessage(Message message) {
        message.setId(idGenerator.incrementAndGet());
        messageMap.put(message.getId(), message);
        return message;
    }
}
