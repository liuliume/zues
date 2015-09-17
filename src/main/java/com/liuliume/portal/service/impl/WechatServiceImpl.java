package com.liuliume.portal.service.impl;

import java.security.MessageDigest;
import java.util.Arrays;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.liuliume.portal.common.Constants;
import com.liuliume.portal.model.wechat.WechatCheckModel;
import com.liuliume.portal.service.WechatService;

@Service
public class WechatServiceImpl implements WechatService{
	
	private Logger logger = LoggerFactory.getLogger(WechatServiceImpl.class);
	
	private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5',
        '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	@Override
	public String validate(WechatCheckModel model) throws Exception{
		if(null==model){
			return null;
		}
//		String token = WechatUtil.getAccess_token();
		String token = Constants.Token;
		
		String signature = model.getSignature();
		Long timestamp = model.getTimestamp();
		String echoStr = model.getEchostr();
		Long nonce = model.getNonce();
		
		String[] arr = {token,timestamp.toString(),nonce.toString()};
		logger.info("arr:{}",arr);
		Arrays.sort(arr);
		String str = arr[0]+arr[1]+arr[2];
		logger.info("after sort:{}",str);
		
//		MessageDigest digest = MessageDigest.getInstance("SHA1");
//		digest.update(str.getBytes());
//		String sha = getFormattedText(digest.digest());
        String sha = DigestUtils.sha1Hex(str);
		if(sha.equalsIgnoreCase(signature)){
			return echoStr;
		}
		
		return "error";
	}
	
	private String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        // 把密文转换成十六进制的字符串形式
        for (int j = 0; j < len; j++) {             
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }
}
