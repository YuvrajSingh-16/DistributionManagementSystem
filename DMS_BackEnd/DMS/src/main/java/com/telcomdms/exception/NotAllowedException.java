package com.telcomdms.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotAllowedException extends Exception{
		
		public NotAllowedException() {
			super("Not allowed..");
		}
		
		public NotAllowedException(String msg) {
			super(msg);
		}

}
