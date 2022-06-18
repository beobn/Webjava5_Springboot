package com.example.assm_sof3021.controller.pay.vnpay;



import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;


        public class vnpayRefund  {


@RequestMapping("/pay/vpnrefund")
        protected void doGet(
        @RequestParam(name = "order_id", defaultValue = "", required = false) String order_id,
        @RequestParam(name = "trans_date", defaultValue = "", required = false) String trans_date,
        @RequestParam(name = "email", defaultValue = "", required = false) String email,
        @RequestParam(name = "amount", defaultValue = "", required = false) String amount,
        @RequestParam(name = "trantype", defaultValue = "", required = false) String trantype,
                HttpServletRequest request
)  {
        try {
                //vnp_Command = refund
                String vnp_TxnRef = order_id;
                String vnp_TransDate = trans_date;

                int amountt = Integer.parseInt(amount)*100;

                String vnp_TmnCode = com.example.assm_sof3021.controller.pay.vnpay.Config.vnp_TmnCode;
                String vnp_IpAddr = com.example.assm_sof3021.controller.pay.vnpay.Config.getIpAddress(request);

                Map<String, String> vnp_Params = new HashMap<>();
                vnp_Params.put("vnp_Version", "2.1.0");
                vnp_Params.put("vnp_Command", "refund");
                vnp_Params.put("vnp_Amount", String.valueOf(amount));
                vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
                vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
                vnp_Params.put("vnp_OrderInfo", "Kiem tra ket qua GD OrderId:" + vnp_TxnRef);
                vnp_Params.put("vnp_TransDate", vnp_TransDate);
                vnp_Params.put("vnp_IpAddr", vnp_IpAddr);
                vnp_Params.put("vnp_CreateBy", email);
                vnp_Params.put("vnp_TransactionType", trantype);

                Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
                String vnp_CreateDate = formatter.format(cld.getTime());
                vnp_Params.put("vnp_CreateDate", vnp_CreateDate);
                //Build data to hash and querystring
                List fieldNames = new ArrayList(vnp_Params.keySet());
                Collections.sort(fieldNames);
                StringBuilder hashData = new StringBuilder();
                StringBuilder query = new StringBuilder();
                Iterator itr = fieldNames.iterator();
                while (itr.hasNext()) {
                        String fieldName = (String) itr.next();
                        String fieldValue = (String) vnp_Params.get(fieldName);
                        if ((fieldValue != null) && (fieldValue.length() > 0)) {
                                //Build hash data
                                try {
                                        hashData.append(fieldName);
                                        hashData.append('=');
                                        hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                                        //Build query
                                        query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                                        query.append('=');
                                        query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));

                                        if (itr.hasNext()) {
                                                query.append('&');
                                                hashData.append('&');
                                        }
                                }catch (Exception e){
                                        e.printStackTrace();
                                }
                        }
                }
                String queryUrl = query.toString();
                String vnp_SecureHash = com.example.assm_sof3021.controller.pay.vnpay.Config.hmacSHA512(com.example.assm_sof3021.controller.pay.vnpay.Config.vnp_HashSecret , hashData.toString());
                queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
                String paymentUrl = Config.vnp_apiUrl + "?" + queryUrl;
                URL url = new URL(paymentUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                }
                in.close();
                String Rsp = response.toString();
                String respDecode = URLDecoder.decode(Rsp, "UTF-8");
                String[] responseData = respDecode.split("&|\\=");
        }catch (Exception e){
                e.printStackTrace();

        }


        }
        }

