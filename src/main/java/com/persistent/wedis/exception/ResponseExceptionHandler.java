package com.persistent.wedis.exception;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.persistent.wedis.util.WedisStringConstant;

@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(ResponseExceptionHandler.class);

	/**
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(value = { Exception.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ResponseEntity<JSONObject> handleUnknownException(Exception ex, WebRequest request) {
		// log.debug("Internal Server Error\n",ex);
		JSONObject response = new JSONObject();
		try {
			response.put(WedisStringConstant.ERROR, ex.getMessage());
		} catch (JSONException e) {
			log.error("JSONException");
		}
		return ResponseEntity.ok(response);
	}

}