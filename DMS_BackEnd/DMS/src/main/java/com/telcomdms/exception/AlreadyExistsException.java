package com.telcomdms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AlreadyExistsException extends Exception{
		
		public AlreadyExistsException() {
			super("Already Exists..");
		}
		
		public AlreadyExistsException(String msg) {
			super(msg);
		}

}
