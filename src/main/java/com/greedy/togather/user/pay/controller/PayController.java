package com.greedy.togather.user.pay.controller;




import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
//import org.json.simple.JSONObject;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.greedy.togather.user.pay.dto.PayOrderDTO;
import com.greedy.togather.user.pay.dto.PaymentDTO;
import com.greedy.togather.user.pay.service.PaymentService;
import com.greedy.togather.user.user.model.dto.UserDTO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/pay")
public class PayController {
	
	private final PaymentService paymentService;
	
	public PayController(PaymentService paymentService) {
		this.paymentService = paymentService;
	}
	
	
	@GetMapping("payScreen")
	public String goPayScreen(@RequestParam("projNo") String projNo, @RequestParam("rewardNo") String rewardNo, Model model) {
			//@RequestParam("projNo") String projNo, @RequestParam("rewardNo") String rewardNo, 
			System.out.println("하하");
//			ModelAndView model = new  ModelAndView();
			
//			System.out.println("size >>> " + reqMap.size());//2 이상
//			System.out.println("projNo 1>>>" + reqMap.get("projNo"));// prjoNO=
//			System.out.println("rewardNo >>> " + reqMap.get("rewardNo")); // rewardNo = 
			/* 프로젝트 상세 내용 조회 */
			Map<String, Object> payScreenDetails = paymentService.searchPayScreen(projNo);		
			log.info("[PayController] payScreenDetails : {}", payScreenDetails);
			Map<String, Object> payScreenReward = paymentService.searchRewardScreen(rewardNo);
			
			log.info(rewardNo);
			log.info(projNo);
			
			
			model.addAttribute("payD", payScreenDetails.get("projectNo"));
			model.addAttribute("reward", payScreenReward.get("reward"));
			
			//model.addAllObjects(payScreenDetails);
//			model.setViewName("/user/pay/payScreen");
		
		return "/user/pay/payScreen";
	}
	
	@GetMapping("/payComplete")
	public String payComplete(@RequestParam(value="payNo", required=false) String payNo, Model model) { 
		
		Map<String, Object> paymentList = paymentService.slectPayment(payNo);
		log.info("paymentList : {}" + paymentList);
		model.addAttribute("order", paymentList.get("payment"));
		
		return "/user/pay/payComplete";
	}
	
	@PostMapping("/payComplete")
	public @ResponseBody String postPayComplete(/* @RequestBody Map<String, String> requestMap */@RequestBody PayOrderDTO order) {
		

//		log.info("order : {}",order);
		
		PayOrderDTO orderList = order;
		
		paymentService.registOrder(order);
		paymentService.registDelivery(order);
		paymentService.registPayment(order);
		paymentService.updatefundingAchive(order);
		
		String api_key = "4472688766767282";
		String api_secret = "uVesZr8wTfgxjSI8vfCN61pqnRsyMdmru5w81ZiHhdMH2TMv0qllSYW81Pi8sqeQKEBbIoZNZ4yOerY6";
		
		
		
		return "/pay/payComplete";
	}
	
	@GetMapping("/cancel")
	public String Cancel(@RequestParam(value="orderNo") String orderNo, @RequestParam(value="userNo") String userNo, Model model) {
		
		Map<String, Object> rePaymentList = paymentService.selectFund(orderNo);
		log.info("orderNo : {} " + orderNo);
		log.info("cancel : {}" + rePaymentList);
		model.addAttribute("cancel", rePaymentList.get("rePayment"));
		
						
		return "/user/pay/refund";
	}
	
	@GetMapping("/payFund")
	public String payFund(/*@RequestParam String userNo, Model model*/@AuthenticationPrincipal UserDTO loginUser, @RequestParam(value="userNo", required=false) String userNo, Model model
		, @RequestParam(defaultValue="1") int page) {
		
//		log.info("[BoardController] page : {}", page);
//		
//		Map<String, Object> fundList = paymentService.slectFundList(userNo, page);
//		log.info("fundList : {}" + fundList); 
//		Map<String, Object> refundList = paymentService.selectRefund(userNo, page);
//		log.info(userNo);
//		
//		model.addAttribute("fundList", fundList.get("fundNo"));
//		model.addAttribute("refundList", refundList.get("refund"));
//		log.info("refundList : {} " + refundList);
//		
//		Map<String, String> searchMap = new HashMap<>();
		Map<String, Object> fundList = paymentService.slectFundList(userNo, page);
		model.addAttribute("fundList", fundList.get("fundNo"));
		model.addAttribute("paging", fundList.get("paging"));
		Map<String, Object> refundList = paymentService.selectRefund(userNo);
		model.addAttribute("refundList", refundList.get("refund"));
		return "/user/myPage/fundProject";
	}
	
	@PostMapping("/orderCancel")
	public @ResponseBody String payCancel(@RequestBody PaymentDTO cancel) {
		
		System.out.println("dddd");
		PaymentDTO cancelList = cancel;
		try {
			URL address = new URL("https://api.iamport.kr/users/getToken");
			HttpURLConnection conn = (HttpURLConnection) address.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Accept", "application/json");
			
			conn.setDoOutput(true);
			
			JSONObject cancelData = new JSONObject();
			cancelData.put("imp_key", "4472688766767282");
			cancelData.put("imp_secret", "uVesZr8wTfgxjSI8vfCN61pqnRsyMdmru5w81ZiHhdMH2TMv0qllSYW81Pi8sqeQKEBbIoZNZ4yOerY6");
			
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			bw.write(cancelData.toJSONString());
			bw.flush();
			bw.close();
			
			int result = 0;
			int responseCode = conn.getResponseCode();
			
			System.out.println("응답 코드는 ???" + responseCode);
			
			if(responseCode == 200) {
				paymentService.insertRefund(cancel);
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				StringBuilder sb = new StringBuilder();
				String line = null;
				while((line = br.readLine()) != null) {
					sb.append(line + "\n");
				}
				br.close();
				System.out.println("" + sb.toString());
				return "";
			} else {
				return "";
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "아";
	}


	
}
