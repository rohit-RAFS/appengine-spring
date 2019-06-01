package com.example.demo;

import com.example.demo.Model.DatabaseController;
import com.google.gson.Gson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.atomic.AtomicLong;


@SpringBootApplication
@RestController
public class RestServices implements ErrorController {

	public static void main(String[] args) {
		SpringApplication.run(RestServices.class, args);
	}





	private final AtomicLong counter = new AtomicLong();
	Account account;
	SendOtpResponse otpresponse;
	ValidateOtpResponse validateOtpResponse;

	//For a NULL Page
	@GetMapping("/")
	public String hello() {
		return "Visit https://www.perpule.com";
	}

	//Handling /error throwback
	@RequestMapping("/error")
	public String handleError() {
		//do something like logging
		return "We encountered an error. Lol, better get to someone with more knowledge!";
	}
	@Override
	public String getErrorPath() {
		return "/error";
	}

	//To check existence of a number(customer in database)
	@RequestMapping("/checkexistence")
	public String checkexistence(@RequestParam(value ="number",defaultValue = "0")String number){
		try {
			Connection conn = DatabaseController.getConnection();

			PreparedStatement getstmt = conn.prepareStatement("SELECT * FROM Customer WHERE PhoneNumber LIKE ?");
			getstmt.setString(1, "%" + number + "%");

			ResultSet rs = getstmt.executeQuery();
			SQLTableEntry sl = new SQLTableEntry();

			sl.SQLRetrieve(rs);
			if(sl.getPhoneNumber()==null){return "no";}
			else {return "yes";}

		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}


	@RequestMapping("/sendotp")
	public SendOtpResponse sendingotp(@RequestParam(value="number", defaultValue="0") String number ) {
		SendOtp so = new SendOtp("", "+91" + number);
		so.Send_OTP();
		Gson g=new Gson();
		otpresponse= g.fromJson(so.getResponseData(),SendOtpResponse.class);
		return otpresponse;

	}

	@RequestMapping("/validateotp")
	public ValidateOtpResponse validatingotp(@RequestParam(value="number", defaultValue="111111") String number,@RequestParam(value="otp", defaultValue="111111") String otp) {
		ValidateOtp votp =new ValidateOtp(number,otp);
		votp.validate_OTP();
		Gson g=new Gson();
		validateOtpResponse= g.fromJson(votp.getResponseData(),ValidateOtpResponse.class);
		return validateOtpResponse;
	}

	@RequestMapping("/validatetoken")
	public String validatingtoken(){
		ValidateToken token=new ValidateToken(validateOtpResponse.getAccess_token());
		token.validate_token();
		return token.getResponseData();
	}

	@RequestMapping("/checkbalance")
	public String checkingbalance(){
		CheckBalance check=new CheckBalance(validateOtpResponse.getAccess_token());
		check.check_balance();
		return check.getResponseData();
	}
}
