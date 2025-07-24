package com.portfolio.website.Service;

import java.util.List;

// import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.website.Model.Message;
import com.portfolio.website.Repository.MessageRepo;

@Service
public class MessageService {
    @Autowired
    private MessageRepo messageRepo;

    public MessageService(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    public List<Message> getAllMessages() {
        return messageRepo.findAll();
    }

    public Message saveMessage(Message message) {
        return messageRepo.save(message);
    }

    public void deleteMessage(long id) {
        messageRepo.deleteById(id);
    }

    public Message getMessageById(long id) {
        return messageRepo.findById(id).orElse(null);
    }

}
