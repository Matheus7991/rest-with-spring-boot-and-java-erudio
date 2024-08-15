package br.com.matheus;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.matheus.exceptions.UnsupportedMathOperationException;

@RestController
public class MathController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	
	@GetMapping(value = "/sum/{numerOne}/{numerTwo}")
	public Double sum(
			@PathVariable(value = "numerOne") String numberOne,
			@PathVariable(value = "numerTwo") String numerTwo) throws Exception {
		
		if(!isnNumeric(numberOne) || !isnNumeric(numerTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value");
		}
		return convertToDouble(numberOne) + convertToDouble(numerTwo);
	}

	private Double convertToDouble(String strNumber) {
		if (strNumber == null) return 0D;
		String number = strNumber.replaceAll(",", ".");
		if (isnNumeric(number)) return Double.parseDouble(number);
		return 0D;
	}

	private boolean isnNumeric(String strNumber) {
		if (strNumber == null) return false;
		String number = strNumber.replaceAll(",", ".");
		return number.matches("[-+]?[0-9]*\\.?[0-9]+");
	}
}
