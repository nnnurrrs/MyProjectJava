package org.example.app.services;

import org.example.web.dto.Book;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.*;

@Repository
public class BookRepository implements ProjectRepository<Book>, ApplicationContextAware {

    private final Logger logger = Logger.getLogger(BookRepository.class);
    private final List<Book> repo = new ArrayList<>();
    private ApplicationContext context;

    @Override
    public List<Book> retreiveAll() {
        return new ArrayList<>(repo);
    }

    @Override
    public void store(Book book) {
        book.setId(context.getBean(IdProvider.class).providerId(book));
        logger.info("store new book: " + book);
        repo.add(book);
    }

    @Override
    public boolean removeItemById(String bookIdToRemove) {
        for (Book book : retreiveAll()) {
            if (book.getId().equals(bookIdToRemove)) {
                logger.info("remove book completed: " + book + "  equals " + book.getId().equals(bookIdToRemove));
                return repo.remove(book);
            }
        }
        return false;
    }

    @Override
    public boolean removeItemByRegex(String bookRegexToRemove) {
        //logger.info("store new book: " + "NURS");
        if (bookRegexToRemove != "") {
            for (Book book : retreiveAll()) {
                if (book.getAuthor().equals(bookRegexToRemove) ||
                        book.getTitle().equals(bookRegexToRemove) ||
                        String.valueOf(book.getSize()).equals(bookRegexToRemove)) {
                    logger.info("remove book completed: " + book + "  equals " + book.getAuthor().equals(bookRegexToRemove));
                    logger.info("SIZE REPO " + retreiveAll().toString().toLowerCase());
                    return repo.remove(book);
                }
            }
        }
        return false;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    private void defaultInit() {
        logger.info("default INIT in book repo bean");
    }

    private void defaultDestroy() {
        logger.info("default DESTROY in book repo bean");
    }
}
