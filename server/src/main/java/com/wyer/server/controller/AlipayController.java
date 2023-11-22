package com.wyer.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.wyer.server.common.AlipayConfig;
import com.wyer.server.common.AuthAccess;
import com.wyer.server.entity.Order;
import com.wyer.server.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Function: 支付宝沙箱接口类
 * Writer: wyer
 * Date: 2023/12/05 16:26
 **/
@RestController
@RequestMapping("/alipay")
public class AlipayController {

    /**
     * 沙箱网关
     */
    private static final String GATEWAY_URL = "https://openapi-sandbox.dl.alipaydev.com/gateway.do";

    /**
     * 格式
     */
    private static final String FORMAT = "JSON";

    /**
     * 格式
     */
    private static final String CHARSET = "UTF-8";

    /**
     * 加密算法
     */
    private static final String SIGN_TYPE = "RSA2";

    /**
     *服务器ip
     */
    @Value("${ip:localhost}")
    String ip;

    /**
     * 服务器端口
     */
    @Value("${server.port}")
    String port;

    @Resource
    private AlipayConfig alipayConfig;

    @Autowired
    OrderServiceImpl orderService;

    /**
     * 沙箱支付接口
     * @param oid
     * @param httpResponse
     * @throws Exception
     */
    @AuthAccess
    @GetMapping("/pay")
    public void pay(Integer oid, HttpServletResponse httpResponse) throws Exception {
        Order order = orderService.selectOrderByOid(oid);
        AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY_URL, alipayConfig.getAppId(),
                alipayConfig.getAppPrivateKey(), FORMAT, CHARSET, alipayConfig.getAliPublicKey(), SIGN_TYPE);
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setNotifyUrl(alipayConfig.getNotifyUrl());
        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no", order.getOid());
        bizContent.put("total_amount", order.getTotal());
        bizContent.put("subject", order.getOrdername());
        bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");
        request.setBizContent(bizContent.toJSONString());
        request.setReturnUrl("http://" + ip + ":" + port + "/space/user/order");

        String form = "";
        try {
            form = alipayClient.pageExecute(request).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset=" + CHARSET);
        httpResponse.getWriter().write(form);
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();

    }

    /**
     * 支付完成反馈接口
     * @param request
     */
    @AuthAccess
    @PostMapping("/notify")
    public void payNotify(HttpServletRequest request) {
        try {
            if (request.getParameter("trade_status").equals("TRADE_SUCCESS")) {
                Map<String, String> params = new HashMap<>();
                Map<String, String[]> requestParams = request.getParameterMap();
                for (String name : requestParams.keySet()) {
                    params.put(name, request.getParameter(name));
                }
                String sign = params.get("sign");
                String content = AlipaySignature.getSignCheckContentV1(params);
                boolean chechSignature = AlipaySignature.rsa256CheckContent(content, sign, alipayConfig.getAliPublicKey(),
                        "UTF-8");
                if (chechSignature) {
                    String oid = params.get("out_trade_no");
                    String gmtPayment = params.get("gmt_payment");
                    String alipayTradeNo = params.get("trade_no");
                    orderService.updateOrder(Integer.parseInt(oid), gmtPayment, alipayTradeNo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
