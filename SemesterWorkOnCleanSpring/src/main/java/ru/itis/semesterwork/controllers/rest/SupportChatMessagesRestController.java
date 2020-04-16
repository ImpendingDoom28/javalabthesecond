package ru.itis.semesterwork.controllers.rest;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.semesterwork.dto.MessageDto;
import ru.itis.semesterwork.services.MessageService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/1.0/messages")
public class SupportChatMessagesRestController {

    @Autowired
    private MessageService messageService;

    private static final Map<String, List<MessageDto>> pagesWithMessages = new HashMap<>();

    @PostMapping
    @CrossOrigin(origins = {"http://localhost:3000"})
    public ResponseEntity<?> receiveMessage(@RequestBody MessageDto messageDto) {
        if(!pagesWithMessages.containsKey(messageDto.getPageId())) {
            pagesWithMessages.put(messageDto.getPageId(), new ArrayList<>());
        }
        messageService.saveMessage(messageDto);
        for (List<MessageDto> pageMessages: pagesWithMessages.values()) {
            synchronized (pageMessages) {
                pageMessages.add(messageDto);
                pageMessages.notifyAll();
            }
        }
        return ResponseEntity.ok().build();
    }

    @SneakyThrows
    @GetMapping
    @CrossOrigin(origins = {"http://localhost:3000"})
    public ResponseEntity<List<MessageDto>> getMessagesForPage(@RequestParam("pageId") String pageId) {
        synchronized (pagesWithMessages.get(pageId)) {
            if(pagesWithMessages.get(pageId).isEmpty()) {
                pagesWithMessages.get(pageId).wait();
            }
        }
        List<MessageDto> response = messageService.findAllMessages();
        pagesWithMessages.get(pageId).clear();
        return ResponseEntity.ok(response);
    }
}
