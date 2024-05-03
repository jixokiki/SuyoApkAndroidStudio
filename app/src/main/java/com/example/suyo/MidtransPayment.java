//package com.example.suyo;
//
//import okhttp3.*;
//import org.json.*;
//
//public class MidtransPayment {
//
//    public void initiatePayment(int totalPrice, String orderId, String customerName, String customerEmail) {
//        OkHttpClient client = new OkHttpClient();
//
//        MediaType mediaType = MediaType.parse("application/json");
//        RequestBody body = RequestBody.create(mediaType, "{\n    \"payment_type\": \"bank_transfer\",\n    \"transaction_details\": {\n        \"order_id\": \"" + orderId + "\",\n    \"customer_details\": {\n        \"first_name\": \"" + customerName + "\",\n        \"email\": \"" + customerEmail + "\"\n    }\n}");
//        Request request = new Request.Builder()
//                .url("https://api.sandbox.midtrans.com/v2/charge")
//                .post(body)
//                .addHeader("Content-Type", "application/json")
//                .addHeader("Authorization", "Basic YOUR_MIDTRANS_API_KEY") // Ganti dengan API key Midtrans Anda
//                .build();
//
//        try {
//            Response response = client.newCall(request).execute();
//            String responseData = response.body().string();
//            JSONObject jsonResponse = new JSONObject(responseData);
//            String redirectUrl = jsonResponse.getString("redirect_url");
//
//            // Redirect user to Midtrans payment page
//            // You can open this URL in a WebView or redirect the user to this URL
//            // Example:
//            // openWebView(redirectUrl);
//            // or
//            // redirectToUrl(redirectUrl);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
//
