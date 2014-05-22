
package com.team.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.apache.commons.lang.StringEscapeUtils;

import com.team.exeption.VException;

/**
 * @author Trong 24-11-2013 - 01:13:27
 */
@Controller
public abstract class VController {
	private static final Logger LOG = LoggerFactory
			.getLogger(VController.class);

	@SuppressWarnings("static-access")
	@ExceptionHandler
	public void handleException(Exception e, final HttpServletRequest request,
			HttpServletResponse response) {
		try {
			if (e instanceof VException) {
				VException ex = (VException) e;
				LOG.error("handleException: ---------------" + ex.getTechnicalMsg());
				printError(response, ex.getTechnicalMsg(), ex.getErrorCode());

			} else {
				String message = e.getMessage();
				if (message == null) {
					message = e.getClass().getName();
				}
				printError(response,
						new StringEscapeUtils().escapeJava(message),
						"UNKNOWN");
			}
		} catch (IOException e1) {
			LOG.warn(e1.getMessage());
		}

	}

	private void printError(HttpServletResponse response, String message,
			String code) throws IOException {
		response.setContentType("application/json");
		response.getWriter().write(
				String.format(
						"{\"errorCode\":1,\"msg\":\"%s\",\"data\": \"%s\"}",
						message, code));
		response.flushBuffer();

	}
}
