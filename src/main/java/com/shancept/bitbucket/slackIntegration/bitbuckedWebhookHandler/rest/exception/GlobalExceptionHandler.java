package com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.rest.exception;

import com.shancept.bitbucket.slackIntegration.slackNotifier.repository.ChannelNotFoundException;
import com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.method.channelCreator.CreatingChannelException;
import com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.method.channelArchiver.ArchiveChannelException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CreatingChannelException.class)
    public ResponseEntity<String> handleCreatingChanelException(CreatingChannelException e) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
    }

    @ExceptionHandler(ArchiveChannelException.class)
    public ResponseEntity<String> handleArchiveChannelException(ArchiveChannelException e) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
    }

    @ExceptionHandler(ChannelNotFoundException.class)
    public ResponseEntity<String> handleChannelNotFoundException(ChannelNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request body: " + e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error: " + e.getMessage());
    }
}
